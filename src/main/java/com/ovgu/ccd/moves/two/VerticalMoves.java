package com.ovgu.ccd.moves.two;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.gui.twoplayer.Chessboard;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

/**
 *
 */
public class VerticalMoves {

    private Piece piece;
    private Chessboard board;
    private Square pieceSquare;

    /**
     * @param piece for which moves are calculated
     * @param board in which moves are calculated
     */
    public VerticalMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (Chessboard) board;
        this.pieceSquare = piece.getSquare();
    }

    /**
     * @return possible moves
     */
    public ArrayList all() {
        ArrayList moves = new ArrayList();
        Square square = piece.getSquare();

        //up
        for (int i = square.getPosY() + 1; i <= 7; ++i) {
            Square nextPosition = board.getSquare(square.getPosX(), i);
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
        for (int i = square.getPosY() - 1; i >= 0; --i) {
            Square nextPosition = board.getSquare(square.getPosX(), i);
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
     * @param nextPosition for the piece after move
     * @return true if the move is valid
     */
    private boolean validMove(Square nextPosition) {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(pieceSquare, nextPosition);
    }
}
