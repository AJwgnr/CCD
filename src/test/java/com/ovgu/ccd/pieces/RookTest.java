package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.gui.twoplayer.Chessboard;
import com.ovgu.ccd.gui.twoplayer.Game;
import com.ovgu.ccd.gui.twoplayer.Moves;
import org.junit.Before;
import com.ovgu.ccd.applogic.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.hamcrest.MatcherAssert;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItems;

public class RookTest {

    Chessboard board = new Chessboard(new Settings(), new Moves(mock(Game.class)));
    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());

    @Before
    public void setup() {
        King blackKing = (King) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        King whiteKing = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.setPiece(blackKing, 4,7);
        board.setKingBlack(blackKing);

        board.setKingWhite(whiteKing);
        board.setPiece(whiteKing, 4, 0);
    }

    @Test
    public void testWhitePlayerImage() {
        Rook rook = (Rook) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);

        assertEquals(Rook.imageWhite, rook.image);
        assertEquals(Rook.imageWhite, rook.orgImage);
    }


    @Test
    public void testBlackPlayerImage() {
        Rook rook = (Rook) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);


        assertEquals(Rook.imageBlack, rook.image);
        assertEquals(Rook.imageBlack, rook.orgImage);
    }

    @Test
    public void testSymbol() {
        Rook rook = (Rook) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);

        assertEquals("R", rook.getSymbol());
    }


    @Test
    public void testAllMovesBottomLeftCorner() {
        Rook rook = (Rook) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 0,0);
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
        Rook rook = (Rook) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);

        Pawn leftTopPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn leftMiddlePiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn leftBottomPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn rightTopPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn rightMiddlePiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn rightBottomPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn bottomPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        Pawn topPiece = (Pawn) PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);

        board.setPiece(rook, 3, 3);
        board.setPiece(leftMiddlePiece, 2, 3);
        board.setPiece(leftBottomPiece,2, 2);
        board.setPiece(leftTopPiece, 2, 4);
        board.setPiece(rightMiddlePiece, 4, 3);
        board.setPiece(rightBottomPiece, 4, 2);
        board.setPiece(rightTopPiece,4, 4);
        board.setPiece(bottomPiece, 3, 2);
        board.setPiece(topPiece, 3, 4);

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
        Rook rook = (Rook) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, 3);
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