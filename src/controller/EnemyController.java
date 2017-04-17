package controller;

import model.GameRect;
import view.ImageRenderer;

import java.awt.*;

/**
 * Created by Quang Minh on 13/04/2017.
 */
public class EnemyController {
    private ImageRenderer imageRenderer;
    private GameRect gameRect;
    private MoveBehavior moveBehavior;
    private int dX;
    private int dY;

    public EnemyController(int x, int y, Image image) {
        imageRenderer = new ImageRenderer(image);
        gameRect = new GameRect(x, y, 70, 60);
        moveBehavior = new MoveBehavior();
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public void draw(Graphics graphics){
        imageRenderer.render(graphics, gameRect);
    }

    public  void update(){
        moveBehavior.move(gameRect);
    }
}