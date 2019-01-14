package com.ovgu.ccd.gui.chessboardListener;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 * @since
 */
public abstract class GeometricPrimitiveDrawer extends JComponent
{
    private static final long serialVersionUID = -5177239702410583033L;

    /**
     * redraw panel elements
     *
     */
    public void redraw() {
        repaint();
    }


    /**
     * draws the elements
     *
     * @param graphics graphics
     */
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        draw(graphics);
    }


    /**
     * will be called in paintComponent() and can be used to define certain things to draw
     *
     * @param graphics graphics
     */
    public abstract void draw(Graphics graphics);
}
