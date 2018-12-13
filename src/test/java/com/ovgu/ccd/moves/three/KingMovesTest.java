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

public class KingMovesTest {

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
        ArrayList<Square> moves = new KingMoves(king, board).moves();

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
        ArrayList<Square> moves = new KingMoves(king, board).moves();

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
        ArrayList<Square> moves = new KingMoves(king, board).moves();

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

        board.setPiece(king, 3, ThreePlayerChessboard.F);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

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

        board.setPiece(king, 9, ThreePlayerChessboard.E);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

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

        board.setPiece(king, 3, ThreePlayerChessboard.D);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

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


    @Test
    public void testMovesIn3DBlockedByPieces() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.setPiece(king, 3, ThreePlayerChessboard.D);
        Piece pawn1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(3, ThreePlayerChessboard.E).setPiece(pawn1);
        Piece pawn2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(2, ThreePlayerChessboard.D).setPiece(pawn2);
        Piece pawn3 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(3, ThreePlayerChessboard.C).setPiece(pawn3);
        Piece pawn4 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.getSquare(4, ThreePlayerChessboard.D).setPiece(pawn4);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

        Assert.assertEquals(5, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.C, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(4, ThreePlayerChessboard.C, null),
                        new Square(4, ThreePlayerChessboard.I, null),
                        new Square(8, ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testMovesIn3E() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.setPiece(king, 3, ThreePlayerChessboard.E);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

        Assert.assertEquals(9, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.I, null),
                        new Square(8, ThreePlayerChessboard.F, null),
                        new Square(3, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(2, ThreePlayerChessboard.D, null),
                        new Square(3, ThreePlayerChessboard.D, null),
                        new Square(4, ThreePlayerChessboard.D, null)
                )
        );
    }


    @Test
    public void testMovesIn4D() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.setPiece(king, 4, ThreePlayerChessboard.D);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

        Assert.assertEquals(9, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.I, null),
                        new Square(5, ThreePlayerChessboard.D, null),
                        new Square(5, ThreePlayerChessboard.C, null),

                        new Square(4, ThreePlayerChessboard.C, null),
                        new Square(4, ThreePlayerChessboard.I, null),

                        new Square(3, ThreePlayerChessboard.C, null),
                        new Square(3, ThreePlayerChessboard.D, null),
                        new Square(3, ThreePlayerChessboard.E, null),

                        new Square(8, ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testMovesIn7ICanEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.setPiece(king, 7, ThreePlayerChessboard.I);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 7, ThreePlayerChessboard.J);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 7, ThreePlayerChessboard.D);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 6, ThreePlayerChessboard.D);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 6, ThreePlayerChessboard.I);
        Piece pawn5 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn5, 6, ThreePlayerChessboard.J);

        Assert.assertEquals(5, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(7, ThreePlayerChessboard.J, null),
                        new Square(7, ThreePlayerChessboard.D, null),
                        new Square(6, ThreePlayerChessboard.D, null),
                        new Square(6, ThreePlayerChessboard.I, null),
                        new Square(6, ThreePlayerChessboard.J, null)
                )
        );
    }


    @Test
    public void testMovesIn6ICanEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.setPiece(king, 6, ThreePlayerChessboard.I);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 7, ThreePlayerChessboard.J);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 7, ThreePlayerChessboard.D);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 7, ThreePlayerChessboard.I);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 6, ThreePlayerChessboard.D);
        Piece pawn5 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn5, 6, ThreePlayerChessboard.J);
        Piece pawn6 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn6, 5, ThreePlayerChessboard.I);
        Piece pawn7 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn7, 5, ThreePlayerChessboard.D);
        Piece pawn8 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn8, 5, ThreePlayerChessboard.J);

        Assert.assertEquals(8, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(7, ThreePlayerChessboard.J, null),
                        new Square(7, ThreePlayerChessboard.D, null),
                        new Square(7, ThreePlayerChessboard.I, null),
                        new Square(6, ThreePlayerChessboard.D, null),
                        new Square(6, ThreePlayerChessboard.J, null),
                        new Square(5, ThreePlayerChessboard.D, null),
                        new Square(5, ThreePlayerChessboard.I, null),
                        new Square(5, ThreePlayerChessboard.J, null)
                )
        );
    }


    @Test
    public void testMovesIn8LCanEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.setPiece(king, 8, ThreePlayerChessboard.L);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 4, ThreePlayerChessboard.L);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 9, ThreePlayerChessboard.L);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 4, ThreePlayerChessboard.K);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 8, ThreePlayerChessboard.K);
        Piece pawn5 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn5, 9, ThreePlayerChessboard.K);


        Assert.assertEquals(5, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(4, ThreePlayerChessboard.L, null),
                        new Square(9, ThreePlayerChessboard.L, null),
                        new Square(4, ThreePlayerChessboard.K, null),
                        new Square(8, ThreePlayerChessboard.K, null),
                        new Square(9, ThreePlayerChessboard.K, null)
                )
        );
    }


    @Test
    public void testMovesIn10FCanEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.setPiece(king, 10, ThreePlayerChessboard.F);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 11, ThreePlayerChessboard.E);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 11, ThreePlayerChessboard.F);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 11, ThreePlayerChessboard.G);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 10, ThreePlayerChessboard.E);
        Piece pawn5 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn5, 10, ThreePlayerChessboard.G);
        Piece pawn6 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn6, 9, ThreePlayerChessboard.E);
        Piece pawn7 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn7, 9, ThreePlayerChessboard.F);
        Piece pawn8 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn8, 9, ThreePlayerChessboard.G);

        Assert.assertEquals(8, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11, ThreePlayerChessboard.E, null),
                        new Square(11, ThreePlayerChessboard.F, null),
                        new Square(11, ThreePlayerChessboard.G, null),
                        new Square(10, ThreePlayerChessboard.E, null),
                        new Square(10, ThreePlayerChessboard.G, null),
                        new Square(9, ThreePlayerChessboard.E, null),
                        new Square(9, ThreePlayerChessboard.F, null),
                        new Square(9, ThreePlayerChessboard.G, null)
                )
        );
    }

    @Test
    public void testMovesIn10JCanEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Piece king = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);

        board.setPiece(king, 10, ThreePlayerChessboard.J);
        ArrayList<Square> moves = new KingMoves(king, board).moves();

        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 11, ThreePlayerChessboard.K);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 11, ThreePlayerChessboard.J);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 11, ThreePlayerChessboard.I);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 10, ThreePlayerChessboard.K);
        Piece pawn5 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn5, 10, ThreePlayerChessboard.I);
        Piece pawn6 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn6, 9, ThreePlayerChessboard.K);
        Piece pawn7 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn7, 9, ThreePlayerChessboard.J);
        Piece pawn8 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn8, 9, ThreePlayerChessboard.I);

        Assert.assertEquals(8, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11, ThreePlayerChessboard.K, null),
                        new Square(11, ThreePlayerChessboard.J, null),
                        new Square(11, ThreePlayerChessboard.I, null),
                        new Square(10, ThreePlayerChessboard.K, null),
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(9, ThreePlayerChessboard.K, null),
                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(9, ThreePlayerChessboard.I, null)
                )
        );
    }
}
