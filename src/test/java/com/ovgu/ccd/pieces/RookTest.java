package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.Game;
import com.ovgu.ccd.gui.Moves;
import com.ovgu.ccd.gui.Player;
import com.ovgu.ccd.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.hamcrest.MatcherAssert;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItems;

public class RookTest {

    Chessboard board = new Chessboard(new Settings(), new Moves(mock(Game.class)));
    Player whitePlayer = new Player("John", Player.colors.white.name());
    Player blackPlayer = new Player("John", Player.colors.black.name());

    @Before
    public void setup() {
        King blackKing = new King(board, blackPlayer);
        King whiteKing = new King(board, whitePlayer);

        board.getSquare(4,7).setPiece(blackKing);
        board.kingBlack = blackKing;

        board.kingWhite = whiteKing;
        board.getSquare(4, 0).setPiece(whiteKing);
    }

    @Test
    public void testWhitePlayerImage() {
        Rook rook = new Rook(board, whitePlayer);

        assertEquals(Rook.imageWhite, rook.image);
        assertEquals(Rook.imageWhite, rook.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Rook rook = new Rook(board, blackPlayer);

        assertEquals(Rook.imageBlack, rook.image);
        assertEquals(Rook.imageBlack, rook.orgImage);
    }

    @Test
    public void testSymbol() {
        Rook rook = new Rook(board, whitePlayer);

        assertEquals("R", rook.getSymbol());
    }


    @Test
    public void testAllMovesBottomLeftCorner() {
        Rook rook = new Rook(board, whitePlayer);
        board.getSquare(0,0).setPiece(rook);
        List<Square> moves = rook.allMoves();

        assertEquals(10, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(0,1, null),
                        new Square(0,2, null),
                        new Square(0,3, null),
                        new Square(0,4, null),
                        new Square(0,5, null),
                        new Square(0,6, null),
                        new Square(0,7, null),
                        new Square(1,0, null),
                        new Square(2,0, null),
                        new Square(3,0, null)
                )
        );
    }

    @Test
    public void testAllMovesLimitedByPieces() {
        /*
                |_|_|_|_|K|_|_|_|7
                |_|_|_|_|_|_|_|_|6
                |_|_|_|_|_|_|_|_|5
                |_|_|P|P|P|_|_|_|4
                |_|_|P|R|P|_|_|_|3
                |_| |P|P|P|_|_|_|2
                |_|_|_|_|_|_|_|_|1
                |_|_|_|_|K|_|_|_|0
                 0 1 2 3 4 5 6 7
        */
        Rook rook = new Rook(board, whitePlayer);

        Pawn leftTopPiece = new Pawn(board, blackPlayer);
        Pawn leftMiddlePiece = new Pawn(board, blackPlayer);
        Pawn leftBottomPiece = new Pawn(board, blackPlayer);
        Pawn rightTopPiece = new Pawn(board, blackPlayer);
        Pawn rightMiddlePiece = new Pawn(board, blackPlayer);
        Pawn rightBottomPiece = new Pawn(board, blackPlayer);
        Pawn bottomPiece = new Pawn(board, blackPlayer);
        Pawn topPiece = new Pawn(board, blackPlayer);

        board.getSquare(3, 3).setPiece(rook);
        board.getSquare(2, 3).setPiece(leftMiddlePiece);
        board.getSquare(2, 2).setPiece(leftBottomPiece);
        board.getSquare(2, 4).setPiece(leftTopPiece);
        board.getSquare(4, 3).setPiece(rightMiddlePiece);
        board.getSquare(4, 2).setPiece(rightBottomPiece);
        board.getSquare(4, 4).setPiece(rightTopPiece);
        board.getSquare(3, 2).setPiece(bottomPiece);
        board.getSquare(3, 4).setPiece(topPiece);

        List<Square> moves = rook.allMoves();
        assertEquals(4, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2,3, leftMiddlePiece),
                        new Square(4,3, rightMiddlePiece),
                        new Square(3,2, bottomPiece),
                        new Square(3,4, topPiece)
                )
        );
    }

    @Test
    public void testAllMovesMiddleBoard() {
        /*
                |_|_|_|_|K|_|_|_|7
                |_|_|_|_|_|_|_|_|6
                |_|_|_|_|_|_|_|_|5
                |_|_|_|_|_|_|_|_|4
                |_|_|_|R|_|_|_|_|3
                |_|_|_|_|_|_|_|_|2
                |_|_|_|_|_|_|_|_|1
                |_|_|_|_|K|_|_|_|0
                 0 1 2 3 4 5 6 7
        */
        Rook rook = new Rook(board, whitePlayer);
        board.getSquare(3, 3).setPiece(rook);
        List<Square> moves = rook.allMoves();

        assertEquals(14, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(0,3, null),
                        new Square(1,3, null),
                        new Square(2,3, null),
                        new Square(4,3, null),
                        new Square(5,3, null),
                        new Square(6,3, null),
                        new Square(7,3, null),
                        new Square(3,0, null),
                        new Square(3,1, null),
                        new Square(3,2, null),
                        new Square(3,4, null),
                        new Square(3,5, null),
                        new Square(3,6, null),
                        new Square(3,7, null)
                )
        );
    }
}