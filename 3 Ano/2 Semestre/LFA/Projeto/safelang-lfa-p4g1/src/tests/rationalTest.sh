#!/bin/zsh

cd ..
rm rational/*.class
javac rational/RationalTest.java
java rational.RationalTest
cd rational