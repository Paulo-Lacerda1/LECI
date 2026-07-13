#!/usr/bin/env bash
set -euo pipefail

if [ "$#" -ne 1 ]; then
    echo "Uso: $0 <programa.sl>" >&2
    exit 1
fi

ROOT_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
SRC_DIR="$ROOT_DIR/src"
BUILD_DIR="$ROOT_DIR/build"
CLASSES_DIR="$BUILD_DIR/classes"
GENERATED_DIR="$BUILD_DIR/generated"
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

PROGRAM="$1"
if [ ! -f "$PROGRAM" ]; then
    echo "Erro: ficheiro nao encontrado: $PROGRAM" >&2
    exit 1
fi

PROGRAM_DIR="$(CDPATH= cd -- "$(dirname -- "$PROGRAM")" && pwd)"
PROGRAM_ABS="$PROGRAM_DIR/$(basename -- "$PROGRAM")"
BASE_NAME="$(basename -- "$PROGRAM_ABS")"
CLASS_NAME="${BASE_NAME%.*}"
CLASS_NAME="${CLASS_NAME//-/_}"

"$ROOT_DIR/build.sh"

mkdir -p "$GENERATED_DIR" "$PROGRAMS_DIR"

CP="$CLASSES_DIR:$(classpath_entries)"

cd "$SRC_DIR"
java -cp "$CP" SafelangMain "$PROGRAM_ABS"

if [ ! -f "$SRC_DIR/$CLASS_NAME.java" ]; then
    echo "Erro: o compilador nao gerou $CLASS_NAME.java." >&2
    exit 1
fi

mv "$SRC_DIR/$CLASS_NAME.java" "$GENERATED_DIR/$CLASS_NAME.java"
javac -cp "$CP" -d "$PROGRAMS_DIR" "$GENERATED_DIR/$CLASS_NAME.java"

printf '%s\n' "$CLASS_NAME" > "$STATE_FILE"
echo "Programa compilado: $CLASS_NAME"
