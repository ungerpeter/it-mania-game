package ch.zhaw.itmania.tiles;

import ch.zhaw.itmania.entities.Entity;
import ch.zhaw.itmania.entities.creatures.Creature;
import ch.zhaw.itmania.gfx.Assets;

/**
 * ch.zhaw.itmania.tiles
 * Created by Peter Unger on 12.12.2015.
 */
public class LavaTile extends Tile {
    public LavaTile(int id) {
        super(Assets.LAVA_TILE, id);
    }

    @Override
    public void onTouch(Entity entity) {
        if(entity instanceof Creature) {
            ((Creature) entity).setHealth(0);
        }
    }
}
