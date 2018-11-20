package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.Game;
import com.ovgu.ccd.gui.Moves;
import com.ovgu.ccd.gui.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.hasItems;
import org.hamcrest.MatcherAssert;

public class KingTest {

    Chessboard board = new Chessboard(new Settings(), new Moves(mock(Game.class)));
    Player whitePlayer = new Player("John", Player.colors.white.name());
    Player blackPlayer = new Player("John", Player.colors.black.name());

    @Before
    public void setup() {
        King blackKing = new King(board, blackPlayer);
        board.getSquare(4, 7).setPiece(blackKing);
        board.setKingBlack(blackKing);
    }

    @Test
    public void testWhitePlayerImage() {
        King king = new King(board, whitePlayer);

        assertEquals(King.imageWhite, king.image);
        assertEquals(King.imageWhite, king.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        King king = new King(board, blackPlayer);

        assertEquals(King.imageBlack, king.image);
        assertEquals(King.imageBlack, king.orgImage);
    }

    @Test
    public void testSymbol() {
        King king = new King(board, blackPlayer);

        assertEquals("K", king.getSymbol());
    }

    @Test
    public void testAllMovesMiddleBoard() {
        King whiteKing = new King(board, whitePlayer);
        board.setKingWhite(whiteKing);
        board.getSquare(3, 3).setPiece(whiteKing);
        List<Square> moves = whiteKing.allMoves();

        assertEquals(8, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2,2, null),
                        new Square(2,3, null),
                        new Square(4,4, null),
                        new Square(3,2, null),
                        new Square(3,4, null),
                        new Square(4,2, null),
                        new Square(4,3, null),
                        new Square(4,4, null)
                )
        );
    }


    @Test
    public void testAllMovesTopLeftCorner() {
        King whiteKing = new King(board, whitePlayer);
        board.setKingWhite(whiteKing);
        board.getSquare(0, 7).setPiece(whiteKing);
        List<Square> moves = whiteKing.allMoves();

        assertEquals(3, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,7, null),
                        new Square(1,6, null),
                        new Square(0,6, null)
                )
        );
    }

    @Test
    public void testAllMovesLimitedByPieces() {
        /*
                |_|_|_|_|K|_|_|_|7
                |_|_|_|_|_|_|_|_|6
                |_|_|_|_|_|_|_|_|5
                |_|_|X|B|X|_|_|_|4
                |_|_|B|K|B|_|_|_|3
                |_| |X|B|X|_|_|_|2
                |_|_|_|_|_|_|_|_|1
                |_|_|_|_|_|_|_|_|0
                 0 1 2 3 4 5 6 7
        */
        King   whiteKing = new King(board, whitePlayer);
        Knight leftPiece = new Knight(board, whitePlayer);
        Pawn   rightPiece = new Pawn(board, whitePlayer);
        Bishop topPiece = new Bishop(board, whitePlayer);
        Knight bottomPiece = new Knight(board, whitePlayer);
        board.setKingWhite(whiteKing);
        board.getSquare(0, 7).setPiece(whiteKing);

        board.getSquare(3, 3).setPiece(whiteKing);
        board.getSquare(2, 3).setPiece(leftPiece);
        board.getSquare(3, 4).setPiece(topPiece);
        board.getSquare(4, 3).setPiece(rightPiece);
        board.getSquare(3, 2).setPiece(bottomPiece);

        List<Square> moves = whiteKing.allMoves();

        assertEquals(4, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2,4, null),
                        new Square(4,4, null),
                        new Square(4,2, null),
                        new Square(2,2, null)
                )
        );
    }
}