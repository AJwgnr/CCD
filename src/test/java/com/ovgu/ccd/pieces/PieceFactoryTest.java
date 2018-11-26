package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.gui.Chessboard;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PieceFactoryTest {

    @Test
    public void testBishopCreation() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.WHITE.name());
        Piece piece = PieceFactory.getPiece(board, player, Piece.PieceTypes.BISHOP);

        Assert.assertThat(piece, instanceOf(Bishop.class));


    }

    @Test
    public void testKingCreation() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.WHITE.name());
        Piece piece = PieceFactory.getPiece(board, player, Piece.PieceTypes.KING);

        Assert.assertThat(piece, instanceOf(King.class));
    }

    @Test
    public void testKnightCreation() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.WHITE.name());
        Piece piece = PieceFactory.getPiece(board, player, Piece.PieceTypes.KNIGHT);

        Assert.assertThat(piece, instanceOf(Knight.class));
    }

    @Test
    public void testPawnCreation() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.WHITE.name());
        Piece piece = PieceFactory.getPiece(board, player, Piece.PieceTypes.PAWN);

        Assert.assertThat(piece, instanceOf(Pawn.class));
    }

    @Test
    public void testRookCreation() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.WHITE.name());
        Piece piece = PieceFactory.getPiece(board, player, Piece.PieceTypes.ROOK);

        Assert.assertThat(piece, instanceOf(Rook.class));
    }

    @Test
    public void testQueenCreation() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.WHITE.name());
        Piece piece = PieceFactory.getPiece(board, player, Piece.PieceTypes.QUEEN);

        Assert.assertThat(piece, instanceOf(Queen.class));
    }
}
