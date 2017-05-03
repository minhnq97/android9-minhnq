package player;

import bonus.HeartController;
import bonus.PowerupController;
import controller.CollisionManager;
import controller.Controller;
import enemy.EnemyController;
import game.Collider;
import model.GameRect;
import util.Utils;
import view.ImageRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Quang Minh on 11/04/2017.
 */
public class PlayerController extends Controller implements Collider {

    private GameRect gameRect;
    private ImageRenderer imageRenderer;
    private ArrayList<BulletController> bullets;
    private boolean isCoolDown=false;
    private int cooldownTime=0;
    private int hitPoint = 5;
    private boolean isDead;
    private int powerLevel = 1;

    private int dxPlayer;
    private int dyPlayer;

    public PlayerController(Image playerImage, int x, int y) {
        imageRenderer = new ImageRenderer(playerImage);
        gameRect = new GameRect(x, y, 70, 51);
        CollisionManager.instance.add(this);
    }


    public void draw(Graphics graphics) {
        if(isDead==true){
            return;
        }
        imageRenderer.render(graphics,gameRect);
    }

    public void setDead(boolean dead) {
        isDead = dead;
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
            BulletController bullet = null;
            if(powerLevel==1){
                try {
                    bullet = new BulletController(Utils.loadImage("res/bullet.png"), gameRect.getX()+30, gameRect.getY());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(powerLevel==2){
                try {
                    bullet = new BulletController(Utils.loadImage("res/bullet-double.png"), gameRect.getX()+30, gameRect.getY());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    public void getHit(int damage){
        hitPoint-=damage;
        if(hitPoint<=0){
            setDead(true);
        }
    }

    public void getHeart(){
        hitPoint=5;
    }

    public void getPowerup(){
        powerLevel=2;
    }


    public void setBullets(ArrayList<BulletController> bullets) {
        this.bullets = bullets;
    }

    @Override
    public GameRect getGameRect() {
        return gameRect;
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof HeartController){
            ((HeartController)other).getHit();
        }
        else if(other instanceof EnemyController){
            ((EnemyController)other).getHit(1);
        }
        else if(other instanceof PowerupController){
            ((PowerupController)other).getHit();
        }
        System.out.println("Hit point: "+hitPoint);
    }
}
