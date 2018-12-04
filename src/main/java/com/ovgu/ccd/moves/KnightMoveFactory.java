package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.moves.three.KnightMoves;
import com.ovgu.ccd.pieces.Piece;

public class KnightMoveFactory {

    public static IMove getMoves(IBoard board, Piece piece) {
        if (board instanceof ThreePlayerChessboard) {
            return new KnightMoves(piece, board);
        } else if (board instanceof Chessboard) {
            return new com.ovgu.ccd.moves.two.KnightMoves(piece, board);
        } else {
            throw new IllegalArgumentException("Invalid board");
        }
    }
}