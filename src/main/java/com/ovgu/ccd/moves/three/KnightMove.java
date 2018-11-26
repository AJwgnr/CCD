package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.moves.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class KnightMove implements IMove {
    private Piece piece;
    private ThreePlayerChessboard board;

    KnightMove(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (ThreePlayerChessboard) board;
    }

    @Override
    public ArrayList<Square> moves() throws Exception {
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

        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }

    public ArrayList<Square> oneDownTwoRight(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (piece.getPozX() + 1 == 12 || piece.getPozX() + 1 == 1 || piece.getPozX() + 1 == 8) { return possibleMoves; }
        if (piece.getPozY() == ThreePlayerChessboard.A || piece.getPozY() == ThreePlayerChessboard.H || piece.getPozY() == ThreePlayerChessboard.L) { return possibleMoves; }

        if ((2 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4)) {
            move = new Square(piece.getPozX() - 1, piece.getPozY() + 2, null);
        }

        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 8) && (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.D)) {
            move = new Square(piece.getPozX() + 1, piece.getPozY() - 2, null);
        }
        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 8)) {
            if (ThreePlayerChessboard.K <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() - 2, null);
            } else if (ThreePlayerChessboard.K <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() - 5, null);
            }
        }
        if ((9 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 12)) {
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() + 2, null);
            }
            if (ThreePlayerChessboard.H <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.G) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() - 2, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.F) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() + 5, null);
            }
        }
        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneDownTwoLeft(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (piece.getPozX() + 1 == 12 || piece.getPozX() + 1 == 1 || piece.getPozX() + 1 == 8) { return possibleMoves; }
        if (piece.getPozY() == ThreePlayerChessboard.A || piece.getPozY() == ThreePlayerChessboard.H || piece.getPozY() == ThreePlayerChessboard.L) { return possibleMoves; }

        if ((2 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4)) {
            move = new Square(piece.getPozX() - 1, piece.getPozY() - 2, null);
        }

        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 8) && (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L)) {
            move = new Square(piece.getPozX() + 1, piece.getPozY() + 2, null);
        }
        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 8)) {
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.B) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() + 2, null);
            } else if (ThreePlayerChessboard.C <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() + 6, null);
            }
        }
        if ((9 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 12)) {
            if (ThreePlayerChessboard.L <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() - 2, null);
            }
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.J) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() - 3, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.F) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() + 2, null);
            }
        }
        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneUpTwoRight(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 3) {
            if (ThreePlayerChessboard.G < piece.getPozY()) {
                move = new Square(piece.getPozX() + 1, piece.getPozY() + 2, null);
            }
        }

        if (piece.getPozX() + 1 == 4) {
            if (ThreePlayerChessboard.E <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.F) {
                move = new Square(piece.getPozX() + 5, piece.getPozY() + 2, null);
            } else if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.B) {
                move = new Square(4, piece.getPozY() + 2, null);
            } else {
                if (ThreePlayerChessboard.D == piece.getPozY()) {
                    move = new Square(8, ThreePlayerChessboard.J, null);
                } else {
                    if (piece.getPozY() != ThreePlayerChessboard.H && piece.getPozY() != ThreePlayerChessboard.G) {
                        move = new Square(4, ThreePlayerChessboard.I, null);
                    }
                }
            }
        }

        if (6 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 8) {
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() + 2, null);
            } else if (ThreePlayerChessboard.L <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() - 2, null);
            } else {
                move = new Square(piece.getPozX() - 1, piece.getPozY() - 6, null);
            }
        }

        if (piece.getPozX() + 1 == 5) {
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() - 2, null);
            } else if (ThreePlayerChessboard.L <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.K) {
                move = new Square(8, piece.getPozY() - 2, null);
            } else {
                if (ThreePlayerChessboard.I == piece.getPozY()) {
                    move = new Square(8, ThreePlayerChessboard.F, null);
                } else {
                    move = new Square(8, ThreePlayerChessboard.E, null);
                }
            }
        }

        if (10 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 12) {
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() + 2, null);
            } else if (ThreePlayerChessboard.G <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.H) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() - 2, null);
            } else {
                if (ThreePlayerChessboard.F == piece.getPozY()) {
                    move = new Square(piece.getPozX() - 1, piece.getPozY() + 3, null);
                } else {
                    move = new Square(piece.getPozX() - 1, piece.getPozY() + 5, null);
                }
            }
        }

        if (piece.getPozX() + 1 == 9) {
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(4, piece.getPozY() + 2, null);
            } else if (ThreePlayerChessboard.G <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.H) {
                move = new Square(3, piece.getPozY() - 2, null);
            } else {
                if (ThreePlayerChessboard.F == piece.getPozY()) {
                    move = new Square(2, ThreePlayerChessboard.D, null);
                } else {
                    move = new Square(3, ThreePlayerChessboard.C, null);
                }
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneUpTwoLeft(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 3) {
            move = new Square(piece.getPozX() + 1, piece.getPozY() - 2, null);
        }

        if (piece.getPozX() + 1 == 4) {
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.D) {
                move = new Square(4, piece.getPozY() - 2, null);
            }
            if (ThreePlayerChessboard.G <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.H) {
                move = new Square(8, piece.getPozY() - 2, null);
            }
            if (ThreePlayerChessboard.F == piece.getPozY()) {
                move = new Square(8, ThreePlayerChessboard.I, null);
            }
            if (ThreePlayerChessboard.E == piece.getPozY()) {
                move = new Square(8, ThreePlayerChessboard.J, null);
            }
        }
        if (6 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 8) {
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() + 2, null);
            }
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.B) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() + 2, null);
            }
            if (ThreePlayerChessboard.C == piece.getPozY()) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() + 6, null);
            }
            if (ThreePlayerChessboard.D == piece.getPozY()) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() + 6, null);
            }
        }
        if (piece.getPozX() + 1 == 5) {
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(8, piece.getPozY() + 2, null);
            }
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.B) {
                move = new Square(3, piece.getPozY() + 2, null);
            }
            if (ThreePlayerChessboard.C == piece.getPozY()) {
                move = new Square(3, ThreePlayerChessboard.E, null);
            }
            if (ThreePlayerChessboard.D == piece.getPozY()) {
                move = new Square(3, ThreePlayerChessboard.F, null);
            }
        }
        if (10 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 12) {
            if (ThreePlayerChessboard.E <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.F) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() + 2, null);
            }
            if (ThreePlayerChessboard.L <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.K) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() - 2, null);
            }
            if (ThreePlayerChessboard.J == piece.getPozY()) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() - 5, null);
            }
            if (ThreePlayerChessboard.I == piece.getPozY()) {
                move = new Square(piece.getPozX() - 1, piece.getPozY() - 3, null);
            }
        }
        if (9 == piece.getPozX() + 1) {
            if (ThreePlayerChessboard.E <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.F) {
                move = new Square(3, piece.getPozY() + 2, null);
            }
            if (ThreePlayerChessboard.L <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.K) {
                move = new Square(4, piece.getPozY() - 2, null);
            }
            if (ThreePlayerChessboard.J == piece.getPozY()) {
                move = new Square(4, ThreePlayerChessboard.D, null);
            }
            if (ThreePlayerChessboard.I == piece.getPozY()) {
                move = new Square(4, ThreePlayerChessboard.C, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneRightTwoUp(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 2) {
            if (piece.getPozY() != ThreePlayerChessboard.H) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() + 1, null);
            }
        }
        if (3 == piece.getPozX() + 1 || 4 == piece.getPozX() + 1) {
            if (ThreePlayerChessboard.A <= piece.getPozX() + 1 && piece.getPozX() + 1 <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() + 1, null);
            } else {
                if (piece.getPozY() != ThreePlayerChessboard.H) {
                    move = new Square(piece.getPozX() + 6, piece.getPozY() + 1, null);
                }
            }
        }
        if (7 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 8) {
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() - 2, piece.getPozY() + 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() - 2, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.I == piece.getPozY()) {
                move = new Square(piece.getPozX() - 2, ThreePlayerChessboard.D, null);
            }
        }

        if (6 == piece.getPozX() + 1) {
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() - 2, piece.getPozY() + 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(8, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.I == piece.getPozY()) {
                move = new Square(3, ThreePlayerChessboard.D, null);
            }
        }
        if (5 == piece.getPozX() + 1) {
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() - 2, piece.getPozY() + 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(9, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.I == piece.getPozY()) {
                move = new Square(2, ThreePlayerChessboard.D, null);
            }
        }

        if (11 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 12) {
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() - 2, piece.getPozY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.H) {
                move = new Square(piece.getPozX() - 2, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.E == piece.getPozY()) {
                move = new Square(piece.getPozX() - 2, ThreePlayerChessboard.I, null);
            }
        }

        if (9 == piece.getPozX() + 1) {
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(5, piece.getPozY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.H) {
                move = new Square(3, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.E == piece.getPozY()) {
                move = new Square(5, ThreePlayerChessboard.I, null);
            }
        }
        if (10 == piece.getPozX() + 1) {
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(4, piece.getPozY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.H) {
                move = new Square(3, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.E == piece.getPozY()) {
                move = new Square(4, ThreePlayerChessboard.I, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> oneRightTwoDown(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 2) {
            return possibleMoves;
        }
        if (7 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 8) {
            return possibleMoves;
        }
        if (11 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 12) {
            return possibleMoves;
        }
        if (3 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 4) {
            if (piece.getPozY() != ThreePlayerChessboard.H) {
                move = new Square(piece.getPozX() - 2, piece.getPozY() + 1, null);
            }
        }
        if (5 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 6) {
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.J <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.I == piece.getPozY()) {
                move = new Square(piece.getPozX() + 2, ThreePlayerChessboard.D, null);
            }
        }
        if (9 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 10) {
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() + 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.H) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.E == piece.getPozY()) {
                move = new Square(piece.getPozX() + 2, ThreePlayerChessboard.I, null);
            }
        }
        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneLeftTwoUp(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 4) {
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.E) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.F <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.H) {
                if (1 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 2) {
                    move = new Square(piece.getPozX() + 2, piece.getPozY() - 1, null);
                } else {
                    move = new Square(piece.getPozX() + 6, piece.getPozY() - 1, null);
                }
            }
        }
        if (5 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 8) {
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPozX() - 2, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.D == piece.getPozY()) {
                if (7 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 8) {
                    move = new Square(piece.getPozX() - 2, ThreePlayerChessboard.I, null);
                } else {
                    if (6 == piece.getPozX() + 1) {
                        move = new Square(8, piece.getPozY() + 1, null);
                    } else {
                        move = new Square(9, piece.getPozY() + 1, null);
                    }
                }
            }
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                if (7 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 8) {
                    move = new Square(piece.getPozX() - 2, piece.getPozY() + 1, null);
                } else {
                    if (6 == piece.getPozX() + 1) {
                        move = new Square(8, piece.getPozY() + 1, null);
                    } else {
                        move = new Square(9, piece.getPozY() + 1, null);
                    }
                }
            }
        }
        if (9 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 12) {
            if (ThreePlayerChessboard.E <= piece.getPozY() && piece.getPozY() < ThreePlayerChessboard.G) {
                if (11 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 12) {
                    move = new Square(piece.getPozX() - 2, piece.getPozY() + 1, null);
                } else {
                    if (10 == piece.getPozX() + 1) {
                        move = new Square(3, piece.getPozY() + 1, null);
                    } else {
                        move = new Square(2, piece.getPozY() + 1, null);
                    }
                }
            }
            if (ThreePlayerChessboard.J <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                if (11 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 12) {
                    move = new Square(piece.getPozX() - 2, piece.getPozY() - 1, null);
                } else {
                    if (10 == piece.getPozX() + 1) {
                        move = new Square(4, piece.getPozY() - 1, null);
                    } else {
                        move = new Square(5, piece.getPozY() - 1, null);
                    }
                }
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> oneLeftTwoDown(){
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 2) {
            return possibleMoves;
        }
        if (7 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 8) {
            return possibleMoves;
        }
        if (11 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 12) {
            return possibleMoves;
        }

        if (3 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 4) {
            move = new Square(piece.getPozX() - 2, piece.getPozY() - 1, null);
        }
        if (5 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 6) {
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() + 1, null);
            }
            if (ThreePlayerChessboard.A <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.C) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() + 1, null);
            }
            if (ThreePlayerChessboard.D == piece.getPozY()) {
                move = new Square(piece.getPozX() + 2, ThreePlayerChessboard.I, null);
            }
        }
        if (9 <= piece.getPozX() + 1 && piece.getPozX() + 1 <= 10) {
            if (ThreePlayerChessboard.J <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.E <= piece.getPozY() && piece.getPozY() < ThreePlayerChessboard.G) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() + 1, null);
            }
            if (ThreePlayerChessboard.I == piece.getPozY()) {
                move = new Square(piece.getPozX() + 2, ThreePlayerChessboard.E, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    };

    public ArrayList<Square> twoDownOneRight() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((3 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4)) {
            if (piece.getPozY() != ThreePlayerChessboard.H) {
                move = new Square(piece.getPozX() - 2, piece.getPozY() + 1, null);
            }
        }
        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 6)) {
            if (piece.getPozY() == ThreePlayerChessboard.I) {
                move = new Square(piece.getPozX() + 2, ThreePlayerChessboard.D, null);
            } else {
                if (piece.getPozY() != ThreePlayerChessboard.A){
                    move = new Square(piece.getPozX() +2,piece.getPozY() - 1, null);
                }
            }
        }
        if ((9 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 12)) {
            if ((ThreePlayerChessboard.F <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.H)) {
                move = new Square(piece.getPozX() +2,piece.getPozY() - 1, null);
            }
            if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.K){
                move = new Square(piece.getPozX() +2,piece.getPozY() + 1, null);
            }
            if (piece.getPozY() ==ThreePlayerChessboard.E) {
                move = new Square(piece.getPozX() + 2, ThreePlayerChessboard.I, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> twoDownOneLeft() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((3 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4)) {
            move = new Square(piece.getPozX() - 2, piece.getPozY() - 1, null);
        }

        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 6)) {
            move = new Square(piece.getPozX() + 2, piece.getPozY() + 1, null);
            if ( piece.getPozY() ==ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() + 2, ThreePlayerChessboard.I, null);
            }
        }
        if ((9 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 10)){
            if ((ThreePlayerChessboard.E <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.G)) {
                move = new Square(piece.getPozX() + 2,piece.getPozY() + 1, null);
            }
            if ((ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L)) {
                move = new Square(piece.getPozX() + 2,piece.getPozY() - 1, null);
            }
            if (piece.getPozY() == ThreePlayerChessboard.I) {
                move = new Square(piece.getPozX() + 2, ThreePlayerChessboard.E, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
                
        return possibleMoves;
    }

    public ArrayList<Square> twoUpOneRight() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((1 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 2)) {
            if (piece.getPozY() != ThreePlayerChessboard.H) {
                move = new Square(piece.getPozX() + 2, piece.getPozY() + 1, null);
            }
        }
        if ((3 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4)){
            if (piece.getPozY() ==ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() +2, ThreePlayerChessboard.I, null);
            }
            if (ThreePlayerChessboard.A <= piece.getPozY() &&piece.getPozY() <=ThreePlayerChessboard.C){
                move = new Square(piece.getPozX() +2,piece.getPozY() +1, null);
            }
            if ((ThreePlayerChessboard.E <= piece.getPozY()) &&( piece.getPozY() <=ThreePlayerChessboard.G)){
                move = new Square(piece.getPozX() +6,piece.getPozY() +1, null);
            }
        }
        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 8)) {
            if ((ThreePlayerChessboard.J <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L)){
                if (( piece.getPozX() +1) ==6){
                    move = new Square(piece.getPozX() +3,piece.getPozY() -1, null);
                } else{
                    if ((piece.getPozX() +1) == 5){
                        move = new Square(piece.getPozX() +5,piece.getPozY() -1, null);
                    } else{
                        move = new Square(piece.getPozX() -2,piece.getPozY() -1, null);
                    }
                }
            }
            if ((ThreePlayerChessboard.B <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.D)){
                move = new Square(piece.getPozX() -2,piece.getPozY() -1, null);
            }
            if (piece.getPozY() == ThreePlayerChessboard.I){
                if (8 == ( piece.getPozX() +1)){
                    move = new Square(piece.getPozX() -2, ThreePlayerChessboard.D, null);
                }
                if (7 == ( piece.getPozX() +1)){
                    move = new Square(piece.getPozX() -2, ThreePlayerChessboard.D, null);
                }
                if (6 == ( piece.getPozX() +1)){
                    move = new Square(piece.getPozX() +3, ThreePlayerChessboard.E, null);
                }
                if (5 == ( piece.getPozX() +1)){
                    move = new Square(piece.getPozX() +5, ThreePlayerChessboard.E, null);
                }
            }
        }
        if ((9 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 12) && (piece.getPozY() != ThreePlayerChessboard.L)) {
            if (piece.getPozY() == ThreePlayerChessboard.E) {
                if (12 == (piece.getPozX() + 1)){
                    move = new Square(piece.getPozX() -2, ThreePlayerChessboard.I, null);
                }
                if (11 == (piece.getPozX() + 1)){
                    move = new Square(piece.getPozX() -2, ThreePlayerChessboard.I, null);
                }
                if (10 == (piece.getPozX() + 1)){
                    move = new Square(piece.getPozX() -6, ThreePlayerChessboard.D, null);
                }
                if (9 == (piece.getPozX() + 1)){
                    move = new Square(piece.getPozX() -6, ThreePlayerChessboard.D, null);
                }
            } else {
                if (piece.getPozX() + 1 >= 11) {
                    move = new Square(piece.getPozX() - 2, piece.getPozY() - 1, null);
                } else {
                    if (ThreePlayerChessboard.I <= piece.getPozY() && piece.getPozY() <= ThreePlayerChessboard.L){
                        if (10 == (piece.getPozX() +1)){
                            move = new Square(piece.getPozX() -5,piece.getPozY() +1, null);
                        } else{
                            move = new Square(piece.getPozX() -3,piece.getPozY() +1, null);
                        }
                    } else {
                        move = new Square(piece.getPozX() -6,piece.getPozY() -1, null);
                    }
                }
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> twoUpOneLeft() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((1 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 2) && (piece.getPozY() != ThreePlayerChessboard.A)){
            move = new Square(piece.getPozX() +2,piece.getPozY() -1, null);
        }
        if ((3 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4)){
            if (piece.getPozY() ==ThreePlayerChessboard.E){
                move = new Square(piece.getPozX() +6, ThreePlayerChessboard.I, null);
            }
            if (ThreePlayerChessboard.B <= piece.getPozY() &&piece.getPozY() <=ThreePlayerChessboard.D){
                move = new Square(piece.getPozX() +2,piece.getPozY() -1, null);
            }
            if ((ThreePlayerChessboard.F <= piece.getPozY()) &&( piece.getPozY() <=ThreePlayerChessboard.H)){
                move = new Square(piece.getPozX() +6,piece.getPozY() -1, null);
            }
        }
        if ((5 <= (piece.getPozX() + 1)) && ((piece.getPozX() + 1) <= 8)){
            if ((ThreePlayerChessboard.A <= piece.getPozY() &&piece.getPozY() <=ThreePlayerChessboard.C)){
                if (( piece.getPozX() +1) ==6){
                    move = new Square(3,piece.getPozY() +1, null);
                } else{
                    if (( piece.getPozX() +1) ==5){
                        move = new Square(2,piece.getPozY() +1, null);
                    } else{
                        move = new Square(piece.getPozX() -2,piece.getPozY() +1, null);
                    }

                }
            }

            if ((ThreePlayerChessboard.I <= piece.getPozY() &&piece.getPozY() <=ThreePlayerChessboard.K)){
                if (( piece.getPozX() +1) ==6){
                    move = new Square(8,piece.getPozY() +1, null);
                } else{
                    if (( piece.getPozX() +1) ==5){
                        move = new Square(9,piece.getPozY() +1, null);
                    } else{
                        move = new Square(piece.getPozX() -2,piece.getPozY() +1, null);
                    }
                }
            }

            if (piece.getPozY() == ThreePlayerChessboard.D){
                if (8 == ( piece.getPozX() +1)){
                    move = new Square(5, ThreePlayerChessboard.I, null);
                }
                if (7 == ( piece.getPozX() +1)){
                    move = new Square(4, ThreePlayerChessboard.I, null);
                }
                if (6 == ( piece.getPozX() +1)){
                    move = new Square(3, ThreePlayerChessboard.E, null);
                }
                if (5 == ( piece.getPozX() +1)){
                    move = new Square(2, ThreePlayerChessboard.E, null);
                }
            }
        }
        if ((9 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 12) && (piece.getPozY() != ThreePlayerChessboard.H)) {
            if (piece.getPozY() == ThreePlayerChessboard.I) {
                if (12 == (piece.getPozX() +1)) {
                    move = new Square(piece.getPozX() -2, ThreePlayerChessboard.E, null);
                }
                if (11 == (piece.getPozX() +1)) {
                    move = new Square(piece.getPozX() -2, ThreePlayerChessboard.E, null);
                }
                if (10 == (piece.getPozX() +1)) {
                    move = new Square(4, ThreePlayerChessboard.D, null);
                }
                if (9 == (piece.getPozX() +1)) {
                    move = new Square(5, ThreePlayerChessboard.D, null);
                }
            } else {
                if ((ThreePlayerChessboard.J <= piece.getPozY()) && (piece.getPozY() <= ThreePlayerChessboard.L)) {
                    if (piece.getPozX() +1 >= 11){
                        move = new Square(piece.getPozX() -2,piece.getPozY() -1, null);
                    } else {
                        if (piece.getPozX() + 1 == 10) {
                            move = new Square(4, piece.getPozY() - 1, null);
                        } else {
                            move = new Square(5, piece.getPozY() - 1, null);
                        }
                    }
                } else {
                    if (piece.getPozX() +1 >= 11){
                        move = new Square(piece.getPozX() -2,piece.getPozY() +1, null);
                    } else {
                        move = new Square(piece.getPozX() -6,piece.getPozY() +1, null);
                    }
                }
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> twoRightOneUp() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((1 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4) && (piece.getPozY() != ThreePlayerChessboard.G && piece.getPozY() != ThreePlayerChessboard.H)) {
            if (piece.getPozY() >=ThreePlayerChessboard.C){
                if ((piece.getPozX() +1) <=3){
                    move = new Square(piece.getPozX() +1,piece.getPozY() +2, null);
                } else {
                    move = new Square(8,piece.getPozY() +2, null);
                }
            } else{
                move = new Square(piece.getPozX() +1,piece.getPozY() +2, null);
            }
        }

        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 8) && (piece.getPozY() != ThreePlayerChessboard.A && piece.getPozY() != ThreePlayerChessboard.B)) {
            if (piece.getPozY() ==ThreePlayerChessboard.K || piece.getPozY() ==ThreePlayerChessboard.L) {
                if (piece.getPozX() +1 >= 6) {
                    move = new Square(piece.getPozX() -1,piece.getPozY() -2, null);
                } else{
                    move = new Square(8,piece.getPozY() -2, null);
                }
            }
            if (piece.getPozY() ==ThreePlayerChessboard.I || piece.getPozY() ==ThreePlayerChessboard.J){
                if (piece.getPozX() +1 >= 6) {
                    move = new Square(piece.getPozX() -1,piece.getPozY() -6, null);
                } else {
                    move = new Square(3,piece.getPozY() -6, null);
                }
            }
            if (piece.getPozY() ==ThreePlayerChessboard.C || piece.getPozY() == ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() -1,piece.getPozY() -2, null);
            }
        }

        if ((9 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 12) && (piece.getPozY() != ThreePlayerChessboard.K && piece.getPozY() != ThreePlayerChessboard.L)) {
            if (piece.getPozY() == ThreePlayerChessboard.I || piece.getPozY() == ThreePlayerChessboard.J) {
                if (piece.getPozX() +1 >= 10) {
                    move = new Square(piece.getPozX() -1,piece.getPozY() +2, null);
                } else {
                    move = new Square(piece.getPozX() -4,piece.getPozY() +2, null);
                }
            }
            if ((piece.getPozY() == ThreePlayerChessboard.E) || (piece.getPozY() == ThreePlayerChessboard.F)) {
                if (piece.getPozX() +1 >= 10) {
                    move = new Square(piece.getPozX() -1,piece.getPozY() +5, null);
                } else {
                    if (piece.getPozY() ==ThreePlayerChessboard.F) {
                        move = new Square(4, ThreePlayerChessboard.I, null);
                    } else {
                        move = new Square(4, ThreePlayerChessboard.J, null);
                    }
                }
            }
            if (piece.getPozY() == ThreePlayerChessboard.H || piece.getPozY() == ThreePlayerChessboard.G) {
                if (piece.getPozX() +1 >= 10) {
                    move = new Square(piece.getPozX() -1,piece.getPozY() -2, null);
                } else {
                    move = new Square(3,piece.getPozY() -2, null);
                }
            }
        }
        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }

        return possibleMoves;
    }

    public ArrayList<Square> twoRightOneDown() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((1 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4) && (piece.getPozY() != ThreePlayerChessboard.G && piece.getPozY() != ThreePlayerChessboard.H)) {
            move = new Square(piece.getPozX() -1,piece.getPozY() +2, null);
        }

        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 7) && (piece.getPozY() != ThreePlayerChessboard.A && piece.getPozY() != ThreePlayerChessboard.B)) {
            if (piece.getPozY() == ThreePlayerChessboard.C || piece.getPozY() == ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() +1,piece.getPozY() -2, null);
            }
            if (piece.getPozY() == ThreePlayerChessboard.I || piece.getPozY() == ThreePlayerChessboard.J) {
                move = new Square(piece.getPozX() +1,piece.getPozY() -6, null);
            }
            if (piece.getPozY() == ThreePlayerChessboard.K || piece.getPozY() == ThreePlayerChessboard.L) {
                move = new Square(piece.getPozX() +1,piece.getPozY() -2, null);
            }
        }

        if ((9 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 11) && (piece.getPozY() != ThreePlayerChessboard.K && piece.getPozY() != ThreePlayerChessboard.L)){
            if (piece.getPozY() == ThreePlayerChessboard.I || piece.getPozY() == ThreePlayerChessboard.J) {
                move = new Square(piece.getPozX() +1,piece.getPozY() +2, null);
            }
            if (piece.getPozY() == ThreePlayerChessboard.E) {
                move = new Square(piece.getPozX() +1, ThreePlayerChessboard.J, null);
            }
            if (piece.getPozY() == ThreePlayerChessboard.F) {
                move = new Square(piece.getPozX() +1, ThreePlayerChessboard.I, null);
            }
            if (piece.getPozY() == ThreePlayerChessboard.G || piece.getPozY() == ThreePlayerChessboard.H) {
                move = new Square(piece.getPozX() +1,piece.getPozY() -2, null);
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> twoLeftOneUp() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((1 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4) && (piece.getPozY() != ThreePlayerChessboard.A && piece.getPozY() != ThreePlayerChessboard.B)){
            if ( piece.getPozX() +1 <= 3){
                move = new Square(piece.getPozX() +1,piece.getPozY() -2, null);
            } else{
                if (piece.getPozY() == ThreePlayerChessboard.G || piece.getPozY() ==ThreePlayerChessboard.H){
                    move = new Square(8, piece.getPozY() -2, null);
                } else {
                    move = new Square(4, piece.getPozY() -2, null);
                }
            }
        }
        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 8) && (piece.getPozY() != ThreePlayerChessboard.K && piece.getPozY() != ThreePlayerChessboard.L)) {
            if (piece.getPozY() == ThreePlayerChessboard.A || piece.getPozY() == ThreePlayerChessboard.B) {
                move = new Square(piece.getPozX() -1,piece.getPozY() +2, null);
            }
            if (piece.getPozY() == ThreePlayerChessboard.C || piece.getPozY() == ThreePlayerChessboard.D) {
                if ( piece.getPozX() +1 >= 6){
                    move = new Square(piece.getPozX() -1,piece.getPozY() +6, null);
                } else {
                    move = new Square(8,piece.getPozY() +6, null);
                }
            }
            if (piece.getPozY() == ThreePlayerChessboard.I || piece.getPozY() == ThreePlayerChessboard.J) {
                if (piece.getPozX() +1 >= 6){
                    move = new Square(piece.getPozX() -1,piece.getPozY() +2, null);
                } else {
                    move = new Square(8,piece.getPozY() +2, null);
                }
            }
        }
        if ((9 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 12) && (piece.getPozY() != ThreePlayerChessboard.H && piece.getPozY() != ThreePlayerChessboard.G)) {
            if (piece.getPozY() == ThreePlayerChessboard.L || piece.getPozY() == ThreePlayerChessboard.K){
                if (piece.getPozX() +1 >= 10) {
                    move = new Square(piece.getPozX() -1,piece.getPozY() -2, null);
                } else {
                    move = new Square(4,piece.getPozY() -2, null);
                }
            }
            if (piece.getPozY() == ThreePlayerChessboard.F || piece.getPozY() == ThreePlayerChessboard.E) {
                if (piece.getPozX() +1 >= 10){
                    move = new Square(piece.getPozX() -1,piece.getPozY() +2, null);
                } else {
                    move = new Square(3,piece.getPozY() +2, null);
                }
            }

            if (piece.getPozY() == ThreePlayerChessboard.J || piece.getPozY() == ThreePlayerChessboard.I){
                if (piece.getPozY() ==ThreePlayerChessboard.J) {
                    if (piece.getPozX() +1 >= 10){
                        move = new Square(piece.getPozX() -1, ThreePlayerChessboard.E, null);
                    } else{
                        move = new Square(3, ThreePlayerChessboard.E, null);
                    }
                } else {
                if (piece.getPozX() +1 >= 10){
                        move = new Square(piece.getPozX() -1, ThreePlayerChessboard.F, null);
                    } else{
                        move = new Square(3, ThreePlayerChessboard.F, null);
                    }
                }
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ArrayList<Square> twoLeftOneDown() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((2 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4)) {
            move = new Square(piece.getPozX() -1,piece.getPozY() -2, null);
        }
        if ((5 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 7)) {
            if (piece.getPozY() == ThreePlayerChessboard.C || piece.getPozY() == ThreePlayerChessboard.D) {
                move = new Square(piece.getPozX() +1,piece.getPozY() +6, null);
            } else {
                move = new Square(piece.getPozX() +1,piece.getPozY() +2, null);
            }
        }
        if ((9 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 12) && (piece.getPozY() != ThreePlayerChessboard.G && piece.getPozY() != ThreePlayerChessboard.H)){
            if (piece.getPozY() == ThreePlayerChessboard.E || piece.getPozY() == ThreePlayerChessboard.F) {
                move = new Square(piece.getPozX() +1,piece.getPozY() +2, null);
            } else {
                if (piece.getPozY() == ThreePlayerChessboard.L || piece.getPozY() == ThreePlayerChessboard.K){
                    move = new Square(piece.getPozX() +1,piece.getPozY() -2, null);
                } else{
                    if (piece.getPozY() == ThreePlayerChessboard.J) {
                        move = new Square(piece.getPozX() +1, ThreePlayerChessboard.E, null);
                    } else{
                        move = new Square(piece.getPozX() +1, ThreePlayerChessboard.F, null);
                    }
                }
            }
        }

        if (move != null && board.validMove(move)) {
            possibleMoves.add(move);
        }
        
        return possibleMoves;
    }
}
