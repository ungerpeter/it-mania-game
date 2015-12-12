package ch.zhaw.itmania.game;

import ch.zhaw.itmania.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 *
 * Created by Peter Unger on 12.11.2015.
 *
 */
public class Game implements Runnable {

    private static final int BUFFER_LVL = 3;
    private final Display display;
    private BufferStrategy bufferStrategy;

    // Game Loop Fields
    private boolean running = false;
    private long lastFpsTime;
    private int FPS;

    // Tests
    private BufferedImage testBufferedImage;
    private int tempCount = 0;

    public Game() {

        // Load Assets
        Assets.init();

        // Load Display
        display = new Display();
        bufferStrategy = display.generateBufferStrategy(BUFFER_LVL);

        // Tests
        testBufferedImage = Assets.PLAYER1;
    }

    @Override
    public void run() {

        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // in nanoseconds

        // Game loop
        while (running)
        {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double deltaTime = updateLength / ((double)OPTIMAL_TIME);

            lastFpsTime += updateLength;
            FPS++;

            calculateFPS();
            doGameUpdates(deltaTime);
            render();

            try{
                // calculate sleep time for the 60 fps in ms
                long sleepTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if(sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void calculateFPS() {
        if (lastFpsTime >= 1000000000) // Time in nanoseconds
        {
            // Debug info
            System.out.println("(FPS: "+ FPS +")");

            lastFpsTime = 0;
            FPS = 0;

            // Test
            if(tempCount == 1) {
                testBufferedImage = Assets.PLAYER1;
                tempCount = 0;
            } else {
                testBufferedImage = Assets.PLAYER2;
                tempCount = 1;
            }

        }
    }

    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, display.getWidth(), display.getHeight());
        g.drawImage(testBufferedImage, 0, 0, testBufferedImage.getWidth(), testBufferedImage.getHeight(), null);
        g.dispose();
        bufferStrategy.show();
    }

    private void doGameUpdates(double deltaTime)
    {
        /*for (int i = 0; i < stuff.size(); i++)
        {
            // all time-related values must be multiplied by delta!
            Stuff s = stuff.get(i);
            s.velocity += Gravity.VELOCITY * delta;
            s.position += s.velocity * delta;

            // stuff that isn't time-related doesn't care about delta...
            if (s.velocity >= 1000)
            {
                s.color = Color.RED;
            }
            else
            {
                s.color = Color.BLUE;
            }
        }*/
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }
}
