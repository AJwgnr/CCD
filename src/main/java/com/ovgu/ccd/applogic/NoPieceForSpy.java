package com.ovgu.ccd.applogic;

/**
 * Exception for when there is no piece
 */
public class NoPieceForSpy extends Exception {
    /**
     * @param message
     */
    public NoPieceForSpy(String message) {
        super(message);
    }
}
