package com.ovgu.ccd;

import com.ovgu.ccd.jchess.Pawn;
import com.ovgu.ccd.jchess.Chessboard;
import com.ovgu.ccd.jchess.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PawnTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.white.name());
        Pawn pawn = new Pawn(board, player);

        assertEquals(Pawn.imageWhite, pawn.image);
        assertEquals(Pawn.imageWhite, pawn.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        Pawn pawn = new Pawn(board, player);

        assertEquals(Pawn.imageBlack, pawn.image);
        assertEquals(Pawn.imageBlack, pawn.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.colors.black.name());
        Pawn pawn = new Pawn(board, player);

        assertEquals("", pawn.getSymbol());
    }
}