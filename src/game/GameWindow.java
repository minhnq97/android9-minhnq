package game;

import bonus.HeartController;
import bonus.PowerupController;
import controller.*;
import enemy.EnemyController;
import enemy.SecondEnemyController;
import player.BulletController;
import player.PlayerController;
import scenes.GameScene;
import scenes.Level1Scene;
import scenes.MenuScene;
import util.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Quang Minh on 08/04/2017.
 */
public class GameWindow extends Frame {
    //objects

    private BufferedImage bufferedImage;
    private Graphics bufferedGraphics;
    private GameScene currentScene;

    public static GameWindow instance;

    public void setCurrentScene(GameScene currentScene) {
        this.currentScene = currentScene;
    }

    public GameWindow() {
        //initialize objects

        instance = this;

        setVisible(true);
        setSize(600, 750);
        bufferedImage = new BufferedImage(600, 750, BufferedImage.TYPE_INT_ARGB);
        bufferedGraphics = bufferedImage.getGraphics();

        currentScene = new MenuScene();

        //add listener
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {

                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                currentScene.keyPressed(keyEvent);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                currentScene.keyReleased(keyEvent);
            }
        });

        //draw
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(17);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    currentScene.update();

                    ControllerManager.instance.update();

                    CollisionManager.instance.update();

                    repaint();
                }
            }
        });
        thread.start();
    }

    @Override
    public void update(Graphics graphics) {
        //drawing on buffer
        currentScene.draw(bufferedGraphics);
        ControllerManager.instance.draw(bufferedGraphics);

        //drawing from buffer to window
        graphics.drawImage(bufferedImage, 0, 0, null);
    }

}
