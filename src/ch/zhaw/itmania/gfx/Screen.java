package ch.zhaw.itmania.gfx;

import ch.zhaw.itmania.entities.Entity;
import ch.zhaw.itmania.entities.creatures.Player;
import ch.zhaw.itmania.input.KeyManager;

import java.awt.*;
import java.util.ArrayList;

/**
 * ch.zhaw.itmania.gfx
 * Created by Peter Unger on 12.12.2015.
 */
public class Screen {

    ArrayList<Entity> entities = new ArrayList<>();
    KeyManager keyManager;

    public Screen(KeyManager keyManager) {
        this.keyManager = keyManager;
        entities.add(new Player(this, 100, 100));
    }

    public void tick() {
        keyManager.tick();
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g);
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
}
