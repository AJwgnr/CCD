package com.ovgu.ccd.moves.two;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.gui.twoplayer.Chessboard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

public class StraightMoves implements IMove {
    private Piece piece;
    private Chessboard board;

    /**
     * @param piece for which moves are calculated
     * @param board in which moves are calculated
     */
    public StraightMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (Chessboard) board;
    }


    @Override
    public ArrayList<Square> moves() throws Exception {
        ArrayList moves = new ArrayList();

        moves.addAll(new HorizontalMoves(piece, board).all());
        moves.addAll(new VerticalMoves(piece, board).all());


        return moves;
    }
}
