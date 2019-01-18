package com.ovgu.ccd.applogic;

import com.ovgu.ccd.moves.three.*;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for checking if a king is under "check"
 */
public class CheckController {

    /**
     * current board
     */
    private ThreePlayerChessboard board;
    /**
     * king to be checked
     */
    private King king;
    /**
     * piece that's being moved
     */
    private Piece piece;
    /**
     * current move
     */
    private Square move;
    /**
     * previous position of the piece
     */
    private Square previousPosition;
    /**
     * piece that might have been eaten in the move
     */
    private Piece eatenPiece;

    /**
     * @param board current board
     * @param king king under attack
     * @param piece that's being moved
     * @param move to be done
     */
    public CheckController(ThreePlayerChessboard board, King king, Piece piece, Square move) {
        this.board = board;
        this.king = king;
        this.piece = piece;
        if (piece.getSquare() != null) {
            this.previousPosition = new Square(piece.getPosX(), piece.getPosY(), null);
        }
        if (move != null) {
            this.move = new Square(move.getPosX(), move.getPosY(), null);
            if (!board.getSquare(move.getPosX(), move.getPosY()).isEmpty()) {
                this.eatenPiece = board.getSquare(move.getPosX(), move.getPosY()).getPiece();
            }
        }
    }

    /**
     * @return true if after move the king is not under "check"
     * @throws Exception in case a move is invalid
     */
    public boolean isSafe() throws Exception {
        boolean inCheck = false;

        applyMove();
        inCheck = !isSafeStraightDirection() ||
                !isSafeDiagonalDirection() ||
                !isaSafeKingDirection() ||
                !isSafeKnightDirection() ||
                !isSafePawnDirection();
        undoMove();
        return !inCheck;
    }

    /**
     * applies the corresponding move
     */
    private void applyMove() {
        if (move != null) {
            board.setPiece(null, piece.getPosX(), piece.getPosY());
            board.setPiece(piece, move.getPosX(), move.getPosY());
        }
    }

    /**
     * undoes the applied move if any
     */
    private void undoMove() {
        if (move != null) {
            board.setPiece(null, move.getPosX(), move.getPosY());
            board.setPiece(piece, previousPosition.getPosX(), previousPosition.getPosY());
            if (eatenPiece != null) {
                board.setPiece(eatenPiece, move.getPosX(), move.getPosY());
            }
        }
    }

    /**
     * @return true if king is safe from pawns
     * @throws Exception in case a move is invalid
     */
    private boolean isSafePawnDirection() throws Exception {
        ArrayList<Square> moves = new ArrayList<>();
        List<Piece> pawns = null;
        boolean safe = true;

        if (king.getColor() == Player.Colors.WHITE) {
            pawns = board.blackPawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());

            for (Piece pawn : pawns) {
                moves.addAll(new PawnMoves(pawn, board).allBlackMoves(false));
            }
            safe = noPawnAttacking(moves);
            moves = new ArrayList<>();

            pawns = board.greyPawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());
            for (Piece pawn : pawns) {
                moves.addAll(new PawnMoves(pawn, board).allGreyMoves(false));
            }
            safe = safe && noPawnAttacking(moves);
        } else if (king.getColor() == Player.Colors.BLACK) {
            pawns = board.whitePawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());

            for (Piece pawn : pawns) {
                moves.addAll(new PawnMoves(pawn, board).allWhiteMoves(false));
            }
            safe = noPawnAttacking(moves);
            moves = new ArrayList<>();

            pawns = board.greyPawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());
            for (Piece pawn : pawns) {
                moves.addAll(new PawnMoves(pawn, board).allGreyMoves(false));
            }
            safe = safe && noPawnAttacking(moves);
        } else {
            pawns = board.whitePawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());

            for (Piece pawn : pawns) {
                moves.addAll(new PawnMoves(pawn, board).allWhiteMoves(false));
            }
            safe = noPawnAttacking(moves);
            moves = new ArrayList<>();

            pawns = board.blackPawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());
            for (Piece pawn : pawns) {
                moves.addAll(new PawnMoves(pawn, board).allBlackMoves(false));
            }
            safe = safe && noPawnAttacking(moves);
        }

        return safe;
    }

    /**
     * @param moves from other players pawns
     * @return true if none of the provided moves attacks the king
     */
    private boolean noPawnAttacking(List<Square> moves) {
        return !moves.stream().anyMatch(square -> {
            return square.equals(king.getSquare());
        });
    }

    /**
     * @return true if no other king is attacking
     * @throws Exception in case a move is invalid
     */
    private boolean isaSafeKingDirection() throws Exception {
        ArrayList<Square> moves = new KingMoves(king, board).allMoves(false);
        List<Square> squares = filteredMoves(moves);

        boolean isSafe = true;


        for (Square s : squares) {
            Square boardSquare = board.getSquare(s.getPosX(), s.getPosY());
            if (boardSquare.getPiece().name.equals("King")) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    /**
     * @return true if no piece is attacking the king in a straight fashion
     * @throws Exception in case a move is invalid
     */
    private boolean isSafeStraightDirection() throws Exception {
        ArrayList<Square> moves = new StraightMoves(king, board).allMoves(false);
        List<Square> squares = filteredMoves(moves);
        boolean isSafe = true;


        for (Square s : squares) {
            Square boardSquare = board.getSquare(s.getPosX(), s.getPosY());
            if (boardSquare.getPiece().name.equals("Rook") || boardSquare.getPiece().name.equals("Queen")) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    /**
     * @return true if no piece is attacking the king in a diagonal fashion
     * @throws Exception in case a move is invalid
     */
    private boolean isSafeDiagonalDirection() throws Exception {
        ArrayList<Square> moves = new DiagonalMoves(king, board).allMoves(false);
        List<Square> squares = filteredMoves(moves);
        boolean isSafe = true;


        for (Square s : squares) {
            Square boardSquare = board.getSquare(s.getPosX(), s.getPosY());
            if (boardSquare.getPiece().name.equals("Queen") || boardSquare.getPiece().name.equals("Bishop")) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    /**
     * @return true if no knight is attacking the king
     * @throws Exception in case a move is invalid
     */
    private boolean isSafeKnightDirection() throws Exception {
        ArrayList<Square> moves = new KnightMoves(king, board).allMoves(false);
        List<Square> squares = filteredMoves(moves);
        boolean isSafe = true;


        for (Square s : squares) {
            Square boardSquare = board.getSquare(s.getPosX(), s.getPosY());
            if (boardSquare.getPiece().name.equals("Knight")) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    /**
     * @param moves from attacking pieces
     * @return the moves that are possible and belong to pieces from other players
     */
    private List<Square> filteredMoves(ArrayList<Square> moves) {
        List<Square> result = new ArrayList<>();


        for (Square s : moves) {
            Square boardSquare = board.getSquare(s.getPosX(), s.getPosY());
            if (boardSquare.getPiece() != null && boardSquare.getPiece().getColor() != king.getColor()) {
                result.add(new Square(s.getPosX(), s.getPosY(), null));
            }
        }
        return result;
    }
}
