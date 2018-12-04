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

public class PawnMovesBlackTest {

    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());


    @Test
    public void testMovesIn6A() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 6, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.I);

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
    public void testMovesIn8I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.I);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.E);

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
    public void testMovesIn3F() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.F);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 6, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 5, ThreePlayerChessboard.A);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn6ABlockedByPieceInTwoStep() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 6, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 4, ThreePlayerChessboard.A);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
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
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 3, ThreePlayerChessboard.A);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn3ABlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.A);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 2, ThreePlayerChessboard.A);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn4IBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 4, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 8, ThreePlayerChessboard.I);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn8IBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.I);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 9, ThreePlayerChessboard.I);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn8EBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 8, ThreePlayerChessboard.E);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 9, ThreePlayerChessboard.E);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn3FBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn, 3, ThreePlayerChessboard.F);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(blockingPiece, 2, ThreePlayerChessboard.F);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }
}
