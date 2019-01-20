package com.ovgu.ccd.applogic;

import com.ovgu.ccd.gui.threeplayer.ChessboardGrid;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Pawn;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.awt.*;

/**
 * Interface that handles different boards
 */
public interface IBoard {

    /**
     * @param square representing where the place will be moved
     * @param piece piece to be moved
     * @return true if the move is valid
     */
    boolean validMove(Square square, Piece piece);

    /**
     * @return the white kings
     */
    King getKingWhite();

    /**
     * @param kingWhite white king
     */
    void setKingWhite(King kingWhite);

    /**
     * @return the black king
     */
    King getKingBlack();

    /**
     * @param kingBlack black king
     */
    void setKingBlack(King kingBlack);

    /**
     * @return the last pawn that was moved two squares
     */
    Pawn getTwoSquareMovedPawn();

    /**
     * @param twoSquareMovedPawn pawn that's being moved two squares
     */
    void setTwoSquareMovedPawn(Pawn twoSquareMovedPawn);

    /**
     * @return the grid
     */
    ChessboardGrid getChessboardGrid();

    /**
     * Method setPieces on begin of new game or loaded game
     *
     * @param places  string with pieces to set on chessboard
     * @param plWhite reference to white player
     * @param plBlack reference to black player
     */
    //Old methods
    void setPieces(String places, Player plWhite, Player plBlack);

    /**
     * method to get reference to square from given x and y integeres
     *
     * @param x x position on chessboard
     * @param y y position on chessboard
     * @return reference to searched square
     */
    Square getSquareConsideringLabels(int x, int y);

    /**
     * Method selecting piece in chessboard
     *
     * @param sq square to select (when clicked))
     */
    void select(Square sq);

    /**
     * Method set variables active_x_square and active_y_square
     * to 0 values.
     */
    void unselect();

    /**
     * @return the width of the component
     */
    int get_widht();

    /**
     * @return the height of the component
     */
    int get_height();


    /**
     * @param includeLables boolean to determine if labels should be included
     * @return the width of the component including labels
     */
    int get_widht(boolean includeLables);

    /**
     * @return height of square
     */
    int get_square_height();

    /**
     * @param begin of move
     * @param end of move
     */
    void move(Square begin, Square end);

    /**
     * Method to move piece over chessboard
     *
     * @param xFrom from which x move piece
     * @param yFrom from which y move piece
     * @param xTo   to which x move piece
     * @param yTo   to which y move piece
     */
    void move(int xFrom, int yFrom, int xTo, int yTo);

    /**
     * @param begin start of move
     * @param end ond of move
     * @param refresh if square should be unselected and force a refresh
     */
    void move(Square begin, Square end, boolean refresh);

    /**
     * Method move piece from square to square
     *
     * @param begin   square from which move piece
     * @param end     square where we want to move piece         *
     * @param refresh chessboard, default: true
     * @param clearForwardHistory clear move history
     */
    void move(Square begin, Square end, boolean refresh, boolean clearForwardHistory);

    /**
     * @param color of the player
     * @return the player's king
     */
    King myKing(Player.Colors color);

    /**
     * @param xCoordinate of square
     * @param yCoordinate of square
     * @return the square for the corresponding coordinates
     */
    Square getSquare(int xCoordinate, int yCoordinate);

    /**
     * @return the collection of squares
     */
    Square[][] getSquares();

    /**
     * @return top left point
     */
    Point getTopLeftPoint();

    /**
     * @param piece to be placed
     * @param x coordinate where to place it
     * @param y coordinate where to place it
     */
    void setPiece(Piece piece, int x, int y);
}
