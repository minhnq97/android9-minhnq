package view;

import model.GameRect;
import util.Utils;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Quang Minh on 16/04/2017.
 */
public class ImageRenderer {
    private Image image;

    public ImageRenderer(Image image) {
        this.image = image;
    }

    public ImageRenderer(String path) throws IOException {
        this(Utils.loadImage(path));
    }

    public void render(Graphics graphics, GameRect gameRect){
        graphics.drawImage(image,gameRect.getX(),gameRect.getY(),gameRect.getWidth(),gameRect.getHeight(),null);
    }
}
