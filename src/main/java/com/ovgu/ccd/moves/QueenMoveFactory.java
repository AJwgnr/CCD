package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.three.QueenMoves;
import com.ovgu.ccd.pieces.Piece;

/**
 * This specifically returns the squares of the
 * chessboard for the Queen of the game.
 */

public class QueenMoveFactory {

    /**
     * Returns the list of the squares of the given
     * chessboard with respect to the Queen of the game.
     * @param board defines whether ThreePlayerChessBoard
     *              or TwoPlayerChess to be provided.
     * @param piece refers to the Queen of the game.
     * @return the the list of squares of the given chessboard.
     */

    public static IMove getMoves(final IBoard board, final Piece piece) {
        if (board instanceof ThreePlayerChessboard) {
            return new QueenMoves(piece, board);
        } else if (board instanceof Chessboard) {
            return new com.ovgu.ccd.moves.two.QueenMoves(piece, board);
        } else {
            throw new IllegalArgumentException("Invalid board");
        }
    }
}
