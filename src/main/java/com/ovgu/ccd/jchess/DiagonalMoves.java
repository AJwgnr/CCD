package com.ovgu.ccd.jchess;

import java.util.ArrayList;

public class DiagonalMoves {

    private Piece piece;
    private Chessboard board;

    public DiagonalMoves(Piece piece, Chessboard board)
    {
        this.piece = piece;
        this.board = board;
    }

    public ArrayList all()
    {
        ArrayList moves = new ArrayList();
        Square square = piece.square;

        //left
        for (int x = square.pozX - 1, y = square.pozY + 1; !piece.outsideOfBoard(x, y); --x, ++y)
        {
            Square nextPosition = board.squares[x][y];
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.piece)) { break; }
        }

        for (int x = square.pozX - 1, y = square.pozY - 1; !piece.outsideOfBoard(x, y); --x, --y)
        {
            Square nextPosition = board.squares[x][y];
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.piece)) { break; }
        }

        //right
        for (int x = square.pozX + 1, y = square.pozY + 1; !piece.outsideOfBoard(x, y); ++x, ++y)
        {
            Square nextPosition = board.squares[x][y];
            if (!piece.canMoveTo(nextPosition)) { break; }
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(nextPosition.piece)) { break; }
        }

        for (int x = square.pozX + 1, y = square.pozY - 1; !piece.outsideOfBoard(x, y); ++x, --y)
        {
            Square nextPosition = board.squares[x][y];
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
