package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import com.ovgu.ccd.pieces.Square;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ThreePlayerChessTest {
    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());
    Player greyPlayer = new Player("John", Player.Colors.GREY.name());

    @Test
    public void testGetLeftSextantSquare3E() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.E);

        assertTrue(
                board.getLeftSextantSquare(new Square(3, ThreePlayerChessboard.E, null)).equals(
                        new Square(4, ThreePlayerChessboard.D, null))
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
                        new Square(3, ThreePlayerChessboard.E, null))
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

    @Test
    public void testWhiteCastlingLeft() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.E);
        board.setPiece(rook, 0, ThreePlayerChessboard.A);

        board.setPiece(king, 0, ThreePlayerChessboard.B);

        assertTrue(rook.getSquare().equals(new Square(0, ThreePlayerChessboard.C, null)));
        assertTrue(king.getSquare().equals(new Square(0, ThreePlayerChessboard.B, null)));
        assertTrue(board.getSquare(0, ThreePlayerChessboard.A).getPiece() == null);
    }

    @Test
    public void testWhiteCastlingRight() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.E);
        board.setPiece(rook, 0, ThreePlayerChessboard.H);

        board.setPiece(king, 0, ThreePlayerChessboard.G);

        assertTrue(rook.getSquare().equals(new Square(0, ThreePlayerChessboard.F, null)));
        assertTrue(king.getSquare().equals(new Square(0, ThreePlayerChessboard.G, null)));
        assertTrue(board.getSquare(0, ThreePlayerChessboard.H).getPiece() == null);
    }

    @Test
    public void testBlackCastlingLeft() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.D);
        board.setPiece(rook, 7, ThreePlayerChessboard.L);

        board.setPiece(king, 7, ThreePlayerChessboard.K);

        assertTrue(rook.getSquare().equals(new Square(7, ThreePlayerChessboard.J, null)));
        assertTrue(king.getSquare().equals(new Square(7, ThreePlayerChessboard.K, null)));
        assertTrue(board.getSquare(7, ThreePlayerChessboard.L).getPiece() == null);
    }

    @Test
    public void testBlackCastlingRight() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.D);
        board.setPiece(rook, 7, ThreePlayerChessboard.A);

        board.setPiece(king, 7, ThreePlayerChessboard.B);

        assertTrue(rook.getSquare().equals(new Square(7, ThreePlayerChessboard.C, null)));
        assertTrue(king.getSquare().equals(new Square(7, ThreePlayerChessboard.B, null)));
        assertTrue(board.getSquare(7, ThreePlayerChessboard.A).getPiece() == null);
    }

    @Test
    public void testGreyCastlingLeft() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.ROOK);
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 11, ThreePlayerChessboard.I);
        board.setPiece(rook, 11, ThreePlayerChessboard.H);

        board.setPiece(king, 11, ThreePlayerChessboard.G);

        assertTrue(rook.getSquare().equals(new Square(11, ThreePlayerChessboard.F, null)));
        assertTrue(king.getSquare().equals(new Square(11, ThreePlayerChessboard.G, null)));
        assertTrue(board.getSquare(11, ThreePlayerChessboard.H).getPiece() == null);
    }

    @Test
    public void testGreyCastlingRight() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.ROOK);
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 11, ThreePlayerChessboard.I);
        board.setPiece(rook, 11, ThreePlayerChessboard.L);

        board.setPiece(king, 11, ThreePlayerChessboard.K);

        assertTrue(rook.getSquare().equals(new Square(11, ThreePlayerChessboard.J, null)));
        assertTrue(king.getSquare().equals(new Square(11, ThreePlayerChessboard.K, null)));
        assertTrue(board.getSquare(11, ThreePlayerChessboard.L).getPiece() == null);
    }
}
