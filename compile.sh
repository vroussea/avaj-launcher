#!/usr/bin/env bash
find . -name '*.java' > sources.txt
mkdir compil
if [[ "$(uname)" == "Darwin" ]]; then
    javac -cp "./lib/junit-4.12.jar:./lib/mockito-core-1.10.19.jar" @sources.txt -d ./compil/
elif [[ "$(expr substr $(uname -s) 1 5)" == "Linux" ]]; then
    javac -cp "./lib/junit-4.12.jar:./lib/mockito-core-1.10.19.jar" @sources.txt -d ./compil/
elif [[ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]]; then
    javac -cp "./lib/junit-4.12.jar;./lib/mockito-core-1.10.19.jar" @sources.txt -d ./compil/
fi
java -cp compil/ tower.AvajLauncher $@
rm -r compil/