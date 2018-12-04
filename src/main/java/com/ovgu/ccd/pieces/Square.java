/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Authors:
 * Mateusz Sławomir Lach ( matlak, msl )
 * Damian Marciniak
 */
package com.ovgu.ccd.pieces;

import com.ovgu.ccd.gui.chessboardListener.Line;
import com.ovgu.ccd.gui.chessboardListener.Point;
import com.ovgu.ccd.gui.chessboardListener.Triangle;

import java.util.HashMap;
import java.util.Objects;


/**
 * Class to represent a chessboard square
 */
public class Square
{
    private boolean invalid = false;
    private Piece piece = null;
    private HashMap<String, Point> vertices = new HashMap<String, Point>();
    private int boardPosX = -1;
    private int boardPosY = -1;

    // dummy square
    public Square() { }

    // @TODO remove later
    public Square(int x, int y, Piece piece)
    {
        this.boardPosX = x;
        this.boardPosY = y;
        this.piece = piece;
    }

    public Square(Square square)
    {
        this.boardPosX = square.getPosX();
        this.boardPosY = square.getPosY();
        this.piece = square.getPiece();
    }

    public Square(Point a, Point b, Point c, Point d)
    {
        this.vertices.put("A", a);
        this.vertices.put("B", b);
        this.vertices.put("C", c);
        this.vertices.put("D", d);
    }


    public Square(int boardPosX, int boardPosY, Point a, Point b, Point c, Point d)
    {
        this.boardPosX = boardPosX;
        this.boardPosY = boardPosY;
        this.vertices.put("A", a);
        this.vertices.put("B", b);
        this.vertices.put("C", c);
        this.vertices.put("D", d);
    }


    public void setPosX(int x)
    {
        this.boardPosX = x;
    }


    public void setPosY(int y)
    {
        this.boardPosY = y;
    }


    public int getPosX()
    {
        return this.boardPosX;
    }


    public int getPosY()
    {
        return this.boardPosY;
    }

    public Point getVertex(String name)
    {
        if (this.vertices.containsKey(name))
            return this.vertices.get(name);
        return null;
    }


    public double getArea()
    {
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


    public boolean isPointInside(Point point)
    {
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

    // computes the center of the chess panel
    // @return: center point
    public Point center()
    {
        Line diagonalLineAC = new Line(this.vertices.get("A"), this.vertices.get("C"));
        Line diagonalLineBD = new Line(this.vertices.get("D"), this.vertices.get("B"));
        return diagonalLineAC.computeIntersectionPoint(diagonalLineBD);
    }


    public void print()
    {
        System.out.println("Square:           " + this.hashCode());
        System.out.println("Board Position X: " + this.getPosX());
        System.out.println("Board Position Y: " + this.getPosY());
    }

    /**
     * @param piece
     */
    public void setPiece(Piece piece)
    {
        this.piece = piece;
        if (piece != null)
            this.piece.setSquare(this);
    }

    /**
     * checks whether two squares are equal by comparing the x,y corrdinate and checking if there is the same piece on it
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof Square)) return false;

        Square square = (Square) o;
        if (square.getVertex("A") == this.vertices.get("A") &&
                square.getVertex("B") == this.vertices.get("B") &&
                square.getVertex("C") == this.vertices.get("C") &&
                square.getVertex("D") == this.vertices.get("D"))
        {
            if (square.getPosX() == this.boardPosX &&
                square.getPosY() == this.boardPosY)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.vertices);
    }

    /**
     * Retrieve the piece on the square
     *
     * @return
     */
    public Piece getPiece() {
        return piece;
    }

    public boolean isEmpty() { return piece == null; }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean value) {
        this.invalid = value;
    }
}
