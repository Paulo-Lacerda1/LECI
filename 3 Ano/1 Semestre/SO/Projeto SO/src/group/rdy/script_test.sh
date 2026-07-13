#!/bin/bash

cd ~/Desktop/ProjetoSO/somm25nm-g21/build || exit 1

make clean && make || exit 1

cd ../bin || exit 1

./main -b -r 501-505 > meu.txt
./main -b > prof.txt

meld meu.txt prof.txt

rm meu.txt prof.txt
