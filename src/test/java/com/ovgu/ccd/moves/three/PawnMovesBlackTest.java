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

public class PawnMovesBlackTest {

    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());
    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());

    @Test
    public void testMovesIn6A() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 6, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.A, null),
                        new Square(4, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMovesIn4A() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMovesIn3A() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMovesIn4I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testMovesIn8I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testMovesIn8E() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testMovesIn3F() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.F);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.F, null)
                )
        );
    }

    @Test
    public void testMovesIn6ABlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 6, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 5, ThreePlayerChessboard.A);

        assertTrue(new PawnMoves(pawn, board).allBlackMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn6ABlockedByPieceInTwoStep() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 6, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 4, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMovesIn4ABlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 3, ThreePlayerChessboard.A);

        assertTrue(new PawnMoves(pawn, board).allBlackMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn3ABlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 2, ThreePlayerChessboard.A);

        assertTrue(new PawnMoves(pawn, board).allBlackMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn4IBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 8, ThreePlayerChessboard.I);

        assertTrue(new PawnMoves(pawn, board).allBlackMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn8IBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 9, ThreePlayerChessboard.I);

        assertTrue(new PawnMoves(pawn, board).allBlackMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn8EBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 9, ThreePlayerChessboard.E);

        assertTrue(new PawnMoves(pawn, board).allBlackMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn3FBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.F);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 2, ThreePlayerChessboard.F);

        assertTrue(new PawnMoves(pawn, board).allBlackMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn3FBlockedByPieceOtherPlayer() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.F);

        Piece blockingPiece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 2, ThreePlayerChessboard.F);

        assertTrue(new PawnMoves(pawn, board).allBlackMoves(true).isEmpty());
    }


    @Test
    public void testMovesIn6IEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 6, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 5, ThreePlayerChessboard.I);
        Piece eatPiece1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 5, ThreePlayerChessboard.D);
        Piece eatPiece2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 5, ThreePlayerChessboard.J);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.D, null),
                        new Square(5, ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testMovesIn6DEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 6, ThreePlayerChessboard.D);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 5, ThreePlayerChessboard.D);
        Piece eatPiece1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 5, ThreePlayerChessboard.I);
        Piece eatPiece2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 5, ThreePlayerChessboard.C);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.I, null),
                        new Square(5, ThreePlayerChessboard.C, null)
                )
        );
    }

    @Test
    public void testMovesIn4IEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 8, ThreePlayerChessboard.I);
        Piece eatPiece1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 8, ThreePlayerChessboard.E);
        Piece eatPiece2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 8, ThreePlayerChessboard.J);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testMovesIn4DEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.D);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 3, ThreePlayerChessboard.D);
        Piece eatPiece1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 3, ThreePlayerChessboard.C);
        Piece eatPiece2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 3, ThreePlayerChessboard.E);
        Piece eatPiece3 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece3, 8, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(3, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.E, null),
                        new Square(3, ThreePlayerChessboard.C, null),
                        new Square(8, ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testMovesIn10KEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 10, ThreePlayerChessboard.K);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 11, ThreePlayerChessboard.K);
        Piece eatPiece1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 11, ThreePlayerChessboard.J);
        Piece eatPiece2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 11, ThreePlayerChessboard.L);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11, ThreePlayerChessboard.L, null),
                        new Square(11, ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testMovesIn2GEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 2, ThreePlayerChessboard.G);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 1, ThreePlayerChessboard.G);
        Piece eatPiece1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 1, ThreePlayerChessboard.F);
        Piece eatPiece2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 1, ThreePlayerChessboard.H);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1, ThreePlayerChessboard.H, null),
                        new Square(1, ThreePlayerChessboard.F, null)
                )
        );
    }

    @Test
    public void testMovesIn8IEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 9, ThreePlayerChessboard.I);
        Piece eatPiece1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 9, ThreePlayerChessboard.J);
        Piece eatPiece2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 9, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(9, ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testMovesIn8EEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 0, ThreePlayerChessboard.A);
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 9, ThreePlayerChessboard.E);
        Piece eatPiece1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece1, 9, ThreePlayerChessboard.I);
        Piece eatPiece2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(eatPiece2, 9, ThreePlayerChessboard.F);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allBlackMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.I, null),
                        new Square(9, ThreePlayerChessboard.F, null)
                )
        );
    }


    @Test
    public void testMovesIn3CWithPossibleCheck() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 3, ThreePlayerChessboard.C);

        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.B);

        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testMovesIn3CStopCheck() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) king);
        board.setPiece(king, 3, ThreePlayerChessboard.C);

        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.B);

        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.B, null),
                        new Square(3, ThreePlayerChessboard.A, null)
                )
        );
    }
}
