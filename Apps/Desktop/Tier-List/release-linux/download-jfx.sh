#!/bin/bash

URL="https://download.oracle.com/java/26/latest/javafx-26_linux-x64_bin-sdk.tar.gz"
INSTALL_DIR="jfx-linux"

mkdir -p $INSTALL_DIR
echo "Downloading JavaFX SDK..."
wget -qO- $URL | tar -xz -C $INSTALL_DIR --strip-components=1

echo "JavaFX 25.0.2 installed to $INSTALL_DIR"
