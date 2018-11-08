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
            if (!piece.canMoveTo(i, square.pozY)) { break; }

            Square nextPosition = board.squares[i][square.pozY];
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(i, square.pozY)) { break; }
        }

        //right
        for (int i = square.pozX + 1; i <= 7; ++i)
        {
            if (!piece.canMoveTo(i, square.pozY)) { break; }

            Square nextPosition = board.squares[i][square.pozY];
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(i, square.pozY)) { break; }
        }
        return moves;
    }

    private boolean validMove(Square nextPosition)
    {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(piece.square, nextPosition);
    }
}
