package ch.zhaw.itmania.game;

import ch.zhaw.itmania.gfx.Assets;
import ch.zhaw.itmania.gfx.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by ungerpet on 12.11.2015.
 */
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String NAME = "IT-MANIA";
    private static final int BUFFER_LVL = 3;

    private JFrame frame;
    private boolean running = false;
    private long lastFpsTime;
    private int FPS;
    private BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
    private BufferStrategy bufferStrategy;
    private BufferedImage testBufferedImage;
    private int tempCount = 0;

    public Game() {

        // Load Assets
        Assets.init();

        // Setup UI Frame
        this.setSize(WIDTH, HEIGHT);
        frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        createBufferStrategy(BUFFER_LVL);
        bufferStrategy = getBufferStrategy();

        testBufferedImage = Assets.PLAYER1;
    }

    @Override
    public void run() {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // in nanoseconds

        // game loop
        while (running)
        {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double deltaTime = updateLength / ((double)OPTIMAL_TIME);

            lastFpsTime += updateLength;
            FPS++;

            // if last printout is > 1s ago
            if (lastFpsTime >= 1000000000)
            {
                // resets fps count to only get fps per 1s
                System.out.println("(FPS: "+ FPS +")");
                lastFpsTime = 0;
                FPS = 0;
                if(tempCount == 1) {
                    testBufferedImage = Assets.PLAYER1;
                    tempCount = 0;
                } else {
                    testBufferedImage = Assets.PLAYER2;
                    tempCount = 1;
                }

            }

            // update the game logic
            doGameUpdates(deltaTime);

            // draw everyting
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

    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
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

        /*for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 555555 + FPS * 3;
        }*/



    }

    public static void main(String[] args) {
        new Game().start();
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }
}
