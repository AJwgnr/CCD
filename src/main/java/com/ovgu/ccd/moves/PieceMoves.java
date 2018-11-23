package com.ovgu.ccd.moves;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

/**
 *
 */
public class PieceMoves {

    protected Piece piece;
    protected Chessboard board;

    /**
     * @param piece
     * @param board
     */
    public PieceMoves(Piece piece, Chessboard board) {
        this.piece = piece;
        this.board = board;
    }

    /**
     * @param nextPosition
     * @return
     */
    private boolean validMove(Square nextPosition) {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(piece.square, nextPosition);
    }
}
