package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.pieces.*;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertTrue;

public class KnightMovesTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());
    ThreePlayerChessboard board;
    Knight knight;

    @Before
    public void initObjects(){
        board = new ThreePlayerChessboard();
        knight = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 11, ThreePlayerChessboard.L);
    }

    @Test
    public void testTwoDownOneRightInPosX0to3() {
        ArrayList<Square> moves;
        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight, 0, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
            board.setPiece(null,0, y);
        }

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight, 1, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
            board.setPiece(null, 1, y);
        }

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.G; y++) {
            board.setPiece(knight,2, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(0, y + 1, null)));
            board.setPiece(null, 2, y);
        }

        board.setPiece(knight, 2, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
        board.setPiece(null,2, ThreePlayerChessboard.H);

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.G; y++) {
            board.setPiece(knight,3, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(1, y + 1, null)));
            board.setPiece(null,3, y);
        }

        board.setPiece(knight, 3, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
        board.setPiece(null, 3, ThreePlayerChessboard.H);
    }

    @Test
    public void testTwoDownOneRightInPosX4to7() {
        ArrayList<Square> moves;

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight,7, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
            board.setPiece(null, 7, y);
        }

        for(int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight, 7, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
            board.getSquare(7, y).setPiece(null);
        }

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight, 6, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
            board.setPiece(null, 6, y);
        }

        for(int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight, 5, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(7, y - 1, null)));
            board.setPiece(null, 5, y);
        }

        board.setPiece(knight, 5, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoDownOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(7, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 5, ThreePlayerChessboard.I);

        for(int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight, 5, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(7, y - 1, null)));
            board.setPiece(null, 5, y);
        }

        board.setPiece(knight, 5, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
        board.setPiece(null, 5, ThreePlayerChessboard.A);

        for(int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight, 4, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(6, y - 1, null)));
            board.setPiece(null, 4, y);
        }

        board.setPiece(knight, 4, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoDownOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(6, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 4, ThreePlayerChessboard.I);

        for(int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight, 4, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(6, y - 1, null)));
            board.setPiece(null, 4, y);
        }

        board.setPiece(knight, 4, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
        board.setPiece(null, 4, ThreePlayerChessboard.A);
    }

    @Test
    public void testTwoDownOneRightInPosX8to11() {
        ArrayList<Square> moves;

        for(int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight,11, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
            board.setPiece(null, 11, y);
        }

        for(int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight,10, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
            board.setPiece(null, 10, y);
        }

        for(int y = ThreePlayerChessboard.F; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight,9, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(11, y - 1, null)));
            board.setPiece(null,9, y);
        }

        board.setPiece(knight, 9, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoDownOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(11, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 9, ThreePlayerChessboard.E);

        for(int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight,9, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(11, y + 1, null)));
            board.setPiece(null,9, y);
        }

        board.setPiece(knight,9, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
        board.setPiece(null, 9, ThreePlayerChessboard.L);

        for(int y = ThreePlayerChessboard.F; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight,9, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(11, y - 1, null)));
            board.setPiece(null,9, y);
        }

        for(int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight,9, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(11, y + 1, null)));
            board.setPiece(null,9, y);
        }

        board.setPiece(knight,9, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
        board.setPiece(null, 9, ThreePlayerChessboard.L);

        for(int y = ThreePlayerChessboard.F; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight,8, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(10, y - 1, null)));
            board.setPiece(null,8, y);
        }

        board.setPiece(knight, 8, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoDownOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(10, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.E);

        for(int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight,8, y);
            moves = new KnightMoves(knight, board).twoDownOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(10, y + 1, null)));
            board.setPiece(null,8, y);
        }

        board.setPiece(knight,8, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoDownOneRight().isEmpty());
        board.setPiece(null, 8, ThreePlayerChessboard.L);
    }

    @Test
    public void testTwoDownOneLeftInPosX0to3() {
        ArrayList<Square> moves;

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight,0, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
            board.setPiece(null, 0, y);
        }

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight,1, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
            board.setPiece(null, 1, y);
        }

        for(int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight,2, y);
            moves = new KnightMoves(knight, board).twoDownOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(0, y - 1, null)));
            board.setPiece(null,2, y);
        }

        board.setPiece(knight,2, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
        board.setPiece(null, 2, ThreePlayerChessboard.A);

        for(int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight,3, y);
            moves = new KnightMoves(knight, board).twoDownOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(1, y - 1, null)));
            board.setPiece(null,3, y);
        }

        board.setPiece(knight,3, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
        board.setPiece(null, 3, ThreePlayerChessboard.A);
    }

    @Test
    public void testTwoDownOneLeftInPosX4to7() {
        ArrayList<Square> moves;

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight,7, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
            board.setPiece(null, 7, y);
        }

        board.setPiece(knight, 5, ThreePlayerChessboard.A);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(7, ThreePlayerChessboard.B, null)));
        board.setPiece(null, 5, ThreePlayerChessboard.A);

        board.setPiece(knight, 5, ThreePlayerChessboard.B);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(7, ThreePlayerChessboard.C, null)));
        board.setPiece(null, 5, ThreePlayerChessboard.B);

        board.setPiece(knight, 5, ThreePlayerChessboard.C);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(7, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 5, ThreePlayerChessboard.C);

        board.setPiece(knight, 5, ThreePlayerChessboard.D);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(7, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 5, ThreePlayerChessboard.D);

        board.setPiece(knight, 4, ThreePlayerChessboard.A);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(6, ThreePlayerChessboard.B, null)));
        board.setPiece(null, 4, ThreePlayerChessboard.A);

        board.setPiece(knight, 4, ThreePlayerChessboard.B);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(6, ThreePlayerChessboard.C, null)));
        board.setPiece(null, 4, ThreePlayerChessboard.B);

        board.setPiece(knight, 4, ThreePlayerChessboard.C);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(6, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 4, ThreePlayerChessboard.C);

        board.setPiece(knight, 4, ThreePlayerChessboard.D);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(6, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 4, ThreePlayerChessboard.D);

        board.setPiece(knight, 5, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(7, ThreePlayerChessboard.J, null)));
        board.setPiece(null, 5, ThreePlayerChessboard.I);

        board.setPiece(knight, 5, ThreePlayerChessboard.J);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(7, ThreePlayerChessboard.K, null)));
        board.setPiece(null, 5, ThreePlayerChessboard.J);

        board.setPiece(knight, 5, ThreePlayerChessboard.K);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(7, ThreePlayerChessboard.L, null)));
        board.setPiece(null, 5, ThreePlayerChessboard.K);

        board.setPiece(knight,5, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
        board.setPiece(null, 5, ThreePlayerChessboard.L);

        board.setPiece(knight, 4, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(6, ThreePlayerChessboard.J, null)));
        board.setPiece(null, 4, ThreePlayerChessboard.I);

        board.setPiece(knight, 4, ThreePlayerChessboard.J);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(6, ThreePlayerChessboard.K, null)));
        board.setPiece(null, 4, ThreePlayerChessboard.J);

        board.setPiece(knight, 4, ThreePlayerChessboard.K);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(6, ThreePlayerChessboard.L, null)));
        board.setPiece(null, 4, ThreePlayerChessboard.K);

        board.setPiece(knight, 4, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
        board.setPiece(null, 4, ThreePlayerChessboard.L);
    }

    @Test
    public void testTwoDownOneLeftInPosX8to11() {
        ArrayList<Square> moves;

        for(int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight,11, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
            board.setPiece(null, 11, y);
        }

        for(int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight,10, y);
            assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
            board.setPiece(null, 10, y);
        }

        board.setPiece(knight, 9, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(11, ThreePlayerChessboard.F, null)));
        board.setPiece(null, 9, ThreePlayerChessboard.E);

        board.setPiece(knight, 9, ThreePlayerChessboard.F);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(11, ThreePlayerChessboard.G, null)));
        board.setPiece(null, 9, ThreePlayerChessboard.F);

        board.setPiece(knight, 9, ThreePlayerChessboard.G);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(11, ThreePlayerChessboard.H, null)));
        board.setPiece(null, 9, ThreePlayerChessboard.G);

        board.setPiece(knight, 9, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
        board.setPiece(null, 9, ThreePlayerChessboard.H);

        board.setPiece(knight, 8, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(10, ThreePlayerChessboard.F, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.E);

        board.setPiece(knight, 8, ThreePlayerChessboard.F);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(10, ThreePlayerChessboard.G, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.F);

        board.setPiece(knight, 8, ThreePlayerChessboard.G);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(10, ThreePlayerChessboard.H, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.G);

        board.setPiece(knight, 8, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoDownOneLeft().isEmpty());
        board.setPiece(null, 8, ThreePlayerChessboard.H);

        board.setPiece(knight, 9, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(11, ThreePlayerChessboard.E, null)));
        board.setPiece(null, 9, ThreePlayerChessboard.I);

        board.setPiece(knight, 9, ThreePlayerChessboard.J);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(11, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 9, ThreePlayerChessboard.J);

        board.setPiece(knight, 9, ThreePlayerChessboard.K);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(11, ThreePlayerChessboard.J, null)));
        board.setPiece(null, 9, ThreePlayerChessboard.K);

        board.setPiece(knight, 8, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(10, ThreePlayerChessboard.E, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.I);

        board.setPiece(knight, 8, ThreePlayerChessboard.J);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(10, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.J);

        board.setPiece(knight, 8, ThreePlayerChessboard.K);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(10, ThreePlayerChessboard.J, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.K);

        board.setPiece(knight, 8, ThreePlayerChessboard.L);
        moves = new KnightMoves(knight, board).twoDownOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(10, ThreePlayerChessboard.K, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.L);
    }

    @Test
    public void testTwoUpOneRightInPosX0to3() {
        ArrayList<Square> moves;

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.G; y++) {
            board.setPiece(knight,0, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(2, y + 1, null)));
            board.setPiece(null, 0, y);
        }

        board.setPiece(knight,0, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 0, ThreePlayerChessboard.H);

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.G; y++) {
            board.setPiece(knight,1, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(3, y + 1, null)));
            board.setPiece(null, 1, y);
        }

        board.setPiece(knight,1, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 1, ThreePlayerChessboard.H);

        for(int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.C; y++) {
            board.setPiece(knight,2, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(4, y + 1, null)));
            board.setPiece(null, 2, y);
        }

        board.setPiece(knight,2, ThreePlayerChessboard.D);
        moves = new KnightMoves(knight, board).twoUpOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 2, ThreePlayerChessboard.D);

        for(int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.G; y++) {
            board.setPiece(knight,2, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, y + 1, null)));
            board.setPiece(null, 2, y);
        }

        board.setPiece(knight,2, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 2, ThreePlayerChessboard.H);
    }

    @Test
    public void testTwoUpOneRightInPosX4to7() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight, 7, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(5, y - 1, null)));
            board.setPiece(null, 7, y);
        }

        board.setPiece(knight, 7, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 7, ThreePlayerChessboard.A);

        for (int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight, 7, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(5, y - 1, null)));
            board.setPiece(null, 7, y);
        }

        board.setPiece(knight,7, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoUpOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(5, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 7, ThreePlayerChessboard.I);

        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight, 6, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(4, y - 1, null)));
            board.setPiece(null, 6, y);
        }

        board.setPiece(knight, 6, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 6, ThreePlayerChessboard.A);

        for (int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight, 6, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(4, y - 1, null)));
            board.setPiece(null, 6, y);
        }

        board.setPiece(knight,6, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoUpOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 6, ThreePlayerChessboard.I);

        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight, 5, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(3, y - 1, null)));
            board.setPiece(null, 5, y);
        }

        board.setPiece(knight, 5, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 5, ThreePlayerChessboard.A);

        for (int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight, 5, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, y - 1, null)));
            board.setPiece(null, 5, y);
        }

        board.setPiece(knight,5, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoUpOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(8, ThreePlayerChessboard.E, null)));
        board.setPiece(null, 5, ThreePlayerChessboard.I);

        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight, 4, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(2, y - 1, null)));
            board.setPiece(null, 4, y);
        }

        board.setPiece(knight, 4, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 4, ThreePlayerChessboard.A);

        for (int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight, 4, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(9, y - 1, null)));
            board.setPiece(null, 4, y);
        }


        board.setPiece(knight,4, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoUpOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(9, ThreePlayerChessboard.E, null)));
        board.setPiece(null, 4, ThreePlayerChessboard.I);
    }

    @Test
    public void testTwoUpOneRightInPosX8to11() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight, 11, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(9, y - 1, null)));
            board.setPiece(null, 11, y);
        }

        board.setPiece(knight, 11, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 11, ThreePlayerChessboard.L);

        for (int y = ThreePlayerChessboard.F; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight, 11, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(9, y - 1, null)));
            board.setPiece(null, 11, y);
        }

        board.setPiece(knight, 11, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoUpOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(9, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 11, ThreePlayerChessboard.E);

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight, 10, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, y - 1, null)));
            board.setPiece(null, 10, y);
        }

        board.setPiece(knight, 10, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 10, ThreePlayerChessboard.L);

        for (int y = ThreePlayerChessboard.F; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight, 10, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, y - 1, null)));
            board.setPiece(null, 10, y);
        }

        board.setPiece(knight, 10, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoUpOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(8, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 10, ThreePlayerChessboard.E);

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight, 9, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(4, y + 1, null)));
            board.setPiece(null, 9, y);
        }

        board.setPiece(knight, 9, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 9, ThreePlayerChessboard.L);

        for (int y = ThreePlayerChessboard.F; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight, 9, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(3, y - 1, null)));
            board.setPiece(null, 9, y);
        }

        board.setPiece(knight, 9, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoUpOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(3, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 9, ThreePlayerChessboard.E);

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight, 8, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(5, y + 1, null)));
            board.setPiece(null, 8, y);
        }

        board.setPiece(knight, 8, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoUpOneRight().isEmpty());
        board.setPiece(null, 8, ThreePlayerChessboard.L);

        for (int y = ThreePlayerChessboard.F; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight, 8, y);
            moves = new KnightMoves(knight, board).twoUpOneRight();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(2, y - 1, null)));
            board.setPiece(null, 8, y);
        }

        board.setPiece(knight, 8, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoUpOneRight();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(2, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.E);
    }

    @Test
    public void testTwoUpOneLeftInPosX0to3() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight, 0, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(2, y - 1, null)));
            board.setPiece(null, 0, y);
        }

        board.setPiece(knight, 0, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 0, ThreePlayerChessboard.A);

        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight, 1, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(3, y - 1, null)));
            board.setPiece(null, 1, y);
        }

        board.setPiece(knight, 1, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 1, ThreePlayerChessboard.A);

        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight, 2, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(4, y - 1, null)));
            board.setPiece(null, 2, y);
        }

        board.setPiece(knight, 2, ThreePlayerChessboard.A);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 2, ThreePlayerChessboard.A);

        for (int y = ThreePlayerChessboard.F; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight, 2, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, y - 1, null)));
            board.setPiece(null, 2, y);
        }

        board.setPiece(knight, 2, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoUpOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(8, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 2, ThreePlayerChessboard.E);
    }

    @Test
    public void testTwoUpOneLeftInPosX4to7() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.C; y++) {
            board.setPiece(knight, 7, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(5, y + 1, null)));
            board.setPiece(null, 7, y);
        }

        board.setPiece(knight, 7, ThreePlayerChessboard.D);
        moves = new KnightMoves(knight, board).twoUpOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(5, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 7, ThreePlayerChessboard.D);

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight, 7, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(5, y + 1, null)));
            board.setPiece(null, 7, y);
        }

        board.setPiece(knight, 7, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 7, ThreePlayerChessboard.L);

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.C; y++) {
            board.setPiece(knight, 6, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(4, y + 1, null)));
            board.setPiece(null, 6, y);
        }

        board.setPiece(knight, 6, ThreePlayerChessboard.D);
        moves = new KnightMoves(knight, board).twoUpOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.I, null)));
        board.setPiece(null, 6, ThreePlayerChessboard.D);

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight, 6, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(4, y + 1, null)));
            board.setPiece(null, 6, y);
        }

        board.setPiece(knight, 6, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 6, ThreePlayerChessboard.L);

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.C; y++) {
            board.setPiece(knight, 5, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(3, y + 1, null)));
            board.setPiece(null, 5, y);
        }

        board.setPiece(knight, 5, ThreePlayerChessboard.D);
        moves = new KnightMoves(knight, board).twoUpOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(3, ThreePlayerChessboard.E, null)));
        board.setPiece(null, 5, ThreePlayerChessboard.D);

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight, 5, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, y + 1, null)));
            board.setPiece(null, 5, y);
        }

        board.setPiece(knight, 5, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 5, ThreePlayerChessboard.L);

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.C; y++) {
            board.setPiece(knight, 4, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(2, y + 1, null)));
            board.setPiece(null, 4, y);
        }

        board.setPiece(knight, 4, ThreePlayerChessboard.D);
        moves = new KnightMoves(knight, board).twoUpOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(2, ThreePlayerChessboard.E, null)));
        board.setPiece(null, 4, ThreePlayerChessboard.D);

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            board.setPiece(knight, 4, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(9, y + 1, null)));
            board.setPiece(null, 4, y);
        }

        board.setPiece(knight, 4, ThreePlayerChessboard.L);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 4, ThreePlayerChessboard.L);
    }

    @Test
    public void testTwoUpOneLeftInPosX8to11() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight,  11, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(9, y - 1, null)));
            board.setPiece(null, 11, y);
        }

        board.setPiece(knight, 11, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoUpOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(9, ThreePlayerChessboard.E, null)));
        board.setPiece(null, 11, ThreePlayerChessboard.I);

        for (int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.G; y++) {
            board.setPiece(knight,  11, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(9, y + 1, null)));
            board.setPiece(null, 11, y);
        }

        board.setPiece(knight, 11, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 11, ThreePlayerChessboard.H);

        for (int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight,  10, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, y - 1, null)));
            board.setPiece(null, 10, y);
        }

        board.setPiece(knight, 10, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoUpOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(8, ThreePlayerChessboard.E, null)));
        board.setPiece(null, 10, ThreePlayerChessboard.I);

        for (int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.G; y++) {
            board.setPiece(knight,  10, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, y + 1, null)));
            board.setPiece(null, 10, y);
        }

        board.setPiece(knight, 10, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 10, ThreePlayerChessboard.H);

        for (int y = ThreePlayerChessboard.K; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight,  9, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(4, y - 1, null)));
            board.setPiece(null, 9, y);
        }

        board.setPiece(knight, 9, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoUpOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 9, ThreePlayerChessboard.I);

        for (int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.G; y++) {
            board.setPiece(knight,  9, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(3, y + 1, null)));
            board.setPiece(null, 9, y);
        }

        board.setPiece(knight, 9, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 9, ThreePlayerChessboard.H);

        for (int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight,  8, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(5, y - 1, null)));
            board.setPiece(null, 8, y);
        }

        board.setPiece(knight, 8, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoUpOneLeft();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(5, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.I);

        for (int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.G; y++) {
            board.setPiece(knight,  8, y);
            moves = new KnightMoves(knight, board).twoUpOneLeft();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(2, y + 1, null)));
            board.setPiece(null, 8, y);
        }

        board.setPiece(knight, 8, ThreePlayerChessboard.H);
        assertTrue(new KnightMoves(knight, board).twoUpOneLeft().isEmpty());
        board.setPiece(null, 8, ThreePlayerChessboard.H);
    }

    @Test
    public void testTwoRightOneUpInPosX0to3() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.F; y++) {
            board.setPiece(knight,  0, y);
            moves = new KnightMoves(knight, board).twoRightOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(1, y + 2, null)));
            board.setPiece(null, 0, y);
        }

        for (int y = ThreePlayerChessboard.G; y <= ThreePlayerChessboard.H; y++) {
            for(int x = 0; x <= 3; x++) {
                board.setPiece(knight,  x, y);
                assertTrue(new KnightMoves(knight, board).twoRightOneUp().isEmpty());
                board.setPiece(null, x, y);
            }
        }

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.F; y++) {
            board.setPiece(knight,  1, y);
            moves = new KnightMoves(knight, board).twoRightOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(2, y + 2, null)));
            board.setPiece(null, 1, y);
        }

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.F; y++) {
            board.setPiece(knight,  2, y);
            moves = new KnightMoves(knight, board).twoRightOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(3, y + 2, null)));
            board.setPiece(null, 2, y);
        }

        board.setPiece(knight,  3, ThreePlayerChessboard.A);
        moves = new KnightMoves(knight, board).twoRightOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.C, null)));
        board.setPiece(null, 3, ThreePlayerChessboard.A);

        board.setPiece(knight,  3, ThreePlayerChessboard.B);
        moves = new KnightMoves(knight, board).twoRightOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.D, null)));
        board.setPiece(null, 3, ThreePlayerChessboard.B);

        for (int y = ThreePlayerChessboard.C; y <= ThreePlayerChessboard.F; y++) {
            board.setPiece(knight,  3, y);
            moves = new KnightMoves(knight, board).twoRightOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, y + 2, null)));
            board.setPiece(null, 3, y);
        }
    }

    @Test
    public void testTwoRightOneUpInPosX4to7() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.B; y++) {
            for(int x = 4; x <= 7; x++) {
                board.setPiece(knight,  x, y);
                assertTrue(new KnightMoves(knight, board).twoRightOneUp().isEmpty());
                board.setPiece(null, x, y);
            }
        }

        for (int y = ThreePlayerChessboard.K; y <= ThreePlayerChessboard.L; y++) {
            for(int x = 5; x <= 7; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() - 2, null)));
                board.setPiece(null,  x, y);
            }
        }

        for(int x = 5; x <= 7; x++) {
            board.setPiece(knight,  x, ThreePlayerChessboard.I);
            moves = new KnightMoves(knight, board).twoRightOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, ThreePlayerChessboard.C, null)));
            board.setPiece(null,  x, ThreePlayerChessboard.I);
        }

        for(int x = 5; x <= 7; x++) {
            board.setPiece(knight,  x, ThreePlayerChessboard.J);
            moves = new KnightMoves(knight, board).twoRightOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, ThreePlayerChessboard.D, null)));
            board.setPiece(null,  x, ThreePlayerChessboard.J);
        }

        board.setPiece(knight,  4, ThreePlayerChessboard.J);
        moves = new KnightMoves(knight, board).twoRightOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(3, ThreePlayerChessboard.D, null)));
        board.setPiece(null,  4, ThreePlayerChessboard.J);

        for (int y = ThreePlayerChessboard.C; y <= ThreePlayerChessboard.D; y++) {
            for(int x = 4; x <= 7; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() - 2, null)));
                board.setPiece(null,  x, y);
            }
        }
    }

    @Test
    public void testTwoRightOneUpInPosX8to11() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.L; y <= ThreePlayerChessboard.K; y++) {
            for(int x = 8; x <= 11; x++) {
                board.setPiece(knight,  x, y);
                assertTrue(new KnightMoves(knight, board).twoRightOneUp().isEmpty());
                board.setPiece(null, x, y);
            }
        }

        for (int y = ThreePlayerChessboard.H; y <= ThreePlayerChessboard.G; y++) {
            for(int x = 9; x <= 11; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() - 2, null)));
                board.setPiece(null,  x, y);
            }
        }

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.J; y++) {
            for(int x = 9; x <= 11; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() + 2, null)));
                board.setPiece(null,  x, y);
            }
        }

        board.setPiece(knight,  8, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoRightOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.K, null)));
        board.setPiece(null,  8, ThreePlayerChessboard.I);

        board.setPiece(knight,  8, ThreePlayerChessboard.J);
        moves = new KnightMoves(knight, board).twoRightOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.L, null)));
        board.setPiece(null,  8, ThreePlayerChessboard.J);

        board.setPiece(knight,  8, ThreePlayerChessboard.H);
        moves = new KnightMoves(knight, board).twoRightOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(3, ThreePlayerChessboard.F, null)));
        board.setPiece(null,  8, ThreePlayerChessboard.H);

        board.setPiece(knight,  8, ThreePlayerChessboard.G);
        moves = new KnightMoves(knight, board).twoRightOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(3, ThreePlayerChessboard.E, null)));
        board.setPiece(null,  8, ThreePlayerChessboard.G);

        for (int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.F; y++) {
            for(int x = 9; x <= 11; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() + 5, null)));
                board.setPiece(null,  x, y);
            }
        }

        board.setPiece(knight,  8, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoRightOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.J, null)));
        board.setPiece(null,  8, ThreePlayerChessboard.E);

        board.setPiece(knight,  8, ThreePlayerChessboard.F);
        moves = new KnightMoves(knight, board).twoRightOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.I, null)));
        board.setPiece(null,  8, ThreePlayerChessboard.F);
    }

    @Test
    public void testTwoRightOneDownInPosX0to3() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.F; y++) {
            for(int x = 1; x <= 3; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() + 2, null)));
                board.setPiece(null,  x, y);

            }
            board.setPiece(knight,  0, y);
            assertTrue(new KnightMoves(knight, board).twoRightOneDown().isEmpty());
            board.setPiece(null, 0, y);
        }
    }

    @Test
    public void testTwoRightOneDownInPosX4to7() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.B; y++) {
            for (int x = 4; x <= 7; x++) {
                board.setPiece(knight, x, y);
                assertTrue(new KnightMoves(knight, board).twoRightOneDown().isEmpty());
                board.setPiece(null, x, y);
            }
        }

        for (int y = ThreePlayerChessboard.C; y <= ThreePlayerChessboard.D; y++) {
            for(int x = 4; x <= 6; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, knight.getPosY() - 2, null)));
                board.setPiece(null,  x, y);

            }
            board.setPiece(knight,  7, y);
            assertTrue(new KnightMoves(knight, board).twoRightOneDown().isEmpty());
            board.setPiece(null, 7, y);
        }

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.J; y++) {
            for(int x = 4; x <= 6; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, knight.getPosY() - 6, null)));
                board.setPiece(null,  x, y);

            }
            board.setPiece(knight,  7, y);
            assertTrue(new KnightMoves(knight, board).twoRightOneDown().isEmpty());
            board.setPiece(null, 7, y);
        }

        for (int y = ThreePlayerChessboard.K; y <= ThreePlayerChessboard.L; y++) {
            for(int x = 4; x <= 6; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, knight.getPosY() - 2, null)));
                board.setPiece(null,  x, y);

            }
            board.setPiece(knight,  7, y);
            assertTrue(new KnightMoves(knight, board).twoRightOneDown().isEmpty());
            board.setPiece(null, 7, y);
        }
    }

    @Test
    public void testTwoRightOneDownInPosX8to11() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.L; y++) {
            board.setPiece(knight, 11, y);
            assertTrue(new KnightMoves(knight, board).twoRightOneDown().isEmpty());
            board.setPiece(null, 11, y);
        }

        for (int y = ThreePlayerChessboard.L; y <= ThreePlayerChessboard.K; y++) {
            for(int x = 8; x <= 11; x++) {
                board.setPiece(knight,  x, y);
                assertTrue(new KnightMoves(knight, board).twoRightOneDown().isEmpty());
                board.setPiece(null, x, y);
            }
        }

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.J; y++) {
            for(int x = 8; x <= 10; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, knight.getPosY() + 2, null)));
                board.setPiece(null,  x, y);
            }
        }

        for(int x = 8; x <= 10; x++) {
            board.setPiece(knight,  x, ThreePlayerChessboard.E);
            moves = new KnightMoves(knight, board).twoRightOneDown();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, ThreePlayerChessboard.J, null)));
            board.setPiece(null,  x, ThreePlayerChessboard.E);

            board.setPiece(knight,  x, ThreePlayerChessboard.F);
            moves = new KnightMoves(knight, board).twoRightOneDown();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, ThreePlayerChessboard.I, null)));
            board.setPiece(null,  x, ThreePlayerChessboard.F);
        }

        for (int y = ThreePlayerChessboard.G; y <= ThreePlayerChessboard.H; y++) {
            for(int x = 8; x <= 10; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoRightOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, knight.getPosY() - 2, null)));
                board.setPiece(null,  x, y);
            }
        }
    }

    @Test
    public void testTwoLeftOneUpInPosX0to3() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.B; y++) {
            for(int x = 0; x <= 3; x++) {
                board.setPiece(knight,  x, y);
                assertTrue(new KnightMoves(knight, board).twoLeftOneUp().isEmpty());
                board.setPiece(null, x, y);
            }
        }


        for (int y = ThreePlayerChessboard.C; y <= ThreePlayerChessboard.H; y++) {
            for(int x = 0; x <= 2; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, knight.getPosY() - 2, null)));
                board.setPiece(null,  x, y);
            }
        }

        for (int y = ThreePlayerChessboard.G; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight,  3, y);
            moves = new KnightMoves(knight, board).twoLeftOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, knight.getPosY() - 2, null)));
            board.setPiece(null,  3, y);
        }

        for (int y = ThreePlayerChessboard.C; y <= ThreePlayerChessboard.D; y++) {
            board.setPiece(knight,  3, y);
            moves = new KnightMoves(knight, board).twoLeftOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(4, knight.getPosY() - 2, null)));
            board.setPiece(null,  3, y);
        }

        board.setPiece(knight,  3, ThreePlayerChessboard.F);
        moves = new KnightMoves(knight, board).twoLeftOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.D, null)));
        board.setPiece(null,  3, ThreePlayerChessboard.F);

        board.setPiece(knight,  3, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).twoLeftOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(4, ThreePlayerChessboard.C, null)));
        board.setPiece(null,  3, ThreePlayerChessboard.E);
    }

    @Test
    public void testTwoLeftOneUpInPosX4to7() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.B; y++) {
            for(int x = 4; x <= 7; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() + 2, null)));
                board.setPiece(null, x, y);
            }
        }

        for (int y = ThreePlayerChessboard.C; y <= ThreePlayerChessboard.D; y++) {
            for(int x = 5; x <= 7; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() + 6, null)));
                board.setPiece(null, x, y);
            }

            board.setPiece(knight,  4, y);
            moves = new KnightMoves(knight, board).twoLeftOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, knight.getPosY() + 6, null)));
            board.setPiece(null, 4, y);
        }

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.J; y++) {
            for(int x = 5; x <= 7; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() + 2, null)));
                board.setPiece(null, x, y);
            }

            board.setPiece(knight,  4, y);
            moves = new KnightMoves(knight, board).twoLeftOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(8, knight.getPosY() + 2, null)));
            board.setPiece(null, 4, y);
        }

        for (int y = ThreePlayerChessboard.K; y <= ThreePlayerChessboard.L; y++) {
            for(int x = 4; x <= 7; x++) {
                board.setPiece(knight,  x, y);
                assertTrue(new KnightMoves(knight, board).twoLeftOneUp().isEmpty());
                board.setPiece(null, x, y);
            }
        }
    }

    @Test
    public void testTwoLeftOneUpInPosX8to11() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.G; y <= ThreePlayerChessboard.H; y++) {
            for(int x = 8; x <= 11; x++) {
                board.setPiece(knight,  x, y);
                assertTrue(new KnightMoves(knight, board).twoLeftOneUp().isEmpty());
                board.setPiece(null, x, y);
            }
        }

        for (int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.F; y++) {
            for(int x = 9; x <= 11; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() + 2, null)));
                board.setPiece(null, x, y);
            }

            board.setPiece(knight,  8, y);
            moves = new KnightMoves(knight, board).twoLeftOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(3, knight.getPosY() + 2, null)));
            board.setPiece(null, 8, y);
        }

        for(int x = 9; x <= 11; x++) {
            board.setPiece(knight,  x, ThreePlayerChessboard.J);
            moves = new KnightMoves(knight, board).twoLeftOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, ThreePlayerChessboard.E, null)));
            board.setPiece(null, x, ThreePlayerChessboard.J);
        }

        board.setPiece(knight,  8, ThreePlayerChessboard.J);
        moves = new KnightMoves(knight, board).twoLeftOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(3, ThreePlayerChessboard.E, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.J);

        for(int x = 9; x <= 11; x++) {
            board.setPiece(knight,  x, ThreePlayerChessboard.I);
            moves = new KnightMoves(knight, board).twoLeftOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, ThreePlayerChessboard.F, null)));
            board.setPiece(null, x, ThreePlayerChessboard.I);
        }

        board.setPiece(knight,  8, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).twoLeftOneUp();
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(moves, hasItems(new Square(3, ThreePlayerChessboard.F, null)));
        board.setPiece(null, 8, ThreePlayerChessboard.I);

        for (int y = ThreePlayerChessboard.K; y <= ThreePlayerChessboard.L; y++) {
            for(int x = 9; x <= 11; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneUp();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() - 2, null)));
                board.setPiece(null, x, y);
            }

            board.setPiece(knight,  8, y);
            moves = new KnightMoves(knight, board).twoLeftOneUp();
            assertTrue(moves.size() == 1);
            MatcherAssert.assertThat(moves, hasItems(new Square(4, knight.getPosY() - 2, null)));
            board.setPiece(null, 8, y);
        }
    }

    @Test
    public void testTwoLeftOneDownInPosX0to3() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.B; y++) {
            for(int x = 0; x <= 3; x++) {
                board.setPiece(knight,  x, y);
                assertTrue(new KnightMoves(knight, board).twoLeftOneDown().isEmpty());
                board.setPiece(null, x, y);
            }
        }

        for (int y = ThreePlayerChessboard.C; y <= ThreePlayerChessboard.H; y++) {
            board.setPiece(knight,  0, y);
            assertTrue(new KnightMoves(knight, board).twoLeftOneDown().isEmpty());
            board.setPiece(null, 0, y);

            for(int x = 1; x <= 3; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() - 1, knight.getPosY() - 2, null)));
                board.setPiece(null, x, y);
            }
        }
    }

    @Test
    public void testTwoLeftOneDownInPosX4to7() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.A; y <= ThreePlayerChessboard.B; y++) {
            for(int x = 4; x <= 6; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, knight.getPosY() + 2, null)));
                board.setPiece(null, x, y);
            }

            board.setPiece(knight,  7, y);
            assertTrue(new KnightMoves(knight, board).twoLeftOneDown().isEmpty());
            board.setPiece(null, 7, y);
        }

        for (int y = ThreePlayerChessboard.C; y <= ThreePlayerChessboard.D; y++) {
            for(int x = 4; x <= 6; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, knight.getPosY() + 6, null)));
                board.setPiece(null, x, y);
            }

            board.setPiece(knight,  7, y);
            assertTrue(new KnightMoves(knight, board).twoLeftOneDown().isEmpty());
            board.setPiece(null, 7, y);
        }

        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.J; y++) {
            for(int x = 4; x <= 6; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, knight.getPosY() + 2, null)));
                board.setPiece(null, x, y);
            }

            board.setPiece(knight,  7, y);
            assertTrue(new KnightMoves(knight, board).twoLeftOneDown().isEmpty());
            board.setPiece(null, 7, y);
        }

        for (int y = ThreePlayerChessboard.K; y <= ThreePlayerChessboard.L; y++) {
            for(int x = 4; x <= 7; x++) {
                board.setPiece(knight,  x, y);
                assertTrue(new KnightMoves(knight, board).twoLeftOneDown().isEmpty());
                board.setPiece(null, x, y);
            }
        }
    }

    @Test
    public void testTwoLeftOneDownInPosX8to11() {
        ArrayList<Square> moves;

        for (int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.F; y++) {
            for(int x = 8; x <= 10; x++) {
                board.setPiece(knight,  x, y);
                moves = new KnightMoves(knight, board).twoLeftOneDown();
                assertTrue(moves.size() == 1);
                MatcherAssert.assertThat(moves, hasItems(new Square(knight.getPosX() + 1, knight.getPosY() + 2, null)));
                board.setPiece(null, x, y);
            }

            board.setPiece(knight,  11, y);
            assertTrue(new KnightMoves(knight, board).twoLeftOneDown().isEmpty());
            board.setPiece(null, 11, y);
        }

        for (int y = ThreePlayerChessboard.G; y <= ThreePlayerChessboard.H; y++) {
            for(int x = 8; x <= 11; x++) {
                board.setPiece(knight,  x, y);
                assertTrue(new KnightMoves(knight, board).twoLeftOneDown().isEmpty());
                board.setPiece(null, x, y);
            }
        }
    }

    @Test
    public void testMovesInRousette1() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight, 8, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 10);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(3, ThreePlayerChessboard.C, null),
                        new Square(2, ThreePlayerChessboard.D, null),
                        new Square(2, ThreePlayerChessboard.F, null),
                        new Square(3, ThreePlayerChessboard.G, null),
                        new Square(9, ThreePlayerChessboard.G, null),
                        new Square(10, ThreePlayerChessboard.F, null),
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(4, ThreePlayerChessboard.J, null),
                        new Square(5, ThreePlayerChessboard.I, null)

                )
        );
        board.setPiece(null, 8, ThreePlayerChessboard.E);
    }

    @Test
    public void testMovesInRousette2() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight, 4, ThreePlayerChessboard.D);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 10);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(3, ThreePlayerChessboard.B, null),
                        new Square(3, ThreePlayerChessboard.F, null),

                        new Square(2, ThreePlayerChessboard.C, null),
                        new Square(2, ThreePlayerChessboard.E, null),

                        new Square(5, ThreePlayerChessboard.B, null),
                        new Square(5, ThreePlayerChessboard.J, null),

                        new Square(6, ThreePlayerChessboard.C, null),
                        new Square(6, ThreePlayerChessboard.I, null),


                        new Square(8, ThreePlayerChessboard.J, null),
                        new Square(9, ThreePlayerChessboard.I, null)

                )
        );
        board.setPiece(null, 4, ThreePlayerChessboard.D);
    }

    @Test
    public void testMovesInRousette3() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight, 3, ThreePlayerChessboard.D);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 10);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(1, ThreePlayerChessboard.C, null),
                        new Square(1, ThreePlayerChessboard.E, null),

                        new Square(2, ThreePlayerChessboard.B, null),
                        new Square(2, ThreePlayerChessboard.F, null),

                        new Square(4, ThreePlayerChessboard.B, null),
                        new Square(4, ThreePlayerChessboard.J, null),

                        new Square(5, ThreePlayerChessboard.C, null),
                        new Square(5, ThreePlayerChessboard.I, null),

                        new Square(8, ThreePlayerChessboard.F, null),
                        new Square(9, ThreePlayerChessboard.E, null)

                )
        );
        board.setPiece(null, 3, ThreePlayerChessboard.D);
    }

    @Test
    public void testMovesInRousette4() throws Exception {
        ArrayList<Square> moves;

        board.setPiece(knight, 4, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 10);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(3, ThreePlayerChessboard.C, null),
                        new Square(2, ThreePlayerChessboard.D, null),

                        new Square(5, ThreePlayerChessboard.C, null),
                        new Square(5, ThreePlayerChessboard.K, null),

                        new Square(6, ThreePlayerChessboard.D, null),
                        new Square(6, ThreePlayerChessboard.J, null),

                        new Square(8, ThreePlayerChessboard.K, null),
                        new Square(8, ThreePlayerChessboard.F, null),

                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(9, ThreePlayerChessboard.E, null)

                )
        );
        board.setPiece(null, 4, ThreePlayerChessboard.I);
    }

    @Test
    public void testMovesInRousette5() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight, 8, ThreePlayerChessboard.I);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 10);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(3, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.E, null),

                        new Square(4, ThreePlayerChessboard.C, null),
                        new Square(4, ThreePlayerChessboard.K, null),

                        new Square(5, ThreePlayerChessboard.D, null),
                        new Square(5, ThreePlayerChessboard.J, null),

                        new Square(9, ThreePlayerChessboard.K, null),
                        new Square(9, ThreePlayerChessboard.F, null),

                        new Square(10, ThreePlayerChessboard.J, null),
                        new Square(10, ThreePlayerChessboard.E, null)

                )
        );
        board.setPiece(null, 8, ThreePlayerChessboard.I);

    }

    @Test
    public void testMovesInRousette6() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  3, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 10);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(1, ThreePlayerChessboard.D, null),
                        new Square(1, ThreePlayerChessboard.F, null),

                        new Square(2, ThreePlayerChessboard.C, null),
                        new Square(2, ThreePlayerChessboard.G, null),

                        new Square(5, ThreePlayerChessboard.D, null),
                        new Square(4, ThreePlayerChessboard.C, null),

                        new Square(8, ThreePlayerChessboard.J, null),
                        new Square(8, ThreePlayerChessboard.G, null),

                        new Square(9, ThreePlayerChessboard.I, null),
                        new Square(9, ThreePlayerChessboard.F, null)

                )
        );
        board.setPiece(null, 3, ThreePlayerChessboard.E);
    }

    @Test
    public void testMovesInSquare0H() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  0, ThreePlayerChessboard.H);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(2, ThreePlayerChessboard.G, null),
                        new Square(1, ThreePlayerChessboard.F, null)
                )
        );
        board.setPiece(null, 0, ThreePlayerChessboard.H);
    }

    @Test
    public void testMovesInSquare0G() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  0, ThreePlayerChessboard.G);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(2, ThreePlayerChessboard.H, null),
                        new Square(2, ThreePlayerChessboard.F, null),
                        new Square(1, ThreePlayerChessboard.E, null)
                )
        );
        board.setPiece(null, 0, ThreePlayerChessboard.G);
    }

    @Test
    public void testMovesInSquare0F() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  0, ThreePlayerChessboard.F);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(1, ThreePlayerChessboard.H, null),
                        new Square(1, ThreePlayerChessboard.D, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(2, ThreePlayerChessboard.G, null)
                )
        );
        board.setPiece(null, 0, ThreePlayerChessboard.F);
    }

    @Test
    public void testMovesInSquare1E() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  1, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(0, ThreePlayerChessboard.G, null),
                        new Square(0, ThreePlayerChessboard.C, null),
                        new Square(2, ThreePlayerChessboard.C, null),
                        new Square(3, ThreePlayerChessboard.F, null),
                        new Square(3, ThreePlayerChessboard.D, null)
                )
        );
        board.setPiece(null, 1, ThreePlayerChessboard.E);
    }

    @Test
    public void testMovesInSquare1F() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  1, ThreePlayerChessboard.F);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(0, ThreePlayerChessboard.H, null),
                        new Square(2, ThreePlayerChessboard.H, null),
                        new Square(0, ThreePlayerChessboard.D, null),
                        new Square(2, ThreePlayerChessboard.D, null),
                        new Square(3, ThreePlayerChessboard.G, null),
                        new Square(3, ThreePlayerChessboard.E, null)
                )
        );
        board.setPiece(null, 1, ThreePlayerChessboard.F);
    }

    @Test
    public void testMovesInSquare1G() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  1, ThreePlayerChessboard.G);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(3, ThreePlayerChessboard.H, null),
                        new Square(3, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(0, ThreePlayerChessboard.E, null)
                )
        );
        board.setPiece(null, 1, ThreePlayerChessboard.G);
    }

    @Test
    public void testMovesInSquare1H() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  1, ThreePlayerChessboard.H);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(3, ThreePlayerChessboard.G, null),
                        new Square(2, ThreePlayerChessboard.F, null),
                        new Square(0, ThreePlayerChessboard.F, null)
                )
        );
        board.setPiece(null, 1, ThreePlayerChessboard.H);
    }

    @Test
    public void testMovesInSquare2H() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  2, ThreePlayerChessboard.H);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(8, ThreePlayerChessboard.G, null),
                        new Square(0, ThreePlayerChessboard.G, null),
                        new Square(3, ThreePlayerChessboard.F, null),
                        new Square(1, ThreePlayerChessboard.F, null)
                )
        );
        board.setPiece(null, 2, ThreePlayerChessboard.H);
    }

    @Test
    public void testMovesInSquare2G() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  2, ThreePlayerChessboard.G);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(8, ThreePlayerChessboard.H, null),
                        new Square(8, ThreePlayerChessboard.F, null),
                        new Square(0, ThreePlayerChessboard.H, null),
                        new Square(0, ThreePlayerChessboard.F, null),
                        new Square(3, ThreePlayerChessboard.E, null),
                        new Square(1, ThreePlayerChessboard.E, null)
                )
        );
        board.setPiece(null, 2, ThreePlayerChessboard.G);
    }

    @Test
    public void testMovesInSquare2F() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  2, ThreePlayerChessboard.F);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 8);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(0, ThreePlayerChessboard.E, null),
                        new Square(0, ThreePlayerChessboard.G, null),
                        new Square(3, ThreePlayerChessboard.H, null),
                        new Square(1, ThreePlayerChessboard.H, null),
                        new Square(8, ThreePlayerChessboard.G, null),
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(3, ThreePlayerChessboard.D, null),
                        new Square(1, ThreePlayerChessboard.D, null)
                )
        );
        board.setPiece(null, 2, ThreePlayerChessboard.F);
    }

    @Test
    public void testMovesInSquare2E() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  2, ThreePlayerChessboard.F);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 8);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(0, ThreePlayerChessboard.E, null),
                        new Square(0, ThreePlayerChessboard.G, null),
                        new Square(3, ThreePlayerChessboard.H, null),
                        new Square(1, ThreePlayerChessboard.H, null),
                        new Square(8, ThreePlayerChessboard.G, null),
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(3, ThreePlayerChessboard.D, null),
                        new Square(1, ThreePlayerChessboard.D, null)
                )
        );
        board.setPiece(null, 2, ThreePlayerChessboard.E);
    }

    @Test
    public void testMovesInSquare3E() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  3, ThreePlayerChessboard.E);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 10);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(1, ThreePlayerChessboard.D, null),
                        new Square(1, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.G, null),
                        new Square(8, ThreePlayerChessboard.G, null),
                        new Square(9, ThreePlayerChessboard.I, null),
                        new Square(9, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.C, null),
                        new Square(4, ThreePlayerChessboard.C, null),
                        new Square(8, ThreePlayerChessboard.J, null),
                        new Square(5, ThreePlayerChessboard.D, null)
                )
        );
        board.setPiece(null, 3, ThreePlayerChessboard.E);
    }

    @Test
    public void testMovesInSquare3F() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  3, ThreePlayerChessboard.F);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 9);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(1, ThreePlayerChessboard.E, null),
                        new Square(1, ThreePlayerChessboard.G, null),
                        new Square(2, ThreePlayerChessboard.H, null),
                        new Square(8, ThreePlayerChessboard.H, null),
                        new Square(9, ThreePlayerChessboard.E, null),
                        new Square(9, ThreePlayerChessboard.G, null),
                        new Square(2, ThreePlayerChessboard.D, null),
                        new Square(4, ThreePlayerChessboard.D, null),
                        new Square(8, ThreePlayerChessboard.I, null)
                )
        );
        board.setPiece(null, 3, ThreePlayerChessboard.F);
    }

    @Test
    public void testMovesInSquare3G() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  3, ThreePlayerChessboard.G);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(1, ThreePlayerChessboard.F, null),
                        new Square(1, ThreePlayerChessboard.H, null),
                        new Square(9, ThreePlayerChessboard.F, null),
                        new Square(9, ThreePlayerChessboard.H, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.E, null)
                )
        );
        board.setPiece(null, 3, ThreePlayerChessboard.G);
    }

    @Test
    public void testMovesInSquare3H() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  3, ThreePlayerChessboard.H);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(1, ThreePlayerChessboard.G, null),
                        new Square(9, ThreePlayerChessboard.G, null),
                        new Square(2, ThreePlayerChessboard.F, null),
                        new Square(8, ThreePlayerChessboard.F, null)
                )
        );
        board.setPiece(null, 3, ThreePlayerChessboard.H);
    }

    @Test
    public void testMovesInSquare4K() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  4, ThreePlayerChessboard.K);
        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(6, ThreePlayerChessboard.L, null),
                        new Square(9, ThreePlayerChessboard.L, null),
                        new Square(6, ThreePlayerChessboard.J, null),
                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(5, ThreePlayerChessboard.I, null),
                        new Square(8, ThreePlayerChessboard.I, null)
                )
        );
        board.setPiece(null, 4, ThreePlayerChessboard.K);
    }

    @Test
    public void testMovesInSquare3EBlockedByPieces() throws Exception {
        ArrayList<Square> moves;
        board.setPiece(knight,  3, ThreePlayerChessboard.E);
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(1, ThreePlayerChessboard.D).setPiece(pawn);
        board.getSquare(9, ThreePlayerChessboard.I).setPiece(pawn);
        board.getSquare(8, ThreePlayerChessboard.J).setPiece(pawn);

        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 7);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(1, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.G, null),
                        new Square(8, ThreePlayerChessboard.G, null),
                        new Square(9, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.C, null),
                        new Square(4, ThreePlayerChessboard.C, null),
                        new Square(5, ThreePlayerChessboard.D, null)
                )
        );
        board.setPiece(null, 3, ThreePlayerChessboard.E);
    }

    @Test
    public void testMovesInSquare3FWithPossibleCheck() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);
        ArrayList<Square> moves;

        board.setPiece(knight,  3, ThreePlayerChessboard.E);

        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.H);

        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testMovesInSquare3FStopCheckByEating() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);
        ArrayList<Square> moves;

        board.setPiece(knight,  1, ThreePlayerChessboard.G);

        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.H);

        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(3, ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testMovesInSquare3FStopCheck() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);
        ArrayList<Square> moves;

        board.setPiece(knight,  1, ThreePlayerChessboard.F);

        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.H);

        moves = new KnightMoves(knight, board).allMoves(true);
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves, hasItems(
                        new Square(3, ThreePlayerChessboard.G, null)
                )
        );
    }
}
