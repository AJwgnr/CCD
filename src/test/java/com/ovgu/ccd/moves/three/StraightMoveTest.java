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

public class StraightMoveTest {

    Player whitePlayer = new Player("John", Player.Colors.WHITE.name());

    @Test
    public void testMovesIn3E() {
        IBoard board = new ThreePlayerChessboard();
        Piece rook = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 3, ThreePlayerChessboard.E);
        ArrayList<Square> moves = new StraightMove(rook, board).moves();

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
        ArrayList<Square> moves = new StraightMove(rook, board).moves();

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
        ArrayList<Square> moves = new StraightMove(rook, board).moves();

        Assert.assertEquals(14, moves.size());
        MatcherAssert.assertThat(
                moves,
                hasItems(
                        new Square(9, ThreePlayerChessboard.I, null),
                        new Square(9, ThreePlayerChessboard.E, null),
                        new Square(9, ThreePlayerChessboard.F, null),
                        new Square(9, ThreePlayerChessboard.G, null),
                        new Square(9, ThreePlayerChessboard.H, null),
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
}
