package ch.zhaw.itmania.objects.entities;

import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.objects.GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * ch.zhaw.itmania.objects.tiles.entities
 * Created by Peter Unger on 12.12.2015.
 */
public abstract class Entity extends GameObject {

    protected Screen screen;
    protected BufferedImage currentDisplayImage;
    protected float xPosition, yPosition;

    public Entity(Screen screen, float xPosition, float yPosition) {
        this.screen = screen;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

}
