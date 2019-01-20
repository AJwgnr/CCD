package com.ovgu.ccd.applogic;


/**
 * manager class which is responsible for the player sequence of turns
 *
 * @author  CCD DeepBlue team
 * @version 1.0
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
        if (currentPlayerID >= (players.length - 1))
        {
            currentPlayerID = 0;
        }
        else
        {
            currentPlayerID++;
        }
        this.print();
    }


    /**
     * simple print method, which outputs the current player in the terminal
     *
     */
    public void print()
    {
        System.out.println("Its your turn, " + this.getCurrentPlayer().getName() + " !");
    }


    /**
     * returns the array of all stored players
     *
     * @return array of all players, which are stored
     */
    public Player[] getStoredPlayers()
    {
        return this.players;
    }


    /**
     * sets the array of all stored players
     *
     * @param   players array of players
     */
    public void setPlayers(final Player players[])
    {
        this.players = players;
    }
}
