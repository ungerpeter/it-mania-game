package ch.zhaw.itmania.game;

import ch.zhaw.itmania.gfx.Assets;
import ch.zhaw.itmania.gfx.Screen;
import ch.zhaw.itmania.input.KeyManager;
import ch.zhaw.itmania.tiles.Tile;

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
    private Graphics2D g;

    public Game() {

        // Load Assets
        Assets.init();

        // Load Tiles
        Tile.init();

        // Load Display
        display = new Display();
        bufferStrategy = display.generateBufferStrategy(BUFFER_LVL);

        // Setup Key Manager
        KeyManager keyManager = new KeyManager();
        display.getWindowFrame().addKeyListener(keyManager);
        screen = new Screen(display, keyManager);
    }

    /**
     * The threads run method contains the game loop to update everything.
     * Furthermore this method limits the FPS to 60.
     */
    @Override
    public void run() {

        long lastLoopTime = System.nanoTime();
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // in nanoseconds
        //final long TEST_TIME = 1000000000 / 15; // Lock to 15 FPS for testing purpose

        // Game loop
        while (running)
        {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double deltaTime = updateLength / ((double)OPTIMAL_TIME);
            //System.out.println(deltaTime);

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
        g = (Graphics2D) bufferStrategy.getDrawGraphics();

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
        // all time-related values must be multiplied by delta
        screen.tick(deltaTime);
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
