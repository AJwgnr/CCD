package com.ovgu.ccd.jchess;

import java.util.ArrayList;

public class HorizontalMoves {

    private Piece piece;
    private Chessboard board;

    public HorizontalMoves(Piece piece, Chessboard board)
    {
        this.piece = piece;
        this.board = board;
    }

    public ArrayList all()
    {
        ArrayList moves = new ArrayList();
        Square square = piece.square;

        //left
        for (int i = square.pozX - 1; i >= 0; --i)
        {
            Square nextPosition = board.getSquare(i, square.pozY);
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.piece)) { break; }
        }

        //right
        for (int i = square.pozX + 1; i <= 7; ++i)
        {
            Square nextPosition = board.getSquare(i, square.pozY);
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.piece)) { break; }
        }
        return moves;
    }

    private boolean validMove(Square nextPosition)
    {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(piece.square, nextPosition);
    }
}
