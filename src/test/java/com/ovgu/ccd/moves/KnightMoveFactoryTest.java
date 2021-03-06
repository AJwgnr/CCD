package com.ovgu.ccd.moves;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.twoplayer.Chessboard;
import com.ovgu.ccd.gui.twoplayer.Game;
import com.ovgu.ccd.gui.twoplayer.Moves;
import com.ovgu.ccd.moves.two.KnightMoves;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.mock;

public class KnightMoveFactoryTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());

    @Test
    public void testGetMovesTwoPlayers() {
        Chessboard board = new Chessboard(new Settings(), new Moves(mock(Game.class)));
        Piece piece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        Assert.assertThat(KnightMoveFactory.getMoves(board, piece), instanceOf(KnightMoves.class));
    }

    @Test
    public void testGetMovesThreePlayers() {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece piece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        Assert.assertThat(KnightMoveFactory.getMoves(board, piece), instanceOf(com.ovgu.ccd.moves.three.KnightMoves.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMovesOther() {
        ThreePlayerChessboard board = new ThreePlayerChessboard();
        Piece piece = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        KnightMoveFactory.getMoves(mock(IBoard.class), piece);
    }
}
