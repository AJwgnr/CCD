package com.ovgu.ccd.moves.two;

import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Rook;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

public class KingMoves implements IMove {

    private King piece;
    private IBoard board;

    public KingMoves(Piece piece, IBoard board) {
        this.piece = (King) piece;
        this.board = board;
    }

    @Override
    public ArrayList<Square> moves() throws Exception {
        ArrayList list = new ArrayList();

        list.addAll(immediateMoves());
        list.addAll(possibleCastlings());

        return list;
    }


    private ArrayList immediateMoves() {
        Square possibleNewPosition;
        ArrayList moves = new ArrayList();

        for (int x = piece.getSquare().getPosX() - 1; x <= piece.getSquare().getPosX() + 1; x++) {
            for (int y = piece.getSquare().getPosY() - 1; y <= piece.getSquare().getPosY() + 1; y++) {
                if (!piece.outsideOfBoard(x, y)) {
                    possibleNewPosition = board.getSquare(x, y);

                    if (piece.getSquare().equals(possibleNewPosition)) { continue; }

                    if (piece.canMoveTo(possibleNewPosition) && piece.isSafe(possibleNewPosition)) {
                        moves.add(possibleNewPosition);
                    }
                }
            }
        }
        return moves;
    }

    private ArrayList possibleCastlings() {
        ArrayList list = new ArrayList();

        if (piece.isWasMotion() || piece.isChecked()) {
            return list;
        }


        if (leftCastlingPossible()) {
            list.add(board.getSquare(piece.getSquare().getPosX() - 2, piece.getSquare().getPosY()));
        }

        if (rightCastlingPossible()) {
            list.add(board.getSquare(piece.getSquare().getPosX() + 2, piece.getSquare().getPosY()));
        }

        return list;
    }

    private boolean leftCastlingPossible()
    {
        if (board.getSquare(0, piece.getSquare().getPosY()).getPiece() == null ||
            !board.getSquare(0, piece.getSquare().getPosY()).getPiece().name.equals("Rook")) { return false; }

        Rook rook = (Rook) board.getSquare(0, piece.getSquare().getPosY()).getPiece();
        if (!rook.isWasMotion())
        {
            for (int i = piece.getSquare().getPosX() - 1; i > 0; i--)
            {
                if (board.getSquare(i, piece.getSquare().getPosY()).getPiece() != null) { return false; }
            }
            Square kingsNextPosition = board.getSquare(piece.getSquare().getPosX() - 2, piece.getSquare().getPosY());
            Square rooksNextPosition = board.getSquare(piece.getSquare().getPosX() - 1, piece.getSquare().getPosY());
            return (piece.isSafe(kingsNextPosition) && piece.isSafe(rooksNextPosition));
        }

        return true;
    }

    private boolean rightCastlingPossible()
    {
        if (board.getSquare(7, piece.getSquare().getPosY()).getPiece() == null ||
            !board.getSquare(7, piece.getSquare().getPosY()).getPiece().name.equals("Rook")) { return false; }

        Rook rook = (Rook) board.getSquare(7, piece.getSquare().getPosY()).getPiece();
        if (!rook.isWasMotion())
        {
            for (int i = piece.getSquare().getPosX() + 1; i < 7; i++)
            {
                if (board.getSquare(i, piece.getSquare().getPosY()).getPiece() != null) { return false; }
            }

            Square kingsNextPosition = board.getSquare(piece.getSquare().getPosX() + 2, piece.getSquare().getPosY());
            Square rooksNextPosition = board.getSquare(piece.getSquare().getPosX() + 1, piece.getSquare().getPosY());
            return (piece.isSafe(kingsNextPosition) && piece.isSafe(rooksNextPosition));
        }

        return true;
    }
}
