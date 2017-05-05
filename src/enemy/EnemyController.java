package enemy;

import bonus.HeartController;
import bonus.PowerupController;
import controller.*;
import player.BulletController;
import model.GameRect;
import player.PlayerController;
import scenes.Level1Scene;
import util.Utils;
import view.ImageRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Quang Minh on 13/04/2017.
 */
public class EnemyController extends Controller implements Collider {
    private MoveBehavior moveBehavior;
    private boolean isHeartAppear;
    private boolean isPowerupAppear;
    private Level1Scene level1Scene;

    public EnemyController(int x, int y, Image image) {
        super(new GameRect(x, y, 70, 60),new ImageRenderer(image));
        moveBehavior = new MoveBehavior();
        CollisionManager.instance.add(this);
        level1Scene = new Level1Scene();
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof BulletController){
            ((BulletController)other).getHit();
        }
        if(other instanceof PlayerController){
            ((PlayerController)other).getHit(1);
        }
    }

    public void getHit(int damage){
        gameRect.setDead(true);
        CollisionManager.instance.remove(this);

        //bonus
        Random rand = new Random();
        int randomBonus = rand.nextInt(15);
        if (randomBonus == 1) {
            try {
                HeartController heart = new HeartController(300, 0, Utils.loadImage("res/heart2.png"));
                ControllerManager.instance.add(heart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (randomBonus == 2) {
            try {
                PowerupController powerup = new PowerupController(200, 0, Utils.loadImage("res/power-up.png"));
                ControllerManager.instance.add(powerup);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }


    public  void update(){
        moveBehavior.move(gameRect);
    }
}