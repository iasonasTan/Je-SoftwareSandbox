package app.menu;

import app.Main;
import app.game.Game;
import lib.gui.AbstractScreen;
import lib.gui.UI;
import lib.gui.layout.VerticalFlowLayout;
import lib.gui.style.*;
import lib.io.Resources;
import lib.media.Sound;

import javax.swing.*;
import java.awt.*;

public class Menu extends AbstractScreen {
    // swing components
    private final JButton mStartGameButton = new JButton("Start Game"),
            mExitButton = new JButton("Exit"),
            mSettingsButton = new JButton("Settings");

    public Menu() {
        initSwing();
        UI.init();
        Sound.load();
    }

    private void initSwing() {
        setLayout(new GridBagLayout());
        addListeners();

        Style style = SimpleStyleLoader.instance.loadStyle(getClass().getResourceAsStream("/res/menu/styles/menu_style.style"));
        Styler styler = new SimpleStyler(style);
        ComponentFactory factory = new ComponentFactory(styler);

        JLabel titleLabel = UI.newComponentBuilder(new JLabel())
                .style(styler)
                .setText("Vouli Game")
                .setFont(style.getFont("font").deriveFont(24f))
                .build();

        JComponent[] components = {
                mStartGameButton,
                mSettingsButton,
                mExitButton,
                factory.newComponent(JLabel.class, "Version "+ Main.APPLICATION_VERSION),
                factory.newComponent(JLabel.class, "Made by JasonTan in 6 hours.")
        };

        styler.styleComponents(components);

        addComponentBuilder(new JPanel(), new GridBagConstraints())
                .addChildren(titleLabel)
                .addChildren(components)
                .setLayout(new VerticalFlowLayout(10, 10))
                .setSize(new Dimension(300, 500))
                .setBackground(new Color(50, 50, 50))
                .build();
    }

    private void addListeners() {
        mStartGameButton.addActionListener(ae -> {
            Game game = new Game();
            game.setVisible();
            game.start();
        });
        mExitButton.addActionListener(ae -> {
            dispose();
            System.exit(0);
        });
        mSettingsButton.addActionListener(ae -> new Settings().setVisible());
    }

    @Override
    public AbstractScreen setVisible() {
        super.setVisible();
        setPreferredSize(getFrame().getSize());
        return this;
    }

    @Override
    protected String title() {
        return "Vouli Game - Menu";
    }

    @Override
    protected Image background() {
        return Resources.loadImage("/res/background.jpg");
    }

    @Override
    protected Image icon() {
        return Resources.loadImage("/res/app_icon.png");
    }
}
