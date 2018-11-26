package com.ovgu.ccd.pieces;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.applogic.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PawnTest {

    @Test
    public void testWhitePlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.WHITE.name());
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, player, Piece.PieceTypes.PAWN);
        assertEquals(Pawn.imageWhite, pawn.image);
        assertEquals(Pawn.imageWhite, pawn.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.BLACK.name());
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, player, Piece.PieceTypes.PAWN);

        assertEquals(Pawn.imageBlack, pawn.image);
        assertEquals(Pawn.imageBlack, pawn.orgImage);
    }

    @Test
    public void testSymbol() {
        Chessboard board = mock(Chessboard.class);
        Player player = new Player("John", Player.Colors.BLACK.name());
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, player, Piece.PieceTypes.PAWN);

        assertEquals("", pawn.getSymbol());
    }
}
