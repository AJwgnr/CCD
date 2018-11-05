package com.ovgu.ccd;

import com.ovgu.ccd.jchess.King;
import com.ovgu.ccd.jchess.Chessboard;
import com.ovgu.ccd.jchess.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class KingTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.white.name());
        King king = new King(board, player);

        assertEquals(King.imageWhite, king.image);
        assertEquals(King.imageWhite, king.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        King king = new King(board, player);

        assertEquals(King.imageBlack, king.image);
        assertEquals(King.imageBlack, king.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        King king = new King(board, player);

        assertEquals("K", king.getSymbol());
    }
}
