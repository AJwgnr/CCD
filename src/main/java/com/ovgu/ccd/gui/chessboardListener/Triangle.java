package com.ovgu.ccd.gui.chessboardListener;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 */
public class Triangle extends GeometricPrimitiveDrawer implements GeometricShape {
    private static final long serialVersionUID = -7342706080221603266L;
    private HashMap<String, Point> vertices = new HashMap<String, Point>();
    private HashMap<String, Line> lines = new HashMap<String, Line>();


    /**
     * constructor
     *
     * @param a vertex a
     * @param b vertex b
     * @param c vertex c
     */
    public Triangle(Point a, Point b, Point c) {
        this.vertices.put("A", a);
        this.vertices.put("B", b);
        this.vertices.put("C", c);
        this.lines.put("AB", new Line(a, b));
        this.lines.put("BC", new Line(b, c));
        this.lines.put("CA", new Line(c, a));
    }


    /**
     * draws the lines of the triangle
     *
     * @param graphics graphics
     */
    @Override
    public void draw(Graphics graphics) {
        for (Map.Entry<String, Line> line : lines.entrySet())
            line.getValue().paint(graphics);
    }


    /**
     * computes the area of the triangle
     *
     * @return area of the triangle
     */
    @Override
    public double area() {
        int x1 = this.vertices.get("A").getX();
        int x2 = this.vertices.get("B").getX();
        int x3 = this.vertices.get("C").getX();
        int y1 = this.vertices.get("A").getY();
        int y2 = this.vertices.get("B").getY();
        int y3 = this.vertices.get("C").getY();
        return Math.abs((float) (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2);
    }
}
