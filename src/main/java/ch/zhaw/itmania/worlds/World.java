package ch.zhaw.itmania.worlds;

import ch.zhaw.itmania.entities.Coin;
import ch.zhaw.itmania.entities.Entity;
import ch.zhaw.itmania.entities.creatures.Creature;
import ch.zhaw.itmania.entities.creatures.CreatureEventListener;
import ch.zhaw.itmania.gfx.Camera;
import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.tiles.GrassTile;
import ch.zhaw.itmania.tiles.Tile;
import ch.zhaw.itmania.utils.FileUtils;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * ch.zhaw.itmania.worlds
 * Created by Peter Unger on 12.12.2015.
 */
public class World implements CreatureEventListener {

    private Screen screen;
    private int width, height;
    private int[][] tiles;
    private ArrayList<Entity> entities;
    private int spawnX;
    private int spawnY;

    public World(Screen screen, String path) {
        loadWorld(path);
        entities = new ArrayList<Entity>();
        this.screen = screen;
        generateRandomCoins(25);
    }

    public void tick(Double deltaTime) {
        if(entities.size() > 0) {
            for(Entity entity : entities) {
                entity.tick(deltaTime);
            }
        }
    }

    public void render(Graphics g, Camera camera) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(g, x * Tile.DEFAULT_TILE_WIDTH, y * Tile.DEFAULT_TILE_HEIGHT, camera);
            }
        }
        // TODO: Only render tiles which are actually in the display.
        if(entities.size() > 0) {
            for(Entity entity : entities) {
                entity.render(g);
            }
        }
    }

    public Tile getTile(int x, int y) {
        Tile tile = Tile.tiles[tiles[x][y]];
        if(tile == null) return new GrassTile(999);
        return tile;
    }

    public Entity getEntity(int x, int y) {
        Entity targetEntity = null;
        for (Entity entity : entities) {
            if(entity.getXPosition() == x * Tile.DEFAULT_TILE_WIDTH && entity.getYPosition() == y * Tile.DEFAULT_TILE_HEIGHT) {
                targetEntity = entity;
                break;
            }
        }
        if(targetEntity == null) return null;
        return targetEntity;
    }

    private void loadWorld(String path) {
        String file = FileUtils.loadFileAsString(path);
        String[] chars = file.split("\\s+");
        width = FileUtils.parseInt(chars[0]);
        height = FileUtils.parseInt(chars[1]);
        spawnX = FileUtils.parseInt(chars[2]) * Tile.DEFAULT_TILE_WIDTH;
        spawnY = FileUtils.parseInt(chars[3]) * Tile.DEFAULT_TILE_HEIGHT;

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = FileUtils.parseInt(chars[(x + y * width) + 4]);
            }
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        if(entity instanceof Creature) {
            ((Creature) entity).addCreatureEventListener(this);
        }
    }

    public int getPlayerSpawnX() {
        return spawnX;
    }

    public int getPlayerSpawnY() {
        return spawnY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void onCreatureMoved(Creature creature) {
        int xTilePosition = (int)((creature.getXPosition() + creature.getWidth() / 2) / Tile.DEFAULT_TILE_WIDTH);
        int yTilePosition = (int)((creature.getYPosition() + creature.getHeight() / 2) / Tile.DEFAULT_TILE_HEIGHT);
        getTile(xTilePosition, yTilePosition).onTouch(creature);
        Entity entity = getEntity(xTilePosition, yTilePosition);
        if(entity != null) {
            entity.onTouch(creature);
        }
    }

    public void removeEntity(Entity entity) {

        /*Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity currEntity = iterator.next();

            if (currEntity == entity) {
                iterator.remove();
            }
        }*/
    }

    public void generateRandomCoins(int amount) {
        while(amount > 0) {
            int randomPositionX = (int) (Math.random() * width);
            int randomPositionY = (int) (Math.random() * height);

            if(!getTile(randomPositionX, randomPositionY).isSolid()) {
                entities.add(new Coin(this, randomPositionX * Tile.DEFAULT_TILE_WIDTH, randomPositionY * Tile.DEFAULT_TILE_HEIGHT));
                amount--;
            }
        }
    }

    public Screen getScreen() {
        return screen;
    }
}
