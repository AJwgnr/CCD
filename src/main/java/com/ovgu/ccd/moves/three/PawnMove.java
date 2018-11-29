package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.jchess.ThreePlayerChessboard;
import com.ovgu.ccd.moves.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;

public class PawnMove implements IMove {
    private Piece piece;
    private ThreePlayerChessboard board;

    PawnMove(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (ThreePlayerChessboard) board;
    }

    @Override
    public ArrayList<Square> moves() throws Exception {
        if (piece.getColor() == Player.Colors.WHITE) {
            return whiteMoves();
        } else if (piece.getColor() == Player.Colors.BLACK) {
            return blackMoves();
        } else {
            return greyMoves();
        }
    }

    private ArrayList<Square> whiteMoves() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                if (piece.getPosX() + 1 <= 3) {
                    move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
                } else {
                    move = new Square(8, piece.getPosY(), null);
                }
            }
        }
        if (piece.getPosX() + 1 == 2) {
            possibleMoves.add(new Square(3, piece.getPosY(), null));
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
        }

        if (board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> blackMoves() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            if (piece.getPosX() + 1 == 5) {
                if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                    move = new Square(8, piece.getPosY(), null);
                } else {
                    move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
                }
            } else {
                move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
            }
        }
        if (piece.getPosX() + 1 == 7) {
            possibleMoves.add(new Square(4, piece.getPosY(), null));
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
        }

        if (board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    private ArrayList<Square> greyMoves() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (piece.getPosX() + 1 == 9) {
                if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                    move = new Square(4, piece.getPosY(), null);
                } else {
                    move = new Square(3, piece.getPosY(), null);
                }
            } else {
                move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
            }
        }
        if (piece.getPosX() + 1 == 11) {
            possibleMoves.add(new Square(8, piece.getPosY(), null));
        }

        if (board.validMove(move)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }
}
