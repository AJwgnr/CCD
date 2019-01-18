package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.*;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that generates king moves
 */
public class KingMoves implements IMove {
    /**
     * king for which moves are calculated
     */
    final private Piece piece;
    /**
     * board in which moves are calculated
     */
    final private ThreePlayerChessboard board;

    /**
     * @param piece king for which moves are calculated
     * @param board in which moves are calculated
     */
    public KingMoves(final Piece piece, final IBoard board) {
        this.piece = piece;
        this.board = (ThreePlayerChessboard) board;
    }

    @Override
    public ArrayList<Square> moves() throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();

        try {
            possibleMoves.addAll(allMoves(true));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }

    /**
     * @param withCheck true if we want to only return the moves
     *                 that don't generate a "check"
     * @return a list of possible moves
     * @throws Exception in case a move is invalid
     */
    public ArrayList<Square> allMoves(final boolean withCheck) throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        possibleMoves.addAll(rightHorizontalMoves());
        possibleMoves.addAll(leftHorizontalMoves());
        possibleMoves.addAll(upwardsMove());
        possibleMoves.addAll(downwardsMove());
        possibleMoves.addAll(leftDiagonalMoves());
        possibleMoves.addAll(rightDiagonalMoves());
        possibleMoves.addAll(rosetteMoves());
        possibleMoves.addAll(castlingMoves());

        final ArrayList<Square> results = new ArrayList<>();
        if (withCheck) {
            for (final Square s : possibleMoves) {
                final boolean safe = new CheckController(board, board.myKing(piece.getColor()), piece, s).isSafe();
                if (safe) {
                    results.add(new Square(s.getPosX(), s.getPosY(), null));
                }
            }
            possibleMoves = results;
        }
        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }

    private ArrayList<Square> rightHorizontalMoves() throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();
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

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> leftHorizontalMoves() throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();
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

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> upwardsMove() throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();
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

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> downwardsMove() throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();
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

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> leftDiagonalMoves() throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();
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

        if (move != null && board.validMove(move, piece)) {
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
                move = new Square(piece.getPosX() - 1, ThreePlayerChessboard.D, null);
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
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(4, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(3, piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.E) {
                move = new Square(4, ThreePlayerChessboard.I, null);
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> rightDiagonalMoves() throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        // downwards
        if (2 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            if (piece.getPosY() < ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
            }
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 7) {
            if (ThreePlayerChessboard.B <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
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

        if (move != null && board.validMove(move, piece)) {
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
                move = new Square(4, ThreePlayerChessboard.D, null);
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
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
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
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(4, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.G) {
                move = new Square(3, piece.getPosY() + 1, null);
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> rosetteMoves() throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();

        final Square current = new Square(piece.getPosX(), piece.getPosY(), null);
        if (board.getCurrentRosette(current).equals(current)) {
            final ArrayList<Square> squares = board.getDiagonalCenterPositions(current);
            for (final Square square : squares) {
                if (board.validMove(square, piece)) {
                    possibleMoves.add(square);
                }
            }
        }
        return possibleMoves;
    }

    private ArrayList<Square> castlingMoves() throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (new Castling((King) piece, board).leftCastlingPossible()) {
            if (piece.getColor() == Player.Colors.WHITE) {
                move = new Square(0, ThreePlayerChessboard.B, null);
            } else if (piece.getColor() == Player.Colors.BLACK) {
                move = new Square(7, ThreePlayerChessboard.K, null);
            } else {
                move = new Square(11, ThreePlayerChessboard.G, null);
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }

        if (new Castling((King) piece, board).rightCastlingPossible()) {
            if (piece.getColor() == Player.Colors.WHITE) {
                move = new Square(0, ThreePlayerChessboard.G, null);
            } else if (piece.getColor() == Player.Colors.BLACK) {
                move = new Square(7, ThreePlayerChessboard.B, null);
            } else {
                move = new Square(11, ThreePlayerChessboard.K, null);
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    ;
}