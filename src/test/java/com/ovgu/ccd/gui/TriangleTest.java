package com.ovgu.ccd.gui;

import com.ovgu.ccd.gui.threeplayer.Point;
import com.ovgu.ccd.gui.threeplayer.Triangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TriangleTest
{
    private Triangle triangle = null;


    @Before
    public void setup()
    {
        triangle = new Triangle(
                new Point(100,100),
                new Point(200, 100),
                new Point(200,200)
        );
    }


    @Test
    public void testAreaComputation()
    {
        assertEquals(triangle.area(), 5000, 0);
    }
}
