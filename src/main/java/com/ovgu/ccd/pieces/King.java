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

/**
 * Class to represent a chess pawn king. King is the most important
 * piece for the game. Loose of king is the and of game.
 * When king is in danger by the opponent then it's a Checked, and when have
 * no other escape then stay on a square "in danger" by the opponent
 * then it's a CheckedMate, and the game is over.
 * <p>
 * |_|_|_|_|_|_|_|_|7
 * |_|_|_|_|_|_|_|_|6
 * |_|_|_|_|_|_|_|_|5
 * |_|_|X|X|X|_|_|_|4
 * |_|_|X|K|X|_|_|_|3
 * |_|_|X|X|X|_|_|_|2
 * |_|_|_|_|_|_|_|_|1
 * |_|_|_|_|_|_|_|_|0
 * 0 1 2 3 4 5 6 7
 */

import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.moves.IBoard;
import com.ovgu.ccd.applogic.Player;

import java.util.ArrayList;

public class King extends Piece {

    public boolean wasMotion = false;
    public static short value = 99;

    protected King(IBoard chessboard, Player player) {
        super(chessboard, player);
        this.symbol = "K";
        imageWhite = ResourceManager.loadImage("King-W.png");
        imageBlack = ResourceManager.loadImage("King-B.png");
        this.setImage();

    }

    /**
     * Annotation to superclass Piece changing pawns location
     *
     * @return ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves() {
        ArrayList list = new ArrayList();

        list.addAll(immediateMoves());
        list.addAll(possibleCastlings());

        return list;
    }

    /**
     * Method to check is the king is checked
     *
     * @return bool true if king is not save, else returns false
     */
    public boolean isChecked()
    {
        return !isSafe(this.getSquare());
    }

    /**
     * Method to check is the king is checked or stalemated
     *
     * @return int 0 if nothing, 1 if checkmate, else returns 2
     */
    public int isCheckmatedOrStalemated() {
        /*
         *returns: 0-nothing, 1-checkmate, 2-stalemate
         */
        if (this.allMoves().size() == 0) {
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    if (chessboard.getSquare(i, j).getPiece() != null
                            && chessboard.getSquare(i, j).getPiece().getPlayer() == this.getPlayer()
                            && chessboard.getSquare(i, j).getPiece().allMoves().size() != 0) {
                        return 0;
                    }
                }
            }

            if (this.isChecked()) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }

    /**
     * Method to check is the king is checked by an opponent
     *
     * @param s Squere where is a king
     * @return bool true if king is save, else returns false
     */
    private boolean isSafe(Square s) //A bit confusing code.
    {
        // Rook & Queen
        for (int i = s.getPosY() + 1; i <= 7; ++i) //up
        {
            if (chessboard.getSquare(s.getPosX(), i).getPiece() == null || this.chessboard.getSquare(s.getPosX(), i).getPiece() == this) //if on this sqhuare isn't piece
            {
                continue;
            } else if (this.chessboard.getSquare(s.getPosX(), i).getPiece().getPlayer() != this.getPlayer()) //if isn't our piece
            {
                if (this.chessboard.getSquare(s.getPosX(), i).getPiece().name.equals("Rook")
                        || this.chessboard.getSquare(s.getPosX(), i).getPiece().name.equals("Queen")) {
                    return false;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int i = s.getPosY() - 1; i >= 0; --i) //down
        {
            if (this.chessboard.getSquare(s.getPosX(), i).getPiece() == null || this.chessboard.getSquare(s.getPosX(), i).getPiece() == this) //if on this sqhuare isn't piece
            {
                continue;
            } else if (this.chessboard.getSquare(s.getPosX(), i).getPiece().getPlayer() != this.getPlayer()) //if isn't our piece
            {
                if (this.chessboard.getSquare(s.getPosX(), i).getPiece().name.equals("Rook")
                        || this.chessboard.getSquare(s.getPosX(), i).getPiece().name.equals("Queen")) {
                    return false;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int i = s.getPosX() - 1; i >= 0; --i) //left
        {
            if (this.chessboard.getSquare(i, s.getPosY()).getPiece() == null || this.chessboard.getSquare(i, s.getPosY()).getPiece() == this) //if on this sqhuare isn't piece
            {
                continue;
            } else if (this.chessboard.getSquare(i, s.getPosY()).getPiece().getPlayer() != this.getPlayer()) //if isn't our piece
            {
                if (this.chessboard.getSquare(i, s.getPosY()).getPiece().name.equals("Rook")
                        || this.chessboard.getSquare(i, s.getPosY()).getPiece().name.equals("Queen")) {
                    return false;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int i = s.getPosX() + 1; i <= 7; ++i) //right
        {
            if (this.chessboard.getSquare(i, s.getPosY()).getPiece() == null || this.chessboard.getSquare(i, s.getPosY()).getPiece() == this) //if on this sqhuare isn't piece
            {
                continue;
            } else if (this.chessboard.getSquare(i, s.getPosY()).getPiece().getPlayer() != this.getPlayer()) //if isn't our piece
            {
                if (this.chessboard.getSquare(i, s.getPosY()).getPiece().name.equals("Rook")
                        || this.chessboard.getSquare(i, s.getPosY()).getPiece().name.equals("Queen")) {
                    return false;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        // Bishop & Queen
        for (int h = s.getPosX() - 1, i = s.getPosY() + 1; !outsideOfBoard(h, i); --h, ++i) //left-up
        {
            if (this.chessboard.getSquare(h, i).getPiece() == null || this.chessboard.getSquare(h, i).getPiece() == this) //if on this sqhuare isn't piece
            {
                continue;
            } else if (this.chessboard.getSquare(h, i).getPiece().getPlayer() != this.getPlayer()) //if isn't our piece
            {
                if (this.chessboard.getSquare(h, i).getPiece().name.equals("Bishop")
                        || this.chessboard.getSquare(h, i).getPiece().name.equals("Queen")) {
                    return false;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int h = s.getPosX() - 1, i = s.getPosY() - 1; !outsideOfBoard(h, i); --h, --i) //left-down
        {
            if (this.chessboard.getSquare(h, i).getPiece() == null || this.chessboard.getSquare(h, i).getPiece() == this) //if on this sqhuare isn't piece
            {
                continue;
            } else if (this.chessboard.getSquare(h, i).getPiece().getPlayer() != this.getPlayer()) //if isn't our piece
            {
                if (this.chessboard.getSquare(h, i).getPiece().name.equals("Bishop")
                        || this.chessboard.getSquare(h, i).getPiece().name.equals("Queen")) {
                    return false;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int h = s.getPosX() + 1, i = s.getPosY() + 1; !outsideOfBoard(h, i); ++h, ++i) //right-up
        {
            if (this.chessboard.getSquare(h, i).getPiece() == null || this.chessboard.getSquare(h, i).getPiece() == this) //if on this sqhuare isn't piece
            {
                continue;
            } else if (this.chessboard.getSquare(h, i).getPiece().getPlayer() != this.getPlayer()) //if isn't our piece
            {
                if (this.chessboard.getSquare(h, i).getPiece().name.equals("Bishop")
                        || this.chessboard.getSquare(h, i).getPiece().name.equals("Queen")) {
                    return false;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int h = s.getPosX() + 1, i = s.getPosY() - 1; !outsideOfBoard(h, i); ++h, --i) //right-down
        {
            if (this.chessboard.getSquare(h, i).getPiece() == null || this.chessboard.getSquare(h, i).getPiece() == this) //if on this sqhuare isn't piece
            {
                continue;
            } else if (this.chessboard.getSquare(h, i).getPiece().getPlayer() != this.getPlayer()) //if isn't our piece
            {
                if (this.chessboard.getSquare(h, i).getPiece().name.equals("Bishop")
                        || this.chessboard.getSquare(h, i).getPiece().name.equals("Queen")) {
                    return false;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        // Knight
        int newX, newY;

        //1
        newX = s.getPosX() - 2;
        newY = s.getPosY() + 1;

        if (!outsideOfBoard(newX, newY)) {
            if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Knight")) {
                return false;
            }
        }

        //2
        newX = s.getPosX() - 1;
        newY = s.getPosY() + 2;

        if (!outsideOfBoard(newX, newY)) {
            if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Knight")) {
                return false;
            }
        }

        //3
        newX = s.getPosX() + 1;
        newY = s.getPosY() + 2;

        if (!outsideOfBoard(newX, newY)) {
            if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Knight")) {
                return false;
            }
        }

        //4
        newX = s.getPosX() + 2;
        newY = s.getPosY() + 1;

        if (!outsideOfBoard(newX, newY)) {
            if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Knight")) {
                return false;
            }
        }

        //5
        newX = s.getPosX() + 2;
        newY = s.getPosY() - 1;

        if (!outsideOfBoard(newX, newY)) {
            if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Knight")) {
                return false;
            }
        }

        //6
        newX = s.getPosX() + 1;
        newY = s.getPosY() - 2;

        if (!outsideOfBoard(newX, newY)) {
            if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Knight")) {
                return false;
            }
        }

        //7
        newX = s.getPosX() - 1;
        newY = s.getPosY() - 2;

        if (!outsideOfBoard(newX, newY)) {
            if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Knight")) {
                return false;
            }
        }

        //8
        newX = s.getPosX() - 2;
        newY = s.getPosY() - 1;

        if (!outsideOfBoard(newX, newY)) {
            if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
            {
            } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Knight")) {
                return false;
            }
        }

        // King
        King otherKing;
        if (this == chessboard.getKingWhite())
        {
            otherKing = chessboard.getKingBlack();
        }
        else
        {
            otherKing = chessboard.getKingWhite();
        }

        if (s.getPosX() <= otherKing.getSquare().getPosX() + 1
                && s.getPosX() >= otherKing.getSquare().getPosX() - 1
                && s.getPosY() <= otherKing.getSquare().getPosY() + 1
                && s.getPosY() >= otherKing.getSquare().getPosY() - 1) {
            return false;
        }

        // Pawn
        if (this.getPlayer().isGoDown()) //check if player "go" down or up
        {//System.out.println("go down");
            newX = s.getPosX() - 1;
            newY = s.getPosY() + 1;
            if (!outsideOfBoard(newX, newY)) {
                if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
                {
                } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
                {
                } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Pawn")) {
                    return false;
                }
            }
            newX = s.getPosX() + 1;
            if (!outsideOfBoard(newX, newY)) {
                if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
                {
                } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
                {
                } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Pawn")) {
                    return false;
                }
            }
        } else {//System.out.println("go up");
            newX = s.getPosX() - 1;
            newY = s.getPosY() - 1;
            if (!outsideOfBoard(newX, newY)) {
                if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
                {
                } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
                {
                } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Pawn")) {
                    return false;
                }
            }
            newX = s.getPosX() + 1;
            if (!outsideOfBoard(newX, newY)) {
                if (this.chessboard.getSquare(newX, newY).getPiece() == null) //if on this sqhuare isn't piece
                {
                } else if (this.chessboard.getSquare(newX, newY).getPiece().getPlayer() == this.getPlayer()) //if is our piece
                {
                } else if (this.chessboard.getSquare(newX, newY).getPiece().name.equals("Pawn")) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Method to check will the king be safe when move
     *
     * @return bool true if king is save, else returns false
     */
    public boolean willBeSafeWhenMoveOtherPiece(Square currentPosition, Square nextPosition) //long name ;)
    {
        Piece tmp = nextPosition.getPiece();
        nextPosition.setPiece(currentPosition.getPiece()); // move without redraw
        currentPosition.setPiece( null);

        boolean ret = isSafe(this.getSquare());

        currentPosition.setPiece(nextPosition.getPiece());
        nextPosition.setPiece( tmp);

        return ret;
    }


    private ArrayList immediateMoves() {
        Square possibleNewPosition;
        ArrayList moves = new ArrayList();

        for (int x = this.getSquare().getPosX() - 1; x <= this.getSquare().getPosX() + 1; x++) {
            for (int y = this.getSquare().getPosY() - 1; y <= this.getSquare().getPosY() + 1; y++) {
                if (!this.outsideOfBoard(x, y)) {
                    possibleNewPosition = this.chessboard.getSquare(x, y);

                    if (this.getSquare() == possibleNewPosition) { continue; }

                    if (this.canMoveTo(possibleNewPosition) && isSafe(possibleNewPosition)) {
                        moves.add(possibleNewPosition);
                    }
                }
            }
        }
        return moves;
    }

    private ArrayList possibleCastlings() {
        ArrayList list = new ArrayList();

        if (wasMotion || isChecked()) {
            return list;
        }


        if (leftCastlingPossible()) {
            list.add(chessboard.getSquare(getSquare().getPosX() - 2, getSquare().getPosY()));
        }

        if (rightCastlingPossible()) {
            list.add(chessboard.getSquare(getSquare().getPosX() + 2, getSquare().getPosY()));
        }

        return list;
    }

    private boolean leftCastlingPossible()
    {
        if (chessboard.getSquare(0, getSquare().getPosY()).getPiece() == null ||
            !chessboard.getSquare(0, getSquare().getPosY()).getPiece().name.equals("Rook")) { return false; }

        Rook rook = (Rook) chessboard.getSquare(0, getSquare().getPosY()).getPiece();
        if (!rook.wasMotion)
        {
            for (int i = getSquare().getPosX() - 1; i > 0; i--)
            {
                if (chessboard.getSquare(i, getSquare().getPosY()).getPiece() != null) { return false; }
            }
            Square kingsNextPosition = chessboard.getSquare(getSquare().getPosX() - 2, getSquare().getPosY());
            Square rooksNextPosition = chessboard.getSquare(getSquare().getPosX() - 1, getSquare().getPosY());
            return (isSafe(kingsNextPosition) && isSafe(rooksNextPosition));
        }

        return true;
    }

    private boolean rightCastlingPossible()
    {
        if (chessboard.getSquare(7, getSquare().getPosY()).getPiece() == null ||
            !chessboard.getSquare(7, getSquare().getPosY()).getPiece().name.equals("Rook")) { return false; }

        Rook rook = (Rook) chessboard.getSquare(7, getSquare().getPosY()).getPiece();
        if (!rook.wasMotion)
        {
            for (int i = getSquare().getPosX() + 1; i < 7; i++)
            {
                if (chessboard.getSquare(i, getSquare().getPosY()).getPiece() != null) { return false; }
            }

            Square kingsNextPosition = chessboard.getSquare(getSquare().getPosX() + 2, getSquare().getPosY());
            Square rooksNextPosition = chessboard.getSquare(getSquare().getPosX() + 1, getSquare().getPosY());
            return (isSafe(kingsNextPosition) && isSafe(rooksNextPosition));
        }

        return true;
    }

    public boolean isWasMotion() {
        return wasMotion;
    }

    public void setWasMotion(boolean wasMotion) {
        this.wasMotion = wasMotion;
    }
}