package com.ovgu.ccd.gui.chessboardListener;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private HashMap<String, ChessPanel> panels = new HashMap<String, ChessPanel>();
	private Node<ChessPanel> panelRoot = null;
	private Hexagon hexagon = null;

	
	public ChessboardGrid(Point center, int radius)
	{
		this.hexagon = new Hexagon(center, radius);
		this.points = this.hexagon.getVertices();
		this.points.put("Center", center);
		init();
	}
	
	
	private void init()
	{
		setupPoints();
		setupLines();
		setupPanels();
		setupPanelTree();
	}
	
	
	private void setupPanels()
	{
		this.panels.put("Section A", new ChessPanel(
				this.points.get("A"), this.points.get("A4"),
				this.points.get("Center"), this.points.get("F4")));
		
		this.panels.put("Section B", new ChessPanel(
				this.points.get("B"), this.points.get("B4"),
				this.points.get("Center"), this.points.get("A4")));
		
		this.panels.put("Section C", new ChessPanel(
				this.points.get("C"), this.points.get("C4"),
				this.points.get("Center"), this.points.get("B4")));
		
		this.panels.put("Section D", new ChessPanel(
				this.points.get("D"), this.points.get("D4"),
				this.points.get("Center"), this.points.get("C4")));
		
		this.panels.put("Section E", new ChessPanel(
				this.points.get("E"), this.points.get("E4"),
				this.points.get("Center"), this.points.get("D4")));
		
		this.panels.put("Section F", new ChessPanel(
				this.points.get("F"), this.points.get("F4"),
				this.points.get("Center"), this.points.get("E4")));
		
		// Sector A
		this.panels.put("Section A1F7", new ChessPanel(
				this.points.get("A"), this.points.get("A2"),
				this.points.get("A2F6"), this.points.get("F6")));
		
		this.panels.put("Section A3F7", new ChessPanel(
				this.points.get("A2"), this.points.get("A4"),
				this.points.get("A4B2"), this.points.get("A2F6")));
		
		this.panels.put("Section A1F5", new ChessPanel(
				this.points.get("F6"), this.points.get("A2F6"),
				this.points.get("F4A2"), this.points.get("F4")));
		
		this.panels.put("Section A3F5", new ChessPanel(
				this.points.get("A2F6"), this.points.get("A4B2"),
				this.points.get("Center"), this.points.get("F4A2")));
		
		// Sector B
		this.panels.put("Section B1A7", new ChessPanel(
				this.points.get("B"), this.points.get("B2"),
				this.points.get("B2A6"), this.points.get("A6")));
		
		this.panels.put("Section B3A7", new ChessPanel(
				this.points.get("B2"), this.points.get("B4"),
				this.points.get("B4C2"), this.points.get("B2A6")));
		
		this.panels.put("Section B1A5", new ChessPanel(
				this.points.get("A6"), this.points.get("B2A6"),
				this.points.get("A4B2"), this.points.get("A4")));
		
		this.panels.put("Section B3A5", new ChessPanel(
				this.points.get("B2A6"), this.points.get("B4C2"),
				this.points.get("Center"), this.points.get("A4B2")));
		
		// Sector C
		this.panels.put("Section C1B7", new ChessPanel(
				this.points.get("C"), this.points.get("C2"),
				this.points.get("C2B6"), this.points.get("B6")));
		
		this.panels.put("Section C3B7", new ChessPanel(
				this.points.get("C2"), this.points.get("C4"),
				this.points.get("C4D2"), this.points.get("C2B6")));
		
		this.panels.put("Section C1B5", new ChessPanel(
				this.points.get("B6"), this.points.get("C2B6"),
				this.points.get("B4C2"), this.points.get("B4")));
		
		this.panels.put("Section C3B5", new ChessPanel(
				this.points.get("C2B6"), this.points.get("C4D2"),
				this.points.get("Center"), this.points.get("B4C2")));
		
		// Sector D
		this.panels.put("Section D1C7", new ChessPanel(
				this.points.get("D"), this.points.get("D2"),
				this.points.get("D2C6"), this.points.get("C6")));
		
		this.panels.put("Section D3C7", new ChessPanel(
				this.points.get("D2"), this.points.get("D4"),
				this.points.get("D4E2"), this.points.get("D2C6")));
		
		this.panels.put("Section D1C5", new ChessPanel(
				this.points.get("C6"), this.points.get("D2C6"),
				this.points.get("C4D2"), this.points.get("C4")));
		
		this.panels.put("Section D3C5", new ChessPanel(
				this.points.get("D2C6"), this.points.get("D4E2"),
				this.points.get("Center"), this.points.get("C4D2")));
		
		// Sector E
		this.panels.put("Section E1D7", new ChessPanel(
				this.points.get("E"), this.points.get("E2"),
				this.points.get("E2D6"), this.points.get("D6")));
		
		this.panels.put("Section E3D7", new ChessPanel(
				this.points.get("E2"), this.points.get("E4"),
				this.points.get("E4F2"), this.points.get("E2D6")));
		
		this.panels.put("Section E1D5", new ChessPanel(
				this.points.get("D6"), this.points.get("E2D6"),
				this.points.get("D4E2"), this.points.get("D4")));
		
		this.panels.put("Section E3D5", new ChessPanel(
				this.points.get("E2D6"), this.points.get("E4F2"),
				this.points.get("Center"), this.points.get("D4E2")));
		
		// Sector F
		this.panels.put("Section F1E7", new ChessPanel(
				this.points.get("F"), this.points.get("F2"),
				this.points.get("F2E6"), this.points.get("E6")));
		
		this.panels.put("Section F3E7", new ChessPanel(
				this.points.get("F2"), this.points.get("F4"),
				this.points.get("F4A2"), this.points.get("F2E6")));
		
		this.panels.put("Section F1E5", new ChessPanel(
				this.points.get("E6"), this.points.get("F2E6"),
				this.points.get("E4F2"), this.points.get("E4")));
		
		this.panels.put("Section F3E5", new ChessPanel(
				this.points.get("F2E6"), this.points.get("F4A2"),
				this.points.get("Center"), this.points.get("E4F2")));
		
		// Panels - Row 0
		this.panels.put("A1", new ChessPanel( 0, 0,
				this.points.get("A"), this.points.get("A1"),
				this.points.get("A1F7"), this.points.get("F7")));
		
		this.panels.put("B1", new ChessPanel( 1, 0,
				this.points.get("A1"), this.points.get("A2"),
				this.points.get("A2F7"), this.points.get("A1F7")));
		
		this.panels.put("C1", new ChessPanel( 2, 0,
				this.points.get("A2"), this.points.get("A3"),
				this.points.get("A3F7"), this.points.get("A2F7")));
		
		this.panels.put("D1", new ChessPanel( 3, 0,
				this.points.get("A3"), this.points.get("A4"),
				this.points.get("A4B1"), this.points.get("A3F7")));
	
		this.panels.put("E1", new ChessPanel( 4, 0,
				this.points.get("A4"), this.points.get("A5"),
				this.points.get("B1A5"), this.points.get("A4B1")));
		
		this.panels.put("F1", new ChessPanel( 5, 0,
				this.points.get("A5"), this.points.get("A6"),
				this.points.get("B1A6"), this.points.get("B1A5")));
		
		this.panels.put("G1", new ChessPanel( 6, 0,
				this.points.get("A6"), this.points.get("A7"),
				this.points.get("B1A7"), this.points.get("B1A6")));
		
		this.panels.put("H1", new ChessPanel( 7, 0,
				this.points.get("A7"), this.points.get("B"),
				this.points.get("B1"), this.points.get("B1A7")));
		
		// Panels - Row 1
		this.panels.put("A2", new ChessPanel( 0, 1,
				this.points.get("F7"), this.points.get("A1F7"),
				this.points.get("A1F6"), this.points.get("F6")));
		
		this.panels.put("B2", new ChessPanel( 1, 1,
				this.points.get("A1F7"), this.points.get("A2F7"),
				this.points.get("A2F6"), this.points.get("A1F6")));
		
		this.panels.put("C2", new ChessPanel( 2, 1,
				this.points.get("A2F7"), this.points.get("A3F7"),
				this.points.get("A3F6"), this.points.get("A2F6")));
		
		this.panels.put("D2", new ChessPanel( 3, 1,
				this.points.get("A3F7"), this.points.get("A4B1"),
				this.points.get("A4B2"), this.points.get("A3F6")));
	
		this.panels.put("E2", new ChessPanel( 4, 1,
				this.points.get("A4B1"), this.points.get("B1A5"),
				this.points.get("B2A5"), this.points.get("A4B2")));
		
		this.panels.put("F2", new ChessPanel( 5, 1,
				this.points.get("B1A5"), this.points.get("B1A6"),
				this.points.get("B2A6"), this.points.get("B2A5")));
		
		this.panels.put("G2", new ChessPanel( 6, 1,
				this.points.get("B1A6"), this.points.get("B1A7"),
				this.points.get("B2A7"), this.points.get("B2A6")));
		
		this.panels.put("H2", new ChessPanel( 7, 1,
				this.points.get("B1A7"), this.points.get("B1"),
				this.points.get("B2"), this.points.get("B2A7")));
		
		// Panels - Row 2
		this.panels.put("A3", new ChessPanel( 0, 2,
				this.points.get("F6"), this.points.get("A1F6"),
				this.points.get("A1F5"), this.points.get("F5")));
		
		this.panels.put("B3", new ChessPanel( 1, 2,
				this.points.get("A1F6"), this.points.get("A2F6"),
				this.points.get("A2F5"), this.points.get("A1F5")));
		
		this.panels.put("C3", new ChessPanel( 2, 2,
				this.points.get("A2F6"), this.points.get("A3F6"),
				this.points.get("A3F5"), this.points.get("A2F5")));
		
		this.panels.put("D3", new ChessPanel( 3, 2,
				this.points.get("A3F6"), this.points.get("A4B2"),
				this.points.get("A4B3"), this.points.get("A3F5")));
	
		this.panels.put("E3", new ChessPanel( 4, 2,
				this.points.get("A4B2"), this.points.get("B2A5"),
				this.points.get("B3A5"), this.points.get("A4B3")));
		
		this.panels.put("F3", new ChessPanel( 5, 2,
				this.points.get("B2A5"), this.points.get("B2A6"),
				this.points.get("B3A6"), this.points.get("B3A5")));
		
		this.panels.put("G3", new ChessPanel( 6, 2,
				this.points.get("B2A6"), this.points.get("B2A7"),
				this.points.get("B3A7"), this.points.get("B3A6")));
		
		this.panels.put("H3", new ChessPanel( 7, 2,
				this.points.get("B2A7"), this.points.get("B2"),
				this.points.get("B3"), this.points.get("B3A7")));
		
		// Panels - Row 3
		this.panels.put("A4", new ChessPanel( 0, 3,
				this.points.get("F5"), this.points.get("A1F5"),
				this.points.get("F4A1"), this.points.get("F4")));
		
		this.panels.put("B4", new ChessPanel( 1, 3,
				this.points.get("A1F5"), this.points.get("A2F5"),
				this.points.get("F4A2"), this.points.get("F4A1")));
		
		this.panels.put("C4", new ChessPanel( 2, 3,
				this.points.get("A2F5"), this.points.get("A3F5"),
				this.points.get("F4A3"), this.points.get("F4A2")));
		
		this.panels.put("D4", new ChessPanel( 3, 3,
				this.points.get("A3F5"), this.points.get("A4B3"),
				this.points.get("Center"), this.points.get("F4A3")));
	
		this.panels.put("E4", new ChessPanel( 4, 3,
				this.points.get("A4B3"), this.points.get("B3A5"),
				this.points.get("B4C3"), this.points.get("Center")));
		
		this.panels.put("F4", new ChessPanel( 5, 3,
				this.points.get("B3A5"), this.points.get("B3A6"),
				this.points.get("B4C2"), this.points.get("B4C3")));
		
		this.panels.put("G4", new ChessPanel( 6, 3,
				this.points.get("B3A6"), this.points.get("B3A7"),
				this.points.get("B4C2"), this.points.get("B4C2")));
		
		this.panels.put("H4", new ChessPanel( 7, 3,
				this.points.get("B3A7"), this.points.get("B3"),
				this.points.get("B4"), this.points.get("B4C1")));
		
		// Panels - Row 4
		this.panels.put("A5", new ChessPanel( 0, 4,
				this.points.get("F4"), this.points.get("F4A1"),
				this.points.get("F3E7"), this.points.get("F3")));
		
		this.panels.put("B5", new ChessPanel( 1, 4,
				this.points.get("F4A1"), this.points.get("F4A2"),
				this.points.get("F3E6"), this.points.get("F3E7")));
		
		this.panels.put("C5", new ChessPanel( 2, 4,
				this.points.get("F4A2"), this.points.get("F4A3"),
				this.points.get("F3E5"), this.points.get("F3E6")));
		
		this.panels.put("D5", new ChessPanel( 3, 4,
				this.points.get("F4A3"), this.points.get("Center"),
				this.points.get("E4F3"), this.points.get("F3E5")));
		
		this.panels.put("I5", new ChessPanel( 8, 4,
				this.points.get("Center"), this.points.get("D4E3"),
				this.points.get("E3D5"), this.points.get("E4F3")));
		
		this.panels.put("J5", new ChessPanel( 9, 4,
				this.points.get("D4E3"), this.points.get("D4E2"),
				this.points.get("E2D5"), this.points.get("E3D5")));
		
		this.panels.put("K5", new ChessPanel( 10, 4,
				this.points.get("D4E2"), this.points.get("D4E1"),
				this.points.get("E1D5"), this.points.get("E2D5")));
		
		this.panels.put("L5", new ChessPanel( 11, 4,
				this.points.get("D4E1"), this.points.get("D4"),
				this.points.get("D5"), this.points.get("E1D5")));
		
		// Panels - Row 5
		this.panels.put("A6", new ChessPanel( 0, 5,
				this.points.get("F3"), this.points.get("F3E7"),
				this.points.get("F2E7"), this.points.get("F2")));
		
		this.panels.put("B6", new ChessPanel( 1, 5,
				this.points.get("F3E7"), this.points.get("F3E6"),
				this.points.get("F2E6"), this.points.get("F2E7")));
		
		this.panels.put("C6", new ChessPanel( 2, 5,
				this.points.get("F3E6"), this.points.get("F3E5"),
				this.points.get("F2E5"), this.points.get("F2E6")));
		
		this.panels.put("D6", new ChessPanel( 3, 5,
				this.points.get("F3E5"), this.points.get("E4F3"),
				this.points.get("E4F2"), this.points.get("F2E5")));
		
		this.panels.put("I6", new ChessPanel( 8, 5,
				this.points.get("E4F3"), this.points.get("E3D5"),
				this.points.get("E3D6"), this.points.get("E4F2")));
		
		this.panels.put("J6", new ChessPanel( 9, 5,
				this.points.get("E3D5"), this.points.get("E2D5"),
				this.points.get("E2D6"), this.points.get("E3D6")));
		
		this.panels.put("K6", new ChessPanel( 10, 5,
				this.points.get("E2D5"), this.points.get("E1D5"),
				this.points.get("E1D6"), this.points.get("E2D6")));
		
		this.panels.put("L6", new ChessPanel( 11, 5,
				this.points.get("E1D5"), this.points.get("D5"),
				this.points.get("D6"), this.points.get("E1D6")));
		
		// Panels - Row 6
		this.panels.put("A7", new ChessPanel( 0, 6,
				this.points.get("F2"), this.points.get("F2E7"),
				this.points.get("F1E7"), this.points.get("F1")));
		
		this.panels.put("B7", new ChessPanel( 1, 6,
				this.points.get("F2E7"), this.points.get("F2E6"),
				this.points.get("F1E6"), this.points.get("F1E7")));
		
		this.panels.put("C7", new ChessPanel( 2, 6,
				this.points.get("F2E6"), this.points.get("F2E5"),
				this.points.get("F1E5"), this.points.get("F1E6")));
		
		this.panels.put("D7", new ChessPanel( 3, 6,
				this.points.get("F2E5"), this.points.get("E4F2"),
				this.points.get("E4F1"), this.points.get("F1E5")));
		
		this.panels.put("I7", new ChessPanel( 8, 6,
				this.points.get("E4F2"), this.points.get("E3D6"),
				this.points.get("E3D7"), this.points.get("E4F1")));
		
		this.panels.put("J7", new ChessPanel( 9, 6,
				this.points.get("E3D6"), this.points.get("E2D6"),
				this.points.get("E2D7"), this.points.get("E3D7")));
		
		this.panels.put("K7", new ChessPanel( 10, 6,
				this.points.get("E2D6"), this.points.get("E1D6"),
				this.points.get("E1D7"), this.points.get("E2D7")));
		
		this.panels.put("L7", new ChessPanel( 11, 6,
				this.points.get("E1D6"), this.points.get("D6"),
				this.points.get("D7"), this.points.get("E1D7")));
		
		// Panels - Row 7
		this.panels.put("A8", new ChessPanel( 0, 7,
				this.points.get("F1"), this.points.get("F1E7"),
				this.points.get("E7"), this.points.get("F")));
		
		this.panels.put("B8", new ChessPanel( 1, 7,
				this.points.get("F1E7"), this.points.get("F1E6"),
				this.points.get("E6"), this.points.get("E7")));
		
		this.panels.put("C8", new ChessPanel( 2, 7,
				this.points.get("F1E6"), this.points.get("F1E5"),
				this.points.get("E5"), this.points.get("E6")));
		
		this.panels.put("D8", new ChessPanel( 3, 7,
				this.points.get("F1E5"), this.points.get("E4F1"),
				this.points.get("E4"), this.points.get("E5")));
		
		this.panels.put("I8", new ChessPanel( 8, 7,
				this.points.get("E4F1"), this.points.get("E3D7"),
				this.points.get("E3"), this.points.get("E4")));
		
		this.panels.put("J8", new ChessPanel( 9, 7,
				this.points.get("E3D7"), this.points.get("E2D7"),
				this.points.get("E2"), this.points.get("E3")));
		
		this.panels.put("K8", new ChessPanel( 10, 7,
				this.points.get("E2D7"), this.points.get("E1D7"),
				this.points.get("E1"), this.points.get("E2")));
		
		this.panels.put("L8", new ChessPanel( 11, 7,
				this.points.get("E1D7"), this.points.get("D7"),
				this.points.get("E"), this.points.get("E1")));
		
		// Panels - Row 8
		this.panels.put("H9", new ChessPanel( 0, 8,
				this.points.get("B4"), this.points.get("B4C1"),
				this.points.get("C1B5"), this.points.get("B5")));
		
		this.panels.put("G9", new ChessPanel( 1, 8,
				this.points.get("B4C1"), this.points.get("B4C2"),
				this.points.get("C2B5"), this.points.get("C1B5")));
		
		this.panels.put("F9", new ChessPanel( 2, 8,
				this.points.get("B4C2"), this.points.get("B4C3"),
				this.points.get("C3B5"), this.points.get("C2B5")));
		
		this.panels.put("E9", new ChessPanel( 3, 8,
				this.points.get("B4C3"), this.points.get("Center"),
				this.points.get("C4D3"), this.points.get("C3B5")));
		
		this.panels.put("I9", new ChessPanel( 8, 8,
				this.points.get("Center"), this.points.get("D4E3"),
				this.points.get("D3C5"), this.points.get("C4D3")));
		
		this.panels.put("J9", new ChessPanel( 9, 8,
				this.points.get("D4E3"), this.points.get("D4E2"),
				this.points.get("D3C6"), this.points.get("D3C5")));
		
		this.panels.put("K9", new ChessPanel( 10, 8,
				this.points.get("D4E2"), this.points.get("D4E1"),
				this.points.get("D3C7"), this.points.get("D3C6")));
		
		this.panels.put("L9", new ChessPanel( 11, 8,
				this.points.get("D4E1"), this.points.get("D4"),
				this.points.get("D3"), this.points.get("D3C7")));
		
		// Panels - Row 9
		this.panels.put("H10", new ChessPanel( 0, 9,
				this.points.get("B5"), this.points.get("C1B5"),
				this.points.get("C1B6"), this.points.get("B6")));
		
		this.panels.put("G10", new ChessPanel( 1, 9,
				this.points.get("C1B5"), this.points.get("C2B5"),
				this.points.get("C2B6"), this.points.get("C1B6")));
		
		this.panels.put("F10", new ChessPanel( 2, 9,
				this.points.get("C2B5"), this.points.get("C3B5"),
				this.points.get("C3B6"), this.points.get("C2B6")));
		
		this.panels.put("E10", new ChessPanel( 3, 9,
				this.points.get("C3B5"), this.points.get("C4D3"),
				this.points.get("C4D2"), this.points.get("C2B6")));
		
		this.panels.put("I10", new ChessPanel( 8, 9,
				this.points.get("C4D3"), this.points.get("D3C5"),
				this.points.get("D2C5"), this.points.get("C4D2")));
		
		this.panels.put("J10", new ChessPanel( 9, 9,
				this.points.get("D3C5"), this.points.get("D3C6"),
				this.points.get("D2C6"), this.points.get("D2C5")));
		
		this.panels.put("K10", new ChessPanel( 10, 9,
				this.points.get("D3C6"), this.points.get("D3C7"),
				this.points.get("D2C7"), this.points.get("D2C6")));
		
		this.panels.put("L10", new ChessPanel( 11, 9,
				this.points.get("D3C7"), this.points.get("D3"),
				this.points.get("D2"), this.points.get("D2C7")));
		
		// Panels - Row 10
		this.panels.put("H11", new ChessPanel( 0, 10,
				this.points.get("B6"), this.points.get("C1B6"),
				this.points.get("C1B7"), this.points.get("B7")));
		
		this.panels.put("G11", new ChessPanel( 1, 10,
				this.points.get("C1B6"), this.points.get("C2B6"),
				this.points.get("C2B7"), this.points.get("C1B7")));
		
		this.panels.put("F11", new ChessPanel( 2, 10,
				this.points.get("C2B6"), this.points.get("C3B6"),
				this.points.get("C3B7"), this.points.get("C2B7")));
		
		this.panels.put("E11", new ChessPanel( 3, 10,
				this.points.get("C3B6"), this.points.get("C4D2"),
				this.points.get("C4D1"), this.points.get("C3B7")));
		
		this.panels.put("I11", new ChessPanel( 8, 10,
				this.points.get("C4D2"), this.points.get("D2C5"),
				this.points.get("D1C5"), this.points.get("C4D1")));
		
		this.panels.put("J11", new ChessPanel( 9, 10,
				this.points.get("D2C5"), this.points.get("D2C6"),
				this.points.get("D1C6"), this.points.get("D1C5")));
		
		this.panels.put("K11", new ChessPanel( 10, 10,
				this.points.get("D2C6"), this.points.get("D2C7"),
				this.points.get("D1C7"), this.points.get("D1C6")));
		
		this.panels.put("L11", new ChessPanel( 11, 10,
				this.points.get("D2C7"), this.points.get("D2"),
				this.points.get("D1"), this.points.get("D1C7")));
		
		// Panels - Row 11
		this.panels.put("H12", new ChessPanel( 0, 11,
				this.points.get("B7"), this.points.get("C1B7"),
				this.points.get("C1"), this.points.get("C")));
		
		this.panels.put("G12", new ChessPanel( 1, 11,
				this.points.get("C1B7"), this.points.get("C2B7"),
				this.points.get("C2"), this.points.get("C1")));
		
		this.panels.put("F12", new ChessPanel( 2, 11,
				this.points.get("C2B7"), this.points.get("C3B7"),
				this.points.get("C3"), this.points.get("C2")));
		
		this.panels.put("E12", new ChessPanel( 3, 11,
				this.points.get("C3"), this.points.get("C4D1"),
				this.points.get("C4"), this.points.get("C3B7")));
		
		this.panels.put("I12", new ChessPanel( 8, 11,
				this.points.get("C4D1"), this.points.get("D1C5"),
				this.points.get("C5"), this.points.get("C4")));
		
		this.panels.put("J12", new ChessPanel( 9, 11,
				this.points.get("D1C5"), this.points.get("D1C6"),
				this.points.get("C6"), this.points.get("C5")));
		
		this.panels.put("K12", new ChessPanel( 10, 11,
				this.points.get("D1C6"), this.points.get("D1C7"),
				this.points.get("C7"), this.points.get("C6")));
		
		this.panels.put("L12", new ChessPanel( 11, 11,
				this.points.get("D1C7"), this.points.get("D1"),
				this.points.get("D"), this.points.get("C7")));
	}
	
	
	private void setupPanelTree()
	{
		// set whole hexagon as the root node
		this.panelRoot = new Node<ChessPanel>("root", new ChessPanel());
		
		List<Node<ChessPanel>> children = new ArrayList<Node<ChessPanel>>();
		
		children.add(new Node<ChessPanel>("Section A", this.panels.get("Section A")));
		children.add(new Node<ChessPanel>("Section B", this.panels.get("Section B")));
		children.add(new Node<ChessPanel>("Section C", this.panels.get("Section C")));
		children.add(new Node<ChessPanel>("Section D", this.panels.get("Section D")));
		children.add(new Node<ChessPanel>("Section E", this.panels.get("Section E")));
		children.add(new Node<ChessPanel>("Section F", this.panels.get("Section F")));
		this.panelRoot.addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("Section A1F7", this.panels.get("Section A1F7")));
		children.add(new Node<ChessPanel>("Section A3F7", this.panels.get("Section A3F7")));
		children.add(new Node<ChessPanel>("Section A1F5", this.panels.get("Section A1F5")));
		children.add(new Node<ChessPanel>("Section A3F5", this.panels.get("Section A3F5")));
		this.panelRoot.getChild("Section A").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("Section B1A7", this.panels.get("Section B1A7")));
		children.add(new Node<ChessPanel>("Section B3A7", this.panels.get("Section B3A7")));
		children.add(new Node<ChessPanel>("Section B1A5", this.panels.get("Section B1A5")));
		children.add(new Node<ChessPanel>("Section B3A5", this.panels.get("Section B3A5")));
		this.panelRoot.getChild("Section B").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("Section C1B7", this.panels.get("Section C1B7")));
		children.add(new Node<ChessPanel>("Section C3B7", this.panels.get("Section C3B7")));
		children.add(new Node<ChessPanel>("Section C1B5", this.panels.get("Section C1B5")));
		children.add(new Node<ChessPanel>("Section C3B5", this.panels.get("Section C3B5")));
		this.panelRoot.getChild("Section C").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("Section D1C7", this.panels.get("Section D1C7")));
		children.add(new Node<ChessPanel>("Section D3C7", this.panels.get("Section D3C7")));
		children.add(new Node<ChessPanel>("Section D1C5", this.panels.get("Section D1C5")));
		children.add(new Node<ChessPanel>("Section D3C5", this.panels.get("Section D3C5")));
		this.panelRoot.getChild("Section D").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("Section E1D7", this.panels.get("Section E1D7")));
		children.add(new Node<ChessPanel>("Section E3D7", this.panels.get("Section E3D7")));
		children.add(new Node<ChessPanel>("Section E1D5", this.panels.get("Section E1D5")));
		children.add(new Node<ChessPanel>("Section E3D5", this.panels.get("Section E3D5")));
		this.panelRoot.getChild("Section E").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("Section F1E7", this.panels.get("Section F1E7")));
		children.add(new Node<ChessPanel>("Section F3E7", this.panels.get("Section F3E7")));
		children.add(new Node<ChessPanel>("Section F1E5", this.panels.get("Section F1E5")));
		children.add(new Node<ChessPanel>("Section F3E5", this.panels.get("Section F3E5")));
		this.panelRoot.getChild("Section F").addChildren(children);
		children.clear();
		
		
		children.add(new Node<ChessPanel>("A1", this.panels.get("A1")));
		children.add(new Node<ChessPanel>("B1", this.panels.get("B1")));
		children.add(new Node<ChessPanel>("A2", this.panels.get("A2")));
		children.add(new Node<ChessPanel>("B2", this.panels.get("B2")));
		this.panelRoot.getNode("Section A1F7").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("C1", this.panels.get("C1")));
		children.add(new Node<ChessPanel>("D1", this.panels.get("D1")));
		children.add(new Node<ChessPanel>("C2", this.panels.get("C2")));
		children.add(new Node<ChessPanel>("D2", this.panels.get("D2")));
		this.panelRoot.getNode("Section A3F7").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("E1", this.panels.get("E1")));
		children.add(new Node<ChessPanel>("F1", this.panels.get("F1")));
		children.add(new Node<ChessPanel>("E2", this.panels.get("E2")));
		children.add(new Node<ChessPanel>("F2", this.panels.get("F2")));
		this.panelRoot.getNode("Section B1A5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("G1", this.panels.get("G1")));
		children.add(new Node<ChessPanel>("H1", this.panels.get("H1")));
		children.add(new Node<ChessPanel>("G2", this.panels.get("G2")));
		children.add(new Node<ChessPanel>("H2", this.panels.get("H2")));
		this.panelRoot.getNode("Section B1A7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<ChessPanel>("A3", this.panels.get("A3")));
		children.add(new Node<ChessPanel>("B3", this.panels.get("B3")));
		children.add(new Node<ChessPanel>("A4", this.panels.get("A4")));
		children.add(new Node<ChessPanel>("B4", this.panels.get("B4")));
		this.panelRoot.getNode("Section A1F5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("C3", this.panels.get("C3")));
		children.add(new Node<ChessPanel>("D3", this.panels.get("D3")));
		children.add(new Node<ChessPanel>("C4", this.panels.get("C4")));
		children.add(new Node<ChessPanel>("D4", this.panels.get("D4")));
		this.panelRoot.getNode("Section A3F5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("E3", this.panels.get("E3")));
		children.add(new Node<ChessPanel>("F3", this.panels.get("F3")));
		children.add(new Node<ChessPanel>("E4", this.panels.get("E4")));
		children.add(new Node<ChessPanel>("F4", this.panels.get("F4")));
		this.panelRoot.getNode("Section B3A5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("G3", this.panels.get("G3")));
		children.add(new Node<ChessPanel>("H3", this.panels.get("H3")));
		children.add(new Node<ChessPanel>("G4", this.panels.get("G4")));
		children.add(new Node<ChessPanel>("H4", this.panels.get("H4")));
		this.panelRoot.getNode("Section B3A7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<ChessPanel>("A5", this.panels.get("A5")));
		children.add(new Node<ChessPanel>("B5", this.panels.get("B5")));
		children.add(new Node<ChessPanel>("A6", this.panels.get("A6")));
		children.add(new Node<ChessPanel>("B6", this.panels.get("B6")));
		this.panelRoot.getNode("Section F3E7").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("C5", this.panels.get("C5")));
		children.add(new Node<ChessPanel>("D5", this.panels.get("D5")));
		children.add(new Node<ChessPanel>("C6", this.panels.get("C6")));
		children.add(new Node<ChessPanel>("D6", this.panels.get("D6")));
		this.panelRoot.getNode("Section F3E5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("I5", this.panels.get("I5")));
		children.add(new Node<ChessPanel>("J5", this.panels.get("J5")));
		children.add(new Node<ChessPanel>("I6", this.panels.get("I6")));
		children.add(new Node<ChessPanel>("J6", this.panels.get("J6")));
		this.panelRoot.getNode("Section E3D5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("K5", this.panels.get("K5")));
		children.add(new Node<ChessPanel>("L5", this.panels.get("L5")));
		children.add(new Node<ChessPanel>("K6", this.panels.get("K6")));
		children.add(new Node<ChessPanel>("L6", this.panels.get("L6")));
		this.panelRoot.getNode("Section E1D5").addChildren(children);
		children.clear();
		
		
		children.add(new Node<ChessPanel>("A7", this.panels.get("A7")));
		children.add(new Node<ChessPanel>("B7", this.panels.get("B7")));
		children.add(new Node<ChessPanel>("A8", this.panels.get("A8")));
		children.add(new Node<ChessPanel>("B8", this.panels.get("B8")));
		this.panelRoot.getNode("Section F1E7").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("C7", this.panels.get("C7")));
		children.add(new Node<ChessPanel>("D7", this.panels.get("D7")));
		children.add(new Node<ChessPanel>("C8", this.panels.get("C8")));
		children.add(new Node<ChessPanel>("D8", this.panels.get("D8")));
		this.panelRoot.getNode("Section F1E5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("I7", this.panels.get("I7")));
		children.add(new Node<ChessPanel>("J7", this.panels.get("J7")));
		children.add(new Node<ChessPanel>("I8", this.panels.get("I8")));
		children.add(new Node<ChessPanel>("J8", this.panels.get("J8")));
		this.panelRoot.getNode("Section E3D7").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("K7", this.panels.get("K7")));
		children.add(new Node<ChessPanel>("L7", this.panels.get("L7")));
		children.add(new Node<ChessPanel>("K8", this.panels.get("K8")));
		children.add(new Node<ChessPanel>("L8", this.panels.get("L8")));
		this.panelRoot.getNode("Section E1D7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<ChessPanel>("H9", this.panels.get("H9")));
		children.add(new Node<ChessPanel>("G9", this.panels.get("G9")));
		children.add(new Node<ChessPanel>("H10", this.panels.get("H10")));
		children.add(new Node<ChessPanel>("G10", this.panels.get("G10")));
		this.panelRoot.getNode("Section C1B5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("F9", this.panels.get("F9")));
		children.add(new Node<ChessPanel>("E9", this.panels.get("E9")));
		children.add(new Node<ChessPanel>("F10", this.panels.get("F10")));
		children.add(new Node<ChessPanel>("E10", this.panels.get("E10")));
		this.panelRoot.getNode("Section C3B5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("I9", this.panels.get("I9")));
		children.add(new Node<ChessPanel>("J9", this.panels.get("J9")));
		children.add(new Node<ChessPanel>("I10", this.panels.get("I10")));
		children.add(new Node<ChessPanel>("J10", this.panels.get("J10")));
		this.panelRoot.getNode("Section D3C5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("K9", this.panels.get("K9")));
		children.add(new Node<ChessPanel>("L9", this.panels.get("L9")));
		children.add(new Node<ChessPanel>("K10", this.panels.get("K10")));
		children.add(new Node<ChessPanel>("L10", this.panels.get("L10")));
		this.panelRoot.getNode("Section D3C7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<ChessPanel>("H11", this.panels.get("H11")));
		children.add(new Node<ChessPanel>("G11", this.panels.get("G11")));
		children.add(new Node<ChessPanel>("H12", this.panels.get("H12")));
		children.add(new Node<ChessPanel>("G12", this.panels.get("G12")));
		this.panelRoot.getNode("Section C1B7").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("F11", this.panels.get("F11")));
		children.add(new Node<ChessPanel>("E11", this.panels.get("E11")));
		children.add(new Node<ChessPanel>("F12", this.panels.get("F12")));
		children.add(new Node<ChessPanel>("E12", this.panels.get("E12")));
		this.panelRoot.getNode("Section C3B7").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("I11", this.panels.get("I11")));
		children.add(new Node<ChessPanel>("J11", this.panels.get("J11")));
		children.add(new Node<ChessPanel>("I12", this.panels.get("I12")));
		children.add(new Node<ChessPanel>("J12", this.panels.get("J12")));
		this.panelRoot.getNode("Section D1C5").addChildren(children);
		children.clear();
		
		children.add(new Node<ChessPanel>("K11", this.panels.get("K11")));
		children.add(new Node<ChessPanel>("L11", this.panels.get("L11")));
		children.add(new Node<ChessPanel>("K12", this.panels.get("K12")));
		children.add(new Node<ChessPanel>("L12", this.panels.get("L12")));
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
	
	
	// @return: ChessPanel or NULL if the point isnt within any panel
	public ChessPanel getPanelWithinPoint(Point point)
	{
		if (this.hexagon.isPointWithinHexagon(point))
			return breadthFirstSearch(point);
		return null;
	}
	
	
	private ChessPanel breadthFirstSearch(Point point)
	{
		return searchInTreeLevel(this.panelRoot, point);
	}
	
	
	private ChessPanel searchInTreeLevel(Node<ChessPanel> parentNode, Point point)
	{
		List<Node<ChessPanel>> childNodes = parentNode.getChildren();
		
		for (Node<ChessPanel> node : childNodes)
		{
			if (node.getData().isPointInsidePanel(point))
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
	}
}
