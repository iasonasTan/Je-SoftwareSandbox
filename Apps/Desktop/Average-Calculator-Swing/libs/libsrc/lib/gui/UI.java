package lib.gui;

import lib.NotInitializedException;
import lib.io.Configuration;
import lib.io.InputProperties;
import lib.io.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class UI {
    private static boolean sInitialized = false;
    public static boolean sNativeFullScreen = false;

    public static void init() {
        InputProperties properties = Configuration.loadProperties("settings.properties");
        sNativeFullScreen = properties.getBoolean("native_fullscreen", true);
        sInitialized = true;
    }

    public static void check() {
        if(!sInitialized)
            throw new NotInitializedException();
    }

    public static void showException(Exception exception) {
        String messageText = String.format("""
                        The application has a bug "%s: %s".
                        
                        Stacktrace:
                        %s
                        Please send this to the developer (iasonas.tan@gmail.com)
                        """,
                exception.getClass().getName(),
                exception.getMessage()==null?"No message details.":exception.getLocalizedMessage(),
                stacktraceToString(exception.getStackTrace())
        );
        Image image = Resources.loadImage("alert.png", UI.class);
        image = image.getScaledInstance(100, 100, BufferedImage.SCALE_SMOOTH);
        JTextArea textArea = UI.newComponentBuilder(new JTextArea())
                .setEditable(false)
                .setText(messageText)
                .build();
        JPanel panel = UI.newComponentBuilder(new JPanel())
                .addChildren(textArea)
                .setLayout(new FlowLayout())
                .build();
        JOptionPane.showMessageDialog(null, panel, "Unexpected Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(image));
    }

    public static String stacktraceToString(StackTraceElement[] stackTrace) {
        StringBuilder messageBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement: stackTrace) {
            messageBuilder
                    .append(stackTraceElement.toString())
                    .append('\n');
        }
        return messageBuilder.toString();
    }

    public static <C extends JComponent> ComponentBuilder<C> newComponentBuilder(C component) {
        return new JComponentBuilder<>(component);
    }

    /**
     * @deprecated Use {@link ComponentBuilder#addChildren(JComponent...)} for adding children.
     */
    @Deprecated
    public static <C extends JComponent> ComponentBuilder<C> newComponentBuilder(C component, Component... children) {
        for (Component child : children) {
            component.add(child);
        }
        return new JComponentBuilder<>(component);
    }

    public static JFrame createFrame(JPanel contentPane, String title, Image icon) {
        JFrame frame = assembleFrame(contentPane, title, icon);
        frame.setVisible(true);
        return frame;
    }

    public static JFrame createFullscreenFrame(JPanel contentPane, String title, Image icon) {
        JFrame frame = assembleFrame(contentPane, title, icon);
        frame.setUndecorated(true);
        if(sNativeFullScreen) {
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .setFullScreenWindow(frame);
        } else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setPreferredSize(screenSize);
            frame.setSize(screenSize);
        }
        frame.setVisible(true);
        return frame;
    }

    private static JFrame assembleFrame(JPanel contentPane, String title, Image icon) {
        check();
        JFrame frame = new JFrame(title);
        frame.setIconImage(icon);
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    private static class JComponentBuilder<C extends JComponent> extends AbstractComponentBuilder<C> {
        public JComponentBuilder(C comp) {
            super(comp);
        }

        @Override
        public C build() {
            return component;
        }
    }

    private UI() {}
}
