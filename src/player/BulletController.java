package player;

import controller.CollisionManager;
import controller.Controller;
import enemy.EnemyController;
import controller.Collider;
import enemy.SecondEnemyController;
import model.GameRect;
import view.ImageRenderer;

import java.awt.*;

/**
 * Created by Quang Minh on 11/04/2017.
 */
public class BulletController extends Controller implements Collider{

    private int damage =1;


    public BulletController(Image bulletImage, int x, int y) {
        super(new GameRect(x,y,13,33),new ImageRenderer(bulletImage));
        CollisionManager.instance.add(this);
    }

    public int getDamage() {
        return damage;
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void getHit(){
        gameRect.setDead(true);
        CollisionManager.instance.remove(this);
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof EnemyController){
            ((EnemyController)other).getHit(damage);
            PlayerController.score+=100;
        }
        else if(other instanceof SecondEnemyController){
            ((SecondEnemyController)other).getHit(damage);
            PlayerController.score+=150;
        }
    }

    public void draw(Graphics graphics){
        imageRenderer.render(graphics,gameRect);
    }

    public void update(){
        gameRect.move(0,-10);
    }
}
