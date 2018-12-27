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
        ArrayList<Square> moves = new ArrayList<>();

        Bishop rightWhiteBishop = (Bishop) board.getSquare(0, ThreePlayerChessboard.F).getPiece();

        moves = rightWhiteBishop.allMoves();
        assertTrue(moves.size() == 0);

        Pawn whitePawn = (Pawn) board.getSquare(1, ThreePlayerChessboard.E).getPiece();
        board.setPiece(whitePawn, 3, ThreePlayerChessboard.E);

        moves = rightWhiteBishop.allMoves();
        assertTrue(moves.size() == 5);

        whitePawn = (Pawn) board.getSquare(1, ThreePlayerChessboard.D).getPiece();
        board.setPiece(whitePawn, 3, ThreePlayerChessboard.D);

        Bishop leftWhiteBishop = (Bishop) board.getSquare(0, ThreePlayerChessboard.C).getPiece();
        moves = leftWhiteBishop.allMoves();
        assertTrue(moves.size() == 5);

        Pawn blackPawn = (Pawn) board.getSquare(6, ThreePlayerChessboard.D).getPiece();
        board.setPiece(blackPawn, 4, ThreePlayerChessboard.D);

        blackPawn = (Pawn) board.getSquare(6, ThreePlayerChessboard.B).getPiece();
        board.setPiece(blackPawn, 4, ThreePlayerChessboard.B);

        Bishop rightBlackBishop = (Bishop) board.getSquare(7, ThreePlayerChessboard.C).getPiece();
        moves = rightBlackBishop.allMoves();
        assertTrue(moves.size() == 7);

        blackPawn = (Pawn) board.getSquare(6, ThreePlayerChessboard.I).getPiece();
        board.setPiece(blackPawn, 4, ThreePlayerChessboard.I);
        blackPawn = (Pawn) board.getSquare(6, ThreePlayerChessboard.K).getPiece();
        board.setPiece(blackPawn, 4, ThreePlayerChessboard.K);

        Bishop leftBlackBishop = (Bishop) board.getSquare(7, ThreePlayerChessboard.J).getPiece();
        moves = leftBlackBishop.allMoves();
        assertTrue(moves.size() == 7);
    }
}
