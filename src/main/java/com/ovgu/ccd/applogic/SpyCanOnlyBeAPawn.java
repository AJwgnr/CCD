package com.ovgu.ccd.applogic;

/**
 * Exception for when the piece is different from a Pawn
 */
public  class SpyCanOnlyBeAPawn extends Exception {
    /**
     * @param message
     */
    public SpyCanOnlyBeAPawn(String message) {
        super(message);
    }
}
