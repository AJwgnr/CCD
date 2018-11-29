package com.ovgu.ccd.moves.two;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

public class KnightMoves implements IMove {
    private Piece piece;
    private Chessboard board;

    /**
     * @param piece
     * @param board
     */
    public KnightMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (Chessboard) board;
    }

    @Override
    public ArrayList<Square> moves() throws Exception {
        ArrayList moves = new ArrayList();

        // knight moves moves
        //  _______________ Y:
        // |_|_|_|_|_|_|_|_|7
        // |_|_|_|_|_|_|_|_|6
        // |_|_|2|_|3|_|_|_|5
        // |_|1|_|_|_|4|_|_|4
        // |_|_|_|K|_|_|_|_|3
        // |_|8|_|_|_|5|_|_|2
        // |_|_|7|_|6|_|_|_|1
        // |_|_|_|_|_|_|_|_|0
        //X:0 1 2 3 4 5 6 7
        //

        int newX;
        int newY;
        Square square = piece.getSquare();

        if (validMove(newX = square.getPosX() - 2, newY = square.getPosY() + 1))
            moves.add(board.getSquare(newX, newY));

        if (validMove(newX = square.getPosX() - 1, newY = square.getPosY() + 2))
            moves.add(board.getSquare(newX, newY));

        if (validMove(newX = square.getPosX() + 1, newY = square.getPosY() + 2))
            moves.add(board.getSquare(newX, newY));

        if (validMove(newX = square.getPosX() + 2, newY = square.getPosY() + 1))
            moves.add(board.getSquare(newX, newY));

        if (validMove(newX = square.getPosX() + 2, newY = square.getPosY() - 1))
            moves.add(board.getSquare(newX, newY));

        if (validMove(newX = square.getPosX() + 1, newY = square.getPosY() - 2))
            moves.add(board.getSquare(newX, newY));

        if (validMove(newX = square.getPosX() - 1, newY = square.getPosY() - 2))
            moves.add(board.getSquare(newX, newY));

        if (validMove(newX = square.getPosX() - 2, newY = square.getPosY() - 1))
            moves.add(board.getSquare(newX, newY));

        return moves;
    }

    private boolean validMove(int newX, int newY) {
        if (piece.outsideOfBoard(newX, newY)) {
            return false;
        }

        Square nextPosition = board.getSquare(newX, newY);
        return (piece.canMoveTo(nextPosition) && (board.myKing(piece.getPlayer().getColor()).willBeSafeWhenMoveOtherPiece(piece.getSquare(), nextPosition)));
    }
}
