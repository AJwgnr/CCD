package com.ovgu.ccd.gui;


import com.ovgu.ccd.gui.chessboardListener.Hexagon;
import com.ovgu.ccd.gui.chessboardListener.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


/**
 * Test class for the hexagon test
 *
 * @author CCD team
 * @version 1.0
 * @since
 * @see
 */
public class HexagonTest
{
    private Hexagon hexagon = null;


    /**
     * setup method - this will setup and appropriated environment to work with
     * (this creates a basic hexagon object to work with)
     *
     * @param
     * @return
     * @result
     */
    @Before
    public void setup()
    {
        hexagon = new Hexagon(new Point(100,100),100);
    }


    /**
     * Test if boolean function if the a point is inside the hexagon or not
     *
     * @param
     * @return
     * @result  the point inside the hexagon is correctly specified by the method
     */
    @Test
    public void testIsPointWithinHexagon()
    {
        assertTrue(hexagon.isPointWithinHexagon(new Point(80,80)));
    }
}
