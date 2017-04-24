package controller;

import model.Bullet;
import model.GameRect;
import util.Utils;
import view.ImageRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Quang Minh on 11/04/2017.
 */
public class PlayerController {

    private GameRect gameRect;
    private ImageRenderer imageRenderer;
    private Image playerImage;
    private ArrayList<Bullet> bullets;
    private boolean isCoolDown=false;
    private int cooldownTime=0;

    private int dxPlayer;
    private int dyPlayer;

    public PlayerController(Image playerImage, int x, int y) {
        imageRenderer = new ImageRenderer(playerImage);
        gameRect = new GameRect(x, y, 70, 51);
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(Image playerImage) {
        this.playerImage = playerImage;
    }

    public void draw(Graphics graphics) {
        imageRenderer.render(graphics,gameRect);
    }


    public void processInput(boolean isUpPressed,
                             boolean isDownPressed,
                             boolean isLeftPressed,
                             boolean isRightPressed,
                             boolean isFirePressed) {
        dyPlayer = 0;
        dxPlayer = 0;
        if (isUpPressed) {
            dyPlayer -= 5;
        }
        if (isDownPressed) {
            dyPlayer += 5;
        }
        if (isRightPressed) {
            dxPlayer += 5;
        }
        if (isLeftPressed) {
            dxPlayer -= 5;
        }

        if (isFirePressed && !isCoolDown) {
            isCoolDown=true;
            Bullet bullet = null;
            try {
                bullet = new Bullet(Utils.loadImage("res/bullet.png"), gameRect.getX()+30, gameRect.getY());
            } catch (IOException e) {
                e.printStackTrace();
            }
            bullets.add(bullet);
        }
    }

    public void update() {
        gameRect.move(dxPlayer, dyPlayer);
        if(isCoolDown){
            cooldownTime++;
            System.out.println(cooldownTime);
            if(cooldownTime>20){
                isCoolDown=false;
                cooldownTime=0;
            }
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}
