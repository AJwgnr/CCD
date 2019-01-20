package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import com.ovgu.ccd.pieces.Square;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertTrue;

public class PawnMovesWhiteTest {
    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());

    @Test
    public void testMovesInSquare1A() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 1, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.A, null),
                        new Square(3, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMovesInSquare3A() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMovesInSquare4A() throws Exception {
        IBoard board = new ThreePlayerChessboard();

        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMovesInSquare2H() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 2, ThreePlayerChessboard.H);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testMovesInSquare3H() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.H);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testMovesInSquare8I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testMovesInSquare4I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn,4, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testMovesInSquare7I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 7, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(0, moves.size());
    }


    @Test
    public void testMovesInSquare11J() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 11, ThreePlayerChessboard.J);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(0, moves.size());
    }


    @Test
    public void testMovesInSquare1ABlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);


        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 1, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 2, ThreePlayerChessboard.A);

        assertTrue(new PawnMoves(pawn, board).allWhiteMoves(true).isEmpty());
    }

    @Test
    public void testMovesInSquare1ABlockedByPieceInTwoStep() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);


        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 1, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 3, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMovesInSquare3ABlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();

        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 4, ThreePlayerChessboard.A);

        assertTrue(new PawnMoves(pawn, board).allWhiteMoves(true).isEmpty());
    }

    @Test
    public void testMovesInSquare2HBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);


        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 2, ThreePlayerChessboard.H);

        Piece blockingPiece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 3, ThreePlayerChessboard.H);

        assertTrue(new PawnMoves(pawn, board).allWhiteMoves(true).isEmpty());
    }

    @Test
    public void testMovesInSquare3HBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);


        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.H);

        Piece blockingPiece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 8, ThreePlayerChessboard.H);

        assertTrue(new PawnMoves(pawn, board).allWhiteMoves(true).isEmpty());
    }

    @Test
    public void testMovesInSquare8IBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();

        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 9, ThreePlayerChessboard.I);

        assertTrue(new PawnMoves(pawn, board).allWhiteMoves(true).isEmpty());
    }

    @Test
    public void testMovesInSquare4IBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 5, ThreePlayerChessboard.I);

        assertTrue(new PawnMoves(pawn, board).allWhiteMoves(true).isEmpty());
    }


    @Test
    public void testMovesInSquare4IBlockedByPieceOtherPlayer() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 5, ThreePlayerChessboard.I);

        assertTrue(new PawnMoves(pawn, board).allWhiteMoves(true).isEmpty());
    }

    @Test
    public void testMovesInSquare1EEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 1, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 2, ThreePlayerChessboard.E);
        Piece eatPiece1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 2, ThreePlayerChessboard.D);
        Piece eatPiece2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 2, ThreePlayerChessboard.F);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.D, null),
                        new Square(2, ThreePlayerChessboard.F, null)
                )
        );
    }


    @Test
    public void testMovesInSquare9EEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 9, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 10, ThreePlayerChessboard.E);
        Piece eatPiece1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 10, ThreePlayerChessboard.I);
        Piece eatPiece2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 10, ThreePlayerChessboard.F);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(10, ThreePlayerChessboard.F, null)
                )
        );
    }



    @Test
    public void testMovesInSquare9FEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 9, ThreePlayerChessboard.F);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 10, ThreePlayerChessboard.F);
        Piece eatPiece1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 10, ThreePlayerChessboard.E);
        Piece eatPiece2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 10, ThreePlayerChessboard.G);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.E, null),
                        new Square(10, ThreePlayerChessboard.G, null)
                )
        );
    }



    @Test
    public void testMovesInSquare9IEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 9, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 10, ThreePlayerChessboard.I);
        Piece eatPiece1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 10, ThreePlayerChessboard.J);
        Piece eatPiece2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 10, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.E, null),
                        new Square(10, ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testMovesInSquare5CEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 5, ThreePlayerChessboard.C);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 6, ThreePlayerChessboard.C);
        Piece eatPiece1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 6, ThreePlayerChessboard.B);
        Piece eatPiece2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 6, ThreePlayerChessboard.D);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6, ThreePlayerChessboard.B, null),
                        new Square(6, ThreePlayerChessboard.D, null)
                )
        );
    }


    @Test
    public void testMovesInSquare5DEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 5, ThreePlayerChessboard.D);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 6, ThreePlayerChessboard.D);
        Piece eatPiece1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 6, ThreePlayerChessboard.C);
        Piece eatPiece2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 6, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6, ThreePlayerChessboard.I, null),
                        new Square(6, ThreePlayerChessboard.C, null)
                )
        );
    }


    @Test
    public void testMovesInSquare5IEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 5, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 6, ThreePlayerChessboard.I);
        Piece eatPiece1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 6, ThreePlayerChessboard.D);
        Piece eatPiece2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 6, ThreePlayerChessboard.J);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6, ThreePlayerChessboard.D, null),
                        new Square(6, ThreePlayerChessboard.J, null)
                )
        );
    }


    @Test
    public void testMovesInSquare3EEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 8, ThreePlayerChessboard.E);
        Piece eatPiece1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 4, ThreePlayerChessboard.D);
        Piece eatPiece2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 8, ThreePlayerChessboard.I);
        Piece eatPiece3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece3, 8, ThreePlayerChessboard.F);


        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(3, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4, ThreePlayerChessboard.D, null),
                        new Square(8, ThreePlayerChessboard.I, null),
                        new Square(8, ThreePlayerChessboard.F, null)
                )
        );
    }

    @Test
    public void testMovesInSquare3DEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.D);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 4, ThreePlayerChessboard.D);
        Piece eatPiece1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 4, ThreePlayerChessboard.C);
        Piece eatPiece2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 4, ThreePlayerChessboard.I);
        Piece eatPiece3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece3, 8, ThreePlayerChessboard.E);


        ArrayList<Square> moves = new PawnMoves(pawn, board).allWhiteMoves(true);
        Assert.assertEquals(3, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4, ThreePlayerChessboard.C, null),
                        new Square(4, ThreePlayerChessboard.I, null),
                        new Square(8, ThreePlayerChessboard.E, null)
                )
        );
    }


    @Test
    public void testMovesInSquare3FWithPossibleCheck() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.G);

        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.H);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testMovesInSquare3FStopCheck() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 2, ThreePlayerChessboard.G);

        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.H);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.G, null),
                        new Square(3, ThreePlayerChessboard.H, null)
                )
        );
    }
}
