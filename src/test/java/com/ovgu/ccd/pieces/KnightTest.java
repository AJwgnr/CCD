package com.ovgu.ccd.pieces;


import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.applogic.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class KnightTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.WHITE.name());
        Knight knight = (Knight) PieceFactory.getPiece(board, player, Piece.PieceTypes.KNIGHT);


        assertEquals(Knight.imageWhite, knight.image);
        assertEquals(Knight.imageWhite, knight.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.BLACK.name());
        Knight knight = (Knight) PieceFactory.getPiece(board, player, Piece.PieceTypes.KNIGHT);

        assertEquals(Knight.imageBlack, knight.image);
        assertEquals(Knight.imageBlack, knight.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.BLACK.name());
        Knight knight = (Knight) PieceFactory.getPiece(board, player, Piece.PieceTypes.KNIGHT);

        assertEquals("N", knight.getSymbol());
    }
}
