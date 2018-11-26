package com.ovgu.ccd.pieces;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.applogic.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class QueenTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.WHITE.name());

        Queen queen = (Queen) PieceFactory.getPiece(board, player, Piece.PieceTypes.QUEEN);
        assertEquals(Queen.imageWhite, queen.image);
        assertEquals(Queen.imageWhite, queen.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.BLACK.name());
        Queen queen = (Queen) PieceFactory.getPiece(board, player, Piece.PieceTypes.QUEEN);

        assertEquals(Queen.imageBlack, queen.image);
        assertEquals(Queen.imageBlack, queen.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.BLACK.name());
        Queen queen = (Queen) PieceFactory.getPiece(board, player, Piece.PieceTypes.QUEEN);

        assertEquals("Q", queen.getSymbol());
    }


}