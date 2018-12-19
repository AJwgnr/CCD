package com.ovgu.ccd.applogic;

import com.ovgu.ccd.moves.three.DiagonalMoves;
import com.ovgu.ccd.moves.three.StraightMoves;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckController {

    private ThreePlayerChessboard board;
    private King king;

    public  CheckController(ThreePlayerChessboard board, King king) {
        this.board = board;
        this.king = king;
    }

    public boolean isSafe() throws Exception {
        return checkStraightDirection() || checkDiagonalDirection();
    }

    private boolean checkStraightDirection() {
        ArrayList<Square> moves = new StraightMoves(king, board).moves();

        List<Square> squares = moves.stream().filter(square -> square.getPiece() != null).collect(Collectors.toList());

        return !squares.stream().anyMatch(square -> square.getPiece().name.equals("Rook") || square.getPiece().name.equals("Queen"));
    }

    private boolean checkDiagonalDirection() throws Exception {
        ArrayList<Square> moves = new DiagonalMoves(king, board).moves();

        List<Square> squares = moves.stream().filter(square -> square.getPiece() != null).collect(Collectors.toList());

        return !squares.stream().anyMatch(
                square -> square.getPiece().name.equals("Pawn") ||
                          square.getPiece().name.equals("Queen") ||
                          square.getPiece().name.equals("Bishop") ||
                          square.getPiece().name.equals("King")
        );
    }
}
