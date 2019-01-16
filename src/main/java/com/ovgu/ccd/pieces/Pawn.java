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


import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.moves.PawnMoveFactory;

import java.util.ArrayList;

/**
 * Class to represent a pawn piece
 * Pawn can move only forvard and can beat only across
 * In first move pawn can move 2 sqares
 * pawn can be upgreade to rook, knight, bishop, Queen if it's in the
 * squers nearest the side where opponent is lockated
 * Firat move of pawn:
 * |_|_|_|_|_|_|_|_|7
 * |_|_|_|_|_|_|_|_|6
 * |_|_|_|X|_|_|_|_|5
 * |_|_|_|X|_|_|_|_|4
 * |_|_|_|P|_|_|_|_|3
 * |_|_|_|_|_|_|_|_|2
 * |_|_|_|_|_|_|_|_|1
 * |_|_|_|_|_|_|_|_|0
 * 0 1 2 3 4 5 6 7
 * <p>
 * Move of a pawn:
 * |_|_|_|_|_|_|_|_|7
 * |_|_|_|_|_|_|_|_|6
 * |_|_|_|_|_|_|_|_|5
 * |_|_|_|X|_|_|_|_|4
 * |_|_|_|P|_|_|_|_|3
 * |_|_|_|_|_|_|_|_|2
 * |_|_|_|_|_|_|_|_|1
 * |_|_|_|_|_|_|_|_|0
 * 0 1 2 3 4 5 6 7
 * Beats with can take pawn:
 * |_|_|_|_|_|_|_|_|7
 * |_|_|_|_|_|_|_|_|6
 * |_|_|_|_|_|_|_|_|5
 * |_|_|X|_|X|_|_|_|4
 * |_|_|_|P|_|_|_|_|3
 * |_|_|_|_|_|_|_|_|2
 * |_|_|_|_|_|_|_|_|1
 * |_|_|_|_|_|_|_|_|0
 * 0 1 2 3 4 5 6 7
 */

public class Pawn extends Piece {

    public static short value = 1;
    boolean down;

    protected Pawn(IBoard chessboard, Player player) {
        super(chessboard, player);
        this.symbol = "";
        imageWhite = ResourceManager.loadImage("Pawn-W.png");
        imageBlack = ResourceManager.loadImage("Pawn-B.png");
        imageGray = ResourceManager.loadImage("Pawn-G.png");

        this.setImage();
    }

    /**
     * Annotation to superclass Piece changing pawns location
     *
     * @return ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves() {
        ArrayList moves = new ArrayList();
        try {
            moves.addAll(PawnMoveFactory.getMoves(chessboard, this).moves());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moves;
    }


    /**Promotes the pawn into a new piece.
     * @param newPieceType piece the pawn should be promoted to
     */
    public void promote(final PieceTypes newPieceType) {
        ThreePlayerChessboard board = (ThreePlayerChessboard) chessboard;
        if (getColor() == Player.Colors.WHITE) {
            board.whitePawns.remove(this);
        } else if (getColor() == Player.Colors.BLACK) {
            board.blackPawns.remove(this);
        } else {
            board.greyPawns.remove(this);
        }

        Piece newPiece = PieceFactory.getPiece(chessboard, player, newPieceType);
        getSquare().setPiece(newPiece);
    }

    /**
     * @return true if it can be promoted
     */
    public boolean canBePromoted() {
        if (getColor() == Player.Colors.WHITE) {
            return (getPosX() == 7 || getPosX() == 11);
        } else if (getColor() == Player.Colors.BLACK) {
            return (getPosX() == 0 || getPosX() == 11);
        } else {
            return (getPosX() == 0 || getPosX() == 7);
        }
    }
}
