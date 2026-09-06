#!/bin/bash

echo "Compiling Java code..."
javac -Xlint:unchecked -Xdiags:verbose -d out server/utils/LimitedSizeList.java
javac -Xlint:unchecked -Xdiags:verbose -d out server/utils/RainEffect.java
javac -Xlint:unchecked -Xdiags:verbose -d out server/main/Server.java
cp -r res/ out/

echo "Creating jar file..."
jar --create --file App-Server-Ascii-Art.jar --main-class server/main/Server -C out .