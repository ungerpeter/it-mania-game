package ch.zhaw.itmania.entities.creatures;

import ch.zhaw.itmania.gfx.Assets;
import ch.zhaw.itmania.gfx.CameraListener;
import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.input.KeyManager;

import java.awt.*;

/**
 * ch.zhaw.itmania.tiles.entities.creatures
 * Created by Peter Unger on 12.12.2015.
 */
public class Player extends Creature implements CameraListener {

    public Player(Screen screen, float xPosition, float yPosition) {
        super(screen, xPosition, yPosition);
        currentDisplayImage = Assets.PLAYER2;

        boundaryBox.x = 5;
        boundaryBox.y = 10;
        boundaryBox.width = 22;
        boundaryBox.height = 22;
    }

    @Override
    protected void updatePosition(KeyManager keyManager, double deltaTime) {

        if(keyManager.up) {
            if(keyManager.left || keyManager.right) {
                moveUp((float) Math.sqrt(speed * deltaTime));
            } else {
                moveUp((float)(speed * deltaTime));
            }
        }

        if(keyManager.down) {
            if(keyManager.left || keyManager.right) {
                moveDown((float) Math.sqrt(speed * deltaTime));
            } else {
                moveDown((float) (speed * deltaTime));
            }
        }

        if(keyManager.left) {
            if(keyManager.up || keyManager.down) {
                moveLeft((float) Math.sqrt(speed * deltaTime));
            } else {
                moveLeft((float) (speed * deltaTime));
            }
        }

        if(keyManager.right) {
            if(keyManager.up || keyManager.down) {
                moveRight((float) Math.sqrt(speed * deltaTime));
            } else {
                moveRight((float)(speed * deltaTime));
            }
        }
    }

    /*private void checkPosition(Camera camera) {
        if(xPosition > screen.getWorld().getWidth() + camera.getXOffset() - 100) {
            //camera.move(100,0);
        }
        if(xPosition < camera.getXOffset() + 100) {
            //camera.move(-100,0);
        }
    }*/

    @Override
    public void tick(double deltaTime) {
        //checkPosition(screen.getCamera());
        updatePosition(screen.getKeyManager(), deltaTime);
        screen.getCamera().follow(this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentDisplayImage, (int) (xPosition - screen.getCamera().getXOffset()), (int) (yPosition - screen.getCamera().getYOffset()), width, height, null);

        g.setColor(Color.red);
        g.drawRect((int)(xPosition + boundaryBox.x - screen.getCamera().getXOffset()), (int)(yPosition + boundaryBox.y - screen.getCamera().getYOffset()), boundaryBox.width, boundaryBox.height);

    }

    @Override
    public void onCameraMove() {
        //System.out.println("Camera Moved");
    }
}
