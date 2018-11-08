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

/**
 * Class to represent a chess pawn king. King is the most important
 * piece for the game. Loose of king is the and of game.
 * When king is in danger by the opponent then it's a Checked, and when have
 * no other escape then stay on a square "in danger" by the opponent
 * then it's a CheckedMate, and the game is over.
 *
 *       |_|_|_|_|_|_|_|_|7
        |_|_|_|_|_|_|_|_|6
        |_|_|_|_|_|_|_|_|5
        |_|_|X|X|X|_|_|_|4
        |_|_|X|K|X|_|_|_|3
        |_|_|X|X|X|_|_|_|2
        |_|_|_|_|_|_|_|_|1
        |_|_|_|_|_|_|_|_|0
        0 1 2 3 4 5 6 7
 */
import java.util.ArrayList;

public class King extends Piece
{

    public boolean wasMotion = false;
    public static short value = 99;

    public King(Chessboard chessboard, Player player)
    {
        super(chessboard, player);
        this.symbol = "K";
        imageWhite = GUI.loadImage("King-W.png");
        imageBlack = GUI.loadImage("King-B.png");
        this.setImage();

    }

    /**
     * Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves()
    {
        ArrayList list = new ArrayList();

        list.addAll(immediateMoves());
        list.addAll(possibleCastlings());

       return list;
    }

    /** Method to check is the king is checked
     *  @return bool true if king is not save, else returns false
     */
    public boolean isChecked()
    {
        return !isSafe(this.square);
    }

    /** Method to check is the king is checked or stalemated
     *  @return int 0 if nothing, 1 if checkmate, else returns 2
     */
    public int isCheckmatedOrStalemated()
    {
        /*
         *returns: 0-nothing, 1-checkmate, 2-stalemate
         */
        if (this.allMoves().size() == 0)
        {
            for (int i = 0; i < 8; ++i)
            {
                for (int j = 0; j < 8; ++j)
                {
                    if (chessboard.squares[i][j].piece != null
                            && chessboard.squares[i][j].piece.player == this.player
                            && chessboard.squares[i][j].piece.allMoves().size() != 0)
                    {
                        return 0;
                    }
                }
            }

            if (this.isChecked())
            {
                return 1;
            }
            else
            {
                return 2;
            }
        }
        else
        {
            return 0;
        }
    }

    /** Method to check is the king is checked by an opponent
     * @param s Squere where is a king
     * @return bool true if king is save, else returns false
     */
    private boolean isSafe(Square s) //A bit confusing code.
    {
        // Rook & Queen
        for (int i = s.pozY + 1; i <= 7; ++i) //up
        {
            if (chessboard.squares[s.pozX][i].piece == null || this.chessboard.squares[s.pozX][i].piece == this) //if on this sqhuare isn't piece
            {
                continue;
            }
            else if (this.chessboard.squares[s.pozX][i].piece.player != this.player) //if isn't our piece
            {
                if (this.chessboard.squares[s.pozX][i].piece.name.equals("Rook")
                        || this.chessboard.squares[s.pozX][i].piece.name.equals("Queen"))
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }

        for (int i = s.pozY - 1; i >= 0; --i) //down
        {
            if (this.chessboard.squares[s.pozX][i].piece == null || this.chessboard.squares[s.pozX][i].piece == this) //if on this sqhuare isn't piece
            {
                continue;
            }
            else if (this.chessboard.squares[s.pozX][i].piece.player != this.player) //if isn't our piece
            {
                if (this.chessboard.squares[s.pozX][i].piece.name.equals("Rook")
                        || this.chessboard.squares[s.pozX][i].piece.name.equals("Queen"))
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }

        for (int i = s.pozX - 1; i >= 0; --i) //left
        {
            if (this.chessboard.squares[i][s.pozY].piece == null || this.chessboard.squares[i][s.pozY].piece == this) //if on this sqhuare isn't piece
            {
                continue;
            }
            else if (this.chessboard.squares[i][s.pozY].piece.player != this.player) //if isn't our piece
            {
                if (this.chessboard.squares[i][s.pozY].piece.name.equals("Rook")
                        || this.chessboard.squares[i][s.pozY].piece.name.equals("Queen"))
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }

        for (int i = s.pozX + 1; i <= 7; ++i) //right
        {
            if (this.chessboard.squares[i][s.pozY].piece == null || this.chessboard.squares[i][s.pozY].piece == this) //if on this sqhuare isn't piece
            {
                continue;
            }
            else if (this.chessboard.squares[i][s.pozY].piece.player != this.player) //if isn't our piece
            {
                if (this.chessboard.squares[i][s.pozY].piece.name.equals("Rook")
                        || this.chessboard.squares[i][s.pozY].piece.name.equals("Queen"))
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }

        // Bishop & Queen
        for (int h = s.pozX - 1, i = s.pozY + 1; !isout(h, i); --h, ++i) //left-up
        {
            if (this.chessboard.squares[h][i].piece == null || this.chessboard.squares[h][i].piece == this) //if on this sqhuare isn't piece
            {
                continue;
            }
            else if (this.chessboard.squares[h][i].piece.player != this.player) //if isn't our piece
            {
                if (this.chessboard.squares[h][i].piece.name.equals("Bishop")
                        || this.chessboard.squares[h][i].piece.name.equals("Queen"))
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }

        for (int h = s.pozX - 1, i = s.pozY - 1; !isout(h, i); --h, --i) //left-down
        {
            if (this.chessboard.squares[h][i].piece == null || this.chessboard.squares[h][i].piece == this) //if on this sqhuare isn't piece
            {
                continue;
            }
            else if (this.chessboard.squares[h][i].piece.player != this.player) //if isn't our piece
            {
                if (this.chessboard.squares[h][i].piece.name.equals("Bishop")
                        || this.chessboard.squares[h][i].piece.name.equals("Queen"))
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }

        for (int h = s.pozX + 1, i = s.pozY + 1; !isout(h, i); ++h, ++i) //right-up
        {
            if (this.chessboard.squares[h][i].piece == null || this.chessboard.squares[h][i].piece == this) //if on this sqhuare isn't piece
            {
                continue;
            }
            else if (this.chessboard.squares[h][i].piece.player != this.player) //if isn't our piece
            {
                if (this.chessboard.squares[h][i].piece.name.equals("Bishop")
                        || this.chessboard.squares[h][i].piece.name.equals("Queen"))
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }

        for (int h = s.pozX + 1, i = s.pozY - 1; !isout(h, i); ++h, --i) //right-down
        {
            if (this.chessboard.squares[h][i].piece == null || this.chessboard.squares[h][i].piece == this) //if on this sqhuare isn't piece
            {
                continue;
            }
            else if (this.chessboard.squares[h][i].piece.player != this.player) //if isn't our piece
            {
                if (this.chessboard.squares[h][i].piece.name.equals("Bishop")
                        || this.chessboard.squares[h][i].piece.name.equals("Queen"))
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }

        // Knight
        int newX, newY;

        //1
        newX = s.pozX - 2;
        newY = s.pozY + 1;

        if (!isout(newX, newY))
        {
            if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.name.equals("Knight"))
            {
                return false;
            }
        }

        //2
        newX = s.pozX - 1;
        newY = s.pozY + 2;

        if (!isout(newX, newY))
        {
            if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.name.equals("Knight"))
            {
                return false;
            }
        }

        //3
        newX = s.pozX + 1;
        newY = s.pozY + 2;

        if (!isout(newX, newY))
        {
            if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.name.equals("Knight"))
            {
                return false;
            }
        }

        //4
        newX = s.pozX + 2;
        newY = s.pozY + 1;

        if (!isout(newX, newY))
        {
            if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.name.equals("Knight"))
            {
                return false;
            }
        }

        //5
        newX = s.pozX + 2;
        newY = s.pozY - 1;

        if (!isout(newX, newY))
        {
            if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.name.equals("Knight"))
            {
                return false;
            }
        }

        //6
        newX = s.pozX + 1;
        newY = s.pozY - 2;

        if (!isout(newX, newY))
        {
            if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.name.equals("Knight"))
            {
                return false;
            }
        }

        //7
        newX = s.pozX - 1;
        newY = s.pozY - 2;

        if (!isout(newX, newY))
        {
            if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.name.equals("Knight"))
            {
                return false;
            }
        }

        //8
        newX = s.pozX - 2;
        newY = s.pozY - 1;

        if (!isout(newX, newY))
        {
            if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
            {
            }
            else if (this.chessboard.squares[newX][newY].piece.name.equals("Knight"))
            {
                return false;
            }
        }

        // King
        King otherKing;
        if (this == chessboard.kingWhite)
        {
            otherKing = chessboard.kingBlack;
        }
        else
        {
            otherKing = chessboard.kingWhite;
        }

        if (s.pozX <= otherKing.square.pozX + 1
                && s.pozX >= otherKing.square.pozX - 1
                && s.pozY <= otherKing.square.pozY + 1
                && s.pozY >= otherKing.square.pozY - 1)
        {
            return false;
        }

        // Pawn
        if (this.player.isGoDown()) //check if player "go" down or up
        {//System.out.println("go down");
            newX = s.pozX - 1;
            newY = s.pozY + 1;
            if (!isout(newX, newY))
            {
                if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
                {
                }
                else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
                {
                }
                else if (this.chessboard.squares[newX][newY].piece.name.equals("Pawn"))
                {
                    return false;
                }
            }
            newX = s.pozX + 1;
            if (!isout(newX, newY))
            {
                if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
                {
                }
                else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
                {
                }
                else if (this.chessboard.squares[newX][newY].piece.name.equals("Pawn"))
                {
                    return false;
                }
            }
        }
        else
        {//System.out.println("go up");
            newX = s.pozX - 1;
            newY = s.pozY - 1;
            if (!isout(newX, newY))
            {
                if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
                {
                }
                else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
                {
                }
                else if (this.chessboard.squares[newX][newY].piece.name.equals("Pawn"))
                {
                    return false;
                }
            }
            newX = s.pozX + 1;
            if (!isout(newX, newY))
            {
                if (this.chessboard.squares[newX][newY].piece == null) //if on this sqhuare isn't piece
                {
                }
                else if (this.chessboard.squares[newX][newY].piece.player == this.player) //if is our piece
                {
                }
                else if (this.chessboard.squares[newX][newY].piece.name.equals("Pawn"))
                {
                    return false;
                }
            }
        }

        return true;
    }

    /** Method to check will the king be safe when move
     *  @return bool true if king is save, else returns false
     */
    public boolean willBeSafeWhenMoveOtherPiece(Square currentPosition, Square nextPosition) //long name ;)
    {
        Piece tmp = nextPosition.piece;
        nextPosition.piece = currentPosition.piece; // move without redraw
        currentPosition.piece = null;

        boolean ret = isSafe(this.square);

        currentPosition.piece = nextPosition.piece;
        nextPosition.piece = tmp;

        return ret;
    }


    private ArrayList immediateMoves()
    {
        Square possibleNewPosition;
        ArrayList moves = new ArrayList();

        for (int x = this.square.pozX - 1; x <= this.square.pozX + 1; x++)
        {
            for (int y = this.square.pozY - 1; y <= this.square.pozY + 1; y++)
            {
                if (!this.isout(x, y))
                {
                    possibleNewPosition = this.chessboard.squares[x][y];

                    if (this.square == possibleNewPosition) { continue; }

                    if (this.checkPiece(x, y) && isSafe(possibleNewPosition))
                    {
                        moves.add(possibleNewPosition);
                    }
                }
            }
        }
        return moves;
    }

    private ArrayList possibleCastlings()
    {
        ArrayList list = new ArrayList();

        if (wasMotion || isChecked()) { return list; }

        if (leftCastlingPossible()) { list.add(chessboard.squares[square.pozX - 2][square.pozY]); }

        if (rightCastlingPossible()) { list.add(chessboard.squares[square.pozX + 2][square.pozY]); }

        return list;
    }

    private boolean leftCastlingPossible()
    {
        if (chessboard.squares[0][square.pozY].piece == null ||
            !chessboard.squares[0][square.pozY].piece.name.equals("Rook")) { return false; }

        Rook rook = (Rook) chessboard.squares[0][square.pozY].piece;
        if (!rook.wasMotion)
        {
            for (int i = square.pozX - 1; i > 0; i--)
            {
                if (chessboard.squares[i][square.pozY].piece != null) { return false; }
            }
            Square kingsNextPosition = chessboard.squares[square.pozX - 2][square.pozY];
            Square rooksNextPosition = chessboard.squares[square.pozX - 1][square.pozY];
            return (isSafe(kingsNextPosition) && isSafe(rooksNextPosition));
        }

        return true;
    }

    private boolean rightCastlingPossible()
    {
        if (chessboard.squares[7][square.pozY].piece == null ||
                !chessboard.squares[7][square.pozY].piece.name.equals("Rook")) { return false; }

        Rook rook = (Rook) chessboard.squares[7][square.pozY].piece;
        if (!rook.wasMotion)
        {
            for (int i = square.pozX + 1; i < 7; i++)
            {
                if (chessboard.squares[i][square.pozY].piece != null) { return false; }
            }

            Square kingsNextPosition = chessboard.squares[square.pozX + 2][square.pozY];
            Square rooksNextPosition = chessboard.squares[square.pozX + 1][square.pozY];
            return (isSafe(kingsNextPosition) && isSafe(rooksNextPosition));
        }

        return true;
    }
}
