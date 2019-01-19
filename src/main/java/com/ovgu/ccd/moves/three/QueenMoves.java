package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

/**
 * Class that generates queen moves
 */
public class QueenMoves implements IMove {

    /**
     * queen for which moves are calculated
     */
    final private Piece piece;
    /**
     * board in which moves are calculated
     */
    final private IBoard board;

    /**
     * @param piece queen for which moves are calculated
     * @param board in which moves are calculated
     */
    public QueenMoves(final Piece piece, final IBoard board) {
        this.piece = piece;
        this.board = board;
    }


    @Override
    public ArrayList<Square> moves() throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();
        possibleMoves.addAll(new DiagonalMoves(piece, board).moves());
        possibleMoves.addAll(new StraightMoves(piece, board).moves());

        return possibleMoves;
    }
}
