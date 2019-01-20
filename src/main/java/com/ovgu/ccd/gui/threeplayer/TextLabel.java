package com.ovgu.ccd.gui.threeplayer;

import java.awt.*;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 */
public class TextLabel extends GeometricPrimitiveDrawer {
    private String text = "";
    private Point position = null;


    /**
     * constructor
     *
     * @param text      string of the label
     * @param position  position of the label on screen
     *
     */
    public TextLabel(String text, Point position) {
        this.text = text;
        this.position = position;
    }


    /**
     * draws the text label
     *
     * @param   graphics    graphics
     *
     */
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
