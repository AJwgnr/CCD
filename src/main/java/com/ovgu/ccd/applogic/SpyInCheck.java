package com.ovgu.ccd.applogic;

/**
 * Exception for when the king is in check
 */
public class SpyInCheck extends Exception {
    /**
     * @param message
     */
    public SpyInCheck(String message) {
        super(message);
    }
}
