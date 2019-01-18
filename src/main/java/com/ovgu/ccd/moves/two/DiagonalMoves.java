package com.ovgu.ccd.moves.two;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.gui.twoplayer.Chessboard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

/**
 *
 */
public class DiagonalMoves implements IMove {

    private Piece piece;
    private Chessboard board;
    private Square pieceSquare;

    /**
     * @param piece
     * @param board
     */
    public DiagonalMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (Chessboard) board;
        this.pieceSquare = piece.getSquare();
    }

    /**
     * @return
     */
    public ArrayList moves() {
        ArrayList moves = new ArrayList();
        Square square = piece.getSquare();

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
        piece.setSquare(pieceSquare);
        return moves;
    }

    /**
     * @param nextPosition
     * @return
     */
    private boolean validMove(Square nextPosition) {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(pieceSquare, nextPosition);
    }
}
