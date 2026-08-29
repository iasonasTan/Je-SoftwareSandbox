package game;

import java.awt.*;
import javax.swing.*;

public abstract class Entity {
    // screen
    protected int x, y;
    protected int width, height;
    protected Image[] sprites=new Image[0];
    protected int sprite_idx=0;

    // physics
    private Rectangle rect=new Rectangle();

    // logic
    protected final Game context;

    public Entity(Game context) {
        this.context=context;
        setDefaultValues();
    }

    protected void initSprites(String resRoot, String... res) {
        sprites=new Image[res.length];
        int idx=0;
        for (String re: res) {
            sprites[idx]= new ImageIcon(getClass().getResource(resRoot+"/"+re)).getImage();
            idx++;
        }
    }

    abstract public void setDefaultValues();

    public void update() {
        sprite_idx++;
        if(sprite_idx>=sprites.length)
            sprite_idx=0;

        updateRect();
    }

    public void randomLocation() {
        x=(int)(Math.random()*(context.getWidth()-width));
        y=(int)(Math.random()*(context.getHeight()-height));
    }

    public Rectangle getRect() {
        return new Rectangle(rect);
    }

    protected final void updateRect() {
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
    }

    protected final void config(int x, int y, int width, int height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public void render(Graphics g) {
        //g.drawImage(sprites[sprite_idx], x, y, width, height, null);
        g.fillRect(x,y,width,height);
    }

    public boolean collides(Entity other) {
        return rect.intersects(other.rect);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
