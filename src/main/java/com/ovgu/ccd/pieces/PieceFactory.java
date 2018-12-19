package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.IBoard;

/**
 *
 */
public class PieceFactory {

    private static final String BISHOP = "BISHOP";
    private static final String KING = "KING";
    private static final String QUEEN = "QUEEN";
    private static final String KNIGHT = "KNIGHT";
    private static final String ROOK = "ROOK";
    private static final String PAWN = "PAWN";

    /**
     * @param pieceType
     * @return
     */
    public static Piece getPiece(IBoard board, Player player, Piece.PieceTypes pieceType) {
        String piece = pieceType.toString();
        switch (piece) {
            case BISHOP:
                Bishop bishop = new Bishop(board, player);
                return bishop;
            case KING:
                King king = new King(board, player);
                return king;
            case KNIGHT:
                Knight knight = new Knight(board, player);
                return knight;
            case PAWN:
                Pawn pawn = new Pawn(board, player);
                return pawn;
            case QUEEN:
                Queen queen = new Queen(board, player);
                return queen;
            case ROOK:
                Rook rook = new Rook(board, player);
                return rook;
            default:
                throw new IllegalArgumentException("Invalid type of a piece " + piece);
        }
    }
}
