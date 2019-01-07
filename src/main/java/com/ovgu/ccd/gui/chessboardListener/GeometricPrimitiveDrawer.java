package com.ovgu.ccd.gui.chessboardListener;

import javax.swing.*;
import java.awt.*;


public abstract class GeometricPrimitiveDrawer extends JComponent {
    private static final long serialVersionUID = -5177239702410583033L;

    // redraw panel elements
    public void redraw() {
        repaint();
    }


    // draw (auto execution)
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        draw(graphics);
    }


    // draw primitive
    public abstract void draw(Graphics graphics);
}
