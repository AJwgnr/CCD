package com.ovgu.ccd.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Panel in which you can see fireworks.
 * <p>
 * This class has been created based on code snippets from:
 * https://harryjoy.me/2012/04/13/fireworks-in-swing/
 */
public class Fireworks extends JPanel implements ActionListener {
    private static final long serialVersionUID = 5053650234847311814L;
    private static final int DELAY = 10;
    private static final int DIVIDER = 180;
    private static final int MULTIPLY_FACTOR = 36;
    private static final int LINE_LENGTH = 2;
    private static final int FIREWORK_RADIUS = 75;
    private static final int ARRAY_LENGTH = 5;

    private static int X_CENTER[] = new int[ARRAY_LENGTH];
    private static int Y_CENTER[] = new int[ARRAY_LENGTH];
    private static Color colors[] = new Color[ARRAY_LENGTH];

    private int x1;
    private int moveX;
    private int index;
    private int color_index;
    private Timer timer;
    private Robot robot = null;

    private List<Integer> xx = new ArrayList<>();
    private List<Integer> yy = new ArrayList<>();


    /**
     * constructor
     */
    public Fireworks() {
        x1 = 0;
        index = 0;
        color_index = 0;
        moveX = 3;

        try {
            this.robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }

        timer = new Timer(DELAY, this);
        timer.start();

        X_CENTER[0] = 100;
        Y_CENTER[0] = 100;

        X_CENTER[1] = 200;
        Y_CENTER[1] = 200;

        X_CENTER[2] = 300;
        Y_CENTER[2] = 300;

        X_CENTER[3] = 300;
        Y_CENTER[3] = 100;

        X_CENTER[4] = 100;
        Y_CENTER[4] = 300;

        colors[0] = Color.ORANGE;
        colors[1] = Color.BLUE;
        colors[2] = Color.CYAN;
        colors[3] = Color.RED;
        colors[4] = Color.PINK;
    }


    /**
     * creates firework explosions with randomly assigned positions and colors
     *
     * @param arg0 action event
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        x1 += moveX;
        if (x1 == 0 || x1 >= FIREWORK_RADIUS) {
            x1 = 0;
            Random random = new Random();
            index = random.nextInt(ARRAY_LENGTH);
            color_index = random.nextInt(ARRAY_LENGTH);
        }
        xx.clear();
        yy.clear();
        for (int i = 0; i < 10; i++) {
            xx.add((int) (X_CENTER[index] + x1 * Math.cos((MULTIPLY_FACTOR * i * Math.PI) / DIVIDER)));
            yy.add((int) (Y_CENTER[index] + x1 * Math.sin((MULTIPLY_FACTOR * i * Math.PI) / DIVIDER)));
        }
        repaint();
        robot.keyPress(62);
    }


    /**
     * this draws the firework
     *
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2d = (Graphics2D) g;
        Stroke stroke = new BasicStroke(
                3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                5, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(colors[color_index]);
        for (int i = 0; i < xx.size(); i++) {
            graphics2d.drawLine(xx.get(i), yy.get(i), xx.get(i) + LINE_LENGTH, yy.get(i) + LINE_LENGTH);
        }
    }
}