package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.jchess.ThreePlayerChessboard;
import com.ovgu.ccd.moves.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class DiagonalMove implements IMove {

    private Piece piece;
    private ThreePlayerChessboard board;

    DiagonalMove(Piece piece, IBoard board) {
        this.piece = piece;
        this.board = (ThreePlayerChessboard) board;
    }

    @Override
    public ArrayList<Square> moves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        if (!getSameSextantMoves(piece.getSquare()).isEmpty()) {
            possibleMoves.addAll(getSameSextantMoves(piece.getSquare()));
        }
        Square currentRosette = board.getCurrentRosette(piece.getSquare());

        // check if rousette is amongst my moves
        if (possibleMoves.contains(currentRosette) || piece.getSquare().equals(currentRosette)) {
            possibleMoves.addAll(oppositeRosetteDiagonalMoves(currentRosette));
        } else {
            // We don't reach the rosette, get reachable rosette, its neighbors and the diagonal moves
            //also, for the reachable rosette, get left or right neighbors
            Square side = board.getSideRosetteTile(piece.getSquare(), diagonal(piece.getSquare()));
            possibleMoves.addAll(oppositeRosetteDiagonalMoves(side));

            // Here it's ok to check both ways, we are adding redundant moves but it's ok.
            Square leftSide = board.getLeftQuadrantSquare(side);
            if (board.validMove(leftSide)) {
                possibleMoves.addAll(neighborSextantMoves(leftSide));
            }

            Square rightSide = board.getRightQuadrantSquare(side);
            if (board.validMove(rightSide)){
                possibleMoves.addAll(neighborSextantMoves(rightSide));
            }
        }


        // check sextants on both sides and ask for their diagonals...
        Square leftSide = null;
        ArrayList<Square> leftSquares = left(piece.getSquare());
        if (!leftSquares.isEmpty() && leftSquares.get(leftSquares.size() - 1) != null) {
            leftSide = board.getLeftQuadrantSquare(leftSquares.get(leftSquares.size() - 1));
        } else {
            leftSide = board.getLeftQuadrantSquare(piece.getSquare());
        }
        possibleMoves.addAll(neighborSextantMoves(leftSide));

        Square rightSide = null;
        ArrayList<Square> rightSquares = right(piece.getSquare());
        if (!rightSquares.isEmpty() && (rightSquares.get(rightSquares.size() - 1)) != null) {
            rightSide = board.getRightQuadrantSquare(rightSquares.get(rightSquares.size() - 1));
        } else {
            rightSide = board.getRightQuadrantSquare(piece.getSquare());
        }
        possibleMoves.addAll(neighborSextantMoves(rightSide));

        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }

    public boolean perfectDiagonal() {
        // white diagonals first, black diagonals second
        return (
                ((piece.getPosX() + 1) + (piece.getPosY() + 1) == 9) ||
                (piece.getPosX() == piece.getPosY()) ||
                (Math.abs((piece.getPosX() + 1) - (piece.getPosY() + 1)) == 4)
        );
    }

    public ArrayList<Square> getSameSextantMoves(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        possibleMoves.addAll(left(square));
        possibleMoves.addAll(right(square));
        possibleMoves.addAll(diagonal(square));

        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }

    private ArrayList<Square> left(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        for(int i = 1; i <= 3; i++) {
            int xCoord = square.getPosX();
            int yCoord = square.getPosY();

            if (((1 <= (square.getPosX() + 1) && (square.getPosX() + 1 <= 4)) && (ThreePlayerChessboard.E <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.H)) ||
                ((5 <= (square.getPosX() + 1) && (square.getPosX() + 1 <= 8)) && (ThreePlayerChessboard.I <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.L)) ||
                ((9 <= (square.getPosX() + 1) && (square.getPosX() + 1 <= 12)) && (ThreePlayerChessboard.E <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.H))) {
                xCoord -= i;
            } else {
                xCoord += i;
            }


            if ((1 <= (square.getPosX() + 1) && (square.getPosX() + 1 <= 4)) ||
                ((9 <= (square.getPosX() + 1) && (square.getPosX() + 1 <= 12)) && (ThreePlayerChessboard.I <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.L))) {
                yCoord -= i;
            } else {
                yCoord += i;
            }

            Square nextMove = new Square(xCoord, yCoord, null);
            if (board.validMove(nextMove) && board.inSextant(square, xCoord, yCoord)) {
                possibleMoves.add(nextMove);
            }
        }

        return possibleMoves;
    }

    private ArrayList<Square> right(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        for(int i = 1; i <= 3; i++) {
            int xCoord = square.getPosX();
            int yCoord = square.getPosY();

            if ((ThreePlayerChessboard.A <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.D) || ((9 <= (square.getPosX() + 1) && (square.getPosX() + 1 <= 12)) && (ThreePlayerChessboard.I <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.L))) {
                xCoord -= i;
            } else {
                xCoord += i;
            }

            if (((5 <= (square.getPosX() + 1) && (square.getPosX() + 1 <= 8)) || ((9 <= (square.getPosX() + 1) && (square.getPosX() + 1 <= 12)) && (ThreePlayerChessboard.E <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.H)))) {
                yCoord -= i;
            } else {
                yCoord += i;
            }
            
            Square nextMove = new Square(xCoord, yCoord, null);
            if (board.validMove(nextMove) && board.inSextant(square, xCoord, yCoord)) {
                possibleMoves.add(nextMove);
            }
        }

        return possibleMoves;
    }

    private ArrayList<Square> diagonal(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        // going outside in the same diagonal, same sextant
        for(int i = 1; i <= 3; i++) {
            int xCoord = square.getPosX();
            int yCoord = square.getPosY();

            if ((square.getPosX() + 1) >= 5) {
                xCoord += i;
            } else {
                xCoord -= i;
            }

            if ((ThreePlayerChessboard.A <= square.getPosY()) && (square.getPosY() <= ThreePlayerChessboard.D)) {
                yCoord -= i;
            } else {
                yCoord += i;
            }

            Square nextMove = new Square(xCoord, yCoord, null);
            if (board.validMove(nextMove) && board.inSextant(square, xCoord, yCoord)) {
                possibleMoves.add(nextMove);
            }
        }

        // going inside in the same diagonal, same sextant
        for(int i = 1; i <= 3; i++) {
            int xCoord = square.getPosX();
            int yCoord = square.getPosY();

            if ((1 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 4)) {
                xCoord += i;
            } else {
                xCoord -= i;
            }

            if ((ThreePlayerChessboard.A <= square.getPosY()) && (square.getPosY() <= ThreePlayerChessboard.D)) {
                yCoord += i;
            } else {
                yCoord -= i;
            }
            
            Square nextMove = new Square(xCoord, yCoord, null);
            if (board.validMove(nextMove) && board.inSextant(square, xCoord, yCoord)) {
                possibleMoves.add(nextMove);
            }
        }

        return possibleMoves;
    }

    private ArrayList<Square> oppositeRosetteDiagonalMoves(Square currentRosette) throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        ArrayList<Square> squares = board.getDiagonalCenterPositions(currentRosette);
        for (Square square : squares) {
            if (board.validMove(square)) {
                possibleMoves.add(square);
                possibleMoves.addAll(diagonal(square));
            }
        }

        return possibleMoves;
    }

    private ArrayList<Square> neighborSextantMoves(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        if (board.validMove(square)) {
            possibleMoves.add(square);
            possibleMoves.addAll(diagonal(square));
        }

        return possibleMoves;
    }
}
