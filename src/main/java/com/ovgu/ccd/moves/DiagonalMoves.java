package com.ovgu.ccd.moves;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

/**
 *
 */
public class DiagonalMoves {

    private Piece piece;
    private Chessboard board;

    /**
     * @param piece
     * @param board
     */
    public DiagonalMoves(Piece piece, Chessboard board) {
        this.piece = piece;
        this.board = board;
    }

    /**
     * @return
     */
    public ArrayList all() {
        ArrayList moves = new ArrayList();
        Square square = piece.square;

        //left
        for (int x = square.getPosX() - 1, y = square.getPosY() + 1; !piece.outsideOfBoard(x, y); --x, ++y) {
            Square nextPosition = board.getSquare(x, y);
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

        for (int x = square.getPosX() - 1, y = square.getPosY() - 1; !piece.outsideOfBoard(x, y); --x, --y) {
            Square nextPosition = board.getSquare(x, y);
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

        //right
        for (int x = square.getPosX() + 1, y = square.getPosY() + 1; !piece.outsideOfBoard(x, y); ++x, ++y) {
            Square nextPosition = board.getSquare(x, y);
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

        for (int x = square.getPosX() + 1, y = square.getPosY() - 1; !piece.outsideOfBoard(x, y); ++x, --y) {
            Square nextPosition = board.getSquare(x, y);
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
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(piece.square, nextPosition);
    }
}