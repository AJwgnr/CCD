package com.ovgu.ccd.applogic;

import com.ovgu.ccd.moves.three.*;
import com.ovgu.ccd.pieces.King;
import com.ovgu.ccd.pieces.Pawn;
import com.ovgu.ccd.pieces.Piece;
import com.ovgu.ccd.pieces.Square;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CheckController {

    private ThreePlayerChessboard board;
    private King king;
    private Piece piece;
    private Square move;
    private Square previousPosition;
    private Piece eatenPiece;

    public  CheckController(ThreePlayerChessboard board, King king, Piece piece, Square move) {
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

    public boolean isSafe() throws Exception {
        boolean inCheck = false;

        applyMove();
        inCheck = !isSafeStraightDirection() ||
                  !isSafeDiagonalDirection() ||
                  !isaSafeKingDirection()    ||
                  !isSafeKnightDirection()   ||
                  !isSafePawnDirection();
        undoMove();
        return !inCheck;
    }

    private void applyMove() {
        // TODO: castlings...
        if (move != null) {
            board.setPiece(null, piece.getPosX(), piece.getPosY());
            board.setPiece(piece, move.getPosX(), move.getPosY());
        }
    }

    private void undoMove() {
        if (move != null) {
            board.setPiece(null, move.getPosX(), move.getPosY());
            board.setPiece(piece, previousPosition.getPosX(), previousPosition.getPosY());
            if (eatenPiece != null) {
                board.setPiece(eatenPiece, move.getPosX(), move.getPosY());
            }
        }
    }

    private boolean isSafePawnDirection() throws Exception {
        ArrayList<Square> moves = new ArrayList<>();
        List<Square> squares = null;
        List<Piece> pawns = null;
        boolean safe = true;

        if (king.getColor() == Player.Colors.WHITE) {
            pawns = board.blackPawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());

            for(Piece pawn : pawns) { moves.addAll(new PawnMoves(pawn, board).allBlackMoves(false)); }
            safe = noPawnAttacking(moves);
            moves = new ArrayList<>();

            pawns = board.greyPawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());
            for(Piece pawn : pawns) { moves.addAll(new PawnMoves(pawn, board).allGreyMoves(false)); }
            safe = safe && noPawnAttacking(moves);
        } else if (king.getColor() == Player.Colors.BLACK) {
            pawns = board.whitePawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());

            for(Piece pawn : pawns) { moves.addAll(new PawnMoves(pawn, board).allWhiteMoves(false)); }
            safe = noPawnAttacking(moves);
            moves = new ArrayList<>();

            pawns = board.greyPawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());
            for(Piece pawn : pawns) { moves.addAll(new PawnMoves(pawn, board).allGreyMoves(false)); }
            safe = safe && noPawnAttacking(moves);
        } else {
            pawns = board.whitePawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());

            for(Piece pawn : pawns) { moves.addAll(new PawnMoves(pawn, board).allWhiteMoves(false)); }
            safe = noPawnAttacking(moves);
            moves = new ArrayList<>();

            pawns = board.blackPawns.stream().filter(pawn -> pawn.getSquare() != null).collect(Collectors.toList());
            for(Piece pawn : pawns) { moves.addAll(new PawnMoves(pawn, board).allBlackMoves(false)); }
            safe = safe && noPawnAttacking(moves);
        }

        return safe;
    }

    private boolean noPawnAttacking(List<Square> moves) {
        return !moves.stream().anyMatch(square -> { return square.equals(king.getSquare()); });
    }

    private boolean isaSafeKingDirection() throws Exception {
        ArrayList<Square> moves = new KingMoves(king, board).allMoves(false);
        List<Square> squares = filteredMoves(moves);

        boolean isSafe = true;


        for (Square s: squares) {
            Square boardSquare = board.getSquare(s.getPosX(), s.getPosY());
            if (boardSquare.getPiece().name.equals("King")) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    private boolean isSafeStraightDirection() throws Exception {
        ArrayList<Square> moves = new StraightMoves(king, board).allMoves(false);
        List<Square> squares = filteredMoves(moves);
        boolean isSafe = true;


        for (Square s: squares) {
            Square boardSquare = board.getSquare(s.getPosX(), s.getPosY());
            if (boardSquare.getPiece().name.equals("Rook") || boardSquare.getPiece().name.equals("Queen")) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    private boolean isSafeDiagonalDirection() throws Exception {
        ArrayList<Square> moves = new DiagonalMoves(king, board).allMoves(false);
        List<Square> squares = filteredMoves(moves);
        boolean isSafe = true;


        for (Square s: squares) {
            Square boardSquare = board.getSquare(s.getPosX(), s.getPosY());
            if (boardSquare.getPiece().name.equals("Queen") || boardSquare.getPiece().name.equals("Bishop")) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    private boolean isSafeKnightDirection() throws Exception {
        ArrayList<Square> moves = new KnightMoves(king, board).allMoves(false);
        List<Square> squares = filteredMoves(moves);
        boolean isSafe = true;


        for (Square s: squares) {
            Square boardSquare = board.getSquare(s.getPosX(), s.getPosY());
            if (boardSquare.getPiece().name.equals("Knight")) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    private List<Square> filteredMoves(ArrayList<Square> moves) {
        List<Square> result = new ArrayList<>();


        for (Square s: moves) {
            Square boardSquare = board.getSquare(s.getPosX(), s.getPosY());
            if (boardSquare.getPiece() != null && boardSquare.getPiece().getColor() != king.getColor()) {
                result.add(new Square(s.getPosX(), s.getPosY(), null));
            }
        }
        return result;
    }
}
