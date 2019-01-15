package com.ovgu.ccd.moves;

import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

/**
 * Interface for classes that implement moves
 */
public interface IMove {
    /**
     * @return a list of moves
     * @throws Exception in case of invalid moves
     */
    ArrayList<Square> moves() throws Exception;
}
