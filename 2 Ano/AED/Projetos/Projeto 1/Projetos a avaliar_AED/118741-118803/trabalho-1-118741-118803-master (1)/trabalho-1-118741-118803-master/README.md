[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/0MZJIdpt)
# Trabalho 1 AED 2024-2025

Este é o código-fonte para o primeiro projeto de AED 2024-2025.

## Ambiente de programação

Recomendamos vivamente que trabalhem em ambiente Linux
ou dentro do [WSL em Windows][wsl].
Nesse ambiente, podem instalar as ferramentas necessárias facilmente:

```bash
sudo apt install build-essential git wget
sudo snap install --classic code            # if you want VS Code 
```

[wsl]: https://learn.microsoft.com/en-us/windows/wsl/install

As instruções neste documento e nos ficheiros fornecidos
presumem que estão a trabalhar nesse tipo de ambiente.
_You have been warned!_

## Ficheiros

- `imageBW.c` - implementação do módulo (a COMPLETAR)
- `imageBW.h` - interface do módulo
- `instrumentation.[ch]` - módulo para contagens de operações e medição de tempos
- `imageBWTest.c` - programa de teste simples
- `imageBWTool.c` - programa de teste mais versátil
- `Makefile` - regras para compilar e testar usando `make`
- `imageDiff.py` - script python para medir diferenças entre imagens

- `README.md` - estas informações que está a ler

## Descarregar imagens

- `make pbm` - para descarregar imagens para pasta `pbm/`
- `make setup` - para descarregar imagens para testes em `pbmt/`

## Compilar

- `make` - Compila e gera os programas de teste.
- `make clean` - Limpa ficheiros objeto e executáveis.

## Testar

- `make test1` - para correr o `test1` (também há `test2`, `test3`, ...)
- `make tests` - para correr todos os testes


## Atualizar repositório

Dada a natureza do trabalho, poderá ser necessário
atualizar repositório base (upstream) deste projeto.
Se isso acontecer, deverá atualizar o seu repositório de duas formas

Pode actualizar através da página do repositório.
Sempre que o repositório original contiver commits novos em relação ao seu pode clicar em:

```
Sync fork
```

Se quiser actualizar através da linha de comandos:

```bash
git remote add upstream git@github.com:detiuaveiro/detiuaveiro-aed-2024-trabalho-1-aed-imageBW-public.git

git fetch upstream
git checkout master
git merge --allow-unrelated-histories upstream/master
# (Resolver quaisquer conflitos que surjam...)
git commit
```
