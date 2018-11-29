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

public class PawnMoveWhiteTest {
    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());

    @Test
    public void testMovesIn1A() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(1, ThreePlayerChessboard.A).setPiece(pawn);

        ArrayList<Square> moves = new PawnMove(pawn, board).moves();
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
    public void testMovesIn3A() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(3, ThreePlayerChessboard.A).setPiece(pawn);

        ArrayList<Square> moves = new PawnMove(pawn, board).moves();
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMovesIn4A() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(4, ThreePlayerChessboard.A).setPiece(pawn);

        ArrayList<Square> moves = new PawnMove(pawn, board).moves();
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMovesIn2H() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(2, ThreePlayerChessboard.H).setPiece(pawn);

        ArrayList<Square> moves = new PawnMove(pawn, board).moves();
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testMovesIn3H() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(3, ThreePlayerChessboard.H).setPiece(pawn);

        ArrayList<Square> moves = new PawnMove(pawn, board).moves();
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testMovesIn8I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(8, ThreePlayerChessboard.I).setPiece(pawn);

        ArrayList<Square> moves = new PawnMove(pawn, board).moves();
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testMovesIn4I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(4, ThreePlayerChessboard.I).setPiece(pawn);

        ArrayList<Square> moves = new PawnMove(pawn, board).moves();
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.I, null)
                )
        );
    }
}
