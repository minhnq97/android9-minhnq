package controller;

import model.GameRect;
import view.ImageRenderer;

import java.awt.*;

/**
 * Created by Quang Minh on 26/04/2017.
 */
public class Controller {
    protected GameRect gameRect;
    protected ImageRenderer imageRenderer;

    public Controller() {
    }

    public Controller(GameRect gameRect, ImageRenderer imageRenderer) {
        this.gameRect = gameRect;
        this.imageRenderer = imageRenderer;
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public void draw(Graphics graphics){
        if(gameRect.isDead()) return;
        imageRenderer.render(graphics,gameRect);
    }

    public void update(){

    }
}
