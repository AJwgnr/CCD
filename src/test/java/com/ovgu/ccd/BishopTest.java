package com.ovgu.ccd;

import com.ovgu.ccd.jchess.*;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BishopTest {

    Chessboard board = new Chessboard(new Settings(), new Moves(mock(Game.class)));
    Player whitePlayer = new Player("John", Player.colors.white.name());
    Player blackPlayer = new Player("John", Player.colors.black.name());
    King whiteKing = new King(board, whitePlayer);
    King blackKing = new King(board, blackPlayer);

    @Before
    public void setup() {
        board.kingWhite = whiteKing;
        board.kingBlack = blackKing;
        board.squares[4][0].setPiece(whiteKing);
        board.squares[4][7].setPiece(blackKing);
    }

    @Test
    public void testWhitePlayerImage() {
        Bishop bishop = new Bishop(board, whitePlayer);

        assertEquals(Bishop.imageWhite, bishop.image);
        assertEquals(Bishop.imageWhite, bishop.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Bishop bishop = new Bishop(board, blackPlayer);

        assertEquals(Bishop.imageBlack, bishop.image);
        assertEquals(Bishop.imageBlack, bishop.orgImage);
    }

    @Test
    public void testSymbol() {
        Bishop bishop = new Bishop(board, blackPlayer);

        assertEquals("B", bishop.getSymbol());
    }

    @Test
    public void testAllMovesMiddleBoard() {
        Bishop bishop = new Bishop(board, whitePlayer);
        board.squares[3][3].setPiece(bishop);
        List<Square> moves = bishop.allMoves();

        assertEquals(13, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(0,0, null),
                        new Square(1,1, null),
                        new Square(2,2, null),
                        new Square(4,4, null),
                        new Square(5,5, null),
                        new Square(6,6, null),
                        new Square(7,7, null),
                        new Square(0,6, null),
                        new Square(1,5, null),
                        new Square(2,4, null),
                        new Square(4,2, null),
                        new Square(5,1, null),
                        new Square(6,0, null)
                )
        );
    }


    @Test
    public void testAllMovesTopLeftCorner() {
        Bishop bishop = new Bishop(board, whitePlayer);
        board.squares[0][7].setPiece(bishop);
        List<Square> moves = bishop.allMoves();

        assertEquals(7, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,6, null),
                        new Square(2,5, null),
                        new Square(3,4, null),
                        new Square(4,3, null),
                        new Square(5,2, null),
                        new Square(6,1, null),
                        new Square(7,0, null)
                )
        );
    }

    @Test
    public void testAllMovesLimitedByPieces() {
        /*
                |_|_|_|_|K|_|_|_|7
                |_|_|_|_|_|_|_|_|6
                |_|P|_|_|_|P|_|_|5
                |_|_|X|_|X|_|_|_|4
                |_|_|_|B|_|_|_|_|3
                |_| |X|_|X|_|_|_|2
                |_|P|_|_|_|P|_|_|1
                |_|_|_|_|K|_|_|_|0
                 0 1 2 3 4 5 6 7
        */
        Bishop bishop = new Bishop(board, whitePlayer);
        Knight topLeftPiece = new Knight(board, whitePlayer);
        Pawn topRightPiece = new Pawn(board, whitePlayer);
        Bishop bottomRightPiece = new Bishop(board, whitePlayer);
        Knight bottomLeftPiece = new Knight(board, whitePlayer);

        board.squares[3][3].setPiece(bishop);
        board.squares[1][5].setPiece(topLeftPiece);
        board.squares[5][5].setPiece(topRightPiece);
        board.squares[5][1].setPiece(bottomRightPiece);
        board.squares[1][1].setPiece(bottomLeftPiece);

        List<Square> moves = bishop.allMoves();

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
