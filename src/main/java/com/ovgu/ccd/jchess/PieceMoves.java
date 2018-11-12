package com.ovgu.ccd.jchess;

public class PieceMoves {

    protected Piece piece;
    protected Chessboard board;


    public PieceMoves(Piece piece, Chessboard board)
    {
        this.piece = piece;
        this.board = board;
    }

    private boolean validMove(Square nextPosition)
    {
        return board.myKing(piece.getColor()).willBeSafeWhenMoveOtherPiece(piece.square, nextPosition);
    }
}
