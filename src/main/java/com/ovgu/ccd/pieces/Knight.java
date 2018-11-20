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

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.GUI;
import com.ovgu.ccd.gui.Player;

import java.util.ArrayList;

/**
 * Class to represent a chess pawn knight
 */
public class Knight extends Piece {

    public static short value = 3;


    public Knight(Chessboard chessboard, Player player) {
        super(chessboard, player);
        this.symbol = "N";
        imageWhite = GUI.loadImage("Knight-W.png");
        imageBlack = GUI.loadImage("Knight-B.png");
        this.setImage();
    }

    /**
     * Annotation to superclass Piece changing pawns location
     *
     * @return ArrayList with new possition of pawn
     */
    @Override
    public ArrayList allMoves()
    {
        ArrayList moves = new ArrayList();

        // knight all moves
        //  _______________ Y:
        // |_|_|_|_|_|_|_|_|7
        // |_|_|_|_|_|_|_|_|6
        // |_|_|2|_|3|_|_|_|5
        // |_|1|_|_|_|4|_|_|4
        // |_|_|_|K|_|_|_|_|3
        // |_|8|_|_|_|5|_|_|2
        // |_|_|7|_|6|_|_|_|1
        // |_|_|_|_|_|_|_|_|0
        //X:0 1 2 3 4 5 6 7
        //

        int newX;
        int newY;

        if (validMove(newX = square.pozX - 2, newY = square.pozY + 1))
            moves.add(chessboard.getSquare(newX, newY));

        if (validMove(newX = square.pozX - 1, newY = square.pozY + 2))
            moves.add(chessboard.getSquare(newX, newY));

        if (validMove(newX = square.pozX + 1, newY = square.pozY + 2))
            moves.add(chessboard.getSquare(newX, newY));

        if (validMove(newX = square.pozX + 2, newY = square.pozY + 1))
            moves.add(chessboard.getSquare(newX, newY));

        if (validMove(newX = square.pozX + 2, newY = square.pozY - 1))
            moves.add(chessboard.getSquare(newX, newY));

        if (validMove(newX = square.pozX + 1, newY = square.pozY - 2))
            moves.add(chessboard.getSquare(newX, newY));

        if (validMove(newX = square.pozX - 1, newY = square.pozY - 2))
            moves.add(chessboard.getSquare(newX, newY));

        if (validMove(newX = square.pozX - 2, newY = square.pozY - 1))
            moves.add(chessboard.getSquare(newX, newY));

        return moves;
    }

    private boolean validMove(int newX, int newY) {
        if (outsideOfBoard(newX, newY)) { return false; }

        Square nextPosition = chessboard.getSquare(newX, newY);
        return (canMoveTo(nextPosition) && (chessboard.myKing(getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(square, nextPosition)));
    }
}