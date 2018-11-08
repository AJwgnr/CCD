package com.ovgu.ccd;

import com.ovgu.ccd.pieces.Rook;
import com.ovgu.ccd.view.Chessboard;
import com.ovgu.ccd.view.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RookTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.white.name());
        Rook rook = new Rook(board, player);

        assertEquals(Rook.imageWhite, rook.image);
        assertEquals(Rook.imageWhite, rook.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        Rook rook = new Rook(board, player);

        assertEquals(Rook.imageBlack, rook.image);
        assertEquals(Rook.imageBlack, rook.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        Rook rook = new Rook(board, player);

        assertEquals("R", rook.getSymbol());
    }
}