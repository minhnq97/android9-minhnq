package bonus;

import controller.CollisionManager;
import controller.Controller;
import controller.MoveBehavior;
import controller.Collider;
import model.GameRect;
import player.PlayerController;
import view.ImageRenderer;

import java.awt.*;

/**
 * Created by Quang Minh on 01/05/2017.
 */

public class HeartController extends Controller implements Collider {
    private MoveBehavior moveBehavior;

    public HeartController(int x, int y, Image image) {
        super(new GameRect(x,y,25,25),new ImageRenderer(image));
        moveBehavior = new MoveBehavior();
        CollisionManager.instance.add(this);
    }

    @Override
    public GameRect getGameRect() {
        return gameRect;
    }

    public void getHit(){
        gameRect.setDead(true);
        CollisionManager.instance.remove(this);
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof PlayerController){
            ((PlayerController)other).getHeart();
        }
    }

    public  void update(){
        moveBehavior.move(gameRect);
    }
}
