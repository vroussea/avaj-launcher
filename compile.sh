#!/usr/bin/env bash
mkdir compil
if [ "$(uname)" == "Darwin" ]; then
    javac -cp "./lib/junit-4.12.jar:./lib/mockito-core-1.10.19.jar" $(find . -name "*.java") -d ./compil/
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    javac -cp "./lib/junit-4.12.jar:./lib/mockito-core-1.10.19.jar" $(find . -name "*.java") -d ./compil/
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
    javac -cp "./lib/junit-4.12.jar;./lib/mockito-core-1.10.19.jar" $(find . -name "*.java") -d ./compil/
fi
java -cp compil/ main.AvajLauncher $@
rm -r compil/