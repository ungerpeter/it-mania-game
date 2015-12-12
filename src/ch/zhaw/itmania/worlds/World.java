package ch.zhaw.itmania.worlds;

import ch.zhaw.itmania.objects.tiles.GrassTile;
import ch.zhaw.itmania.objects.tiles.Tile;
import ch.zhaw.itmania.utils.FileUtils;

import java.awt.Graphics;
import java.io.File;

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

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // TODO: Get rid of the fix tile width and height
                getTile(x, y).render(g, x * 32, y * 32);
            }
        }
    }

    public Tile getTile(int x, int y) {
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null) return new GrassTile(999);
        return t;
    }
    private void loadWorld(String path) {
        String file = FileUtils.loadFileAsString(path);
        String[] chars = file.split("\\s+");
        width = FileUtils.parseInt(chars[0]);
        height = FileUtils.parseInt(chars[1]);
        spawnX = FileUtils.parseInt(chars[2]) * 32;
        spawnY = FileUtils.parseInt(chars[3]) * 32;
        // TODO: Get rid of the fix tile size

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
}
