package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Rook;
import com.ovgu.ccd.pieces.Square;

/**
 *
 */
public class Castling {

    private King king;
    private ThreePlayerChessboard board;

    /**
     * @param king to check if in 'check' situation
     * @param board that's being used
     */
    public Castling(final King king, final ThreePlayerChessboard board) {
        this.king = king;
        this.board = board;
    }

    /**
     * @param piece to check if it is a rook
     * @return true if piece is an instance of Rook
     */
    private boolean isRook(final Piece piece) {
        if (piece == null) {
            return false;
        }
        if (!piece.name.equals("Rook")) {
            return false;
        }
        if (king != null && piece.getColor() != king.getColor()) {
            return false;
        }
        return true;
    }

    /**
     * @param posX X position that king will take
     * @param posY Y position that king will take
     * @return the corresponding rook
     */
    protected Rook getRook(final int posX, final int posY) {
        if (king.getColor() == Player.Colors.WHITE) {
            if (leftWhiteCastling(posX, posY)) {
                return (Rook) board.getSquare(0, ThreePlayerChessboard.A).getPiece();
            } else {
                return (Rook) board.getSquare(0, ThreePlayerChessboard.H).getPiece();
            }
        } else if (king.getColor() == Player.Colors.BLACK) {
            if (leftBlackCastling(posX, posY)) {
                return (Rook) board.getSquare(7, ThreePlayerChessboard.L).getPiece();
            } else {
                return (Rook) board.getSquare(7, ThreePlayerChessboard.A).getPiece();
            }
        } else {
            if (leftGreyCastling(posX, posY)) {
                return (Rook) board.getSquare(11, ThreePlayerChessboard.H).getPiece();
            } else {
                return (Rook) board.getSquare(11, ThreePlayerChessboard.L).getPiece();
            }
        }
    }

    /**
     * @param rook that takes part in castling
     * @return Square in which the rook will be afterwards
     */
    protected Square newRookPosition(final Piece rook) {
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

    /**
     * @return true if left castling is possible for white king
     */
    public boolean leftWhiteCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.WHITE) {
            piece = board.getSquare(0, ThreePlayerChessboard.A).getPiece();
        }
        if (!isRook(piece)) {
            return false;
        }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) {
            return true;
        }
        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.D; y++) {
            if (board.getSquare(0, y).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if left castling is possible for black king
     */
    public boolean leftBlackCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.BLACK) {
            piece = (Piece) board.getSquare(7, ThreePlayerChessboard.L).getPiece();
        }
        if (!isRook(piece)) {
            return false;
        }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) {
            return true;
        }
        for (int y = ThreePlayerChessboard.I; y <= ThreePlayerChessboard.K; y++) {
            if (board.getSquare(7, y).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if left castling is possible for grey king
     */
    public boolean leftGreyCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.GREY) {
            piece = (Piece) board.getSquare(11, ThreePlayerChessboard.H).getPiece();
        }
        if (!isRook(piece)) {
            return false;
        }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) {
            return true;
        }
        for (int y = ThreePlayerChessboard.E; y <= ThreePlayerChessboard.G; y++) {
            if (board.getSquare(11, y).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if left castling is possible
     */
    public boolean leftCastlingPossible() {
        if (king == null) {
            return false;
        }
        if (king.isWasMotion()) {
            return false;
        }
        if (king.getColor() == Player.Colors.WHITE) {
            return leftWhiteCastlingPossible();
        } else if (king.getColor() == Player.Colors.BLACK) {
            return leftBlackCastlingPossible();
        } else {
            return leftGreyCastlingPossible();
        }
    }

    /**
     * @return true if right castling is possible for white king
     */
    public boolean rightWhiteCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.WHITE) {
            piece = board.getSquare(0, ThreePlayerChessboard.H).getPiece();
        }
        if (!isRook(piece)) {
            return false;
        }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) {
            return true;
        }
        for (int y = ThreePlayerChessboard.F; y <= ThreePlayerChessboard.G; y++) {
            if (board.getSquare(0, y).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if right castling is possible for black king
     */
    public boolean rightBlackCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.BLACK) {
            piece = board.getSquare(7, ThreePlayerChessboard.A).getPiece();
        }
        if (!isRook(piece)) {
            return false;
        }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) {
            return true;
        }
        for (int y = ThreePlayerChessboard.B; y <= ThreePlayerChessboard.C; y++) {
            if (board.getSquare(7, y).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if right castling is possible for grey king
     */
    public boolean rightGreyCastlingPossible() {
        Piece piece = null;

        if (king.getColor() == Player.Colors.GREY) {
            piece = board.getSquare(11, ThreePlayerChessboard.L).getPiece();
        }
        if (!isRook(piece)) {
            return false;
        }
        Rook rook = (Rook) piece;
        if (rook.isWasMotion()) {
            return true;
        }
        for (int y = ThreePlayerChessboard.J; y <= ThreePlayerChessboard.K; y++) {
            if (board.getSquare(11, y).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if right castling is possible
     */
    public boolean rightCastlingPossible() {
        if (king == null) {
            return false;
        }
        if (king.isWasMotion()) {
            return false;
        }
        if (king.getColor() == Player.Colors.WHITE) {
            return rightWhiteCastlingPossible();
        } else if (king.getColor() == Player.Colors.BLACK) {
            return rightBlackCastlingPossible();
        } else {
            return rightGreyCastlingPossible();
        }
    }

    /**
     * @param posX position in which the king will be placed
     * @param posY position in which the king will be placed
     * @return true is whit king is castling to the left
     */
    public boolean leftWhiteCastling(final int posX, final int posY) {
        return leftWhiteCastlingPossible() && posX == 0 && posY == ThreePlayerChessboard.B;
    }

    /**
     * @param posX position in which the king will be placed
     * @param posY position in which the king will be placed
     * @return true is white king is castling to the right
     */
    private boolean rightWhiteCastling(final int posX, final int posY) {
        return rightWhiteCastlingPossible() && posX == 0 && posY == ThreePlayerChessboard.G;
    }

    /**
     * @param posX position in which the king will be placed
     * @param posY position in which the king will be placed
     * @return true is black king is castling to the left
     */
    public boolean leftBlackCastling(final int posX, final int posY) {
        return leftBlackCastlingPossible() && posX == 7 && posY == ThreePlayerChessboard.K;
    }

    /**
     * @param posX position in which the king will be placed
     * @param posY position in which the king will be placed
     * @return true is black king is castling to the right
     */
    public boolean rightBlackCastling(final int posX, final int posY) {
        return rightBlackCastlingPossible() && posX == 7 && posY == ThreePlayerChessboard.B;
    }

    /**
     * @param posX position in which the king will be placed
     * @param posY position in which the king will be placed
     * @return true is grey king is castling to the left
     */
    public boolean leftGreyCastling(final int posX, final int posY) {
        return leftGreyCastlingPossible() && posX == 11 && posY == ThreePlayerChessboard.G;
    }

    /**
     * @param posX position in which the king will be placed
     * @param posY position in which the king will be placed
     * @return true is grey king is castling to the right
     */
    public boolean rightGreyCastling(final int posX, final int posY) {
        return rightGreyCastlingPossible() && posX == 11 && posY == ThreePlayerChessboard.K;
    }

    /**
     * @param posX position in which the king will be placed
     * @param posY position in which the king will be placed
     * @return true if move is castling
     */
    public boolean isCastling(final int posX, final int posY) {
        if (king == null) {
            return false;
        }

        boolean castling = false;
        if (king.getColor() == Player.Colors.WHITE) {
            castling = leftWhiteCastling(posX, posY) || rightWhiteCastling(posX, posY);
        } else if (king.getColor() == Player.Colors.BLACK) {
            castling = leftBlackCastling(posX, posY) || rightBlackCastling(posX, posY);
        } else {
            castling = leftGreyCastling(posX, posY) || rightGreyCastling(posX, posY);
        }
        return castling;
    }
}
