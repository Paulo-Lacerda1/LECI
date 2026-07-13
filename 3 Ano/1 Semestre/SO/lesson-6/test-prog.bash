#!/bin/bash

# Verifica se há pelo menos dois argumentos
if [ $# -lt 2 ]; then
  echo "Uso: $0 N comando [args...]"
  exit 1
fi

# O primeiro argumento é o número de repetições
N=$1
shift  # remove o primeiro argumento, deixando apenas o comando

# Verifica se N é um número
if ! [[ "$N" =~ ^[0-9]+$ ]]; then
  echo "Erro: o primeiro argumento deve ser um número inteiro."
  exit 1
fi

# Executa o comando N vezes
for ((i=1; i<=N; i++)); do
  echo "Execução $i:"
  "$@"
done
