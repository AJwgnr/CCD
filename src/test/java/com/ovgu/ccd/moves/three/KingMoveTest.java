package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import com.ovgu.ccd.pieces.Square;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItems;

public class KingMoveTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());
    Player greyPlayer = new Player("John", Player.Colors.GREY.name());

    @Test
    public void testMovesIn0E() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        Piece rook1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        Piece rook2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);

        board.setPiece(king, 0, ThreePlayerChessboard.E);
        board.setPiece(rook1, 0, ThreePlayerChessboard.A);
        board.setPiece(rook2, 0, ThreePlayerChessboard.H);
        ArrayList<Square> moves = new KingMove(king, board).moves();

        Assert.assertEquals(7, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(0, ThreePlayerChessboard.D, null),
                        new Square(0, ThreePlayerChessboard.F, null),
                        new Square(1, ThreePlayerChessboard.D, null),
                        new Square(1, ThreePlayerChessboard.E, null),
                        new Square(1, ThreePlayerChessboard.F, null),
                        new Square(0, ThreePlayerChessboard.B, null),
                        new Square(0, ThreePlayerChessboard.G, null)
                )
        );
    }

    @Test
    public void testMovesIn7D() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        Piece rook1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        Piece rook2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);

        board.setPiece(king, 7, ThreePlayerChessboard.D);
        board.setPiece(rook1, 7, ThreePlayerChessboard.A);
        board.setPiece(rook2, 7, ThreePlayerChessboard.L);
        ArrayList<Square> moves = new KingMove(king, board).moves();

        Assert.assertEquals(7, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(7, ThreePlayerChessboard.C, null),
                        new Square(7, ThreePlayerChessboard.I, null),
                        new Square(6, ThreePlayerChessboard.C, null),
                        new Square(6, ThreePlayerChessboard.D, null),
                        new Square(6, ThreePlayerChessboard.I, null),
                        new Square(7, ThreePlayerChessboard.B, null),
                        new Square(7, ThreePlayerChessboard.K, null)
                )
        );
    }

    @Test
    public void testMovesIn11I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        Piece rook1 = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.ROOK);
        Piece rook2 = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.ROOK);

        board.setPiece(king, 11, ThreePlayerChessboard.I);
        board.setPiece(rook1, 11, ThreePlayerChessboard.H);
        board.setPiece(rook2, 11, ThreePlayerChessboard.L);
        ArrayList<Square> moves = new KingMove(king, board).moves();

        Assert.assertEquals(7, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11, ThreePlayerChessboard.J, null),
                        new Square(11, ThreePlayerChessboard.E, null),
                        new Square(10, ThreePlayerChessboard.J, null),
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(10, ThreePlayerChessboard.E, null),
                        new Square(11, ThreePlayerChessboard.K, null),
                        new Square(11, ThreePlayerChessboard.G, null)
                )
        );
    }

    @Test
    public void testMovesIn3F() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.getSquare(3, ThreePlayerChessboard.F).setPiece(king);
        ArrayList<Square> moves = new KingMove(king, board).moves();

        Assert.assertEquals(8, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(2, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.G, null),
                        new Square(3, ThreePlayerChessboard.E, null),
                        new Square(3, ThreePlayerChessboard.G, null),
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.F, null),
                        new Square(8, ThreePlayerChessboard.G, null)
                )
        );
    }

    @Test
    public void testMovesIn9E() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.getSquare(9, ThreePlayerChessboard.E).setPiece(king);
        ArrayList<Square> moves = new KingMove(king, board).moves();

        Assert.assertEquals(8, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(10, ThreePlayerChessboard.E, null),
                        new Square(10, ThreePlayerChessboard.F, null),
                        new Square(9, ThreePlayerChessboard.I, null),
                        new Square(9, ThreePlayerChessboard.F, null),
                        new Square(8, ThreePlayerChessboard.I, null),
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.F, null)
                )
        );
    }


    @Test
    public void testMovesIn3D() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.getSquare(3, ThreePlayerChessboard.D).setPiece(king);
        ArrayList<Square> moves = new KingMove(king, board).moves();

        Assert.assertEquals(9, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.C, null),
                        new Square(2, ThreePlayerChessboard.D, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(3, ThreePlayerChessboard.C, null),
                        new Square(3, ThreePlayerChessboard.E, null),
                        new Square(4, ThreePlayerChessboard.C, null),
                        new Square(4, ThreePlayerChessboard.D, null),
                        new Square(4, ThreePlayerChessboard.I, null),
                        new Square(8, ThreePlayerChessboard.E, null)
                )
        );
    }
}
