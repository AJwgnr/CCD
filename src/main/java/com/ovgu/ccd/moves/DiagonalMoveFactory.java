package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.three.DiagonalMoves;
import com.ovgu.ccd.pieces.Piece;

/**
 * Factory that returns the corresponding diagonal moves
 * depending on the board.
 */

public class DiagonalMoveFactory {

    /**
     * @param board current board.
     * @param piece to be moved.
     * @return a list of possible diagonal moves.
     */
    public static IMove getMoves(final IBoard board, final Piece piece) {
        if (board instanceof ThreePlayerChessboard) {
            return new DiagonalMoves(piece, board);
        } else if (board instanceof Chessboard) {
            return new com.ovgu.ccd.moves.two.DiagonalMoves(piece, board);
        } else {
            throw new IllegalArgumentException("Invalid board");
        }
    }
}
