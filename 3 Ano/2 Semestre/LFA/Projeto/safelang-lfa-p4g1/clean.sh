#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
SRC_DIR="$ROOT_DIR/src"

rm -rf "$ROOT_DIR/build"

rm -f "$SRC_DIR"/*.class
rm -f "$SRC_DIR"/rational/*.class
rm -f "$SRC_DIR"/.antlr/*.class
rm -f "$SRC_DIR"/*.tokens "$SRC_DIR"/*.interp
rm -f "$SRC_DIR"/.antlr/*.tokens "$SRC_DIR"/.antlr/*.interp

rm -f "$SRC_DIR"/SafelangLexer.java \
      "$SRC_DIR"/SafelangParser.java \
      "$SRC_DIR"/SafelangBaseListener.java \
      "$SRC_DIR"/SafelangListener.java \
      "$SRC_DIR"/SafelangBaseVisitor.java \
      "$SRC_DIR"/SafelangVisitor.java

echo "Ficheiros gerados removidos."
