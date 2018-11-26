package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.gui.Chessboard;

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
    public static Piece getPiece(Chessboard board, Player player, Piece.PieceTypes pieceType) {
        Piece newPiece = null;
        String piece = pieceType.toString();
        switch (piece) {
            case BISHOP:
                newPiece = new Bishop(board, player);
                break;
            case KING:
                newPiece = new King(board, player);
                break;
            case KNIGHT:
                newPiece = new Knight(board, player);
                break;
            case PAWN:
                newPiece = new Pawn(board, player);
                break;
            case QUEEN:
                newPiece = new Queen(board, player);
                break;
            case ROOK:
                newPiece = new Rook(board, player);
                break;
            default:
                newPiece = null;
                throw new IllegalArgumentException("Invalid type of a piece " + piece);
        }
        return newPiece;
    }
}
