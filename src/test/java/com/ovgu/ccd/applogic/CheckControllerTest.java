package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import com.ovgu.ccd.pieces.Square;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckControllerTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());
    Player greyPlayer = new Player("John", Player.Colors.GREY.name());

    @Test
    public void testNoCheckOnlyKing() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        assertTrue(new CheckController(board, king, king, null).isSafe());
    }


    @Test
    public void testCheckKingWithMoveStraightDirection() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 8, ThreePlayerChessboard.H);
        assertFalse(new CheckController(board, king, rook, new Square(3, ThreePlayerChessboard.H, rook)).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.H);

        board.setPiece(rook, 8, ThreePlayerChessboard.H);
        assertTrue(new CheckController(board, king, rook, new Square(9, ThreePlayerChessboard.H, rook)).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.H);

        board.setPiece(rook, 4, ThreePlayerChessboard.A);
        assertFalse(new CheckController(board, king, rook, new Square(3, ThreePlayerChessboard.A, rook)).isSafe());
        board.setPiece(null, 4, ThreePlayerChessboard.A);
    }

    @Test
    public void testCheckKingWithoutMoveStraightDirection() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.H);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.H);
        board.setPiece(rook, 3, ThreePlayerChessboard.A);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.A);
        board.setPiece(rook, 11, ThreePlayerChessboard.F);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 11, ThreePlayerChessboard.F);
        board.setPiece(rook, 0, ThreePlayerChessboard.F);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 0, ThreePlayerChessboard.F);

        Piece queen = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.QUEEN);
        board.setPiece(queen, 3, ThreePlayerChessboard.H);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.H);
        board.setPiece(queen, 3, ThreePlayerChessboard.A);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.A);
        board.setPiece(queen, 11, ThreePlayerChessboard.F);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 11, ThreePlayerChessboard.F);
        board.setPiece(queen, 0, ThreePlayerChessboard.F);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 0, ThreePlayerChessboard.F);

        Piece king2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setPiece(king2, 3, ThreePlayerChessboard.E);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.E);
        board.setPiece(king2, 3, ThreePlayerChessboard.G);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.G);
        board.setPiece(king2, 8, ThreePlayerChessboard.F);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.F);
        board.setPiece(king2, 2, ThreePlayerChessboard.F);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 2, ThreePlayerChessboard.F);

        board.setPiece(king2, 3, ThreePlayerChessboard.D);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.D);
        board.setPiece(king2, 3, ThreePlayerChessboard.H);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.H);
        board.setPiece(king2, 9, ThreePlayerChessboard.F);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 9, ThreePlayerChessboard.F);
        board.setPiece(king2, 1, ThreePlayerChessboard.F);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 1, ThreePlayerChessboard.F);

        Piece rook2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook2, 3, ThreePlayerChessboard.H);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.H);
        board.setPiece(rook2, 3, ThreePlayerChessboard.A);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.A);
        board.setPiece(rook2, 11, ThreePlayerChessboard.F);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 11, ThreePlayerChessboard.F);
        board.setPiece(rook2, 0, ThreePlayerChessboard.F);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 0, ThreePlayerChessboard.F);
    }

    @Test
    public void testCheckKingWithMoveDiagonalDirection() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        //bishop
        Piece bishop = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.BISHOP);
        board.setPiece(bishop, 9, ThreePlayerChessboard.F);
        assertFalse(new CheckController(board, king, bishop, new Square(8, ThreePlayerChessboard.E, bishop)).isSafe());
        board.setPiece(null, 9, ThreePlayerChessboard.F);

        //queen
        Piece queen = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.QUEEN);
        board.setPiece(queen, 5, ThreePlayerChessboard.J);
        assertFalse(new CheckController(board, king, queen, new Square(8, ThreePlayerChessboard.E, null)).isSafe());
        board.setPiece(null, 5, ThreePlayerChessboard.J);
    }

    @Test
    public void testCheckKingWithoutMoveDiagonalDirection() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        //bishop
        Piece bishop = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.BISHOP);
        board.setPiece(bishop, 8, ThreePlayerChessboard.E);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.E);
        board.setPiece(bishop, 2, ThreePlayerChessboard.E);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 2, ThreePlayerChessboard.E);
        board.setPiece(bishop, 8, ThreePlayerChessboard.G);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.G);
        board.setPiece(bishop, 2, ThreePlayerChessboard.G);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 2, ThreePlayerChessboard.G);
        board.setPiece(bishop, 11, ThreePlayerChessboard.K);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 11, ThreePlayerChessboard.K);
        board.setPiece(bishop, 1, ThreePlayerChessboard.H);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 1, ThreePlayerChessboard.H);
        board.setPiece(bishop, 0, ThreePlayerChessboard.C);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 0, ThreePlayerChessboard.C);
        board.setPiece(bishop, 9, ThreePlayerChessboard.H);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 9, ThreePlayerChessboard.H);

        //queen
        Piece queen = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.QUEEN);
        board.setPiece(queen, 8, ThreePlayerChessboard.E);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.E);
        board.setPiece(queen, 2, ThreePlayerChessboard.E);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 2, ThreePlayerChessboard.E);
        board.setPiece(queen, 8, ThreePlayerChessboard.G);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.G);
        board.setPiece(queen, 2, ThreePlayerChessboard.G);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 2, ThreePlayerChessboard.G);
        board.setPiece(queen, 11, ThreePlayerChessboard.K);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 11, ThreePlayerChessboard.K);
        board.setPiece(queen, 1, ThreePlayerChessboard.H);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 1, ThreePlayerChessboard.H);
        board.setPiece(queen, 0, ThreePlayerChessboard.C);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 0, ThreePlayerChessboard.C);
        board.setPiece(queen, 9, ThreePlayerChessboard.H);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 9, ThreePlayerChessboard.H);

        //king
        Piece king2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setPiece(king2, 2, ThreePlayerChessboard.E);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 2, ThreePlayerChessboard.E);
        board.setPiece(king2, 8, ThreePlayerChessboard.G);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.G);
        board.setPiece(king2, 8, ThreePlayerChessboard.G);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.G);
        board.setPiece(king2, 2, ThreePlayerChessboard.G);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 2, ThreePlayerChessboard.G);

        board.setPiece(king2, 9, ThreePlayerChessboard.I);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 9, ThreePlayerChessboard.I);
        board.setPiece(king2, 1, ThreePlayerChessboard.D);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 1, ThreePlayerChessboard.D);
        board.setPiece(king2, 1, ThreePlayerChessboard.H);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 1, ThreePlayerChessboard.H);
        board.setPiece(king2, 9, ThreePlayerChessboard.H);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 9, ThreePlayerChessboard.H);
    }

    @Test
    public void testCheckKingWithMoveKnightDirection() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 4, ThreePlayerChessboard.I);

        Piece knight = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KNIGHT);
        board.setPiece(knight, 0, ThreePlayerChessboard.B);
        assertFalse(new CheckController(board, king, knight, new Square(3, ThreePlayerChessboard.C, null)).isSafe());
        board.setPiece(null, 0, ThreePlayerChessboard.B);
    }

    @Test
    public void testCheckKingWithoutMoveKnightDirection() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 4, ThreePlayerChessboard.I);

        Piece knight = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KNIGHT);
        board.setPiece(knight, 3, ThreePlayerChessboard.C);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.C);
        board.setPiece(knight, 2, ThreePlayerChessboard.D);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 2, ThreePlayerChessboard.D);
        board.setPiece(knight, 5, ThreePlayerChessboard.C);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 5, ThreePlayerChessboard.C);
        board.setPiece(knight, 6, ThreePlayerChessboard.D);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 6, ThreePlayerChessboard.D);
        board.setPiece(knight, 6, ThreePlayerChessboard.J);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 6, ThreePlayerChessboard.J);
        board.setPiece(knight, 8, ThreePlayerChessboard.K);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.K);
        board.setPiece(knight, 8, ThreePlayerChessboard.F);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 8, ThreePlayerChessboard.F);
        board.setPiece(knight, 9, ThreePlayerChessboard.J);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 9, ThreePlayerChessboard.J);
        board.setPiece(knight, 9, ThreePlayerChessboard.E);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 9, ThreePlayerChessboard.E);
    }

    @Test
    public void testCheckKingWithoutMovePawnDirection() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 2, ThreePlayerChessboard.E);

        Piece blackPawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Piece whitePawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        Piece greyPawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);

        board.blackPawns.add(blackPawn);
        board.whitePawns.add(whitePawn);
        board.greyPawns.add(greyPawn);

        board.setPiece(blackPawn, 3, ThreePlayerChessboard.D);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.D);

        board.setPiece(blackPawn, 3, ThreePlayerChessboard.F);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.F);

        board.setPiece(greyPawn, 3, ThreePlayerChessboard.D);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.D);

        board.setPiece(greyPawn, 3, ThreePlayerChessboard.F);
        assertFalse(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.F);

        board.setPiece(whitePawn, 3, ThreePlayerChessboard.F);
        assertTrue(new CheckController(board, king, king, null).isSafe());
        board.setPiece(null, 3, ThreePlayerChessboard.F);
    }

    @Test
    public void testCheckKingWithMovePawnDirection() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 2, ThreePlayerChessboard.E);

        Piece blackPawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Piece whitePawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        Piece greyPawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);

        board.blackPawns.add(blackPawn);
        board.whitePawns.add(whitePawn);
        board.greyPawns.add(greyPawn);

        board.setPiece(blackPawn, 4, ThreePlayerChessboard.D);
        assertFalse(new CheckController(board, king, blackPawn, new Square(3, ThreePlayerChessboard.D, null)).isSafe());
        board.setPiece(null, 4, ThreePlayerChessboard.D);

        board.setPiece(greyPawn, 4, ThreePlayerChessboard.D);
        assertFalse(new CheckController(board, king, greyPawn, new Square(3, ThreePlayerChessboard.D, null)).isSafe());
        board.setPiece(null, 4, ThreePlayerChessboard.D);
    }

    @Test
    public void test() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.G);

        Piece rookBlack = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rookBlack, 3, ThreePlayerChessboard.H);

        assertFalse(new CheckController(board, king, rook, new Square(2, ThreePlayerChessboard.G, null)).isSafe());
        assertFalse(new CheckController(board, king, rook, new Square(1, ThreePlayerChessboard.G, null)).isSafe());
        assertFalse(new CheckController(board, king, rook, new Square(0, ThreePlayerChessboard.G, null)).isSafe());
        assertFalse(new CheckController(board, king, rook, new Square(8, ThreePlayerChessboard.G, null)).isSafe());
        assertFalse(new CheckController(board, king, rook, new Square(9, ThreePlayerChessboard.G, null)).isSafe());
        assertFalse(new CheckController(board, king, rook, new Square(10, ThreePlayerChessboard.G, null)).isSafe());
        assertFalse(new CheckController(board, king, rook, new Square(11, ThreePlayerChessboard.G, null)).isSafe());
    }

    @Test
    public void test2() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.G);

        Piece rookBlack = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rookBlack, 3, ThreePlayerChessboard.H);

        assertTrue(new CheckController(board, king, rook, new Square(3, ThreePlayerChessboard.H, null)).isSafe());
    }
}
