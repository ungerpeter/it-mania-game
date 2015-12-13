package ch.zhaw.itmania.worlds;

import ch.zhaw.itmania.gfx.Camera;
import ch.zhaw.itmania.tiles.GrassTile;
import ch.zhaw.itmania.tiles.Tile;
import ch.zhaw.itmania.utils.FileUtils;

import java.awt.Graphics;

/**
 * ch.zhaw.itmania.worlds
 * Created by Peter Unger on 12.12.2015.
 */
public class World {
    
    private int width, height;
    private int[][] tiles;
    private int spawnX;
    private int spawnY;

    public World(String path) {
        loadWorld(path);
    }

    public void tick() {

    }

    public void render(Graphics g, Camera camera) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(g, x * Tile.DEFAULT_TILE_WIDTH, y * Tile.DEFAULT_TILE_HEIGHT, camera);
            }
        }
        // TODO: Only render tiles which are actually in the display.
    }

    public Tile getTile(int x, int y) {
        Tile tile = Tile.tiles[tiles[x][y]];
        if(tile == null) return new GrassTile(999);
        return tile;
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
}
