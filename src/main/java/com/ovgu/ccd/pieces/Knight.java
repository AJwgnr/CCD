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

import com.ovgu.ccd.applogic.ResourceLoader;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.GUI;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.moves.KnightMoveFactory;

import java.util.ArrayList;

/**
 * Class to represent a chess pawn knight
 */
public class Knight extends Piece {

    public static short value = 3;


    protected Knight(IBoard chessboard, Player player) {
        super(chessboard, player);
        this.symbol = "N";
        imageWhite = ResourceLoader.loadImage("Knight-W.png");
        imageBlack = ResourceLoader.loadImage("Knight-B.png");
        imageGray = ResourceLoader.loadImage("Knight-G.png");
        this.setImage();
    }

    /**
     * Annotation to superclass Piece changing pawns location
     *
     * @return ArrayList with new possition of pawn
     */
    @Override
    public ArrayList allMoves() {
        ArrayList moves = new ArrayList();
        try {
            moves.addAll(KnightMoveFactory.getMoves(chessboard, this).moves());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moves;
    }
}
