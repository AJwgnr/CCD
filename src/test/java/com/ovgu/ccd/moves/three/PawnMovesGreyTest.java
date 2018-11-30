package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
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

    @Test
    public void testMovesIn10E() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn,10, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 9, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.K);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.K);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.D);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.D);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 10, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(9, ThreePlayerChessboard.E).setPiece(blockingPiece);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn10EBlockedByPieceInTwoStep() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 10, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 8, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 3, ThreePlayerChessboard.E);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn9IBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 9, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 8, ThreePlayerChessboard.I);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn8KBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.K);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 4, ThreePlayerChessboard.K);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn4KBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.K);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 5, ThreePlayerChessboard.K);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn4DBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.D);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 5, ThreePlayerChessboard.D);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn3DBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.D);

        Piece blockingPiece = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 2, ThreePlayerChessboard.D);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }
}
