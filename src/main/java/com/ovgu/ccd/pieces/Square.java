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

/**
 * Class to represent a chessboard square
 */
public class Square {
    private int posX;
    private int posY;
    private Piece piece = null;

    /**
     * Initializes a Square on the chessboard with the
     *
     * @param posX
     * @param posY
     * @param piece
     */
    public Square(int posX, int posY, Piece piece) {
        this.posX = posX;
        this.posY = posY;
        this.piece = piece;
    }

    /**
     * @param square
     */
    public Square(Square square) {
        this.posX = square.posX;
        this.posY = square.posY;
        this.piece = square.piece;
    }

    /**
     * @param square
     * @return
     */
    public Square clone(Square square) {
        return new Square(square);
    }

    /**
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            this.piece.square = this;
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
        return square.getPozX() == posX && square.getPozY() == posY && square.getPiece() == piece;
    }

    /**
     * @return X position of the square
     */
    public int getPozX() {
        return posX;
    }

    /**
     * @return Y Position of the square
     */
    public int getPozY() {
        return posY;
    }

    /**
     * Retrieve the piece on the square
     *
     * @return
     */
    public Piece getPiece() {
        return piece;
    }

}
