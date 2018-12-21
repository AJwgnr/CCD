package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.pieces.Bishop;
import com.ovgu.ccd.pieces.Pawn;
import com.ovgu.ccd.pieces.Square;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

public class FullBoardTest {

    @Test
    public void test() {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        board.initPlayers();
        board.initPieceStartPositions();

        Bishop rightWhiteBishop = (Bishop) board.getSquare(0, ThreePlayerChessboard.F).getPiece();

        ArrayList<Square> moves = rightWhiteBishop.allMoves();
        assertTrue(moves.size() == 0);

        Pawn whitePawn = (Pawn) board.getSquare(1, ThreePlayerChessboard.E).getPiece();
        board.setPiece(whitePawn, 3, ThreePlayerChessboard.E);

        moves = rightWhiteBishop.allMoves();
        assertTrue(moves.size() == 5);
    }
}
