/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Authors:
 * Mateusz Sławomir Lach ( matlak, msl )
 * Damian Marciniak
 */
package com.ovgu.ccd.pieces;


import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.Player.Colors;
import com.ovgu.ccd.gui.chessboardListener.Point;


import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Class to represent a piece (any kind) - this class should be extended to represent pawn, bishop etc.
 */
public abstract class Piece {

    public static Image imageBlack;
    public static Image imageWhite;
    public static Image imageGray;
    private Square square;
    public Player player;
    public String name;
    public Image orgImage;
    public Image image;
    protected String symbol;
    IBoard chessboard; // <-- this relations isn't in class diagram, but it's necessary :/

    /**
     * @param chessboard
     * @param player
     */
    protected Piece(final IBoard chessboard, final Player player) {
        this.chessboard = chessboard;
        this.setPlayer(player);
        this.name = this.getClass().getSimpleName();

    }

    /**
     *
     */
    public enum PieceTypes {
        BISHOP, ROOK, KING, KNIGHT, PAWN, QUEEN
    }

    /**
     * Method to draw piece on chessboard
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

    /**
     *
     */
    void setImage() {
        if (this.getPlayer().getColor() == this.getPlayer().getColor().BLACK) {
            image = imageBlack;
        } else if (this.getPlayer().getColor() == this.getPlayer().getColor().WHITE) {
            image = imageWhite;
        } else {
            image = imageGray;
        }
        orgImage = image;
    }

    /**
     *
     * @return
     */
    abstract public ArrayList allMoves();

    /**
     * Method is useful for out of bounds protection
     *
     * @param x x position on chessboard
     * @param y y position on chessboard
     * @return true if parameters are out of bounds (array)
     */
    public boolean outsideOfBoard(final int x, final int y) {
        return (x < 0 || x > 7 || y < 0 || y > 7);
    }

    /**
     *
     * @param position
     * @return
     */
    public boolean canMoveTo(Square position) {
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
     *
     * @param otherPiece
     * @return
     */
    public boolean otherOwner(Piece otherPiece) {
        if (otherPiece == null) {
            return false;
        }
        return player != otherPiece.getPlayer();
    }

    /**
     *
     * @return
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @param player
     */
    public void setPlayer(final Player player) {
        this.player = player;
    }

    /**
     *
     * @return
     */
    public Colors getColor() {
        return player.getColor();
    }

    /**
     *
     * @return
     */
    public IBoard getChessboard() {
        return chessboard;
    }

    /**
     *
     * @param chessboard
     */
    public void setChessboard(final IBoard chessboard) {
        this.chessboard = chessboard;
    }

    /**
     *
     * @return
     */
    public Square getSquare() {
        return square;
    }

    /**
     *
     * @param square
     */
    public void setSquare(Square square) {
        this.square = square;
    }

    /**
     *
     * @return
     */
    public int getPosX() {
        return square.getPosX();
    }

    /**
     *
     * @return
     */
    public int getPosY() {
        return square.getPosY();
    }
}
