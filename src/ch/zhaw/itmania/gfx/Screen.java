package ch.zhaw.itmania.gfx;

import ch.zhaw.itmania.entities.Entity;
import ch.zhaw.itmania.entities.creatures.Player;
import ch.zhaw.itmania.game.Display;
import ch.zhaw.itmania.input.KeyManager;
import ch.zhaw.itmania.worlds.World;

import java.awt.*;
import java.util.ArrayList;

/**
 * ch.zhaw.itmania.gfx
 * Created by Peter Unger on 12.12.2015.
 */
public class Screen {

    ArrayList<Entity> entities = new ArrayList<>();
    Display display;
    KeyManager keyManager;
    World world;
    Camera camera;

    public Screen(Display display, KeyManager keyManager) {

        this.display = display;
        this.keyManager = keyManager;
        world = new World("res/worlds/world.wld");
        entities.add(new Player(this, world.getPlayerSpawnX(), world.getPlayerSpawnY()));
        camera = new Camera(this, 0, 0);
        camera.addCameraListener((CameraListener) entities.get(0));

    }

    public void tick(double deltaTime) {
        keyManager.tick();
        world.tick();
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).tick(deltaTime);
        }
    }

    public void render(Graphics g) {

        world.render(g, camera);

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g);
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public Camera getCamera() {
        return camera;
    }

    public World getWorld() {
        return world;
    }

    public int getWidth() {
        return display.getWidth();
    }

    public int getHeight() {
        return display.getHeight();
    }
}
