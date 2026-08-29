package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Game extends JPanel {
    private Thread gameThread;
    private Image background=new ImageIcon(getClass().getResource("/game/background.png")).getImage();
    private JButton backToMenu_button=new JButton("Back");

    public final World world=new World(this);
    public final KeyHandler keyHandler;

    public Game() {
        keyHandler=new KeyHandler();
        add(backToMenu_button);
        backToMenu_button.setFocusable(false);

        setPreferredSize(new Dimension(600, 400));
        setFocusable(true);
        addKeyListener(keyHandler);
        requestFocus();
        requestFocusInWindow();
    }

    public void addBackListener(ActionListener l) {
        backToMenu_button.addActionListener(l);
    }

    public void start() {
        gameThread=new Thread(this::loop);
        gameThread.start();
    }

    public void update() {
        world.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);
        g.drawString("Score: "+world.getSnake().getScore(), getWidth()/2, 100);
        world.render(g);
    }

    public void stop() {
        gameThread=null;
    }

    public static class KeyHandler implements KeyListener {
        public boolean up, down, left, right;

        @Override public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            final int kc=e.getKeyCode();
            switch (kc) {
                case KeyEvent.VK_UP -> up=true;
                case KeyEvent.VK_DOWN -> down=true;
                case KeyEvent.VK_LEFT -> left=true;
                case KeyEvent.VK_RIGHT -> right=true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            final int kc=e.getKeyCode();
            switch (kc) {
                case KeyEvent.VK_UP -> up=false;
                case KeyEvent.VK_DOWN -> down=false;
                case KeyEvent.VK_LEFT -> left=false;
                case KeyEvent.VK_RIGHT -> right=false;
            }
        }
    }

    private void loop() {
        long previousTime;
        long elapsedTime;
        long waitTime;
        final long FPS=60;
        final long SEC=1000;

        while(gameThread!=null) {
            previousTime=System.currentTimeMillis();

            update();
            repaint();
            paintImmediately(0, 0, getWidth(), getHeight());

            elapsedTime=System.currentTimeMillis()-previousTime;
            waitTime=SEC/FPS-elapsedTime;

            try {
                Thread.sleep(waitTime>0?waitTime:0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
