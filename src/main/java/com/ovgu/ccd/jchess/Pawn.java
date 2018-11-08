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

import java.util.ArrayList;

/**
 * Class to represent a pawn piece
 * Pawn can move only forvard and can beat only across
 * In first move pawn can move 2 sqares
 * pawn can be upgreade to rook, knight, bishop, Queen if it's in the
 * squers nearest the side where opponent is lockated
 * Firat move of pawn:
 *       |_|_|_|_|_|_|_|_|7
|_|_|_|_|_|_|_|_|6
|_|_|_|X|_|_|_|_|5
|_|_|_|X|_|_|_|_|4
|_|_|_|P|_|_|_|_|3
|_|_|_|_|_|_|_|_|2
|_|_|_|_|_|_|_|_|1
|_|_|_|_|_|_|_|_|0
0 1 2 3 4 5 6 7
 *
 * Move of a pawn:
 *       |_|_|_|_|_|_|_|_|7
|_|_|_|_|_|_|_|_|6
|_|_|_|_|_|_|_|_|5
|_|_|_|X|_|_|_|_|4
|_|_|_|P|_|_|_|_|3
|_|_|_|_|_|_|_|_|2
|_|_|_|_|_|_|_|_|1
|_|_|_|_|_|_|_|_|0
0 1 2 3 4 5 6 7
 * Beats with can take pawn:
 *       |_|_|_|_|_|_|_|_|7
|_|_|_|_|_|_|_|_|6
|_|_|_|_|_|_|_|_|5
|_|_|X|_|X|_|_|_|4
|_|_|_|P|_|_|_|_|3
|_|_|_|_|_|_|_|_|2
|_|_|_|_|_|_|_|_|1
|_|_|_|_|_|_|_|_|0
0 1 2 3 4 5 6 7
 */

public class Pawn extends Piece {

    boolean down;
    public static short value = 1;

    public Pawn(Chessboard chessboard, Player player) {
        super(chessboard, player);
        this.symbol = "";
        imageWhite = GUI.loadImage("Pawn-W.png");
        imageBlack = GUI.loadImage("Pawn-B.png");
        this.setImage();
    }

    /**
     * Annotation to superclass Piece changing pawns location
     *
     * @return ArrayList with new position of piece
     */
    @Override
    public ArrayList allMoves() {
        int immediateYCoordinate;
        int twoPositionsYCoordinate;
        ArrayList list = new ArrayList();

        if (player.goDown) {
            immediateYCoordinate = square.pozY + 1;
            twoPositionsYCoordinate = square.pozY + 2;
        } else {
            immediateYCoordinate = square.pozY - 1;
            twoPositionsYCoordinate = square.pozY - 2;
        }

        if (isout(immediateYCoordinate, immediateYCoordinate)) {
            return list;
        }

        list.addAll(regularMove(immediateYCoordinate));
        if (player.goDown && square.pozY == 1 || !player.goDown && square.pozY == 6)
        {
            list.addAll(regularMove(twoPositionsYCoordinate));
        }

        // Capture left
        list.addAll(captureMove(square.pozX - 1, immediateYCoordinate));

        // Capture right
        list.addAll(captureMove(square.pozX + 1, immediateYCoordinate));

        // EnPassant left
        list.addAll(enPassantMove(square.pozX - 1, immediateYCoordinate));

        // EnPassant right
        list.addAll(enPassantMove(square.pozX + 1, immediateYCoordinate));

        return list;
    }

    void promote(Piece newPiece) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private ArrayList regularMove(Integer nextYCoordinate) {
        ArrayList list = new ArrayList();
        Square nextPosition = chessboard.squares[square.pozX][nextYCoordinate];

        if (nextPosition.piece != null) {
            return list;
        }
        if (chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, chessboard.squares[square.pozX][nextYCoordinate])) {
            list.add(chessboard.squares[square.pozX][nextYCoordinate]);
        }
        return list;
    }

    private ArrayList captureMove(Integer nextXCoordinate, Integer nextYCoordinate)
    {
        ArrayList list = new ArrayList();
        if (isout(nextXCoordinate, nextYCoordinate)) { return list; }

        Square nextPosition = chessboard.squares[nextXCoordinate][nextYCoordinate];
        if (checkPiece(nextXCoordinate, nextYCoordinate) && otherOwner(nextXCoordinate, nextYCoordinate)
            && chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, nextPosition))
        {
            list.add(nextPosition);
        }

        return list;
    }

    private ArrayList enPassantMove(Integer nextXCoordinate, Integer nextYCoordinate)
    {
        ArrayList list = new ArrayList();
        if (isout(nextXCoordinate, nextYCoordinate)) { return list; }

        Square nextPosition = chessboard.squares[nextXCoordinate][nextYCoordinate];
        Square otherPiecePosition = chessboard.squares[nextXCoordinate][square.pozY];
        if (otherPiecePosition.piece != null && otherOwner(otherPiecePosition.pozX, otherPiecePosition.pozY)
                && !otherPiecePosition.piece.name.equals("King")
                && chessboard.myKing(player.color).willBeSafeWhenMoveOtherPiece(square, nextPosition)
                && chessboard.twoSquareMovedPawn != null
                && otherPiecePosition == chessboard.twoSquareMovedPawn.square)
        {
            list.add(nextPosition);
        }

        return list;
    }
}
