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
import com.ovgu.ccd.applogic.Player;

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

    public Pawn(Chessboard chessboard, Player player) {
        super(chessboard, player);
        this.symbol = "";
        imageWhite = GUI.loadImage("Pawn-W.png");
        imageBlack = GUI.loadImage("Pawn-B.png");
        this.setImage();
    }

    /**
     * Annotation to superclass Piece changing pawns location
     *
     * @return ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves() {
        int immediateYCoordinate;
        int twoPositionsYCoordinate;
        ArrayList moves = new ArrayList();

        if (getPlayer().isGoDown()) {
            immediateYCoordinate = square.getPosY() + 1;
            twoPositionsYCoordinate = square.getPosY() + 2;
        } else {
            immediateYCoordinate = square.getPosY() - 1;
            twoPositionsYCoordinate = square.getPosY() - 2;
        }

        if (outsideOfBoard(immediateYCoordinate, immediateYCoordinate)) {
            return moves;
        }

        moves.addAll(regularMove(immediateYCoordinate));
        if (getPlayer().isGoDown() && square.getPosY() == 1 || !getPlayer().isGoDown() && square.getPosY() == 6) {
            moves.addAll(regularMove(twoPositionsYCoordinate));
        }

        // Capture left
        moves.addAll(captureMove(square.getPosX() - 1, immediateYCoordinate));

        // Capture right
        moves.addAll(captureMove(square.getPosX() + 1, immediateYCoordinate));

        // EnPassant left
        moves.addAll(enPassantMove(square.getPosX() - 1, immediateYCoordinate));

        // EnPassant right
        moves.addAll(enPassantMove(square.getPosX() + 1, immediateYCoordinate));

        return moves;
    }


    private ArrayList regularMove(Integer nextYCoordinate) {
        ArrayList list = new ArrayList();
        Square nextPosition = chessboard.getSquare(square.getPosX(), nextYCoordinate);

        if (nextPosition.getPiece() != null) {
            return list;
        }
        if (chessboard.myKing(getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(square, nextPosition)) {
            list.add(nextPosition);
        }
        return list;
    }

    private ArrayList captureMove(Integer nextXCoordinate, Integer nextYCoordinate) {
        ArrayList list = new ArrayList();
        if (outsideOfBoard(nextXCoordinate, nextYCoordinate)) {
            return list;
        }

        Square nextPosition = chessboard.getSquare(nextXCoordinate, nextYCoordinate);
        if (canMoveTo(nextPosition) && otherOwner(nextPosition.getPiece())
                && chessboard.myKing(getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(square, nextPosition)) {
            list.add(nextPosition);
        }

        return list;
    }


    private ArrayList enPassantMove(Integer nextXCoordinate, Integer nextYCoordinate) {
        ArrayList list = new ArrayList();
        if (outsideOfBoard(nextXCoordinate, nextYCoordinate)) {
            return list;
        }

        Square nextPosition = chessboard.getSquare(nextXCoordinate, nextYCoordinate);
        Square otherPiecePosition = chessboard.getSquare(nextXCoordinate, square.getPosY());
        if (otherPiecePosition.getPiece() != null && otherOwner(otherPiecePosition.getPiece())
                && !otherPiecePosition.getPiece().name.equals("King")
                && chessboard.myKing(getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(square, nextPosition)
                && chessboard.twoSquareMovedPawn != null
                && otherPiecePosition == chessboard.twoSquareMovedPawn.square) {
            list.add(nextPosition);
        }

        return list;
    }


    void promote(Piece newPiece) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
