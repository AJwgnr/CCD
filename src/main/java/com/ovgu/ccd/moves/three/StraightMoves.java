package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class StraightMoves implements IMove {

    private Piece piece;
    private ThreePlayerChessboard board;

    public StraightMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (ThreePlayerChessboard) board;
    }

    public ArrayList<Square> moves() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square currentSquare = piece.getSquare();
        Square nextMove;

        for(int i = currentSquare.getPosX(); i <= 11; i++) {
            nextMove = new Square(currentSquare.getPosX(), i, null);
            if (!nextMove.equals(currentSquare)) {
                if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                    && !board.occupiedByOther(piece, nextMove)) { break; }
                if (board.validMove(nextMove, piece)) {
                    possibleMoves.add(nextMove);
                }
            }
        }
        for(int i = currentSquare.getPosX(); i >= 0; i--) {
            nextMove = new Square(currentSquare.getPosX(), i, null);
            if (!nextMove.equals(currentSquare))
                if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                    && !board.occupiedByOther(piece, nextMove)) { break; }{
                if (board.validMove(nextMove, piece)) {
                    possibleMoves.add(nextMove);
                }
            }
        }

        for(int i = currentSquare.getPosY(); i >= ThreePlayerChessboard.A; i--) {
            nextMove = new Square(i, currentSquare.getPosY(), null);
            if (!nextMove.equals(currentSquare)) {
                if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                    && !board.occupiedByOther(piece, nextMove)) { break; }
                if (board.validMove(nextMove, piece)) {
                    possibleMoves.add(nextMove);
                }
            }
        }

        for(int i = currentSquare.getPosY(); i <= ThreePlayerChessboard.L; i++) {
            nextMove = new Square(i, currentSquare.getPosY(), null);
            if (!nextMove.equals(currentSquare)) {
                if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                    && !board.occupiedByOther(piece, nextMove)) { break; }
                if (board.validMove(nextMove, piece)) {
                    possibleMoves.add(nextMove);
                }
            }
        }

        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }
}
