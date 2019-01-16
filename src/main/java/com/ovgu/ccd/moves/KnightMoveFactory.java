package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.three.KnightMoves;
import com.ovgu.ccd.pieces.Piece;

/**
 * This is basically used for returning the Knight
 * in determining the list of squares on
 * the chessboard used for Knight.
 */

public class KnightMoveFactory {

    /**
     * Returns a list of squares of the of given
     * chessboard for the Knight.
     * @param board defines which chessboard is currently used.
     * @param piece refers the Knight of the chess game.
     * @return the list of squares.
     */

    public static IMove getMoves(final IBoard board, final Piece piece) {
        if (board instanceof ThreePlayerChessboard) {
            return new KnightMoves(piece, board);
        } else if (board instanceof Chessboard) {
            return new com.ovgu.ccd.moves.two.KnightMoves(piece, board);
        } else {
            throw new IllegalArgumentException("Invalid board");
        }
    }
}
