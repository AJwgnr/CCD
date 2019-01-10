package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;

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
     *
     * @param board
     * @param player
     * @param pieceType
     * @return
     */
    public static Piece getPiece(final IBoard board, final Player player, final Piece.PieceTypes pieceType) {
        String piece = pieceType.toString();
        switch (piece) {
            case BISHOP:
                return (new Bishop(board, player));
            case KING:
                return (new King(board, player));
            case KNIGHT:
                return (new Knight(board, player));
            case PAWN:
                return (new Pawn(board, player));
            case QUEEN:
                return (new Queen(board, player));
            case ROOK:
                return (new Rook(board, player));
            default:
                throw new IllegalArgumentException("Invalid type of a piece " + piece);
        }
    }
}
