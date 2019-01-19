package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.*;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class CheckMateControllerTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());

    @Test
    public void testIsCheckMate() throws Exception {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(king);
        board.setPiece(king, 0, ThreePlayerChessboard.E);

        Queen queen = (Queen) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.QUEEN);
        board.setPiece(queen, 0, ThreePlayerChessboard.A);

        Rook rook = (Rook) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 1, ThreePlayerChessboard.A);

        assertTrue(new CheckMateController(board, king).isCheckMate());
    }
}
