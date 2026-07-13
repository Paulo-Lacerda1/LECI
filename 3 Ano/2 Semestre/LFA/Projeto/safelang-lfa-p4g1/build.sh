#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
SRC_DIR="$ROOT_DIR/src"
ANTLR_DIR="$ROOT_DIR/build/antlr"
CLASSES_DIR="$ROOT_DIR/build/classes"

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

generate_parser() {
    local antlr_path="${ANTLR4_PATH:-/usr/local/lib}"
    local antlr_jar="${ANTLR4_JAR:-}"

    if [ -z "$antlr_jar" ]; then
        antlr_jar="$(find_first_file "$antlr_path"/antlr-4*-complete.jar /usr/local/lib/antlr-4*-complete.jar 2>/dev/null || true)"
    fi

    cd "$SRC_DIR"
    if [ -n "$antlr_jar" ]; then
        java -jar "$antlr_jar" -visitor -o "$ANTLR_DIR" Safelang.g4
    elif command -v antlr4 >/dev/null 2>&1; then
        antlr4 -visitor -o "$ANTLR_DIR" Safelang.g4
    else
        echo "Erro: nao foi encontrado antlr4 nem antlr-4*-complete.jar." >&2
        echo "Defina ANTLR4_PATH ou ANTLR4_JAR." >&2
        exit 1
    fi
}

mkdir -p "$ANTLR_DIR" "$CLASSES_DIR"
generate_parser

CP="$(classpath_entries)"

javac -cp "$CP" -d "$CLASSES_DIR" \
    "$ANTLR_DIR"/*.java \
    "$SRC_DIR"/rational/FractionType.java \
    "$SRC_DIR"/rational/IntegerType.java \
    "$SRC_DIR"/rational/Rational.java \
    "$SRC_DIR"/rational/RationalCore.java \
    "$SRC_DIR"/CompilerReturn.java \
    "$SRC_DIR"/Execute.java \
    "$SRC_DIR"/ExecuteMain.java \
    "$SRC_DIR"/JavaCompiler.java \
    "$SRC_DIR"/STGBuilder.java \
    "$SRC_DIR"/SafelangMain.java \
    "$SRC_DIR"/SymbolTable.java \
    "$SRC_DIR"/TypeChecker.java \
    "$SRC_DIR"/TypeDescriptor.java \
    "$SRC_DIR"/TypeSystem.java

echo "Compilador compilado em build/classes."
