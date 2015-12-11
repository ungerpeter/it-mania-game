package ch.zhaw.itmania.gfx;

/**
 * Created by ungerpet on 09.12.2015.
 */
public class Screen {
    private static final int MAP_WIDTH = 64;
    private static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
    private int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
    private int[] colours = new int[MAP_WIDTH * MAP_WIDTH * 4];

    private int xOffset;
    private int yOffset;
    private int width;
    private int height;

    private SpriteSheet spriteSheet;

    public Screen(int width, int height, SpriteSheet spriteSheet) {
        this.width = width;
        this.height = height;
        this.spriteSheet = spriteSheet;

        for (int i = 0; i < MAP_WIDTH * MAP_WIDTH; i++) {
            colours[i*4+0] = 0xff00ff; // Pink
            colours[i*4+1] = 0x00ffff; // Cyan
            colours[i*4+2] = 0xffff00; // Yellow
            colours[i*4+3] = 0xffffff; // White
        }
    }

    public void render(int[] pixels, int offset, int row) {
        for (int yTile = yOffset >> 3; yTile <= (yOffset + height) >> 3 ; yTile++) {
            int yMin = yTile * 8 - yOffset;
            int yMax = yMin + 8;
            if(yMin < 0) yMin = 0;
            if(yMax > height) yMax = height;

            for (int xTile = xOffset >> 3; xTile <= (xOffset + width) >> 3 ; xTile++) {
                int xMin = xTile * 8 - xOffset;
                int xMax = xMin + 8;
                if(xMin < 0) xMin = 0;
                if(xMax > width) xMax = width;

                int tileIndex = (xTile & (MAP_WIDTH_MASK)) + (yTile & (MAP_WIDTH_MASK)) * MAP_WIDTH;

                for (int y = yMin; y < yMax; y++) {
                    int sheetPixel = ((y + yOffset) & 7) * spriteSheet.getWidth() + ((xMin + xOffset) & 7);
                    int tilePixel = offset + xMin + y * row;

                    for(int x = xMin; x < xMax; x++) {
                        int colour = tileIndex * 4 + spriteSheet.getPixelAt(sheetPixel++);
                        pixels[tilePixel++] = colours[colour];
                    }
                }
            }
        }
    }
}
