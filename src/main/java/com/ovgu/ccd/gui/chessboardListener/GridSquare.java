package com.ovgu.ccd.gui.chessboardListener;

import com.ovgu.ccd.pieces.Square;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.HashMap;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 */
public class GridSquare extends GeometricPrimitiveDrawer {
    private HashMap<String, Point> vertices = new HashMap<String, Point>();
    private Square square = null;
    private boolean highlight = false;
    private Color fillColor = null;
    private Color highlightColor = null;


    /**
     * dummy constructor
     *
     */
    public GridSquare(){

    }

    /**
     * constructor
     *
     * @param a vertex 1
     * @param b vertex 2
     * @param c vertex 3
     * @param d vertex 4
     */
    public GridSquare(final Point a, final Point b, final Point c, final Point d) {
        this.vertices.put("A", a);
        this.vertices.put("B", b);
        this.vertices.put("C", c);
        this.vertices.put("D", d);
    }


    /**
     * constructor
     *
     * @param boardPosX chessboard x position
     * @param boardPosY chessboard y position
     * @param a         vertex 1
     * @param b         vertex 2
     * @param c         vertex 3
     * @param d         vertex 4
     */
    public GridSquare(final int boardPosX, final int boardPosY, final Point a, final Point b, final Point c, final Point d) {
        this.square = new Square(boardPosX, boardPosY, null);
        this.vertices.put("A", a);
        this.vertices.put("B", b);
        this.vertices.put("C", c);
        this.vertices.put("D", d);
    }


    /**
     * get a certain vertex as a point object by name.
     *
     * @param name name of the vertex (A,B,C,D)
     * @return Point (vertex)
     */
    public Point getVertex(final String name) {
        if (this.vertices.containsKey(name))
            return this.vertices.get(name);
        return null;
    }


    /**
     * does the grid square has a board square assigned to it.
     *
     * @return boolean
     */
    public boolean hasBoardSquare() {
        return this.square != null;
    }


    /**
     * get the chessboard square.
     *
     * @return Square of the chessboard
     */
    public Square getBoardSquare() {
        return this.square;
    }


    /**
     * sets the chessboard square.
     *
     * @param square chessboard square
     */
    public void setBoardSquare(final Square square) {
        this.square = square;
    }


    /**
     * sets highlight property, mark squares, which should be highlighted.
     *
     * @param en highlight on/off
     */
    public void setHighlight(final boolean en) {
        this.highlight = en;
    }


    /**
     * get highlight property
     *
     * @return boolean highlight on/off
     */
    public boolean getHighlight() {
        return this.highlight;
    }


    /**
     * get the area of the square.
     * <p>
     * computed by splitting the area of the square into two triangles
     *
     * @return the area of the square
     */
    public double getArea() {
        Triangle triangle1 = new Triangle(
                this.vertices.get("A"),
                this.vertices.get("B"),
                this.vertices.get("C"));
        Triangle triangle2 = new Triangle(
                this.vertices.get("C"),
                this.vertices.get("D"),
                this.vertices.get("A"));
        return triangle1.area() + triangle2.area();
    }


    /**
     * checks whether a given point lays inside the square or not
     * <p>
     * constructs four triangles (point + one side of the square) and computes the are
     * if its the same as the area of the square itself, then the point is inside the square.
     *
     * @param point the point to check for
     * @return boolean if the point is inside or not
     */
    public boolean isPointInside(final Point point) {
        Triangle triangleAPD = new Triangle(
                this.vertices.get("A"),
                point,
                this.vertices.get("D"));
        Triangle triangleDPC = new Triangle(
                this.vertices.get("D"),
                point,
                this.vertices.get("C"));
        Triangle triangleCPB = new Triangle(
                this.vertices.get("C"),
                point,
                this.vertices.get("B"));
        Triangle trianglePBA = new Triangle(
                point,
                this.vertices.get("B"),
                this.vertices.get("A"));

        double areaOfTriangles =
                triangleAPD.area() + triangleDPC.area() +
                        triangleCPB.area() + trianglePBA.area();

        // if size(APD) + size(DPC) + size(CPB) + size(PBA) > size(ABCD)
        // -> Point P is outside
        if (areaOfTriangles > this.getArea())
            return false;

            // if size(APD) + size(DPC) + size(CPB) + size(PBA) = size(ABCD)
            // -> Point P lies on one of the panel lines
            // else
            // -> Point P lies inside the panel
        else
            return true;
    }


    /**
     * get the center point of the square.
     *
     * @return Point center
     */
    public Point center() {
        Line diagonalLineAC = new Line(this.vertices.get("A"), this.vertices.get("C"));
        Line diagonalLineBD = new Line(this.vertices.get("D"), this.vertices.get("B"));
        return diagonalLineAC.computeIntersectionPoint(diagonalLineBD);
    }


    /**
     * transforms the square to an polygon.
     *
     * @return Polygon
     */
    public Polygon toPolygon() {
        int xPoly[] =
                {
                        this.vertices.get("A").getX(),
                        this.vertices.get("B").getX(),
                        this.vertices.get("C").getX(),
                        this.vertices.get("D").getX()
                };
        int yPoly[] =
                {
                        this.vertices.get("A").getY(),
                        this.vertices.get("B").getY(),
                        this.vertices.get("C").getY(),
                        this.vertices.get("D").getY()
                };
        return new Polygon(xPoly, yPoly, xPoly.length);
    }


    /**
     * sets fill color.
     *
     * @param color color
     */
    public void setFillColor(final Color color) {
        this.fillColor = color;
    }


    /**
     * returns the fill color value.
     *
     * @return: fill color
     */
    public Color getFillColor() {
        return this.fillColor;
    }


    /**
     * sets highlight color.
     *
     * @param color to highlight the square
     */
    public void setHighlightColor(final Color color) {
        this.highlightColor = color;
    }


    /**
     * returns the highlight color value
     *
     * @return fill color
     */
    public Color getHighlightColor() {
        return this.highlightColor;
    }


    /**
     * draws the square.
     *
     * @param graphics graphics
     */
    @Override
    public void draw(final Graphics graphics) {
        Polygon polygon = toPolygon();

        if (this.fillColor != null) {
            graphics.setColor(this.fillColor);
            graphics.fillPolygon(polygon);
        }

        if (this.highlight) {
            graphics.setColor(new Color(50, 250, 100, 100));
            graphics.fillPolygon(polygon);
        }

        if (this.square != null && this.square.getPiece() != null)
            this.square.getPiece().draw(graphics);
    }
}
