package game;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class MovableEntity extends Entity {
    // screen
    private int currentSpeed;
    private int defaultSpeed;

    public MovableEntity(Game context) {
        super(context);
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void move(int diffX, int diffY) {
        this.x += diffX;
        this.y += diffY;
    }

    public void setDefaultSpeed(int s) {
        currentSpeed=s;
        defaultSpeed=s;
    }

    public void dash(int diff, long millis) {
        currentSpeed+=diff;
        try (var executor= Executors.newSingleThreadScheduledExecutor()) {
            executor.schedule(() -> {
                currentSpeed=defaultSpeed;
            }, millis, TimeUnit.MILLISECONDS);
        }
    }
}
