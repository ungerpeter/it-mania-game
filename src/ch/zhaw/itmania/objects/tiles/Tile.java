package ch.zhaw.itmania.objects.tiles;

import ch.zhaw.itmania.objects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ch.zhaw.itmania.objects.tiles
 * Created by Peter Unger on 12.12.2015.
 */
public class Tile extends GameObject {

    // TODO: Specify tiles array size, also not needed yet!
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);

    private static final int DEFAULT_TILE_HEIGHT = 32;
    private static final int DEFAULT_TILE_WIDTH = 32;
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[0] = Tile.grassTile;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        // TODO: Correct position parameters
        g.drawImage(texture, 0, 0, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT, null);
    }

    public void render(Graphics g, int xPosition, int yPosition) {
        g.drawImage(texture, xPosition, yPosition, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }
}
