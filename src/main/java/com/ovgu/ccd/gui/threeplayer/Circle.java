package com.ovgu.ccd.gui.threeplayer;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 */
public class Circle  extends GeometricPrimitiveDrawer implements GeometricShape {
    private static final long serialVersionUID = -4701817387438983685L;
    private int radius;
    private Point center;


    /**
     * computes the area of the circle
     *
     * @return the area of the circle
     */
    @Override
    public double area() {
        return 0;
    }


    /**
     * constructor
     *
     * @param   center  center point of the circle
     * @param   radius  radius of the circle
     */
    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }


    /**
     * computes the intersection points between the circle and a given line
     *
     * @param   line    line
     * @return  list of intersection points (0,1,2)
     */
    public List<Point> findIntersectionPoint(final Line line) {
        double baX = line.getEndPoint().getX() - line.getStartPoint().getX();
        double baY = line.getEndPoint().getY() - line.getStartPoint().getY();

        double caX = this.center.getX() - line.getStartPoint().getX();
        double caY = this.center.getY() - line.getStartPoint().getY();

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - this.radius * this.radius;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;

        if (disc < 0)
            return Collections.emptyList();

        // if disc == 0 ... dealt with later
        double tmpSqrt = Math.sqrt(disc);
        double abScalingFactor1 = -pBy2 + tmpSqrt;
        double abScalingFactor2 = -pBy2 - tmpSqrt;

        Point p1 = new Point(
                (int) (line.getStartPoint().getX() - baX * abScalingFactor1),
                (int) (line.getStartPoint().getY() - baY * abScalingFactor1));

        // abScalingFactor1 == abScalingFactor2
        if (disc == 0)
            return Collections.singletonList(p1);

        Point p2 = new Point(
                (int) (line.getStartPoint().getX() - baX * abScalingFactor2),
                (int) (line.getStartPoint().getY() - baY * abScalingFactor2));

        return Arrays.asList(p1, p2);
    }


    /**
     * computes the intersection points between two circles
     *
     * @param circle other circle
     * @return list of intersection points
     */
    public List<Point> findIntersectionPoint(final Circle circle) {
        Point circleCenter1 = this.center;
        Point circleCenter2 = circle.center;

        double distBetwCenters = circleCenter1.getDistanceTo(circleCenter2);

        double distToIntersection = (Math.pow(this.radius, 2) - Math.pow(circle.radius, 2)
                + Math.pow(distBetwCenters, 2)) / (2 * distBetwCenters);

        double heightOfIntersection = Math.sqrt(Math.pow(this.radius, 2) - Math.pow(distToIntersection, 2));

        double x = circleCenter1.getX() + distToIntersection
                * (circleCenter2.getX() - circleCenter1.getX()) / distBetwCenters;
        double y = circleCenter1.getY() + distToIntersection
                * (circleCenter2.getY() - circleCenter1.getY()) / distBetwCenters;
        Point pointOnIntersectionLine = new Point(x, y);

        Point intersectionPoint1 = new Point(
                pointOnIntersectionLine.getX() + heightOfIntersection
                        * (circleCenter2.getY() - circleCenter1.getY()) / distBetwCenters,
                pointOnIntersectionLine.getY() - heightOfIntersection
                        * (circleCenter2.getX() - circleCenter1.getX()) / distBetwCenters);

        Point intersectionPoint2 = new Point(
                pointOnIntersectionLine.getX() - heightOfIntersection
                        * (circleCenter2.getY() - circleCenter1.getY()) / distBetwCenters,
                pointOnIntersectionLine.getY() + heightOfIntersection
                        * (circleCenter2.getX() - circleCenter1.getX()) / distBetwCenters);

        return Arrays.asList(intersectionPoint1, intersectionPoint2);
    }


    /**
     * drawing method for the circle
     *
     * @param graphics graphics
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.drawOval(this.center.getX() - this.radius,
                this.center.getY() - this.radius,
                this.radius * 2, this.radius * 2);
    }
}
