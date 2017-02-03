package ch.zhaw.itmania.entities;

import ch.zhaw.itmania.entities.creatures.Player;
import ch.zhaw.itmania.gfx.Assets;
import ch.zhaw.itmania.worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ch.zhaw.itmania.entities
 * Created by Peter Unger on 13.12.2015.
 */
public class Coin extends Entity {

    private boolean earned = false;

    public Coin(World world, float xPosition, float yPosition) {
        super(world, xPosition, yPosition);
        currentDisplayImage = Assets.COIN;
    }

    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics g) {
        if(!earned) {
            g.drawImage(currentDisplayImage, (int) (xPosition - world.getScreen().getCamera().getXOffset()), (int) (yPosition - world.getScreen().getCamera().getYOffset()), width, height, null);
        }
    }

    @Override
    public void onTouch(Entity entity) {
        if(entity instanceof Player) {
            if(!earned) {
                ((Player) entity).addGold(100);
                earned = true;
            }
        }
    }
}
