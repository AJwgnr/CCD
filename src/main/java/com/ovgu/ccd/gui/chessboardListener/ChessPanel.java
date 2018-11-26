package com.ovgu.ccd.gui.chessboardListener;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;


public class ChessPanel extends GeometricPrimitiveDrawer
{
	private static final long serialVersionUID = -7739043840904275539L;
	private HashMap<String, Point> vertices = new HashMap<String, Point>();
	private HashMap<String, Line> lines = new HashMap<String, Line>();
	private int columnIdx = -1;
	private int rowIdx = -1;
	
	
	public ChessPanel()
	{
		
	}
	
	
	public ChessPanel(Point a, Point b, Point c, Point d)
	{
		this.vertices.put("A", a);
		this.vertices.put("B", b);
		this.vertices.put("C", c);
		this.vertices.put("D", d);
		this.lines.put("AB", new Line(a, b));
		this.lines.put("BC", new Line(b, c));
		this.lines.put("CD", new Line(c, d));
		this.lines.put("DA", new Line(d, a));
	}
	
	
	public ChessPanel(int columnIdx, int rowIdx, Point a, Point b, Point c, Point d)
	{
		this.columnIdx = columnIdx;
		this.rowIdx = rowIdx;
		this.vertices.put("A", a);
		this.vertices.put("B", b);
		this.vertices.put("C", c);
		this.vertices.put("D", d);
		this.lines.put("AB", new Line(a, b));
		this.lines.put("BC", new Line(b, c));
		this.lines.put("CD", new Line(c, d));
		this.lines.put("DA", new Line(d, a));
	}
	
	
	public void setColumnIdx(int idx)
	{
		this.columnIdx = idx;
	}
	
	
	public void setRowIdx(int idx)
	{
		this.rowIdx = idx;
	}
	
	
	public int getColumnIdx()
	{
		return this.columnIdx;
	}
	
	
	public int getRowIdx()
	{
		return this.rowIdx;
	}
	
	
	public double getArea()
	{
		Triangle triangle1 = new Triangle(
				this.vertices.get("A"),
				this.vertices.get("B"),
				this.vertices.get("C"));
		Triangle triangle2 = new Triangle(
				this.vertices.get("C"),
				this.vertices.get("D"),
				this.vertices.get("A"));
		return triangle1.area() + triangle2.area();
	}
	
	
	public boolean isPointInsidePanel(Point point)
	{
		Triangle triangleAPD = new Triangle(
				this.vertices.get("A"),
				point,
				this.vertices.get("D"));
		Triangle triangleDPC = new Triangle(
				this.vertices.get("D"),
				point,
				this.vertices.get("C"));
		Triangle triangleCPB = new Triangle(
				this.vertices.get("C"),
				point,
				this.vertices.get("B"));
		Triangle trianglePBA = new Triangle(
				point,
				this.vertices.get("B"),
				this.vertices.get("A"));
		
		double areaAPD = triangleAPD.area();
		double areaDPC = triangleDPC.area();
		double areaCPB = triangleCPB.area();
		double areaPBA = trianglePBA.area();
		
		double areaOfTriagles = areaAPD + areaDPC + areaCPB + areaPBA;
		
		// if size(APD) + size(DPC) + size(CPB) + size(PBA) > size(ABCD)
		// -> Point P is outside
		if (areaOfTriagles > this.getArea())
			return false;
		
		// if size(APD) + size(DPC) + size(CPB) + size(PBA) = size(ABCD)
		// -> Point P lies on one of the panel lines
		// else
		// -> Point P lies inside the panel
		else
			return true;
	}
	
	
	public void printPanel()
	{
		System.out.println("Column: " + this.getColumnIdx() + " Row: " + this.getRowIdx());
	}


	@Override
	public void draw(Graphics graphics)
	{
		for(Map.Entry<String, Line> line : lines.entrySet())
			line.getValue().paintComponent(graphics);
	}
}
