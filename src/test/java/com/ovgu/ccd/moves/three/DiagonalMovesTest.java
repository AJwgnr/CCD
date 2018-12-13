package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.pieces.Bishop;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import com.ovgu.ccd.pieces.Square;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertTrue;

public class DiagonalMovesTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
    Player blackPlayer = new Player("John", Player.Colors.BLACK.name());

    @Test
    public void testPerfectDiagonal() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);

        //White diagonals
        ((ThreePlayerChessboard) board).setPiece(bishop, 0,  ThreePlayerChessboard.H);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,1,  ThreePlayerChessboard.G);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,2,  ThreePlayerChessboard.F);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,3,  ThreePlayerChessboard.E);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,8,  ThreePlayerChessboard.I);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,9,  ThreePlayerChessboard.J);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,10, ThreePlayerChessboard.K);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,11, ThreePlayerChessboard.L);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,7,  ThreePlayerChessboard.A);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,6,  ThreePlayerChessboard.B);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,5,  ThreePlayerChessboard.C);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,4,  ThreePlayerChessboard.D);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        //Black diagonals
        ((ThreePlayerChessboard) board).setPiece(bishop,0,  ThreePlayerChessboard.A);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,1,  ThreePlayerChessboard.B);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,2,  ThreePlayerChessboard.C);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,3,  ThreePlayerChessboard.D);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,4,  ThreePlayerChessboard.I);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,5,  ThreePlayerChessboard.J);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,6,  ThreePlayerChessboard.K);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,7,  ThreePlayerChessboard.L);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,8,  ThreePlayerChessboard.E);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,9,  ThreePlayerChessboard.F);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,10, ThreePlayerChessboard.G);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,11, ThreePlayerChessboard.H);
        assertTrue(new DiagonalMoves(bishop, board).perfectDiagonal());
    }

    @Test
    public void testSameSextantMovesTopRight() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.I);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(10, ThreePlayerChessboard.K, null),
                        new Square(11, ThreePlayerChessboard.L, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 8, ThreePlayerChessboard.I);

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.I, null),
                        new Square(10, ThreePlayerChessboard.K, null),
                        new Square(11, ThreePlayerChessboard.L, null),
                        new Square(8,  ThreePlayerChessboard.K, null),
                        new Square(10, ThreePlayerChessboard.I, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 9, ThreePlayerChessboard.J);

        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.K);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8,  ThreePlayerChessboard.I, null),
                        new Square(9,  ThreePlayerChessboard.J, null),
                        new Square(11, ThreePlayerChessboard.L, null),
                        new Square(9,  ThreePlayerChessboard.L, null),
                        new Square(11, ThreePlayerChessboard.J, null)

                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 10, ThreePlayerChessboard.K);

        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.L);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.K, null),
                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(8, ThreePlayerChessboard.I, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 11, ThreePlayerChessboard.L);

        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.I);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(8, ThreePlayerChessboard.K, null),
                        new Square(11, ThreePlayerChessboard.J, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 10, ThreePlayerChessboard.I);

        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(10, ThreePlayerChessboard.K, null),
                        new Square(9, ThreePlayerChessboard.L, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 11, ThreePlayerChessboard.J);

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.K);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.L, null),
                        new Square(9, ThreePlayerChessboard.J, null),
                        new Square(10, ThreePlayerChessboard.I, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 8, ThreePlayerChessboard.K);

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.L);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.K, null),
                        new Square(10, ThreePlayerChessboard.K, null),
                        new Square(11, ThreePlayerChessboard.J, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 9, ThreePlayerChessboard.L);

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.L);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.K, null),
                        new Square(10, ThreePlayerChessboard.J, null),
                        new Square(11, ThreePlayerChessboard.I, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 8, ThreePlayerChessboard.L);

        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.L);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11, ThreePlayerChessboard.K, null),
                        new Square(9, ThreePlayerChessboard.K, null),
                        new Square(8, ThreePlayerChessboard.J, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 10, ThreePlayerChessboard.L);

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.K);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.J, null),
                        new Square(10, ThreePlayerChessboard.L, null),
                        new Square(8, ThreePlayerChessboard.L, null),
                        new Square(10, ThreePlayerChessboard.J, null),
                        new Square(11, ThreePlayerChessboard.I, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 9, ThreePlayerChessboard.K);

        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.K);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.J, null),
                        new Square(9, ThreePlayerChessboard.I, null),
                        new Square(10, ThreePlayerChessboard.L, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 11, ThreePlayerChessboard.K);

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.K, null),
                        new Square(10, ThreePlayerChessboard.L, null),
                        new Square(9, ThreePlayerChessboard.I, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 8, ThreePlayerChessboard.J);

        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.I, null),
                        new Square(11, ThreePlayerChessboard.K, null),
                        new Square(9, ThreePlayerChessboard.K, null),
                        new Square(8, ThreePlayerChessboard.L, null),
                        new Square(11, ThreePlayerChessboard.I, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 10, ThreePlayerChessboard.J);

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.I);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.J, null),
                        new Square(11, ThreePlayerChessboard.K, null),
                        new Square(8, ThreePlayerChessboard.J, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 9, ThreePlayerChessboard.I);

        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.I);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.J, null),
                        new Square(9, ThreePlayerChessboard.K,  null),
                        new Square(8, ThreePlayerChessboard.L, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 11, ThreePlayerChessboard.I);

        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.E);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.F, null),
                        new Square(1, ThreePlayerChessboard.G, null),
                        new Square(0, ThreePlayerChessboard.H, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 3, ThreePlayerChessboard.E);

        ((ThreePlayerChessboard) board).setPiece(bishop, 0, ThreePlayerChessboard.H);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1, ThreePlayerChessboard.G, null),
                        new Square(2, ThreePlayerChessboard.F, null),
                        new Square(3, ThreePlayerChessboard.E, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 0, ThreePlayerChessboard.H);

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.E);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11, ThreePlayerChessboard.H, null),
                        new Square(10, ThreePlayerChessboard.G, null),
                        new Square(9, ThreePlayerChessboard.F, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 8, ThreePlayerChessboard.E);

        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.H);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.G, null),
                        new Square(9, ThreePlayerChessboard.F, null),
                        new Square(8, ThreePlayerChessboard.E, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 11, ThreePlayerChessboard.H);

        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.D);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2, ThreePlayerChessboard.C, null),
                        new Square(1, ThreePlayerChessboard.B, null),
                        new Square(0, ThreePlayerChessboard.A, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 3, ThreePlayerChessboard.D);

        ((ThreePlayerChessboard) board).setPiece(bishop, 0, ThreePlayerChessboard.A);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1, ThreePlayerChessboard.B, null),
                        new Square(2, ThreePlayerChessboard.C, null),
                        new Square(3, ThreePlayerChessboard.D, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 0, ThreePlayerChessboard.A);

        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.D);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.C, null),
                        new Square(6, ThreePlayerChessboard.B, null),
                        new Square(7, ThreePlayerChessboard.A, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 4, ThreePlayerChessboard.D);

        ((ThreePlayerChessboard) board).setPiece(bishop, 7, ThreePlayerChessboard.A);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6, ThreePlayerChessboard.B, null),
                        new Square(5, ThreePlayerChessboard.C, null),
                        new Square(4, ThreePlayerChessboard.D, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 7, ThreePlayerChessboard.A);

        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.I);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.J, null),
                        new Square(6, ThreePlayerChessboard.K, null),
                        new Square(7, ThreePlayerChessboard.L, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 4, ThreePlayerChessboard.I);

        ((ThreePlayerChessboard) board).setPiece(bishop, 7, ThreePlayerChessboard.L);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6, ThreePlayerChessboard.K, null),
                        new Square(5, ThreePlayerChessboard.J, null),
                        new Square(4, ThreePlayerChessboard.I, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 7, ThreePlayerChessboard.L);


        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.E, null),
                        new Square(9, ThreePlayerChessboard.G, null),
                        new Square(10, ThreePlayerChessboard.H, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 8, ThreePlayerChessboard.F);

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).getSameSextantMoves(bishop.getSquare());
        assertTrue(moves.size() == 5);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(10, ThreePlayerChessboard.G, null),
                        new Square(11, ThreePlayerChessboard.H, null),
                        new Square(10, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.G, null)
                )
        );
        ((ThreePlayerChessboard) board).setPiece(null, 9, ThreePlayerChessboard.F);
    }

    @Test
    public void testMoves8I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.I);
        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 17);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        // same sextant
                        new Square(9, ThreePlayerChessboard.J, null),  new Square(10, ThreePlayerChessboard.K, null),  new Square(11, ThreePlayerChessboard.L, null),
                        // right
                        new Square(4, ThreePlayerChessboard.J, null),  new Square(5, ThreePlayerChessboard.K, null),   new Square(6, ThreePlayerChessboard.L, null),
                        // left
                        new Square(9, ThreePlayerChessboard.E, null),  new Square(10, ThreePlayerChessboard.F, null),  new Square(11, ThreePlayerChessboard.G, null),
                        new Square(0, ThreePlayerChessboard.H, null),  new Square(1, ThreePlayerChessboard.G, null),  new Square(2, ThreePlayerChessboard.F, null),  new Square(3, ThreePlayerChessboard.E, null),
                        new Square(4, ThreePlayerChessboard.D, null),  new Square(5, ThreePlayerChessboard.C, null),  new Square(6, ThreePlayerChessboard.B, null),  new Square(7, ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMoves2G() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.G);
        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 20);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,  ThreePlayerChessboard.H, null), // down diagonal
                        new Square(0,  ThreePlayerChessboard.E, null), new Square(1, ThreePlayerChessboard.F, null), // left diagonal same sextant
                        new Square(3,  ThreePlayerChessboard.H, null), // right diagonal same sextant
                        new Square(3,  ThreePlayerChessboard.F, null), // top diagonal same sextant
                        new Square(8,  ThreePlayerChessboard.E, null), new Square(9, ThreePlayerChessboard.F, null),  new Square(10, ThreePlayerChessboard.G, null),
                        new Square(11, ThreePlayerChessboard.H, null), new Square(9, ThreePlayerChessboard.I, null),  new Square(10, ThreePlayerChessboard.J, null),
                        new Square(11, ThreePlayerChessboard.K, null), new Square(3, ThreePlayerChessboard.D, null),  new Square(2, ThreePlayerChessboard.C, null),  new Square(1, ThreePlayerChessboard.B, null),
                        new Square(0,  ThreePlayerChessboard.A, null), new Square(4, ThreePlayerChessboard.I, null),  new Square(5, ThreePlayerChessboard.J, null),  new Square(3, ThreePlayerChessboard.H, null),
                        new Square(7,  ThreePlayerChessboard.L, null)
                )
        );
    }

    @Test
    public void testMoves3F() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 3, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 22);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(2,  ThreePlayerChessboard.G, null),
                        new Square(1,  ThreePlayerChessboard.H, null),
                        new Square(8,  ThreePlayerChessboard.G, null),
                        new Square(9,  ThreePlayerChessboard.H, null),
                        new Square(2,  ThreePlayerChessboard.E, null),
                        new Square(1,  ThreePlayerChessboard.D, null),
                        new Square(0,  ThreePlayerChessboard.C, null),
                        new Square(8,  ThreePlayerChessboard.E, null),
                        new Square(9,  ThreePlayerChessboard.F, null),
                        new Square(10,  ThreePlayerChessboard.G, null),
                        new Square(11,  ThreePlayerChessboard.H, null),
                        new Square(9,  ThreePlayerChessboard.I, null),
                        new Square(10,  ThreePlayerChessboard.J, null),
                        new Square(11,  ThreePlayerChessboard.K, null),
                        new Square(4,  ThreePlayerChessboard.I, null),
                        new Square(5,  ThreePlayerChessboard.J, null),
                        new Square(6,  ThreePlayerChessboard.K, null),
                        new Square(7,  ThreePlayerChessboard.L, null),
                        new Square(3,  ThreePlayerChessboard.D, null),
                        new Square(2,  ThreePlayerChessboard.C, null),
                        new Square(1,  ThreePlayerChessboard.B, null),
                        new Square(0,  ThreePlayerChessboard.A, null)
                )
        );
    }

    @Test
    public void testMoves2GBlockedByPieces() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.G);
        Piece pawn1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 1, ThreePlayerChessboard.H);
        Piece pawn2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 8, ThreePlayerChessboard.E);
        Piece pawn3 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 0, ThreePlayerChessboard.E);

        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1, ThreePlayerChessboard.F, null), // left diagonal same sextant
                        new Square(3,  ThreePlayerChessboard.H, null), // right diagonal same sextant
                        new Square(3,  ThreePlayerChessboard.F, null) // top diagonal same sextant
                )
        );
    }

    @Test
    public void testMoves0F() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 0, ThreePlayerChessboard.F);
        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 7);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,  ThreePlayerChessboard.E, null),
                        new Square(2,  ThreePlayerChessboard.D, null),
                        new Square(3,  ThreePlayerChessboard.C, null),
                        new Square(4,  ThreePlayerChessboard.B, null),
                        new Square(5,  ThreePlayerChessboard.A, null),
                        new Square(1,  ThreePlayerChessboard.G, null),
                        new Square(2,  ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testMoves11KCanEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 11, ThreePlayerChessboard.K);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 1, ThreePlayerChessboard.B);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 10, ThreePlayerChessboard.G);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 11, ThreePlayerChessboard.H);

        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 16);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10,  ThreePlayerChessboard.L, null),
                        new Square(10,  ThreePlayerChessboard.J, null),
                        new Square(9,  ThreePlayerChessboard.I, null),

                        new Square(8,  ThreePlayerChessboard.E, null),
                        new Square(9,  ThreePlayerChessboard.F, null),
                        new Square(10,  ThreePlayerChessboard.G, null),

                        new Square(3,  ThreePlayerChessboard.F, null),
                        new Square(2,  ThreePlayerChessboard.G, null),
                        new Square(1,  ThreePlayerChessboard.H, null),

                        new Square(3,  ThreePlayerChessboard.D, null),
                        new Square(2,  ThreePlayerChessboard.C, null),
                        new Square(1,  ThreePlayerChessboard.B, null),

                        new Square(4,  ThreePlayerChessboard.I, null),
                        new Square(5,  ThreePlayerChessboard.J, null),
                        new Square(6,  ThreePlayerChessboard.K, null),
                        new Square(7,  ThreePlayerChessboard.L, null)
                )
        );
    }

    @Test
    public void testMoves10LBlockedByPiece() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.L);
        Piece pawn1 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 4, ThreePlayerChessboard.I);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 6, ThreePlayerChessboard.A);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 1, ThreePlayerChessboard.B);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 0, ThreePlayerChessboard.A);
        Piece pawn5 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn5, 1, ThreePlayerChessboard.F);
        Piece pawn6 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn6, 0, ThreePlayerChessboard.G);
        Piece pawn7 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn7, 10, ThreePlayerChessboard.G);
        Piece pawn8 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn8, 11, ThreePlayerChessboard.H);

        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 3);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11,  ThreePlayerChessboard.K, null),
                        new Square(9,  ThreePlayerChessboard.K, null),
                        new Square(8,  ThreePlayerChessboard.J, null)
                )
        );
    }

    @Test
    public void testMoves10LCanEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 10, ThreePlayerChessboard.L);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 4, ThreePlayerChessboard.I);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 6, ThreePlayerChessboard.A);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 1, ThreePlayerChessboard.B);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 0, ThreePlayerChessboard.A);
        Piece pawn5 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn5, 1, ThreePlayerChessboard.F);
        Piece pawn6 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn6, 0, ThreePlayerChessboard.G);
        Piece pawn7 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn7, 10, ThreePlayerChessboard.G);
        Piece pawn8 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn8, 11, ThreePlayerChessboard.H);

        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 4);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(11,  ThreePlayerChessboard.K, null),
                        new Square(9,  ThreePlayerChessboard.K, null),
                        new Square(8,  ThreePlayerChessboard.J, null),
                        new Square(4,  ThreePlayerChessboard.I, null)
                )
        );
    }

    @Test
    public void testMoves8HCanEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.H);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 10, ThreePlayerChessboard.F);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 11, ThreePlayerChessboard.E);

        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 6);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9,  ThreePlayerChessboard.G, null),
                        new Square(10,  ThreePlayerChessboard.F, null),
                        new Square(3,  ThreePlayerChessboard.G, null),
                        new Square(2,  ThreePlayerChessboard.F, null),
                        new Square(1,  ThreePlayerChessboard.E, null),
                        new Square(0,  ThreePlayerChessboard.D, null)
                )
        );
    }

    @Test
    public void testMoves2FCanEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.F);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 6, ThreePlayerChessboard.B);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 10, ThreePlayerChessboard.K);
        Piece pawn3 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 0, ThreePlayerChessboard.D);
        Piece pawn4 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 0, ThreePlayerChessboard.H);

        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 11);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(1,  ThreePlayerChessboard.G, null),
                        new Square(1,  ThreePlayerChessboard.E, null),
                        new Square(3,  ThreePlayerChessboard.G, null),
                        new Square(8,  ThreePlayerChessboard.H, null),
                        new Square(3,  ThreePlayerChessboard.E, null),
                        new Square(8,  ThreePlayerChessboard.I, null),
                        new Square(9,  ThreePlayerChessboard.J, null),
                        new Square(4,  ThreePlayerChessboard.D, null),
                        new Square(5,  ThreePlayerChessboard.C, null),
                        new Square(6,  ThreePlayerChessboard.B, null),
                        new Square(10,  ThreePlayerChessboard.K, null)
                )
        );
    }

    @Test
    public void testMoves8FCanEat() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.F);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 10, ThreePlayerChessboard.I);
        Piece pawn2 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 1, ThreePlayerChessboard.C);

        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 19);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.G, null),  new Square(9, ThreePlayerChessboard.E, null),  new Square(10, ThreePlayerChessboard.H, null),
                        new Square(3, ThreePlayerChessboard.G, null),  new Square(2, ThreePlayerChessboard.H, null),
                        new Square(3, ThreePlayerChessboard.E, null),  new Square(2, ThreePlayerChessboard.D, null),
                        new Square(10, ThreePlayerChessboard.I, null),
                        new Square(8, ThreePlayerChessboard.I, null),  new Square(9, ThreePlayerChessboard.J, null),  new Square(10, ThreePlayerChessboard.K, null),  new Square(11, ThreePlayerChessboard.L, null),
                        new Square(4, ThreePlayerChessboard.D, null),  new Square(5, ThreePlayerChessboard.C, null),  new Square(6, ThreePlayerChessboard.B, null), new Square(7, ThreePlayerChessboard.A, null),
                        new Square(2, ThreePlayerChessboard.F, null),  new Square(1, ThreePlayerChessboard.G, null), new Square(0, ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testMoves4J() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.J);
        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 22);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.K, null),  new Square(6, ThreePlayerChessboard.L, null),
                        new Square(8, ThreePlayerChessboard.K, null),  new Square(9, ThreePlayerChessboard.L, null),
                        new Square(5, ThreePlayerChessboard.I, null),  new Square(6, ThreePlayerChessboard.D, null), new Square(7, ThreePlayerChessboard.C, null),
                        new Square(9, ThreePlayerChessboard.E, null),  new Square(10, ThreePlayerChessboard.F, null),  new Square(11, ThreePlayerChessboard.G, null),
                        new Square(8, ThreePlayerChessboard.I, null),  new Square(9, ThreePlayerChessboard.J, null),  new Square(10, ThreePlayerChessboard.K, null), new Square(11, ThreePlayerChessboard.L, null),
                        new Square(4, ThreePlayerChessboard.D, null),  new Square(5, ThreePlayerChessboard.C, null),  new Square(6, ThreePlayerChessboard.B, null), new Square(7, ThreePlayerChessboard.A, null),
                        new Square(3, ThreePlayerChessboard.E, null),  new Square(2, ThreePlayerChessboard.F, null),  new Square(1, ThreePlayerChessboard.G, null), new Square(0, ThreePlayerChessboard.H, null)
                )
        );
    }

    @Test
    public void testMoves5I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 5, ThreePlayerChessboard.I);
        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 22);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(6, ThreePlayerChessboard.J, null),  new Square(7, ThreePlayerChessboard.K, null),
                       // new Square(6, ThreePlayerChessboard.D, null),  new Square(7, ThreePlayerChessboard.C, null),
                        new Square(4, ThreePlayerChessboard.J, null),  new Square(8, ThreePlayerChessboard.K, null), new Square(9, ThreePlayerChessboard.L, null),
                        new Square(3, ThreePlayerChessboard.C, null),  new Square(2, ThreePlayerChessboard.B, null),  new Square(1, ThreePlayerChessboard.A, null),
                        new Square(8, ThreePlayerChessboard.I, null),  new Square(9, ThreePlayerChessboard.J, null),  new Square(10, ThreePlayerChessboard.K, null), new Square(11, ThreePlayerChessboard.L, null),
                        new Square(4, ThreePlayerChessboard.D, null),  new Square(5, ThreePlayerChessboard.C, null),  new Square(6, ThreePlayerChessboard.B, null), new Square(7, ThreePlayerChessboard.A, null),
                        new Square(3, ThreePlayerChessboard.E, null),  new Square(2, ThreePlayerChessboard.F, null),  new Square(1, ThreePlayerChessboard.G, null), new Square(0, ThreePlayerChessboard.H, null)
                        )
        );
    }

    @Test
    public void testMoves4D() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 4, ThreePlayerChessboard.D);
        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 17);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(5, ThreePlayerChessboard.I, null),  new Square(6, ThreePlayerChessboard.J, null), new Square(7, ThreePlayerChessboard.K, null),
                        new Square(3, ThreePlayerChessboard.C, null),  new Square(2, ThreePlayerChessboard.B, null),  new Square(1, ThreePlayerChessboard.A, null),
                        new Square(8, ThreePlayerChessboard.I, null),  new Square(9, ThreePlayerChessboard.J, null),  new Square(10, ThreePlayerChessboard.K, null), new Square(11, ThreePlayerChessboard.L, null),
                        new Square(5, ThreePlayerChessboard.C, null),  new Square(6, ThreePlayerChessboard.B, null), new Square(7, ThreePlayerChessboard.A, null),
                        new Square(3, ThreePlayerChessboard.E, null),  new Square(2, ThreePlayerChessboard.F, null),  new Square(1, ThreePlayerChessboard.G, null), new Square(0, ThreePlayerChessboard.H, null)
                        )
        );
    }


    @Test
    public void testMoves9I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.I);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 10, ThreePlayerChessboard.J);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 10, ThreePlayerChessboard.E);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 10, ThreePlayerChessboard.G);
        Piece pawn4 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 6, ThreePlayerChessboard.K);
        Piece pawn5 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn5, 0, ThreePlayerChessboard.A);

        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 17);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(8, ThreePlayerChessboard.J, null),  new Square(10, ThreePlayerChessboard.J, null),
                        new Square(10, ThreePlayerChessboard.E, null),
                        new Square(4, ThreePlayerChessboard.K, null),  new Square(5, ThreePlayerChessboard.L, null),
                        new Square(3, ThreePlayerChessboard.F, null),  new Square(2, ThreePlayerChessboard.G, null), new Square(1, ThreePlayerChessboard.H, null),
                        new Square(4, ThreePlayerChessboard.I, null),  new Square(5, ThreePlayerChessboard.J, null), new Square(6, ThreePlayerChessboard.K, null),
                        new Square(3, ThreePlayerChessboard.D, null),  new Square(2, ThreePlayerChessboard.C, null), new Square(1, ThreePlayerChessboard.B, null),
                        new Square(8, ThreePlayerChessboard.E, null),  new Square(9, ThreePlayerChessboard.F, null), new Square(10, ThreePlayerChessboard.G, null)

                )
        );
    }

    @Test
    public void testMoves9F() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = (Bishop) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.BISHOP);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 9, ThreePlayerChessboard.F);
        Piece pawn1 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn1, 10, ThreePlayerChessboard.E);
        Piece pawn2 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn2, 10, ThreePlayerChessboard.G);
        Piece pawn3 = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn3, 6, ThreePlayerChessboard.K);
        Piece pawn4 = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.PAWN);
        board.setPiece(pawn4, 0, ThreePlayerChessboard.A);


        moves = new DiagonalMoves(bishop, board).moves();
        assertTrue(moves.size() == 11);
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(10, ThreePlayerChessboard.E, null),
                        new Square(10, ThreePlayerChessboard.G, null),
                        new Square(8, ThreePlayerChessboard.E, null),
                        new Square(8, ThreePlayerChessboard.G, null),
                        new Square(3, ThreePlayerChessboard.H, null),
                        new Square(4, ThreePlayerChessboard.I, null),  new Square(5, ThreePlayerChessboard.J, null), new Square(6, ThreePlayerChessboard.K, null),
                        new Square(3, ThreePlayerChessboard.D, null),  new Square(2, ThreePlayerChessboard.C, null), new Square(1, ThreePlayerChessboard.B, null)

                )
        );
    }
}
