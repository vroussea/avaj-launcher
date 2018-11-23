#!/usr/bin/env bash
mkdir compil
javac $(find . -name "*.java") -d ./compil/
java -cp compil/ main.AvajLauncher $@
rm -r compil/