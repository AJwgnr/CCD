package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.three.DiagonalMoves;
import com.ovgu.ccd.pieces.Piece;

/**
 * Class is used to provide the DiagonalMoves for the pieces
 * on the chess board whether it is ThreePlayer
 * or TwoPlayer chessboard by returning the DiagonalMoves
 * of the respective chessboard.
 */

public class DiagonalMoveFactory {

    /**
     *
     * @param board defines the type of chessboard currently
     *              being used
     * @param piece defines the pieces for which u need
     *              to decide the move on the given chessbord
     * @return the DiagonalMoves of a piece on the given ChessBoard
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
