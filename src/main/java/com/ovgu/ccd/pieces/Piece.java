package com.ovgu.ccd.pieces;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.Player.Colors;
import com.ovgu.ccd.gui.chessboardListener.Point;
import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;

/**Class to represent a piece (any kind) - this class should be extended to represent pawn, bishop etc.
 */
public abstract class Piece {
    /**
     */
    public static Image imageBlack;

    /**
     */
    public static Image imageWhite;

    /**
     *
     */
    public static Image imageGray;

    /**
     *
     */
    private Square square;

    /**
     *
     */
    public Player player;

    /**
     *
     */
    public String name;

    /**
     *
     */
    public Image orgImage;

    /**
     *
     */
    public Image image;

    /**
     *
     */
    protected String symbol;

    /**
     *
     */
    IBoard chessboard;

    /**
     * @param chessboard chessboard to add the piece
     * @param player player to add the piece
     */
    protected Piece(final IBoard chessboard, final Player player) {
        this.chessboard = chessboard;
        this.setPlayer(player);
        this.name = this.getClass().getSimpleName();

    }

    public enum PieceTypes {
        /**Enumeration of the differnt piece names.
         */
        BISHOP, ROOK, KING, KNIGHT, PAWN, QUEEN
    }

    /** Method to draw piece on chessboard.
     * @param g where to draw
     */
    final public void draw(final Graphics g) {
        Point center = this.chessboard.getChessboardGrid().getSquare(
                this.square.getPosX(),
                this.square.getPosY()).center();
        g.drawImage(orgImage,
                center.getX() - (orgImage.getWidth(null) / 2),
                center.getY() - (orgImage.getHeight(null) / 2),
                null);
    }

    /** Sets the image of the piece
     */
    void setImage() {
        if (this.getPlayer().getColor() == Colors.BLACK) {
            image = imageBlack;
        } else if (this.getPlayer().getColor() == Colors.WHITE) {
            image = imageWhite;
        } else {
            image = imageGray;
        }
        orgImage = image;
    }

    /** abstract method that returns all possible moves of a piece.
     * Must be overwritten in the pieces implementations
     * @return all possible moves of a piece in a list
     */
    abstract public ArrayList allMoves();

    /**Method is useful for out of bounds protection.
     *
     * @param x x position on chessboard
     * @param y y position on chessboard
     * @return true if parameters are out of bounds (array)
     */
    public boolean outsideOfBoard(final int x, final int y) {
        return (x < 0 || x > 7 || y < 0 || y > 7);
    }

    /** Checks wether a piece can move to a square.
     * @param position sqaure to move
     * @return boolean if move is possible
     */
    public boolean canMoveTo(final Square position) {
        Piece piece = position.getPiece();
        if (piece != null && piece.name.equals("King")) {
            return false;
        }
        if (piece == null || piece.getPlayer() != this.getPlayer()) {
            return true;
        }
        return false;
    }

    /**
     * @param otherPiece piece to check owner to
     * @return true if otherPiece has another owner
     */
    public boolean otherOwner(final Piece otherPiece) {
        if (otherPiece == null) {
            return false;
        }
        return player != otherPiece.getPlayer();
    }

    /** Gets the symbol of the piece.
     * @return symbol
     */
    public String getSymbol() {
        return this.symbol;
    }

    /** Gets the player of the piece.
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /** Sets the player of the piece.
     * @param player player
     */
    public void setPlayer(final Player player) {
        this.player = player;
    }

    /**Gets the color of the player on the piece.
     * @return player color on the square
     */
    public Colors getColor() {
        return player.getColor();
    }

    /** Gets the chessboard of the piece.
     * @return IBOARD chessboard
     */
    public IBoard getChessboard() {
        return chessboard;
    }

    /** Sets the chessboard of the piece.
     * @param chessboard chessboard
     */
    public void setChessboard(final IBoard chessboard) {
        this.chessboard = chessboard;
    }

    /** Gets the sqaure of the piece.
     * @return square
     */
    public Square getSquare() {
        return square;
    }

    /** Sets the square of the piece.
     * @param square sqare to set
     */
    public void setSquare(final Square square) {
        this.square = square;
    }

    /**Gets the x position of the square of this piece.
     * @return x coordinate
     */
    public int getPosX() {
        return square.getPosX();
    }

    /** Gets the x position of the square of this piece.
     * @return y coordinate
     */
    public int getPosY() {
        return square.getPosY();
    }
}
