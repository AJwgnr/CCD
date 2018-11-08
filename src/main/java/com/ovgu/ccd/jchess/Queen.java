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

import java.util.ArrayList;

/**
 * Class to represent a queen piece
 * Queen can move almost in every way:
 * |_|_|_|X|_|_|_|X|7
    |X|_|_|X|_|_|X|_|6
    |_|X|_|X|_|X|_|_|5
    |_|_|X|X|x|_|_|_|4
    |X|X|X|Q|X|X|X|X|3
    |_|_|X|X|X|_|_|_|2
    |_|X|_|X|_|X|_|_|1
    |X|_|_|X|_|_|X|_|0
    0 1 2 3 4 5 6 7
 */

public class Queen extends Piece
{

    public static short value = 9;

    public Queen(Chessboard chessboard, Player player)
    {
        super(chessboard, player);
        this.symbol = "Q";
        imageWhite = GUI.loadImage("Queen-W.png");
        imageBlack = GUI.loadImage("Queen-B.png");
        this.setImage();
    }

    /**
     * Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves()
    {
        ArrayList moves = new ArrayList();

        moves.addAll(verticalMoves());
        moves.addAll(horizontalMoves());
        moves.addAll(diagonalMoves());

        return moves;
    }

    private ArrayList horizontalMoves()
    {
        ArrayList moves = new ArrayList();
        //left
        for (int i = square.pozX - 1; i >= 0; --i)
        {
            if (!checkPiece(i, square.pozY)) { break; }
            Square nextPosition = chessboard.squares[i][square.pozY];
            if (chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, nextPosition))
            {
                moves.add(nextPosition);
            }
            if (otherOwner(i, square.pozY)) { break; }
        }

        //right
        for (int i = square.pozX + 1; i <= 7; ++i)
        {
            if (!checkPiece(i, square.pozY)) { break; }
            Square nextPosition = chessboard.squares[i][square.pozY];
            if (chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, nextPosition))
            {
                moves.add(nextPosition);
            }
            if (otherOwner(i, square.pozY)) { break; }
        }
        return moves;
    }

    private ArrayList verticalMoves()
    {
        ArrayList moves = new ArrayList();

        //up
        for (int i = square.pozY + 1; i <= 7; ++i)
        {
            if (!checkPiece(square.pozX, i)) { break; }
            Square nextPosition = chessboard.squares[square.pozX][i];
            if (chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, nextPosition))
            {
                moves.add(nextPosition);
            }
            if (otherOwner(square.pozX, i)) { break; }
        }

        //down
        for (int i = square.pozY - 1; i >= 0; --i)
        {
            if (!checkPiece(square.pozX, i)) { break; }
            Square nextPosition = chessboard.squares[square.pozX][i];
            if (chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, nextPosition))
            {
                moves.add(nextPosition);
            }
            if (otherOwner(square.pozX, i)) { break; }
        }

        return moves;
    }

    private ArrayList diagonalMoves()
    {
        ArrayList moves = new ArrayList();

        //left
        for (int x = square.pozX - 1, y = square.pozY + 1; !isout(x, y); --x, ++y)
        {
            if (!checkPiece(x, y)) { break; }
            Square nextPosition = chessboard.squares[x][y];
            if (chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, nextPosition))
            {
                moves.add(nextPosition);
            }
            if (otherOwner(x, y)) { break; }
        }

        for (int x = square.pozX - 1, y = square.pozY - 1; !isout(x, y); --x, --y)
        {
            if (!checkPiece(x, y)) { break; }
            Square nextPosition = chessboard.squares[x][y];
            if (chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, nextPosition))
            {
                moves.add(nextPosition);
            }
            if (otherOwner(x, y)) { break; }
        }

        //right
        for (int x = square.pozX + 1, y = square.pozY + 1; !isout(x, y); ++x, ++y)
        {
            if (!checkPiece(x, y)) { break; }
            Square nextPosition = chessboard.squares[x][y];
            if (chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, nextPosition))
            {
                moves.add(nextPosition);
            }
            if (otherOwner(x, y)) { break; }
        }

        for (int x = square.pozX + 1, y = square.pozY - 1; !isout(x, y); ++x, --y)
        {
            if (!checkPiece(x, y)) { break; }
            Square nextPosition = chessboard.squares[x][y];
            if (chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, nextPosition))
            {
                moves.add(nextPosition);
            }
            if (otherOwner(x, y)) { break; }
        }


        return moves;
    }
}
