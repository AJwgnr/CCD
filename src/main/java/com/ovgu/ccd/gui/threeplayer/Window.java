package com.ovgu.ccd.gui.threeplayer;


import javax.swing.*;
import java.awt.*;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 */
public class Window
{
    private static final int MIN_WINDOW_WIDTH = 512;
    private static final int MIN_WINDOW_HEIGHT = 512;
    private static final int MAX_WINDOW_WIDTH = 1280;
    private static final int MAX_WINDOW_HEIGHT = 1280;

    private JFrame frame = null;
    private String windowName = "Default Name";
    private int windowHeight = 100;
    private int windowWidth = 100;


    /**
     * constructor
     *
     * @param   name    name of window
     * @param   width   width of the window
     * @param   height  height of the window
     * @param   panel   add panel to window
     */
    public Window(String name, int width, int height, JPanel panel) {
        setWindowName(name);
        setWindowWidth(width);
        setWindowHeight(height);
        createWindow();
        addPanel(panel);
    }


    /**
     * creates JFrame object with given parameter
     *
     */
    private void createWindow() {
        frame = new JFrame(this.windowName);
        frame.setPreferredSize(new Dimension(this.windowWidth, this.windowHeight));
        frame.setMinimumSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));
        frame.setMaximumSize(new Dimension(MAX_WINDOW_WIDTH, MAX_WINDOW_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * adds a panel to the window
     *
     * @param   panel   panel to add
     */
    public void addPanel(JPanel panel) {
        if (panel != null)
            frame.add(panel);
    }


    /**
     * sets the window width
     *
     * @param   width   width of the window
     */
    public void setWindowWidth(int width) {
        if (width > 0)
            this.windowWidth = width;
    }


    /**
     * sets the window height
     *
     * @param   height   height of the window
     */
    public void setWindowHeight(int height) {
        if (height > 0)
            this.windowHeight = height;
    }


    /**
     * sets the window name
     *
     * @param   name   name of the window
     */
    public void setWindowName(String name) {
        this.windowName = name;
    }


    /**
     * sets the parameter "exit on close" of the JFrame object
     *
     * @param   en   true - enable the setting
     *               false - disable the setting
     */
    public void setExitOnClose(boolean en) {
        if (en)
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * returns the name of the window
     *
     * @return  name of the window
     */
    public String getWindowName() {
        return this.windowName;
    }


    /**
     * returns the width of the window
     *
     * @return  width of the window
     */
    public int getWindowWidth() {
        return this.windowWidth;
    }


    /**
     * returns the height of the window
     *
     * @return  height of the window
     */
    public int getWindowHeight() {
        return this.windowHeight;
    }


    /**
     * returns the JFrame object
     *
     * @return  JFrame object
     */
    public JFrame getFrame() {
        return frame;
    }
}
