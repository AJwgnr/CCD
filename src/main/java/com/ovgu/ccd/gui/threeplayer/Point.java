package com.ovgu.ccd.gui.threeplayer;

import java.awt.*;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 */
public class Point extends GeometricPrimitiveDrawer
{
    private static final long serialVersionUID = -821761050568399843L;
    private int x = 0;
    private int y = 0;
    private int radiusForDrawing = 6;


    /**
     * dummy constructor
     */
    public Point() {

    }


    /**
     * constructor
     *
     * @param   x   x coordinate of the point
     * @param   y   y coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * constructor
     *
     * @param   x   x coordinate of the point
     * @param   y   y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }


    /**
     * returns the x coordinate of the point
     *
     * @return x coordinate
     */
    public int getX() {
        return this.x;
    }


    /**
     * returns the y coordinate of the point
     *
     * @return y coordinate
     */
    public int getY() {
        return this.y;
    }


    /**
     * computes the distance to a certain point
     *
     * @param   point   target point
     * @return distance
     */
    public float getDistanceTo(Point point) {
        return (float) Math.sqrt(
                Math.pow(point.getX() - this.getX(), 2) +
                        Math.pow(point.getY() - this.getY(), 2));
    }


    /**
     * adds two points together, meaning this method will add the x,y coordinates, respectively
     *
     * @param   point   the other point
     * @return the point with the added coordinates
     */
    public Point add(Point point) {
        return new Point(this.x + point.getX(), this.y + point.getY());
    }


    /**
     * adds a certain value to each of the coordinates of the point
     *
     * @param   value   scalar value to add
     */
    public void add(double value) {
        this.x += value;
        this.y += value;
    }


    /**
     * sets the x coordinate of the point to a certain value
     *
     * @param   x   x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }


    /**
     * sets the y coordinate of the point to a certain value
     *
     * @param   y   y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }


    /**
     * computes the mean point between two points
     *
     * @param   otherPoint  the other point
     * @return  the mean point
     */
    public Point getMeanPointBetween(Point otherPoint) {
        return new Point((this.x + otherPoint.getX()) / 2, (this.y + otherPoint.getY()) / 2);
    }


    /**
     * draws a circle around the point to mark it and make it therefor visible
     *
     * @param   graphics    graphics
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.drawOval(
                this.x - (this.radiusForDrawing / 2),
                this.y - (this.radiusForDrawing / 2),
                this.radiusForDrawing,
                this.radiusForDrawing);
    }
}
