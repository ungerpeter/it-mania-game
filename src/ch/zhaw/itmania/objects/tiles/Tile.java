package ch.zhaw.itmania.objects.tiles;

import ch.zhaw.itmania.objects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ch.zhaw.itmania.objects.tiles
 * Created by Peter Unger on 12.12.2015.
 */
public class Tile extends GameObject {

    public static Tile[] tiles = new Tile[8];

    private static final int DEFAULT_TILE_HEIGHT = 32;
    private static final int DEFAULT_TILE_WIDTH = 32;
    protected BufferedImage texture;
    protected final int id;

    public static final GrassTile GRASS_TILE = new GrassTile(0);
    public static final StoneTile STONE_TILE = new StoneTile(1);
    public static final DirtTile DIRT_TILE = new DirtTile(2);
    public static final BrickTile BRICK_TILE = new BrickTile(3);
    public static final LavaTile LAVA_TILE = new LavaTile(4);
    public static final SnowTile SNOW_TILE = new SnowTile(5);
    public static final BooksTile BOOKS_TILE = new BooksTile(6);
    public static final StreetTile STREET_TILE = new StreetTile(7);

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[0] = Tile.GRASS_TILE;
        tiles[1] = Tile.STONE_TILE;
        tiles[2] = Tile.DIRT_TILE;
        tiles[3] = Tile.BRICK_TILE;
        tiles[4] = Tile.LAVA_TILE;
        tiles[5] = Tile.SNOW_TILE;
        tiles[6] = Tile.BOOKS_TILE;
        tiles[7] = Tile.STREET_TILE;
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
