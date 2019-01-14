package com.ovgu.ccd.gui.chessboardListener;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;

/**
 *
 */
public abstract class GeometricPrimitiveDrawer extends JComponent {
    private static final long serialVersionUID = -5177239702410583033L;

    // redraw panel elements
    public void redraw() {
        repaint();
    }


    /**
     *
     * @param graphics
     */
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        draw(graphics);
    }


    /**
     *
     * @param graphics
     */
    public abstract void draw(Graphics graphics);
}
