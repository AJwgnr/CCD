package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.CheckController;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that generates straight moves
 */
public class StraightMoves implements IMove {

    /**
     * moves for which straight moves are calculated
     */
    final private Piece piece;
    /**
     * board in which moves are calculated
     */
    final private ThreePlayerChessboard board;

    /**
     * @param piece for which moves are calculated
     * @param board in which moves are calculated
     */
    public StraightMoves(final Piece piece, final IBoard board) {
        this.piece = piece;
        this.board = (ThreePlayerChessboard) board;
    }

    @Override
    public ArrayList<Square> moves() {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();

        try {
            possibleMoves.addAll(allMoves(true));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return possibleMoves;
    }

    /**
     * @param withCheck true if we want to only return the moves
     *                 that don't generate a "check"
     * @return a list of possible moves
     * @throws Exception in case a move is invalid
     */
    public ArrayList<Square> allMoves(final boolean withCheck) throws Exception {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();
        final Square currentSquare = piece.getSquare();
        Square nextMove = null;

        if (1 <= currentSquare.getPosX() + 1 && currentSquare.getPosX() + 1 <= 4) {
            for (int i = currentSquare.getPosX(); i <= 11; i++) {
                nextMove = new Square(i, currentSquare.getPosY(), null);
                if (!nextMove.equals(currentSquare)) {
                    if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                            && !board.occupiedByOther(piece, nextMove)) {
                        break;
                    }
                    if (board.validMove(nextMove, piece)) {
                        possibleMoves.add(nextMove);
                        if (board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                    }
                }
            }
            for (int i = currentSquare.getPosX(); i >= 0; i--) {
                nextMove = new Square(i, currentSquare.getPosY(), null);
                if (!nextMove.equals(currentSquare)) {
                    if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                            && !board.occupiedByOther(piece, nextMove)) {
                        break;
                    }
                    if (board.validMove(nextMove, piece)) {
                        possibleMoves.add(nextMove);
                        if (board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                    }
                }
            }
        }

        if (5 <= currentSquare.getPosX() + 1 && currentSquare.getPosX() + 1 <= 8) {
            for (int i = currentSquare.getPosX(); i <= 7; i++) {
                nextMove = new Square(i, currentSquare.getPosY(), null);
                if (!nextMove.equals(currentSquare)) {
                    if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                            && !board.occupiedByOther(piece, nextMove)) {
                        break;
                    }
                    if (board.validMove(nextMove, piece)) {
                        possibleMoves.add(nextMove);
                        if (board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                    }
                }
            }
            for (int i = currentSquare.getPosX(); i >= 4; i--) {
                nextMove = new Square(i, currentSquare.getPosY(), null);
                if (!nextMove.equals(currentSquare)) {
                    if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                            && !board.occupiedByOther(piece, nextMove)) {
                        break;
                    }
                    if (board.validMove(nextMove, piece)) {
                        possibleMoves.add(nextMove);
                        if (board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                    }
                }
            }
            if (nextMove != null && nextMove.getPosX() == 4 && board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() || currentSquare.getPosX() == 4) {
                if (ThreePlayerChessboard.I <= currentSquare.getPosY() && currentSquare.getPosY() <= ThreePlayerChessboard.L) {
                    for (int i = 8; i <= 11; i++) {
                        nextMove = new Square(i, currentSquare.getPosY(), null);
                        if (!nextMove.equals(currentSquare)) {
                            if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                    && !board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 3; i >= 0; i--) {
                        nextMove = new Square(i, currentSquare.getPosY(), null);
                        if (!nextMove.equals(currentSquare)) {
                            if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                    && !board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (9 <= currentSquare.getPosX() + 1 && currentSquare.getPosX() + 1 <= 12) {
            for (int i = currentSquare.getPosX(); i <= 11; i++) {
                nextMove = new Square(i, currentSquare.getPosY(), null);
                if (!nextMove.equals(currentSquare)) {
                    if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                            && !board.occupiedByOther(piece, nextMove)) {
                        break;
                    }
                    if (board.validMove(nextMove, piece)) {
                        possibleMoves.add(nextMove);
                        if (board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                    }
                }
            }
            for (int i = currentSquare.getPosX(); i >= 8; i--) {
                nextMove = new Square(i, currentSquare.getPosY(), null);
                if (!nextMove.equals(currentSquare)) {
                    if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                            && !board.occupiedByOther(piece, nextMove)) {
                        break;
                    }
                    if (board.validMove(nextMove, piece)) {
                        possibleMoves.add(nextMove);
                        if (board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                    }
                }
            }
            if (nextMove != null && nextMove.getPosX() == 8 && board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() || currentSquare.getPosX() == 8) {
                if (ThreePlayerChessboard.E <= currentSquare.getPosY() && currentSquare.getPosY() <= ThreePlayerChessboard.H) {
                    for (int i = 3; i >= 0; i--) {
                        nextMove = new Square(i, currentSquare.getPosY(), null);
                        if (!nextMove.equals(currentSquare)) {
                            if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                    && !board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 4; i <= 7; i++) {
                        nextMove = new Square(i, currentSquare.getPosY(), null);
                        if (!nextMove.equals(currentSquare)) {
                            if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                    && !board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (1 <= currentSquare.getPosX() + 1 && currentSquare.getPosX() + 1 <= 4) {
            for (int i = currentSquare.getPosY(); i >= ThreePlayerChessboard.A; i--) {
                nextMove = new Square(currentSquare.getPosX(), i, null);
                if (!nextMove.equals(currentSquare)) {
                    if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                            && !board.occupiedByOther(piece, nextMove)) {
                        break;
                    }
                    if (board.validMove(nextMove, piece)) {
                        possibleMoves.add(nextMove);
                        if (board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                    }
                }
            }

            for (int i = currentSquare.getPosY(); i <= ThreePlayerChessboard.L; i++) {
                nextMove = new Square(currentSquare.getPosX(), i, null);
                if (!nextMove.equals(currentSquare)) {
                    if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                            && !board.occupiedByOther(piece, nextMove)) {
                        break;
                    }
                    if (board.validMove(nextMove, piece)) {
                        possibleMoves.add(nextMove);
                        if (board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                    }
                }
            }
        }

        if (9 <= currentSquare.getPosX() + 1 && currentSquare.getPosX() + 1 <= 12) {
            if (ThreePlayerChessboard.E <= currentSquare.getPosY() && currentSquare.getPosY() <= ThreePlayerChessboard.H) {
                for (int i = currentSquare.getPosY(); i <= ThreePlayerChessboard.H; i++) {
                    nextMove = new Square(currentSquare.getPosX(), i, null);
                    if (!nextMove.equals(currentSquare)) {
                        if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                && !board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                    }
                }
                for (int i = currentSquare.getPosY(); i >= ThreePlayerChessboard.E; i--) {
                    nextMove = new Square(currentSquare.getPosX(), i, null);
                    if (!nextMove.equals(currentSquare)) {
                        if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                && !board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                    }
                }
                if (nextMove != null && nextMove.getPosY() == ThreePlayerChessboard.E && board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() || currentSquare.getPosY() == ThreePlayerChessboard.E) {
                    for (int i = ThreePlayerChessboard.I; i <= ThreePlayerChessboard.L; i++) {
                        nextMove = new Square(currentSquare.getPosX(), i, null);
                        if (!nextMove.equals(currentSquare)) {
                            if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                    && !board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (ThreePlayerChessboard.I <= currentSquare.getPosY() && currentSquare.getPosY() <= ThreePlayerChessboard.L) {
                for (int i = currentSquare.getPosY(); i <= ThreePlayerChessboard.L; i++) {
                    nextMove = new Square(currentSquare.getPosX(), i, null);
                    if (!nextMove.equals(currentSquare)) {
                        if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                && !board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                    }
                }
                for (int i = currentSquare.getPosY(); i >= ThreePlayerChessboard.I; i--) {
                    nextMove = new Square(currentSquare.getPosX(), i, null);
                    if (!nextMove.equals(currentSquare)) {
                        if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                && !board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                    }
                }
                if (nextMove != null && nextMove.getPosY() == ThreePlayerChessboard.I &&
                        board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty() ||
                        currentSquare.getPosY() == ThreePlayerChessboard.I) {
                    for (int i = ThreePlayerChessboard.E; i <= ThreePlayerChessboard.H; i++) {
                        nextMove = new Square(currentSquare.getPosX(), i, null);
                        if (!nextMove.equals(currentSquare)) {
                            if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                    && !board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (5 <= currentSquare.getPosX() + 1 && currentSquare.getPosX() + 1 <= 8) {
            if (ThreePlayerChessboard.A <= currentSquare.getPosY() && currentSquare.getPosY() <= ThreePlayerChessboard.D) {
                for (int i = currentSquare.getPosY(); i >= ThreePlayerChessboard.A; i--) {
                    nextMove = new Square(currentSquare.getPosX(), i, null);
                    if (!nextMove.equals(currentSquare)) {
                        if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                && !board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                    }
                }
                for (int i = currentSquare.getPosY(); i <= ThreePlayerChessboard.D; i++) {
                    nextMove = new Square(currentSquare.getPosX(), i, null);
                    if (!nextMove.equals(currentSquare)) {
                        if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                && !board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                    }
                }
                if (nextMove != null && nextMove.getPosY() == ThreePlayerChessboard.D &&
                        board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                        || currentSquare.getPosY() == ThreePlayerChessboard.D) {
                    for (int i = ThreePlayerChessboard.I; i <= ThreePlayerChessboard.L; i++) {
                        nextMove = new Square(currentSquare.getPosX(), i, null);
                        if (!nextMove.equals(currentSquare)) {
                            if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                    && !board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (ThreePlayerChessboard.I <= currentSquare.getPosY() && currentSquare.getPosY() <= ThreePlayerChessboard.L) {
                for (int i = currentSquare.getPosY(); i <= ThreePlayerChessboard.L; i++) {
                    nextMove = new Square(currentSquare.getPosX(), i, null);
                    if (!nextMove.equals(currentSquare)) {
                        if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                && !board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                    }
                }
                for (int i = currentSquare.getPosY(); i >= ThreePlayerChessboard.I; i--) {
                    nextMove = new Square(currentSquare.getPosX(), i, null);
                    if (!nextMove.equals(currentSquare)) {
                        if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                && !board.occupiedByOther(piece, nextMove)) {
                            break;
                        }
                        if (board.validMove(nextMove, piece)) {
                            possibleMoves.add(nextMove);
                            if (board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                        }
                    }
                }
                if (nextMove != null && nextMove.getPosY() == ThreePlayerChessboard.I &&
                        board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                        || currentSquare.getPosY() == ThreePlayerChessboard.I) {
                    for (int i = ThreePlayerChessboard.D; i >= ThreePlayerChessboard.A; i--) {
                        nextMove = new Square(currentSquare.getPosX(), i, null);
                        if (!nextMove.equals(currentSquare)) {
                            if (!board.getSquare(nextMove.getPosX(), nextMove.getPosY()).isEmpty()
                                    && !board.occupiedByOther(piece, nextMove)) {
                                break;
                            }
                            if (board.validMove(nextMove, piece)) {
                                possibleMoves.add(nextMove);
                                if (board.occupiedByOther(piece, nextMove)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        ArrayList<Square> results = new ArrayList<>();
        if (withCheck) {
            for (final Square move : possibleMoves) {
                final boolean safe = new CheckController(board, board.myKing(piece.getColor()), piece, move).isSafe();
                if (safe) {
                    results.add(new Square(move.getPosX(), move.getPosY(), null));
                }
            }
        } else {
            results = possibleMoves;
        }

        return new ArrayList<Square>(Arrays.asList(results.stream().distinct().toArray(Square[]::new)));
    }
}
