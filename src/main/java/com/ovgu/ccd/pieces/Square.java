package com.ovgu.ccd.pieces;

import java.util.Objects;


/**
 * Class to represent a chessboard square
 */
public class Square {
    private boolean invalid = false;
    private Piece piece = null;
    private int boardPosX = -1;
    private int boardPosY = -1;


    /**
     *
     * @param x
     * @param y
     * @param piece
     */
    public Square(int x, int y, Piece piece) {
        this.boardPosX = x;
        this.boardPosY = y;
        this.piece = piece;
    }

    /**
     *
     * @param square
     */
    public Square(Square square) {
        this.boardPosX = square.getPosX();
        this.boardPosY = square.getPosY();
        this.piece = square.getPiece();
    }


    /**
     *
     * @param x
     */
    public void setPosX(final int x) {
        this.boardPosX = x;
    }


    /**
     *
     * @param y
     */
    public void setPosY(final int y) {
        this.boardPosY = y;
    }


    /**
     *
     * @return
     */
    public int getPosX() {
        return this.boardPosX;
    }

    /**
     *
     * @return
     */
    public int getPosY() {
        return this.boardPosY;
    }


    /**
     *
     */
    public void print() {
        System.out.println("Square Hashcode:  " + this.hashCode());
        System.out.println("Board Position X: " + this.getPosX());
        System.out.println("Board Position Y: " + this.getPosY());

        if (this.piece != null)
            System.out.println("Piece:            " + this.getPiece());
    }

    /**
     * @param piece
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

    /**
     * checks whether two squares are equal by comparing the x,y corrdinate and checking if there is the same piece on it
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

    @Override
    public int hashCode() {
        return Objects.hash(100 * this.boardPosX + this.boardPosY);
    }

    /**
     * Retrieve the piece on the square
     *
     * @return
     */
    public Piece getPiece() {
        return piece;
    }


    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return piece == null;
    }

    /**
     *
     * @return
     */
    public boolean isInvalid() {
        return invalid;
    }

    /**
     *
     * @param value
     */
    public void setInvalid(boolean value) {
        this.invalid = value;
    }
}
