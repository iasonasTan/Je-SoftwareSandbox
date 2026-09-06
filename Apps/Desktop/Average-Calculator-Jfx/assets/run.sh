#!/bin/bash

java \
    --module-path ".:libs:libs/javafx-sdk-25.0.3/lib" \
    --add-modules javafx.fxml,javafx.base,javafx.controls,javafx.graphics,JeJFX \
    --enable-native-access=javafx.graphics \
    app.avcalc.main.Main