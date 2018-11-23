package com.ovgu.ccd.pieces;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.applogic.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class RookTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.white.name());
        Rook rook = new Rook(board, player);

        assertEquals(Rook.imageWhite, rook.image);
        assertEquals(Rook.imageWhite, rook.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.black.name());
        Rook rook = new Rook(board, player);

        assertEquals(Rook.imageBlack, rook.image);
        assertEquals(Rook.imageBlack, rook.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.black.name());
        Rook rook = new Rook(board, player);

        assertEquals("R", rook.getSymbol());
    }
}