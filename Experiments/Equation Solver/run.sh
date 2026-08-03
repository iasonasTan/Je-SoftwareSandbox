#!/usr/bin/env bash
cd src/
rustc main.rs -o main
mv main ../out/
cd ../out
chmod +x main
./main
