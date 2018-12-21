package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
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

public class PawnMovesGreyTest {

    Player greyPlayer = new Player("John", Player.Colors.GREY.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());

    @Test
    public void testMovesIn10E() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();

        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn,10, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testMovesIn8E() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testMovesIn3E() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testMovesIn9I() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 9, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testMovesIn8K() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.K);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4, ThreePlayerChessboard.K, null)
                )
        );
    }

    @Test
    public void testMovesIn4K() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.K);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.K, null)
                )
        );
    }

    @Test
    public void testMovesIn4D() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.D);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.D, null)
                )
        );
    }

    @Test
    public void testMovesIn3D() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.D);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.D, null)
                )
        );
    }

    @Test
    public void testMovesIn10EBlockedByPiece() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 10, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(9, ThreePlayerChessboard.E).setPiece(blockingPiece);

        assertTrue(new PawnMoves(pawn, board).allGreyMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn10EBlockedByPieceInTwoStep() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 10, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 8, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testMovesIn8EBlockedByPiece() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 3, ThreePlayerChessboard.E);

        assertTrue(new PawnMoves(pawn, board).allGreyMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn9IBlockedByPiece() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 9, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 8, ThreePlayerChessboard.I);

        assertTrue(new PawnMoves(pawn, board).allGreyMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn8KBlockedByPiece() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.K);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 4, ThreePlayerChessboard.K);

        assertTrue(new PawnMoves(pawn, board).allGreyMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn4KBlockedByPiece() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.K);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 5, ThreePlayerChessboard.K);

        assertTrue(new PawnMoves(pawn, board).allGreyMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn4DBlockedByPiece() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.D);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 5, ThreePlayerChessboard.D);

        assertTrue(new PawnMoves(pawn, board).allGreyMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn3DBlockedByPiece() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.D);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 2, ThreePlayerChessboard.D);

        assertTrue(new PawnMoves(pawn, board).allGreyMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn4IBlockedByPieceOtherPlayer() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.D);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 2, ThreePlayerChessboard.D);

        assertTrue(new PawnMoves(pawn, board).allGreyMoves(true).isEmpty());
    }

    @Test
    public void testMovesIn10KEat() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 10, ThreePlayerChessboard.K);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 9, ThreePlayerChessboard.K);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 9, ThreePlayerChessboard.L);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 9, ThreePlayerChessboard.J);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.L, null),
                        new Square(9, ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testMovesIn10GEat() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 10, ThreePlayerChessboard.G);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 9, ThreePlayerChessboard.G);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 9, ThreePlayerChessboard.F);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 9, ThreePlayerChessboard.H);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.F, null),
                        new Square(9, ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testMovesIn8IEat() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 4, ThreePlayerChessboard.I);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 4, ThreePlayerChessboard.J);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 4, ThreePlayerChessboard.D);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 3, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(3, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.E, null),
                        new Square(4, ThreePlayerChessboard.D, null),
                        new Square(4, ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testMovesIn8EEat() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 3, ThreePlayerChessboard.E);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 3, ThreePlayerChessboard.F);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 3, ThreePlayerChessboard.D);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 4, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(3, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.F, null),
                        new Square(3, ThreePlayerChessboard.D, null),
                        new Square(4, ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testMovesIn5KEat() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 5, ThreePlayerChessboard.K);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 6, ThreePlayerChessboard.K);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 6, ThreePlayerChessboard.L);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 6, ThreePlayerChessboard.J);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6, ThreePlayerChessboard.L, null),
                        new Square(6, ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testMovesIn2CEat() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 2, ThreePlayerChessboard.C);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 1, ThreePlayerChessboard.C);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 1, ThreePlayerChessboard.B);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 1, ThreePlayerChessboard.D);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1, ThreePlayerChessboard.B, null),
                        new Square(1, ThreePlayerChessboard.D, null)
                )
        );
    }

    @Test
    public void testMovesIn5CEat() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 7, ThreePlayerChessboard.L);
        
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 5, ThreePlayerChessboard.C);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 6, ThreePlayerChessboard.C);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 6, ThreePlayerChessboard.B);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 6, ThreePlayerChessboard.D);

        ArrayList<Square> moves = new PawnMoves(pawn, board).allGreyMoves(true);
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
    public void testMovesIn3FWithPossibleCheck() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.G);

        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.H);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
        assertTrue(moves.size() == 0);
    }

    @Test
    public void testMovesIn3FStopCheck() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.G);

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
