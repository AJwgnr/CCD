package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.three.PawnMoves;
import com.ovgu.ccd.pieces.Piece;

/**
 * This helps in getting the squares of a given
 * chessboard, whether Three player or Two Player,
 * related to the Pawns of the chess game.
 */

public class PawnMoveFactory {

    /**
     * Returns the list of the squares of the specified
     * chessboard with respect to the Pawns of the chess game.
     * @param board defines the type of chessboard,
     *              i.e. Two or Three player's ones
     * @param piece refers to the Pawns of the chess game
     * @return list of squares of the chessboard for the Pawns.
     */

    public static IMove getMoves(final IBoard board, final Piece piece) {
        if (board instanceof ThreePlayerChessboard) {
            return new PawnMoves(piece, board);
        } else if (board instanceof Chessboard) {
            return new com.ovgu.ccd.moves.two.PawnMoves(piece, board);
        } else {
            throw new IllegalArgumentException("Invalid board");
        }
    }
}
