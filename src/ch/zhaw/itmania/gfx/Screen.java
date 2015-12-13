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

    Display display;
    KeyManager keyManager;
    World world;
    Camera camera;

    public Screen(Display display, KeyManager keyManager) {

        this.display = display;
        this.keyManager = keyManager;
        world = new World(this, "res/worlds/world.wld");
        Entity player = (new Player(world , world.getPlayerSpawnX(), world.getPlayerSpawnY()));
        world.addEntity(player);
        camera = new Camera(this, 0, 0);
        camera.addCameraListener((CameraListener) player);

    }

    public void tick(double deltaTime) {
        keyManager.tick();
        world.tick(deltaTime);
    }

    public void render(Graphics g) {
        world.render(g, camera);
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

    public Display getDisplay() {
        return display;
    }
}
