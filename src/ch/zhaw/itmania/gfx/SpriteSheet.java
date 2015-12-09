package ch.zhaw.itmania.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by ungerpet on 09.12.2015.
 */
public class SpriteSheet {
    private String path;
    private int width;
    private int height;
    private int[] pixels;

    public SpriteSheet(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.path = path;
        width = image.getWidth();
        height = image.getHeight();
        pixels = image.getRGB(0, 0, width, height, null, 0, width);
    }
}
