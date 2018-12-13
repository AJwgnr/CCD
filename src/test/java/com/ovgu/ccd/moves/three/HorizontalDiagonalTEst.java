package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.pieces.*;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertTrue;


public class HorizontalDiagonalTEst {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());

    @Test
    public void testLeftHorizontal3A() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.A);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.B, null),
                        new Square(5,  ThreePlayerChessboard.C, null),
                        new Square(6,  ThreePlayerChessboard.D, null),
                        new Square(7,  ThreePlayerChessboard.I, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.B);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(3, ThreePlayerChessboard.A, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftHorizontal3B() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.B);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.A, null),
                        new Square(4,  ThreePlayerChessboard.C, null),
                        new Square(5,  ThreePlayerChessboard.D, null),
                        new Square(6,  ThreePlayerChessboard.I, null),
                        new Square(7,  ThreePlayerChessboard.J, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(4, ThreePlayerChessboard.A, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftHorizontal3C() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.C);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.D, null),
                        new Square(5,  ThreePlayerChessboard.I, null),
                        new Square(6,  ThreePlayerChessboard.J, null),
                        new Square(7,  ThreePlayerChessboard.K, null),

                        new Square(4,  ThreePlayerChessboard.B, null),
                        new Square(5,  ThreePlayerChessboard.A, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.D);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(3, ThreePlayerChessboard.C, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.B, null),
                        new Square(5,  ThreePlayerChessboard.A, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.B);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(3, ThreePlayerChessboard.C, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.D, null),
                        new Square(5,  ThreePlayerChessboard.I, null),
                        new Square(6,  ThreePlayerChessboard.J, null),
                        new Square(7,  ThreePlayerChessboard.K, null)
                        )
        );
    }

    @Test
    public void testLeftHorizontal0E() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 0, ThreePlayerChessboard.E);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,  ThreePlayerChessboard.D, null),
                        new Square(2,  ThreePlayerChessboard.C, null),
                        new Square(3,  ThreePlayerChessboard.B, null),
                        new Square(4,  ThreePlayerChessboard.A, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(bishop, 1, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(0, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 0);

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.G);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(0, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftHorizontal1E() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 1, ThreePlayerChessboard.E);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(0,  ThreePlayerChessboard.D, null),
                        new Square(2,  ThreePlayerChessboard.D, null),
                        new Square(3,  ThreePlayerChessboard.C, null),
                        new Square(4,  ThreePlayerChessboard.B, null),
                        new Square(5,  ThreePlayerChessboard.A, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(bishop, 0, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(1, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2,  ThreePlayerChessboard.D, null),
                        new Square(3,  ThreePlayerChessboard.C, null),
                        new Square(4,  ThreePlayerChessboard.B, null),
                        new Square(5,  ThreePlayerChessboard.A, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(1, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(0,  ThreePlayerChessboard.D, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal2E() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.E);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.D, null),
                        new Square(4,  ThreePlayerChessboard.C, null),
                        new Square(5,  ThreePlayerChessboard.B, null),
                        new Square(6,  ThreePlayerChessboard.A, null),

                        new Square(1,  ThreePlayerChessboard.D, null),
                        new Square(0,  ThreePlayerChessboard.C, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(2, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,  ThreePlayerChessboard.D, null),
                        new Square(0,  ThreePlayerChessboard.C, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(bishop, 1, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(2, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.D, null),
                        new Square(4,  ThreePlayerChessboard.C, null),
                        new Square(5,  ThreePlayerChessboard.B, null),
                        new Square(6,  ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal7D() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 7, ThreePlayerChessboard.D);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6,  ThreePlayerChessboard.I, null),
                        new Square(5,  ThreePlayerChessboard.J, null),
                        new Square(4,  ThreePlayerChessboard.K, null),
                        new Square(8,  ThreePlayerChessboard.L, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 6, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(7, ThreePlayerChessboard.D, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftHorizontal6D() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 6, ThreePlayerChessboard.D);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(7,  ThreePlayerChessboard.I, null),
                        new Square(5,  ThreePlayerChessboard.I, null),
                        new Square(4,  ThreePlayerChessboard.J, null),
                        new Square(8,  ThreePlayerChessboard.K, null),
                        new Square(9,  ThreePlayerChessboard.L, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(6, ThreePlayerChessboard.D, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(7,  ThreePlayerChessboard.I, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 7, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(6, ThreePlayerChessboard.D, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5,  ThreePlayerChessboard.I, null),
                        new Square(4,  ThreePlayerChessboard.J, null),
                        new Square(8,  ThreePlayerChessboard.K, null),
                        new Square(9,  ThreePlayerChessboard.L, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal5D() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.D);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(7,  ThreePlayerChessboard.J, null),
                        new Square(6,  ThreePlayerChessboard.I, null),
                        new Square(4,  ThreePlayerChessboard.I, null),
                        new Square(8,  ThreePlayerChessboard.J, null),
                        new Square(9,  ThreePlayerChessboard.K, null),
                        new Square(10,  ThreePlayerChessboard.L, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 6, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(5, ThreePlayerChessboard.D, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.I, null),
                        new Square(8,  ThreePlayerChessboard.J, null),
                        new Square(9,  ThreePlayerChessboard.K, null),
                        new Square(10,  ThreePlayerChessboard.L, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(5, ThreePlayerChessboard.D, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6,  ThreePlayerChessboard.I, null),
                        new Square(7,  ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal4L() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.L);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.K, null),
                        new Square(9,  ThreePlayerChessboard.J, null),
                        new Square(10,  ThreePlayerChessboard.I, null),
                        new Square(11,  ThreePlayerChessboard.E, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.K);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(4, ThreePlayerChessboard.L, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftHorizontal4K() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.K);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.L, null),
                        new Square(8,  ThreePlayerChessboard.J, null),
                        new Square(9,  ThreePlayerChessboard.I, null),
                        new Square(10,  ThreePlayerChessboard.E, null),
                        new Square(11,  ThreePlayerChessboard.F, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.L);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(4, ThreePlayerChessboard.K, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.J, null),
                        new Square(9,  ThreePlayerChessboard.I, null),
                        new Square(10,  ThreePlayerChessboard.E, null),
                        new Square(11,  ThreePlayerChessboard.F, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(4, ThreePlayerChessboard.K, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.L, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal4J() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.J);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.I, null),
                        new Square(9,  ThreePlayerChessboard.E, null),
                        new Square(10,  ThreePlayerChessboard.F, null),
                        new Square(11,  ThreePlayerChessboard.G, null),

                        new Square(8,  ThreePlayerChessboard.K, null),
                        new Square(9,  ThreePlayerChessboard.L, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.K);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(4, ThreePlayerChessboard.J, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.I, null),
                        new Square(9,  ThreePlayerChessboard.E, null),
                        new Square(10,  ThreePlayerChessboard.F, null),
                        new Square(11,  ThreePlayerChessboard.G, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.I);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(4, ThreePlayerChessboard.J, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.K, null),
                        new Square(9,  ThreePlayerChessboard.L, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal11I() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10,  ThreePlayerChessboard.E, null),
                        new Square(9,  ThreePlayerChessboard.F, null),
                        new Square(8,  ThreePlayerChessboard.G, null),
                        new Square(3,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(11, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftHorizontal10I() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11,  ThreePlayerChessboard.E, null),
                        new Square(9,  ThreePlayerChessboard.E, null),
                        new Square(8,  ThreePlayerChessboard.F, null),
                        new Square(3,  ThreePlayerChessboard.G, null),
                        new Square(2,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(10, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9,  ThreePlayerChessboard.E, null),
                        new Square(8,  ThreePlayerChessboard.F, null),
                        new Square(3,  ThreePlayerChessboard.G, null),
                        new Square(2,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(10, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11,  ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal9I() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.E, null),
                        new Square(3,  ThreePlayerChessboard.F, null),
                        new Square(2,  ThreePlayerChessboard.G, null),
                        new Square(1,  ThreePlayerChessboard.H, null),
                        new Square(10,  ThreePlayerChessboard.E, null),
                        new Square(11,  ThreePlayerChessboard.F, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(9, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10,  ThreePlayerChessboard.E, null),
                        new Square(11,  ThreePlayerChessboard.F, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(9, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.E, null),
                        new Square(3,  ThreePlayerChessboard.F, null),
                        new Square(2,  ThreePlayerChessboard.G, null),
                        new Square(1,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.K);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(9, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.E, null),
                        new Square(3,  ThreePlayerChessboard.F, null),
                        new Square(2,  ThreePlayerChessboard.G, null),
                        new Square(1,  ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal8H() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.H);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.G, null),
                        new Square(2,  ThreePlayerChessboard.F, null),
                        new Square(1,  ThreePlayerChessboard.E, null),
                        new Square(0,  ThreePlayerChessboard.D, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.G);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(8, ThreePlayerChessboard.H, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testLeftHorizontal8G() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.G);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.H, null),
                        new Square(3,  ThreePlayerChessboard.F, null),
                        new Square(2,  ThreePlayerChessboard.E, null),
                        new Square(1,  ThreePlayerChessboard.D, null),
                        new Square(0,  ThreePlayerChessboard.C, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(8, ThreePlayerChessboard.G, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.H);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(8, ThreePlayerChessboard.G, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.F, null),
                        new Square(2,  ThreePlayerChessboard.E, null),
                        new Square(1,  ThreePlayerChessboard.D, null),
                        new Square(0,  ThreePlayerChessboard.C, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal8F() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.F);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.E, null),
                        new Square(2,  ThreePlayerChessboard.D, null),
                        new Square(1,  ThreePlayerChessboard.C, null),
                        new Square(0,  ThreePlayerChessboard.B, null),

                        new Square(3,  ThreePlayerChessboard.G, null),
                        new Square(2,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.E);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(8, ThreePlayerChessboard.F, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.G, null),
                        new Square(2,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.G);
        moves = new DiagonalMoves(bishop, board).horizontalLeft(new Square(8, ThreePlayerChessboard.F, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.E, null),
                        new Square(2,  ThreePlayerChessboard.D, null),
                        new Square(1,  ThreePlayerChessboard.C, null),
                        new Square(0,  ThreePlayerChessboard.B, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal3E() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.E);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2,  ThreePlayerChessboard.D, null),
                        new Square(1,  ThreePlayerChessboard.C, null),
                        new Square(0,  ThreePlayerChessboard.B, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal4D() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.D);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5,  ThreePlayerChessboard.I, null),
                        new Square(6,  ThreePlayerChessboard.J, null),
                        new Square(7,  ThreePlayerChessboard.K, null)
                )
        );
    }

    @Test
    public void testLeftHorizontal4I() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalLeft(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.J, null),
                        new Square(9,  ThreePlayerChessboard.K, null),
                        new Square(10,  ThreePlayerChessboard.L, null)
                )
        );
    }

    @Test
    public void testRightHorizontal0D() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 0, ThreePlayerChessboard.D);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,  ThreePlayerChessboard.E, null),
                        new Square(2,  ThreePlayerChessboard.F, null),
                        new Square(3,  ThreePlayerChessboard.G, null),
                        new Square(8,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 1, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(0, ThreePlayerChessboard.D, null));
        assertTrue(moves.size() == 0);

    }

    @Test
    public void testRightHorizontal1D() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 1, ThreePlayerChessboard.D);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(0,  ThreePlayerChessboard.E, null),
                        new Square(2,  ThreePlayerChessboard.E, null),
                        new Square(3,  ThreePlayerChessboard.F, null),
                        new Square(8,  ThreePlayerChessboard.G, null),
                        new Square(9,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 0, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(1, ThreePlayerChessboard.D, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2,  ThreePlayerChessboard.E, null),
                        new Square(3,  ThreePlayerChessboard.F, null),
                        new Square(8,  ThreePlayerChessboard.G, null),
                        new Square(9,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(1, ThreePlayerChessboard.D, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(0,  ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testRightHorizontal2D() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.D);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.E, null),
                        new Square(8,  ThreePlayerChessboard.F, null),
                        new Square(9,  ThreePlayerChessboard.G, null),
                        new Square(10,  ThreePlayerChessboard.H, null),

                        new Square(1,  ThreePlayerChessboard.E, null),
                        new Square(0,  ThreePlayerChessboard.F, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(2, ThreePlayerChessboard.D, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,  ThreePlayerChessboard.E, null),
                        new Square(0,  ThreePlayerChessboard.F, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 1, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(2, ThreePlayerChessboard.D, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.E, null),
                        new Square(8,  ThreePlayerChessboard.F, null),
                        new Square(9,  ThreePlayerChessboard.G, null),
                        new Square(10,  ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testRightHorizontal3H() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.H);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.G, null),
                        new Square(9,  ThreePlayerChessboard.F, null),
                        new Square(10,  ThreePlayerChessboard.E, null),
                        new Square(11,  ThreePlayerChessboard.I, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.G);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(3, ThreePlayerChessboard.H, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testRightHorizontal3G() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.G);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.H, null),
                        new Square(8,  ThreePlayerChessboard.F, null),
                        new Square(9,  ThreePlayerChessboard.E, null),
                        new Square(10,  ThreePlayerChessboard.I, null),
                        new Square(11,  ThreePlayerChessboard.J, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.H);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(3, ThreePlayerChessboard.G, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.F, null),
                        new Square(9,  ThreePlayerChessboard.E, null),
                        new Square(10,  ThreePlayerChessboard.I, null),
                        new Square(11,  ThreePlayerChessboard.J, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(3, ThreePlayerChessboard.G, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testRightHorizontal3F() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.F);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.E, null),
                        new Square(9,  ThreePlayerChessboard.I, null),
                        new Square(10,  ThreePlayerChessboard.J, null),
                        new Square(11,  ThreePlayerChessboard.K, null),

                        new Square(8,  ThreePlayerChessboard.G, null),
                        new Square(9,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.E);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(3, ThreePlayerChessboard.F, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.G, null),
                        new Square(9,  ThreePlayerChessboard.H, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.G);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(3, ThreePlayerChessboard.F, null));
        assertTrue(moves.size() == 4);

        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.E, null),
                        new Square(9,  ThreePlayerChessboard.I, null),
                        new Square(10,  ThreePlayerChessboard.J, null),
                        new Square(11,  ThreePlayerChessboard.K, null)
                )
        );
    }

    @Test
    public void testRightHorizontal11E() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.E);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10,  ThreePlayerChessboard.I, null),
                        new Square(9,  ThreePlayerChessboard.J, null),
                        new Square(8,  ThreePlayerChessboard.K, null),
                        new Square(4,  ThreePlayerChessboard.L, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(11, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testRightHorizontal10E() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.E);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11,  ThreePlayerChessboard.I, null),
                        new Square(9,  ThreePlayerChessboard.I, null),
                        new Square(8,  ThreePlayerChessboard.J, null),
                        new Square(4,  ThreePlayerChessboard.K, null),
                        new Square(5,  ThreePlayerChessboard.L, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(10, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11,  ThreePlayerChessboard.I, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(10, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9,  ThreePlayerChessboard.I, null),
                        new Square(8,  ThreePlayerChessboard.J, null),
                        new Square(4,  ThreePlayerChessboard.K, null),
                        new Square(5,  ThreePlayerChessboard.L, null)
                )
        );
    }

    @Test
    public void testRightHorizontal8L() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.L);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.K, null),
                        new Square(5,  ThreePlayerChessboard.J, null),
                        new Square(6,  ThreePlayerChessboard.I, null),
                        new Square(7,  ThreePlayerChessboard.D, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.K);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(8, ThreePlayerChessboard.L, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testRightHorizontal8K() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.K);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.L, null),
                        new Square(4,  ThreePlayerChessboard.J, null),
                        new Square(5,  ThreePlayerChessboard.I, null),
                        new Square(6,  ThreePlayerChessboard.D, null),
                        new Square(7,  ThreePlayerChessboard.C, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.L);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(8, ThreePlayerChessboard.K, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.J, null),
                        new Square(5,  ThreePlayerChessboard.I, null),
                        new Square(6,  ThreePlayerChessboard.D, null),
                        new Square(7,  ThreePlayerChessboard.C, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(8, ThreePlayerChessboard.K, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.L, null)
                )
        );
    }

    @Test
    public void testRightHorizontal8J() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.J);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.I, null),
                        new Square(5,  ThreePlayerChessboard.D, null),
                        new Square(6,  ThreePlayerChessboard.C, null),
                        new Square(7,  ThreePlayerChessboard.B, null),
                        new Square(4,  ThreePlayerChessboard.K, null),
                        new Square(5,  ThreePlayerChessboard.L, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.I);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(8, ThreePlayerChessboard.J, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.K, null),
                        new Square(5,  ThreePlayerChessboard.L, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.K);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(8, ThreePlayerChessboard.J, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testRightHorizontal7I() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 7, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6,  ThreePlayerChessboard.D, null),
                        new Square(5,  ThreePlayerChessboard.C, null),
                        new Square(4,  ThreePlayerChessboard.B, null),
                        new Square(3,  ThreePlayerChessboard.A, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 6, ThreePlayerChessboard.I);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(7, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testRightHorizontal6I(){
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 6, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(7,  ThreePlayerChessboard.D, null),
                        new Square(5,  ThreePlayerChessboard.D, null),
                        new Square(4,  ThreePlayerChessboard.C, null),
                        new Square(3,  ThreePlayerChessboard.B, null),
                        new Square(2,  ThreePlayerChessboard.A, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(6, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(7,  ThreePlayerChessboard.D, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 7, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(6, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5,  ThreePlayerChessboard.D, null),
                        new Square(4,  ThreePlayerChessboard.C, null),
                        new Square(3,  ThreePlayerChessboard.B, null),
                        new Square(2,  ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testRightHorizontal5I() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.D, null),
                        new Square(3,  ThreePlayerChessboard.C, null),
                        new Square(2,  ThreePlayerChessboard.B, null),
                        new Square(1,  ThreePlayerChessboard.A, null),

                        new Square(6,  ThreePlayerChessboard.D, null),
                        new Square(7,  ThreePlayerChessboard.C, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(5, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6,  ThreePlayerChessboard.D, null),
                        new Square(7,  ThreePlayerChessboard.C, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 7, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(5, ThreePlayerChessboard.I, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.D, null),
                        new Square(3,  ThreePlayerChessboard.C, null),
                        new Square(2,  ThreePlayerChessboard.B, null),
                        new Square(1,  ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testRightHorizontal4A() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.A);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.B, null),
                        new Square(2,  ThreePlayerChessboard.C, null),
                        new Square(1,  ThreePlayerChessboard.D, null),
                        new Square(0,  ThreePlayerChessboard.E, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.B);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(4, ThreePlayerChessboard.A, null));
        assertTrue(moves.size() == 0);


        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.A);
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        ((ThreePlayerChessboard) board).setPiece(pawn, 0, ThreePlayerChessboard.E);
        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.B, null),
                        new Square(2,  ThreePlayerChessboard.C, null),
                        new Square(1,  ThreePlayerChessboard.D, null)
                )
        );
    }

    @Test
    public void testRightHorizontal4B() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.B);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.A, null),
                        new Square(3,  ThreePlayerChessboard.C, null),
                        new Square(2,  ThreePlayerChessboard.D, null),
                        new Square(1,  ThreePlayerChessboard.E, null),
                        new Square(0,  ThreePlayerChessboard.F, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.A);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(4, ThreePlayerChessboard.B, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.C, null),
                        new Square(2,  ThreePlayerChessboard.D, null),
                        new Square(1,  ThreePlayerChessboard.E, null),
                        new Square(0,  ThreePlayerChessboard.F, null)
                )
        );


        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.C);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(4, ThreePlayerChessboard.B, null));
        assertTrue(moves.size() == 1);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testRightHorizontal4C() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.C);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.D, null),
                        new Square(2,  ThreePlayerChessboard.E, null),
                        new Square(1,  ThreePlayerChessboard.F, null),
                        new Square(0,  ThreePlayerChessboard.G, null),

                        new Square(3,  ThreePlayerChessboard.B, null),
                        new Square(2,  ThreePlayerChessboard.A, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.B);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(4, ThreePlayerChessboard.C, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.D, null),
                        new Square(2,  ThreePlayerChessboard.E, null),
                        new Square(1,  ThreePlayerChessboard.F, null),
                        new Square(0,  ThreePlayerChessboard.G, null)
                )
        );


        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.D);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(4, ThreePlayerChessboard.C, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.B, null),
                        new Square(2,  ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testRightHorizontal8I() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4,  ThreePlayerChessboard.J, null),
                        new Square(5,  ThreePlayerChessboard.K, null),
                        new Square(6,  ThreePlayerChessboard.L, null)
                )
        );
    }

    @Test
    public void testRightHorizontal4D() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.D);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,  ThreePlayerChessboard.C, null),
                        new Square(2,  ThreePlayerChessboard.B, null),
                        new Square(1,  ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testRightHorizontal4I() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.I);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.D, null),
                        new Square(6, ThreePlayerChessboard.C, null),
                        new Square(7, ThreePlayerChessboard.B, null)
                )
        );
    }


    @Test
    public void testRightHorizontal9E() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.E);
        ArrayList<Square> moves;

        moves = new DiagonalMoves(bishop, board).horizontalRight(bishop.getSquare());
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(11, ThreePlayerChessboard.J, null),
                        new Square(8, ThreePlayerChessboard.I, null),
                        new Square(4, ThreePlayerChessboard.J, null),
                        new Square(5, ThreePlayerChessboard.K, null),
                        new Square(6, ThreePlayerChessboard.L, null)
                )
        );


        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(9, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 2);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(11, ThreePlayerChessboard.J, null)
                )
        );

        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).horizontalRight(new Square(9, ThreePlayerChessboard.E, null));
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.I, null),
                        new Square(4, ThreePlayerChessboard.J, null),
                        new Square(5, ThreePlayerChessboard.K, null),
                        new Square(6, ThreePlayerChessboard.L, null)
                )
        );
    }
}