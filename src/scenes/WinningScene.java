package scenes;

import game.GameWindow;
import util.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by Quang Minh on 05/05/2017.
 */
public class WinningScene implements GameScene {
    private Image backgroundImage;

    public WinningScene() {
        try {
            backgroundImage = Utils.loadImage("res/background.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        graphics.drawImage(backgroundImage, 0, 0, 600, 750, null);
        graphics.drawString("YOU WIN!!",140,500);
    }

    @Override
    public void update() {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode()==keyEvent.VK_SPACE){
            GameWindow.instance.setCurrentScene(new Level1Scene());
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }
}
