package com.ovgu.ccd.gui.chessboardListener;

import java.awt.*;

public class TextLabel extends GeometricPrimitiveDrawer {
    private String text = "";
    private Point position = null;


    public TextLabel(String text, Point position) {
        this.text = text;
        this.position = position;
    }

    @Override
    public void draw(Graphics graphics) {
        if (graphics instanceof Graphics2D) {
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawString(
                    this.text,
                    this.position.getX(),
                    this.position.getY());
        }
    }
}
