package com.ovgu.ccd.pieces;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.applogic.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BishopTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.WHITE.name());
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, player, Piece.PieceTypes.BISHOP);

        assertEquals(Bishop.imageWhite, bishop.image);
        assertEquals(Bishop.imageWhite, bishop.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.BLACK.name());
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, player, Piece.PieceTypes.BISHOP);

        assertEquals(Bishop.imageBlack, bishop.image);
        assertEquals(Bishop.imageBlack, bishop.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.BLACK.name());
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, player, Piece.PieceTypes.BISHOP);

        assertEquals("B", bishop.getSymbol());
    }
}
