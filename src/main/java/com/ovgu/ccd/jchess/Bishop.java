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
 * Class to represent a chess pawn bishop
 * Bishop can move across the chessboard
 *
|_|_|_|_|_|_|_|X|7
|X|_|_|_|_|_|X|_|6
|_|X|_|_| |X|_|_|5
|_|_|X|_|X|_|_|_|4
|_|_|_|B|_|_|_|_|3
|_| |X|_|X|_|_|_|2
|_|X|_|_|_|X|_|_|1
|X|_|_|_|_|_|X|_|0
0 1 2 3 4 5 6 7
 */

public class Bishop extends Piece
{

    public static short value = 3;

    public Bishop(Chessboard chessboard, Player player)
    {
        super(chessboard, player);
        this.symbol = "B";
        imageWhite = GUI.loadImage("Bishop-W.png");
        imageBlack = GUI.loadImage("Bishop-B.png");
        this.setImage();
    }


    /**
     * Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new possition of piece
     */
    @Override
    public ArrayList allMoves()
    {
        ArrayList list = new ArrayList();

        // Top left
        for (int h = this.square.pozX - 1, i = this.square.pozY + 1; !isout(h, i); --h, ++i)
        {
            if (addMove(h, i)) {
                list.add(chessboard.squares[h][i]);
            } else {
                break;
            }
        }

        // Bottom left
        for (int h = this.square.pozX - 1, i = this.square.pozY - 1; !isout(h, i); --h, --i)
        {
            if (addMove(h, i)) {
                list.add(chessboard.squares[h][i]);
            } else {
                break;
            }
        }

        // Top right
        for (int h = this.square.pozX + 1, i = this.square.pozY + 1; !isout(h, i); ++h, ++i)
        {
            if (addMove(h, i)) {
                list.add(chessboard.squares[h][i]);
            } else {
                break;
            }
        }

        // Bottom right
        for (int h = this.square.pozX + 1, i = this.square.pozY - 1; !isout(h, i); ++h, --i)
        {
            if (addMove(h, i)) {
                list.add(chessboard.squares[h][i]);
            } else {
                break;
            }
        }

        return list;
    }

    private boolean addMove(int posX, int posY)
    {

        if (!this.checkPiece(posX, posY)) return false;

        if (this.player.color == Player.colors.white && this.chessboard.kingWhite.willBeSafeWhenMoveOtherPiece(this.square, chessboard.squares[posX][posY])
                || this.player.color == Player.colors.black && this.chessboard.kingBlack.willBeSafeWhenMoveOtherPiece(this.square, chessboard.squares[posX][posY]))
        {
            return true;
        }

        if (this.otherOwner(posX, posY)) return false;

        return true;
    }

}
