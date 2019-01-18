package com.ovgu.ccd.gui.threeplayer;

import java.awt.*;
import java.util.ArrayList;


public class Line extends GeometricShape
{
	private static final long serialVersionUID = -516881209755986697L;
	
	private Point start = new Point(0,0);
	private Point end = new Point(0,0);

	@Override
	public double area() {
		return 0;
	}

	
	// constructor
	public Line(Point start, Point end)
	{
		//setLayout(null);
		setStartPoint(start);
		setEndPoint(end);
	}
	

	// set coordinates of the start point
	public void setStartPoint(Point start)
	{
		this.start = start;
	}
	
	
	// set coordinates of the end point 
	public void setEndPoint(Point end)
	{
		this.end = end;
	}
	
	
	// @return: the start point coordinates
	public Point getStartPoint()
	{
		return this.start;
	}
	
	
	// @return: the end point coordinates
	public Point getEndPoint()
	{
		return this.end;
	}
	
	
	// with euclidean distance
	public double getLength()
	{
		return Math.sqrt(
				Math.pow(this.end.getX() - this.start.getX(), 2) +
				Math.pow(this.end.getY() - this.start.getY(), 2));
	}
	
	
	public Point computeIntersectionPoint(Line otherLine)
	{
        int a1 = this.end.getY() - this.start.getY();
        int b1 = this.start.getX() - this.end.getX();
        int c1 = a1 * this.start.getX() + b1 * this.start.getY();
 
        int a2 = otherLine.end.getY() - otherLine.start.getY();
        int b2 = otherLine.start.getX() - otherLine.end.getX();
        int c2 = a2 * otherLine.start.getX() + b2 * otherLine.start.getY();
 
        int delta = a1 * b2 - a2 * b1;
        
        return new Point((b2 * c1 - b1 * c2) / delta, (a1 * c2 - a2 * c1) / delta);
	}
	
	
	public float getSlope()
	{
		float diffY = (float)(this.getEndPoint().getY() - this.getStartPoint().getY());
		float diffX = (float)(this.getEndPoint().getX() - this.getStartPoint().getX());
		
		if (diffX != 0)
			return diffY / diffX;
		else
			return 0; 
	}
	
	
	public float getIntercept()
	{
		return this.getStartPoint().getY() - getSlope() * this.getStartPoint().getX();
	}
	
	
	public boolean isPointOnLine(Point point)
	{
		if (point.getY() == getSlope() * point.getX() + getIntercept())
			return true;
		else
			return false;
	}
	
	
	public float getMinDistanceToPoint(Point point)
	{
		// compute via linear interpolation
		// d = |(p-p1) - (((p-p1)*(p2-p1)) / |p2-p1|^2) * (p2-p1)|
		float lengthLine = (float)Math.pow(this.getLength(),2); 		// |p2-p1|^2
		Point pp1 = point.sub(this.getStartPoint()); 					// (p-p1)
		Point p2p1 = this.getEndPoint().sub(this.getStartPoint()); 		// (p2-p1)
		Point scaled = p2p1.scale((float)(pp1.dot(p2p1) / lengthLine));
		return (float)pp1.sub(scaled).toVectorGetNorm();
	}


	public Point getMeanPoint()
	{
		return this.getStartPoint().getMeanPointBetween(this.getEndPoint());
	}


	// @param: numOfPoints: sum of points on line without start and end point
	// @return: list of ALL point on line
	public ArrayList<Point> getEquallyDistributedPoints(int numOfPoints)
	{
		ArrayList<Point> points = new ArrayList<Point>();
		float distance = (float)this.getLength() / (numOfPoints - 1);
		for (int i = 0; i < numOfPoints; i++)
			points.add(getPointAfterDistance(distance * i));
		return points;
	}


	// comuted with linear interpolation
	public Point getPointAfterDistance(float distance)
	{
		if (distance == 0.0f)
			return this.getStartPoint();
		else if (distance == getLength())
			return this.getEndPoint();
		else
		{
			float t = distance / (float)getLength();
			float x = (1 - t) * this.getStartPoint().getX() + t * this.getEndPoint().getX();
			float y = (1 - t) * this.getStartPoint().getY() + t * this.getEndPoint().getY();
			return new Point(x,y);
		}
	}


	@Override
	public void draw(Graphics graphics)
	{
		graphics.drawLine(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY());
	}
}
