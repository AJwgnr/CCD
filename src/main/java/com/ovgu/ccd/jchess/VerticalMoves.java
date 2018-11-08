package com.ovgu.ccd.jchess;

import java.util.ArrayList;

public class VerticalMoves {

    private Piece piece;
    private Chessboard board;

    public VerticalMoves(Piece piece, Chessboard board)
    {
        this.piece = piece;
        this.board = board;
    }

    public ArrayList all()
    {
        ArrayList moves = new ArrayList();
        Square square = piece.square;

        //up
        for (int i = square.pozY + 1; i <= 7; ++i)
        {
            if (!piece.canMoveTo(square.pozX, i)) { break; }

            Square nextPosition = board.squares[square.pozX][i];
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(square.pozX, i)) { break; }
        }

        //down
        for (int i = square.pozY - 1; i >= 0; --i)
        {
            if (!piece.canMoveTo(square.pozX, i)) { break; }

            Square nextPosition = board.squares[square.pozX][i];
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(square.pozX, i)) { break; }
        }

        return moves;
    }

    private boolean validMove(Square nextPosition)
    {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(piece.square, nextPosition);
    }
}
