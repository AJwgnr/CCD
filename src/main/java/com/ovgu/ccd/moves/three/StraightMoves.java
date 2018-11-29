package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.moves.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class StraightMoves implements IMove {

    private Piece piece;
    private IBoard board;

    public StraightMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = board;
    }

    public ArrayList<Square> moves() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square currentSquare = piece.getSquare();

        for(int i = 0; i <= 11; i++) {
            Square nextMove = new Square(currentSquare.getPosX(), i, null);
            if (board.validMove(nextMove)) {
                possibleMoves.add(nextMove);
            }
            nextMove = new Square(i, currentSquare.getPosY(), null);
            if (board.validMove(nextMove)) {
                possibleMoves.add(nextMove);
            }
        }

        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }
}
