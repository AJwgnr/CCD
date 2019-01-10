package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.moves.StraightMoveFactory;

import java.util.ArrayList;

/**
 * Class to represent a chess pawn rook
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

    boolean wasMotion = false;

    /**
     *
     * @param chessboard
     * @param player
     */
    protected Rook(IBoard chessboard, Player player) {
        super(chessboard, player);
        this.symbol = "R";
        imageWhite = ResourceManager.loadImage("Rook-W.png");
        imageBlack = ResourceManager.loadImage("Rook-B.png");
        imageGray = ResourceManager.loadImage("Rook-G.png");
        this.setImage();
    }


    /**
     * Annotation to superclass Piece changing pawns location
     *
     * @return ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves() {
        ArrayList moves = new ArrayList();
        try {
            moves.addAll(StraightMoveFactory.getMoves(chessboard, this).moves());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moves;
    }


    /**
     *
     * @return
     */
    public boolean isWasMotion() {
        return wasMotion;
    }

    /**
     *
     * @param wasMotion
     */
    public void setWasMotion(boolean wasMotion) {
        this.wasMotion = wasMotion;
    }
}
