package controller;

import model.GameRect;

/**
 * Created by Quang Minh on 26/04/2017.
 */
public interface Collider {
    GameRect getGameRect();
    void onCollide(Collider other);
}
