package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.moves.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

public class QueenMove implements IMove {

    private Piece piece;
    private IBoard board;

    QueenMove(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = board;
    }


    @Override
    public ArrayList<Square> moves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        possibleMoves.addAll(new DiagonalMove(piece, board).moves());
        possibleMoves.addAll(new StraightMove(piece, board).moves());

        return possibleMoves;
    }
}
