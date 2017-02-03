package ch.zhaw.itmania.tiles;

import ch.zhaw.itmania.gfx.Assets;

/**
 * ch.zhaw.itmania.tiles
 * Created by Peter Unger on 12.12.2015.
 */
public class BooksTile extends Tile {
    public BooksTile(int id) {
        super(Assets.BOOKS_TILE, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
