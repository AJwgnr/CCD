package com.ovgu.ccd.gui;

import com.ovgu.ccd.gui.chessboardListener.Point;
import com.ovgu.ccd.pieces.Square;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SquareTest
{
    private Square square = null;


    @Before
    public void setup()
    {
        square = new Square(
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
