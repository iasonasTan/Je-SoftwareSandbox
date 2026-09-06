#!/bin/bash

rm -rf out

mkdir out

cd src
javac -d ../out --module-path ".:../libs:../libs/javafx-sdk-25.0.3/lib" --add-modules javafx.fxml,javafx.base,javafx.controls,javafx.graphics,JeJFX app/avcalc/main/Main.java app/avcalc/logic/Calculator.java app/avcalc/controller/HomeController.java
cd ..

cp -r resources/* out/

cp -r libs/ out/

cp assets/run.sh out/
