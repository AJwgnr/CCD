package com.ovgu.ccd.moves.two;

import com.ovgu.ccd.gui.twoplayer.Chessboard;
import com.ovgu.ccd.gui.twoplayer.Moves.castling;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

/**
 * Represents a move done by a player
 */
public class MoveHistoryEntry {

    /**
     * where the move starts from
     */
    private Square from = null;
    /**
     * where the move ends
     */
    private Square to = null;
    /**
     * piece that was moved
     */
    private Piece movedPiece = null;
    /**
     * piece that was taken in the move
     */
    private Piece takenPiece = null;
    /**
     * piece that was promoted
     */
    private Piece promotedTo = null;
    /**
     * flag to check if it was an en passant move
     */
    private boolean wasEnPassant = false;
    /**
     * castling move
     */
    private castling castlingMove = castling.none;
    /**
     * flag to check if a pawn was moved 2 squares
     */
    private boolean wasPawnTwoFieldsMove = false;

    /**
     * @param from where move starts
     * @param to where move ends
     * @param movedPiece piece that was moved
     * @param takenPiece during the move
     * @param castlingMove possible casting
     * @param wasEnPassant if it was an en passant move
     * @param promotedPiece during the move
     */
    public MoveHistoryEntry(final Square from, final Square to, final Piece movedPiece, final Piece takenPiece, final castling castlingMove, final boolean wasEnPassant, final Piece promotedPiece) {
        this.setFrom(from);
        this.setTo(to);
        this.setMovedPiece(movedPiece);
        this.setTakenPiece(takenPiece);
        this.setCastlingMove(castlingMove);
        this.setWasEnPassant(wasEnPassant);

        if ("Pawn".equals(movedPiece.name) && Math.abs(to.getPosY() - from.getPosY()) == 2) {
            this.setWasPawnTwoFieldsMove(true);
        } else if ("Pawn".equals(movedPiece.name) && to.getPosY() == Chessboard.bottom || to.getPosY() == Chessboard.top && promotedPiece != null) {
            this.setPromotedTo(promotedPiece);
        }
    }

    /**
     * @return square from where the move was originated
     */
    public Square getFrom() {
        return this.from;
    }

    /**
     * @return square to where the move was done
     */
    public Square getTo() {
        return this.to;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    /**
     * @return the taken piece
     */
    public Piece getTakenPiece() {
        return this.takenPiece;
    }

    /**
     * @return if it was an en passant move
     */
    public boolean wasEnPassant() {
        return this.isWasEnPassant();
    }

    /**
     * @return if a pawn was moved two squares
     */
    public boolean wasPawnTwoFieldsMove() {
        return this.isWasPawnTwoFieldsMove();
    }

    /**
     * @return the castling
     */
    public castling getCastlingMove() {
        return this.castlingMove;
    }

    /**
     * @return the promoted piece
     */
    public Piece getPromotedPiece() {
        return this.getPromotedTo();
    }

    /**
     * @param from square where the move was originated
     */
    private void setFrom(final Square from) {
        this.from = from;
    }

    /**
     * @param to square where the move ends
     */
    private void setTo(final Square to) {
        this.to = to;
    }

    /**
     * @param movedPiece piece that was moved
     */
    private void setMovedPiece(final Piece movedPiece) {
        this.movedPiece = movedPiece;
    }

    /**
     * @param takenPiece piece to be taken
     */
    private void setTakenPiece(final Piece takenPiece) {
        this.takenPiece = takenPiece;
    }

    /**
     * @return promoted piece
     */
    public Piece getPromotedTo() {
        return promotedTo;
    }

    /**
     * @param promotedTo piece
     */
    private void setPromotedTo(final Piece promotedTo) {
        this.promotedTo = promotedTo;
    }

    /**
     * @return true if move was en passant
     */
    public boolean isWasEnPassant() {
        return wasEnPassant;
    }

    /**
     * @param wasEnPassant boolean that determines it
     */
    private void setWasEnPassant(final boolean wasEnPassant) {
        this.wasEnPassant = wasEnPassant;
    }

    /**
     * @param castlingMove castling move
     */
    private void setCastlingMove(final castling castlingMove) {
        this.castlingMove = castlingMove;
    }

    /**
     * @return true if a pawn was moved two squares
     */
    public boolean isWasPawnTwoFieldsMove() {
        return wasPawnTwoFieldsMove;
    }

    /**
     * @param wasPawnTwoFieldsMove sets the flag
     */
    private void setWasPawnTwoFieldsMove(final boolean wasPawnTwoFieldsMove) {
        this.wasPawnTwoFieldsMove = wasPawnTwoFieldsMove;
    }
}
