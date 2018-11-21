package com.ovgu.ccd.gui.chessboardListener;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// regular hexagon
public class Hexagon extends GeometricShape
{
	private static final long serialVersionUID = 4539157182258466862L;
	
	private boolean visibility = true;
	private HashMap<String, Point> vertices = new HashMap<String, Point>();
	private HashMap<String, Line> lines = new HashMap<String, Line>();
	private Point center = null;
	private int radius = 0;

	
	public Hexagon(Point center, int radius)
	{
		this.radius = radius;
		this.center = center;
		computeVertices();
		setupLines();
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


	private void setupLines()
	{
		lines.put("AB", new Line(this.vertices.get("A"), this.vertices.get("B")));
		lines.put("BC", new Line(this.vertices.get("B"), this.vertices.get("C")));
		lines.put("CD", new Line(this.vertices.get("C"), this.vertices.get("D")));
		lines.put("DE", new Line(this.vertices.get("D"), this.vertices.get("E")));
		lines.put("EF", new Line(this.vertices.get("E"), this.vertices.get("F")));
		lines.put("FA", new Line(this.vertices.get("F"), this.vertices.get("A")));
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

	public List<Line> getLinesAsList()
	{
		return this.lines.values().stream().collect(Collectors.toList());
	}

	public HashMap<String, Line> getLinesAsMap()
	{
		return this.lines;
	}

	public List<Point> getVerticesAsList()
	{
		return this.vertices.values().stream().collect(Collectors.toList());
	}
	
	public HashMap<String, Point> getVerticesAsMap()
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
		if (this.visibility)
		{
			for(Map.Entry<String, Line> line : lines.entrySet())
				line.getValue().paint(graphics);
		}
	}

	@Override
	public double area() {
		return 0;
	}
}
