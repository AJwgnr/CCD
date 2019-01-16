package com.ovgu.ccd.pieces;


import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.moves.QueenMoveFactory;

import java.util.ArrayList;

/** Class to represent a queen piece.
 * Queen can move almost in every way:
 * |_|_|_|X|_|_|_|X|7
 * |X|_|_|X|_|_|X|_|6
 * |_|X|_|X|_|X|_|_|5
 * |_|_|X|X|x|_|_|_|4
 * |X|X|X|Q|X|X|X|X|3
 * |_|_|X|X|X|_|_|_|2
 * |_|X|_|X|_|X|_|_|1
 * |X|_|_|X|_|_|X|_|0
 * 0 1 2 3 4 5 6 7
 */
public class Queen extends Piece {


    /**
     * @param chessboard board on which the Bishop is places
     * @param player player the Bishop is assigned
     */
    protected Queen(final IBoard chessboard, final Player player) {
        super(chessboard, player);
        this.symbol = "Q";
        imageWhite = ResourceManager.loadImage("Queen-W.png");
        imageBlack = ResourceManager.loadImage("Queen-B.png");
        imageGray = ResourceManager.loadImage("Queen-G.png");
        this.setImage();
    }

    /** Determines all possible Moves of this piece.
     * @return ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves() {
        ArrayList<Square> moves = new ArrayList<>();
        try {
            moves.addAll(QueenMoveFactory.getMoves(chessboard, this).moves());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moves;
    }
}
