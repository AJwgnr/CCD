package com.ovgu.ccd.gui.chessboardListener;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * @author CCD DeepBlue team
 * @version 1.0
 * @since
 */
public class Line extends GeometricPrimitiveDrawer implements GeometricShape
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
     * constructor
     *
     * @param   start   start point for line
     * @param   end     end point for line
     */
    public Line(final Point start, final Point end) {
        setStartPoint(start);
        setEndPoint(end);
    }


    /** set coordinates of the start point.
     *
     * @param start start point
     */
    public void setStartPoint(final Point start) {
        this.start = start;
    }



    /**set coordinates of the end point.
     *
     * @param end end point
     */
    public void setEndPoint(final Point end) {
        this.end = end;
    }


    /**
     * get start point of the line
     *
     * @return  returns start point
     */
    public Point getStartPoint() {
        return this.start;
    }


    /**
     * get end point of the line
     *
     * @return  returns end point
     */
    public Point getEndPoint() {
        return this.end;
    }


    /**
     * computes the length of the line
     *
     * @return  length of the line
     */
    public double getLength() {
        return Math.sqrt(
                Math.pow(this.end.getX() - this.start.getX(), 2) +
                        Math.pow(this.end.getY() - this.start.getY(), 2));
    }


    /**
     * computes and returns the intersection point of two lines
     *
     * @param   otherLine   the other line to compute the intersection with
     * @return  intersection point
     */
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


    /**
     * returns a list of points which are equally distributed along the line
     *
     * @param    numOfPoints sum of points on line without start and end point
     * @return   list of ALL point on line
     */
    public ArrayList<Point> getEquallyDistributedPoints(final int numOfPoints) {
        ArrayList<Point> points = new ArrayList<Point>();
        float distance = (float) this.getLength() / (numOfPoints - 1);
        for (int i = 0; i < numOfPoints; i++)
            points.add(getPointAfterDistance(distance * i));
        return points;
    }


    /**
     * computes via linear interpolation the point after a certain distance (according to the start point of the line)
     *
     * @param    distance   distance from the start point to the target point on the line
     * @return   point
     */
    private Point getPointAfterDistance(final float distance) {
        if (distance == 0.0f)
            return this.getStartPoint();
        else if (distance == getLength())
            return this.getEndPoint();
        else {
            float t = distance / (float) getLength();
            float x = (1 - t) * this.getStartPoint().getX() + t * this.getEndPoint().getX();
            float y = (1 - t) * this.getStartPoint().getY() + t * this.getEndPoint().getY();
            return new Point(x, y);
        }
    }


    /**
     * draw method of the line to let it been drawn
     *
     * @param   graphics    Graphic object
     */
    @Override
    public void draw(final Graphics graphics) {
        graphics.drawLine(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY());
    }
}
