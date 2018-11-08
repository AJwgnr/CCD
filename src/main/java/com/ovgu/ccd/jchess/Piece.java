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
package com.ovgu.ccd.jchess;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
Class to represent a piece (any kind) - this class should be extended to represent pawn, bishop etc.
 */
public abstract class Piece
{

    Chessboard chessboard; // <-- this relations isn't in class diagram, but it's necessary :/
    public Square square;
    private Player player;
    String name;
    protected String symbol;
    public static Image imageBlack;
    public static Image imageWhite;
    public Image orgImage;
    public Image image;
    public static short value = 0;

    Piece(Chessboard chessboard, Player player)
    {
        this.chessboard = chessboard;
        this.setPlayer(player);
        this.name = this.getClass().getSimpleName();

    }
    /* Method to draw piece on chessboard
     * @graph : where to draw
     */

    final void draw(Graphics g)
    {
        try
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Point topLeft = this.chessboard.getTopLeftPoint();
            int height = this.chessboard.get_square_height();
            int x = (this.square.pozX * height) + topLeft.x;
            int y = (this.square.pozY * height) + topLeft.y;
            float addX = (height - image.getWidth(null)) / 2;
            float addY = (height - image.getHeight(null)) / 2;
            if (image != null && g != null)
            {
                Image tempImage = orgImage;
                BufferedImage resized = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB_PRE);
                Graphics2D imageGr = (Graphics2D) resized.createGraphics();
                imageGr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                imageGr.drawImage(tempImage, 0, 0, height, height, null);
                imageGr.dispose();
                image = resized.getScaledInstance(height, height, 0);
                g2d.drawImage(image, x, y, null);
            }
            else
            {
                System.out.println("image is null!");
            }

        }
        catch (java.lang.NullPointerException exc)
        {
            System.out.println("Something wrong when painting piece: " + exc.getMessage());
        }
    }

    void setImage()
    {
        if (this.getPlayer().color == this.getPlayer().color.black)
        {
            image = imageBlack;
        }
        else
        {
            image = imageWhite;
        }
        orgImage = image;
    }


    abstract public ArrayList allMoves();

    /** Method is useful for out of bounds protection
     * @param x  x position on chessboard
     * @param y y position on chessboard
     * @return true if parameters are out of bounds (array)
     * */
    protected boolean outsideOfBoard(int x, int y)
    {
        return (x < 0 || x > 7 || y < 0 || y > 7);
    }

    protected boolean canMoveTo(Square position)
    {
        Piece piece = position.piece;
        if (piece != null && piece.name.equals("King"))
        {
            return false;
        }
        if (piece == null || piece.getPlayer() != this.getPlayer())
        {
            return true;
        }
        return false;
    }

    protected boolean otherOwner(Piece otherPiece)
    {
        if (otherPiece == null) { return false; }
        return player != otherPiece.getPlayer();
    }

    public String getSymbol()
    {
        return this.symbol;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player.colors getColor()
    {
        return player.color;
    }
}
