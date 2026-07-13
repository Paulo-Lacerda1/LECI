#!/bin/bash

python3 tpi2_tests.py > output.txt

code --diff  output.txt tpi2_results.txt
