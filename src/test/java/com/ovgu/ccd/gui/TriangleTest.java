package com.ovgu.ccd.gui;

import com.ovgu.ccd.gui.chessboardListener.Point;
import com.ovgu.ccd.gui.chessboardListener.Triangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Test class for the triangle test
 *
 * @author CCD team
 * @version 1.0
 */
public class TriangleTest
{
    private Triangle triangle = null;

    /**
     * setup method - this will setup and appropriated environment to work with
     * (this creates a basic triangle object to work with)
     *
     */
    @Before
    public void setup()
    {
        triangle = new Triangle(
                new Point(100,100),
                new Point(200, 100),
                new Point(200,200)
        );
    }


    /**
     * test the triangle area computation
     *
     * @result  the triangle area computation is working properly
     */
    @Test
    public void testAreaComputation()
    {
        assertEquals(triangle.area(), 5000, 0);
    }
}
