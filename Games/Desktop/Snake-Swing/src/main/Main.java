package main;

import game.Game;
import menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static final Main instance=new Main();

    private final Game game=new Game();
    private final Menu menu=new Menu();
    private final MainWindow window= new MainWindow();

    private Main() {
        menu.addOnStartGameListener(this::startGame);
        game.addBackListener(ae -> {
            window.setContentPane(menu);
            game.stop();
        });
        window.setContentPane(menu);
    }

    public void startGame() {
        window.setContentPane(game);
        game.start();
    }

    private void show() {
    }

    private static final class MainWindow extends JFrame {

        public MainWindow() {
            setTitle("Snake Game");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);
        }

        @Override
        public void setContentPane(Container contentPane) {
            super.setContentPane(contentPane);
            pack();
            contentPane.requestFocus();
        }
    }

    public static void main(String[] args) {
        instance.show();
    }
}
