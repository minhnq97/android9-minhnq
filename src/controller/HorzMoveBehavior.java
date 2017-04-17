package controller;

import model.GameRect;

/**
 * Created by Quang Minh on 17/04/2017.
 */
public class HorzMoveBehavior extends MoveBehavior {
    @Override
    public void move(GameRect gameRect) {
        gameRect.move(3,3);
        if(gameRect.getX() >= 550){
            gameRect.move(-3,3);
        }
    }
}
