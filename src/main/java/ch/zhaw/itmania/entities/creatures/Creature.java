package ch.zhaw.itmania.entities.creatures;

import ch.zhaw.itmania.entities.Entity;
import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.input.KeyManager;
import ch.zhaw.itmania.tiles.Tile;
import ch.zhaw.itmania.worlds.World;

import java.util.ArrayList;

/**
 * ch.zhaw.itmania.tiles.entities.creatures
 * Created by Peter Unger on 12.12.2015.
 */
public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 100;
    public static final float DEFAULT_SPEED = 2f;
    public static final int DIRECTION_UP_ID = 0;
    public static final int DIRECTION_DOWN_ID = 1;
    public static final int DIRECTION_LEFT_ID = 2;
    public static final int DIRECTION_RIGHT_ID = 3;
    protected ArrayList<CreatureEventListener> creatureEventListeners = new ArrayList<CreatureEventListener>();
    protected final float speed;
    protected int health;

    public Creature(World world, float xPosition, float yPosition) {
        super(world, xPosition, yPosition);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
    }

    public void addCreatureEventListener(CreatureEventListener eventListener) {
        creatureEventListeners.add(eventListener);
    }

    protected abstract void updatePosition(KeyManager keyManager, double deltaTime);

    protected void moveLeft(float amount) {
        if(!detectCollision(DIRECTION_LEFT_ID, amount)) {
            xPosition -= amount;
            moved();
        }
    }

    protected void moved() {
        for(CreatureEventListener listener : creatureEventListeners) {
            listener.onCreatureMoved(this);
        }
    };

    protected void moveRight(float amount) {
        if(!detectCollision(DIRECTION_RIGHT_ID, amount)) {
            xPosition += amount;
            moved();
        }
    }

    protected void moveUp(float amount) {
        if(!detectCollision(DIRECTION_UP_ID, amount)) {
            yPosition -= amount;
            moved();
        }
    }

    protected void moveDown(float amount) {
        if(!detectCollision(DIRECTION_DOWN_ID, amount)) {
            yPosition += amount;
            moved();
        }
    }

    protected boolean detectCollision(int direction, float amount) {

        int tryPosX = 0;
        int tryPosY = 0;

        switch (direction) {
            case DIRECTION_UP_ID:
                tryPosX = (int) ((xPosition + boundaryBox.x) / Tile.DEFAULT_TILE_WIDTH);
                tryPosY = (int) ((yPosition - amount + boundaryBox.y) / Tile.DEFAULT_TILE_HEIGHT);
                break;

            case DIRECTION_DOWN_ID:
                tryPosX = (int) ((xPosition + boundaryBox.x) / Tile.DEFAULT_TILE_WIDTH);
                tryPosY = (int) ((yPosition + amount + boundaryBox.y + boundaryBox.height) / Tile.DEFAULT_TILE_HEIGHT);
                break;

            case DIRECTION_LEFT_ID:
                tryPosX = (int) ((xPosition - amount + boundaryBox.x) / Tile.DEFAULT_TILE_WIDTH);
                tryPosY = (int) ((yPosition + boundaryBox.y) / Tile.DEFAULT_TILE_HEIGHT);
                break;

            case DIRECTION_RIGHT_ID:
                tryPosX = (int) ((xPosition + amount + boundaryBox.x + boundaryBox.width) / Tile.DEFAULT_TILE_WIDTH);
                tryPosY = (int) ((yPosition + boundaryBox.y) / Tile.DEFAULT_TILE_HEIGHT);
                break;
            // TODO: directions still buggy  -> have to check all 4 corners
        }

        if (world.getTile(tryPosX, tryPosY).isSolid()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHealth(int health) {
        this.health = health;
        checkIfAlive();
    }

    protected boolean checkIfAlive() {
        if(health > 0) {
            return true;
        } else {
            die();
            return false;
        }
    }

    protected void die() {
        //screen.getWorld().removeEntity(this);
    }
}
