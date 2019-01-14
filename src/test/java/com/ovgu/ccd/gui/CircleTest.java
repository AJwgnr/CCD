package com.ovgu.ccd.gui;

import com.ovgu.ccd.gui.chessboardListener.Circle;
import com.ovgu.ccd.gui.chessboardListener.Line;
import com.ovgu.ccd.gui.chessboardListener.Point;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Test class for the circle test
 *
 * @author CCD team
 * @version 1.0
 * @since
 * @see
 */
public class CircleTest
{
    private Circle circle = null;


    /**
     * setup method - this will setup and appropriated environment to work with
     * (this creates a basic circle object to work with)
     *
     * @param
     * @return
     * @result
     */
    @Before
    public void setup()
    {
        circle = new Circle(new Point(100,100),100);
    }


    /**
     * This method will test the circle-line-intersection
     *
     * @param
     * @return
     * @result  Line object works correct
     *          Intersections between a line and a circle is computed right
     */
    @Test
    public void testLineIntersection()
    {
        Line line = new Line(new Point(0,100), new Point(100,100));
        List<Point> output = circle.findIntersectionPoint(line);
        assertEquals(output.get(0).getX(), 0);
        assertEquals(output.get(0).getY(), 100);
    }
}
