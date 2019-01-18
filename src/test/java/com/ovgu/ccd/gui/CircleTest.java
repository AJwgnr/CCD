package com.ovgu.ccd.gui;

import com.ovgu.ccd.gui.threeplayer.Circle;
import com.ovgu.ccd.gui.threeplayer.Line;
import com.ovgu.ccd.gui.threeplayer.Point;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CircleTest
{
    private Circle circle = null;


    @Before
    public void setup()
    {
        circle = new Circle(new Point(100,100),100);
    }


    @Test
    public void testLineIntersection()
    {
        Line line = new Line(new Point(0,100), new Point(100,100));
        List<Point> output = circle.findIntersectionPoint(line);
        assertEquals(output.get(0).getX(), 0);
        assertEquals(output.get(0).getY(), 100);
    }
}
