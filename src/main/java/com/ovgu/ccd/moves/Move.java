/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Author: Mateusz Sławomir Lach ( matlak, msl )
 */
package com.ovgu.ccd.moves;

import com.ovgu.ccd.gui.Chessboard;
import com.ovgu.ccd.gui.Moves.castling;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

/**
 * Represents a move done by a player
 */
public class Move {

    private Square from = null;
    private Square to = null;
    private Piece movedPiece = null;
    private Piece takenPiece = null;
    private Piece promotedTo = null;
    private boolean wasEnPassant = false;
    private castling castlingMove = castling.none;
    private boolean wasPawnTwoFieldsMove = false;

    public Move(Square from, Square to, Piece movedPiece, Piece takenPiece, castling castlingMove, boolean wasEnPassant, Piece promotedPiece) {
        this.setFrom(from);
        this.setTo(to);
        this.setMovedPiece(movedPiece);
        this.setTakenPiece(takenPiece);
        this.setCastlingMove(castlingMove);
        this.setWasEnPassant(wasEnPassant);

        if (movedPiece.name.equals("Pawn") && Math.abs(to.getPosY() - from.getPosY()) == 2) {
            this.setWasPawnTwoFieldsMove(true);
        } else if (movedPiece.name.equals("Pawn") && to.getPosY() == Chessboard.bottom || to.getPosY() == Chessboard.top && promotedPiece != null) {
            this.setPromotedTo(promotedPiece);
        }
    }

    /**
     * @return
     */
    public Square getFrom() {
        return this.from;
    }

    /**
     * @return
     */
    public Square getTo() {
        return this.to;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    /**
     * @return
     */
    public Piece getTakenPiece() {
        return this.takenPiece;
    }

    /**
     * @return
     */
    public boolean wasEnPassant() {
        return this.isWasEnPassant();
    }

    /**
     * @return
     */
    public boolean wasPawnTwoFieldsMove() {
        return this.isWasPawnTwoFieldsMove();
    }

    /**
     * @return
     */
    public castling getCastlingMove() {
        return this.castlingMove;
    }

    /**
     * @return
     */
    public Piece getPromotedPiece() {
        return this.getPromotedTo();
    }

    /**
     * @param from
     */
    private void setFrom(Square from) {
        this.from = from;
    }

    /**
     * @param to
     */
    private void setTo(Square to) {
        this.to = to;
    }

    /**
     * @param movedPiece
     */
    private void setMovedPiece(Piece movedPiece) {
        this.movedPiece = movedPiece;
    }

    /**
     * @param takenPiece
     */
    private void setTakenPiece(Piece takenPiece) {
        this.takenPiece = takenPiece;
    }

    /**
     * @return
     */
    public Piece getPromotedTo() {
        return promotedTo;
    }

    /**
     * @param promotedTo
     */
    private void setPromotedTo(Piece promotedTo) {
        this.promotedTo = promotedTo;
    }

    /**
     * @return
     */
    public boolean isWasEnPassant() {
        return wasEnPassant;
    }

    /**
     * @param wasEnPassant
     */
    private void setWasEnPassant(boolean wasEnPassant) {
        this.wasEnPassant = wasEnPassant;
    }

    /**
     * @param castlingMove
     */
    private void setCastlingMove(castling castlingMove) {
        this.castlingMove = castlingMove;
    }

    /**
     * @return
     */
    public boolean isWasPawnTwoFieldsMove() {
        return wasPawnTwoFieldsMove;
    }

    /**
     * @param wasPawnTwoFieldsMove
     */
    private void setWasPawnTwoFieldsMove(boolean wasPawnTwoFieldsMove) {
        this.wasPawnTwoFieldsMove = wasPawnTwoFieldsMove;
    }
}
