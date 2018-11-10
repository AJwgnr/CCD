package com.ovgu.ccd;

import com.ovgu.ccd.pieces.Queen;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class QueenTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.white.name());
        Queen queen = new Queen(board, player);

        assertEquals(Queen.imageWhite, queen.image);
        assertEquals(Queen.imageWhite, queen.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        Queen queen = new Queen(board, player);

        assertEquals(Queen.imageBlack, queen.image);
        assertEquals(Queen.imageBlack, queen.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        Queen queen = new Queen(board, player);

        assertEquals("Q", queen.getSymbol());
    }
}