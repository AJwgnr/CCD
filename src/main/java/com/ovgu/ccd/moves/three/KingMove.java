package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.jchess.ThreePlayerChessboard;
import com.ovgu.ccd.moves.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Rook;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class KingMove implements IMove {
    private Piece piece;
    private ThreePlayerChessboard board;

    KingMove(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (ThreePlayerChessboard) board;
    }

    @Override
    public ArrayList<Square> moves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        possibleMoves.addAll(rightHorizontalMoves());
        possibleMoves.addAll(leftHorizontalMoves());
        possibleMoves.addAll(upwardsMove());
        possibleMoves.addAll(downwardsMove());
        possibleMoves.addAll(leftDiagonalMoves());
        possibleMoves.addAll(rightDiagonalMoves());
        possibleMoves.addAll(rosetteMoves());
        possibleMoves.addAll(castlingMoves());

        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }

    private ArrayList<Square> rightHorizontalMoves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            if (piece.getPosY() < ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX(), piece.getPosY() + 1, null);
            }
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.B <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX(), piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX(), piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                move = new Square(piece.getPosX(), ThreePlayerChessboard.D, null);
            }
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPosX(), piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX(), piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.E) {
                move = new Square(piece.getPosX(), ThreePlayerChessboard.I, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> leftHorizontalMoves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;


        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            if (piece.getPosY() >= ThreePlayerChessboard.B) {
                move = new Square(piece.getPosX(), piece.getPosY() - 1, null);
            }
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPosX(), piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPosX(), piece.getPosY() + 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX(), ThreePlayerChessboard.I, null);
            }
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX(), piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.G) {
                move = new Square(piece.getPosX(), piece.getPosY() + 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                move = new Square(piece.getPosX(), ThreePlayerChessboard.E, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> upwardsMove() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 3) {
            move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
        }
        if (piece.getPosX() + 1 == 4) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(8, piece.getPosY(), null);
            }
        }
        if (6 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
        }
        if (piece.getPosX() + 1 == 5) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
            }
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(8, piece.getPosY(), null);
            }
        }
        if (10 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
        }
        if (9 == piece.getPosX() + 1) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(4, piece.getPosY(), null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(3, piece.getPosY(), null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> downwardsMove() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (2 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 7) {
            move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 11) {
            move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> leftDiagonalMoves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        // downwards
        if (2 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            move = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 7) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() + 1, ThreePlayerChessboard.I, null);
            }
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 11) {
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.G) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                move = new Square(piece.getPosX() + 1, ThreePlayerChessboard.E, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }

        // upwards
        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 3) {
            if (piece.getPosY() < ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
            }
        }
        if (piece.getPosX() + 1 == 4) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.D) {
                move = new Square(8, ThreePlayerChessboard.E, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.G) {
                move = new Square(8, piece.getPosY() + 1, null);
            }
        }
        if (6 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.B <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                move = new Square(piece.getPosX() - 1, ThreePlayerChessboard.E, null);
            }
        }
        if (piece.getPosX() + 1 == 5) {
            if (ThreePlayerChessboard.B <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(8, piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                move = new Square(8, ThreePlayerChessboard.E, null);
            }
        }
        if (10 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.E) {
                move = new Square(piece.getPosX() - 1, ThreePlayerChessboard.I, null);
            }
        }
        if (piece.getPosX() + 1 == 9) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(4, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(3, piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.E) {
                move = new Square(4, ThreePlayerChessboard.I, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> rightDiagonalMoves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        // downwards
        if (2 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            if (piece.getPosY() < ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
            }
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 7) {
            if (ThreePlayerChessboard.B <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                move = new Square(piece.getPosX() + 1, ThreePlayerChessboard.D, null);
            }
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 11) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.E) {
                move = new Square(piece.getPosX() + 1, ThreePlayerChessboard.I, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }

        // upwards
        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 3) {
            if (piece.getPosY() > ThreePlayerChessboard.A) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
            }
        }
        if (piece.getPosX() + 1 == 4) {
            if (ThreePlayerChessboard.B <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(8, piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.E) {
                move = new Square(5, ThreePlayerChessboard.D, null);
            }
        }
        if (6 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() - 1, ThreePlayerChessboard.I, null);
            }
        }
        if (piece.getPosX() + 1 == 5) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(8, piece.getPosY() + 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.D) {
                move = new Square(8, ThreePlayerChessboard.I, null);
            }
        }
        if (8 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.L <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.J) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.G) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                move = new Square(piece.getPosX() - 1, ThreePlayerChessboard.E, null);
            }
        }
        if (piece.getPosX() + 1 == 9) {
            if (ThreePlayerChessboard.L <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.J) {
                move = new Square(5, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.G) {
                move = new Square(3, piece.getPosY() + 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                move = new Square(3, ThreePlayerChessboard.E, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> rosetteMoves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        Square current = new Square(piece.getPosX(), piece.getPosY(), null);
        if (board.getCurrentRosette(current).equals(current)) {
            ArrayList<Square> squares = board.getDiagonalCenterPositions(current);
            for (Square square : squares) {
                if (board.validMove(square)) {
                    possibleMoves.add(square);
                }
            }
        }
        return possibleMoves;
    }

    private ArrayList<Square> castlingMoves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (board.leftCastlingPossible((King) piece)) {
            if (piece.getColor() == Player.Colors.WHITE) {
                move = new Square(0, ThreePlayerChessboard.B, null);
            } else if (piece.getColor() == Player.Colors.BLACK) {
                move = new Square(7, ThreePlayerChessboard.K, null);
            } else {
                move = new Square(11, ThreePlayerChessboard.G, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }

        if (board.rightCastlingPossible((King) piece)) {
            if (piece.getColor() == Player.Colors.WHITE) {
                move = new Square(0, ThreePlayerChessboard.G, null);
            } else if (piece.getColor() == Player.Colors.BLACK) {
                move = new Square(7, ThreePlayerChessboard.B, null);
            } else {
                move = new Square(11, ThreePlayerChessboard.K, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };
}