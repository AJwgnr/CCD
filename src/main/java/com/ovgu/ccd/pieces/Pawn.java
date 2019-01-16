package com.ovgu.ccd.pieces;


import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.moves.PawnMoveFactory;

import java.util.ArrayList;

/**Class to represent a pawn piece.
 * Pawn can move only forvard and can beat only across
 * In first move pawn can move 2 sqares
 * pawn can be upgreade to rook, knight, bishop, Queen if it's in the
 * squers nearest the side where opponent is lockated
 * Firat move of pawn:
 * |_|_|_|_|_|_|_|_|7
 * |_|_|_|_|_|_|_|_|6
 * |_|_|_|X|_|_|_|_|5
 * |_|_|_|X|_|_|_|_|4
 * |_|_|_|P|_|_|_|_|3
 * |_|_|_|_|_|_|_|_|2
 * |_|_|_|_|_|_|_|_|1
 * |_|_|_|_|_|_|_|_|0
 * 0 1 2 3 4 5 6 7
 * <p>
 * Move of a pawn:
 * |_|_|_|_|_|_|_|_|7
 * |_|_|_|_|_|_|_|_|6
 * |_|_|_|_|_|_|_|_|5
 * |_|_|_|X|_|_|_|_|4
 * |_|_|_|P|_|_|_|_|3
 * |_|_|_|_|_|_|_|_|2
 * |_|_|_|_|_|_|_|_|1
 * |_|_|_|_|_|_|_|_|0
 * 0 1 2 3 4 5 6 7
 * Beats with can take pawn:
 * |_|_|_|_|_|_|_|_|7
 * |_|_|_|_|_|_|_|_|6
 * |_|_|_|_|_|_|_|_|5
 * |_|_|X|_|X|_|_|_|4
 * |_|_|_|P|_|_|_|_|3
 * |_|_|_|_|_|_|_|_|2
 * |_|_|_|_|_|_|_|_|1
 * |_|_|_|_|_|_|_|_|0
 * 0 1 2 3 4 5 6 7
 */
public class Pawn extends Piece {

    /** Creates a pawn on the given chessboard for the given player
     * @param chessboard board on which the pawn is placed
     * @param player player the pawn is assigned
     */
    protected Pawn(final IBoard chessboard, final Player player) {
        super(chessboard, player);
        this.symbol = "";
        imageWhite = ResourceManager.loadImage("Pawn-W.png");
        imageBlack = ResourceManager.loadImage("Pawn-B.png");
        imageGray = ResourceManager.loadImage("Pawn-G.png");
        this.setImage();
    }

    /**Determines all possible Moves of this piece.
     * @return ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves() {
        ArrayList<Square> moves = new ArrayList<>();
        try {
            moves.addAll(PawnMoveFactory.getMoves(chessboard, this).moves());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moves;
    }

    /**Promotes the pawn into a new piece.
     * @param newPiece piece the pawn should be promoted to
     */
    void promote(final Piece newPiece) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
