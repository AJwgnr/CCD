package com.ovgu.ccd.jchess;

import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

public class DiagonalMoves {

    private Piece piece;
    private IBoard board;

    public DiagonalMoves(Piece piece, IBoard board)
    {
        this.piece = piece;
        this.board = board;
    }

    public ArrayList all()
    {
        ArrayList moves = new ArrayList();
        Square square = piece.getSquare();

        //left
        for (int x = square.getPozX() - 1, y = square.getPozY() + 1; !piece.outsideOfBoard(x, y); --x, ++y)
        {
            Square nextPosition = board.getSquare(x, y);
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.getPiece())) { break; }
        }

        for (int x = square.getPozX() - 1, y = square.getPozY() - 1; !piece.outsideOfBoard(x, y); --x, --y)
        {
            Square nextPosition = board.getSquare(x, y);
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.getPiece())) { break; }
        }

        //right
        for (int x = square.getPozX() + 1, y = square.getPozY() + 1; !piece.outsideOfBoard(x, y); ++x, ++y)
        {
            Square nextPosition = board.getSquare(x, y);
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.getPiece())) { break; }
        }

        for (int x = square.getPozX() + 1, y = square.getPozY() - 1; !piece.outsideOfBoard(x, y); ++x, --y)
        {
            Square nextPosition = board.getSquare(x, y);
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.getPiece())) { break; }
        }

        return moves;
    }

    private boolean validMove(Square nextPosition)
    {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(piece.getSquare(), nextPosition);
    }
}
