package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.jchess.ThreePlayerChessboard;
import com.ovgu.ccd.moves.IBoard;
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
        board.getSquare(6, ThreePlayerChessboard.A).setPiece(pawn);

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
        board.getSquare(4, ThreePlayerChessboard.A).setPiece(pawn);

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
        board.getSquare(3, ThreePlayerChessboard.A).setPiece(pawn);

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
        board.getSquare(4, ThreePlayerChessboard.I).setPiece(pawn);

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
        board.getSquare(8, ThreePlayerChessboard.I).setPiece(pawn);

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
        board.getSquare(8, ThreePlayerChessboard.E).setPiece(pawn);

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
        board.getSquare(3, ThreePlayerChessboard.F).setPiece(pawn);

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
        board.getSquare(6, ThreePlayerChessboard.A).setPiece(pawn);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(5, ThreePlayerChessboard.A).setPiece(blockingPiece);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn6ABlockedByPieceInTwoStep() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(6, ThreePlayerChessboard.A).setPiece(pawn);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(4, ThreePlayerChessboard.A).setPiece(blockingPiece);

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
        board.getSquare(4, ThreePlayerChessboard.A).setPiece(pawn);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(3, ThreePlayerChessboard.A).setPiece(blockingPiece);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn3ABlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(3, ThreePlayerChessboard.A).setPiece(pawn);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(2, ThreePlayerChessboard.A).setPiece(blockingPiece);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn4IBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(4, ThreePlayerChessboard.I).setPiece(pawn);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(8, ThreePlayerChessboard.I).setPiece(blockingPiece);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn8IBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(8, ThreePlayerChessboard.I).setPiece(pawn);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(9, ThreePlayerChessboard.I).setPiece(blockingPiece);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn8EBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(8, ThreePlayerChessboard.E).setPiece(pawn);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(9, ThreePlayerChessboard.E).setPiece(blockingPiece);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }

    @Test
    public void testMovesIn3FBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(3, ThreePlayerChessboard.F).setPiece(pawn);

        Piece blockingPiece = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(2, ThreePlayerChessboard.F).setPiece(blockingPiece);

        assertTrue(new PawnMoves(pawn, board).moves().isEmpty());
    }
}
