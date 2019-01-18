package com.ovgu.ccd.applogic;

import com.ovgu.ccd.pieces.Piece;

/**
 * Exception for when there is no piece
 */
class NoPieceForSpy extends Exception {
    /**
     * @param message
     */
    public NoPieceForSpy(String message) {
        super(message);
    }
}

/**
 * Exception for when a spy was already activated
 */
class SpyAlreadyActive extends Exception {
    /**
     * @param message
     */
    public SpyAlreadyActive(String message) {
        super(message);
    }
}

/**
 * Exception for when the piece is different from a Pawn
 */
class SpyCanOnlyBeAPawn extends Exception {
    /**
     * @param message
     */
    public SpyCanOnlyBeAPawn(String message) {
        super(message);
    }
}

/**
 * Exception for when the Pawn is in the initial position
 */
class SpyCantBeInInitialPosition extends Exception {
    /**
     * @param message
     */
    public SpyCantBeInInitialPosition(String message) {
        super(message);
    }
}

/**
 * Exception for when the king is in check
 */
class SpyInCheck extends Exception {
    /**
     * @param message
     */
    public SpyInCheck(String message) {
        super(message);
    }
}

/**
 * Class in charge of activating the spy
 */
public class SpyActivator {

    /**
     * Player that is activating its spy
     */
    private Player player;
    /**
     * Pawn to be activated
     */
    private Piece piece;
    /**
     * Board
     */
    private ThreePlayerChessboard board;

    /**
     * @param player that is activating spy
     * @param piece to be activated
     * @param board
     */
    public SpyActivator(Player player, Piece piece, ThreePlayerChessboard board) {
        this.player = player;
        this.piece = piece;
        this.board = board;

    }

    /**
     * Activates a spy
     *
     * @throws Exception
     */
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
        piece.setImage();
    }
}
