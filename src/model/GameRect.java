package model;

import java.awt.*;

/**
 * Created by Quang Minh on 15/04/2017.
 */
public class GameRect {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isDead;


    public GameRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }

    public void move(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }

    public boolean isCollide(GameRect other){
        Rectangle rect1 = new Rectangle(x,y,width,height);
        Rectangle rect2 = new Rectangle(other.x,other.y,other.width,other.height);
        return rect1.intersects(rect2);
    }

//    public boolean isCollide(GameRect gameRect){
//        if(x>=gameRect.getX()){
//            if(Math.abs(x-gameRect.getX()+width)<(width+gameRect.getWidth())    //x is left from gamerect
//                    && Math.abs(y-gameRect.getY()+height) < (height+gameRect.getHeight())){
//                System.out.println();
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            if(Math.abs(gameRect.getX()+gameRect.getWidth()-x)<(width+gameRect.getWidth())      //x is right from gamerect
//                    && Math.abs(y-gameRect.getY()+height) < (height+gameRect.getHeight())){
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }


}
