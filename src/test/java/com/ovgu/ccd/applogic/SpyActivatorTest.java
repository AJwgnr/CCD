package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.PieceFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class SpyActivatorTest {

    Player whitePlayer;
    Player blackPlayer;
    Player greyPlayer;
    ThreePlayerChessboard board = new ThreePlayerChessboard();;

    @Before
    public void initObjects(){
        board.initPlayers();
        this.whitePlayer = board.getWhitePlayer();
        this.blackPlayer = board.getBlackPlayer();
        this.greyPlayer = board.getGreyPlayer();

        Piece whiteKing = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
        board.setKingWhite((King) whiteKing);
        board.setPiece(whiteKing, 0, ThreePlayerChessboard.E);

        Piece blackKing = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.KING);
        board.setKingBlack((King) blackKing);
        board.setPiece(blackKing, 7, ThreePlayerChessboard.I);

        Piece greyKing = PieceFactory.getPiece(board, greyPlayer, Piece.PieceTypes.KING);
        board.setKingGrey((King) greyKing);
        board.setPiece(greyKing, 11, ThreePlayerChessboard.I);

        board.setupBlackPawns();
        board.setupWhitePawns();
        board.setupGreyPawns();
    }

    @Test(expected = NoPieceForSpy.class)
    public void testWithNullPiece() throws Exception {
        new SpyActivator(whitePlayer, null, board).activateSpy();
    }

    @Test(expected = SpyCanOnlyBeAPawn.class)
    public void testWithPieceDifferentFromPawn() throws Exception {
        Piece queen = PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.QUEEN);
        new SpyActivator(whitePlayer, queen, board).activateSpy();
    }

    @Test(expected = SpyInCheck.class)
    public void testInCheck() throws Exception {
        Piece pawn = board.whitePawns.get(0);
        Piece rook = PieceFactory.getPiece(board, blackPlayer, Piece.PieceTypes.ROOK);
        board.setPiece(rook, 0, ThreePlayerChessboard.A);

        new SpyActivator(whitePlayer, pawn, board).activateSpy();
    }

    @Test(expected = SpyAlreadyActive.class)
    public void testAlreadyActivatedSpy() throws Exception {
        Piece pawn = board.whitePawns.get(0);
        board.setWhiteSpyActive(true);
        new SpyActivator(whitePlayer, pawn, board).activateSpy();
    }

    @Test
    public void testActivateWhiteSpy() throws Exception {
        Piece pawn = board.blackPawns.get(0);
        board.setPiece(pawn, pawn.getPosX() - 2, pawn.getPosY());
        new SpyActivator(whitePlayer, pawn, board).activateSpy();

        assertTrue(board.isWhiteSpyActive());
        assertTrue(board.whitePawns.contains(pawn));
        assertTrue(pawn.getColor() == Player.Colors.WHITE);
        assertTrue(!board.blackPawns.contains(pawn));
    }

    @Test
    public void testActivateBlackSpy() throws Exception {
        Piece pawn = board.whitePawns.get(0);
        board.setPiece(pawn, pawn.getPosX() + 2, pawn.getPosY());
        new SpyActivator(blackPlayer, pawn, board).activateSpy();

        assertTrue(board.isBlackSpyActive());
        assertTrue(board.blackPawns.contains(pawn));
        assertTrue(pawn.getColor() == Player.Colors.BLACK);
        assertTrue(!board.whitePawns.contains(pawn));
    }

    @Test
    public void testActivateGreySpy() throws Exception {
        Piece pawn = board.blackPawns.get(0);
        board.setPiece(pawn, pawn.getPosX() - 2, pawn.getPosY());
        new SpyActivator(greyPlayer, pawn, board).activateSpy();

        assertTrue(board.isGreySpyActive());
        assertTrue(board.greyPawns.contains(pawn));
        assertTrue(pawn.getColor() == Player.Colors.GREY);
        assertTrue(!board.blackPawns.contains(pawn));
    }
}