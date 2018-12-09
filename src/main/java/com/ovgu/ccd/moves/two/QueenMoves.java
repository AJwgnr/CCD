package com.ovgu.ccd.moves.two;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

public class QueenMoves implements IMove {

    private Piece piece;
    private IBoard board;

    public QueenMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = board;
    }


    @Override
    public ArrayList<Square> moves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        possibleMoves.addAll(new DiagonalMoves(piece, board).moves());
        possibleMoves.addAll(new StraightMoves(piece, board).moves());

        return possibleMoves;
    }
}
