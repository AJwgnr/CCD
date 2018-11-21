package com.ovgu.ccd.jchess.three;

import com.ovgu.ccd.jchess.IBoard;
import com.ovgu.ccd.jchess.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMove implements IMove {
    private Piece piece;
    private ThreePlayerChessboard board;

    KnightMove(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (ThreePlayerChessboard) board;
    }

    @Override
    public ArrayList<Square> moves() throws Exception {
        return null;
    }

    public ArrayList<Square> twoDownOneRight() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if ((3 <= piece.getPozX() + 1) && (piece.getPozX() + 1 <= 4)) {
            move = new Square(piece.getPozX() -2, piece.getPozY() + 1, null);
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
            move = new Square(piece.getPozX() +2,piece.getPozY() +1, null);
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
