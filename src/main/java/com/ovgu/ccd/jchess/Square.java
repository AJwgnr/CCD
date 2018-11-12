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
package com.ovgu.ccd.jchess;

/**
 * Class to represent a chessboard square
 */
public class Square
{

    public int pozX;
    public int pozY;
    public Piece piece = null;

    public Square(int pozX, int pozY, Piece piece)
    {
        this.pozX = pozX;
        this.pozY = pozY;
        this.piece = piece;
    }


    Square(Square square)
    {
        this.pozX = square.pozX;
        this.pozY = square.pozY;
        this.piece = square.piece;
    }

    public Square clone(Square square)
    {
        return new Square(square);
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
        this.piece.square = this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Square)) return false;

        Square square = (Square) o;
        return square.pozX == pozX && square.pozY == pozY && square.piece == piece;
    }

}
