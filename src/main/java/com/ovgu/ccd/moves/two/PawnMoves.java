package com.ovgu.ccd.moves.two;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.gui.twoplayer.Chessboard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

public class PawnMoves implements IMove {

    private Piece piece;
    private Chessboard board;
    private Square pieceSquare;

    /**
     * @param piece
     * @param board
     */
    public PawnMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (Chessboard) board;
        this.pieceSquare = piece.getSquare();
    }

    /**
     * @return
     */
    public ArrayList moves() {
        int immediateYCoordinate;
        int twoPositionsYCoordinate;
        ArrayList moves = new ArrayList();

        if (piece.getPlayer().isGoDown()) {
            immediateYCoordinate = pieceSquare.getPosY() + 1;
            twoPositionsYCoordinate = pieceSquare.getPosY() + 2;
        } else {
            immediateYCoordinate = pieceSquare.getPosY() - 1;
            twoPositionsYCoordinate = pieceSquare.getPosY() - 2;
        }

        if (piece.outsideOfBoard(immediateYCoordinate, immediateYCoordinate)) {
            return moves;
        }

        moves.addAll(regularMove(immediateYCoordinate));
        if (piece.getPlayer().isGoDown() && pieceSquare.getPosY() == 1 || !piece.getPlayer().isGoDown() && pieceSquare.getPosY() == 6)
        {
            moves.addAll(regularMove(twoPositionsYCoordinate));
        }

        // Capture left
        moves.addAll(captureMove(pieceSquare.getPosX() - 1, immediateYCoordinate));

        // Capture right
        moves.addAll(captureMove(pieceSquare.getPosX() + 1, immediateYCoordinate));

        // EnPassant left
        moves.addAll(enPassantMove(pieceSquare.getPosX() - 1, immediateYCoordinate));

        // EnPassant right
        moves.addAll(enPassantMove(pieceSquare.getPosX() + 1, immediateYCoordinate));

        piece.setSquare(pieceSquare);
        return moves;
    }

    private ArrayList regularMove(Integer nextYCoordinate) {
        ArrayList list = new ArrayList();

        Square nextPosition = board.getSquare(pieceSquare.getPosX(), nextYCoordinate);

        if (nextPosition.getPiece() != null) {
            return list;
        }
        if (board.myKing(piece.getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(pieceSquare, nextPosition)) {
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
            && board.myKing(piece.getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(pieceSquare, nextPosition))
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

        Square otherPiecePosition = board.getSquare(nextXCoordinate, pieceSquare.getPosY());
        if (otherPiecePosition.getPiece() != null && piece.otherOwner(otherPiecePosition.getPiece())
            && !otherPiecePosition.getPiece().name.equals("King")
            && board.myKing(piece.getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(pieceSquare, nextPosition)
            && board.getTwoSquareMovedPawn() != null
            && otherPiecePosition == board.getTwoSquareMovedPawn().getSquare()) {
            list.add(nextPosition);
        }

        return list;
    }


}
