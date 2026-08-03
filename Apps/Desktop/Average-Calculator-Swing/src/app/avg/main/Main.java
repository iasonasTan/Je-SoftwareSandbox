package app.avg.main;

import lib.io.Configuration;
import lib.gui.UI;
import lib.gui.AbstractScreen;

import java.awt.*;

import app.avg.gui.*;
import app.avg.computer.*;

public class Main {
    public static void main(String[] args) {
        Configuration.init("avg_counter");
        UI.init();

        Computer computer = new Computer();
        Gui gui = new Gui(computer);

        // TODO Make setting window's size easier
        gui.visible(false);
        AbstractScreen.getFrame().setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        AbstractScreen.getFrame().setLocation(screenSize.width/2, screenSize.height/2);
        AbstractScreen.getFrame().setSize(290, 200);
        gui.visible(false);
    }
}