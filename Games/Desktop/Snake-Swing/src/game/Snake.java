package game;

import main.StrictList;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.Iterator;

public final class Snake extends MovableEntity {
    private StrictList<Rectangle> tailComponents;
    private int score=0;

    public Snake(Game context) {
        super(context);
    }

    @Override
    public void update() {
        super.update();

        int xDiff=0, yDiff=0;
        if(context.keyHandler.up) yDiff-=getCurrentSpeed();
        if(context.keyHandler.down) yDiff+=getCurrentSpeed();
        if(context.keyHandler.left) xDiff-=getCurrentSpeed();
        if(context.keyHandler.right) xDiff+=getCurrentSpeed();
        move(xDiff, yDiff);

        int gap=2;
        int time=0;
        for (Rectangle tailComponent : tailComponents) {
            time++;
            if(time>3) {
                tailComponent.width-=gap*2;
                tailComponent.height-=gap*2;
                tailComponent.x+=gap;
                tailComponent.y+=gap;
                time=0;
            }
        }
        tailComponents.add(getRect());

        if(x+width<0||x>context.getWidth()||
                x+height<0||y>context.getHeight()) {

            tailComponents.changeNLimit(-10);
            x=20;
            y=20;
        }
    }

    public int getScore() {
        return score;
    }

    public void score() {
        tailComponents.changeNLimit(1);
        score++;
        width+=1;
        height=width;
    }

    @Override
    public void render(Graphics g) {
        for (Rectangle tailComponent : tailComponents) {
            g.drawImage(sprites[0], tailComponent.x, tailComponent.y,
                    tailComponent.width, tailComponent.height, null);
        }
        g.drawImage(sprites[1], x, y, width, height, null);
    }

    @Override
    public void setDefaultValues() {
        config(20, 20, 50, 50);
        setDefaultSpeed(7);
        tailComponents=new StrictList<>(20);
        initSprites("/game", "snake_component.png", "snake_head.png");
    }
}
