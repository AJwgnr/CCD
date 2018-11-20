package com.ovgu.ccd.gui;

import com.ovgu.ccd.gui.chessboardListener.ChessPanel;
import com.ovgu.ccd.gui.chessboardListener.Point;
import com.ovgu.ccd.gui.chessboardListener.Triangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChessPanelTest
{
    private ChessPanel chesspanel = null;


    @Before
    public void setup()
    {
        chesspanel = new ChessPanel(
                new Point(100,100),
                new Point(200, 100),
                new Point(200,200),
                new Point(100,200)
        );
    }


    @Test
    public void testAreaComputation()
    {
        assertEquals(chesspanel.getArea(), 10000, 0);
    }

    @Test
    public void testIsPointInsidePanel()
    {
        assertTrue(chesspanel.isPointInsidePanel(new Point(150,150)));
    }
}
