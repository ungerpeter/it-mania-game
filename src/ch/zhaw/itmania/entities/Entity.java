package ch.zhaw.itmania.entities;

import ch.zhaw.itmania.game.Game;
import ch.zhaw.itmania.gfx.Screen;

import java.awt.Graphics;

/**
 * ch.zhaw.itmania.entities
 * Created by Peter Unger on 12.12.2015.
 */
public abstract class Entity {

    protected float xPosition, yPosition;
    protected Screen screen;

    public Entity(Screen screen, float xPosition, float yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.screen = screen;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
