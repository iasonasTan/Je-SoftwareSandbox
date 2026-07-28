#!/bin/bash
set -e

clear

./gradlew clean

./gradlew installDist

PROJECT_NAME="LibraryCatalogGJFX"

echo "Jackaging..."
jpackage \
  --input "build/install/${PROJECT_NAME}/lib" \
  --main-jar "${PROJECT_NAME}.jar" \
  --main-class "com.libcat.main.Main" \
  --name "LibraryCatalogApp" \
  --type "app-image" \
  --dest "build/package"

echo "Compiling C runner..."

gcc LibraryCatalog.c -o "build/install/${PROJECT_NAME}/LibraryCatalog.bin"

echo "Creating zip..."

cd build/install

zip -r ../../LibraryCatalogApp.zip .