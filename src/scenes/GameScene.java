package scenes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Quang Minh on 03/05/2017.
 */
public interface GameScene {
    void draw(Graphics graphics);
    void update();
    void keyReleased(KeyEvent keyEvent);
    void keyPressed(KeyEvent keyEvent);
}
