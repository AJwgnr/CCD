package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.two.StraightMoves;
import com.ovgu.ccd.pieces.Piece;

/**
 * This provides a list of squares of a chessboard
 * for a piece.
 */

public class StraightMoveFactory {

    /**
     * Returns the list of squares for the related
     * to the straight movement of the pieces on a
     * given chessboard.
     * @param board defines the type of chessboard currently being used.
     * @param piece refers to the pieces of the chess game.
     * @return the list of squares on a given chessboard with
     * respect to the given piece specified.
     */
    public static IMove getMoves(final IBoard board, final Piece piece) {
        if (board instanceof ThreePlayerChessboard) {
            return new com.ovgu.ccd.moves.three.StraightMoves(piece, board);
        } else if (board instanceof Chessboard) {
            return new StraightMoves(piece, board);
        } else {
            throw new IllegalArgumentException("Invalid board");
        }
    }
}
