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
        square.setPosX(1);
        square.setPosX(2);
    }


    @Test
    public void testAreaComputation()
    {
        assertEquals(square.getArea(), 10000, 0);
    }

    @Test
    public void testIsPointInsideSquare()
    {
        assertTrue(square.isPointInside(new Point(150,150)));
    }

    @Test
    public void testEqualsSquare()
    {
        Square squareOther = new Square(
                new Point(100,100),
                new Point(200, 100),
                new Point(200,200),
                new Point(100,200));
        squareOther.setPosX(1);
        squareOther.setPosX(2);
        assertTrue(square.equals(squareOther));
    }

    @Test
    public void testCenterComputation()
    {
        assertEquals(square.center().getX(), 150);
        assertEquals(square.center().getY(), 150);
    }
}
