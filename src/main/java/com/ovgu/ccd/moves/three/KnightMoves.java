package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.CheckController;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class KnightMoves implements IMove {
    private Piece piece;
    private ThreePlayerChessboard board;

    public KnightMoves(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (ThreePlayerChessboard) board;
    }

    @Override
    public ArrayList<Square> moves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        King king = board.myKing(piece.getColor());

        try {
            possibleMoves.addAll(allMoves(true));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return possibleMoves;
    }

    public ArrayList<Square> allMoves(boolean withCheck) throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        possibleMoves.addAll(twoDownOneRight());
        possibleMoves.addAll(twoDownOneLeft());
        possibleMoves.addAll(twoUpOneRight());
        possibleMoves.addAll(twoUpOneLeft());
        possibleMoves.addAll(twoRightOneUp());
        possibleMoves.addAll(twoRightOneDown());
        possibleMoves.addAll(twoLeftOneUp());
        possibleMoves.addAll(twoLeftOneDown());

        possibleMoves.addAll(oneDownTwoRight());
        possibleMoves.addAll(oneDownTwoLeft());
        possibleMoves.addAll(oneUpTwoRight());
        possibleMoves.addAll(oneUpTwoLeft());
        possibleMoves.addAll(oneRightTwoUp());
        possibleMoves.addAll(oneRightTwoDown());
        possibleMoves.addAll(oneLeftTwoUp());
        possibleMoves.addAll(oneLeftTwoDown());

        ArrayList<Square> results = new ArrayList<>();
        if (withCheck) {
            for (Square move : possibleMoves) {
                boolean safe = new CheckController(board, board.myKing(piece.getColor()), piece, move).isSafe();
                if (safe) {
                    results.add(new Square(move.getPosX(), move.getPosY(), null));
                }
            }
        } else {
            results = possibleMoves;
        }
        return new ArrayList<Square>(Arrays.asList(results.stream().distinct().toArray(Square[]::new)));
    }

    public ArrayList<Square> oneDownTwoRight(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (piece.getPosX() + 1 == 12 || piece.getPosX() + 1 == 1 || piece.getPosX() + 1 == 8) { return possibleMoves; }
        if (piece.getPosY() == ThreePlayerChessboard.A || piece.getPosY() == ThreePlayerChessboard.H || piece.getPosY() == ThreePlayerChessboard.L) { return possibleMoves; }

        if ((2 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 4)) {
            move = new Square(piece.getPosX() - 1, piece.getPosY() + 2, null);
        }

        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 8) && (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D)) {
            move = new Square(piece.getPosX() + 1, piece.getPosY() - 2, null);
        }
        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 8)) {
            if (ThreePlayerChessboard.K <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 2, null);
            } else if (ThreePlayerChessboard.K <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 5, null);
            }
        }
        if ((9 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 12)) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 2, null);
            }
            if (ThreePlayerChessboard.H <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.G) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 2, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() < ThreePlayerChessboard.F) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 5, null);
            }
        }
        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneDownTwoLeft(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (piece.getPosX() + 1 == 12 || piece.getPosX() + 1 == 1 || piece.getPosX() + 1 == 8) { return possibleMoves; }
        if (piece.getPosY() == ThreePlayerChessboard.A || piece.getPosY() == ThreePlayerChessboard.H || piece.getPosY() == ThreePlayerChessboard.L) { return possibleMoves; }

        if ((2 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 4)) {
            move = new Square(piece.getPosX() - 1, piece.getPosY() - 2, null);
        }

        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 8) && (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L)) {
            move = new Square(piece.getPosX() + 1, piece.getPosY() + 2, null);
        }
        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 8)) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.B) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 2, null);
            } else if (ThreePlayerChessboard.C <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 6, null);
            }
        }
        if ((9 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 12)) {
            if (ThreePlayerChessboard.K <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 2, null);
            }
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() < ThreePlayerChessboard.J) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() - 3, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.F) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 2, null);
            }
        }
        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneUpTwoRight(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 3) {
            if (ThreePlayerChessboard.G < piece.getPosY()) {
                move = new Square(piece.getPosX() + 1, piece.getPosY() + 2, null);
            }
        }

        if (piece.getPosX() + 1 == 4) {
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.F) {
                move = new Square(piece.getPosX() + 5, piece.getPosY() + 2, null);
            } else if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.B) {
                move = new Square(4, piece.getPosY() + 2, null);
            } else {
                if (ThreePlayerChessboard.D == piece.getPosY()) {
                    move = new Square(4, ThreePlayerChessboard.J, null);
                } else {
                    if (piece.getPosY() != ThreePlayerChessboard.H && piece.getPosY() != ThreePlayerChessboard.G) {
                        move = new Square(4, ThreePlayerChessboard.I, null);
                    }
                }
            }
        }

        if (6 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 2, null);
            } else if (ThreePlayerChessboard.L <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 2, null);
            } else {
                if (piece.getPosY() != ThreePlayerChessboard.F) {
                    move = new Square(piece.getPosX() - 1, piece.getPosY() - 6, null);
                }
            }
        }

        if (piece.getPosX() + 1 == 5) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 2, null);
            } else if (ThreePlayerChessboard.K <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = null;
            } else {
                if (ThreePlayerChessboard.I == piece.getPosY()) {
                    move = new Square(8, ThreePlayerChessboard.F, null);
                } else {
                    move = new Square(8, ThreePlayerChessboard.E, null);
                }
            }
        }

        if (10 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 2, null);
            } else if (ThreePlayerChessboard.G <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 2, null);
            } else {
                if (ThreePlayerChessboard.F == piece.getPosY()) {
                    move = new Square(piece.getPosX() - 1, piece.getPosY() + 3, null);
                } else {
                    move = new Square(piece.getPosX() - 1, piece.getPosY() + 5, null);
                }
            }
        }

        if (piece.getPosX() + 1 == 9) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(4, piece.getPosY() + 2, null);
            } else if (ThreePlayerChessboard.G <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(3, piece.getPosY() - 2, null);
            } else {
                if (ThreePlayerChessboard.F == piece.getPosY()) {
                    move = new Square(3, ThreePlayerChessboard.D, null);
                } else {
                    move = new Square(3, ThreePlayerChessboard.C, null);
                }
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneUpTwoLeft(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 3) {
            move = new Square(piece.getPosX() + 1, piece.getPosY() - 2, null);
        }

        if (piece.getPosX() + 1 == 4) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(4, piece.getPosY() - 2, null);
            }
            if (ThreePlayerChessboard.G <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(8, piece.getPosY() - 2, null);
            }
            if (ThreePlayerChessboard.F == piece.getPosY()) {
                move = new Square(8, ThreePlayerChessboard.I, null);
            }
            if (ThreePlayerChessboard.E == piece.getPosY()) {
                move = new Square(8, ThreePlayerChessboard.J, null);
            }
        }
        if (6 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 2, null);
            }
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.B) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 2, null);
            }
            if (ThreePlayerChessboard.C == piece.getPosY()) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 6, null);
            }
            if (ThreePlayerChessboard.D == piece.getPosY()) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 6, null);
            }
        }
        if (piece.getPosX() + 1 == 5) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(8, piece.getPosY() + 2, null);
            }
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.B) {
                move = new Square(3, piece.getPosY() + 2, null);
            }
            if (ThreePlayerChessboard.C == piece.getPosY()) {
                move = new Square(3, ThreePlayerChessboard.E, null);
            }
            if (ThreePlayerChessboard.D == piece.getPosY()) {
                move = new Square(3, ThreePlayerChessboard.F, null);
            }
        }
        if (10 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.F) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() + 2, null);
            }
            if (ThreePlayerChessboard.L <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 2, null);
            }
            if (ThreePlayerChessboard.J == piece.getPosY()) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 5, null);
            }
            if (ThreePlayerChessboard.I == piece.getPosY()) {
                move = new Square(piece.getPosX() - 1, piece.getPosY() - 3, null);
            }
        }
        if (9 == piece.getPosX() + 1) {
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.F) {
                move = new Square(3, piece.getPosY() + 2, null);
            }
            if (ThreePlayerChessboard.L <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K) {
                move = new Square(4, piece.getPosY() - 2, null);
            }
            if (ThreePlayerChessboard.J == piece.getPosY()) {
                move = new Square(4, ThreePlayerChessboard.D, null);
            }
            if (ThreePlayerChessboard.I == piece.getPosY()) {
                move = new Square(4, ThreePlayerChessboard.C, null);
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneRightTwoUp(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 2) {
            if (piece.getPosY() != ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() + 1, null);
            }
        }
        if (3 == piece.getPosX() + 1 || 4 == piece.getPosX() + 1) {
            if (ThreePlayerChessboard.A <= piece.getPosX() + 1 && piece.getPosX() + 1 <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() + 1, null);
            } else {
                if (piece.getPosY() != ThreePlayerChessboard.H) {
                    move = new Square(piece.getPosX() + 6, piece.getPosY() + 1, null);
                }
            }
        }
        if (7 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() - 2, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() - 2, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.I == piece.getPosY()) {
                move = new Square(piece.getPosX() - 2, ThreePlayerChessboard.D, null);
            }
        }

        if (6 == piece.getPosX() + 1) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() - 2, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(8, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.I == piece.getPosY()) {
                move = new Square(3, ThreePlayerChessboard.D, null);
            }
        }
        if (5 == piece.getPosX() + 1) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() < ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() - 2, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(9, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.I == piece.getPosY()) {
                move = new Square(2, ThreePlayerChessboard.D, null);
            }
            if (ThreePlayerChessboard.D == piece.getPosY()) {
                move = new Square(2, ThreePlayerChessboard.C, null);
            }
        }

        if (11 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() - 2, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() - 2, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.E == piece.getPosY()) {
                move = new Square(piece.getPosX() - 2, ThreePlayerChessboard.I, null);
            }
        }

        if (9 == piece.getPosX() + 1) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(5, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(3, piece.getPosY() - 2, null);
            }
            if (ThreePlayerChessboard.E == piece.getPosY()) {
                move = new Square(5, ThreePlayerChessboard.I, null);
            }
        }
        if (10 == piece.getPosX() + 1) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(4, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(3, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.E == piece.getPosY()) {
                move = new Square(4, ThreePlayerChessboard.I, null);
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> oneRightTwoDown(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 2) {
            return possibleMoves;
        }
        if (7 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            return possibleMoves;
        }
        if (11 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            return possibleMoves;
        }
        if (3 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            if (piece.getPosY() != ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() - 2, piece.getPosY() + 1, null);
            }
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 6) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.I == piece.getPosY()) {
                move = new Square(piece.getPosX() + 2, ThreePlayerChessboard.D, null);
            }
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 10) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.E == piece.getPosY()) {
                move = new Square(piece.getPosX() + 2, ThreePlayerChessboard.I, null);
            }
        }
        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneLeftTwoUp(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.E) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 2) {
                    move = new Square(piece.getPosX() + 2, piece.getPosY() - 1, null);
                } else {
                    move = new Square(piece.getPosX() + 6, piece.getPosY() - 1, null);
                }
            }
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPosX() - 2, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.D == piece.getPosY()) {
                if (7 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
                    move = new Square(piece.getPosX() - 2, ThreePlayerChessboard.I, null);
                } else {
                    if (6 == (piece.getPosX() + 1)) {
                        move = new Square(8, ThreePlayerChessboard.I, null);
                    } else {
                        if (piece.getPosX() + 1 == 5) {
                            move = new Square(9, ThreePlayerChessboard.I, null);
                        } else {
                            move = new Square(9, piece.getPosY() + 1, null);
                        }
                    }
                }
            }
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                if (7 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
                    move = new Square(piece.getPosX() - 2, piece.getPosY() + 1, null);
                } else {
                    if (6 == piece.getPosX() + 1) {
                        move = new Square(8, piece.getPosY() + 1, null);
                    } else {
                        if (piece.getPosY() == ThreePlayerChessboard.I && piece.getPosX() + 1 == 8) {
                            move = new Square(2, ThreePlayerChessboard.E, null);
                        } else {
                            move = new Square(9, piece.getPosY() + 1, null);
                        }
                    }
                }
            }
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() < ThreePlayerChessboard.G) {
                if (11 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
                    move = new Square(piece.getPosX() - 2, piece.getPosY() + 1, null);
                } else {
                    if (10 == piece.getPosX() + 1) {
                        move = new Square(3, piece.getPosY() + 1, null);
                    } else {
                        move = new Square(2, piece.getPosY() + 1, null);
                    }
                }
            }
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                if (11 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
                    move = new Square(piece.getPosX() - 2, piece.getPosY() - 1, null);
                } else {
                    if (10 == piece.getPosX() + 1) {
                        move = new Square(4, piece.getPosY() - 1, null);
                    } else {
                        move = new Square(5, piece.getPosY() - 1, null);
                    }
                }
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                if(piece.getPosX() + 1 == 9) {
                    move = new Square(2, ThreePlayerChessboard.E, null);
                }
                if(piece.getPosX() + 1 == 10) {
                    move = new Square(3, ThreePlayerChessboard.E, null);
                }
                if(piece.getPosX() + 1 == 11) {
                    move = new Square(8, ThreePlayerChessboard.E, null);
                }
                if(piece.getPosX() + 1 == 12) {
                    move = new Square(9, ThreePlayerChessboard.E, null);
                }
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneLeftTwoDown(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 2) {
            return possibleMoves;
        }
        if (7 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            return possibleMoves;
        }
        if (11 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            return possibleMoves;
        }

        if (3 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            move = new Square(piece.getPosX() - 2, piece.getPosY() - 1, null);
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 6) {
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.D == piece.getPosY()) {
                move = new Square(piece.getPosX() + 2, ThreePlayerChessboard.I, null);
            }
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 10) {
            if (ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() < ThreePlayerChessboard.G) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() + 1, null);
            }
            if (ThreePlayerChessboard.I == piece.getPosY()) {
                move = new Square(piece.getPosX() + 2, ThreePlayerChessboard.E, null);
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> twoDownOneRight() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((3 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 4)) {
            if (piece.getPosY() != ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() - 2, piece.getPosY() + 1, null);
            }
        }
        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 6)) {
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                move = new Square(piece.getPosX() + 2, ThreePlayerChessboard.D, null);
            } else {
                if (piece.getPosY() != ThreePlayerChessboard.A){
                    move = new Square(piece.getPosX() +2,piece.getPosY() - 1, null);
                }
            }
        }
        if ((9 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 12)) {
            if ((ThreePlayerChessboard.F <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H)) {
                move = new Square(piece.getPosX() +2,piece.getPosY() - 1, null);
            }
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.K){
                move = new Square(piece.getPosX() +2,piece.getPosY() + 1, null);
            }
            if (piece.getPosY() ==ThreePlayerChessboard.E) {
                move = new Square(piece.getPosX() + 2, ThreePlayerChessboard.I, null);
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> twoDownOneLeft() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((3 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 4)) {
            move = new Square(piece.getPosX() - 2, piece.getPosY() - 1, null);
        }

        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 6)) {
            move = new Square(piece.getPosX() + 2, piece.getPosY() + 1, null);
            if ( piece.getPosY() ==ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() + 2, ThreePlayerChessboard.I, null);
            }
        }
        if ((9 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 10)){
            if ((ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.G)) {
                move = new Square(piece.getPosX() + 2,piece.getPosY() + 1, null);
            }
            if ((ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L)) {
                move = new Square(piece.getPosX() + 2,piece.getPosY() - 1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                move = new Square(piece.getPosX() + 2, ThreePlayerChessboard.E, null);
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
                
        return possibleMoves;
    }

    public ArrayList<Square> twoUpOneRight() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((1 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 2)) {
            if (piece.getPosY() != ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() + 2, piece.getPosY() + 1, null);
            }
        }
        if ((3 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 4)){
            if (piece.getPosY() ==ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() +2, ThreePlayerChessboard.I, null);
            }
            if (ThreePlayerChessboard.A <= piece.getPosY() &&piece.getPosY() <=ThreePlayerChessboard.C){
                move = new Square(piece.getPosX() +2,piece.getPosY() +1, null);
            }
            if ((ThreePlayerChessboard.E <= piece.getPosY()) &&( piece.getPosY() <=ThreePlayerChessboard.G)){
                move = new Square(piece.getPosX() +6,piece.getPosY() +1, null);
            }
        }
        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 8)) {
            if ((ThreePlayerChessboard.J <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L)){
                if (( piece.getPosX() +1) ==6){
                    move = new Square(piece.getPosX() +3,piece.getPosY() -1, null);
                } else{
                    if ((piece.getPosX() +1) == 5){
                        move = new Square(piece.getPosX() +5,piece.getPosY() -1, null);
                    } else{
                        move = new Square(piece.getPosX() -2,piece.getPosY() -1, null);
                    }
                }
            }
            if ((ThreePlayerChessboard.B <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D)){
                move = new Square(piece.getPosX() -2,piece.getPosY() -1, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I){
                if (8 == ( piece.getPosX() +1)){
                    move = new Square(piece.getPosX() -2, ThreePlayerChessboard.D, null);
                }
                if (7 == ( piece.getPosX() +1)){
                    move = new Square(piece.getPosX() -2, ThreePlayerChessboard.D, null);
                }
                if (6 == ( piece.getPosX() +1)){
                    move = new Square(piece.getPosX() +3, ThreePlayerChessboard.E, null);
                }
                if (5 == ( piece.getPosX() +1)){
                    move = new Square(piece.getPosX() +5, ThreePlayerChessboard.E, null);
                }
            }
        }
        if ((9 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 12) && (piece.getPosY() != ThreePlayerChessboard.L)) {
            if (piece.getPosY() == ThreePlayerChessboard.E) {
                if (12 == (piece.getPosX() + 1)){
                    move = new Square(piece.getPosX() -2, ThreePlayerChessboard.I, null);
                }
                if (11 == (piece.getPosX() + 1)){
                    move = new Square(piece.getPosX() -2, ThreePlayerChessboard.I, null);
                }
                if (10 == (piece.getPosX() + 1)){
                    move = new Square(piece.getPosX() -6, ThreePlayerChessboard.D, null);
                }
                if (9 == (piece.getPosX() + 1)){
                    move = new Square(piece.getPosX() -6, ThreePlayerChessboard.D, null);
                }
            } else {
                if (piece.getPosY() == ThreePlayerChessboard.I ){
                    if (piece.getPosX() + 1 >= 11) {
                        move = new Square(piece.getPosX() - 2, ThreePlayerChessboard.E, null);
                    } else {
                        if (piece.getPosX() + 1 == 10) {
                            move = new Square(4, ThreePlayerChessboard.D, null);
                        } else {
                            move = new Square(5, ThreePlayerChessboard.D, null);
                        }
                    }
                } else {
                    if (piece.getPosX() + 1 >= 11) {
                        move = new Square(piece.getPosX() - 2, piece.getPosY() - 1, null);
                    } else {
                        if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                            if (10 == (piece.getPosX() + 1)) {
                                move = new Square(piece.getPosX() - 5, piece.getPosY() + 1, null);
                            } else {
                                move = new Square(piece.getPosX() - 3, piece.getPosY() + 1, null);
                            }
                        } else {
                            move = new Square(piece.getPosX() - 6, piece.getPosY() - 1, null);
                        }
                    }
                }
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> twoUpOneLeft() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((1 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 2) && (piece.getPosY() != ThreePlayerChessboard.A)){
            move = new Square(piece.getPosX() +2,piece.getPosY() -1, null);
        }
        if ((3 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 4)){
            if (piece.getPosY() ==ThreePlayerChessboard.E){
                move = new Square(piece.getPosX() +6, ThreePlayerChessboard.I, null);
            }
            if (ThreePlayerChessboard.B <= piece.getPosY() &&piece.getPosY() <=ThreePlayerChessboard.D){
                move = new Square(piece.getPosX() +2,piece.getPosY() -1, null);
            }
            if ((ThreePlayerChessboard.F <= piece.getPosY()) &&( piece.getPosY() <=ThreePlayerChessboard.H)){
                move = new Square(piece.getPosX() +6,piece.getPosY() -1, null);
            }
        }
        if ((5 <= (piece.getPosX() + 1)) && ((piece.getPosX() + 1) <= 8)){
            if ((ThreePlayerChessboard.A <= piece.getPosY() &&piece.getPosY() <=ThreePlayerChessboard.C)){
                if (( piece.getPosX() +1) ==6){
                    move = new Square(3,piece.getPosY() +1, null);
                } else{
                    if (( piece.getPosX() +1) ==5){
                        move = new Square(2,piece.getPosY() +1, null);
                    } else{
                        move = new Square(piece.getPosX() -2,piece.getPosY() +1, null);
                    }

                }
            }

            if ((ThreePlayerChessboard.I <= piece.getPosY() &&piece.getPosY() <=ThreePlayerChessboard.K)){
                if (( piece.getPosX() +1) ==6){
                    move = new Square(8,piece.getPosY() +1, null);
                } else{
                    if (( piece.getPosX() +1) ==5){
                        move = new Square(9,piece.getPosY() +1, null);
                    } else{
                        move = new Square(piece.getPosX() -2,piece.getPosY() +1, null);
                    }
                }
            }

            if (piece.getPosY() == ThreePlayerChessboard.D){
                if (8 == ( piece.getPosX() +1)){
                    move = new Square(5, ThreePlayerChessboard.I, null);
                }
                if (7 == ( piece.getPosX() +1)){
                    move = new Square(4, ThreePlayerChessboard.I, null);
                }
                if (6 == ( piece.getPosX() +1)){
                    move = new Square(3, ThreePlayerChessboard.E, null);
                }
                if (5 == ( piece.getPosX() +1)){
                    move = new Square(2, ThreePlayerChessboard.E, null);
                }
            }
        }
        if ((9 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 12) && (piece.getPosY() != ThreePlayerChessboard.H)) {
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                if (12 == (piece.getPosX() +1)) {
                    move = new Square(piece.getPosX() -2, ThreePlayerChessboard.E, null);
                }
                if (11 == (piece.getPosX() +1)) {
                    move = new Square(piece.getPosX() -2, ThreePlayerChessboard.E, null);
                }
                if (10 == (piece.getPosX() +1)) {
                    move = new Square(4, ThreePlayerChessboard.D, null);
                }
                if (9 == (piece.getPosX() +1)) {
                    move = new Square(5, ThreePlayerChessboard.D, null);
                }
            } else {
                if ((ThreePlayerChessboard.J <= piece.getPosY()) && (piece.getPosY() <= ThreePlayerChessboard.L)) {
                    if (piece.getPosX() +1 >= 11){
                        move = new Square(piece.getPosX() -2,piece.getPosY() -1, null);
                    } else {
                        if (piece.getPosX() + 1 == 10) {
                            move = new Square(4, piece.getPosY() - 1, null);
                        } else {
                            move = new Square(5, piece.getPosY() - 1, null);
                        }
                    }
                } else {
                    if (piece.getPosX() +1 >= 11){
                        move = new Square(piece.getPosX() -2,piece.getPosY() +1, null);
                    } else {
                        move = new Square(piece.getPosX() -6,piece.getPosY() +1, null);
                    }
                }
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> twoRightOneUp() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((1 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 4) && (piece.getPosY() != ThreePlayerChessboard.G && piece.getPosY() != ThreePlayerChessboard.H)) {
            if (piece.getPosY() >=ThreePlayerChessboard.C){
                if ((piece.getPosX() +1) <=3){
                    move = new Square(piece.getPosX() +1,piece.getPosY() +2, null);
                } else {
                    move = new Square(8,piece.getPosY() +2, null);
                }
            } else{
                move = new Square(piece.getPosX() +1,piece.getPosY() +2, null);
            }
        }

        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 8) && (piece.getPosY() != ThreePlayerChessboard.A && piece.getPosY() != ThreePlayerChessboard.B)) {
            if (piece.getPosY() ==ThreePlayerChessboard.K || piece.getPosY() ==ThreePlayerChessboard.L) {
                if (piece.getPosX() +1 >= 6) {
                    move = new Square(piece.getPosX() -1,piece.getPosY() -2, null);
                } else{
                    move = new Square(8,piece.getPosY() -2, null);
                }
            }
            if (piece.getPosY() ==ThreePlayerChessboard.I || piece.getPosY() ==ThreePlayerChessboard.J){
                if (piece.getPosX() +1 >= 6) {
                    move = new Square(piece.getPosX() -1,piece.getPosY() -6, null);
                } else {
                    move = new Square(3,piece.getPosY() -6, null);
                }
            }
            if (piece.getPosY() ==ThreePlayerChessboard.C || piece.getPosY() == ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() -1,piece.getPosY() -2, null);
            }
        }

        if ((9 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 12) && (piece.getPosY() != ThreePlayerChessboard.K && piece.getPosY() != ThreePlayerChessboard.L)) {
            if (piece.getPosY() == ThreePlayerChessboard.I || piece.getPosY() == ThreePlayerChessboard.J) {
                if (piece.getPosX() +1 >= 10) {
                    move = new Square(piece.getPosX() -1,piece.getPosY() +2, null);
                } else {
                    move = new Square(piece.getPosX() -4,piece.getPosY() +2, null);
                }
            }
            if ((piece.getPosY() == ThreePlayerChessboard.E) || (piece.getPosY() == ThreePlayerChessboard.F)) {
                if (piece.getPosX() +1 >= 10) {
                    if (piece.getPosY() == ThreePlayerChessboard.E) {
                        move = new Square(piece.getPosX() - 1, ThreePlayerChessboard.J, null);
                    } else {
                        move = new Square(piece.getPosX() - 1, ThreePlayerChessboard.I, null);
                    }
                } else {
                    if (piece.getPosY() ==ThreePlayerChessboard.F) {
                        move = new Square(4, ThreePlayerChessboard.I, null);
                    } else {
                        move = new Square(4, ThreePlayerChessboard.J, null);
                    }
                }
            }
            if (piece.getPosY() == ThreePlayerChessboard.H || piece.getPosY() == ThreePlayerChessboard.G) {
                if (piece.getPosX() +1 >= 10) {
                    move = new Square(piece.getPosX() -1,piece.getPosY() -2, null);
                } else {
                    move = new Square(3,piece.getPosY() -2, null);
                }
            }
        }
        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }

        return possibleMoves;
    }

    public ArrayList<Square> twoRightOneDown() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((1 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 4) && (piece.getPosY() != ThreePlayerChessboard.G && piece.getPosY() != ThreePlayerChessboard.H)) {
            move = new Square(piece.getPosX() -1,piece.getPosY() +2, null);
        }

        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 7) && (piece.getPosY() != ThreePlayerChessboard.A && piece.getPosY() != ThreePlayerChessboard.B)) {
            if (piece.getPosY() == ThreePlayerChessboard.C || piece.getPosY() == ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() +1,piece.getPosY() -2, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I || piece.getPosY() == ThreePlayerChessboard.J) {
                move = new Square(piece.getPosX() +1,piece.getPosY() -6, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.K || piece.getPosY() == ThreePlayerChessboard.L) {
                move = new Square(piece.getPosX() +1,piece.getPosY() -2, null);
            }
        }

        if ((9 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 11) && (piece.getPosY() != ThreePlayerChessboard.K && piece.getPosY() != ThreePlayerChessboard.L)){
            if (piece.getPosY() == ThreePlayerChessboard.I || piece.getPosY() == ThreePlayerChessboard.J) {
                move = new Square(piece.getPosX() +1,piece.getPosY() +2, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.E) {
                move = new Square(piece.getPosX() +1, ThreePlayerChessboard.J, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.F) {
                move = new Square(piece.getPosX() +1, ThreePlayerChessboard.I, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.G || piece.getPosY() == ThreePlayerChessboard.H) {
                move = new Square(piece.getPosX() +1,piece.getPosY() -2, null);
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> twoLeftOneUp() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((1 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 4) && (piece.getPosY() != ThreePlayerChessboard.A && piece.getPosY() != ThreePlayerChessboard.B)){
            if ( piece.getPosX() +1 <= 3){
                move = new Square(piece.getPosX() +1,piece.getPosY() -2, null);
            } else{
                if (piece.getPosY() == ThreePlayerChessboard.G || piece.getPosY() ==ThreePlayerChessboard.H){
                    move = new Square(8, piece.getPosY() -2, null);
                } else {
                    move = new Square(4, piece.getPosY() -2, null);
                }
            }
        }
        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 8) && (piece.getPosY() != ThreePlayerChessboard.K && piece.getPosY() != ThreePlayerChessboard.L)) {
            if (piece.getPosY() == ThreePlayerChessboard.A || piece.getPosY() == ThreePlayerChessboard.B) {
                move = new Square(piece.getPosX() -1,piece.getPosY() +2, null);
            }
            if (piece.getPosY() == ThreePlayerChessboard.C || piece.getPosY() == ThreePlayerChessboard.D) {
                if ( piece.getPosX() +1 >= 6){
                    move = new Square(piece.getPosX() -1,piece.getPosY() +6, null);
                } else {
                    move = new Square(8,piece.getPosY() +6, null);
                }
            }
            if (piece.getPosY() == ThreePlayerChessboard.I || piece.getPosY() == ThreePlayerChessboard.J) {
                if (piece.getPosX() +1 >= 6){
                    move = new Square(piece.getPosX() -1,piece.getPosY() +2, null);
                } else {
                    move = new Square(8,piece.getPosY() +2, null);
                }
            }
        }
        if ((9 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 12) && (piece.getPosY() != ThreePlayerChessboard.H && piece.getPosY() != ThreePlayerChessboard.G)) {
            if (piece.getPosY() == ThreePlayerChessboard.L || piece.getPosY() == ThreePlayerChessboard.K){
                if (piece.getPosX() +1 >= 10) {
                    move = new Square(piece.getPosX() -1,piece.getPosY() -2, null);
                } else {
                    move = new Square(4,piece.getPosY() -2, null);
                }
            }
            if (piece.getPosY() == ThreePlayerChessboard.F || piece.getPosY() == ThreePlayerChessboard.E) {
                if (piece.getPosX() +1 >= 10){
                    move = new Square(piece.getPosX() -1,piece.getPosY() +2, null);
                } else {
                    move = new Square(3,piece.getPosY() +2, null);
                }
            }

            if (piece.getPosY() == ThreePlayerChessboard.J || piece.getPosY() == ThreePlayerChessboard.I){
                if (piece.getPosY() ==ThreePlayerChessboard.J) {
                    if (piece.getPosX() +1 >= 10){
                        move = new Square(piece.getPosX() -1, ThreePlayerChessboard.E, null);
                    } else{
                        move = new Square(3, ThreePlayerChessboard.E, null);
                    }
                } else {
                if (piece.getPosX() +1 >= 10){
                        move = new Square(piece.getPosX() -1, ThreePlayerChessboard.F, null);
                    } else{
                        move = new Square(3, ThreePlayerChessboard.F, null);
                    }
                }
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> twoLeftOneDown() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((2 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 4)) {
            move = new Square(piece.getPosX() -1,piece.getPosY() -2, null);
        }
        if ((5 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 7)) {
            if (piece.getPosY() == ThreePlayerChessboard.C || piece.getPosY() == ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() +1,piece.getPosY() +6, null);
            } else {
                move = new Square(piece.getPosX() +1,piece.getPosY() +2, null);
            }
        }
        if ((9 <= piece.getPosX() + 1) && (piece.getPosX() + 1 <= 12) && (piece.getPosY() != ThreePlayerChessboard.G && piece.getPosY() != ThreePlayerChessboard.H)){
            if (piece.getPosY() == ThreePlayerChessboard.E || piece.getPosY() == ThreePlayerChessboard.F) {
                move = new Square(piece.getPosX() +1,piece.getPosY() +2, null);
            } else {
                if (piece.getPosY() == ThreePlayerChessboard.L || piece.getPosY() == ThreePlayerChessboard.K){
                    move = new Square(piece.getPosX() +1,piece.getPosY() -2, null);
                } else{
                    if (piece.getPosY() == ThreePlayerChessboard.J) {
                        move = new Square(piece.getPosX() +1, ThreePlayerChessboard.E, null);
                    } else{
                        move = new Square(piece.getPosX() +1, ThreePlayerChessboard.F, null);
                    }
                }
            }
        }

        if (move != null && board.validMove(move, piece)) {
            possibleMoves.add(move);
        }
        
        return possibleMoves;
    }
}
