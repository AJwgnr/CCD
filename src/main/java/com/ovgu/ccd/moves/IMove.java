package com.ovgu.ccd.moves;

import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

/**
 * This interface returns an ArrayList
 * of squares of the chessboard.
 *
 */

public interface IMove {

    /**
     * This function returns the list of squares of the moves.
     * @return an ArrayList of the squares of the chessboard
     * @throws Exception if certain exception occurs then it throws
     */
    ArrayList<Square> moves() throws Exception;
}
