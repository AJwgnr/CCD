package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.Game;
import com.ovgu.ccd.gui.Moves;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import com.ovgu.ccd.applogic.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItems;
public class BishopTest {

    Chessboard board = new Chessboard(new Settings(), new Moves(mock(Game.class)));
    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());
    King blackKing = (King) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
    King whiteKing = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

    @Before
    public void setup() {
        board.setKingWhite(whiteKing);
        board.setKingBlack(blackKing);
        board.setPiece(whiteKing, 4, 0);
        board.setPiece(blackKing, 4, 7);
    }

    @Test
    public void testWhitePlayerImage() {
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);

        assertEquals(Bishop.imageWhite, bishop.image);
        assertEquals(Bishop.imageWhite, bishop.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.BISHOP);

        assertEquals(Bishop.imageBlack, bishop.image);
        assertEquals(Bishop.imageBlack, bishop.orgImage);
    }

    @Test
    public void testSymbol() {
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.BISHOP);

        assertEquals("B", bishop.getSymbol());
    }

    @Test
    public void testAllMovesMiddleBoard() {
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        board.setPiece(bishop, 3, 3);
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
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        board.setPiece(bishop, 0, 7);
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
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        Knight topLeftPiece = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);
        Pawn topRightPiece = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        Bishop bottomRightPiece = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        Knight bottomLeftPiece = (Knight) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KNIGHT);

        board.setPiece(bishop, 3, 3);
        board.setPiece(topLeftPiece, 1, 5);
        board.setPiece(topRightPiece, 5, 5);
        board.setPiece(bottomRightPiece, 5, 1);
        board.setPiece(bottomLeftPiece, 1, 1);

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