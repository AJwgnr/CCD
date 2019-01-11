package com.ovgu.ccd.pieces;


import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.moves.KnightMoveFactory;

import java.util.ArrayList;

/**
 * Class to represent a chess pawn knight
 */
public class Knight extends Piece {



    /**
     *
     * @param chessboard
     * @param player
     */
    protected Knight(IBoard chessboard, Player player) {
        super(chessboard, player);
        this.symbol = "N";
        imageWhite = ResourceManager.loadImage("Knight-W.png");
        imageBlack = ResourceManager.loadImage("Knight-B.png");
        imageGray = ResourceManager.loadImage("Knight-G.png");
        this.setImage();
    }

    /**
     * Annotation to superclass Piece changing pawns location
     *
     * @return ArrayList with new possition of pawn
     */
    @Override
    public ArrayList allMoves() {
        ArrayList moves = new ArrayList();
        try {
            moves.addAll(KnightMoveFactory.getMoves(chessboard, this).moves());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moves;
    }
}
