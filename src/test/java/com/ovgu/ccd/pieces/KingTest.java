package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.gui.twoplayer.Chessboard;
import com.ovgu.ccd.gui.twoplayer.Game;
import com.ovgu.ccd.gui.twoplayer.Moves;
import org.junit.Before;
import com.ovgu.ccd.applogic.Player;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.hasItems;
import org.hamcrest.MatcherAssert;

public class KingTest {

    Chessboard board = new Chessboard(new Settings(), new Moves(mock(Game.class)));
    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());

    @Before
    public void setup() {
        King blackKing = (King) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setPiece(blackKing, 4, 7);
        board.setKingBlack(blackKing);
    }

    @Test
    public void testWhitePlayerImage() {
        King king = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        assertEquals(King.imageWhite, king.image);
        assertEquals(King.imageWhite, king.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        King king = (King) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);

        assertEquals(King.imageBlack, king.image);
        assertEquals(King.imageBlack, king.orgImage);
    }

    @Test
    public void testSymbol() {
        King king = (King) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);

        assertEquals("K", king.getSymbol());
    }

    @Test
    public void testAllMovesMiddleBoard() {
        King whiteKing = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(whiteKing);
        board.setPiece(whiteKing, 3, 3);
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
        King whiteKing = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite(whiteKing);
        board.setPiece(whiteKing, 0, 7);
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
        King   whiteKing = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        Knight leftPiece = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        Pawn   rightPiece = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        Bishop topPiece = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        Knight bottomPiece = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        board.setKingWhite(whiteKing);
        board.setPiece(whiteKing, 0, 7);

        board.setPiece(whiteKing, 3, 3);
        board.setPiece(leftPiece, 2, 3);
        board.setPiece(topPiece, 3, 4);
        board.setPiece(rightPiece, 4, 3);
        board.setPiece(bottomPiece, 3, 2);

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