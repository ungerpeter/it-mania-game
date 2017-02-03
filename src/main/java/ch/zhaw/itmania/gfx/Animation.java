package ch.zhaw.itmania.gfx;

import java.awt.image.BufferedImage;

/**
 * ch.zhaw.itmania.gfx
 * Created by Peter Unger on 13.12.2015.
 */
public class Animation {

    private double lastTime = System.currentTimeMillis();
    private double switchTime; // in milliseconds
    private double timer = 0;
    private int speed, index;
    private BufferedImage[] animationFrames;
    private boolean running;

    public Animation(int speed, BufferedImage[] animationFrames) {
        this.speed = speed;
        this.animationFrames = animationFrames;
        index = 0;
        switchTime = speed / animationFrames.length;
    }

    public void tick(Double deltaTime) {
        if(running) {
            timer += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            if (timer > switchTime) {
                timer = 0;
                index++;
                if (index >= animationFrames.length) {
                    index = 0;
                }
            }
        }
    }

    public BufferedImage getCurrentAnimationFrame() {
        return animationFrames[index];
    }

    public void pause() {
        running = false;
    }

    public void resume() {
        running = true;
    }
}
