package com.ovgu.ccd.jchess;

import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;
import com.ovgu.ccd.view.Chessboard;

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
        for (int i = square.getPozY() + 1; i <= 7; ++i)
        {
            Square nextPosition = board.getSquare(square.getPozX(), i);
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.getPiece())) { break; }
        }

        //down
        for (int i = square.getPozY() - 1; i >= 0; --i)
        {
            Square nextPosition = board.getSquare(square.getPozX(), i);
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.getPiece())) { break; }
        }

        return moves;
    }

    private boolean validMove(Square nextPosition)
    {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(piece.square, nextPosition);
    }
}
