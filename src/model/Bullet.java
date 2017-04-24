package model;

import view.ImageRenderer;

import java.awt.*;

/**
 * Created by Quang Minh on 11/04/2017.
 */
public class Bullet {
    private GameRect gameRect;
    private ImageRenderer imageRenderer;
    private Image bulletImage;

    public Bullet() {
    }

    public Bullet(Image bulletImage, int x, int y) {
        imageRenderer = new ImageRenderer(bulletImage);
        gameRect = new GameRect(x,y,13,33);
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public Image getBulletImage() {
        return bulletImage;
    }

    public void setBulletImage(Image bulletImage) {
        this.bulletImage = bulletImage;
    }

    public void draw(Graphics graphics){
        imageRenderer.render(graphics,gameRect);
    }

    public void update(){
        gameRect.move(0,-10);
    }
}
