package com.ovgu.ccd.moves.three;

import com.ovgu.ccd.applogic.CheckController;
import com.ovgu.ccd.applogic.IBoard;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.moves.IMove;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that generates pawn moves
 */
public class PawnMoves implements IMove {
    /**
     * pawn for which moves are calculated
     */
    final private Piece piece;
    /**
     * board in which moves are calculated
     */
    final private ThreePlayerChessboard board;

    /**
     * @param piece pawn for which moves are calculated
     * @param board in which moves are calculated
     */
    public PawnMoves(final Piece piece, final IBoard board) {
        this.piece = piece;
        this.board = (ThreePlayerChessboard) board;
    }

    @Override
    public ArrayList<Square> moves() throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<>();
        if (piece.getColor() == Player.Colors.WHITE) {
            possibleMoves = whiteMoves();
        } else if (piece.getColor() == Player.Colors.BLACK) {
            possibleMoves = blackMoves();
        } else {
            possibleMoves = greyMoves();
        }
        return new ArrayList<Square>(Arrays.asList(possibleMoves.stream().distinct().toArray(Square[]::new)));
    }


    /**
     * @return possible moves for a white pawn
     */
    public ArrayList<Square> whiteMoves() {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();

        try {
            possibleMoves.addAll(allWhiteMoves(true));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return possibleMoves;
    }

    /**
     * @param withCheck true if we want to only return the moves
     *                  for white pawn that don't generate a "check"
     * @return a list of possible moves
     * @throws Exception in case a move is invalid
     */
    public ArrayList<Square> allWhiteMoves(final boolean withCheck) throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;
        Square eatMove;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            if (ThreePlayerChessboard.A <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.D) {
                move = new Square(piece.getPosX() + 1, piece.getPosY(), null);

                eatMove = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }

                if (piece.getPosY() == ThreePlayerChessboard.D && piece.getPosX() + 1 == 4) {
                    eatMove = new Square(8, ThreePlayerChessboard.E, null);
                }
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }
                if (piece.getPosY() == ThreePlayerChessboard.D && piece.getPosX() + 1 == 4) {
                    eatMove = new Square(4, ThreePlayerChessboard.I, null);
                }
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }
                eatMove = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }
            }
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.H) {
                if (piece.getPosX() + 1 <= 3) {
                    move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
                    eatMove = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    eatMove = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    if (piece.getPosY() == ThreePlayerChessboard.D) {
                        eatMove = new Square(8, ThreePlayerChessboard.I, null);
                        if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                            possibleMoves.add(eatMove);
                        }
                    }
                } else {
                    move = new Square(8, piece.getPosY(), null);

                    eatMove = new Square(8, piece.getPosY() + 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }

                    eatMove = new Square(8, piece.getPosY() - 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    if (piece.getPosY() == ThreePlayerChessboard.E) {
                        eatMove = new Square(4, ThreePlayerChessboard.D, null);
                    }
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    if (piece.getPosY() == ThreePlayerChessboard.E) {
                        eatMove = new Square(8, ThreePlayerChessboard.I, null);
                    }
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                }
            }
        }
        if (piece.getPosX() + 1 == 2) {
            final Square twoStepMove = new Square(3, piece.getPosY(), null);
            final Square oneStepMove = new Square(2, piece.getPosY(), null);
            if (board.validMove(oneStepMove, piece) && board.getSquare(oneStepMove.getPosX(), oneStepMove.getPosY()).isEmpty() && board.getSquare(2, twoStepMove.getPosY()).isEmpty()) {
                if (board.getSquare(twoStepMove.getPosX(), twoStepMove.getPosY()).isEmpty()) {
                    possibleMoves.add(twoStepMove);
                }
            }
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 7) {
            move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
            if (piece.getPosY() == ThreePlayerChessboard.D) {
                eatMove = new Square(piece.getPosX() + 1, ThreePlayerChessboard.I, null);
            } else {
                eatMove = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
            }
            if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                possibleMoves.add(eatMove);
            }
            if (piece.getPosY() == ThreePlayerChessboard.I) {
                eatMove = new Square(piece.getPosX() + 1, ThreePlayerChessboard.D, null);
            } else {
                eatMove = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
            }
            if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                possibleMoves.add(eatMove);
            }
        }

        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 11) {
            move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
            if (ThreePlayerChessboard.E <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.F) {
                eatMove = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }

                if (piece.getPosY() == ThreePlayerChessboard.E) {
                    eatMove = new Square(piece.getPosX() + 1, ThreePlayerChessboard.I, null);
                } else {
                    eatMove = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
                }
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }
            } else {
                if (piece.getPosY() == ThreePlayerChessboard.I) {
                    eatMove = new Square(piece.getPosX() + 1, ThreePlayerChessboard.E, null);
                } else {
                    eatMove = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
                }
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }
                if (piece.getPosY() != ThreePlayerChessboard.H) {
                    eatMove = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                }
            }
        }

        if (move != null && board.validMove(move, piece) && board.getSquare(move.getPosX(), move.getPosY()).isEmpty()) {
            possibleMoves.add(move);
        }

        final ArrayList<Square> results = new ArrayList<>();
        if (withCheck) {
            for (final Square s : possibleMoves) {
                final boolean safe = new CheckController(board, board.myKing(piece.getColor()), piece, s).isSafe();
                if (safe) {
                    results.add(new Square(s.getPosX(), s.getPosY(), null));
                }
            }
            possibleMoves = results;
        }
        return possibleMoves;
    }

    /**
     * @return possible moves for a black pawn
     */
    public ArrayList<Square> blackMoves() {
        final ArrayList<Square> possibleMoves = new ArrayList<Square>();

        try {
            possibleMoves.addAll(allBlackMoves(true));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return possibleMoves;
    }

    /**
     * @param withCheck true if we want to only return the moves
     *                  for black pawn that don't generate a "check"
     * @return a list of possible moves
     * @throws Exception in case a move is invalid
     */
    public ArrayList<Square> allBlackMoves(final boolean withCheck) throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;
        Square eatMove;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            move = new Square(piece.getPosX() - 1, piece.getPosY(), null);

            eatMove = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
            if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                possibleMoves.add(eatMove);
            }

            eatMove = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
            if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                possibleMoves.add(eatMove);
            }
        }

        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 8) {
            if (piece.getPosX() + 1 == 5) {
                if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                    move = new Square(8, piece.getPosY(), null);
                    if (ThreePlayerChessboard.I == piece.getPosY()) {
                        eatMove = new Square(8, ThreePlayerChessboard.E, null);
                    } else {
                        eatMove = new Square(8, piece.getPosY() - 1, null);
                    }
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    if (ThreePlayerChessboard.I == piece.getPosY()) {
                        eatMove = new Square(3, ThreePlayerChessboard.D, null);
                    }
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }

                    eatMove = new Square(8, piece.getPosY() + 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                } else {
                    move = new Square(piece.getPosX() - 1, piece.getPosY(), null);

                    eatMove = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    if (ThreePlayerChessboard.D == piece.getPosY()) {
                        eatMove = new Square(8, ThreePlayerChessboard.I, null);
                    }
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    if (ThreePlayerChessboard.D == piece.getPosY()) {
                        eatMove = new Square(3, ThreePlayerChessboard.E, null);
                    }
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                }
            } else {
                move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
                if (ThreePlayerChessboard.I == piece.getPosY()) {
                    eatMove = new Square(piece.getPosX() - 1, ThreePlayerChessboard.D, null);
                } else {
                    eatMove = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
                }
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }

                if (ThreePlayerChessboard.D == piece.getPosY()) {
                    eatMove = new Square(piece.getPosX() - 1, ThreePlayerChessboard.I, null);
                } else {
                    eatMove = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
                }
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }
            }
        }
        if (piece.getPosX() + 1 == 7) {
            final Square twoStepMove = new Square(4, piece.getPosY(), null);
            final Square oneMove = new Square(5, piece.getPosY(), null);
            if (board.validMove(oneMove, piece) && board.getSquare(oneMove.getPosX(), oneMove.getPosY()).isEmpty() && board.validMove(twoStepMove, piece)) {
                if (board.getSquare(twoStepMove.getPosX(), twoStepMove.getPosY()).isEmpty()) {
                    possibleMoves.add(twoStepMove);
                }
            }
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
            if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                if (ThreePlayerChessboard.I == piece.getPosY()) {
                    eatMove = new Square(piece.getPosX() + 1, ThreePlayerChessboard.E, null);
                } else {
                    eatMove = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
                }
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }
                eatMove = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }
            } else {
                eatMove = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }
                if (ThreePlayerChessboard.E == piece.getPosY()) {
                    eatMove = new Square(piece.getPosX() + 1, ThreePlayerChessboard.I, null);
                } else {
                    eatMove = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
                }
                if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                    possibleMoves.add(eatMove);
                }
            }
        }

        if (board.validMove(move, piece) && board.getSquare(move.getPosX(), move.getPosY()).isEmpty()) {
            possibleMoves.add(move);
        }


        final ArrayList<Square> results = new ArrayList<>();
        if (withCheck) {
            for (final Square s : possibleMoves) {
                final boolean safe = new CheckController(board, board.myKing(piece.getColor()), piece, s).isSafe();
                if (safe) {
                    results.add(new Square(s.getPosX(), s.getPosY(), null));
                }
            }
            possibleMoves = results;
        }
        return possibleMoves;
    }

    /**
     * @return possible moves for a grey pawn
     */
    public ArrayList<Square> greyMoves() {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();

        try {
            possibleMoves.addAll(allGreyMoves(true));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return possibleMoves;
    }

    /**
     * @param withCheck true if we want to only return the moves
     *                  for grey pawn that don't generate a "check"
     * @return a list of possible moves
     * @throws Exception in case a move is invalid
     */
    public ArrayList<Square> allGreyMoves(final boolean withCheck) throws Exception {
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        Square move = null;
        Square eatMove;

        if (1 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 4) {
            move = new Square(piece.getPosX() - 1, piece.getPosY(), null);

            eatMove = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
            if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                possibleMoves.add(eatMove);
            }
            eatMove = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
            if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                possibleMoves.add(eatMove);
            }
        }
        if (5 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 7) {
            move = new Square(piece.getPosX() + 1, piece.getPosY(), null);
            if (ThreePlayerChessboard.D == piece.getPosY()) {
                eatMove = new Square(piece.getPosX() + 1, ThreePlayerChessboard.I, null);
            } else {
                eatMove = new Square(piece.getPosX() + 1, piece.getPosY() + 1, null);
            }
            if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                possibleMoves.add(eatMove);
            }
            if (ThreePlayerChessboard.I == piece.getPosY()) {
                eatMove = new Square(piece.getPosX() + 1, ThreePlayerChessboard.D, null);
            } else {
                eatMove = new Square(piece.getPosX() + 1, piece.getPosY() - 1, null);
            }
            if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                possibleMoves.add(eatMove);
            }
        }
        if (9 <= piece.getPosX() + 1 && piece.getPosX() + 1 <= 12) {
            if (piece.getPosX() + 1 == 9) {
                if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                    move = new Square(4, piece.getPosY(), null);
                    eatMove = new Square(4, piece.getPosY() + 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }

                    eatMove = new Square(4, piece.getPosY() - 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    if (ThreePlayerChessboard.I == piece.getPosY()) {
                        eatMove = new Square(3, ThreePlayerChessboard.E, null);
                    }
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    if (ThreePlayerChessboard.I == piece.getPosY()) {
                        eatMove = new Square(4, ThreePlayerChessboard.D, null);
                    }
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                } else {
                    move = new Square(3, piece.getPosY(), null);

                    if (ThreePlayerChessboard.E == piece.getPosY()) {
                        eatMove = new Square(4, ThreePlayerChessboard.I, null);
                        if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                            possibleMoves.add(eatMove);
                        }
                    } else {
                        eatMove = new Square(3, piece.getPosY() - 1, null);
                        if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                            possibleMoves.add(eatMove);
                        }
                    }
                    if (ThreePlayerChessboard.E == piece.getPosY()) {
                        eatMove = new Square(3, ThreePlayerChessboard.D, null);
                        if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                            possibleMoves.add(eatMove);
                        }
                    }
                    eatMove = new Square(3, piece.getPosY() + 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                }
            } else {
                move = new Square(piece.getPosX() - 1, piece.getPosY(), null);
                if (ThreePlayerChessboard.I <= piece.getPosY() && piece.getPosY() <= ThreePlayerChessboard.L) {
                    eatMove = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    if (ThreePlayerChessboard.I == piece.getPosY()) {
                        eatMove = new Square(piece.getPosX() - 1, ThreePlayerChessboard.E, null);
                    } else {
                        eatMove = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
                    }
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                } else {
                    if (ThreePlayerChessboard.E == piece.getPosY()) {
                        eatMove = new Square(piece.getPosX() - 1, ThreePlayerChessboard.I, null);
                    } else {
                        eatMove = new Square(piece.getPosX() - 1, piece.getPosY() - 1, null);
                    }
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                    eatMove = new Square(piece.getPosX() - 1, piece.getPosY() + 1, null);
                    if (board.validMove(eatMove, piece) && board.occupiedByOther(piece, eatMove)) {
                        possibleMoves.add(eatMove);
                    }
                }
            }
        }
        if (piece.getPosX() + 1 == 11) {
            final Square twoStepMove = new Square(8, piece.getPosY(), null);
            final Square oneStepMove = new Square(9, piece.getPosY(), null);
            if (board.validMove(oneStepMove, piece) && board.getSquare(oneStepMove.getPosX(), oneStepMove.getPosY()).isEmpty() && board.validMove(twoStepMove, piece)) {
                if (board.getSquare(twoStepMove.getPosX(), twoStepMove.getPosY()).isEmpty()) {
                    possibleMoves.add(twoStepMove);
                }
            }
        }

        if (move != null && board.validMove(move, piece) && board.getSquare(move.getPosX(), move.getPosY()).isEmpty()) {
            possibleMoves.add(move);
        }


        final ArrayList<Square> results = new ArrayList<>();
        if (withCheck) {
            for (final Square s : possibleMoves) {
                final boolean safe = new CheckController(board, board.myKing(piece.getColor()), piece, s).isSafe();
                if (safe) {
                    results.add(new Square(s.getPosX(), s.getPosY(), null));
                }
            }
            possibleMoves = results;
        }
        return possibleMoves;
    }
}
