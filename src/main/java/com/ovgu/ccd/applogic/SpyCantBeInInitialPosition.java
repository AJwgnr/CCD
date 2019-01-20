package com.ovgu.ccd.applogic;

/**
 * Exception for when the Pawn is in the initial position
 */
public class SpyCantBeInInitialPosition extends Exception {
    /**
     * @param message
     */
    public SpyCantBeInInitialPosition(String message) {
        super(message);
    }
}
