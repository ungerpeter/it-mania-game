package ch.zhaw.itmania.gfx;

import ch.zhaw.itmania.objects.tiles.Tile;

import java.awt.image.BufferedImage;

/**
 * Created by ungerpet on 11.12.2015.
 */
public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage PLAYER1, PLAYER2, GRASS_TILE;

    /**
     * Load all Assets for the game.
     */
    public static void init() {
        SpriteSheet spriteSheet = new SpriteSheet("/sprite_player.png");
        SpriteSheet terrainSpriteSheet = new SpriteSheet("/tiles_terrain.png");
        PLAYER1 = spriteSheet.crop(width*0,height*0,width,height);
        PLAYER2 = spriteSheet.crop(width*1,height*0,width,height);
        GRASS_TILE = terrainSpriteSheet.crop(width*0, height*0, width, height);
    }

}
