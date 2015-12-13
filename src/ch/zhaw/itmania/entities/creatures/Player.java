package ch.zhaw.itmania.entities.creatures;

import ch.zhaw.itmania.entities.Entity;
import ch.zhaw.itmania.gfx.Animation;
import ch.zhaw.itmania.gfx.Assets;
import ch.zhaw.itmania.gfx.CameraListener;
import ch.zhaw.itmania.input.KeyManager;
import ch.zhaw.itmania.worlds.World;

import javax.swing.*;
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
    private int gold = 0;

    public Player(World world, float xPosition, float yPosition) {
        super(world, xPosition, yPosition);

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

    public void addGold(int amount) {
        gold += amount;
        System.out.println("Current Player Gold: " + gold);
    }

    @Override
    public void tick(double deltaTime) {
        updatePosition(world.getScreen().getKeyManager(), deltaTime);
        currentAnimation.tick(deltaTime);
        world.getScreen().getCamera().follow(this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentAnimation.getCurrentAnimationFrame(), (int) (xPosition - world.getScreen().getCamera().getXOffset()), (int) (yPosition - world.getScreen().getCamera().getYOffset()), width, height, null);

        /*g.setColor(Color.red);
        g.drawRect((int)(xPosition + boundaryBox.x - screen.getCamera().getXOffset()), (int)(yPosition + boundaryBox.y - screen.getCamera().getYOffset()), boundaryBox.width, boundaryBox.height);
        */
    }

    @Override
    public void onTouch(Entity entity) {
        return;
    }

    @Override
    public void onCameraMove() {
        //System.out.println("Camera Moved");
    }

    @Override
    protected void die() {
        int result = JOptionPane.showConfirmDialog(world.getScreen().getDisplay().getWindowFrame(), "You are dead! Game will be closed now..", "Game over", JOptionPane.OK_OPTION);
        if(result == JOptionPane.OK_OPTION || result == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
}
