package com.ovgu.ccd.applogic;

/**
 * Exception for when a spy was already activated
 */
public  class SpyAlreadyActive extends Exception {
    /**
     * @param message
     */
    public SpyAlreadyActive(String message) {
        super(message);
    }
}
