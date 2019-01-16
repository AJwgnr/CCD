package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.three.KingMoves;
import com.ovgu.ccd.pieces.Piece;

/**
 * Factory that returns the corresponding king moves
 * depending on the board.
 */

public class KingMoveFactory {

    /**
     * @param board current board.
     * @param piece king to be moved.
     * @return a list of possible moves.
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