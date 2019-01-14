package com.ovgu.ccd.pieces;

import java.util.Objects;


/**Class to represent a chessboard square.
 */
public class Square {
    /**default parameter if the square is invalid.
     */
    private boolean invalid = false;
    /** default piece on the square.
     */
    private Piece piece;
    /**Default x position.
     */
    private int boardPosX;
    /**Default y position.
     */
    private int boardPosY;


    /**Creates a new square with a given piece.
     * @param x x position on the board
     * @param y y position on the board
     * @param piece piece to place on the board
     */
    public Square(final int x, final int y, final Piece piece) {
        this.boardPosX = x;
        this.boardPosY = y;
        this.piece = piece;
    }

    /** Initializes a sqaure with a given square.
     * @param square exisiting sqaure to initialiaze new square
     */
    public Square(final Square square) {
        this.boardPosX = square.getPosX();
        this.boardPosY = square.getPosY();
        this.piece = square.getPiece();
        this.invalid = square.isInvalid();
    }


    /**Sets the x position of the square.
     * @param x x coordinate on the board
     */
    public void setPosX(final int x) {
        this.boardPosX = x;
    }


    /**Sets the y position of the square.
     * @param y y coordinate of the board
     */
    public void setPosY(final int y) {
        this.boardPosY = y;
    }


    /**Gets the x position of the square.
     * @return x position
     */
    public int getPosX() {
        return this.boardPosX;
    }

    /**Gets the x coordinate of the square.
     * @return y position
     */
    public int getPosY() {
        return this.boardPosY;
    }


    /** Prints the information about the square.
     */
    public void print() {
        System.out.println("Square Hashcode:  " + this.hashCode());
        System.out.println("Board Position X: " + this.getPosX());
        System.out.println("Board Position Y: " + this.getPosY());

        if (this.piece != null) {
            System.out.println("Piece:            " + this.getPiece());
        }
    }

    /** Sets a piece on the square.
     * @param piece Piece to place on the square
     */
    public void setPiece(final Piece piece) {
        if (this.piece != null) {
            this.piece.setSquare(null);
        }
        this.piece = piece;
        if (piece != null) {
            this.piece.setSquare(this);
        }
    }

    /**checks whether two squares are equal by comparing the x,y corrdinate and checking if there is the same piece on it.
     * @param o
     * @return
     */
    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Square)) return false;

        Square square = (Square) o;

        return square.getPosX() == getPosX() && square.getPosY() == getPosY();
    }

    /** Calculates the hash of the sqare.
     * @return hashcode as int
     */
    @Override
    public int hashCode() {
        return Objects.hash(100 * this.boardPosX + this.boardPosY);
    }

    /**Retrieve the piece on the square.     *
     * @return piece on the square
     */
    public Piece getPiece() {
        return piece;
    }


    /** Checks wether the square is empty (no piece on it).
     * @return boolean if square is empty
     */
    public boolean isEmpty() {
        return this.piece == null;
    }

    /** Checks wether the square is invalid.
     * @return boolean
     */
    public boolean isInvalid() {
        return invalid;
    }

    /** Sets the invalid attribute of the sqaure.
     * @param value boolean value to set
     */
    public void setInvalid(final boolean value) {
        this.invalid = value;
    }
}
