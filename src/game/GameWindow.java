package game;

import bonus.HeartController;
import controller.*;
import enemy.EnemyController;
import enemy.SecondEnemyController;
import player.BulletController;
import player.PlayerController;
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
    private Image backgroundImage;
    //objects
    private PlayerController player;
    private ArrayList<EnemyController> enemies;
    private ArrayList<HeartController> hearts;
    private ArrayList<BulletController> bullets;

    private BufferedImage bufferedImage;
    private Graphics bufferedGraphics;

    private boolean isUpPressed;
    private boolean isDownPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isFirePressed;


    //generate enemies
    private int enemyTime = 3000;
    private boolean isEnemyAppear;

    //generate bonus
    private boolean isHeartAppear;

    public GameWindow() {
        //initialize objects
        setVisible(true);
        setSize(600, 750);

        try {
            player = new PlayerController(Utils.loadImage("res/plane3.png"), 260, 670);
        } catch (IOException e) {
            e.printStackTrace();
        }

        enemies = new ArrayList<>();
        hearts = new ArrayList<>();
        bullets = new ArrayList<>();
        player.setBullets(bullets);
        isEnemyAppear = true;

        bufferedImage = new BufferedImage(600, 750, BufferedImage.TYPE_INT_ARGB);
        bufferedGraphics = bufferedImage.getGraphics();

        try {
            backgroundImage = Utils.loadImage("res/background.png");
        } catch (IOException e) {
            e.printStackTrace();
        }


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


                    //player
                    player.processInput(isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isFirePressed);
                    player.update();

                    //bullet

                    for (BulletController bullet : bullets) {
                        bullet.update();
                    }


                    //enemy
                    enemyTime -= 17;
                    if (enemyTime < 0) {
                        enemyTime = 3000;
                        isEnemyAppear = true;
                    }

                    if (isEnemyAppear) {
                        for (int x = 0; x < getWidth(); x += 100) {
                            EnemyController enemy = null;
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
                            enemies.add(enemy);
                        }
                        isEnemyAppear = false;
                    }

                    for (EnemyController e : enemies) {
                        e.update();
                    }

                    //bonus
                    if(isHeartAppear){
                        try {
                            HeartController heart = new HeartController(300,0,Utils.loadImage("res/power-up.png"));
                            hearts.add(heart);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        isHeartAppear = false;
                    }
                    for(HeartController h : hearts){
                        h.update();
                    }

                    CollisionManager.instance.update();

//                    for (int i=0;i<enemies.size();i++){
//                        for (int j=0;j<bullets.size();j++){
//                            if(bullets.get(j).getGameRect().isCollide(enemies.get(i).getGameRect())){
//                                bullets.remove(bullets.get(j));
//                                enemies.remove(enemies.get(i));
//                                i--;
//                                j--;
//                            }
//                        }
//                    }

                    //draw
                    repaint();
                }
            }
        });
        thread.start();
    }

    @Override
    public void update(Graphics graphics) {
        //drawing on buffer
        bufferedGraphics.drawImage(backgroundImage, 0, 0, 600, 750, null);
        player.draw(bufferedGraphics);

        for (BulletController bullet : bullets) {
            bullet.draw(bufferedGraphics);
        }

        for (EnemyController enemy : enemies) {
            enemy.draw(bufferedGraphics);
        }

        for (HeartController heart: hearts){
            heart.draw(bufferedGraphics);
        }

        Iterator<EnemyController> enemyControllerIterator = enemies.iterator();
        while(enemyControllerIterator.hasNext()){
            EnemyController enemyController = enemyControllerIterator.next();
            if(enemyController.getGameRect().isDead()){
                enemyControllerIterator.remove();
                Random rand = new Random();
                if(rand.nextInt(10)==1){
                    isHeartAppear = true;
                }
            }
        }

        Iterator<BulletController> bulletControllerIterator = bullets.iterator();
        while(bulletControllerIterator.hasNext()){
            BulletController bulletController = bulletControllerIterator.next();
            if(bulletController.getGameRect().isDead()){
                bulletControllerIterator.remove();
            }
        }

        Iterator<HeartController> heartControllerIterator = hearts.iterator();
        while(heartControllerIterator.hasNext()){
            HeartController heartController = heartControllerIterator.next();
            if(heartController.getGameRect().isDead()){
                heartControllerIterator.remove();
            }
        }

        //drawing from buffer to window
        graphics.drawImage(bufferedImage, 0, 0, null);
    }

}
