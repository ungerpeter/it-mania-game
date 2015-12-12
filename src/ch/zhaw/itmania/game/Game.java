package ch.zhaw.itmania.game;

import ch.zhaw.itmania.gfx.Assets;
import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.input.KeyManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 *
 * Created by Peter Unger on 12.11.2015.
 *
 */
public class Game implements Runnable {

    private static final int BUFFER_LVL = 3;
    private static final int TARGET_FPS = 60;
    private final Display display;
    private Screen screen;
    private BufferStrategy bufferStrategy;

    // Game Loop Fields
    private boolean running = false;
    private long lastFpsTime;
    private int FPS;

    public Game() {

        // Load Assets
        Assets.init();

        // Load Display
        display = new Display();
        bufferStrategy = display.generateBufferStrategy(BUFFER_LVL);

        // Setup Key Manager
        KeyManager keyManager = new KeyManager();
        display.getWindowFrame().addKeyListener(keyManager);
        screen = new Screen(keyManager);
    }

    /**
     * The threads run method contains the game loop to update everything.
     * Furthermore this method limits the FPS to 60.
     */
    @Override
    public void run() {

        long lastLoopTime = System.nanoTime();
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

    /**
     * Sets the Frame count for every second.
     */
    private void calculateFPS() {
        if (lastFpsTime >= 1000000000) // Time in nanoseconds
        {
            // Debug info
            System.out.println("(FPS: "+ FPS +")");

            lastFpsTime = 0;
            FPS = 0;
        }
    }

    /**
     * This method renders the Frame of the current game state.
     */
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, display.getWidth(), display.getHeight());

        screen.render(g);

        g.dispose();
        bufferStrategy.show();
    }

    /**
     * Updates the logic of the game.
     * deltaTime is used to sync the values with runtime.
     * @param deltaTime
     */
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

        screen.tick();
    }

    /**
     * Starts the gamethread, which starts the gameloop
     */
    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    /**
     * Stops the gameloop
     */
    public synchronized void stop() {
        running = false;
    }
}
