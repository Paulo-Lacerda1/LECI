#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
BUILD_DIR="$ROOT_DIR/build"
CLASSES_DIR="$BUILD_DIR/classes"
PROGRAMS_DIR="$BUILD_DIR/programs"
STATE_FILE="$BUILD_DIR/last_compiled"

find_first_file() {
    local file
    for file in "$@"; do
        if [ -f "$file" ]; then
            printf '%s\n' "$file"
            return 0
        fi
    done
    return 1
}

classpath_entries() {
    local antlr_path="${ANTLR4_PATH:-/usr/local/lib}"
    local antlr_jar="${ANTLR4_JAR:-}"
    local st_jar="${ST_JAR:-}"

    if [ -z "$antlr_jar" ]; then
        antlr_jar="$(find_first_file "$antlr_path"/antlr-4*-complete.jar /usr/local/lib/antlr-4*-complete.jar 2>/dev/null || true)"
    fi
    if [ -z "$st_jar" ]; then
        st_jar="$(find_first_file "$antlr_path"/ST-*.jar /usr/local/lib/ST-*.jar 2>/dev/null || true)"
    fi

    if [ -n "$antlr_jar" ]; then
        printf '%s:' "$antlr_jar"
    fi
    if [ -n "$st_jar" ]; then
        printf '%s:' "$st_jar"
    fi
    printf '%s' "${CLASSPATH:-}"
}

if [ "$#" -gt 0 ]; then
    TARGET="$1"
    shift
    TARGET="$(basename -- "$TARGET")"
    TARGET="${TARGET%.sl}"
    TARGET="${TARGET%.java}"
    TARGET="${TARGET%.class}"
    CLASS_NAME="${TARGET//-/_}"
elif [ -f "$STATE_FILE" ]; then
    CLASS_NAME="$(cat "$STATE_FILE")"
else
    echo "Erro: nenhum programa compilado. Use ./compile.sh <programa.sl> primeiro." >&2
    exit 1
fi

if [ ! -f "$PROGRAMS_DIR/$CLASS_NAME.class" ]; then
    echo "Erro: classe compilada nao encontrada: $PROGRAMS_DIR/$CLASS_NAME.class" >&2
    exit 1
fi

CP="$PROGRAMS_DIR:$CLASSES_DIR:$(classpath_entries)"
java -cp "$CP" "$CLASS_NAME" "$@"
