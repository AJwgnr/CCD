package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.twoplayer.Chessboard;
import com.ovgu.ccd.moves.two.StraightMoves;
import com.ovgu.ccd.pieces.Piece;

/**
 * Factory that returns the corresponding straight moves
 * depending on the board.
 */
public class StraightMoveFactory {

    /**
     * @param board current board.
     * @param piece piece to be moved.
     * @return a list of possible moves.
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
