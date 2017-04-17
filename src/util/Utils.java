package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Quang Minh on 15/04/2017.
 */
public class Utils {

    public static Image loadImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
