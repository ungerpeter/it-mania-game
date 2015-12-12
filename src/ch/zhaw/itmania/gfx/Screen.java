package ch.zhaw.itmania.gfx;

import ch.zhaw.itmania.objects.entities.Entity;
import ch.zhaw.itmania.objects.entities.creatures.Player;
import ch.zhaw.itmania.input.KeyManager;
import ch.zhaw.itmania.objects.tiles.Tile;
import ch.zhaw.itmania.worlds.World;

import java.awt.*;
import java.util.ArrayList;

/**
 * ch.zhaw.itmania.gfx
 * Created by Peter Unger on 12.12.2015.
 */
public class Screen {

    ArrayList<Entity> entities = new ArrayList<>();
    ArrayList<Tile> tiles = new ArrayList<>();
    KeyManager keyManager;
    World world;

    public Screen(KeyManager keyManager) {
        this.keyManager = keyManager;
        world = new World("res/worlds/world.wld");
        System.out.println(world.getPlayerSpawnX());
        entities.add(new Player(this, world.getPlayerSpawnX(), world.getPlayerSpawnY()));
    }

    public void tick() {
        keyManager.tick();
        world.tick();
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).tick();
        }
    }

    public void render(Graphics g) {

        world.render(g);

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g);
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
}
