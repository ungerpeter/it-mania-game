package ch.zhaw.ungerpet.itmania.game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ungerpet on 12.11.2015.
 */
public class Game extends Canvas implements Runnable {

    public boolean running = false;
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String NAME = "IT-MANIA";
    private JFrame frame;

    public Game() {
        this.setSize(WIDTH, HEIGHT);
        frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        while (running) {
            System.out.println("Game is running! [incredible fast]");
        }
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
