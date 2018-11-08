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
        for (int x = square.pozX - 1, y = square.pozY + 1; !piece.isout(x, y); --x, ++y)
        {
            if (!piece.checkPiece(x, y)) { break; }

            Square nextPosition = board.squares[x][y];
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(x, y)) { break; }
        }

        for (int x = square.pozX - 1, y = square.pozY - 1; !piece.isout(x, y); --x, --y)
        {
            if (!piece.checkPiece(x, y)) { break; }

            Square nextPosition = board.squares[x][y];
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(x, y)) { break; }
        }

        //right
        for (int x = square.pozX + 1, y = square.pozY + 1; !piece.isout(x, y); ++x, ++y)
        {
            if (!piece.checkPiece(x, y)) { break; }

            Square nextPosition = board.squares[x][y];
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(x, y)) { break; }
        }

        for (int x = square.pozX + 1, y = square.pozY - 1; !piece.isout(x, y); ++x, --y)
        {
            if (!piece.checkPiece(x, y)) { break; }

            Square nextPosition = board.squares[x][y];
            if (validMove(nextPosition)) { moves.add(nextPosition); }
            if (piece.otherOwner(x, y)) { break; }
        }

        return moves;
    }

    private boolean validMove(Square nextPosition)
    {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(piece.square, nextPosition);
    }
}
