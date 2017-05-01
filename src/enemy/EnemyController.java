package enemy;

import controller.CollisionManager;
import controller.Controller;
import controller.MoveBehavior;
import game.Collider;
import player.BulletController;
import model.GameRect;
import player.PlayerController;
import view.ImageRenderer;

import java.awt.*;

/**
 * Created by Quang Minh on 13/04/2017.
 */
public class EnemyController extends Controller implements Collider {
    private MoveBehavior moveBehavior;

    public EnemyController(int x, int y, Image image) {
        super(new GameRect(x, y, 70, 60),new ImageRenderer(image));
        moveBehavior = new MoveBehavior();
        CollisionManager.instance.add(this);
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
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }


    public  void update(){
        moveBehavior.move(gameRect);
    }
}