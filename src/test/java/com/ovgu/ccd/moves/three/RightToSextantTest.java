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

public class RightToSextantTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    IBoard board = new ThreePlayerChessboard();
    Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);

    @Test
    public void testrightToSextant3H() {
        board.setPiece(bishop, 3, ThreePlayerChessboard.H);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testrightToSextant2H() {
        board.setPiece(bishop, 2, ThreePlayerChessboard.H);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.G, null)
                )
        );
    }

    @Test
    public void testrightToSextant3D() {
        board.setPiece(bishop, 3, ThreePlayerChessboard.D);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testrightToSextant0C() {
        board.setPiece(bishop, 0, ThreePlayerChessboard.C);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,  ThreePlayerChessboard.D, null)
                )
        );
    }

    @Test
    public void testrightToSextant5A() {
        board.setPiece(bishop, 5, ThreePlayerChessboard.A);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.B, null)
                )
        );
    }

    @Test
    public void testrightToSextant4A() {
        board.setPiece(bishop, 4, ThreePlayerChessboard.A);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testrightToSextant7I() {
        board.setPiece(bishop, 7, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testrightToSextant7J() {
        board.setPiece(bishop, 7, ThreePlayerChessboard.J);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6,  ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testrightToSextant8L() {
        board.setPiece(bishop, 8, ThreePlayerChessboard.L);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testrightToSextant9L() {
        board.setPiece(bishop, 9, ThreePlayerChessboard.L);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.K, null)
                )
        );
    }

    @Test
    public void testrightToSextant11E() {
        board.setPiece(bishop, 11, ThreePlayerChessboard.E);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testrightToSextant11F() {
        board.setPiece(bishop, 11, ThreePlayerChessboard.F);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10,  ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testrightToSextant2G() {
        board.setPiece(bishop, 2, ThreePlayerChessboard.G);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.F, null)
                )
        );
    }

    @Test
    public void testrightToSextant9I() {
        board.setPiece(bishop, 9, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testrightToSextant8F() {
        board.setPiece(bishop, 8, ThreePlayerChessboard.F);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).rightToSextant(bishop.getSquare());
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9,  ThreePlayerChessboard.E, null)
                )
        );
    }
}
