package Model;

import java.awt.*;

/**
 * Created by Quang Minh on 11/04/2017.
 */
public class Bullet {
    private Image bulletImage;
    private int xBullet;
    private int yBullet;

    public Bullet() {
    }

    public Bullet(Image bulletImage, int xBullet, int yBullet) {
        this.bulletImage = bulletImage;
        this.xBullet = xBullet + 34;
        this.yBullet = yBullet;
    }

    public Image getBulletImage() {
        return bulletImage;
    }

    public void setBulletImage(Image bulletImage) {
        this.bulletImage = bulletImage;
    }

    public int getxBullet() {
        return xBullet;
    }

    public void setxBullet(int xBullet) {
        this.xBullet = xBullet;
    }

    public int getyBullet() {
        return yBullet;
    }

    public void setyBullet(int yBullet) {
        this.yBullet = yBullet;
    }

    public void draw(Graphics graphics){
        graphics.drawImage(bulletImage, xBullet, yBullet, null);
    }

    public void update(){
        yBullet-=10;
    }
}
