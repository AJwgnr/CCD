package com.ovgu.ccd.jchess.three;

import com.ovgu.ccd.gui.Player;
import com.ovgu.ccd.jchess.IBoard;
import com.ovgu.ccd.pieces.Bishop;
import com.ovgu.ccd.pieces.Square;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertTrue;

public class DiagonalMoveTest {

    Player whitePlayer = new Player("John", Player.colors.white.name());

    @Test
    public void testPerfectDiagonal() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = new Bishop(board, whitePlayer);

        //White diagonals
        ((ThreePlayerChessboard) board).setPiece(bishop, 0,  ThreePlayerChessboard.H);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,1,  ThreePlayerChessboard.G);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,2,  ThreePlayerChessboard.F);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,3,  ThreePlayerChessboard.E);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,8,  ThreePlayerChessboard.I);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,9,  ThreePlayerChessboard.J);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,10, ThreePlayerChessboard.K);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,11, ThreePlayerChessboard.L);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,7,  ThreePlayerChessboard.A);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,6,  ThreePlayerChessboard.B);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,5,  ThreePlayerChessboard.C);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,4,  ThreePlayerChessboard.D);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        //Black diagonals
        ((ThreePlayerChessboard) board).setPiece(bishop,0,  ThreePlayerChessboard.A);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,1,  ThreePlayerChessboard.B);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,2,  ThreePlayerChessboard.C);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,3,  ThreePlayerChessboard.D);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,4,  ThreePlayerChessboard.I);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,5,  ThreePlayerChessboard.J);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,6,  ThreePlayerChessboard.K);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,7,  ThreePlayerChessboard.L);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,8,  ThreePlayerChessboard.E);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,9,  ThreePlayerChessboard.F);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,10, ThreePlayerChessboard.G);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());

        ((ThreePlayerChessboard) board).setPiece(bishop,11, ThreePlayerChessboard.H);
        assertTrue(new DiagonalMove(bishop, board).perfectDiagonal());
    }

    @Test
    public void testSameSextantMovesTopRight() {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = new Bishop(board, whitePlayer);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.I);
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
        moves = new DiagonalMove(bishop, board).getSameSextantMoves(bishop.getSquare());
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
    }

    @Test
    public void testMoves8I() throws Exception {
        IBoard board = new ThreePlayerChessboard();
        Bishop bishop = new Bishop(board, whitePlayer);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 8, ThreePlayerChessboard.I);
        moves = new DiagonalMove(bishop, board).moves();
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
        Bishop bishop = new Bishop(board, whitePlayer);
        ArrayList<Square> moves;

        ((ThreePlayerChessboard) board).setPiece(bishop, 2, ThreePlayerChessboard.G);
        moves = new DiagonalMove(bishop, board).moves();
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
}
