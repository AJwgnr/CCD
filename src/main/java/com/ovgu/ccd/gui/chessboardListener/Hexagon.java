package com.ovgu.ccd.gui.chessboardListener;

import java.awt.Graphics;
import java.util.HashMap;


// regular hexagon
public class Hexagon extends GeometricPrimitiveDrawer 
{
	private static final long serialVersionUID = 4539157182258466862L;
	
	private boolean visibility = true;
	private HashMap<String, Point> vertices = new HashMap<String, Point>();
	private Point center = new Point(0,0);
	private int radius = 0;
	
	
	// constructor
	public Hexagon()
	{
		
	}
	
	
	public Hexagon(Point center, int radius)
	{
		this.radius = radius;
		this.center = center;
		computeVertices();
	}
	
	
	// construct the hexagon and computes a list of all vertices
	private void computeVertices()
	{
		Circle outerCircle = new Circle(this.center, this.radius);
		
		Line diameterLine = new Line(
				new Point(this.center.getX() - this.radius, this.center.getY()),
				new Point(this.center.getX() + this.radius, this.center.getY()));
		
		this.vertices.put("F", outerCircle.findIntersectionPoint(diameterLine).get(0));
		this.vertices.put("C", outerCircle.findIntersectionPoint(diameterLine).get(1));
		
		Circle constructionCircleLeft = new Circle(this.vertices.get("F"), this.radius);
		Circle constructionCircleRight = new Circle(this.vertices.get("C"), this.radius);
		
		this.vertices.put("A", outerCircle.findIntersectionPoint(constructionCircleLeft).get(0));
		this.vertices.put("E", outerCircle.findIntersectionPoint(constructionCircleLeft).get(1));
		this.vertices.put("D", outerCircle.findIntersectionPoint(constructionCircleRight).get(0));
		this.vertices.put("B", outerCircle.findIntersectionPoint(constructionCircleRight).get(1));
	}
	
	
	public void setVisibility(boolean en)
	{
		this.visibility = en;
		repaint();
	}
	
	
	public Point getCenter()
	{
		return this.center;
	}
	
	
	public int getRadius()
	{
		return this.radius;
	}
	
	
	public HashMap<String, Point> getVertices()
	{
		return this.vertices;
	}
	
	
	public boolean isPointWithinHexagon(Point point)
	{
		// check whether the point lies inside or outside the radius circle
		float radiusPointToCenter = Math.abs(point.getDistanceTo(this.center));
		if (radiusPointToCenter <= this.radius)
			return true;
		else
			return false;
	}


	@Override
	public void draw(Graphics graphics)
	{
		if (this.visibility && !this.vertices.isEmpty())
		{
			new Line(this.vertices.get("A"), this.vertices.get("B")).paint(graphics);
			new Line(this.vertices.get("B"), this.vertices.get("C")).paint(graphics);
			new Line(this.vertices.get("C"), this.vertices.get("D")).paint(graphics);
			new Line(this.vertices.get("D"), this.vertices.get("E")).paint(graphics);
			new Line(this.vertices.get("E"), this.vertices.get("F")).paint(graphics);
			new Line(this.vertices.get("F"), this.vertices.get("A")).paint(graphics);
		}
	}
}
