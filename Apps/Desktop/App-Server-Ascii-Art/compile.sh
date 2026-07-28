#!/bin/bash
echo "Compiling Java code..."
javac -Xlint:unchecked -Xdiags:verbose server/utils/LimitedSizeList.java
javac -Xlint:unchecked -Xdiags:verbose server/utils/RainEffect.java
javac -Xlint:unchecked -Xdiags:verbose server/main/Server.java
