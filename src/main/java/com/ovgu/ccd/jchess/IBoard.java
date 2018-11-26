package com.ovgu.ccd.jchess;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Pawn;
import com.ovgu.ccd.pieces.Square;

import java.awt.*;

public interface IBoard {

    boolean validMove(Square square);

    King getKingWhite();

    void setKingWhite(King kingWhite);

    King getKingBlack();

    void setKingBlack(King kingBlack);

    Pawn getTwoSquareMovedPawn();

    void setTwoSquareMovedPawn(Pawn twoSquareMovedPawn);

    //Old methods
    void setPieces(String places, Player plWhite, Player plBlack);

    Square getSquareConsideringLabels(int x, int y);

    void select(Square sq);

    void unselect();

    int get_widht();

    int get_height();

    int get_widht(boolean includeLables);

    int get_square_height();

    void move(Square begin, Square end);

    void move(int xFrom, int yFrom, int xTo, int yTo);

    void move(Square begin, Square end, boolean refresh);

    void move(Square begin, Square end, boolean refresh, boolean clearForwardHistory);

    King myKing(Player.Colors color);

    Square getSquare(int xCoordinate, int yCoordinate);

    Square[][] getSquares();

    Point getTopLeftPoint();
}
