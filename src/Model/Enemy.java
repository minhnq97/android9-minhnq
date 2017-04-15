package Model;

import java.awt.*;

/**
 * Created by Quang Minh on 13/04/2017.
 */
public class Enemy {
    private int x;
    private int y;
    private Image image;

    public Enemy() {
    }

    public Enemy(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }

    public void update(){
        y+=5;
    }
}