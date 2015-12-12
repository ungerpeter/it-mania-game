package ch.zhaw.itmania.objects;

import java.awt.Graphics;

/**
 * ch.zhaw.itmania.objects
 * Created by Peter Unger on 12.12.2015.
 */
public abstract class GameObject {

    public GameObject() {

    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
