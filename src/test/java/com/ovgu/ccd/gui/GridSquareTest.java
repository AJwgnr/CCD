package com.ovgu.ccd.gui;

import com.ovgu.ccd.gui.threeplayer.GridSquare;
import com.ovgu.ccd.gui.threeplayer.Point;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test class for the grid square test
 *
 * @author CCD team
 * @version 1.0
 */
public class GridSquareTest
{
    private GridSquare square = null;


    /**
     * setup method - this will setup and appropriated environment to work with
     * (this creates a basic grid square object to work with)
     *
     */
    @Before
    public void setup()
    {
        square = new GridSquare(
                new Point(100,100),
                new Point(200, 100),
                new Point(200,200),
                new Point(100,200)
        );
    }


    /**
     * test for the grid square area computation
     *
     * @result  the computation of the grid square area is working properly
     */
    @Test
    public void testAreaComputation()
    {
        assertEquals(square.getArea(), 10000, 0);
    }


    /**
     * test the boolean function if a point is inside a square
     *
     * @result  the point in the center of the grid square is correctly classified as been inside the square
     */
    @Test
    public void testIsPointInsidePanel()
    {
        assertTrue(square.isPointInside(new Point(150,150)));
    }
}
