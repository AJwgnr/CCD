package com.ovgu.ccd;


import com.ovgu.ccd.jchess.Knight;
import com.ovgu.ccd.jchess.Chessboard;
import com.ovgu.ccd.jchess.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class KnightTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.white.name());
        Knight knight = new Knight(board, player);

        assertEquals(Knight.imageWhite, knight.image);
        assertEquals(Knight.imageWhite, knight.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        Knight knight = new Knight(board, player);

        assertEquals(Knight.imageBlack, knight.image);
        assertEquals(Knight.imageBlack, knight.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        Knight knight = new Knight(board, player);

        assertEquals("N", knight.getSymbol());
    }
}
