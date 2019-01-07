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

import java.util.Objects;


/**
 * Class to represent a chessboard square
 */
public class Square {
    private boolean invalid = false;
    private Piece piece = null;
    private int boardPosX = -1;
    private int boardPosY = -1;

    // dummy square
    public Square() {
    }

    public Square(int x, int y, Piece piece) {
        this.boardPosX = x;
        this.boardPosY = y;
        this.piece = piece;
    }

    public Square(Square square) {
        this.boardPosX = square.getPosX();
        this.boardPosY = square.getPosY();
        this.piece = square.getPiece();
    }


    public void setPosX(int x) {
        this.boardPosX = x;
    }


    public void setPosY(int y) {
        this.boardPosY = y;
    }


    public int getPosX() {
        return this.boardPosX;
    }


    public int getPosY() {
        return this.boardPosY;
    }


    public void print() {
        System.out.println("Square Hashcode:  " + this.hashCode());
        System.out.println("Board Position X: " + this.getPosX());
        System.out.println("Board Position Y: " + this.getPosY());

        if (this.piece != null)
            System.out.println("Piece:            " + this.getPiece());
    }

    /**
     * @param piece
     */
    public void setPiece(Piece piece) {
        if (this.piece != null) {
            this.piece.setSquare(null);
        }
        this.piece = piece;
        if (piece != null) {
            this.piece.setSquare(this);
        }
    }

    /**
     * checks whether two squares are equal by comparing the x,y corrdinate and checking if there is the same piece on it
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Square)) return false;

        Square square = (Square) o;

        return square.getPosX() == getPosX() && square.getPosY() == getPosY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(100 * this.boardPosX + this.boardPosY);
    }

    /**
     * Retrieve the piece on the square
     *
     * @return
     */
    public Piece getPiece() {
        return piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean value) {
        this.invalid = value;
    }
}
