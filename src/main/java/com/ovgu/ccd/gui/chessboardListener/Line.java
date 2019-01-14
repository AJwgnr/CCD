package com.ovgu.ccd.gui.chessboardListener;

import java.awt.Graphics;
import java.util.ArrayList;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 * @since
 */
public class Line extends GeometricShape
{
    private static final long serialVersionUID = -516881209755986697L;
    private Point start = new Point(0, 0);
    private Point end = new Point(0, 0);


    /**
     * computes area of specific geometric shape
     *
     * @return  returns area of specific geometric shape
     */
    @Override
    public double area() {
        return 0;
    }


    /**
     * computes area of specific geometric shape
     *
     * @return  returns area of specific geometric shape
     */
    public Line(final Point start, final Point end) {
        //setLayout(null);
        setStartPoint(start);
        setEndPoint(end);
    }




    /** set coordinates of the start point.
     *
     * @param start
     */
    public void setStartPoint(final Point start) {
        this.start = start;
    }



    /**set coordinates of the end point.
     * @param end
     */
    public void setEndPoint(final Point end) {
        this.end = end;
    }


    // @return: the start point coordinates
    public Point getStartPoint() {
        return this.start;
    }


    // @return: the end point coordinates
    public Point getEndPoint() {
        return this.end;
    }


    public Point computeIntersectionPoint(final Line otherLine) {
        int a1 = this.end.getY() - this.start.getY();
        int b1 = this.start.getX() - this.end.getX();
        int c1 = a1 * this.start.getX() + b1 * this.start.getY();

        int a2 = otherLine.end.getY() - otherLine.start.getY();
        int b2 = otherLine.start.getX() - otherLine.end.getX();
        int c2 = a2 * otherLine.start.getX() + b2 * otherLine.start.getY();

        int delta = a1 * b2 - a2 * b1;

        return new Point((b2 * c1 - b1 * c2) / delta, (a1 * c2 - a2 * c1) / delta);
    }


    @Override
    public void draw(final Graphics graphics) {
        graphics.drawLine(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY());
    }
}
