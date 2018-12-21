package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.CheckController;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.ArrayList;
import java.util.Arrays;

public class DiagonalMoves implements IMove {

    private Piece piece;
    private ThreePlayerChessboard board;

    private ArrayList<Square> NEXT_TO_ROSETTE =  new ArrayList<Square>(
            Arrays.asList(
                    new Square(3, ThreePlayerChessboard.F, null),
                    new Square(2, ThreePlayerChessboard.E, null),
                    new Square(2, ThreePlayerChessboard.D, null),
                    new Square(3, ThreePlayerChessboard.C, null),
                    new Square(4, ThreePlayerChessboard.C, null),
                    new Square(5, ThreePlayerChessboard.D, null),
                    new Square(5, ThreePlayerChessboard.I, null),
                    new Square(4, ThreePlayerChessboard.J, null),
                    new Square(8, ThreePlayerChessboard.J, null),
                    new Square(9, ThreePlayerChessboard.I, null),
                    new Square(9, ThreePlayerChessboard.E, null),
                    new Square(8, ThreePlayerChessboard.F, null)
            )
    );

    public DiagonalMoves(Piece piece, IBoard board) {
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
            ArrayList<Square> diagonal = diagonal(piece.getSquare());
            if (NEXT_TO_ROSETTE.stream().anyMatch(s -> s.equals(piece.getSquare()))) {
                diagonal.add(new Square(piece.getPosX(), piece.getPosY(), null));
            }
            Square side = board.getSideRosetteTile(piece.getSquare(), diagonal);
            if (side != null && board.validMove(side, piece)) {
                possibleMoves.addAll(oppositeRosetteDiagonalMoves(side));
            }
        }

        possibleMoves.addAll(leftNeighborSextantMoves(piece.getSquare()));
        possibleMoves.addAll(rightNeighborSextantMoves(piece.getSquare()));


        ArrayList<Square> results = new ArrayList<>();
        if (withCheck) {
            for (Square s : possibleMoves) {
                boolean safe = new CheckController(board, board.myKing(piece.getColor()), piece, s).isSafe();
                if (safe) {
                    results.add(new Square(s.getPosX(), s.getPosY(), null));
                }
            }
            possibleMoves = results;
        }

        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }

    public boolean leftSextantReachable(Square leftMostSquare) {
        return leftMostSquare.getPosY() == ThreePlayerChessboard.E || leftMostSquare.getPosY() == ThreePlayerChessboard.I || leftMostSquare.getPosY() == ThreePlayerChessboard.D ||
         leftMostSquare.getPosX() +1 == 4 || leftMostSquare.getPosX() +1 == 5 || leftMostSquare.getPosX() +1 == 9;
    }

    public boolean rightSextantReachable(Square rightMostSquare) {
        return rightMostSquare.getPosY() == ThreePlayerChessboard.E ||
               rightMostSquare.getPosY() == ThreePlayerChessboard.I ||
               rightMostSquare.getPosY() == ThreePlayerChessboard.D ||
               (rightMostSquare.getPosX() +1 == 4 && rightMostSquare.getPosY() != ThreePlayerChessboard.H) ||
               rightMostSquare.getPosX() +1 == 5 || rightMostSquare.getPosX() +1 == 9;
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
                if (board.occupiedByOther(piece, nextMove)) {
                    break;
                }
            }
            if (board.occupiedByMe(piece, nextMove)) { break; }
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
                if (board.occupiedByOther(piece, nextMove)) {
                    break;
                }
            }
            if (board.occupiedByMe(piece, nextMove)) { break; }
        }

        return possibleMoves;
    }

    private ArrayList<Square> diagonal(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        // going outside in the same diagonal, same sextant
        for(int i = 1; i <= 4; i++) {
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
                if (board.occupiedByOther(piece, nextMove)) { break; }
            }
            if (board.occupiedByMe(piece, nextMove)) { break; }
        }

        if (!(board.WHITE_ROSETTE.contains(square) || board.BLACK_ROSETTE.contains(square))) {
            // going inside in the same diagonal, same sextant
            for (int i = 1; i <= 3; i++) {
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
                    if (board.occupiedByOther(piece, nextMove)) { break; }
                }
                if (board.occupiedByMe(piece, nextMove)) { break; }
            }
        }

        return possibleMoves;
    }

    private ArrayList<Square> oppositeRosetteDiagonalMoves(Square currentRosette) throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        if (board.occupiedByOther(piece, currentRosette)) {
            possibleMoves.add(currentRosette);
        } else {
            ArrayList<Square> squares = board.getDiagonalCenterPositions(currentRosette);
            for (Square square : squares) {
                if (board.validMove(square, piece)) {
                    possibleMoves.add(square);
                    if (board.occupiedByOther(piece, square)) {
                        break;
                    }
                    possibleMoves.addAll(diagonal(square));
                }
            }
        }

        return possibleMoves;
    }

    private ArrayList<Square> leftNeighborSextantMoves(Square square) throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        ArrayList<Square> leftSquares = leftToSextant(square);
        Square leftSide = null, leftMostSquare = null;

        if (!leftSquares.isEmpty() && leftSquares.get(leftSquares.size() - 1) != null) {
            leftMostSquare = leftSquares.get(leftSquares.size() - 1);
            if (leftSextantReachable(leftMostSquare)) {
                leftSide = board.getLeftSextantSquare(leftMostSquare);
            }
        } else {
            leftSide = board.getLeftSextantSquare(square);
            leftMostSquare = square;
        }

        if (leftSide != null && board.validMove(leftSide, piece) && (board.getSquare(leftMostSquare.getPosX(), leftMostSquare.getPosY()).isEmpty()) || square.equals(leftMostSquare)) {
            possibleMoves.addAll(horizontalLeft(leftMostSquare));
        }

        return possibleMoves;
    }

    private ArrayList<Square> rightNeighborSextantMoves(Square square) throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square rightSide = null, rightMostSquare = null;
        ArrayList<Square> rightSquares = rightToSextant(square);

        if (!rightSquares.isEmpty() && (rightSquares.get(rightSquares.size() - 1)) != null) {
            rightMostSquare = rightSquares.get(rightSquares.size() - 1);
            if (rightSextantReachable(rightMostSquare)) {
                rightSide = board.getRightSextantSquare(rightMostSquare);
            }
        } else {
            rightSide = board.getRightSextantSquare(square);
            rightMostSquare = square;
        }
        if(rightSide != null && board.validMove(rightSide, piece) && (board.getSquare(rightMostSquare.getPosX(), rightMostSquare.getPosY()).isEmpty() || square.equals(rightMostSquare))) {
            possibleMoves.addAll(horizontalRight(rightMostSquare));
        }
        return possibleMoves;
    }

    public ArrayList<Square> leftToSextant(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        if (1 <= square.getPosX() + 1 && square.getPosX() + 1 <= 4) {
            if (ThreePlayerChessboard.E <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.H) {
                if (1 <= square.getPosX() + 1 && square.getPosX() + 1 <= 2) {
                    possibleMoves.addAll(upLeft(square));
                }
                else {
                    possibleMoves.addAll(left(square));
                }
            } else {
                if (ThreePlayerChessboard.A <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.B) {
                    possibleMoves.addAll(upLeft(square));
                } else {
                    possibleMoves.addAll(left(square));
                }
            }
        }
        if (5 <= square.getPosX() + 1 && square.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.C <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.D) {
                if (7 <= square.getPosX() + 1 && square.getPosX() + 1 <= 8) {
                    possibleMoves.addAll(upLeft(square));
                } else {
                    possibleMoves.addAll(left(square));
                }
            }
            if (ThreePlayerChessboard.I <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.L) {
                if (5 <= square.getPosX() + 1 && square.getPosX() + 1 <= 5) {
                    possibleMoves.addAll(upLeft(square));
                } else {
                    possibleMoves.addAll(left(square));
                }
            }
        }
        if (9 <= square.getPosX() + 1 && square.getPosX() + 1 <= 12) {
            if (square.getPosY() == ThreePlayerChessboard.J || square.getPosY() == ThreePlayerChessboard.K) {
                if (11 <= square.getPosX() + 1 && square.getPosX() + 1 <= 12) {
                    possibleMoves.addAll(upLeft(square));
                } else {
                    possibleMoves.addAll(left(square));
                }
            }
            if (ThreePlayerChessboard.G <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.H) {
                if (9 <= square.getPosX() + 1 && square.getPosX() + 1 <= 10) {
                    possibleMoves.addAll(upLeft(square));
                } else {
                    possibleMoves.addAll(left(square));
                }
            }
            if (ThreePlayerChessboard.E <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.F) {
                if (9 <= square.getPosX() + 1 && square.getPosX() + 1 <= 10) {
                    possibleMoves.addAll(left(square));
                }
            }
        }

        return possibleMoves;
    }

    public ArrayList<Square> rightToSextant(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        if (1 <= square.getPosX() + 1 && square.getPosX() + 1 <= 4) {
            if (ThreePlayerChessboard.G <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.H) {
                if (3 <= square.getPosX() + 1 && square.getPosX() + 1 <= 4) {
                    possibleMoves.addAll(upRight(square));
                }
                else {
                    possibleMoves.addAll(right(square));
                }
            } else {
                if (ThreePlayerChessboard.C <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.D) {
                    possibleMoves.addAll(upRight(square));
                } else {
                    possibleMoves.addAll(right(square));
                }
            }
        }

        if (5 <= square.getPosX() + 1 && square.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.A <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.D) {
                if (5 <= square.getPosX() + 1 && square.getPosX() + 1 <= 6) {
                    possibleMoves.addAll(upRight(square));
                }
                else {
                    possibleMoves.addAll(right(square));
                }
            } else {
                if (ThreePlayerChessboard.I <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.J) {
                    if (7 <= square.getPosX() + 1 && square.getPosX() + 1 <= 8) {
                        possibleMoves.addAll(upRight(square));
                    } else {
                        possibleMoves.addAll(right(square));
                    }
                } else {
                    possibleMoves.addAll(right(square));
                }
            }
        }

        if (9 <= square.getPosX() + 1 && square.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.I <= square.getPosY() && square.getPosY() <= ThreePlayerChessboard.L) {
                if (9 <= square.getPosX() + 1 && square.getPosX() + 1 <= 10 && square.getPosY() >= ThreePlayerChessboard.J) {
                    possibleMoves.addAll(upRight(square));
                }
                else {
                    possibleMoves.addAll(right(square));
                }
            } else {
                if (square.getPosY() != ThreePlayerChessboard.H) {
                    if (square.getPosY() == ThreePlayerChessboard.E && (square.getPosX() + 1 == 10 || square.getPosX() + 1 == 11 || square.getPosX() + 1 == 12) ||
                        square.getPosY() == ThreePlayerChessboard.F && (square.getPosX() + 1 == 11 || square.getPosX() + 1 == 12) ||
                        square.getPosY() == ThreePlayerChessboard.G && (square.getPosX() + 1 == 12)) {
                        possibleMoves.addAll(upRight(square));
                    } else {
                        possibleMoves.addAll(right(square));
                    }
                }
            }
        }
        return possibleMoves;
    }

    private ArrayList<Square> upLeft(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        int xCoord = square.getPosX();
        int yCoord = square.getPosY();

        if (7 <= square.getPosX() + 1 && square.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.C == square.getPosY() || ThreePlayerChessboard.B == square.getPosY()) {
                xCoord -= 1;
                yCoord += 1;
            }
            Square nextMove = new Square(xCoord, yCoord, null);
            if (board.validMove(nextMove, piece) && board.inSextant(square, xCoord, yCoord)) {
                possibleMoves.add(nextMove);
            }
        }
        if (6 <= square.getPosX() + 1 && square.getPosX() + 1 <= 5) {
            if (ThreePlayerChessboard.L == square.getPosY() || ThreePlayerChessboard.K == square.getPosY()) {
                xCoord -= 1;
                yCoord -= 1;
            }
            Square nextMove = new Square(xCoord, yCoord, null);
            if (board.validMove(nextMove, piece) && board.inSextant(square, xCoord, yCoord)) {
                possibleMoves.add(nextMove);
            }
        }
        if (9 <= square.getPosX() + 1 && square.getPosX() + 1 <= 10) {
            if (ThreePlayerChessboard.H == square.getPosY() || ThreePlayerChessboard.G == square.getPosY()) {
                xCoord -= 1;
                yCoord -= 1;
            }
            Square nextMove = new Square(xCoord, yCoord, null);
            if (board.validMove(nextMove, piece) && board.inSextant(square, xCoord, yCoord)) {
                possibleMoves.add(nextMove);
            }
        }
        if (11 <= square.getPosX() + 1 && square.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.J == square.getPosY() || ThreePlayerChessboard.K == square.getPosY()) {
                for(int j = square.getPosY(), i = square.getPosX(); j >= ThreePlayerChessboard.I; j--, i--) {
                    Square nextMove = new Square(i, j, null);
                    if (board.validMove(nextMove, piece) && board.inSextant(square, xCoord, yCoord)) {
                        possibleMoves.add(nextMove);
                    }
                }
            }
        }

        if (1 <= square.getPosX() + 1 && square.getPosX() + 1 <= 4) {
            for (int i = 1; i <= 2; i++) {
                if (ThreePlayerChessboard.A == square.getPosY() || ThreePlayerChessboard.B == square.getPosY()) {
                    xCoord += i;
                    yCoord += i;
                } else {
                    xCoord += i;
                    yCoord -= i;
                }

                Square nextMove = new Square(xCoord, yCoord, null);
                if (board.validMove(nextMove, piece) && board.inSextant(square, xCoord, yCoord)) {
                    possibleMoves.add(nextMove);
                    if (board.occupiedByOther(piece, nextMove)) {
                        break;
                    }
                }
            }
        }
        return possibleMoves;
    }

    private ArrayList<Square> upRight(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        int xCoord = square.getPosX();
        int yCoord = square.getPosY();
        Square nextMove = null;

        if ((ThreePlayerChessboard.H == square.getPosY() || ThreePlayerChessboard.G == square.getPosY()) && square.getPosX() + 1 == 3) {
            xCoord += 1;
            yCoord -= 1;
        }
        if (ThreePlayerChessboard.C == square.getPosY() && square.getPosX() + 1 == 1) {
            xCoord += 1;
            yCoord += 1;
        }
        if (ThreePlayerChessboard.A == square.getPosY() && square.getPosX() + 1 == 6) {
            xCoord -= 1;
            yCoord += 1;
        }
        if (ThreePlayerChessboard.J == square.getPosY() && square.getPosX() + 1 == 8) {
            xCoord -= 1;
            yCoord -= 1;
        }
        if (ThreePlayerChessboard.L == square.getPosY() && square.getPosX() + 1 == 10) {
            xCoord -= 1;
            yCoord -= 1;
        }
        if (ThreePlayerChessboard.F == square.getPosY() && square.getPosX() + 1 == 12) {
            xCoord -= 1;
            yCoord -= 1;

        }
        nextMove = new Square(xCoord, yCoord, null);
        if (board.validMove(nextMove, piece) && board.inSextant(square, xCoord, yCoord)) {
            possibleMoves.add(nextMove);
        }
        return possibleMoves;
    }

    // receives the leftMost square in same sextant
    public ArrayList<Square> horizontalLeft(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        boolean stop = false, up = false, down = false;
        int j = square.getPosY();
        int i = square.getPosX();
        Square nextMove;

        if (1 <= square.getPosX() + 1 && square.getPosX() +1 <= 4) {
            if (square.getPosX() == 3 && square.getPosY() != ThreePlayerChessboard.E) {
                if (square.equals(piece.getSquare())) {
                    if (square.getPosY() <= ThreePlayerChessboard.C) { up = true; }
                    if (square.getPosY() >= ThreePlayerChessboard.B) { down = true; }
                } else {
                    if (square.getPosY() >= ThreePlayerChessboard.B && piece.getPosY() < square.getPosY()) { up = true; }
                    if (square.getPosY() >= ThreePlayerChessboard.B && piece.getPosY() > square.getPosY()) { down = true; }
                }
                if (up) {
                    i = square.getPosX() + 1;
                    for(j = square.getPosY() + 1; i <= 7; j++, i++){
                        int posX = i;
                        int posY = j;
                        if (j == ThreePlayerChessboard.E) { posY = ThreePlayerChessboard.I; }
                        if (j == ThreePlayerChessboard.F) { posY = ThreePlayerChessboard.J; }
                        if (j == ThreePlayerChessboard.G) { posY = ThreePlayerChessboard.K; }
                        nextMove = new Square(posX, posY, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
                if (down) {
                    i = square.getPosX() + 1;
                    for(j = square.getPosY() -1; j >= ThreePlayerChessboard.A && i <= 7; j--, i++){
                        nextMove = new Square(i, j, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
            } else {
                if (square.getPosY() == ThreePlayerChessboard.E) {
                    if (square.equals(piece.getSquare())) {
                        if (square.getPosX() + 1 <= 3) { up = true; }
                        if (square.getPosX() + 1 >= 1) { down = true; }
                    } else {
                        if ((square.getPosX() + 1 == 3 || square.getPosX() + 1 == 2) && piece.getPosX() < square.getPosX()) { up = true; }
                        if (square.getPosX() + 1 >= 1 && piece.getPosX() > square.getPosX()) { down = true; }
                    }
                    if (up) {
                        i = square.getPosX() + 1;
                        for(j = square.getPosY() - 1; j >= ThreePlayerChessboard.A; j--, i++){
                            nextMove = new Square(i, j, null);
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                            if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                                break;
                            }
                        }
                    }
                    if (down) {
                        i = square.getPosX() - 1;
                        for(j = square.getPosY() - 1; i >= 0; j--, i--){
                            nextMove = new Square(i, j, null);
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                            if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (5 <= square.getPosX() + 1 && square.getPosX() +1 <= 8) {
            if (square.getPosY() == ThreePlayerChessboard.D) {
                up = false; down = false;
                if (piece.getSquare().equals(square)) {
                    if (square.getPosX() + 1 == 8 || square.getPosX() + 1 == 7 || square.getPosX() + 1 == 6) { up = true; }
                    if (square.getPosX() + 1 == 7 || square.getPosX() + 1 == 6 || square.getPosX() + 1 == 5) { down = true; }

                } else { // piece != square
                    if (piece.getPosX() > square.getPosX() && square.getPosX() + 1 >= 6) { up = true; }
                    if (piece.getPosX() < square.getPosX()) { down = true; }
                    if (square.getPosX() + 1 == 8) { up = false; down = false; }
                }
                if (up) {
                    i = square.getPosX() - 1;
                    for(j = ThreePlayerChessboard.I; j <= ThreePlayerChessboard.L; j++, i--){
                        int posX = i;
                        int posY = j;
                        if (i == 3) { posX = 8; }
                        if (i == 2) { posX = 9; }
                        if (i == 1) { posX = 10; }
                        nextMove = new Square(posX, posY, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
                if (down) {
                    i = square.getPosX() + 1;
                    for(j = ThreePlayerChessboard.I; j <= ThreePlayerChessboard.L && i <= 7; j++, i++){
                        nextMove = new Square(i, j, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
            }
            if (square.getPosX() + 1 == 5 && square.getPosY() != ThreePlayerChessboard.D) {
                 up = false; down = false;
                if (piece.getSquare().equals(square)) {
                    if (square.getPosY() == ThreePlayerChessboard.L || square.getPosY() == ThreePlayerChessboard.K || square.getPosY() == ThreePlayerChessboard.J) { up = true; }
                    if (square.getPosY() == ThreePlayerChessboard.J || square.getPosY() == ThreePlayerChessboard.K || square.getPosY() == ThreePlayerChessboard.I) { down = true; }
                } else { // piece != square
                    if (piece.getPosY() > square.getPosY() && square.getPosY() >= ThreePlayerChessboard.J) { up = true; }
                    if (piece.getPosY() < square.getPosY()) { down = true; }
                    if (square.getPosX() + 1 == 8) { up = false; down = false; }
                }
                if (up) {
                    i = 8;
                    for(j = square.getPosY() - 1; i <= 11; j--, i++){
                        int posX = i;
                        int posY = j;
                        if (j == ThreePlayerChessboard.H && (i == 11 || i == 10 || i == 9)) { posY = ThreePlayerChessboard.E; }
                        if (j == ThreePlayerChessboard.G && (i == 11 || i == 10)) { posY = ThreePlayerChessboard.F; }
                        if (j == ThreePlayerChessboard.F) { posY = ThreePlayerChessboard.G; }
                        nextMove = new Square(posX, posY, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
                if (down) {
                    i = 8;
                    for(j = square.getPosY() + 1; j <= ThreePlayerChessboard.L; j++, i++){
                        nextMove = new Square(i, j, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
            }
        }
        if (9 <= square.getPosX() + 1 && square.getPosX() +1 <= 12) {
            if (square.getPosY() == ThreePlayerChessboard.I) {
                up = false; down = false;
                if (piece.getSquare().equals(square)) {
                    if (square.getPosX() + 1 == 11 || square.getPosX() + 1 == 12 || square.getPosX() + 1 == 10) {
                        up = true;
                    }
                    if (square.getPosX() + 1 == 11 || square.getPosX() + 1 == 10 || square.getPosX() + 1 == 9) {
                        down = true;
                    }

                } else { // piece != square
                    if (piece.getPosX() > square.getPosX() && square.getPosX() + 1 >= 10) {
                        up = true;
                    }
                    if (piece.getPosX() < square.getPosX()) {
                        down = true;
                    }
                    if (square.getPosX() + 1 == 9) {
                        up = false;
                        down = false;
                    }
                }
                if (up) {
                    i = square.getPosX() - 1;
                    for (j = ThreePlayerChessboard.E; j <= ThreePlayerChessboard.H; j++, i--) {
                        int posX = i;
                        int posY = j;
                        if (i == 7) { posX = 3; }
                        if (i == 6) { posX = 2; }
                        if (i == 5) { posX = 1; }
                        if (i == 4) { posX = 0; }
                        nextMove = new Square(posX, posY, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
                if (down) {
                    i = square.getPosX() + 1;
                    for (j = ThreePlayerChessboard.E; i <= 11; j++, i++) {
                        nextMove = new Square(i, j, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
            } else {
                if (square.getPosX() + 1 == 9) {
                    up = false; down = false;
                    if (piece.getSquare().equals(square)) {
                        if (square.getPosY() == ThreePlayerChessboard.G || square.getPosY() == ThreePlayerChessboard.H | square.getPosY() == ThreePlayerChessboard.F) { up = true; }
                        if (square.getPosY() == ThreePlayerChessboard.F || square.getPosY() == ThreePlayerChessboard.G || square.getPosY() == ThreePlayerChessboard.E) { down = true; }

                    } else { // piece != square
                        if (piece.getPosY() > square.getPosY() && square.getPosY() >= ThreePlayerChessboard.F ) { up = true; }
                        if (piece.getPosY() < square.getPosY() && square.getPosY() < ThreePlayerChessboard.H) { down = true; }
                    }
                    if (up) {
                        i = 3;
                        for (j = square.getPosY() - 1; i >= 0; j--, i--) {
                            nextMove = new Square(i, j, null);
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                            if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                                break;
                            }
                        }
                    }
                    if (down) {
                        i = 3;
                        for (j = square.getPosY() + 1; j <= ThreePlayerChessboard.H; j++, i--) {
                            nextMove = new Square(i, j, null);
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                            if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return possibleMoves;
    }

    // receives the rightMost square in same sextant
    public ArrayList<Square> horizontalRight(Square square) {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square nextMove;
        boolean up = false, down = false;
        int i,j;

        if (1 <= square.getPosX() + 1 && square.getPosX() +1 <= 4) {
            if (square.getPosY() == ThreePlayerChessboard.D) {
                up = false;
                down = false;
                if (piece.getSquare().equals(square)) {
                    if (square.getPosX() + 1 == 1 || square.getPosX() + 1 == 2 || square.getPosX() + 1 == 3) {
                        up = true;
                    }
                    down = true;
                } else {
                    if (piece.getPosX() < square.getPosX() && (square.getPosX() + 1 == 2|| square.getPosX() + 1 == 3)) {
                        up = true;
                    }
                    if (piece.getPosX() > square.getPosX() && square.getPosX() + 1 >= 2 && square.getPosX() + 1 <= 4) {
                        down = true;
                    }
                }
                if (up) {
                    i = square.getPosX() + 1;
                    for (j = square.getPosY() + 1; j <= ThreePlayerChessboard.H; j++, i++) {
                        int posX = i, posY = j;
                        if (i == 4) { posX = 8; }
                        if (i == 5) { posX = 9; }
                        if (i == 6) { posX = 10; }
                        nextMove = new Square(posX, posY, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
                if (down) {
                    i = square.getPosX() - 1;
                    for (j = square.getPosY() + 1; i >= 0; j++, i--) {
                        nextMove = new Square(i, j, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
            } else {
                if (square.getPosX() + 1 == 4) {
                    up = false;
                    down = false;
                    if (piece.getSquare().equals(square)) {
                        if (square.getPosY() == ThreePlayerChessboard.H || square.getPosY() == ThreePlayerChessboard.G || square.getPosY() == ThreePlayerChessboard.F) {
                            up = true;
                        }
                        if (square.getPosY() == ThreePlayerChessboard.F || square.getPosY() == ThreePlayerChessboard.G || square.getPosY() == ThreePlayerChessboard.E) {
                            down = true;
                        }
                    } else {
                        if (piece.getPosY() > square.getPosY() && (square.getPosY() == ThreePlayerChessboard.G || square.getPosY() == ThreePlayerChessboard.F)) {
                            up = true;
                        }
                        if ((square.getPosY() == ThreePlayerChessboard.F || square.getPosY() == ThreePlayerChessboard.G|| square.getPosY() == ThreePlayerChessboard.E) && square.getPosY() >= piece.getPosY()) {
                            down = true;
                        }
                    }
                    if (up) {
                        i = 8;
                        for (j = square.getPosY() - 1; i <= 11; j--, i++) {
                            int posX = i, posY = j;
                            if (j == ThreePlayerChessboard.D) { posY = ThreePlayerChessboard.I; }
                            if (j == ThreePlayerChessboard.C) { posY = ThreePlayerChessboard.J; }
                            if (j == ThreePlayerChessboard.B) { posY = ThreePlayerChessboard.K; }
                            nextMove = new Square(posX, posY, null);
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                            if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                                break;
                            }
                        }
                    }
                    if (down) {
                        i = 8;
                        for (j = square.getPosY() + 1; j <= ThreePlayerChessboard.H; j++, i++) {
                            nextMove = new Square(i, j, null);
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                            if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (9 <= square.getPosX() + 1 && square.getPosX() +1 <= 12) {
            if (square.getPosY() == ThreePlayerChessboard.E) {
                up = false;
                down = false;
                if (piece.getSquare().equals(square)) {
                    if (square.getPosX() + 1== 11|| square.getPosX() + 1== 12 || square.getPosX() + 1== 10) {
                        up = true;
                    }
                    if (square.getPosX() + 1== 11|| square.getPosX() + 1== 10 || square.getPosX() + 1== 9) {
                        down = true;
                    }
                } else {
                    if (piece.getPosX() > square.getPosX() && (square.getPosX() + 1== 11 || square.getPosX() + 1== 10)) {
                        up = true;
                    }
                    if ((square.getPosX() == 9 || square.getPosX() == 10) && piece.getPosX() < square.getPosX()) {
                        down = true;
                    }
                }
                if (up) {
                    j = ThreePlayerChessboard.I;
                    for (i = square.getPosX() -1; j <= ThreePlayerChessboard.L; j++, i--) {
                        int posX = i, posY = j;
                        if (i == 7) { posX = 4; }
                        if (i == 6) { posX = 5; }
                        if (i == 5) { posX = 6; }
                        nextMove = new Square(posX, posY, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
                if (down) {
                    j = ThreePlayerChessboard.I;
                    for (i = square.getPosX() + 1; i <= 11; j++, i++) {
                        nextMove = new Square(i, j, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
            } else {
                if (square.getPosX() + 1 == 9) {
                    up = false; down = false;
                    if (piece.getSquare().equals(square)) {
                        if (square.getPosY() == ThreePlayerChessboard.L || square.getPosY() == ThreePlayerChessboard.K || square.getPosY() == ThreePlayerChessboard.J) { up = true; }
                        if (square.getPosY() == ThreePlayerChessboard.J || square.getPosY() == ThreePlayerChessboard.K || square.getPosY() == ThreePlayerChessboard.I) { down = true; }
                    } else {
                        if (piece.getPosY() > square.getPosY() && square.getPosY() == ThreePlayerChessboard.K) { up = true; }
                        if (piece.getPosY() < square.getPosY() && (square.getPosY() == ThreePlayerChessboard.K || square.getPosY() == ThreePlayerChessboard.J)) { down = true; }
                    }
                    if (up) {
                        i = 4;
                        for (j = square.getPosY() - 1; i <= 7; j--, i++) {
                            int posX = i, posY = j;
                            if (j == ThreePlayerChessboard.H) { posY = ThreePlayerChessboard.D; }
                            if (j == ThreePlayerChessboard.G) { posY = ThreePlayerChessboard.C; }
                            if (j == ThreePlayerChessboard.F) { posY = ThreePlayerChessboard.B; }
                            nextMove = new Square(posX, posY, null);
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                            if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                                break;
                            }
                        }
                    }
                    if (down) {
                        i = 4;
                        for (j = square.getPosY() + 1; j <= ThreePlayerChessboard.L; j++, i++) {
                            nextMove = new Square(i, j, null);
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                            if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (5 <= square.getPosX() + 1 && square.getPosX() +1 <= 8) {
            if (square.getPosY() == ThreePlayerChessboard.I) {
                up = false; down = false;
                if (piece.getSquare().equals(square)) {
                    if (square.getPosX() + 1 == 7 || square.getPosX() + 1 == 8|| square.getPosX() + 1 == 6) { up = true; }
                    if (square.getPosX() + 1 == 6 || square.getPosX() + 1 == 7 || square.getPosX() + 1 == 5) { down = true; }
                } else {
                    if (piece.getPosX() > square.getPosX() && square.getPosX() + 1 >= 6) { up = true; }
                    if ((square.getPosX() + 1 == 7 || square.getPosX() + 1 == 6) && piece.getPosX() < square.getPosX()) { down = true; }
                }
                if (up) {
                    j = ThreePlayerChessboard.D;
                    for (i = square.getPosX() - 1; j >= ThreePlayerChessboard.A; j--, i--) {
                        nextMove = new Square(i, j, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
                if (down) {
                    j = ThreePlayerChessboard.D;
                    for (i = square.getPosX() + 1; i <= 7; j--, i++) {
                        nextMove = new Square(i, j, null);
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                        if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                            !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                            break;
                        }
                    }
                }
            } else {
                if (square.getPosX() +1 == 5) {
                    up = false; down = false;
                    if (piece.getSquare().equals(square)) {
                        if (square.getPosY() == ThreePlayerChessboard.A || square.getPosY() == ThreePlayerChessboard.B || square.getPosY() == ThreePlayerChessboard.C) { up = true; }
                        if (square.getPosY() == ThreePlayerChessboard.B || square.getPosY() == ThreePlayerChessboard.C || square.getPosY() == ThreePlayerChessboard.D) { down = true; }
                    } else {
                        if (piece.getPosY() < square.getPosY() && (square.getPosY() == ThreePlayerChessboard.B ||square.getPosY() == ThreePlayerChessboard.C)) { up = true; }
                        if (piece.getPosY() > square.getPosY() && (square.getPosY() == ThreePlayerChessboard.B || square.getPosY() == ThreePlayerChessboard.C)) { down = true; }
                    }
                    if (up) {
                        i = 3;
                        for (j = square.getPosY() + 1; i >= 0; j++, i--) {
                            nextMove = new Square(i, j, null);
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                            if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                                break;
                            }
                        }
                    }
                    if (down) {
                        i = 3;
                        for (j = square.getPosY() - 1; j >= ThreePlayerChessboard.A; j--, i--) {
                            nextMove = new Square(i, j, null);
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                            if (!board.inSextant(square, nextMove.getPosX(), nextMove.getPosY()) &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isInvalid() &&
                                !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() && !board.getSquare(nextMove.getPosX(), nextMove.getPosY()).equals(piece.getSquare())) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return possibleMoves;
    }
}