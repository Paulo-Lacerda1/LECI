#!/bin/bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ROOT_DIR="$(cd "${SCRIPT_DIR}/.." && pwd)"
BUILD_DIR="${ROOT_DIR}/build"
BIN_DIR="${ROOT_DIR}/bin"

usage() {
    cat <<EOF >&2
Uso: $0 intervalo
  intervalo   Ex.: 501-505 ou "501 505"
EOF
}

RANGE=""
TARGET_EXEC="main"

# ---------------- PARSE -----------------

if [[ $# -eq 0 ]]; then
    usage
    exit 1
fi

# 1º argumento: intervalo
if [[ "$1" =~ ^[0-9]+-[0-9]+$ ]]; then
    RANGE="$1"
elif [[ "$1" =~ ^[0-9]+$ ]] && [[ $# -ge 2 ]] && [[ "$2" =~ ^[0-9]+$ ]]; then
    RANGE="$1-$2"
    shift
else
    echo "Intervalo invalido." >&2
    usage
    exit 1
fi

# ---------------- BUILD -----------------

cd "${BUILD_DIR}"
make clean && make

cd "${BIN_DIR}"

# validar executável 'main'
if [[ ! -x "${TARGET_EXEC}" ]]; then
    echo "Executavel 'main' nao encontrado no bin/." >&2
    exit 1
fi

# prefixar ./ se for nome simples
if [[ "${TARGET_EXEC}" != /* && "${TARGET_EXEC}" != ./* ]]; then
    TARGET_EXEC="./${TARGET_EXEC}"
fi

# ---------------- EXECUTAR -----------------

"${TARGET_EXEC}" -b -r "${RANGE}" > meu.txt
"${TARGET_EXEC}" -b > prof.txt

meld meu.txt prof.txt
