package com.ovgu.ccd.moves;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

/**
 *
 */
public class VerticalMoves {

    private Piece piece;
    private Chessboard board;

    /**
     * @param piece
     * @param board
     */
    public VerticalMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (Chessboard) board;
    }

    /**
     * @return
     */
    public ArrayList all() {
        ArrayList moves = new ArrayList();
        Square square = piece.getSquare();

        //up
        for (int i = square.getPozY() + 1; i <= 7; ++i) {
            Square nextPosition = board.getSquare(square.getPozX(), i);
            if (!piece.canMoveTo(nextPosition)) {
                break;
            }
            if (validMove(nextPosition)) {
                moves.add(nextPosition);
            }
            if (piece.otherOwner(nextPosition.getPiece())) {
                break;
            }
        }

        //down
        for (int i = square.getPozY() - 1; i >= 0; --i) {
            Square nextPosition = board.getSquare(square.getPozX(), i);
            if (!piece.canMoveTo(nextPosition)) {
                break;
            }
            if (validMove(nextPosition)) {
                moves.add(nextPosition);
            }
            if (piece.otherOwner(nextPosition.getPiece())) {
                break;
            }
        }

        return moves;
    }

    /**
     * @param nextPosition
     * @return
     */
    private boolean validMove(Square nextPosition) {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(piece.getSquare(), nextPosition);
    }
}
