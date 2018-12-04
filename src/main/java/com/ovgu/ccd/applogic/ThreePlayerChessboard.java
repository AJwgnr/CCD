package com.ovgu.ccd.applogic;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class ThreePlayerChessboard implements IBoard {
    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
    public static final int D = 3;
    public static final int E = 4;
    public static final int F = 5;
    public static final int G = 6;
    public static final int H = 7;
    public static final int I = 8;
    public static final int J = 9;
    public static final int K = 10;
    public static final int L = 11;

    public static final int COLUMNS = 12;

    private Square[][] matrix = null;

    private ArrayList<Square> WHITE_ROSETTE = new ArrayList<Square>(Arrays.asList(new Square(8, I, null), new Square(4, D, null), new Square(3, E, null)));
    private ArrayList<Square> BLACK_ROSETTE = new ArrayList<Square>(Arrays.asList(new Square(4, I, null), new Square(3, D, null), new Square(8, E, null)));

    private King kingWhite;
    private King kingBlack;
    private Pawn twoSquareMovedPawn = null;
    /*------------------------------
    #-------------MATRIX------------
    #-------------------------------
    #     |0|1|2|3|4|5|6|7|8|9|10|11
    #     |A|B|C|D|E|F|G|H|I|J|K|L|
    #   ---------------------------
    #   0 | | | | | | | | |X|X|X|X|
    #   ---------------------------
    #   1 | | | | | | | | |X|X|X|X|
    #   ---------------------------
    #   2 | | | | | | | | |X|X|X|X|
    #   ---------------------------
    #   3 | | | | | | | | |X|X|X|X|
    #   ---------------------------
    #   4 | | | | |X|X|X|X| | | | |
    #   ---------------------------
    #   5 | | | | |X|X|X|X| | | | |
    #   ---------------------------
    #   6 | | | | |X|X|X|X| | | | |
    #   ---------------------------
    #   7 | | | | |X|X|X|X| | | | |
    #   ---------------------------
    #   8 |X|X|X|X| | | | | | | | |
    #   ---------------------------
    #   9 |X|X|X|X| | | | | | | | |
    #   ---------------------------
    #   10|X|X|X|X| | | | | | | | |
    #   ---------------------------
    #   11|X|X|X|X| | | | | | | | |
    #   ---------------------------
    */

    public ThreePlayerChessboard() {
        matrix = new Square[COLUMNS][COLUMNS];
        initializeMatrix();
        setInvalidSquares();
    }

    private void initializeMatrix() {
        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < COLUMNS; y++) {
                matrix[x][y] = new Square(x, y, null);
            }
        }
    }

    private void setInvalidSquares() {
        for (int i = 0; i <= 3; i++) {
            for (int j = 8; j <= 11; j++) {
                matrix[i][j].setInvalid(true);
            }
        }

        for (int i = 4; i <= 7; i++) {
            for (int j = 4; j <= 7; j++) {
                matrix[i][j].setInvalid(true);
            }
        }


        for(int i = 8; i <= 11; i++) {
            for(int j = 0; j <= 3; j++) {
                matrix[i][j].setInvalid(true);
            }
        }
    }

    @Override
    public King getKingWhite() {
        return kingWhite;
    }

    @Override
    public void setKingWhite(King kingWhite) {
        this.kingWhite = kingWhite;
    }

    @Override
    public King getKingBlack() {
        return kingBlack;
    }

    @Override
    public void setKingBlack(King kingBlack) {
        this.kingBlack = kingBlack;
    }

    @Override
    public Pawn getTwoSquareMovedPawn() {
        return twoSquareMovedPawn;
    }

    @Override
    public void setTwoSquareMovedPawn(Pawn twoSquareMovedPawn) {
        this.twoSquareMovedPawn = twoSquareMovedPawn;
    }

    @Override
    public boolean validMove(Square square, Piece piece) {
        return (0 <= square.getPosX() && square.getPosX() <= 11 &&
                0 <= square.getPosY() && square.getPosY() <= 11 &&
                !matrix[square.getPosX()][square.getPosY()].isInvalid() &&
                (matrix[square.getPosX()][square.getPosY()].isEmpty() || occupiedByOther(piece, square)));
    }

    public Square getCurrentRosette(Square square) throws Exception {
        int x = square.getPosX() + 1;
        int y = square.getPosY();

        if (1 <= x && x <= 4 && E <= y && y <= H) { return new Square(3, E, null); }
        if (9 <= x && x <= 12 && E <= y && y <= H) { return new Square(8, E, null); }
        if (9 <= x && x <= 12 && I <= y && y <= L) { return new Square(8, I, null); }
        if (5 <= x && x <= 8  && I <= y && y <= L) { return new Square(4, I, null); }
        if (5 <= x && x <= 8  && A <= y && y <= D) { return new Square(4, D, null); }
        if (1 <= x && x <= 4  && A <= y && y <= D) { return new Square(3, D, null); }

        throw new Exception("Invalid square");
    }

    public boolean inSextant(Square square, int x_coord, int y_coord) {
        return ((1 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 4  && E <= square.getPosY() && square.getPosY() <= H) && (1 <= (x_coord + 1) && (x_coord + 1) <= 4  && E <= y_coord && y_coord <= H) ||
            (9 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 12 && E <= square.getPosY() && square.getPosY() <= H) && (9 <= (x_coord + 1) && (x_coord + 1) <= 12 && E <= y_coord && y_coord <= H) ||
            (9 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 12 && I <= square.getPosY() && square.getPosY() <= L) && (9 <= (x_coord + 1) && (x_coord + 1) <= 12 && I <= y_coord && y_coord <= L) ||
            (5 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 8  && I <= square.getPosY() && square.getPosY() <= L) && (5 <= (x_coord + 1) && (x_coord + 1) <= 8  && I <= y_coord && y_coord <= L) ||
            (5 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 8  && A <= square.getPosY() && square.getPosY() <= D) && (5 <= (x_coord + 1) && (x_coord + 1) <= 8  && A <= y_coord && y_coord <= D) ||
            (1 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 4  && A <= square.getPosY() && square.getPosY() <= D) && (1 <= (x_coord + 1) && (x_coord + 1) <= 4  && A <= y_coord && y_coord <= D));
    }

    public Square getLeftQuadrantSquare(Square square) throws Exception {
        if (9 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 12) {
            if (square.getPosY() == I) {
                return new Square(square.getPosX() + 1, E, null);
            } else {
                return new Square(square.getPosX() - 4, square.getPosY() + 1, null);
            }
        }

        if (1 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 4) {
            if (square.getPosY() == E) {
                return new Square(square.getPosX() - 1, I, null);
            } else {
                return new Square(square.getPosX() + 1, square.getPosY() - 1, null);
            }
        }


        if (5 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 8) {
            if (square.getPosY() == D) {
                return new Square(square.getPosX() + 1, I, null);
            } else {
                return new Square(square.getPosX() + 4, square.getPosY() + 1, null);
            }
        }
        throw new Exception("Invalid square");
    }

    public Square getRightQuadrantSquare(Square square) throws Exception {
        if (9 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 12) {
            if (square.getPosY() == E) {
                return new Square(square.getPosX() + 1, I, null);
            } else {
                return new Square(square.getPosX() - 4, square.getPosY() + 1, null);
            }
        }

        if (1 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 4) {
            if (square.getPosY() == D) {
                return new Square(square.getPosX() - 1, E, null);
            } else {
                return new Square(square.getPosX() + 1, square.getPosY() - 1, null);
            }
        }


        if (5 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 8) {
            if (square.getPosY() == I) {
                return new Square(square.getPosX() + 1, D, null);
            } else {
                return new Square(square.getPosX() + 4, square.getPosY() + 1, null);
            }
        }
        throw new Exception("Invalid square");
    }

    public Square getSideRosetteTile(Square square, ArrayList<Square> diagonal) throws Exception {
        if ((E <= square.getPosY()) && (square.getPosY() <= H)) {
            if ((1 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 4)) {
                if (diagonal.contains(new Square(3, F, null))) { return new Square(8, E, null); }
                if (diagonal.contains(new Square(2, E, null))) { return new Square(3, D, null); }
            }
            if ((9 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 12)) {
                if (diagonal.contains(new Square(9, E, null))) { return new Square(8, I, null); }
                if (diagonal.contains(new Square(3, F, null))) { return new Square(3, E, null); }
            }
        }

        if ((A <= square.getPosY()) && (square.getPosY() <= D)) {
            if ((1 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 4)) {
                if (diagonal.contains(new Square(2, D, null))) { return new Square(3, E, null); }
                if (diagonal.contains(new Square(3, C, null))) { return new Square(4, D, null); }
            }
            if ((5 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 8)) {
                if (diagonal.contains(new Square(4, C, null))) { return new Square(3, D, null); }
                if (diagonal.contains(new Square(5, D, null))) { return new Square(4, I, null); }
            }
        }


        if ((I <= square.getPosY()) && (square.getPosY() <= L)) {
            if ((5 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 8)) {
                if (diagonal.contains(new Square(5, I, null))) { return new Square(4, D, null);  }
                if (diagonal.contains(new Square(4, J, null))) { return new Square(8, I, null); }
                }
            if ((9 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 12)) {
                if (diagonal.contains(new Square(8, J, null))) { return new Square(3, D, null);  }
                if (diagonal.contains(new Square(9, I, null))) { return new Square(8, E, null); }
            }
        }
        throw new Exception("Imposible square");
    }

    public void setPiece(Piece piece, int x, int y) {
        matrix[x][y].setPiece(piece);
    }


    public ArrayList<Square> getDiagonalCenterPositions(Square square) throws Exception {
        if (WHITE_ROSETTE.contains(new Square(square.getPosX(), square.getPosY(), null))) {
            return WHITE_ROSETTE;
        }

        if (BLACK_ROSETTE.contains(new Square(square.getPosX(), square.getPosY(), null))) {
            return BLACK_ROSETTE;
        }

        throw new Exception("Invalid square");
    }


    public boolean occupiedByOther(Piece piece, Square square) {
        Square nextMove = matrix[square.getPosX()][square.getPosY()];
        return (!nextMove.isInvalid() && nextMove.getPiece() != null && nextMove.getPiece().getPlayer() != piece.getPlayer());
    }

    public boolean leftCastlingPossible(King king) {
        if (king.isWasMotion()) { return false; }

        Rook rook;

        if (king.getColor() == Player.Colors.WHITE) {
            rook = (Rook) matrix[0][A].getPiece();
        } else if (king.getColor() == Player.Colors.BLACK) {
            rook = (Rook) matrix[7][L].getPiece();
        } else {
            rook = (Rook) matrix[11][H].getPiece();
        }
        if (rook == null) { return false; }
        if (!rook.name.equals("Rook")) { return false; }
        if (rook.isWasMotion()) { return true; }

        if (king.getColor() == Player.Colors.WHITE) {
            for (int y = B; y <= D; y++) {
                if (matrix[0][y].getPiece() != null) { return false; }
            }
        } else if (king.getColor() == Player.Colors.BLACK) {
            for (int y = I; y <= K; y++) {
                if (matrix[7][y].getPiece() != null) { return false; }
            }
        } else {
            for (int y = E; y <= G; y++) {
                if (matrix[11][y].getPiece() != null) { return false; }
            }
        }

        return true;
    }

    public boolean rightCastlingPossible(King king) {
        if (king.isWasMotion()) { return false; }

        Rook rook;

        if (king.getColor() == Player.Colors.WHITE) {
            rook = (Rook) matrix[0][H].getPiece();
        } else if (king.getColor() == Player.Colors.BLACK) {
            rook = (Rook) matrix[7][A].getPiece();
        } else {
            rook = (Rook) matrix[11][L].getPiece();
        }
        if (rook == null) { return false; }
        if (!rook.name.equals("Rook")) { return false; }
        if (rook.isWasMotion()) { return true; }

        if (king.getColor() == Player.Colors.WHITE) {
            for (int y = F; y <= G; y++) {
                if (matrix[0][y].getPiece() != null) { return false; }
            }
        } else if (king.getColor() == Player.Colors.BLACK) {
            for (int y = B; y <= C; y++) {
                if (matrix[7][y].getPiece() != null) { return false; }
            }
        } else {
            for (int y = J; y <= K; y++) {
                if (matrix[11][y].getPiece() != null) { return false; }
            }
        }

        return true;
    }

    // TODO: Remove me
    @Override
    public King myKing(Player.Colors color)
    {
        return null;
    }

    @Override
    public Square getSquare(int xCoordinate, int yCoordinate)
    {
        return getSquares()[xCoordinate][yCoordinate];
    }

    @Override
    public Square[][] getSquares() {
        return matrix;
    }

    @Override
    public void setPieces(String places, Player plWhite, Player plBlack) {}

    @Override
    public Square getSquareConsideringLabels(int x, int y) { return null; }

    @Override
    public void select(Square sq) {}

    @Override
    public void unselect() {}

    @Override
    public int get_widht() { return 1; }

    @Override
    public int get_height() { return 1; }

    @Override
    public int get_widht(boolean includeLables) { return 1; }

    @Override
    public int get_square_height() { return 1; }

    @Override
    public void move(Square begin, Square end) {}

    @Override
    public void move(int xFrom, int yFrom, int xTo, int yTo) {}

    @Override
    public void move(Square begin, Square end, boolean refresh) {}

    @Override
    public void move(Square begin, Square end, boolean refresh, boolean clearForwardHistory) {}

    @Override
    public Point getTopLeftPoint() { return new Point(); }
}