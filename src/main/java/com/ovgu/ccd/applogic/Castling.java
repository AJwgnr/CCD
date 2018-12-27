package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Rook;
import com.ovgu.ccd.pieces.Square;

public class Castling {

    private King king;
    private ThreePlayerChessboard board;

    public Castling(King king, ThreePlayerChessboard board) {
        this.king = king;
        this.board = board;
    }

    public boolean isRook(Piece piece) {
        if (piece == null) { return false; }
        if (!piece.name.equals("Rook")) { return false; }
        if (king != null && piece.getColor() != king.getColor()) { return false; }
        return true;
    }

    public Rook getRook(int x, int y) {
        if (king.getColor() == Player.Colors.WHITE) {
            if (leftWhiteCastling(x, y)) {
                return (Rook) board.getSquare(0, ThreePlayerChessboard.A).getPiece();
            } else {
                return (Rook) board.getSquare(0, ThreePlayerChessboard.H).getPiece();
            }
        } else if (king.getColor() == Player.Colors.BLACK) {
            if (leftBlackCastling(x, y)) {
                return (Rook) board.getSquare(7, ThreePlayerChessboard.L).getPiece();
            } else {
                return (Rook) board.getSquare(7, ThreePlayerChessboard.A).getPiece();
            }
        } else {
            if (leftGreyCastling(x, y)) {
                return (Rook) board.getSquare(11, ThreePlayerChessboard.H).getPiece();
            } else {
                return (Rook) board.getSquare(11, ThreePlayerChessboard.L).getPiece();
            }
        }
    }

    public Square newRookPosition(Piece rook) {
        Square pieceSquare = rook.getSquare();
        if (pieceSquare.getPosX() == 0 && pieceSquare.getPosY() == ThreePlayerChessboard.A) {
            return new Square(0, ThreePlayerChessboard.C, null);
        } else if (pieceSquare.getPosX() == 0 && pieceSquare.getPosY() == ThreePlayerChessboard.H) {
            return new Square(0, ThreePlayerChessboard.F, null);
        } else if (pieceSquare.getPosX() == 7 && pieceSquare.getPosY() == ThreePlayerChessboard.L) {
            return new Square(7, ThreePlayerChessboard.J, null);
        } else if (pieceSquare.getPosX() == 7 && pieceSquare.getPosY() == ThreePlayerChessboard.A) {
            return new Square(7, ThreePlayerChessboard.C, null);
        } else if (pieceSquare.getPosX() == 11 && pieceSquare.getPosY() == ThreePlayerChessboard.L) {
            return new Square(11, ThreePlayerChessboard.J, null);
        } else {
            return new Square(11, ThreePlayerChessboard.F, null);
        }
    }

    public boolean leftWhiteCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.WHITE) {
            piece = (Piece) board.getSquare(0, ThreePlayerChessboard.A).getPiece();
        }
        if (!isRook(piece)) { return false; }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) { return true; }
        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.D; y++) {
            if (board.getSquare(0, y).getPiece() != null) { return false; }
        }
        return true;
    }

    public boolean leftBlackCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.BLACK) {
            piece = (Piece) board.getSquare(7, ThreePlayerChessboard.L).getPiece();
        }
        if (!isRook(piece)) { return false; }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) { return true; }
        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            if (board.getSquare(7, y).getPiece() != null) { return false; }
        }
        return true;
    }

    public boolean leftGreyCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.GREY) {
            piece = (Piece) board.getSquare(11, ThreePlayerChessboard.H).getPiece();
        }
        if (!isRook(piece)) { return false; }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) { return true; }
        for (int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.G; y++) {
            if (board.getSquare(11, y).getPiece() != null) { return false; }
        }
        return true;
    }

    public boolean leftCastlingPossible() {
        if (king == null) { return false; }
        if (king.isWasMotion()) { return false; }
        if (king.getColor() == Player.Colors.WHITE) {
            return leftWhiteCastlingPossible();
        } else if (king.getColor() == Player.Colors.BLACK) {
            return leftBlackCastlingPossible();
        } else {
            return  leftGreyCastlingPossible();
        }
    }

    public boolean rightWhiteCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.WHITE) {
            piece = (Piece) board.getSquare(0, ThreePlayerChessboard.H).getPiece();
        }
        if (!isRook(piece)) { return false; }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) { return true; }
        for (int y = ThreePlayerChessboard.F; y <= ThreePlayerChessboard.G; y++) {
            if (board.getSquare(0, y).getPiece() != null) { return false; }
        }
        return true;
    }

    public boolean rightBlackCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.BLACK) {
            piece = board.getSquare(7, ThreePlayerChessboard.A).getPiece();
        }
        if (!isRook(piece)) { return false; }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) { return true; }
        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.C; y++) {
            if (board.getSquare(7, y).getPiece() != null) { return false; }
        }
        return true;
    }

    public boolean rightGreyCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.GREY) {
            piece = (Piece) board.getSquare(11, ThreePlayerChessboard.L).getPiece();
        }
        if (!isRook(piece)) { return false; }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) { return true; }
        for (int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.K; y++) {
            if (board.getSquare(11, y).getPiece() != null) { return false; }
        }
        return true;
    }

    public boolean rightCastlingPossible() {
        if (king == null) { return false; }
        if (king.isWasMotion()) { return false; }
        if (king.getColor() == Player.Colors.WHITE) {
            return rightWhiteCastlingPossible();
        } else if (king.getColor() == Player.Colors.BLACK) {
            return rightBlackCastlingPossible();
        } else {
            return  rightGreyCastlingPossible();
        }
    }

    public boolean leftWhiteCastling(int x, int y) {
        return leftWhiteCastlingPossible() && x == 0 && y == ThreePlayerChessboard.B;
    }

    public boolean rightWhiteCastling(int x, int y) {
        return rightWhiteCastlingPossible() && x == 0 && y == ThreePlayerChessboard.G;
    }

    public boolean leftBlackCastling(int x, int y) {
        return leftBlackCastlingPossible() && x == 7 && y == ThreePlayerChessboard.K;
    }

    public boolean rightBlackCastling(int x, int y) {
        return rightBlackCastlingPossible() && x == 7 && y == ThreePlayerChessboard.B;
    }

    public  boolean leftGreyCastling(int x, int y) {
        return leftGreyCastlingPossible() && x == 11 && y == ThreePlayerChessboard.G;
    }

    public boolean rightGreyCastling(int x, int y) {
        return rightGreyCastlingPossible() && x == 11 && y == ThreePlayerChessboard.K;
    }

    public boolean isCastling(int x, int y) {
        if (king == null) { return false; }

        boolean castling = false;
        if (king.getColor() == Player.Colors.WHITE) {
            castling = leftWhiteCastling(x, y) || rightWhiteCastling(x, y);
        } else if (king.getColor() == Player.Colors.BLACK) {
            castling = leftBlackCastling(x, y) || rightBlackCastling(x, y);
        } else {
            castling = leftGreyCastling(x, y) || rightGreyCastling(x, y);
        }
        return castling;
    }
}