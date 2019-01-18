package com.ovgu.ccd.gui;

import com.ovgu.ccd.gui.chessboardListener.ChessboardGrid;
import com.ovgu.ccd.gui.chessboardListener.Point;
import com.ovgu.ccd.pieces.Square;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;

/**
 * Test class for the chessboard grid class
 *
 * @author CCD team
 * @version 1.0
 */
public class ChessboardGridTest
{
    private ChessboardGrid grid = null;


    /**
     * setup method - this will setup and appropriated environment to work with
     * (it creates a basic grid structure)
     *
     */
    @Before
    public void setup()
    {
        grid = new ChessboardGrid(new Point(500,500), 500);
    }


    /**
     * This test method tests if the get-square-x-position is working properly.
     *
     * @result  get square method returns the right square according to (x,y) position
     *          and the x position was correctly stored
     */
    @Test
    public void testGetSquareBoardPosX()
    {
        assertEquals(grid.getSquare(3,1).getBoardSquare().getPosX(), 3);
    }


    /**
     * This test method tests if the get-square-y-position is working properly.
     *
     * @result  get square method returns the right square according to (x,y) position
     *          and the y position was correctly stored
     */
    @Test
    public void testGetSquareBoardPosY()
    {
        assertEquals(grid.getSquare(2,4).getBoardSquare().getPosY(), 4);
    }


    /**
     * Checks if the the center point of a square (1,1) is properly stated to be inside the square.
     *
     * @result  get square method is working
     *          the center method returns a point inside the square
     *          and the method, which returns the square according to a point is working properly too
     */
    @Test
    public void testGetSquareWithinPoint()
    {
        assertEquals(
                grid.getSquareWithinPoint(grid.getSquare(1,1).center()),
                grid.getSquare(1,1));
    }


    /**
     * Checks if the the center point of a square (1,1) is properly stated to be inside the square.
     *
     * @result  the chessboard grid is colored alternately
     */
    @Test
    public void testSquareFillColorScheme()
    {
        assertNotSame(
                grid.getSquare(1,1).getFillColor(),
                grid.getSquare(1,2).getFillColor());
    }
}
