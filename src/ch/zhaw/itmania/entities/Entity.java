package ch.zhaw.itmania.entities;

import ch.zhaw.itmania.gfx.Assets;
import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ch.zhaw.itmania.tiles.entities
 * Created by Peter Unger on 12.12.2015.
 */
public abstract class Entity {

    public static final float DEFAULT_SIZE = 1f;

    protected Screen screen;
    protected BufferedImage currentDisplayImage;
    protected float xPosition, yPosition, size;
    protected int width, height;
    protected Rectangle boundaryBox;

    public Entity(Screen screen, float xPosition, float yPosition) {
        this.screen = screen;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = DEFAULT_SIZE;
        // TODO: width and height not updates if change the size value later, have to fix it

        width = (int)(Assets.DEFAULT_ASSETS_WIDTH * size);
        height = (int)(Assets.DEFAULT_ASSETS_HEIGHT * size);

        boundaryBox = new Rectangle(0, 0, width, height);
    }

    public abstract void tick(double deltaTime);

    public abstract void render(Graphics g);

    public float getXPosition() {
        return xPosition;
    }

    public float getYPosition() {
        return yPosition;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
