package scenes;

import bonus.HeartController;
import bonus.PowerupController;
import controller.ControllerManager;
import controller.HorzMoveBehavior;
import controller.MoveBehavior;
import enemy.EnemyController;
import enemy.SecondEnemyController;
import game.GameWindow;
import player.PlayerController;
import util.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Quang Minh on 03/05/2017.
 */
public class Level1Scene implements GameScene {

    private Image backgroundImage;
    private PlayerController player;
    private boolean isUpPressed;
    private boolean isDownPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isFirePressed;
    private EnemyController enemy;

    //generate enemies
    private int enemyTime = 3000;
    private boolean isEnemyAppear;

    public Level1Scene() {
        try {
            player = new PlayerController(Utils.loadImage("res/plane3.png"), 260, 670);
        } catch (IOException e) {
            e.printStackTrace();
        }

        isEnemyAppear = true;

        try {
            backgroundImage = Utils.loadImage("res/background.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(backgroundImage, 0, 0, 600, 750, null);
        graphics.drawString("Score: "+player.getScore(),50,50);
        graphics.drawString("HP: "+player.getHitPoint(),500,50);
        player.draw(graphics);
    }

    @Override
    public void update() {
        player.processInput(isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isFirePressed);
        player.update();
        if(player.isDead()){
            GameWindow.instance.setCurrentScene(new GameOverScene());
        }

        if(player.getScore()>=2000){
            GameWindow.instance.setCurrentScene(new WinningScene());
        }
        enemyTime -= 17;
        if (enemyTime < 0) {
            enemyTime = 3000;
            isEnemyAppear = true;
        }

        if (isEnemyAppear) {
            for (int x = 0; x < 600; x += 100) {

                if (x < 300) {
                    try {
                        enemy = new EnemyController(x, 0, Utils.loadImage("res/plane1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    enemy.setMoveBehavior(new MoveBehavior());
                } else {
                    try {
                        enemy = new SecondEnemyController(x, 0, Utils.loadImage("res/enemy-green-3.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    enemy.setMoveBehavior(new HorzMoveBehavior());
                }
                ControllerManager.instance.add(enemy);
            }
            isEnemyAppear = false;
        }



    }


    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                isRightPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                isLeftPressed = true;
                break;
            case KeyEvent.VK_UP:
                isUpPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                isDownPressed = true;
                break;
            case KeyEvent.VK_F:
                isFirePressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            isRightPressed = false;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            isLeftPressed = false;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            isUpPressed = false;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            isDownPressed = false;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_F) {
            isFirePressed = false;
        }
    }
}
