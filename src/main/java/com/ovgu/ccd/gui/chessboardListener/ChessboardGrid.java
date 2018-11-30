package com.ovgu.ccd.gui.chessboardListener;

import com.ovgu.ccd.pieces.Square;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ------------------- NOTATION OF VERTICES ---------------------
// --------------------------------------------------------------
// -----------E---D7---D6---D5---D4---D3---D2---D1---D-----------
// ----------E1--E1D7-E1D6-E1D5-D4E1-D3C7-D2C7-D1C7--C7----------
// ---------E2-E2D7-E2D6--E2D5--D4E2--D3C6--D2C6-D1C6-C6---------
// --------E3-E3D7-E3D6--E3D5---D4E3---D3C5--D2C5-D1C5-C5--------
// -------E4-E4F1-E4F2--E4F3---Center---C4D3--C4D2-C4D1-C4-------
// ------E5----------------------------------------------C5------
// -----E6------------------------------------------------C6-----
// ----E7--------------------------------------------------C7----
// ---F----F1E7---F2E6---F3E5--Center--C3B5---C2B6---C1B7----C---
// ---F1---------------------------------------------------B7----
// ----F2-------------------------------------------------B6-----
// -----F3-----------------------------------------------B5------
// ------F4---------------------------------------------B4-------
// -------F5-------------------------------------------B3--------
// --------F6-----------------------------------------B2---------
// ---------F7---------------------------------------B1----------
// ----------A---A1---A2---A3---A4---A5---A6---A7---B------------
// --------------------------------------------------------------

public class ChessboardGrid extends GeometricPrimitiveDrawer
{
	private static final long serialVersionUID = 1716287489012384473L;
	
	private HashMap<String, Point> points = new HashMap<String, Point>();
	private HashMap<String, Line> lines = new HashMap<String, Line>();
	private HashMap<String, Square> squares = new HashMap<String, Square>();
	private Node<Square> panelRoot = null;
	private Hexagon hexagon = null;
	private ChessboardLabeling labeling = null;

	
	public ChessboardGrid(Point center, int radius)
	{
		this.hexagon = new Hexagon(center, radius);
		this.points = this.hexagon.getVerticesAsMap();
		this.points.put("Center", center);
		init();
	}
	
	
	private void init()
	{
		setupPoints();
		setupLines();
		setupPanels();
		setupPanelTree();
		setupLabeling();
	}


	private void setupLabeling()
	{
		String[] labels = new String[]{
				"A", "B", "C", "D", "E", "F", "G", "H",
				"1", "2", "3", "4", "9", "10", "11", "12",
				"H", "G", "F", "E", "I", "J", "K", "L",
				"12", "11", "10", "9", "5", "6", "7", "8",
				"L", "K", "J", "I", "D", "C", "B", "A",
				"8", "7", "6", "5", "4", "3", "2", "1"};
		this.labeling = new ChessboardLabeling(this.hexagon, labels);
	}
	
	
	private void setupPanels()
	{
		this.squares.put("Section A", new Square(
				this.points.get("A"), this.points.get("A4"),
				this.points.get("Center"), this.points.get("F4")));
		
		this.squares.put("Section B", new Square(
				this.points.get("B"), this.points.get("B4"),
				this.points.get("Center"), this.points.get("A4")));
		
		this.squares.put("Section C", new Square(
				this.points.get("C"), this.points.get("C4"),
				this.points.get("Center"), this.points.get("B4")));
		
		this.squares.put("Section D", new Square(
				this.points.get("D"), this.points.get("D4"),
				this.points.get("Center"), this.points.get("C4")));
		
		this.squares.put("Section E", new Square(
				this.points.get("E"), this.points.get("E4"),
				this.points.get("Center"), this.points.get("D4")));
		
		this.squares.put("Section F", new Square(
				this.points.get("F"), this.points.get("F4"),
				this.points.get("Center"), this.points.get("E4")));
		
		// Sector A
		this.squares.put("Section A1F7", new Square(
				this.points.get("A"), this.points.get("A2"),
				this.points.get("A2F6"), this.points.get("F6")));
		
		this.squares.put("Section A3F7", new Square(
				this.points.get("A2"), this.points.get("A4"),
				this.points.get("A4B2"), this.points.get("A2F6")));
		
		this.squares.put("Section A1F5", new Square(
				this.points.get("F6"), this.points.get("A2F6"),
				this.points.get("F4A2"), this.points.get("F4")));
		
		this.squares.put("Section A3F5", new Square(
				this.points.get("A2F6"), this.points.get("A4B2"),
				this.points.get("Center"), this.points.get("F4A2")));
		
		// Sector B
		this.squares.put("Section B1A7", new Square(
				this.points.get("B"), this.points.get("B2"),
				this.points.get("B2A6"), this.points.get("A6")));
		
		this.squares.put("Section B3A7", new Square(
				this.points.get("B2"), this.points.get("B4"),
				this.points.get("B4C2"), this.points.get("B2A6")));
		
		this.squares.put("Section B1A5", new Square(
				this.points.get("A6"), this.points.get("B2A6"),
				this.points.get("A4B2"), this.points.get("A4")));
		
		this.squares.put("Section B3A5", new Square(
				this.points.get("B2A6"), this.points.get("B4C2"),
				this.points.get("Center"), this.points.get("A4B2")));
		
		// Sector C
		this.squares.put("Section C1B7", new Square(
				this.points.get("C"), this.points.get("C2"),
				this.points.get("C2B6"), this.points.get("B6")));
		
		this.squares.put("Section C3B7", new Square(
				this.points.get("C2"), this.points.get("C4"),
				this.points.get("C4D2"), this.points.get("C2B6")));
		
		this.squares.put("Section C1B5", new Square(
				this.points.get("B6"), this.points.get("C2B6"),
				this.points.get("B4C2"), this.points.get("B4")));
		
		this.squares.put("Section C3B5", new Square(
				this.points.get("C2B6"), this.points.get("C4D2"),
				this.points.get("Center"), this.points.get("B4C2")));
		
		// Sector D
		this.squares.put("Section D1C7", new Square(
				this.points.get("D"), this.points.get("D2"),
				this.points.get("D2C6"), this.points.get("C6")));
		
		this.squares.put("Section D3C7", new Square(
				this.points.get("D2"), this.points.get("D4"),
				this.points.get("D4E2"), this.points.get("D2C6")));
		
		this.squares.put("Section D1C5", new Square(
				this.points.get("C6"), this.points.get("D2C6"),
				this.points.get("C4D2"), this.points.get("C4")));
		
		this.squares.put("Section D3C5", new Square(
				this.points.get("D2C6"), this.points.get("D4E2"),
				this.points.get("Center"), this.points.get("C4D2")));
		
		// Sector E
		this.squares.put("Section E1D7", new Square(
				this.points.get("E"), this.points.get("E2"),
				this.points.get("E2D6"), this.points.get("D6")));
		
		this.squares.put("Section E3D7", new Square(
				this.points.get("E2"), this.points.get("E4"),
				this.points.get("E4F2"), this.points.get("E2D6")));
		
		this.squares.put("Section E1D5", new Square(
				this.points.get("D6"), this.points.get("E2D6"),
				this.points.get("D4E2"), this.points.get("D4")));
		
		this.squares.put("Section E3D5", new Square(
				this.points.get("E2D6"), this.points.get("E4F2"),
				this.points.get("Center"), this.points.get("D4E2")));
		
		// Sector F
		this.squares.put("Section F1E7", new Square(
				this.points.get("F"), this.points.get("F2"),
				this.points.get("F2E6"), this.points.get("E6")));
		
		this.squares.put("Section F3E7", new Square(
				this.points.get("F2"), this.points.get("F4"),
				this.points.get("F4A2"), this.points.get("F2E6")));
		
		this.squares.put("Section F1E5", new Square(
				this.points.get("E6"), this.points.get("F2E6"),
				this.points.get("E4F2"), this.points.get("E4")));
		
		this.squares.put("Section F3E5", new Square(
				this.points.get("F2E6"), this.points.get("F4A2"),
				this.points.get("Center"), this.points.get("E4F2")));
		
		// Panels - Row 0
		this.squares.put("A1", new Square( 0, 0,
				this.points.get("A"), this.points.get("A1"),
				this.points.get("A1F7"), this.points.get("F7")));
		
		this.squares.put("B1", new Square( 1, 0,
				this.points.get("A1"), this.points.get("A2"),
				this.points.get("A2F7"), this.points.get("A1F7")));
		
		this.squares.put("C1", new Square( 2, 0,
				this.points.get("A2"), this.points.get("A3"),
				this.points.get("A3F7"), this.points.get("A2F7")));
		
		this.squares.put("D1", new Square( 3, 0,
				this.points.get("A3"), this.points.get("A4"),
				this.points.get("A4B1"), this.points.get("A3F7")));
	
		this.squares.put("E1", new Square( 4, 0,
				this.points.get("A4"), this.points.get("A5"),
				this.points.get("B1A5"), this.points.get("A4B1")));
		
		this.squares.put("F1", new Square( 5, 0,
				this.points.get("A5"), this.points.get("A6"),
				this.points.get("B1A6"), this.points.get("B1A5")));
		
		this.squares.put("G1", new Square( 6, 0,
				this.points.get("A6"), this.points.get("A7"),
				this.points.get("B1A7"), this.points.get("B1A6")));
		
		this.squares.put("H1", new Square( 7, 0,
				this.points.get("A7"), this.points.get("B"),
				this.points.get("B1"), this.points.get("B1A7")));
		
		// Panels - Row 1
		this.squares.put("A2", new Square( 0, 1,
				this.points.get("F7"), this.points.get("A1F7"),
				this.points.get("A1F6"), this.points.get("F6")));
		
		this.squares.put("B2", new Square( 1, 1,
				this.points.get("A1F7"), this.points.get("A2F7"),
				this.points.get("A2F6"), this.points.get("A1F6")));
		
		this.squares.put("C2", new Square( 2, 1,
				this.points.get("A2F7"), this.points.get("A3F7"),
				this.points.get("A3F6"), this.points.get("A2F6")));
		
		this.squares.put("D2", new Square( 3, 1,
				this.points.get("A3F7"), this.points.get("A4B1"),
				this.points.get("A4B2"), this.points.get("A3F6")));
	
		this.squares.put("E2", new Square( 4, 1,
				this.points.get("A4B1"), this.points.get("B1A5"),
				this.points.get("B2A5"), this.points.get("A4B2")));
		
		this.squares.put("F2", new Square( 5, 1,
				this.points.get("B1A5"), this.points.get("B1A6"),
				this.points.get("B2A6"), this.points.get("B2A5")));
		
		this.squares.put("G2", new Square( 6, 1,
				this.points.get("B1A6"), this.points.get("B1A7"),
				this.points.get("B2A7"), this.points.get("B2A6")));
		
		this.squares.put("H2", new Square( 7, 1,
				this.points.get("B1A7"), this.points.get("B1"),
				this.points.get("B2"), this.points.get("B2A7")));
		
		// Panels - Row 2
		this.squares.put("A3", new Square( 0, 2,
				this.points.get("F6"), this.points.get("A1F6"),
				this.points.get("A1F5"), this.points.get("F5")));
		
		this.squares.put("B3", new Square( 1, 2,
				this.points.get("A1F6"), this.points.get("A2F6"),
				this.points.get("A2F5"), this.points.get("A1F5")));
		
		this.squares.put("C3", new Square( 2, 2,
				this.points.get("A2F6"), this.points.get("A3F6"),
				this.points.get("A3F5"), this.points.get("A2F5")));
		
		this.squares.put("D3", new Square( 3, 2,
				this.points.get("A3F6"), this.points.get("A4B2"),
				this.points.get("A4B3"), this.points.get("A3F5")));
	
		this.squares.put("E3", new Square( 4, 2,
				this.points.get("A4B2"), this.points.get("B2A5"),
				this.points.get("B3A5"), this.points.get("A4B3")));
		
		this.squares.put("F3", new Square( 5, 2,
				this.points.get("B2A5"), this.points.get("B2A6"),
				this.points.get("B3A6"), this.points.get("B3A5")));
		
		this.squares.put("G3", new Square( 6, 2,
				this.points.get("B2A6"), this.points.get("B2A7"),
				this.points.get("B3A7"), this.points.get("B3A6")));
		
		this.squares.put("H3", new Square( 7, 2,
				this.points.get("B2A7"), this.points.get("B2"),
				this.points.get("B3"), this.points.get("B3A7")));
		
		// Panels - Row 3
		this.squares.put("A4", new Square( 0, 3,
				this.points.get("F5"), this.points.get("A1F5"),
				this.points.get("F4A1"), this.points.get("F4")));
		
		this.squares.put("B4", new Square( 1, 3,
				this.points.get("A1F5"), this.points.get("A2F5"),
				this.points.get("F4A2"), this.points.get("F4A1")));
		
		this.squares.put("C4", new Square( 2, 3,
				this.points.get("A2F5"), this.points.get("A3F5"),
				this.points.get("F4A3"), this.points.get("F4A2")));
		
		this.squares.put("D4", new Square( 3, 3,
				this.points.get("A3F5"), this.points.get("A4B3"),
				this.points.get("Center"), this.points.get("F4A3")));
	
		this.squares.put("E4", new Square( 4, 3,
				this.points.get("A4B3"), this.points.get("B3A5"),
				this.points.get("B4C3"), this.points.get("Center")));
		
		this.squares.put("F4", new Square( 5, 3,
				this.points.get("B3A5"), this.points.get("B3A6"),
				this.points.get("B4C2"), this.points.get("B4C3")));
		
		this.squares.put("G4", new Square( 6, 3,
				this.points.get("B3A6"), this.points.get("B3A7"),
				this.points.get("B4C2"), this.points.get("B4C2")));
		
		this.squares.put("H4", new Square( 7, 3,
				this.points.get("B3A7"), this.points.get("B3"),
				this.points.get("B4"), this.points.get("B4C1")));
		
		// Panels - Row 4
		this.squares.put("A5", new Square( 0, 4,
				this.points.get("F4"), this.points.get("F4A1"),
				this.points.get("F3E7"), this.points.get("F3")));
		
		this.squares.put("B5", new Square( 1, 4,
				this.points.get("F4A1"), this.points.get("F4A2"),
				this.points.get("F3E6"), this.points.get("F3E7")));
		
		this.squares.put("C5", new Square( 2, 4,
				this.points.get("F4A2"), this.points.get("F4A3"),
				this.points.get("F3E5"), this.points.get("F3E6")));
		
		this.squares.put("D5", new Square( 3, 4,
				this.points.get("F4A3"), this.points.get("Center"),
				this.points.get("E4F3"), this.points.get("F3E5")));
		
		this.squares.put("I5", new Square( 8, 4,
				this.points.get("Center"), this.points.get("D4E3"),
				this.points.get("E3D5"), this.points.get("E4F3")));
		
		this.squares.put("J5", new Square( 9, 4,
				this.points.get("D4E3"), this.points.get("D4E2"),
				this.points.get("E2D5"), this.points.get("E3D5")));
		
		this.squares.put("K5", new Square( 10, 4,
				this.points.get("D4E2"), this.points.get("D4E1"),
				this.points.get("E1D5"), this.points.get("E2D5")));
		
		this.squares.put("L5", new Square( 11, 4,
				this.points.get("D4E1"), this.points.get("D4"),
				this.points.get("D5"), this.points.get("E1D5")));
		
		// Panels - Row 5
		this.squares.put("A6", new Square( 0, 5,
				this.points.get("F3"), this.points.get("F3E7"),
				this.points.get("F2E7"), this.points.get("F2")));
		
		this.squares.put("B6", new Square( 1, 5,
				this.points.get("F3E7"), this.points.get("F3E6"),
				this.points.get("F2E6"), this.points.get("F2E7")));
		
		this.squares.put("C6", new Square( 2, 5,
				this.points.get("F3E6"), this.points.get("F3E5"),
				this.points.get("F2E5"), this.points.get("F2E6")));
		
		this.squares.put("D6", new Square( 3, 5,
				this.points.get("F3E5"), this.points.get("E4F3"),
				this.points.get("E4F2"), this.points.get("F2E5")));
		
		this.squares.put("I6", new Square( 8, 5,
				this.points.get("E4F3"), this.points.get("E3D5"),
				this.points.get("E3D6"), this.points.get("E4F2")));
		
		this.squares.put("J6", new Square( 9, 5,
				this.points.get("E3D5"), this.points.get("E2D5"),
				this.points.get("E2D6"), this.points.get("E3D6")));
		
		this.squares.put("K6", new Square( 10, 5,
				this.points.get("E2D5"), this.points.get("E1D5"),
				this.points.get("E1D6"), this.points.get("E2D6")));
		
		this.squares.put("L6", new Square( 11, 5,
				this.points.get("E1D5"), this.points.get("D5"),
				this.points.get("D6"), this.points.get("E1D6")));
		
		// Panels - Row 6
		this.squares.put("A7", new Square( 0, 6,
				this.points.get("F2"), this.points.get("F2E7"),
				this.points.get("F1E7"), this.points.get("F1")));
		
		this.squares.put("B7", new Square( 1, 6,
				this.points.get("F2E7"), this.points.get("F2E6"),
				this.points.get("F1E6"), this.points.get("F1E7")));
		
		this.squares.put("C7", new Square( 2, 6,
				this.points.get("F2E6"), this.points.get("F2E5"),
				this.points.get("F1E5"), this.points.get("F1E6")));
		
		this.squares.put("D7", new Square( 3, 6,
				this.points.get("F2E5"), this.points.get("E4F2"),
				this.points.get("E4F1"), this.points.get("F1E5")));
		
		this.squares.put("I7", new Square( 8, 6,
				this.points.get("E4F2"), this.points.get("E3D6"),
				this.points.get("E3D7"), this.points.get("E4F1")));
		
		this.squares.put("J7", new Square( 9, 6,
				this.points.get("E3D6"), this.points.get("E2D6"),
				this.points.get("E2D7"), this.points.get("E3D7")));
		
		this.squares.put("K7", new Square( 10, 6,
				this.points.get("E2D6"), this.points.get("E1D6"),
				this.points.get("E1D7"), this.points.get("E2D7")));
		
		this.squares.put("L7", new Square( 11, 6,
				this.points.get("E1D6"), this.points.get("D6"),
				this.points.get("D7"), this.points.get("E1D7")));
		
		// Panels - Row 7
		this.squares.put("A8", new Square( 0, 7,
				this.points.get("F1"), this.points.get("F1E7"),
				this.points.get("E7"), this.points.get("F")));
		
		this.squares.put("B8", new Square( 1, 7,
				this.points.get("F1E7"), this.points.get("F1E6"),
				this.points.get("E6"), this.points.get("E7")));
		
		this.squares.put("C8", new Square( 2, 7,
				this.points.get("F1E6"), this.points.get("F1E5"),
				this.points.get("E5"), this.points.get("E6")));
		
		this.squares.put("D8", new Square( 3, 7,
				this.points.get("F1E5"), this.points.get("E4F1"),
				this.points.get("E4"), this.points.get("E5")));
		
		this.squares.put("I8", new Square( 8, 7,
				this.points.get("E4F1"), this.points.get("E3D7"),
				this.points.get("E3"), this.points.get("E4")));
		
		this.squares.put("J8", new Square( 9, 7,
				this.points.get("E3D7"), this.points.get("E2D7"),
				this.points.get("E2"), this.points.get("E3")));
		
		this.squares.put("K8", new Square( 10, 7,
				this.points.get("E2D7"), this.points.get("E1D7"),
				this.points.get("E1"), this.points.get("E2")));
		
		this.squares.put("L8", new Square( 11, 7,
				this.points.get("E1D7"), this.points.get("D7"),
				this.points.get("E"), this.points.get("E1")));
		
		// Panels - Row 8
		this.squares.put("H9", new Square( 0, 8,
				this.points.get("B4"), this.points.get("B4C1"),
				this.points.get("C1B5"), this.points.get("B5")));
		
		this.squares.put("G9", new Square( 1, 8,
				this.points.get("B4C1"), this.points.get("B4C2"),
				this.points.get("C2B5"), this.points.get("C1B5")));
		
		this.squares.put("F9", new Square( 2, 8,
				this.points.get("B4C2"), this.points.get("B4C3"),
				this.points.get("C3B5"), this.points.get("C2B5")));
		
		this.squares.put("E9", new Square( 3, 8,
				this.points.get("B4C3"), this.points.get("Center"),
				this.points.get("C4D3"), this.points.get("C3B5")));
		
		this.squares.put("I9", new Square( 8, 8,
				this.points.get("Center"), this.points.get("D4E3"),
				this.points.get("D3C5"), this.points.get("C4D3")));
		
		this.squares.put("J9", new Square( 9, 8,
				this.points.get("D4E3"), this.points.get("D4E2"),
				this.points.get("D3C6"), this.points.get("D3C5")));
		
		this.squares.put("K9", new Square( 10, 8,
				this.points.get("D4E2"), this.points.get("D4E1"),
				this.points.get("D3C7"), this.points.get("D3C6")));
		
		this.squares.put("L9", new Square( 11, 8,
				this.points.get("D4E1"), this.points.get("D4"),
				this.points.get("D3"), this.points.get("D3C7")));
		
		// Panels - Row 9
		this.squares.put("H10", new Square( 0, 9,
				this.points.get("B5"), this.points.get("C1B5"),
				this.points.get("C1B6"), this.points.get("B6")));
		
		this.squares.put("G10", new Square( 1, 9,
				this.points.get("C1B5"), this.points.get("C2B5"),
				this.points.get("C2B6"), this.points.get("C1B6")));
		
		this.squares.put("F10", new Square( 2, 9,
				this.points.get("C2B5"), this.points.get("C3B5"),
				this.points.get("C3B6"), this.points.get("C2B6")));
		
		this.squares.put("E10", new Square( 3, 9,
				this.points.get("C3B5"), this.points.get("C4D3"),
				this.points.get("C4D2"), this.points.get("C2B6")));
		
		this.squares.put("I10", new Square( 8, 9,
				this.points.get("C4D3"), this.points.get("D3C5"),
				this.points.get("D2C5"), this.points.get("C4D2")));
		
		this.squares.put("J10", new Square( 9, 9,
				this.points.get("D3C5"), this.points.get("D3C6"),
				this.points.get("D2C6"), this.points.get("D2C5")));
		
		this.squares.put("K10", new Square( 10, 9,
				this.points.get("D3C6"), this.points.get("D3C7"),
				this.points.get("D2C7"), this.points.get("D2C6")));
		
		this.squares.put("L10", new Square( 11, 9,
				this.points.get("D3C7"), this.points.get("D3"),
				this.points.get("D2"), this.points.get("D2C7")));
		
		// Panels - Row 10
		this.squares.put("H11", new Square( 0, 10,
				this.points.get("B6"), this.points.get("C1B6"),
				this.points.get("C1B7"), this.points.get("B7")));
		
		this.squares.put("G11", new Square( 1, 10,
				this.points.get("C1B6"), this.points.get("C2B6"),
				this.points.get("C2B7"), this.points.get("C1B7")));
		
		this.squares.put("F11", new Square( 2, 10,
				this.points.get("C2B6"), this.points.get("C3B6"),
				this.points.get("C3B7"), this.points.get("C2B7")));
		
		this.squares.put("E11", new Square( 3, 10,
				this.points.get("C3B6"), this.points.get("C4D2"),
				this.points.get("C4D1"), this.points.get("C3B7")));
		
		this.squares.put("I11", new Square( 8, 10,
				this.points.get("C4D2"), this.points.get("D2C5"),
				this.points.get("D1C5"), this.points.get("C4D1")));
		
		this.squares.put("J11", new Square( 9, 10,
				this.points.get("D2C5"), this.points.get("D2C6"),
				this.points.get("D1C6"), this.points.get("D1C5")));
		
		this.squares.put("K11", new Square( 10, 10,
				this.points.get("D2C6"), this.points.get("D2C7"),
				this.points.get("D1C7"), this.points.get("D1C6")));
		
		this.squares.put("L11", new Square( 11, 10,
				this.points.get("D2C7"), this.points.get("D2"),
				this.points.get("D1"), this.points.get("D1C7")));
		
		// Panels - Row 11
		this.squares.put("H12", new Square( 0, 11,
				this.points.get("B7"), this.points.get("C1B7"),
				this.points.get("C1"), this.points.get("C")));
		
		this.squares.put("G12", new Square( 1, 11,
				this.points.get("C1B7"), this.points.get("C2B7"),
				this.points.get("C2"), this.points.get("C1")));
		
		this.squares.put("F12", new Square( 2, 11,
				this.points.get("C2B7"), this.points.get("C3B7"),
				this.points.get("C3"), this.points.get("C2")));
		
		this.squares.put("E12", new Square( 3, 11,
				this.points.get("C3"), this.points.get("C4D1"),
				this.points.get("C4"), this.points.get("C3B7")));
		
		this.squares.put("I12", new Square( 8, 11,
				this.points.get("C4D1"), this.points.get("D1C5"),
				this.points.get("C5"), this.points.get("C4")));
		
		this.squares.put("J12", new Square( 9, 11,
				this.points.get("D1C5"), this.points.get("D1C6"),
				this.points.get("C6"), this.points.get("C5")));
		
		this.squares.put("K12", new Square( 10, 11,
				this.points.get("D1C6"), this.points.get("D1C7"),
				this.points.get("C7"), this.points.get("C6")));
		
		this.squares.put("L12", new Square( 11, 11,
				this.points.get("D1C7"), this.points.get("D1"),
				this.points.get("D"), this.points.get("C7")));
	}
	
	
	private void setupPanelTree()
	{
		// set whole hexagon as the root node
		this.panelRoot = new Node<Square>("root", new Square());
		
		List<Node<Square>> children = new ArrayList<Node<Square>>();
		
		children.add(new Node<Square>("Section A", this.squares.get("Section A")));
		children.add(new Node<Square>("Section B", this.squares.get("Section B")));
		children.add(new Node<Square>("Section C", this.squares.get("Section C")));
		children.add(new Node<Square>("Section D", this.squares.get("Section D")));
		children.add(new Node<Square>("Section E", this.squares.get("Section E")));
		children.add(new Node<Square>("Section F", this.squares.get("Section F")));
		this.panelRoot.addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("Section A1F7", this.squares.get("Section A1F7")));
		children.add(new Node<Square>("Section A3F7", this.squares.get("Section A3F7")));
		children.add(new Node<Square>("Section A1F5", this.squares.get("Section A1F5")));
		children.add(new Node<Square>("Section A3F5", this.squares.get("Section A3F5")));
		this.panelRoot.getChild("Section A").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("Section B1A7", this.squares.get("Section B1A7")));
		children.add(new Node<Square>("Section B3A7", this.squares.get("Section B3A7")));
		children.add(new Node<Square>("Section B1A5", this.squares.get("Section B1A5")));
		children.add(new Node<Square>("Section B3A5", this.squares.get("Section B3A5")));
		this.panelRoot.getChild("Section B").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("Section C1B7", this.squares.get("Section C1B7")));
		children.add(new Node<Square>("Section C3B7", this.squares.get("Section C3B7")));
		children.add(new Node<Square>("Section C1B5", this.squares.get("Section C1B5")));
		children.add(new Node<Square>("Section C3B5", this.squares.get("Section C3B5")));
		this.panelRoot.getChild("Section C").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("Section D1C7", this.squares.get("Section D1C7")));
		children.add(new Node<Square>("Section D3C7", this.squares.get("Section D3C7")));
		children.add(new Node<Square>("Section D1C5", this.squares.get("Section D1C5")));
		children.add(new Node<Square>("Section D3C5", this.squares.get("Section D3C5")));
		this.panelRoot.getChild("Section D").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("Section E1D7", this.squares.get("Section E1D7")));
		children.add(new Node<Square>("Section E3D7", this.squares.get("Section E3D7")));
		children.add(new Node<Square>("Section E1D5", this.squares.get("Section E1D5")));
		children.add(new Node<Square>("Section E3D5", this.squares.get("Section E3D5")));
		this.panelRoot.getChild("Section E").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("Section F1E7", this.squares.get("Section F1E7")));
		children.add(new Node<Square>("Section F3E7", this.squares.get("Section F3E7")));
		children.add(new Node<Square>("Section F1E5", this.squares.get("Section F1E5")));
		children.add(new Node<Square>("Section F3E5", this.squares.get("Section F3E5")));
		this.panelRoot.getChild("Section F").addChildren(children);
		children.clear();
		
		
		children.add(new Node<Square>("A1", this.squares.get("A1")));
		children.add(new Node<Square>("B1", this.squares.get("B1")));
		children.add(new Node<Square>("A2", this.squares.get("A2")));
		children.add(new Node<Square>("B2", this.squares.get("B2")));
		this.panelRoot.getNode("Section A1F7").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("C1", this.squares.get("C1")));
		children.add(new Node<Square>("D1", this.squares.get("D1")));
		children.add(new Node<Square>("C2", this.squares.get("C2")));
		children.add(new Node<Square>("D2", this.squares.get("D2")));
		this.panelRoot.getNode("Section A3F7").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("E1", this.squares.get("E1")));
		children.add(new Node<Square>("F1", this.squares.get("F1")));
		children.add(new Node<Square>("E2", this.squares.get("E2")));
		children.add(new Node<Square>("F2", this.squares.get("F2")));
		this.panelRoot.getNode("Section B1A5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("G1", this.squares.get("G1")));
		children.add(new Node<Square>("H1", this.squares.get("H1")));
		children.add(new Node<Square>("G2", this.squares.get("G2")));
		children.add(new Node<Square>("H2", this.squares.get("H2")));
		this.panelRoot.getNode("Section B1A7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<Square>("A3", this.squares.get("A3")));
		children.add(new Node<Square>("B3", this.squares.get("B3")));
		children.add(new Node<Square>("A4", this.squares.get("A4")));
		children.add(new Node<Square>("B4", this.squares.get("B4")));
		this.panelRoot.getNode("Section A1F5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("C3", this.squares.get("C3")));
		children.add(new Node<Square>("D3", this.squares.get("D3")));
		children.add(new Node<Square>("C4", this.squares.get("C4")));
		children.add(new Node<Square>("D4", this.squares.get("D4")));
		this.panelRoot.getNode("Section A3F5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("E3", this.squares.get("E3")));
		children.add(new Node<Square>("F3", this.squares.get("F3")));
		children.add(new Node<Square>("E4", this.squares.get("E4")));
		children.add(new Node<Square>("F4", this.squares.get("F4")));
		this.panelRoot.getNode("Section B3A5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("G3", this.squares.get("G3")));
		children.add(new Node<Square>("H3", this.squares.get("H3")));
		children.add(new Node<Square>("G4", this.squares.get("G4")));
		children.add(new Node<Square>("H4", this.squares.get("H4")));
		this.panelRoot.getNode("Section B3A7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<Square>("A5", this.squares.get("A5")));
		children.add(new Node<Square>("B5", this.squares.get("B5")));
		children.add(new Node<Square>("A6", this.squares.get("A6")));
		children.add(new Node<Square>("B6", this.squares.get("B6")));
		this.panelRoot.getNode("Section F3E7").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("C5", this.squares.get("C5")));
		children.add(new Node<Square>("D5", this.squares.get("D5")));
		children.add(new Node<Square>("C6", this.squares.get("C6")));
		children.add(new Node<Square>("D6", this.squares.get("D6")));
		this.panelRoot.getNode("Section F3E5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("I5", this.squares.get("I5")));
		children.add(new Node<Square>("J5", this.squares.get("J5")));
		children.add(new Node<Square>("I6", this.squares.get("I6")));
		children.add(new Node<Square>("J6", this.squares.get("J6")));
		this.panelRoot.getNode("Section E3D5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("K5", this.squares.get("K5")));
		children.add(new Node<Square>("L5", this.squares.get("L5")));
		children.add(new Node<Square>("K6", this.squares.get("K6")));
		children.add(new Node<Square>("L6", this.squares.get("L6")));
		this.panelRoot.getNode("Section E1D5").addChildren(children);
		children.clear();
		
		
		children.add(new Node<Square>("A7", this.squares.get("A7")));
		children.add(new Node<Square>("B7", this.squares.get("B7")));
		children.add(new Node<Square>("A8", this.squares.get("A8")));
		children.add(new Node<Square>("B8", this.squares.get("B8")));
		this.panelRoot.getNode("Section F1E7").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("C7", this.squares.get("C7")));
		children.add(new Node<Square>("D7", this.squares.get("D7")));
		children.add(new Node<Square>("C8", this.squares.get("C8")));
		children.add(new Node<Square>("D8", this.squares.get("D8")));
		this.panelRoot.getNode("Section F1E5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("I7", this.squares.get("I7")));
		children.add(new Node<Square>("J7", this.squares.get("J7")));
		children.add(new Node<Square>("I8", this.squares.get("I8")));
		children.add(new Node<Square>("J8", this.squares.get("J8")));
		this.panelRoot.getNode("Section E3D7").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("K7", this.squares.get("K7")));
		children.add(new Node<Square>("L7", this.squares.get("L7")));
		children.add(new Node<Square>("K8", this.squares.get("K8")));
		children.add(new Node<Square>("L8", this.squares.get("L8")));
		this.panelRoot.getNode("Section E1D7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<Square>("H9", this.squares.get("H9")));
		children.add(new Node<Square>("G9", this.squares.get("G9")));
		children.add(new Node<Square>("H10", this.squares.get("H10")));
		children.add(new Node<Square>("G10", this.squares.get("G10")));
		this.panelRoot.getNode("Section C1B5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("F9", this.squares.get("F9")));
		children.add(new Node<Square>("E9", this.squares.get("E9")));
		children.add(new Node<Square>("F10", this.squares.get("F10")));
		children.add(new Node<Square>("E10", this.squares.get("E10")));
		this.panelRoot.getNode("Section C3B5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("I9", this.squares.get("I9")));
		children.add(new Node<Square>("J9", this.squares.get("J9")));
		children.add(new Node<Square>("I10", this.squares.get("I10")));
		children.add(new Node<Square>("J10", this.squares.get("J10")));
		this.panelRoot.getNode("Section D3C5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("K9", this.squares.get("K9")));
		children.add(new Node<Square>("L9", this.squares.get("L9")));
		children.add(new Node<Square>("K10", this.squares.get("K10")));
		children.add(new Node<Square>("L10", this.squares.get("L10")));
		this.panelRoot.getNode("Section D3C7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<Square>("H11", this.squares.get("H11")));
		children.add(new Node<Square>("G11", this.squares.get("G11")));
		children.add(new Node<Square>("H12", this.squares.get("H12")));
		children.add(new Node<Square>("G12", this.squares.get("G12")));
		this.panelRoot.getNode("Section C1B7").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("F11", this.squares.get("F11")));
		children.add(new Node<Square>("E11", this.squares.get("E11")));
		children.add(new Node<Square>("F12", this.squares.get("F12")));
		children.add(new Node<Square>("E12", this.squares.get("E12")));
		this.panelRoot.getNode("Section C3B7").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("I11", this.squares.get("I11")));
		children.add(new Node<Square>("J11", this.squares.get("J11")));
		children.add(new Node<Square>("I12", this.squares.get("I12")));
		children.add(new Node<Square>("J12", this.squares.get("J12")));
		this.panelRoot.getNode("Section D1C5").addChildren(children);
		children.clear();
		
		children.add(new Node<Square>("K11", this.squares.get("K11")));
		children.add(new Node<Square>("L11", this.squares.get("L11")));
		children.add(new Node<Square>("K12", this.squares.get("K12")));
		children.add(new Node<Square>("L12", this.squares.get("L12")));
		this.panelRoot.getNode("Section D1C7").addChildren(children);
		children.clear();
	}
	
	
	private void setupPoints()
	{
		points.put("A4", this.points.get("A").getMeanPointBetween(this.points.get("B")));
		points.put("B4", this.points.get("B").getMeanPointBetween(this.points.get("C")));
		points.put("C4", this.points.get("C").getMeanPointBetween(this.points.get("D")));
		points.put("D4", this.points.get("D").getMeanPointBetween(this.points.get("E")));
		points.put("E4", this.points.get("E").getMeanPointBetween(this.points.get("F")));
		points.put("F4", this.points.get("F").getMeanPointBetween(this.points.get("A")));
		
		points.put("A2", this.points.get("A").getMeanPointBetween(this.points.get("A4")));
		points.put("B2", this.points.get("B").getMeanPointBetween(this.points.get("B4")));
		points.put("C2", this.points.get("C").getMeanPointBetween(this.points.get("C4")));
		points.put("D2", this.points.get("D").getMeanPointBetween(this.points.get("D4")));
		points.put("E2", this.points.get("E").getMeanPointBetween(this.points.get("E4")));
		points.put("F2", this.points.get("F").getMeanPointBetween(this.points.get("F4")));
		
		points.put("A1", this.points.get("A").getMeanPointBetween(this.points.get("A2")));
		points.put("B1", this.points.get("B").getMeanPointBetween(this.points.get("B2")));
		points.put("C1", this.points.get("C").getMeanPointBetween(this.points.get("C2")));
		points.put("D1", this.points.get("D").getMeanPointBetween(this.points.get("D2")));
		points.put("E1", this.points.get("E").getMeanPointBetween(this.points.get("E2")));
		points.put("F1", this.points.get("F").getMeanPointBetween(this.points.get("F2")));
		
		points.put("A3", this.points.get("A2").getMeanPointBetween(this.points.get("A4")));
		points.put("B3", this.points.get("B2").getMeanPointBetween(this.points.get("B4")));
		points.put("C3", this.points.get("C2").getMeanPointBetween(this.points.get("C4")));
		points.put("D3", this.points.get("D2").getMeanPointBetween(this.points.get("D4")));
		points.put("E3", this.points.get("E2").getMeanPointBetween(this.points.get("E4")));
		points.put("F3", this.points.get("F2").getMeanPointBetween(this.points.get("F4")));
			
		points.put("A6", this.points.get("A4").getMeanPointBetween(this.points.get("B")));
		points.put("B6", this.points.get("B4").getMeanPointBetween(this.points.get("C")));
		points.put("C6", this.points.get("C4").getMeanPointBetween(this.points.get("D")));
		points.put("D6", this.points.get("D4").getMeanPointBetween(this.points.get("E")));
		points.put("E6", this.points.get("E4").getMeanPointBetween(this.points.get("F")));
		points.put("F6", this.points.get("F4").getMeanPointBetween(this.points.get("A")));
		
		points.put("A5", this.points.get("A4").getMeanPointBetween(this.points.get("A6")));
		points.put("B5", this.points.get("B4").getMeanPointBetween(this.points.get("B6")));
		points.put("C5", this.points.get("C4").getMeanPointBetween(this.points.get("C6")));
		points.put("D5", this.points.get("D4").getMeanPointBetween(this.points.get("D6")));
		points.put("E5", this.points.get("E4").getMeanPointBetween(this.points.get("E6")));
		points.put("F5", this.points.get("F4").getMeanPointBetween(this.points.get("F6")));
		
		points.put("A7", this.points.get("A6").getMeanPointBetween(this.points.get("B")));
		points.put("B7", this.points.get("B6").getMeanPointBetween(this.points.get("C")));
		points.put("C7", this.points.get("C6").getMeanPointBetween(this.points.get("D")));
		points.put("D7", this.points.get("D6").getMeanPointBetween(this.points.get("E")));
		points.put("E7", this.points.get("E6").getMeanPointBetween(this.points.get("F")));
		points.put("F7", this.points.get("F6").getMeanPointBetween(this.points.get("A")));
		
		points.put("A4B2", this.points.get("A4").getMeanPointBetween(this.points.get("Center")));
		points.put("B4C2", this.points.get("B4").getMeanPointBetween(this.points.get("Center")));
		points.put("C4D2", this.points.get("C4").getMeanPointBetween(this.points.get("Center")));
		points.put("D4E2", this.points.get("D4").getMeanPointBetween(this.points.get("Center")));
		points.put("E4F2", this.points.get("E4").getMeanPointBetween(this.points.get("Center")));
		points.put("F4A2", this.points.get("F4").getMeanPointBetween(this.points.get("Center")));
		
		points.put("A4B3", this.points.get("A4B2").getMeanPointBetween(this.points.get("Center")));
		points.put("B4C3", this.points.get("B4C2").getMeanPointBetween(this.points.get("Center")));
		points.put("C4D3", this.points.get("C4D2").getMeanPointBetween(this.points.get("Center")));
		points.put("D4E3", this.points.get("D4E2").getMeanPointBetween(this.points.get("Center")));
		points.put("E4F3", this.points.get("E4F2").getMeanPointBetween(this.points.get("Center")));
		points.put("F4A3", this.points.get("F4A2").getMeanPointBetween(this.points.get("Center")));
		
		points.put("A4B1", this.points.get("A4").getMeanPointBetween(this.points.get("A4B2")));
		points.put("B4C1", this.points.get("B4").getMeanPointBetween(this.points.get("B4C2")));
		points.put("C4D1", this.points.get("C4").getMeanPointBetween(this.points.get("C4D2")));
		points.put("D4E1", this.points.get("D4").getMeanPointBetween(this.points.get("D4E2")));
		points.put("E4F1", this.points.get("E4").getMeanPointBetween(this.points.get("E4F2")));
		points.put("F4A1", this.points.get("F4").getMeanPointBetween(this.points.get("F4A2")));
		
		points.put("A1F6", this.points.get("A1").getMeanPointBetween(this.points.get("F4A1")));
		points.put("B1A6", this.points.get("B1").getMeanPointBetween(this.points.get("A4B1")));
		points.put("C1B6", this.points.get("C1").getMeanPointBetween(this.points.get("B4C1")));
		points.put("D1C6", this.points.get("D1").getMeanPointBetween(this.points.get("C4D1")));
		points.put("E1D6", this.points.get("E1").getMeanPointBetween(this.points.get("D4E1")));
		points.put("F1E6", this.points.get("F1").getMeanPointBetween(this.points.get("E4F1")));
		
		points.put("A2F6", this.points.get("A2").getMeanPointBetween(this.points.get("F4A2")));
		points.put("B2A6", this.points.get("B2").getMeanPointBetween(this.points.get("A4B2")));
		points.put("C2B6", this.points.get("C2").getMeanPointBetween(this.points.get("B4C2")));
		points.put("D2C6", this.points.get("D2").getMeanPointBetween(this.points.get("C4D2")));
		points.put("E2D6", this.points.get("E2").getMeanPointBetween(this.points.get("D4E2")));
		points.put("F2E6", this.points.get("F2").getMeanPointBetween(this.points.get("E4F2")));
		
		points.put("A3F6", this.points.get("A3").getMeanPointBetween(this.points.get("F4A3")));
		points.put("B3A6", this.points.get("B3").getMeanPointBetween(this.points.get("A4B3")));
		points.put("C3B6", this.points.get("C3").getMeanPointBetween(this.points.get("B4C3")));
		points.put("D3C6", this.points.get("D3").getMeanPointBetween(this.points.get("C4D3")));
		points.put("E3D6", this.points.get("E3").getMeanPointBetween(this.points.get("D4E3")));
		points.put("F3E6", this.points.get("F3").getMeanPointBetween(this.points.get("E4F3")));
		
		points.put("A1F5", this.points.get("A1F6").getMeanPointBetween(this.points.get("F4A1")));
		points.put("B1A5", this.points.get("B1A6").getMeanPointBetween(this.points.get("A4B1")));
		points.put("C1B5", this.points.get("C1B6").getMeanPointBetween(this.points.get("B4C1")));
		points.put("D1C5", this.points.get("D1C6").getMeanPointBetween(this.points.get("C4D1")));
		points.put("E1D5", this.points.get("E1D6").getMeanPointBetween(this.points.get("D4E1")));
		points.put("F1E5", this.points.get("F1E6").getMeanPointBetween(this.points.get("E4F1")));
		
		points.put("A2F5", this.points.get("F4A2").getMeanPointBetween(this.points.get("A2F6")));
		points.put("B2A5", this.points.get("A4B2").getMeanPointBetween(this.points.get("B2A6")));
		points.put("C2B5", this.points.get("B4C2").getMeanPointBetween(this.points.get("C2B6")));
		points.put("D2C5", this.points.get("C4D2").getMeanPointBetween(this.points.get("D2C6")));
		points.put("E2D5", this.points.get("D4E2").getMeanPointBetween(this.points.get("E2D6")));
		points.put("F2E5", this.points.get("E4F2").getMeanPointBetween(this.points.get("F2E6")));
		
		points.put("A3F5", this.points.get("F4A3").getMeanPointBetween(this.points.get("A3F6")));
		points.put("B3A5", this.points.get("A4B3").getMeanPointBetween(this.points.get("B3A6")));
		points.put("C3B5", this.points.get("B4C3").getMeanPointBetween(this.points.get("C3B6")));
		points.put("D3C5", this.points.get("C4D3").getMeanPointBetween(this.points.get("D3C6")));
		points.put("E3D5", this.points.get("D4E3").getMeanPointBetween(this.points.get("E3D6")));
		points.put("F3E5", this.points.get("E4F3").getMeanPointBetween(this.points.get("F3E6")));
		
		points.put("A1F7", this.points.get("A1").getMeanPointBetween(this.points.get("A1F6")));
		points.put("B1A7", this.points.get("B1").getMeanPointBetween(this.points.get("B1A6")));
		points.put("C1B7", this.points.get("C1").getMeanPointBetween(this.points.get("C1B6")));
		points.put("D1C7", this.points.get("D1").getMeanPointBetween(this.points.get("D1C6")));
		points.put("E1D7", this.points.get("E1").getMeanPointBetween(this.points.get("E1D6")));
		points.put("F1E7", this.points.get("F1").getMeanPointBetween(this.points.get("F1E6")));
		
		points.put("A2F7", this.points.get("A2").getMeanPointBetween(this.points.get("A2F6")));
		points.put("B2A7", this.points.get("B2").getMeanPointBetween(this.points.get("B2A6")));
		points.put("C2B7", this.points.get("C2").getMeanPointBetween(this.points.get("C2B6")));
		points.put("D2C7", this.points.get("D2").getMeanPointBetween(this.points.get("D2C6")));
		points.put("E2D7", this.points.get("E2").getMeanPointBetween(this.points.get("E2D6")));
		points.put("F2E7", this.points.get("F2").getMeanPointBetween(this.points.get("F2E6")));
		
		points.put("A3F7", this.points.get("A3").getMeanPointBetween(this.points.get("A3F6")));
		points.put("B3A7", this.points.get("B3").getMeanPointBetween(this.points.get("B3A6")));
		points.put("C3B7", this.points.get("C3").getMeanPointBetween(this.points.get("C3B6")));
		points.put("D3C7", this.points.get("D3").getMeanPointBetween(this.points.get("D3C6")));
		points.put("E3D7", this.points.get("E3").getMeanPointBetween(this.points.get("E3D6")));
		points.put("F3E7", this.points.get("F3").getMeanPointBetween(this.points.get("F3E6")));
	}
	

	private void setupLines()
	{
		lines.put("A_B", new Line(this.points.get("A"), this.points.get("B")));
		lines.put("B_C", new Line(this.points.get("B"), this.points.get("C")));
		lines.put("C_D", new Line(this.points.get("C"), this.points.get("D")));
		lines.put("D_E", new Line(this.points.get("D"), this.points.get("E")));
		lines.put("E_F", new Line(this.points.get("E"), this.points.get("F")));
		lines.put("F_A", new Line(this.points.get("F"), this.points.get("A")));

		lines.put("A1_F4A1", new Line(this.points.get("A1"), this.points.get("F4A1")));
		lines.put("B1_A4B1", new Line(this.points.get("B1"), this.points.get("A4B1")));
		lines.put("C1_B4C1", new Line(this.points.get("C1"), this.points.get("B4C1")));
		lines.put("D1_C4D1", new Line(this.points.get("D1"), this.points.get("C4D1")));
		lines.put("E1_D4E1", new Line(this.points.get("E1"), this.points.get("D4E1")));
		lines.put("F1_E4F1", new Line(this.points.get("F1"), this.points.get("E4F1")));
		
		lines.put("A2_F4A2", new Line(this.points.get("A2"), this.points.get("F4A2")));
		lines.put("B2_A4B2", new Line(this.points.get("B2"), this.points.get("A4B2")));
		lines.put("C2_B4C2", new Line(this.points.get("C2"), this.points.get("B4C2")));
		lines.put("D2_C4D2", new Line(this.points.get("D2"), this.points.get("C4D2")));
		lines.put("E2_D4E2", new Line(this.points.get("E2"), this.points.get("D4E2")));
		lines.put("F2_E4F2", new Line(this.points.get("F2"), this.points.get("E4F2")));
		
		lines.put("A3_F4A3", new Line(this.points.get("A3"), this.points.get("F4A3")));
		lines.put("B3_A4B3", new Line(this.points.get("B3"), this.points.get("A4B3")));
		lines.put("C3_B4C3", new Line(this.points.get("C3"), this.points.get("B4C3")));
		lines.put("D3_C4D3", new Line(this.points.get("D3"), this.points.get("C4D3")));
		lines.put("E3_D4E3", new Line(this.points.get("E3"), this.points.get("D4E3")));
		lines.put("F3_E4F3", new Line(this.points.get("F3"), this.points.get("E4F3")));
		
		lines.put("A4_Center", new Line(this.points.get("A4"), this.points.get("Center")));
		lines.put("B4_Center", new Line(this.points.get("B4"), this.points.get("Center")));
		lines.put("C4_Center", new Line(this.points.get("C4"), this.points.get("Center")));
		lines.put("D4_Center", new Line(this.points.get("D4"), this.points.get("Center")));
		lines.put("E4_Center", new Line(this.points.get("E4"), this.points.get("Center")));
		lines.put("F4_Center", new Line(this.points.get("F4"), this.points.get("Center")));
		
		lines.put("A5_F4A5", new Line(this.points.get("A5"), this.points.get("B4C3")));
		lines.put("B5_A4B5", new Line(this.points.get("B5"), this.points.get("C4D3")));
		lines.put("C5_B4C5", new Line(this.points.get("C5"), this.points.get("D4E3")));
		lines.put("D5_C4D5", new Line(this.points.get("D5"), this.points.get("E4F3")));
		lines.put("E5_D4E5", new Line(this.points.get("E5"), this.points.get("F4A3")));
		lines.put("F5_E4F5", new Line(this.points.get("F5"), this.points.get("A4B3")));
		
		lines.put("A6_F4A6", new Line(this.points.get("A6"), this.points.get("B4C2")));
		lines.put("B6_A4B6", new Line(this.points.get("B6"), this.points.get("C4D2")));
		lines.put("C6_B4C6", new Line(this.points.get("C6"), this.points.get("D4E2")));
		lines.put("D6_C4D6", new Line(this.points.get("D6"), this.points.get("E4F2")));
		lines.put("E6_D4E6", new Line(this.points.get("E6"), this.points.get("F4A2")));
		lines.put("F6_E4F6", new Line(this.points.get("F6"), this.points.get("A4B2")));
		
		lines.put("A7_F4A7", new Line(this.points.get("A7"), this.points.get("B4C1")));
		lines.put("B7_A4B7", new Line(this.points.get("B7"), this.points.get("C4D1")));
		lines.put("C7_B4C7", new Line(this.points.get("C7"), this.points.get("D4E1")));
		lines.put("D7_C4D7", new Line(this.points.get("D7"), this.points.get("E4F1")));
		lines.put("E7_D4E7", new Line(this.points.get("E7"), this.points.get("F4A1")));
		lines.put("F7_E4F7", new Line(this.points.get("F7"), this.points.get("A4B1")));
	}

	// @param:  name of the panel regarding the labeling convention (eg.: D7, A3 ...)
	// @return: certain chessPanel object
	public Square getSquares(String name)
	{
		if (this.squares.containsKey(name))
			return this.squares.get(name);
		return null;
	}


	public Square getSquare(int boardPosX, int boardPosY)
	{
		for(Map.Entry<String, Square> square : this.squares.entrySet())
			if (square.getValue().getPosX() == boardPosX &&
				square.getValue().getPosY() == boardPosY)
				return square.getValue();
		return null;
	}


	// @return: Square or NULL if the point isnt within any panel
	public Square getSquareWithinPoint(Point point)
	{
		if (this.hexagon.isPointWithinHexagon(point))
			return breadthFirstSearch(point);
		return null;
	}
	
	
	private Square breadthFirstSearch(Point point)
	{
		return searchInTreeLevel(this.panelRoot, point);
	}
	
	
	private Square searchInTreeLevel(Node<Square> parentNode, Point point)
	{
		List<Node<Square>> childNodes = parentNode.getChildren();
		
		for (Node<Square> node : childNodes)
		{
			if (node.getData().isPointInside(point))
			{
				if (node.hasChildren())
					return searchInTreeLevel(node, point);
				else
					return node.getData();
			}
		}
		return null;
	}


	@Override
	public void draw(Graphics graphics)
	{
		for(Map.Entry<String, Line> line : lines.entrySet())
			line.getValue().paintComponent(graphics);
		
		for(Map.Entry<String, Point> point : points.entrySet())
			point.getValue().paintComponent(graphics);
      
		this.labeling.draw(graphics);
	}
}
