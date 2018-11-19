package com.ovgu.ccd.jchess;

import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.Arrays;

public class ThreePlayerChessboard {
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

    private Square[][] matrix = new Square[COLUMNS][COLUMNS];

    private Square[] WHITE_ROSETTE = new Square[] { new Square(8, I, null), new Square(4, D, null), new Square(3, E, null) };
    private Square[] BLACK_ROSETTE = new Square[] { new Square(4, I, null), new Square(3, D, null), new Square(8, E, null) };


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
        for (int i = 0; i <= 3; i++) {
            for (int j = 8; j <= 11; j++) {
                matrix[i][j].setInvalid(true);
            }
        }

        for (int i = 4; i <= 7; i++) {
            for (int j = 4; j <= 17; j++) {
                matrix[i][j].setInvalid(true);
            }
        }


        for(int i = 8; i <= 11; i++) {
            for(int j = 0; j <= 3; j++) {
                matrix[i][j].setInvalid(true);
            }
        }
    }

    public boolean validMove(Square square) {
        int x = square.getPozX();
        int y = square.getPozY();

        return (0 <= x && x <= 11 && 0 <= y && y <= 11 && matrix[x][y].isEmpty());
    }

    public boolean validSquare(int x, int y) {
        return !!matrix[x][y].isInvalid();
    }

    public Square[] getDiagonalCenterPositions(Square square){
        int x = square.getPozX();
        int y = square.getPozY();

        if (Arrays.asList(WHITE_ROSETTE).contains(new Square(x, y, null))) {
            return  WHITE_ROSETTE.clone();
        } else if (Arrays.asList(BLACK_ROSETTE).contains(new Square(x, y, null))){
            return BLACK_ROSETTE.clone();
        } else {
            return new Square[0];
        }
    }

    public Square getCurrentRosette(Square square) throws Exception {
        int x = square.getPozX() + 1;
        int y = square.getPozY() + 1;

        if (1 <= x && x <= 4  && E <= y && y <= H) { return new Square(3, E, null); }
        if (9 <= x && x <= 12 && E <= y && y <= H) { return new Square(8, E, null); }
        if (9 <= x && x <= 12 && I <= y && y <= L) { return new Square(8, I, null); }
        if (5 <= x && x <= 8  && I <= y && y <= L) { return new Square(4, I, null); }
        if (5 <= x && x <= 8  && A <= y && y <= D) { return new Square(4, D, null); }
        if (1 <= x && x <= 4  && A <= y && y <= D) { return new Square(3, D, null); }

        throw new Exception("Square is not in board");
    }

    public boolean isInSixtant(Square square, int x_coord, int y_coord) {
        return ((1 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 4  && E <= square.getPozY() && square.getPozY() <= H) && (1 <= (x_coord + 1) && (x_coord + 1) <= 4  && E <= y_coord && y_coord <= H) || 
                (9 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 12 && E <= square.getPozY() && square.getPozY() <= H) && (9 <= (x_coord + 1) && (x_coord + 1) <= 12 && E <= y_coord && y_coord <= H) ||
                (9 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 12 && I <= square.getPozY() && square.getPozY() <= L) && (9 <= (x_coord + 1) && (x_coord + 1) <= 12 && I <= y_coord && y_coord <= L) ||
                (5 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 8  && I <= square.getPozY() && square.getPozY() <= L) && (5 <= (x_coord + 1) && (x_coord + 1) <= 8  && I <= y_coord && y_coord <= L) ||
                (5 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 8  && A <= square.getPozY() && square.getPozY() <= D) && (5 <= (x_coord + 1) && (x_coord + 1) <= 8  && A <= y_coord && y_coord <= D) ||
                (1 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 4  && A <= square.getPozY() && square.getPozY() <= D) && (1 <= (x_coord + 1) && (x_coord + 1) <= 4  && A <= y_coord && y_coord <= D));
    }

    public Square getLeftCuadrantTile(Square square) throws Exception {
        if (9 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 12) {
            if (square.getPozY() == I) {
                return new Square(square.getPozX() + 1, E, null);
            } else {
                return new Square(square.getPozX() - 4, square.getPozY() + 1, null);
            }
        }

        if (1 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 4) {
            if (square.getPozY() == E) {
                return new Square(square.getPozX() - 1, I, null);
            } else {
                return new Square(square.getPozX() + 1, square.getPozY() - 1, null);
            }
        }

        if (5 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 8) {
            if (square.getPozY() == D) {
                return new Square(square.getPozX() + 1, I, null);
            } else {
                return new Square(square.getPozX() + 4, square.getPozY() + 1, null);
            }
        }

        throw new Exception("Invalid square");
    }

    public Square getRightCuadrantTile(Square square) throws Exception {
        if (9 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 12) {
            if (square.getPozY() == E) {
                return new Square(square.getPozX() + 1, I, null);
            } else {
                return new Square(square.getPozX() - 4, square.getPozY() + 1, null);
            }
        }

        if (1 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 4) {
            if (square.getPozY() == D) {
                return new Square(square.getPozX() - 1, E, null);
            } else {
                return new Square(square.getPozX() + 1, square.getPozY() - 1, null);
            }
        }

        if (5 <= (square.getPozX() + 1) && (square.getPozX() + 1) <= 8) {
            if (square.getPozY() == I) {
                return new Square(square.getPozX() + 1, D, null);
            } else {
                return new Square(square.getPozX() + 4, square.getPozY() + 1, null);
            }
        }

        throw new Exception("Invalid square");
    }

    public Square getSideRosetteTile(Square square, Square[] diagonal) throws Exception {
        if ((E <= square.getPozY()) && (square.getPozY() <= H)) {
            if ((1 <= square.getPozX() + 1) && (square.getPozX() + 1 <= 4)) {
                if (Arrays.asList(diagonal).contains(new Square(3, F, null))) { return new Square(8, E, null); }
                if (Arrays.asList(diagonal).contains(new Square(2, E, null))) { return new Square(3, D, null); }
            }
            if ((9 <= square.getPozX() + 1) && (square.getPozX() + 1 <= 12)) {
                if (Arrays.asList(diagonal).contains(new Square(9, E, null))) { return new Square(8, I, null); }
                if (Arrays.asList(diagonal).contains(new Square(3, F, null))) { return new Square(3, E, null); }
            }
            throw new Exception("Invalid square");
        }

        if ((A <= square.getPozY()) && (square.getPozY() <= D)) {
            if ((1 <= square.getPozX() + 1) && (square.getPozX() + 1 <= 4)) {
                if (Arrays.asList(diagonal).contains(new Square(2, D, null))) { return new Square(3, E, null); }
                if (Arrays.asList(diagonal).contains(new Square(3, C, null))) { return new Square(4, D, null); }
            }
            if ((5 <= square.getPozX() + 1) && (square.getPozX() + 1 <= 8)) {
                if (Arrays.asList(diagonal).contains(new Square(4, C, null))) { return new Square(3, D, null); }
                if (Arrays.asList(diagonal).contains(new Square(5, D, null))) { return new Square(4, I, null); }
            }
            throw new Exception("Invalid square");
        }


        if ((I <= square.getPozY()) && (square.getPozY() <= L)) {
            if ((5 <= square.getPozX() + 1) && (square.getPozX() + 1 <= 8)) {
                if (Arrays.asList(diagonal).contains(new Square(5, I, null))) { return new Square(4, D, null); }
                if (Arrays.asList(diagonal).contains(new Square(4, J, null))) { return new Square(8, I, null); }
            }
            if ((9 <= square.getPozX() + 1) && (square.getPozX() + 1 <= 12)) {
                if (Arrays.asList(diagonal).contains(new Square(8, J, null))) { return new Square(3, D, null); }
                if (Arrays.asList(diagonal).contains(new Square(9, I, null))) { return new Square(8, E, null); }
            }
            throw new Exception("Invalid square");
        }
        throw new Exception("Invalid square");
    }
}
