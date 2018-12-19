package com.ovgu.ccd.gui;

import com.ovgu.ccd.gui.chessboardListener.ChessboardGrid;
import com.ovgu.ccd.gui.chessboardListener.Point;
import com.ovgu.ccd.pieces.Square;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ChessboardGridTest
{
    private ChessboardGrid grid = null;


    @Before
    public void setup()
    {
        grid = new ChessboardGrid(new Point(500,500), 500);
    }

    @Test
    public void testGetSquareBoardPosX()
    {
        assertEquals(grid.getSquare(3,1).getBoardSquare().getPosX(), 3);
    }

    @Test
    public void testGetSquareBoardPosY()
    {
        assertEquals(grid.getSquare(2,4).getBoardSquare().getPosY(), 4);
    }

    @Test
    public void testGetSquareWithinPoint()
    {
        assertEquals(
                grid.getSquareWithinPoint(grid.getSquare(1,1).center()),
                grid.getSquare(1,1));
    }
}
