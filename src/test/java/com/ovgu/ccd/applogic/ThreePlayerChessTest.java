package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import com.ovgu.ccd.pieces.Square;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ThreePlayerChessTest {
    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());

    @Test
    public void testGetLeftSextantSquare3E() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.E);

        assertTrue(
                board.getLeftSextantSquare(new Square(3, ThreePlayerChessboard.E, null)).equals(
                        new Square(2, ThreePlayerChessboard.D, null))
        );
    }

    @Test
    public void testGetLeftSextantSquare3C() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.C);

        assertTrue(
                board.getLeftSextantSquare(new Square(3, ThreePlayerChessboard.C, null)).equals(
                        new Square(4, ThreePlayerChessboard.B, null))
        );
    }

    @Test
    public void testGetLeftSextantSquare5D() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 5, ThreePlayerChessboard.D);

        assertTrue(
                board.getLeftSextantSquare(new Square(5, ThreePlayerChessboard.D, null)).equals(
                        new Square(6, ThreePlayerChessboard.I, null))
        );
    }

    @Test
    public void testGetLeftSextantSquare4J() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.J);

        assertTrue(
                board.getLeftSextantSquare(new Square(4, ThreePlayerChessboard.J, null)).equals(
                        new Square(8, ThreePlayerChessboard.K, null))
        );
    }

    @Test
    public void testGetLeftSextantSquare9I() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 9, ThreePlayerChessboard.I);

        assertTrue(
                board.getLeftSextantSquare(new Square(9, ThreePlayerChessboard.I, null)).equals(
                        new Square(10, ThreePlayerChessboard.E, null))
        );
    }

    @Test
    public void testGetLeftSextantSquare8F() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.F);

        assertTrue(
                board.getLeftSextantSquare(new Square(8, ThreePlayerChessboard.F, null)).equals(
                        new Square(3, ThreePlayerChessboard.G, null))
        );
    }

    @Test
    public void testGetRightSextantSquare2D() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 2, ThreePlayerChessboard.D);

        assertTrue(
                board.getRightSextantSquare(new Square(2, ThreePlayerChessboard.D, null)).equals(
                        new Square(1, ThreePlayerChessboard.E, null))
        );
    }

    @Test
    public void testGetRightSextantSquare3G() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.G);

        assertTrue(
                board.getRightSextantSquare(new Square(3, ThreePlayerChessboard.G, null)).equals(
                        new Square(8, ThreePlayerChessboard.H, null))
        );
    }

    @Test
    public void testGetRightSextantSquare9E() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 9, ThreePlayerChessboard.E);

        assertTrue(
                board.getRightSextantSquare(new Square(9, ThreePlayerChessboard.E, null)).equals(
                        new Square(10, ThreePlayerChessboard.I, null))
        );
    }

    @Test
    public void testGetRightSextantSquare8K() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.K);

        assertTrue(
                board.getRightSextantSquare(new Square(8, ThreePlayerChessboard.K, null)).equals(
                        new Square(4, ThreePlayerChessboard.L, null))
        );
    }

    @Test
    public void testGetRightSextantSquare5I() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 5, ThreePlayerChessboard.I);

        assertTrue(
                board.getRightSextantSquare(new Square(5, ThreePlayerChessboard.I, null)).equals(
                        new Square(6, ThreePlayerChessboard.D, null))
        );
    }

    @Test
    public void testGetRightSextantSquare4D() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.D);

        assertTrue(
                board.getRightSextantSquare(new Square(4, ThreePlayerChessboard.D, null)).equals(
                        new Square(3, ThreePlayerChessboard.C, null))
        );
    }
}
