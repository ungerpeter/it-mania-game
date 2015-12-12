package ch.zhaw.itmania.gfx;

import ch.zhaw.itmania.objects.tiles.Tile;

import java.awt.image.BufferedImage;

/**
 * Created by ungerpet on 11.12.2015.
 */
public class Assets {

    private static final int width = 32, height = 32;
    public static final int DEFAULT_TILES_WIDTH = width;
    public static final int DEFAULT_TILES_HEIGHT = height;

    // Entities
    public static BufferedImage PLAYER1, PLAYER2;

    // Tiles
    public static BufferedImage GRASS_TILE, STONE_TILE, DIRT_TILE, BRICK_TILE, LAVA_TILE, SNOW_TILE, BOOKS_TILE, STREET_TILE;

    /**
     * Load all Assets for the game.
     */
    public static void init() {

        // Sprite sheets
        SpriteSheet playerSpriteSheet = new SpriteSheet("/sprite_player.png");
        SpriteSheet terrainSpriteSheet = new SpriteSheet("/tiles/terrain.png");

        // Entities
        PLAYER1 = playerSpriteSheet.crop(width*0,height*0,width,height);
        PLAYER2 = playerSpriteSheet.crop(width*1,height*0,width,height);

        // Tiles
        GRASS_TILE = terrainSpriteSheet.crop(width*0, height*0, width, height);
        STONE_TILE = terrainSpriteSheet.crop(width*1, height*0, width, height);
        DIRT_TILE = terrainSpriteSheet.crop(width*2, height*0, width, height);
        BRICK_TILE = terrainSpriteSheet.crop(width*3, height*0, width, height);

        LAVA_TILE = terrainSpriteSheet.crop(width*0, height*1, width, height);
        SNOW_TILE = terrainSpriteSheet.crop(width*1, height*1, width, height);
        BOOKS_TILE = terrainSpriteSheet.crop(width*2, height*1, width, height);
        STREET_TILE = terrainSpriteSheet.crop(width*3, height*1, width, height);

    }

}
