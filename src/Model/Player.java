package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Quang Minh on 11/04/2017.
 */
public class Player {
    private Image playerImage;
    private int x;
    private int y;
    private int dX;
    private int dY;
    private ArrayList<Bullet> bullets;
    public Player() {
        bullets = new ArrayList<>();
    }

    public Player(Image playerImage, int x, int y) {
        this.playerImage = playerImage;
        this.x = x;
        this.y = y;
        this.dX = 0;
        this.dY = 0;
        bullets = new ArrayList<>();
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(Image playerImage) {
        this.playerImage = playerImage;
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

    public void draw(Graphics graphics) {
        graphics.drawImage(playerImage, x, y, null);
    }

    public void update(){
        x+=dX;
        y+=dY;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}
