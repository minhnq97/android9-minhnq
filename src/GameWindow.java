import controller.*;
import model.Bullet;
import util.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Quang Minh on 08/04/2017.
 */
public class GameWindow extends Frame {
    private Image backgroundImage;
    private PlayerController player;
    private ArrayList<EnemyController> enemies;
    private MoveBehavior moveBehavior;
    private ArrayList<Bullet> bullets;

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

    public GameWindow() {
        //initialize objects
        setVisible(true);
        setSize(600, 750);

        try {
            player =  new PlayerController(Utils.loadImage("res/plane3.png"), 260, 670);
        } catch (IOException e) {
            e.printStackTrace();
        }

        enemies = new ArrayList<>();

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
                    player.processInput(isUpPressed,isDownPressed,isLeftPressed,isRightPressed,isFirePressed);


                    //bullet

                    for (Bullet bullet : bullets) {
                        bullet.update();
                    }
                    player.update();

                    //enemy
                    enemyTime -= 17;
                    if (enemyTime < 0) {
                        enemyTime = 3000;
                        isEnemyAppear = true;
                    }

                    if (isEnemyAppear) {
                        for (int x=0;x<getWidth();x+=100){
                            EnemyController enemy = null;
                            if(x<300){
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

        for (Bullet bullet : bullets) {
            bullet.draw(bufferedGraphics);

        }

        for (EnemyController enemy : enemies) {
            enemy.draw(bufferedGraphics);
        }

        //drawing from buffer to window

        graphics.drawImage(bufferedImage, 0, 0, null);
    }

}
