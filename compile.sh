#!/usr/bin/env bash
mkdir compil
javac -cp "./lib/junit-4.12.jar" $(find . -name "*.java") -d ./compil/
java -cp compil/ main.AvajLauncher $@
rm -r compil/