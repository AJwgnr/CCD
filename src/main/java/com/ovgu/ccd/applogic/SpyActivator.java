package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.Piece;

class NoPieceForSpy extends Exception {
    public NoPieceForSpy(String message) {
        super(message);
    }
}

class SpyAlreadyActive extends Exception {
    public SpyAlreadyActive(String message) {
        super(message);
    }
}

class SpyCanOnlyBeAPawn extends Exception {
    public SpyCanOnlyBeAPawn(String message) {
        super(message);
    }
}

class SpyCantBeInInitialPosition extends Exception {
    public SpyCantBeInInitialPosition(String message) {
        super(message);
    }
}

class SpyInCheck extends Exception {
    public SpyInCheck(String message) {
        super(message);
    }
}

public class SpyActivator {

    private Player player;
    private Piece piece;
    private ThreePlayerChessboard board;

    public SpyActivator(Player player, Piece piece, ThreePlayerChessboard board) {
        this.player = player;
        this.piece = piece;
        this.board = board;
    }

    public void activateSpy() throws Exception {
        if (piece == null) {
            throw new NoPieceForSpy("The piece is null");
        }
        if (piece != null && !"Pawn".equals(piece.name)) {
            throw new SpyCanOnlyBeAPawn("Spy can only be a pawn");
        }
        CheckController check = new CheckController(board, board.myKing(player.getColor()), piece, null);

        if (!check.isSafe()) {
            throw new SpyInCheck("Can't activate spy in a check situation");
        }

        if (player.getColor() == Player.Colors.BLACK && board.isBlackSpyActive() ||
            player.getColor() == Player.Colors.WHITE && board.isWhiteSpyActive() ||
            player.getColor() == Player.Colors.GREY && board.isGreySpyActive()) {
            throw new SpyAlreadyActive("Spy already activated for that player");
        }

        if (player.getColor() == Player.Colors.WHITE) {
            if ((piece.getPosX() + 1 == 11 && piece.getColor() == Player.Colors.GREY) ||
                (piece.getPosX() + 1 == 7 && piece.getColor() == Player.Colors.BLACK)) {
                throw new SpyCantBeInInitialPosition("Spy can't be in initial position");
            } else {
                if (piece.getColor() == Player.Colors.GREY){
                    board.greyPawns.remove(piece);
                } else {
                    board.blackPawns.remove(piece);
                }
                board.whitePawns.add(piece);
                board.setWhiteSpyActive(true);
            }
        } else if (player.getColor() == Player.Colors.BLACK) {
            if ((piece.getPosX() + 1 == 11 && piece.getColor() == Player.Colors.GREY) ||
                (piece.getPosX() + 1 == 2 && piece.getColor() == Player.Colors.WHITE)) {
                throw new SpyCantBeInInitialPosition("Spy can't be in initial position");
            } else {
                if (piece.getColor() == Player.Colors.GREY){
                    board.greyPawns.remove(piece);
                } else {
                    board.whitePawns.remove(piece);
                }
                board.blackPawns.add(piece);
                board.setBlackSpyActive(true);
            }
        } else {
            if ((piece.getPosX() + 1 == 2 && piece.getColor() == Player.Colors.WHITE) ||
                (piece.getPosX() + 1 == 7 && piece.getColor() == Player.Colors.BLACK)) {
                throw new SpyCantBeInInitialPosition("Spy can't be in initial position");
            } else {
                if (piece.getColor() == Player.Colors.WHITE){
                    board.whitePawns.remove(piece);
                } else {
                    board.blackPawns.remove(piece);
                }
                board.greyPawns.add(piece);
                board.setGreySpyActive(true);
            }
        }
        piece.setPlayer(player);
    }
}
