package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

/**
 * class that checks if the king is in checkmate
 */
public class CheckMateController {

    /**
     * current board
     */
    private ThreePlayerChessboard board;
    /**
     * king to check
     */
    private King king;

    /**
     * @param board current board
     * @param king to be checked
     */
    public CheckMateController(final ThreePlayerChessboard board, final King king) {
        this.board = board;
        this.king = king;
    }


    /**
     * @return whether the king is in check mate
     * @throws Exception in case of an invalid move
     */
    public boolean isCheckMate() throws Exception {
        CheckController check = new CheckController(board, king, king, null);
        if (check.isSafe()) { return false; }
        boolean mate = true;

        ArrayList<Piece> pieces;
        if (king.getColor() == Player.Colors.WHITE) {
            pieces = board.whitePieces;
        } else if (king.getColor() == Player.Colors.BLACK) {
            pieces = board.blackPieces;
        } else {
            pieces = board.greyPieces;
        }

        for (Piece p: pieces) {
            if (p.getSquare() != null) {
                ArrayList<Square> moves = p.allMoves();
                for (Square s: moves) {
                    check = new CheckController(board, king, p, s);
                    if (check.isSafe()) {
                        mate = false;
                        break;
                    }
                }
            }
        }

        return mate;
    }
}
