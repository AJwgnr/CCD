package com.ovgu.ccd;

import com.ovgu.ccd.jchess.Bishop;
import com.ovgu.ccd.jchess.Chessboard;
import com.ovgu.ccd.jchess.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BishopTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.white.name());
        Bishop bishop = new Bishop(board, player);

        assertEquals(Bishop.imageWhite, bishop.image);
        assertEquals(Bishop.imageWhite, bishop.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        Bishop bishop = new Bishop(board, player);

        assertEquals(Bishop.imageBlack, bishop.image);
        assertEquals(Bishop.imageBlack, bishop.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        Bishop bishop = new Bishop(board, player);

        assertEquals("B", bishop.getSymbol());
    }
}
