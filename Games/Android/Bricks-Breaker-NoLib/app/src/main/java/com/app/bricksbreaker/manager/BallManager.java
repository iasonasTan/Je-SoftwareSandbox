package com.app.bricksbreaker.manager;

import com.app.bricksbreaker.Game;
import com.app.bricksbreaker.entities.Ball;

public class BallManager extends EntityManager <Ball> {

    public BallManager(Game g) {
        super(g);

    }

    public void addBall () {
        Ball ball = new Ball(game);
        if (!getEntities().isEmpty()) {
            Ball lastAdded = getEntities().get(getEntities().size()-1);
            ball.goTo(lastAdded.getX()+lastAdded.getWidth(), lastAdded.getY()+ lastAdded.getHeight());
        }
        addEntity(ball);
    }

    public void increaseSpeed () {
        for (Ball ball : super.getEntities()) {
            ball.increaseSpeed();
        }
    }
}
