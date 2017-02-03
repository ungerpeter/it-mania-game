package ch.zhaw.itmania.tiles;

import ch.zhaw.itmania.gfx.Assets;

/**
 * ch.zhaw.itmania.tiles
 * Created by Peter Unger on 12.12.2015.
 */
public class StoneTile extends Tile {
    public StoneTile(int id) {
        super(Assets.STONE_TILE, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
