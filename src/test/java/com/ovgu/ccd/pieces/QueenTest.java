package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.Game;
import com.ovgu.ccd.gui.Moves;
import org.junit.Before;

import com.ovgu.ccd.applogic.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.hamcrest.MatcherAssert;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;

public class QueenTest {

    Chessboard board = new Chessboard(new Settings(), new Moves(mock(Game.class)));
    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());

    @Before
    public void setup() {
        King blackKing = (King) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        King whiteKing = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.getSquare(4, 7).setPiece(blackKing);
        board.setKingBlack(blackKing);

        board.setKingWhite(whiteKing);
        board.getSquare(4, 0).setPiece(whiteKing);
    }

    @Test
    public void testWhitePlayerImage() {
        Queen queen = (Queen) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.QUEEN);
        assertEquals(Queen.imageWhite, queen.image);
        assertEquals(Queen.imageWhite, queen.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Queen queen = (Queen) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.QUEEN);

        assertEquals(Queen.imageBlack, queen.image);
        assertEquals(Queen.imageBlack, queen.orgImage);
    }

    @Test
    public void testSymbol() {
        Queen queen = (Queen) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.QUEEN);

        assertEquals("Q", queen.getSymbol());
    }

    @Test
    public void testAllMovesMiddleBoard() {
        /*
                |_|_|_|_|K|_|_|_|7
                |_|_|_|_|_|_|_|_|6
                |_|_|_|_|_|_|_|_|5
                |_|_|_|_|_|_|_|_|4
                |_|_|_|Q|_|_|_|_|3
                |_|_|_|_|_|_|_|_|2
                |_|_|_|_|_|_|_|_|1
                |_|_|_|_|K|_|_|_|0
                 0 1 2 3 4 5 6 7
        */
        Queen queen = (Queen) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.QUEEN);
        board.getSquare(3, 3).setPiece(queen);
        List<Square> moves = queen.allMoves();

        assertEquals(27, moves.size());
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
                        new Square(3,7, null),
                        new Square(0,6, null),
                        new Square(1,5, null),
                        new Square(2,4, null),
                        new Square(4,2, null),
                        new Square(5,1, null),
                        new Square(6,0, null),
                        new Square(0,0, null),
                        new Square(1,1, null),
                        new Square(2,2, null),
                        new Square(4,4, null),
                        new Square(5,5, null),
                        new Square(6,6, null),
                        new Square(7,7, null)
                )
        );
    }

    @Test
    public void testAllMovesBottomLeftCorner() {
        Queen queen = (Queen) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.QUEEN);
        board.getSquare(0, 0).setPiece(queen);
        List<Square> moves = queen.allMoves();

        assertEquals(17, moves.size());
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
                        new Square(3,0, null),
                        new Square(1,1, null),
                        new Square(2,2, null),
                        new Square(3,3, null),
                        new Square(4,4, null),
                        new Square(5,5, null),
                        new Square(6,6, null),
                        new Square(7,7, null)
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
                |_|_|P|Q|P|_|_|_|3
                |_| |P|P|P|_|_|_|2
                |_|_|_|_|_|_|_|_|1
                |_|_|_|_|K|_|_|_|0
                 0 1 2 3 4 5 6 7
        */
        Queen queen = (Queen) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.QUEEN);

        Pawn leftTopPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn leftMiddlePiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn leftBottomPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn rightTopPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn rightMiddlePiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn rightBottomPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn bottomPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn topPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);

        board.getSquare(3, 3).setPiece(queen);
        board.getSquare(2, 3).setPiece(leftMiddlePiece);
        board.getSquare(2, 2).setPiece(leftBottomPiece);
        board.getSquare(2, 4).setPiece(leftTopPiece);
        board.getSquare(4, 3).setPiece(rightMiddlePiece);
        board.getSquare(4, 2).setPiece(rightBottomPiece);
        board.getSquare(4, 4).setPiece(rightTopPiece);
        board.getSquare(3, 2).setPiece(bottomPiece);
        board.getSquare(3, 4).setPiece(topPiece);

        List<Square> moves = queen.allMoves();
        assertEquals(8, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2,3, leftMiddlePiece),
                        new Square(2,2, leftBottomPiece),
                        new Square(2,4, leftTopPiece),
                        new Square(4,3, rightMiddlePiece),
                        new Square(4,2, rightBottomPiece),
                        new Square(4,4, rightTopPiece),
                        new Square(3,2, bottomPiece),
                        new Square(3,4, topPiece)
                )
        );
    }
}