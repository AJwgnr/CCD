package com.ovgu.ccd.moves;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.jchess.ThreePlayerChessboard;
import com.ovgu.ccd.moves.three.DiagonalMoves;
import com.ovgu.ccd.pieces.Piece;

public class DiagonalMoveFactory {

    public static IMove getMoves(IBoard board, Piece piece) {
        if (board instanceof ThreePlayerChessboard) {
            return new DiagonalMoves(piece, board);
        } else if (board instanceof Chessboard) {
            return new com.ovgu.ccd.moves.DiagonalMoves(piece, board);
        } else {
            throw new IllegalArgumentException("Invalid board");
        }
    }
}
