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

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.moves.StraightMoveFactory;

import java.util.ArrayList;

/**
 * Class to represent a chess pawn rook
 * Rook can move:
 * |_|_|_|X|_|_|_|_|7
 * |_|_|_|X|_|_|_|_|6
 * |_|_|_|X|_|_|_|_|5
 * |_|_|_|X|_|_|_|_|4
 * |X|X|X|B|X|X|X|X|3
 * |_|_|_|X|_|_|_|_|2
 * |_|_|_|X|_|_|_|_|1
 * |_|_|_|X|_|_|_|_|0
 * 0 1 2 3 4 5 6 7
 */

public class Rook extends Piece {

    public static short value = 5;
    boolean wasMotion = false;

    protected Rook(IBoard chessboard, Player player) {
        super(chessboard, player);
        this.symbol = "R";
        imageWhite = ResourceManager.loadImage("Rook-W.png");
        imageBlack = ResourceManager.loadImage("Rook-B.png");
        imageGray = ResourceManager.loadImage("Rook-G.png");
        this.setImage();
    }


    /**
     *  Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves()
    {
        ArrayList moves = new ArrayList();
        try {
            moves.addAll(StraightMoveFactory.getMoves(chessboard, this).moves());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moves;
    }

    // --changes

    public boolean isWasMotion() {
        return wasMotion;
    }

    public void setWasMotion(boolean wasMotion) {
        this.wasMotion = wasMotion;
    }
}
