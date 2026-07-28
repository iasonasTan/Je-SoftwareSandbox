package com.app.bricksbreaker;

import android.content.Context;

import com.app.bricksbreaker.entities.Controller;
import com.app.bricksbreaker.manager.BallManager;
import com.app.bricksbreaker.manager.BricksManager;

public class Game {
    public Game (MainActivity ma, GamePanel gp) {
        context = ma;
        mainActivity = ma;
        gamePanel = gp;
    }

    public Context context;
    public MainActivity mainActivity;
    public GamePanel gamePanel;
    public Controller controller;
    public BricksManager bricksManager;
    public BallManager ballManager;

}
