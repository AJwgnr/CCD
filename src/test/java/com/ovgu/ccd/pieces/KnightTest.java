package com.ovgu.ccd.pieces;


import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.Game;
import com.ovgu.ccd.gui.Moves;
import org.junit.Before;
import com.ovgu.ccd.applogic.Player;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.hasItems;

public class KnightTest {

    Chessboard board = new Chessboard(new Settings(), new Moves(mock(Game.class)));
    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());
    King blackKing = (King) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
    King whiteKing = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);


    @Before
    public void setup() {
        board.setKingWhite(whiteKing);
        board.setKingBlack(blackKing);
        board.getSquare(4, 0).setPiece(whiteKing);
        board.getSquare(4, 7).setPiece(blackKing);
    }

    @Test
    public void testWhitePlayerImage() {
        Knight knight = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);

        assertEquals(Knight.imageWhite, knight.image);
        assertEquals(Knight.imageWhite, knight.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Knight knight = (Knight) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KNIGHT);

        assertEquals(Knight.imageBlack, knight.image);
        assertEquals(Knight.imageBlack, knight.orgImage);
    }

    @Test
    public void testSymbol() {
        Knight knight = (Knight) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KNIGHT);

        assertEquals("N", knight.getSymbol());
    }

    @Test
    public void testAllMoves() {
        Knight knight = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        board.getSquare(3, 3).setPiece(knight);
        List<Square> moves = knight.allMoves();

        assertEquals(8, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,4, null),
                        new Square(2,5, null),
                        new Square(4,5, null),
                        new Square(5,4, null),
                        new Square(5,2, null),
                        new Square(4,1, null),
                        new Square(2,1, null),
                        new Square(1,2, null)
                )
        );
    }

    @Test
    public void testAllMovesBottomLeftCorner() {
        Knight knight = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        board.getSquare(0, 0).setPiece(knight);
        List<Square> moves = knight.allMoves();

        assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,2, null),
                        new Square(2,1, null)
                )
        );
    }

    @Test
    public void testAllMovesTopLeftCorner() {
        Knight knight = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        board.getSquare(0, 7).setPiece(knight);
        List<Square> moves = knight.allMoves();

        assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,5, null),
                        new Square(2,6, null)
                )
        );
    }

    @Test
    public void testAllMovesTopRightCorner() {
        Knight knight = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        board.getSquare(7, 7).setPiece(knight);
        List<Square> moves = knight.allMoves();

        assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5,6, null),
                        new Square(6,5, null)
                )
        );
    }

    @Test
    public void testAllMovesBottomRightCorner() {
        Knight knight = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        board.getSquare(7, 0).setPiece(knight);
        List<Square> moves = knight.allMoves();

        assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6,2, null),
                        new Square(5,1, null)
                )
        );
    }

    @Test
    public void testAllMovesWithOtherPiece() {
        Knight knight = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        board.getSquare(2, 1).setPiece(knight);
        List<Square> moves = knight.allMoves();

        assertEquals(5, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(0,0, null),
                        new Square(0,2, null),
                        new Square(1,3, null),
                        new Square(3,3, null),
                        new Square(4,2, null)
                )
        );
    }
}