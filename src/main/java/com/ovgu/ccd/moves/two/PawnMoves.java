package com.ovgu.ccd.moves.two;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

public class PawnMoves implements IMove {

    private Piece piece;
    private Chessboard board;

    /**
     * @param piece
     * @param board
     */
    public PawnMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (Chessboard) board;
    }

    /**
     * @return
     */
    public ArrayList moves() {
        int immediateYCoordinate;
        int twoPositionsYCoordinate;
        ArrayList moves = new ArrayList();

        if (piece.getPlayer().isGoDown()) {
            immediateYCoordinate = piece.getSquare().getPosY() + 1;
            twoPositionsYCoordinate = piece.getSquare().getPosY() + 2;
        } else {
            immediateYCoordinate = piece.getSquare().getPosY() - 1;
            twoPositionsYCoordinate = piece.getSquare().getPosY() - 2;
        }

        if (piece.outsideOfBoard(immediateYCoordinate, immediateYCoordinate)) {
            return moves;
        }

        moves.addAll(regularMove(immediateYCoordinate));
        if (piece.getPlayer().isGoDown() && piece.getSquare().getPosY() == 1 || !piece.getPlayer().isGoDown() && piece.getSquare().getPosY() == 6)
        {
            moves.addAll(regularMove(twoPositionsYCoordinate));
        }

        // Capture left
        moves.addAll(captureMove(piece.getSquare().getPosX() - 1, immediateYCoordinate));

        // Capture right
        moves.addAll(captureMove(piece.getSquare().getPosX() + 1, immediateYCoordinate));

        // EnPassant left
        moves.addAll(enPassantMove(piece.getSquare().getPosX() - 1, immediateYCoordinate));

        // EnPassant right
        moves.addAll(enPassantMove(piece.getSquare().getPosX() + 1, immediateYCoordinate));

        return moves;
    }

    private ArrayList regularMove(Integer nextYCoordinate) {
        ArrayList list = new ArrayList();

        Square nextPosition = board.getSquare(piece.getSquare().getPosX(), nextYCoordinate);

        if (nextPosition.getPiece() != null) {
            return list;
        }
        if (board.myKing(piece.getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(piece.getSquare(), nextPosition)) {
            list.add(nextPosition);
        }
        return list;
    }

    private ArrayList captureMove(Integer nextXCoordinate, Integer nextYCoordinate) {
        ArrayList list = new ArrayList();
        if (piece.outsideOfBoard(nextXCoordinate, nextYCoordinate)) {
            return list;
        }

        Square nextPosition = board.getSquare(nextXCoordinate, nextYCoordinate);
        if (piece.canMoveTo(nextPosition) && piece.otherOwner(nextPosition.getPiece())
            && board.myKing(piece.getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(piece.getSquare(), nextPosition))
        {
            list.add(nextPosition);
        }

        return list;
    }


    private ArrayList enPassantMove(Integer nextXCoordinate, Integer nextYCoordinate) {
        ArrayList list = new ArrayList();
        if (piece.outsideOfBoard(nextXCoordinate, nextYCoordinate)) {
            return list;
        }

        Square nextPosition = board.getSquare(nextXCoordinate, nextYCoordinate);

        Square otherPiecePosition = board.getSquare(nextXCoordinate, piece.getSquare().getPosY());
        if (otherPiecePosition.getPiece() != null && piece.otherOwner(otherPiecePosition.getPiece())
            && !otherPiecePosition.getPiece().name.equals("King")
            && board.myKing(piece.getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(piece.getSquare(), nextPosition)
            && board.getTwoSquareMovedPawn() != null
            && otherPiecePosition == board.getTwoSquareMovedPawn().getSquare()) {
            list.add(nextPosition);
        }

        return list;
    }


}
