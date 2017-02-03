package ch.zhaw.itmania.tiles;

import ch.zhaw.itmania.gfx.Assets;

/**
 * ch.zhaw.itmania.tiles
 * Created by Peter Unger on 12.12.2015.
 */
public class BrickTile extends Tile {
    public BrickTile(int id) {
        super(Assets.BRICK_TILE, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
