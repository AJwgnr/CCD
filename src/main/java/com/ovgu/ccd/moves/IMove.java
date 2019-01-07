package com.ovgu.ccd.moves;

import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

public interface IMove {
    ArrayList<Square> moves() throws Exception;
}
