package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.moves.StraightMoveFactory;

import java.util.ArrayList;

/**Class to represent a chess pawn rook.
 * Rook can move:
 * |_|_|_|X|_|_|_|_|7
 * |_|_|_|X|_|_|_|_|6
 * |_|_|_|X|_|_|_|_|5
 * |_|_|_|X|_|_|_|_|4
 * |X|X|X|B|X|X|X|X|3
 * |_|_|_|X|_|_|_|_|2
 * |_|_|_|X|_|_|_|_|1
 * |_|_|_|X|_|_|_|_|0
 * 0 1 2 3 4 5 6 7
 */
public class Rook extends Piece {

    /**
     */
    boolean wasMotion = false;

    /**
     * @param chessboard board on which the rook is places
     * @param player player the rook is assigned
     */
    protected Rook(final IBoard chessboard, final Player player) {
        super(chessboard, player);
        this.symbol = "R";
        imageWhite = ResourceManager.loadImage("Rook-W.png");
        imageBlack = ResourceManager.loadImage("Rook-B.png");
        imageGray = ResourceManager.loadImage("Rook-G.png");
        this.setImage();
    }


    /** Determines all possible Moves of this piece.
     * @return ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves() {
        ArrayList<Square> moves = new ArrayList<>();
        try {
            moves.addAll(StraightMoveFactory.getMoves(chessboard, this).moves());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moves;
    }


    /**
     * @return
     */
    public boolean isWasMotion() {
        return wasMotion;
    }

    /**
     * @param wasMotion
     */
    public void setWasMotion(final boolean wasMotion) {
        this.wasMotion = wasMotion;
    }
}
