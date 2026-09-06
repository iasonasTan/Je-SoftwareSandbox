#!/bin/bash
echo "Compiling Java code..."
javac -Xlint:unchecked -Xdiags:verbose -d out server/utils/LimitedSizeList.java
javac -Xlint:unchecked -Xdiags:verbose -d out server/utils/RainEffect.java
javac -Xlint:unchecked -Xdiags:verbose -d out server/main/Server.java
cp -r res/ out/
