package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.*;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.Game;
import com.ovgu.ccd.gui.Moves;
import com.ovgu.ccd.moves.two.PawnMoves;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.mock;

public class PawnMoveFactoryTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());

    @Test
    public void testGetMovesTwoPlayers() {
        Chessboard board = new Chessboard(new Settings(), new Moves(mock(Game.class)));
        Piece piece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        Assert.assertThat(PawnMoveFactory.getMoves(board, piece), instanceOf(PawnMoves.class));
    }

    @Test
    public void testGetMovesThreePlayers() {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece piece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        Assert.assertThat(PawnMoveFactory.getMoves(board, piece), instanceOf(com.ovgu.ccd.moves.three.PawnMoves.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMovesOther() {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece piece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        PawnMoveFactory.getMoves(mock(IBoard.class), piece);
    }
}