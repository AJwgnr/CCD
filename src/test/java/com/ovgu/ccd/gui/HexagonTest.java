package com.ovgu.ccd.gui;


import com.ovgu.ccd.gui.chessboardListener.Hexagon;
import com.ovgu.ccd.gui.chessboardListener.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class HexagonTest
{
    private Hexagon hexagon = null;


    @Before
    public void setup()
    {
        hexagon = new Hexagon(new Point(100,100),100);
    }


    @Test
    public void testIsPointWithinHexagon()
    {
        assertTrue(hexagon.isPointWithinHexagon(new Point(80,80)));
    }
}
