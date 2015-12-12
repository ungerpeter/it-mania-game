package ch.zhaw.itmania.objects.entities.creatures;

import ch.zhaw.itmania.gfx.Assets;
import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.input.KeyManager;

import java.awt.*;

/**
 * ch.zhaw.itmania.objects.tiles.entities.creatures
 * Created by Peter Unger on 12.12.2015.
 */
public class Player extends Creature {

    public Player(Screen screen, float xPosition, float yPosition) {
        super(screen, xPosition, yPosition);
        currentDisplayImage = Assets.PLAYER2;
    }

    @Override
    protected void receiveInput(KeyManager keyManager) {
        if(keyManager.up) {
            yPosition -= 2;
        }
        if(keyManager.down) {
            yPosition += 2;
        }
        if(keyManager.left) {
            xPosition -= 2;
        }
        if(keyManager.right) {
            xPosition += 2;
        }
    }

    @Override
    public void tick() {
        receiveInput(screen.getKeyManager());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentDisplayImage, (int) xPosition, (int) yPosition, null);
    }
}
