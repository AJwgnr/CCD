package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.moves.DiagonalMoveFactory;

import java.util.ArrayList;

/** Class to represent a chess pawn bishop.
 * Bishop can move across the chessboard
 * <p>
 * |_|_|_|_|_|_|_|X|7
 * |X|_|_|_|_|_|X|_|6
 * |_|X|_|_| |X|_|_|5
 * |_|_|X|_|X|_|_|_|4
 * |_|_|_|B|_|_|_|_|3
 * |_| |X|_|X|_|_|_|2
 * |_|X|_|_|_|X|_|_|1
 * |X|_|_|_|_|_|X|_|0
 * 0 1 2 3 4 5 6 7
 */
public class Bishop extends Piece {

    /** Creates a bishop on the given board for the given player.
     * @param chessboard board on which the Bishop is places
     * @param player player the Bishop is assigned
     */
    protected Bishop(final IBoard chessboard, final Player player) {
        super(chessboard, player);
        this.symbol = "B";
        imageWhite = ResourceManager.loadImage("Bishop-W.png");
        imageBlack = ResourceManager.loadImage("Bishop-B.png");
        imageGray = ResourceManager.loadImage("Bishop-G.png");
        this.setImage();
    }


    /** Determines all possible Moves of this piece.
     * @return ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves() {
        ArrayList<Square> moves = new ArrayList<>();
        try {
            moves.addAll(DiagonalMoveFactory.getMoves(chessboard, this).moves());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moves;
    }

}
