package ch.zhaw.itmania.entities.creatures;

import ch.zhaw.itmania.gfx.Assets;
import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.input.KeyManager;

import java.awt.*;

/**
 * ch.zhaw.itmania.entities.creatures
 * Created by Peter Unger on 12.12.2015.
 */
public class Player extends Creature {

    public Player(Screen screen, float xPosition, float yPosition) {
        super(screen, xPosition, yPosition);
    }

    @Override
    protected void receiveInput(KeyManager keyManager) {
        if(keyManager.up) {
            yPosition -= 3;
        }
        if(keyManager.down) {
            yPosition += 3;
        }
        if(keyManager.left) {
            xPosition -= 3;
        }
        if(keyManager.right) {
            xPosition += 3;
        }
    }

    @Override
    public void tick() {
        receiveInput(screen.getKeyManager());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.PLAYER1, (int) xPosition, (int) yPosition, null);
    }
}
