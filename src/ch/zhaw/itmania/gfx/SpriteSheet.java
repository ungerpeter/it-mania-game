package ch.zhaw.itmania.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * Created by Peter Unger on 09.12.2015.
 *
 */
public class SpriteSheet {
    private String path;
    private int width;
    private int height;
    private BufferedImage bufferedImage;
    private int[] pixels;

    /**
     * Loads a Sprite Sheet image from the given path.
     * (Relative to res folder)
     * @param path
     */
    public SpriteSheet(String path) {
        try {
            bufferedImage = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.path = path;
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        pixels = bufferedImage.getRGB(0, 0, width, height, null, 0, width);
    }

    public int getWidth() {
        return width;
    }

    public int getPixelAt(int index) {
        return pixels[index];
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    /**
     * Return a subimage at the given location of the SpriteSheet.
     * @param x the X-Position in the SpriteSheet.
     * @param y the Y-Position in the SpriteSheet.
     * @param width the width of the subimage.
     * @param height the height of the subimage.
     * @return BufferedImage (subimage from SpriteSheet)
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return bufferedImage.getSubimage(x, y, width, height);
    }
}
