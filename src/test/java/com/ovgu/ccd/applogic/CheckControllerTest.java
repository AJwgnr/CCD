package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CheckControllerTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());

    @Test
    public void testNoCheck() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 3, ThreePlayerChessboard.F);

        assertTrue(new CheckController(board, king).isSafe());
    }
}
