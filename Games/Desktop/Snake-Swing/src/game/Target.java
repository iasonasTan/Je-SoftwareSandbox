package game;

import java.awt.*;

public class Target extends Entity {
    private Snake snake;

    public Target(Game context) {
        super(context);
        randomLocation();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, width, height);
    }

    @Override
    public void update() {
        super.update();
        if(snake==null)
            snake=context.world.getSnake();

        if (snake.collides(this)) {
            snake.score();
            randomLocation();
        }
    }

    @Override
    public void setDefaultValues() {
        width=20;
        height=width;
    }
}
