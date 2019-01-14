package com.ovgu.ccd.gui.chessboardListener;

import java.awt.*;

/**
 *
 */
public class Point extends GeometricShape {
    private static final long serialVersionUID = -821761050568399843L;
    private int x = 0;
    private int y = 0;
    private int radiusForDrawing = 6;

    public Point() {

    }

    @Override
    public double area() {
        return 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public float getDistanceTo(Point point) {
        return (float) Math.sqrt(
                Math.pow(point.getX() - this.getX(), 2) +
                        Math.pow(point.getY() - this.getY(), 2));
    }

    public float toVectorGetNorm() {
        return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Point scale(double scaleValue) {
        return new Point(this.x * scaleValue, this.y * scaleValue);
    }

    public Point add(Point point) {
        return new Point(this.x + point.getX(), this.y + point.getY());
    }

    public Point sub(Point point) {
        return new Point(this.x - point.getX(), this.y - point.getY());
    }

    public float dot(Point point) {
        return (this.x * point.getX() + this.y * point.getY());
    }

    public void add(double value) {
        this.x += value;
        this.y += value;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point getMeanPointBetween(Point otherPoint) {
        return new Point((this.x + otherPoint.getX()) / 2, (this.y + otherPoint.getY()) / 2);
    }

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
