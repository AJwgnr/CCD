package com.ovgu.ccd.applogic;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * test class for the player sequence manager
 *
 * @author  CCD DeepBlue team
 * @version 1.0
 * @since
 */
public class PlayerSequenceManagerTest
{
    private PlayerSequenceManager manager = null;
    private Player whitePlayer = null;
    private Player greyPlayer = null;
    private Player blackPlayer = null;


    /**
     * setup method for the test environment
     *
     */
    @Before
    public void setup()
    {
        this.whitePlayer    = new Player("Mr.White", Player.Colors.WHITE.name());
        this.greyPlayer     = new Player("Mr.Grey", Player.Colors.GREY.name());
        this.blackPlayer    = new Player("Mr.Black", Player.Colors.BLACK.name());

        Player playerArray[] = {this.whitePlayer, this.greyPlayer, this.blackPlayer};

        manager = new PlayerSequenceManager(playerArray);
    }


    /**
     * test if array of players was correctly stored
     *
     */
    @Test
    public void testPlayerStorage()
    {
        assertEquals(this.manager.getStoredPlayers()[0], this.whitePlayer);
        assertEquals(this.manager.getStoredPlayers()[1], this.greyPlayer);
        assertEquals(this.manager.getStoredPlayers()[2], this.blackPlayer);
    }


    /**
     * test player sequence
     *
     */
    @Test
    public void testPlayerSequence()
    {
        assertEquals(this.manager.getCurrentPlayer(), this.whitePlayer);
        this.manager.moveDone();
        assertEquals(this.manager.getCurrentPlayer(), this.greyPlayer);
        this.manager.moveDone();
        assertEquals(this.manager.getCurrentPlayer(), this.blackPlayer);
        this.manager.moveDone();
        assertEquals(this.manager.getCurrentPlayer(), this.whitePlayer);
        this.manager.moveDone();
    }
}
