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

public class StraightMovesTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());

    @Test
    public void testMovesIn3E() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.E);
        ArrayList<Square> moves = new StraightMoves(rook, board).moves();

        Assert.assertEquals(14, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.A, null),
                        new Square(3, ThreePlayerChessboard.B, null),
                        new Square(3, ThreePlayerChessboard.C, null),
                        new Square(3, ThreePlayerChessboard.D, null),
                        new Square(3, ThreePlayerChessboard.F, null),
                        new Square(3, ThreePlayerChessboard.G, null),
                        new Square(3, ThreePlayerChessboard.H, null),
                        new Square(0, ThreePlayerChessboard.E, null),
                        new Square(1, ThreePlayerChessboard.E, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(9, ThreePlayerChessboard.E, null),
                        new Square(10, ThreePlayerChessboard.E, null),
                        new Square(11, ThreePlayerChessboard.E, null)
                )
        );
    }


    @Test
    public void testMovesIn2G() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 2, ThreePlayerChessboard.G);
        ArrayList<Square> moves = new StraightMoves(rook, board).moves();

        Assert.assertEquals(14, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.A, null),
                        new Square(2, ThreePlayerChessboard.B, null),
                        new Square(2, ThreePlayerChessboard.C, null),
                        new Square(2, ThreePlayerChessboard.D, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(2, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.H, null),
                        new Square(0, ThreePlayerChessboard.G, null),
                        new Square(1, ThreePlayerChessboard.G, null),
                        new Square(3, ThreePlayerChessboard.G, null),
                        new Square(8, ThreePlayerChessboard.G, null),
                        new Square(9, ThreePlayerChessboard.G, null),
                        new Square(10, ThreePlayerChessboard.G, null),
                        new Square(11, ThreePlayerChessboard.G, null)
                )
        );
    }

    @Test
    public void testMovesIn9J() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 9, ThreePlayerChessboard.J);
        ArrayList<Square> moves = new StraightMoves(rook, board).moves();

        Assert.assertEquals(14, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.I, null),
                        new Square(9, ThreePlayerChessboard.E, null),
                        new Square(9, ThreePlayerChessboard.F, null),
                        new Square(9, ThreePlayerChessboard.G, null),
                        new Square(9, ThreePlayerChessboard.H, null),//falta
                        new Square(9, ThreePlayerChessboard.K, null),
                        new Square(9, ThreePlayerChessboard.L, null),
                        new Square(4, ThreePlayerChessboard.J, null),
                        new Square(5, ThreePlayerChessboard.J, null),
                        new Square(6, ThreePlayerChessboard.J, null),
                        new Square(7, ThreePlayerChessboard.J, null),
                        new Square(8, ThreePlayerChessboard.J, null),
                        new Square(10, ThreePlayerChessboard.J, null),
                        new Square(11, ThreePlayerChessboard.J, null)
                )
        );
    }


    @Test
    public void testMovesIn3EBlockedByPieces() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.E);

        Piece pawn1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 1, ThreePlayerChessboard.E);
        Piece pawn2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 3, ThreePlayerChessboard.G);
        Piece pawn3 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 3, ThreePlayerChessboard.C);
        Piece pawn4 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 10, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new StraightMoves(rook, board).moves();

        Assert.assertEquals(5, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(3, ThreePlayerChessboard.D, null),
                        new Square(3, ThreePlayerChessboard.F, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(9, ThreePlayerChessboard.E, null)
                )
        );
    }


    @Test
    public void testMovesIn0HBlockedByPieces() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 0, ThreePlayerChessboard.H);

        Piece pawn1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 3, ThreePlayerChessboard.H);
        Piece pawn2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 0, ThreePlayerChessboard.G);

        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 10, ThreePlayerChessboard.H);
        Piece rook1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook1, 11, ThreePlayerChessboard.H);

        ArrayList<Square> moves = new StraightMoves(rook, board).moves();

        Assert.assertEquals(2, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1, ThreePlayerChessboard.H, null),
                        new Square(2, ThreePlayerChessboard.H, null)
                )
        );
    }


    @Test
    public void testMovesIn0HBlockedByPiecesCanEat() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 0, ThreePlayerChessboard.H);

        Piece pawn1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 0, ThreePlayerChessboard.G);

        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 10, ThreePlayerChessboard.H);
        Piece rook1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook1, 11, ThreePlayerChessboard.H);

        ArrayList<Square> moves = new StraightMoves(rook, board).moves();

        Assert.assertEquals(6, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1, ThreePlayerChessboard.H, null),
                        new Square(2, ThreePlayerChessboard.H, null),
                        new Square(3, ThreePlayerChessboard.H, null),
                        new Square(8, ThreePlayerChessboard.H, null),
                        new Square(9, ThreePlayerChessboard.H, null),
                        new Square(10, ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testMovesIn10EBlockedByPiecesCanEatBothSides() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 10, ThreePlayerChessboard.E);

        Piece pawn1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 1, ThreePlayerChessboard.E);

        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 10, ThreePlayerChessboard.F);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 10, ThreePlayerChessboard.I);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 11, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new StraightMoves(rook, board).moves();

        Assert.assertEquals(7, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11, ThreePlayerChessboard.E, null),
                        new Square(9, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(3, ThreePlayerChessboard.E, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(10, ThreePlayerChessboard.F, null)
                )
        );
    }

    @Test
    public void testMovesIn0EBlockedByPiecesCanEatBothSides() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 0, ThreePlayerChessboard.E);

        Piece pawn1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 0, ThreePlayerChessboard.C);
        Piece pawn2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 0, ThreePlayerChessboard.G);

        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 10, ThreePlayerChessboard.E);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 11, ThreePlayerChessboard.E);

        ArrayList<Square> moves = new StraightMoves(rook, board).moves();

        Assert.assertEquals(8, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(0, ThreePlayerChessboard.D, null),
                        new Square(0, ThreePlayerChessboard.F, null),
                        new Square(1, ThreePlayerChessboard.E, null),
                        new Square(2, ThreePlayerChessboard.E, null),
                        new Square(3, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(9, ThreePlayerChessboard.E, null),
                        new Square(10, ThreePlayerChessboard.E, null)
                )
        );
    }

    @Test
    public void testMovesIn10JBlockedByPiecesCanEatBothSides() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 10, ThreePlayerChessboard.J);

        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 10, ThreePlayerChessboard.K);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 10, ThreePlayerChessboard.I);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 11, ThreePlayerChessboard.I);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 7, ThreePlayerChessboard.J);
        Piece pawn5 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn5, 6, ThreePlayerChessboard.J);

        ArrayList<Square> moves = new StraightMoves(rook, board).moves();

        Assert.assertEquals(8, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.K, null),
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(8, ThreePlayerChessboard.J, null),
                        new Square(4, ThreePlayerChessboard.J, null),
                        new Square(5, ThreePlayerChessboard.J, null),
                        new Square(6, ThreePlayerChessboard.J, null),
                        new Square(11, ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testMovesIn6JBlockedByPiecesCanEatBothSides() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 6, ThreePlayerChessboard.J);

        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 6, ThreePlayerChessboard.K);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 6, ThreePlayerChessboard.I);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 7, ThreePlayerChessboard.J);
        ArrayList<Square> moves = new StraightMoves(rook, board).moves();

        Assert.assertEquals(9, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6, ThreePlayerChessboard.K, null),
                        new Square(6, ThreePlayerChessboard.I, null),
                        new Square(7, ThreePlayerChessboard.J, null),
                        new Square(5, ThreePlayerChessboard.J, null),
                        new Square(4, ThreePlayerChessboard.J, null),
                        new Square(8, ThreePlayerChessboard.J, null),
                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(10, ThreePlayerChessboard.J, null),
                        new Square(11, ThreePlayerChessboard.J, null)
                )
        );
    }
}
