package controller;

import model.GameRect;

/**
 * Created by Quang Minh on 17/04/2017.
 */
public class VertMoveBehavior extends MoveBehavior{
    @Override
    public void move(GameRect gameRect) {
        gameRect.move(3,0);
    }
}
