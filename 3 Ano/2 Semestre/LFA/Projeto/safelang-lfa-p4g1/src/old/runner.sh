#!/bin/zsh

antlr4-clean
antlr4 -visitor Safelang.g4
antlr4-build

