package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.three.KingMoves;
import com.ovgu.ccd.pieces.Piece;

/**
 * This specifically judeges the squares of
 * the chessboard for the King.
 */

public class KingMoveFactory {

    /**
     * This function returns list moves of the
     * king with respect to the chessboard
     * i.e. either three or two player.
     * @param board defines the board on which the
     *              movement of the King is to determined
     * @param piece refers to the King of the board
     * @return the list of squares of the for the King on the given chessboard.
     */

    public static IMove getMoves(final IBoard board, final Piece piece) {
        if (board instanceof ThreePlayerChessboard) {
            return new KingMoves(piece, board);
        } else if (board instanceof Chessboard) {
            return new com.ovgu.ccd.moves.two.KingMoves(piece, board);
        } else {
            throw new IllegalArgumentException("Invalid board");
        }
    }
}