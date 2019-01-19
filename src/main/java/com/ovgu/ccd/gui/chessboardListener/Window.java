package com.ovgu.ccd.gui.chessboardListener;


import javax.swing.*;
import java.awt.*;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 */
public class Window {
    /**
     * Dimension provides the screensize.
     */
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Sets the maximum window width from the screensize.
     */
    private final int MAX_WINDOW_WIDTH = this.getDefaultWindowWidth();
    /**
     * Sets the maximum window height from the screensize.
     */
    private final int MAX_WINDOW_HEIGHT = this.getDefaultWindowHeight();
    /**
     * Sets the minimum window width from the screensize.
     */
    private final int MIN_WINDOW_WIDTH = MAX_WINDOW_WIDTH / 2;
    /**
     * Sets the minimum window height from the screensize.
     */
    private final int MIN_WINDOW_HEIGHT = MAX_WINDOW_HEIGHT / 2;
    /**
     * Variable for the frame.
     */
    private JFrame frame = null;
    /**
     * Sets the window name.
     */
    private String windowName = "Default Name";


    /**
     * The parameterized constructor.
     *
     * @param name  name of window
     * @param panel add panel to window
     */
    public Window(final String name, final JPanel panel) {
        setWindowName(name);
        createWindow();
        addPanel(panel);
    }


    /**
     * creates JFrame object with given parameter.
     */
    private void createWindow() {
        frame = new JFrame(this.windowName);
        frame.setPreferredSize(new Dimension(
                this.MAX_WINDOW_WIDTH, this.MAX_WINDOW_HEIGHT));
        frame.setMinimumSize(new Dimension(
                MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));
        frame.setMaximumSize(new Dimension(
                MAX_WINDOW_WIDTH, MAX_WINDOW_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * adds a panel to the window.
     *
     * @param panel panel to add.
     */
    private void addPanel(final JPanel panel) {
        if (panel != null) {
            frame.add(panel);
        }
    }


    /**
     * sets the window name.
     *
     * @param name name of the window
     */
    private void setWindowName(final String name) {
        this.windowName = name;
    }

    /**
     * returns the default width of the window.
     *
     * @return width of the window
     */
    private int getDefaultWindowWidth() {
        return this.screenSize.width;
    }

    /**
     * returns the default height of the window.
     *
     * @return height of the window
     */
    private int getDefaultWindowHeight() {
        return this.screenSize.height;
    }
}
