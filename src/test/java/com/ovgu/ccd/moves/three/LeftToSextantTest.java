package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.pieces.Bishop;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import com.ovgu.ccd.pieces.Square;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertTrue;

public class LeftToSextantTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    IBoard board = new ThreePlayerChessboard();
    Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);

    @Test
    public void testLeftToSextantInSquare0E() {
        board.setPiece(bishop, 0, ThreePlayerChessboard.E);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftToSextantInSquare0F() {
        board.setPiece(bishop, 0, ThreePlayerChessboard.F);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,  ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testLeftToSextantInSquare3A() {
        board.setPiece(bishop, 3, ThreePlayerChessboard.A);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftToSextantInSquare2A() {
        board.setPiece(bishop, 2, ThreePlayerChessboard.A);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.B, null)
                )
        );
    }

    @Test
    public void testLeftToSextantInSquare7C() {
        board.setPiece(bishop, 7, ThreePlayerChessboard.C);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6,  ThreePlayerChessboard.D, null)
                )
        );
    }

    @Test
    public void testLeftToSextantInSquare7D() {
        board.setPiece(bishop, 7, ThreePlayerChessboard.D);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftToSextantInSquare5L() {
        board.setPiece(bishop, 5, ThreePlayerChessboard.L);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftToSextantInSquare4L() {
        board.setPiece(bishop, 4, ThreePlayerChessboard.L);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftToSextantInSquare11J() {
        board.setPiece(bishop, 11, ThreePlayerChessboard.J);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10,  ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testLeftToSextantInSquare5I() {
        board.setPiece(bishop, 5, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.J, null)
                )
        );
    }


    @Test
    public void testLeftToSextantInSquare11I() {
        board.setPiece(bishop, 11, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftToSextantInSquare9F() {
        board.setPiece(bishop, 9, ThreePlayerChessboard.F);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.G, null)
                )
        );
    }

    @Test
    public void testLeftToSextantInSquare11K() {
        board.setPiece(bishop, 11, ThreePlayerChessboard.K);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).leftToSextant(bishop.getSquare());
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10,  ThreePlayerChessboard.J, null),
                        new Square(9,  ThreePlayerChessboard.I, null)
                )
        );
    }
}
