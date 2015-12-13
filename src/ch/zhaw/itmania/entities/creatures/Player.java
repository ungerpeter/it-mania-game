package ch.zhaw.itmania.entities.creatures;

import ch.zhaw.itmania.gfx.Animation;
import ch.zhaw.itmania.gfx.Assets;
import ch.zhaw.itmania.gfx.CameraListener;
import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.input.KeyManager;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ch.zhaw.itmania.tiles.entities.creatures
 * Created by Peter Unger on 12.12.2015.
 */
public class Player extends Creature implements CameraListener {

    Map<String, Animation> animations = new HashMap<String, Animation>();
    Animation currentAnimation;

    public Player(Screen screen, float xPosition, float yPosition) {
        super(screen, xPosition, yPosition);
        currentDisplayImage = Assets.PLAYER2;

        boundaryBox.x = 5;
        boundaryBox.y = 10;
        boundaryBox.width = 22;
        boundaryBox.height = 22;

        animations.put("down", new Animation(500, Assets.PLAYER_DOWN_ANIMATION));
        animations.put("left", new Animation(500, Assets.PLAYER_LEFT_ANIMATION));
        animations.put("right", new Animation(500, Assets.PLAYER_RIGHT_ANIMATION));
        animations.put("up", new Animation(500, Assets.PLAYER_UP_ANIMATION));
        currentAnimation = animations.get("down");
    }

    @Override
    protected void updatePosition(KeyManager keyManager, double deltaTime) {

        currentAnimation.pause();

        if(keyManager.up) {
            if(keyManager.left || keyManager.right) {
                moveUp((float) Math.sqrt(speed * deltaTime));
            } else {
                moveUp((float)(speed * deltaTime));
            }
            currentAnimation = animations.get("up");
            currentAnimation.resume();
        }

        if(keyManager.down) {
            if(keyManager.left || keyManager.right) {
                moveDown((float) Math.sqrt(speed * deltaTime));
            } else {
                moveDown((float) (speed * deltaTime));
            }
            currentAnimation = animations.get("down");
            currentAnimation.resume();
        }

        if(keyManager.left) {
            if(keyManager.up || keyManager.down) {
                moveLeft((float) Math.sqrt(speed * deltaTime));
            } else {
                moveLeft((float) (speed * deltaTime));
            }
            currentAnimation = animations.get("left");
            currentAnimation.resume();
        }

        if(keyManager.right) {
            if(keyManager.up || keyManager.down) {
                moveRight((float) Math.sqrt(speed * deltaTime));
            } else {
                moveRight((float) (speed * deltaTime));
            }
            currentAnimation = animations.get("right");
            currentAnimation.resume();
        }
    }

    @Override
    public void tick(double deltaTime) {
        updatePosition(screen.getKeyManager(), deltaTime);
        currentAnimation.tick(deltaTime);
        screen.getCamera().follow(this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentAnimation.getCurrentAnimationFrame(), (int) (xPosition - screen.getCamera().getXOffset()), (int) (yPosition - screen.getCamera().getYOffset()), width, height, null);

        /*g.setColor(Color.red);
        g.drawRect((int)(xPosition + boundaryBox.x - screen.getCamera().getXOffset()), (int)(yPosition + boundaryBox.y - screen.getCamera().getYOffset()), boundaryBox.width, boundaryBox.height);
        */
    }

    @Override
    public void onCameraMove() {
        //System.out.println("Camera Moved");
    }
}
