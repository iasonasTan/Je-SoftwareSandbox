#!/bin/bash

echo "Removing .class files..."
find . -type f -name "*.class" -print -delete

echo "Removing out directory..."
rm -rf out

echo "Removing jar outputs..."
rm App-Server-Ascii-Art.jar

echo "Clear: Done!"