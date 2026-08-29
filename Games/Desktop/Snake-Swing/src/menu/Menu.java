package menu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Menu extends JPanel {
    private final List<OnStartGameListener> listeners=new ArrayList<>();

    // gui
    private final JButton startGame_button=new JButton("Start game"),
                exitGame_button=new JButton("Exit");
    private final JLabel title_label=new JLabel("Snake Game");

    public Menu() {
        setPreferredSize(new Dimension(600, 400));
        initGUI();
    }

    public void addOnStartGameListener(OnStartGameListener l) {
        listeners.add(l);
    }

    public interface OnStartGameListener {
        void startGame();
    }

    private void initGUI() {
        add(title_label);
        add(startGame_button);
        add(exitGame_button);

        exitGame_button.setFocusable(false);
        startGame_button.setFocusable(false);

        startGame_button.addActionListener(ae -> {
            listeners.forEach(OnStartGameListener::startGame);
        });

        exitGame_button.addActionListener(ae -> {
            System.exit(69);
        });
    }

}
