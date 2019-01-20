package com.ovgu.ccd.pieces;


import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.moves.KnightMoveFactory;

import java.util.ArrayList;

/** Class to represent a chess knight.
 */
public class Knight extends Piece {


    /**
     * @param chessboard board on which the Bishop is places
     * @param player player the Bishop is assigned
     */
    protected Knight(final IBoard chessboard, final Player player) {
        super(chessboard, player);
        this.symbol = "N";
        imageWhite = ResourceManager.loadImage("Knight-W.png");
        imageBlack = ResourceManager.loadImage("Knight-B.png");
        imageGray = ResourceManager.loadImage("Knight-G.png");
        this.setImage(false);
    }

    /**Determines all possible Moves of this piece.
     * @return ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves() {
        ArrayList<Square> moves = new ArrayList<Square>();
        try {
            moves.addAll(KnightMoveFactory.getMoves(chessboard, this).moves());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moves;
    }
}
