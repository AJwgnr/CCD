package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Arrays;

public class DiagonalMoves implements IMove {

    private Piece piece;
    private ThreePlayerChessboard board;

    public DiagonalMoves(Piece piece, IBoard board) {
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
            if (board.validMove(side, piece)) {
                possibleMoves.addAll(oppositeRosetteDiagonalMoves(side));

                // Here it's ok to check both ways, we are adding redundant moves but it's ok.
                Square leftSide = board.getLeftQuadrantSquare(side);
                if (board.validMove(leftSide, piece)) {
                    possibleMoves.addAll(neighborSextantMoves(leftSide));
                }

                Square rightSide = board.getRightQuadrantSquare(side);
                if (board.validMove(rightSide, piece)) {
                    possibleMoves.addAll(neighborSextantMoves(rightSide));
                }
            }
        }


        // check sextants on both sides and ask for their diagonals...
        possibleMoves.addAll(leftSextantMoves());
        possibleMoves.addAll(rightSextantMoves());

        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }

    public ArrayList<Square> leftSextantMoves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square leftSide = null;
        ArrayList<Square> leftSquares = left(piece.getSquare());

        if (!leftSquares.isEmpty() && leftSquares.get(leftSquares.size() - 1) != null) {
            Square leftSquare = leftSquares.get(leftSquares.size() - 1);
            if (leftSextantReachable(leftSquare)) {
                leftSide = board.getLeftQuadrantSquare(leftSquare);
            }
        } else {
            leftSide = board.getLeftQuadrantSquare(piece.getSquare());
        }
        if(leftSide != null && board.validMove(leftSide, piece)) {
            possibleMoves.addAll(neighborSextantMoves(leftSide));
        }

        return possibleMoves;
    }

    public ArrayList<Square> rightSextantMoves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square rightSide = null;
        ArrayList<Square> rightSquares = right(piece.getSquare());

        if (!rightSquares.isEmpty() && (rightSquares.get(rightSquares.size() - 1)) != null) {
            Square rightSquare = rightSquares.get(rightSquares.size() - 1);
            if (rightSextantReachable(rightSquare)) {
                rightSide = board.getRightQuadrantSquare(rightSquare);
            }
        } else {
            rightSide = board.getRightQuadrantSquare(piece.getSquare());
        }
        if(rightSide != null && board.validMove(rightSide, piece)) {
            possibleMoves.addAll(neighborSextantMoves(rightSide));
        }
        return possibleMoves;
    }

    public boolean leftSextantReachable(Square leftMostSquare) {
        return leftMostSquare.getPosY() == ThreePlayerChessboard.E || leftMostSquare.getPosY() == ThreePlayerChessboard.I || leftMostSquare.getPosY() == ThreePlayerChessboard.D ||
         leftMostSquare.getPosX() +1 == 4 || leftMostSquare.getPosX() +1 == 5 || leftMostSquare.getPosX() +1 == 9;
    }

    public boolean rightSextantReachable(Square rightMostSquare) {
        return rightMostSquare.getPosY() == ThreePlayerChessboard.E || rightMostSquare.getPosY() == ThreePlayerChessboard.I || rightMostSquare.getPosY() == ThreePlayerChessboard.D ||
               rightMostSquare.getPosX() +1 == 4 || rightMostSquare.getPosX() +1 == 5 || rightMostSquare.getPosX() +1 == 9;
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
            if (board.validMove(nextMove, piece) && board.inSextant(square, xCoord, yCoord)) {
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
            if (board.validMove(nextMove, piece) && board.inSextant(square, xCoord, yCoord)) {
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
            if (board.validMove(nextMove, piece) && board.inSextant(square, xCoord, yCoord)) {
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
            if (board.validMove(nextMove, piece) && board.inSextant(square, xCoord, yCoord)) {
                possibleMoves.add(nextMove);
            }
        }

        return possibleMoves;
    }

    private ArrayList<Square> oppositeRosetteDiagonalMoves(Square currentRosette) throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        ArrayList<Square> squares = board.getDiagonalCenterPositions(currentRosette);
        for (Square square : squares) {
            if (board.validMove(square, piece)) {
                possibleMoves.add(square);
                possibleMoves.addAll(diagonal(square));
            }
        }

        return possibleMoves;
    }

    private ArrayList<Square> neighborSextantMoves(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        if (board.validMove(square, piece)) {
            possibleMoves.add(square);
            possibleMoves.addAll(diagonal(square));
        }

        return possibleMoves;
    }
}
