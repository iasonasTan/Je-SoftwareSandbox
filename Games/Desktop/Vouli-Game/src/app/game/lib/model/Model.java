package app.game.lib.model;

import lib.game.bounds.Vector2;
import lib.game.bounds.Size2;
import lib.game.bounds.Hitbox2;

import java.awt.*;

public interface Model {
    Model setPosition(Vector2 position);
    Model setSize(Size2 size);

    void move(Vector2 vec);
    void addVelocity(Vector2 acceleration);

    Vector2 copyPosition();
    Size2 copySize();
    Vector2 copyVelocity();
    Hitbox2 copyHitbox();

    void update(double delta);
    void render(Graphics g);

    boolean hasCollisionWith(Model model);

    boolean isAlive();
    void kill();
}
