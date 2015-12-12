package ch.zhaw.itmania.entities.creatures;

import ch.zhaw.itmania.entities.Entity;
import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.input.KeyManager;

/**
 * ch.zhaw.itmania.entities.creatures
 * Created by Peter Unger on 12.12.2015.
 */
public abstract class Creature extends Entity {

    protected int health;

    public Creature(Screen screen, float xPosition, float yPosition) {
        super(screen, xPosition, yPosition);
        health = 100;
    }

    protected abstract void receiveInput(KeyManager keyManager);

}
