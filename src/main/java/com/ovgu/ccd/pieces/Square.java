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
    int pozX;
    int pozY;

    private boolean invalid = false;
    public Piece piece;

    public Square(int pozX, int pozY, Piece piece)
    {
        this.pozX = pozX;
        this.pozY = pozY;
        this.piece = piece;
    }


    public Square(Square square) {
        this.pozX = square.pozX;
        this.pozY = square.pozY;
        this.piece = square.piece;
    }

    public Square clone(Square square) {
        return new Square(square);
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
        if (piece != null) {
            this.piece.setSquare(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Square)) return false;

        Square square = (Square) o;
        return square.getPozX() == pozX && square.getPozY() == pozY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pozX, pozY);
    }

    // modification
    public int getPozX() {
        return pozX;
    }

    public int getPozY() {
        return pozY;
    }

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
