package game;

import java.awt.*;

public final class World {
    private final Game context;

    private final Entity[] entities;

    public World(Game context) {
        this.context = context;

        entities=new Entity[]{
                new Snake(context),
                new Target(context)
        };
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    public void render(Graphics g) {

        for (Entity entity : entities) {
            entity.render(g);
        }
    }

    public Snake getSnake() {
        for (Entity entity : entities) {
            if(entity instanceof Snake snake) {
                return snake;
            }
        }
        throw new RuntimeException();
    }

}
