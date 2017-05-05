package controller;

import enemy.EnemyController;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Quang Minh on 03/05/2017.
 */
public class ControllerManager {
    public static final ControllerManager instance = new ControllerManager();


    private ArrayList<Controller> controllers;

    private ControllerManager() {
        controllers = new ArrayList<>();
    }

    public void add(Controller controller) {
        controllers.add(controller);
    }

    public void draw(Graphics graphics) {
        for (Controller controller : controllers) {
            controller.draw(graphics);
        }
    }

    public void update() {
        //remove dead object
        Iterator<Controller> controllerIterator = controllers.iterator();
        while (controllerIterator.hasNext()) {
            Controller controller = controllerIterator.next();
            if (controller.getGameRect().isDead()) {
                controllerIterator.remove();
//                if (controller instanceof EnemyController) {
//                    Random rand = new Random();
//                    int randomBonus = rand.nextInt(15);
//                    if (randomBonus == 1) {
//                        isHeartAppear = true;
//                    }
//                    if (randomBonus == 2) {
//                        isPowerupAppear = true;
//                    }
//                }
            }
        }

        for (Controller controller : controllers) {
            controller.update();
        }
    }

}
