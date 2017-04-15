import Model.Bullet;
import Model.Enemy;
import Model.Player;
import sun.awt.image.PixelConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Quang Minh on 08/04/2017.
 */
public class GameWindow extends Frame {
    private Image backgroundImage;
    private Player player;
    private ArrayList<Enemy> enemies;

    private BufferedImage bufferedImage;
    private Graphics bufferedGraphics;

    private boolean isUpPressed;
    private boolean isDownPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isFirePressed;
    //cooldown
    private int cooldown = 2000;
    private boolean isCoolDown;

    //generate enemies
    private int enemyTime = 3000;
    private boolean isEnemyAppear;

    public GameWindow() {
        //initialize objects
        setVisible(true);
        setSize(600, 750);

        player = new Player();
        player.setX(260);
        player.setY(670);

        enemies = new ArrayList<>();
        isEnemyAppear = true;

        bufferedImage = new BufferedImage(600, 750, BufferedImage.TYPE_INT_ARGB);
        bufferedGraphics = bufferedImage.getGraphics();

        try {
            backgroundImage = Util.loadImage("res/background.png");
            player.setPlayerImage(Util.loadImage("res/plane3.png"));
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
                if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (player.getX() <= 600 && player.getX() >= 0) {
                        isRightPressed = true;
                    } else if (player.getX() > 600) {
                        player.setX(600);
                        isRightPressed = false;
                    } else if (player.getX() < 0) {
                        player.setX(0);
                        isRightPressed = false;
                    }

                } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (player.getX() <= 600 && player.getX() >= 0) {
                        isLeftPressed = true;
                    } else if (player.getX() > 600) {
                        player.setX(600);
                        isLeftPressed = false;
                    } else if (player.getX() < 0) {
                        player.setX(0);
                        isLeftPressed = false;
                    }
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                    isUpPressed = true;
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                    isDownPressed = true;
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_F) {
                    isFirePressed = true;
                    if (cooldown == 2000) {
                        isCoolDown = false;
                    }

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
                    isCoolDown = true;
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
                    //logic

                    //cooldown
                    if (isCoolDown) {
                        cooldown -= 17;
                        if (cooldown < 0) {
                            System.out.println("Cooldown");
                            cooldown = 2000;
                            isCoolDown = false;
                        }
                    }

                    //player
                    int dyPlayer = 0;
                    int dxPlayer = 0;
                    if (isUpPressed) {
                        dyPlayer -= 5;
                    }
                    if (isDownPressed) {
                        dyPlayer += 5;
                    }
                    if (isRightPressed) {
                        dxPlayer += 5;
                    }
                    if (isLeftPressed) {
                        dxPlayer -= 5;
                    }
                    player.setY(player.getY() + dyPlayer);
                    player.setX(player.getX() + dxPlayer);

                    //bullet
                    if (isFirePressed && !isCoolDown) {

                        Bullet bullet = null;
                        try {
                            bullet = new Bullet(Util.loadImage("res/bullet.png"), player.getX(), player.getY());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.getBullets().add(bullet);
                    }
                    for (Bullet bullet : player.getBullets()) {
                        bullet.update();
                    }

                    //enemy

                    Random rand = new Random();
                    int xEnemy = rand.nextInt(600);
                    int yEnemy = 0;

                    enemyTime -= 17;
                    if (enemyTime < 0) {
                        enemyTime = 3000;
                        isEnemyAppear = true;
                    }

                    if (isEnemyAppear) {
                        Enemy enemy = null;
                        try {
                            enemy = new Enemy(xEnemy, yEnemy, Util.loadImage("res/plane1.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        enemies.add(enemy);

                        isEnemyAppear = false;
                    }
                    for (Enemy e : enemies) {
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

        for (Bullet bullet : player.getBullets()) {
            bullet.draw(bufferedGraphics);

        }

        for (Enemy enemy : enemies) {
            enemy.draw(bufferedGraphics);
        }

        //drawing from buffer to window

        graphics.drawImage(bufferedImage, 0, 0, null);
    }

}
