package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;

/** Factory class that creates the different types of chess pieces.
 * and assigns them to a board and a player
 */
public class PieceFactory {

    /** Bishop Piece string.
     */
    private static final String BISHOP = "BISHOP";
    /** King Piece string.
     */
    private static final String KING = "KING";
    /** Queen Piece string.
     */
    private static final String QUEEN = "QUEEN";
    /** Knight Piece string.
     */
    private static final String KNIGHT = "KNIGHT";
    /** Rook Piece string.
     */
    private static final String ROOK = "ROOK";
    /** Pawn Piece string.
     */
    private static final String PAWN = "PAWN";

    /** Creates a piece (pieceType) on the provided board for the provided player.
     * @param board Board to place the piece on
     * @param player that will get the piece
     * @param pieceType type of the piece that will be created
     * @return the create piece
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
