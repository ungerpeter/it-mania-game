package ch.zhaw.itmania.gfx;

import java.awt.image.BufferedImage;

/**
 * Created by ungerpet on 11.12.2015.
 */
public class Assets {

    private static final int width = 32, height = 32;
    public static final int DEFAULT_ASSETS_WIDTH = width;
    public static final int DEFAULT_ASSETS_HEIGHT = height;

    // Entities
    public static BufferedImage PLAYER1, PLAYER2;

    // Animations
    public static BufferedImage[] PLAYER_DOWN_ANIMATION;
    public static BufferedImage[] PLAYER_UP_ANIMATION;
    public static BufferedImage[] PLAYER_LEFT_ANIMATION;
    public static BufferedImage[] PLAYER_RIGHT_ANIMATION;

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

        // Anmations
        PLAYER_DOWN_ANIMATION = new BufferedImage[3];
        PLAYER_DOWN_ANIMATION[0] = playerSpriteSheet.crop(width*0,height*0,width,height);
        PLAYER_DOWN_ANIMATION[1] = playerSpriteSheet.crop(width*1,height*0,width,height);
        PLAYER_DOWN_ANIMATION[2] = playerSpriteSheet.crop(width*2,height*0,width,height);
        PLAYER_LEFT_ANIMATION = new BufferedImage[3];
        PLAYER_LEFT_ANIMATION[0] = playerSpriteSheet.crop(width*0,height*1,width,height);
        PLAYER_LEFT_ANIMATION[1] = playerSpriteSheet.crop(width*1,height*1,width,height);
        PLAYER_LEFT_ANIMATION[2] = playerSpriteSheet.crop(width*2,height*1,width,height);
        PLAYER_RIGHT_ANIMATION = new BufferedImage[3];
        PLAYER_RIGHT_ANIMATION[0] = playerSpriteSheet.crop(width*0,height*2,width,height);
        PLAYER_RIGHT_ANIMATION[1] = playerSpriteSheet.crop(width*1,height*2,width,height);
        PLAYER_RIGHT_ANIMATION[2] = playerSpriteSheet.crop(width*2,height*2,width,height);
        PLAYER_UP_ANIMATION = new BufferedImage[3];
        PLAYER_UP_ANIMATION[0] = playerSpriteSheet.crop(width*0,height*3,width,height);
        PLAYER_UP_ANIMATION[1] = playerSpriteSheet.crop(width*1,height*3,width,height);
        PLAYER_UP_ANIMATION[2] = playerSpriteSheet.crop(width*2,height*3,width,height);

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
