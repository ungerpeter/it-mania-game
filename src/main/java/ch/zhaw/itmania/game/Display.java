package ch.zhaw.itmania.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by ungerpet on 12.12.2015.
 */
public class Display extends Canvas {

    // Display Constants
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String NAME = "IT-MANIA";

    private JFrame frame;

    public Display() {
        initDisplay();
    }

    /**
     * Initialize the Display class.
     * Set JFrame & Canvas properties.
     */
    private void initDisplay() {

        // Set JFrame Properties
        frame = new JFrame(NAME);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Set Canvas Properties
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(false);

        // Add Canvas to Frame and finalize Display
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
    }

    public JFrame getWindowFrame() {
        return frame;
    }

    /**
     * Generates a BufferStrategy for the Canvas depends on the BufferLVL.
     * @param bufferLvl
     * @return BufferStategy
     */
    public BufferStrategy generateBufferStrategy(int bufferLvl) {
        createBufferStrategy(bufferLvl);
        return getBufferStrategy();
    }
}
