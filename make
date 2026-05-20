#!/bin/bash
# This can be used if you have no `make` binary
if [ $# -eq 0 ] || [ "$1" == "target" ]; then
  ./gradlew jar
  exit
fi
if [ "$1" == "test" ]; then
  java -jar build/libs/tic-tac-toe.jar
fi
if [ "$1" == "fast" ]; then
  ./make target && ./make test
fi