package com.ovgu.ccd.pieces;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.Game;
import com.ovgu.ccd.gui.Moves;
import org.junit.Before;
import com.ovgu.ccd.applogic.Player;
import org.junit.Test;
import org.hamcrest.MatcherAssert;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;


public class PawnTest {

    Game game = new Game();
    Chessboard board = new Chessboard(game.settings, new Moves(game));
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
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);

        assertEquals(Pawn.imageWhite, pawn.image);
        assertEquals(Pawn.imageWhite, pawn.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);

        assertEquals(Pawn.imageBlack, pawn.image);
        assertEquals(Pawn.imageBlack, pawn.orgImage);
    }

    @Test
    public void testSymbol() {
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);

        assertEquals("", pawn.getSymbol());
    }

    @Test
    public void testAllMovesMiddleBoard() {
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(3, 3).setPiece(pawn);

        List<Square> moves = pawn.allMoves();

        assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,2, null)
                )
        );
    }

    @Test
    public void testAllMovesLimitedByPieces() {
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        Pawn blockingPawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);

        board.getSquare(3, 3).setPiece(pawn);
        board.getSquare(3, 2).setPiece(blockingPawn);

        List<Square> moves = pawn.allMoves();

        assertEquals(0, moves.size());
    }

    @Test
    public void testAllMovesEating() {
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        Pawn blockingPawnLeft = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn blockingPawnRight = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);

        board.getSquare(3, 3).setPiece(pawn);
        board.getSquare(2, 2).setPiece(blockingPawnLeft);
        board.getSquare(4, 2).setPiece(blockingPawnRight);

        List<Square> moves = pawn.allMoves();

        assertEquals(3, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2,2, blockingPawnLeft),
                        new Square(3,2, null),
                        new Square(4,2, blockingPawnRight
                        )
                )
        );
    }

    @Test
    public void testAllMovesInBorderLeft() {
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(0, 3).setPiece(pawn);

        List<Square> moves = pawn.allMoves();

        assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(new Square(0,2, null))
        );
    }

    @Test
    public void testAllMovesInBorderRight() {
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(7, 3).setPiece(pawn);

        List<Square> moves = pawn.allMoves();

        assertEquals(1, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(new Square(7,2, null))
        );
    }

    @Test
    public void testAllMovesTwoJump() {
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(3, 6).setPiece(pawn);

        List<Square> moves = pawn.allMoves();

        assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,4, null),
                        new Square(3,5, null)
                )
        );
    }

    @Test
    public void testAllMovesEnPassants() {
        /*
                |_|_|_|_|K|_|_|_|7
                |_|_|_|_|_|_|_|_|6
                |_|_|_|_|_|_|_|_|5
                |_|_|_|_|_|_|_|_|4
                |_|_|_|P|P|_|_|_|3
                |_|_|_|_|^|_|_|_|2
                |_|_|_|_|P|_|_|_|1
                |_|_|_|_|K|_|_|_|0
                 0 1 2 3 4 5 6 7
        */
        Pawn pawn = (Pawn) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        Pawn rightPawn = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);

        board.getSquare(3, 3).setPiece(pawn);
        board.getSquare(4, 1).setPiece(rightPawn);

        game.chessboard = board;

        board.move(board.getSquare(4, 1), board.getSquare(4, 3));
        List<Square> moves = pawn.allMoves();

        assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3,2, null),
                        new Square(4,2, null)
                )
        );
    }
}