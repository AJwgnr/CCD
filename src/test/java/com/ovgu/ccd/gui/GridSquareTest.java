package com.ovgu.ccd.gui;

import com.ovgu.ccd.gui.threeplayer.GridSquare;
import com.ovgu.ccd.gui.threeplayer.Point;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GridSquareTest
{
    private GridSquare square = null;


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


    @Test
    public void testAreaComputation()
    {
        assertEquals(square.getArea(), 10000, 0);
    }

    @Test
    public void testIsPointInsidePanel()
    {
        assertTrue(square.isPointInside(new Point(150,150)));
    }
}
