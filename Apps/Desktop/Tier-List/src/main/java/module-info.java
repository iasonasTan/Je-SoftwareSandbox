module App {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires JeLib.io;
    requires JeLib.core;
    requires JeJFX;

    exports tierlist;
    exports tierlist.tier;
    exports tierlist.images;
}