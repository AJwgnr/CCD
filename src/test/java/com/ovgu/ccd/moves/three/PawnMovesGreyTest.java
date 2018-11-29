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

public class PawnMovesGreyTest {

    Player greyPlayer = new Player("John", Player.Colors.GREY.name());

    @Test
    public void testMovesIn10E() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece pawn = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.PAWN);
        board.getSquare(10, ThreePlayerChessboard.E).setPiece(pawn);

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
        board.getSquare(8, ThreePlayerChessboard.E).setPiece(pawn);

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
        board.getSquare(3, ThreePlayerChessboard.E).setPiece(pawn);

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
        board.getSquare(9, ThreePlayerChessboard.I).setPiece(pawn);

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
        board.getSquare(8, ThreePlayerChessboard.K).setPiece(pawn);

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
        board.getSquare(4, ThreePlayerChessboard.K).setPiece(pawn);

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
        board.getSquare(4, ThreePlayerChessboard.D).setPiece(pawn);

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
        board.getSquare(3, ThreePlayerChessboard.D).setPiece(pawn);

        ArrayList<Square> moves = new PawnMoves(pawn, board).moves();
        Assert.assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.D, null)
                )
        );
    }
}
