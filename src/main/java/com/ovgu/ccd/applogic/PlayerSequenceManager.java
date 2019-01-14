package com.ovgu.ccd.applogic;


/**
 * manager class which is responsible for the player sequence of turns
 *
 * @author  CCD DeepBlue team
 * @version 1.0
 * @since
 */
public class PlayerSequenceManager
{
    private Player players[];
    private int currentPlayerID = 0;

    /**
     * constructor
     *
     * @param players list of players
     *
     */
    public PlayerSequenceManager(final Player players[])
    {
        this.players = players;
    }


    /**
     * returns the current player, who is moving
     *
     * @return player
     *
     */
    public Player getCurrentPlayer()
    {
        return players[currentPlayerID];
    }


    /**
     * this method should be called if a move was made
     *
     */
    public void moveDone()
    {
        if (currentPlayerID >= players.length)
            currentPlayerID = 0;
        else
            currentPlayerID++;
    }
}
