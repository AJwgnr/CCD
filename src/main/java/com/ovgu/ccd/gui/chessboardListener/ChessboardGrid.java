package com.ovgu.ccd.gui.chessboardListener;

import com.ovgu.ccd.pieces.Square;

import java.awt.*;
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

/**
 * @author  CCD DeepBlue team
 * @version 1.0
 * @since   1.0
 */
public class ChessboardGrid extends GeometricPrimitiveDrawer
{
	private static final long serialVersionUID = 1716287489012384473L;
	
	private HashMap<String, Point> points = new HashMap<String, Point>();
	private HashMap<String, GridSquare> squares = new HashMap<String, GridSquare>();
	private Node<GridSquare> panelRoot = null;
	private Hexagon hexagon = null;
	private ChessboardLabeling labeling = null;
	private ArrayList<Square> possibleMoves = null;

	private Color squareStrikeColor = new Color(230, 0, 0, 100);
	private Color squareStuckColor 	= new Color(0, 0, 230, 100);
	private Color squareHighlight 	= new Color(50, 250, 100, 100);
  private Color squareFillColorA 	= new Color(153, 102, 51, 255);
  private Color squareFillColorB 	= new Color(255, 230, 153, 255);


    /**
     * constructor
     *
     * @param center center of the chessboard
     * @param radius radius of the outer hexagon
     *
     */
    public ChessboardGrid(Point center, int radius)
    {
        this.hexagon = new Hexagon(center, radius);
        this.points = this.hexagon.getVerticesAsMap();
        this.points.put("Center", center);
        init();
    }


    /**
     * initialization method
     *
     *
     *
     */
	private void init()
	{
		setupPoints();
		setupSquares();
		setupSquareTree();
		setupLabeling();
		setupSquareColorScheme();
	}


	/**
	 * setup: square dual color scheme
	 *
	 *
	 *
	 */
	public void setupSquareColorScheme()
	{
		this.squares.get("A1").setFillColor(squareFillColorA);
		this.squares.get("B1").setFillColor(squareFillColorB);
		this.squares.get("C1").setFillColor(squareFillColorA);
		this.squares.get("D1").setFillColor(squareFillColorB);
		this.squares.get("E1").setFillColor(squareFillColorA);
		this.squares.get("F1").setFillColor(squareFillColorB);
		this.squares.get("G1").setFillColor(squareFillColorA);
		this.squares.get("H1").setFillColor(squareFillColorB);

		this.squares.get("A2").setFillColor(squareFillColorB);
		this.squares.get("B2").setFillColor(squareFillColorA);
		this.squares.get("C2").setFillColor(squareFillColorB);
		this.squares.get("D2").setFillColor(squareFillColorA);
		this.squares.get("E2").setFillColor(squareFillColorB);
		this.squares.get("F2").setFillColor(squareFillColorA);
		this.squares.get("G2").setFillColor(squareFillColorB);
		this.squares.get("H2").setFillColor(squareFillColorA);

		this.squares.get("A3").setFillColor(squareFillColorA);
		this.squares.get("B3").setFillColor(squareFillColorB);
		this.squares.get("C3").setFillColor(squareFillColorA);
		this.squares.get("D3").setFillColor(squareFillColorB);
		this.squares.get("E3").setFillColor(squareFillColorA);
		this.squares.get("F3").setFillColor(squareFillColorB);
		this.squares.get("G3").setFillColor(squareFillColorA);
		this.squares.get("H3").setFillColor(squareFillColorB);

		this.squares.get("A4").setFillColor(squareFillColorB);
		this.squares.get("B4").setFillColor(squareFillColorA);
		this.squares.get("C4").setFillColor(squareFillColorB);
		this.squares.get("D4").setFillColor(squareFillColorA);
		this.squares.get("E4").setFillColor(squareFillColorB);
		this.squares.get("F4").setFillColor(squareFillColorA);
		this.squares.get("G4").setFillColor(squareFillColorB);
		this.squares.get("H4").setFillColor(squareFillColorA);

		this.squares.get("A5").setFillColor(squareFillColorA);
		this.squares.get("B5").setFillColor(squareFillColorB);
		this.squares.get("C5").setFillColor(squareFillColorA);
		this.squares.get("D5").setFillColor(squareFillColorB);
		this.squares.get("I5").setFillColor(squareFillColorA);
		this.squares.get("J5").setFillColor(squareFillColorB);
		this.squares.get("K5").setFillColor(squareFillColorA);
		this.squares.get("L5").setFillColor(squareFillColorB);

		this.squares.get("A6").setFillColor(squareFillColorB);
		this.squares.get("B6").setFillColor(squareFillColorA);
		this.squares.get("C6").setFillColor(squareFillColorB);
		this.squares.get("D6").setFillColor(squareFillColorA);
		this.squares.get("I6").setFillColor(squareFillColorB);
		this.squares.get("J6").setFillColor(squareFillColorA);
		this.squares.get("K6").setFillColor(squareFillColorB);
		this.squares.get("L6").setFillColor(squareFillColorA);

		this.squares.get("A7").setFillColor(squareFillColorA);
		this.squares.get("B7").setFillColor(squareFillColorB);
		this.squares.get("C7").setFillColor(squareFillColorA);
		this.squares.get("D7").setFillColor(squareFillColorB);
		this.squares.get("I7").setFillColor(squareFillColorA);
		this.squares.get("J7").setFillColor(squareFillColorB);
		this.squares.get("K7").setFillColor(squareFillColorA);
		this.squares.get("L7").setFillColor(squareFillColorB);

		this.squares.get("A8").setFillColor(squareFillColorB);
		this.squares.get("B8").setFillColor(squareFillColorA);
		this.squares.get("C8").setFillColor(squareFillColorB);
		this.squares.get("D8").setFillColor(squareFillColorA);
		this.squares.get("I8").setFillColor(squareFillColorB);
		this.squares.get("J8").setFillColor(squareFillColorA);
		this.squares.get("K8").setFillColor(squareFillColorB);
		this.squares.get("L8").setFillColor(squareFillColorA);

		this.squares.get("H9").setFillColor(squareFillColorB);
		this.squares.get("G9").setFillColor(squareFillColorA);
		this.squares.get("F9").setFillColor(squareFillColorB);
		this.squares.get("E9").setFillColor(squareFillColorA);
		this.squares.get("I9").setFillColor(squareFillColorB);
		this.squares.get("J9").setFillColor(squareFillColorA);
		this.squares.get("K9").setFillColor(squareFillColorB);
		this.squares.get("L9").setFillColor(squareFillColorA);

		this.squares.get("H10").setFillColor(squareFillColorA);
		this.squares.get("G10").setFillColor(squareFillColorB);
		this.squares.get("F10").setFillColor(squareFillColorA);
		this.squares.get("E10").setFillColor(squareFillColorB);
		this.squares.get("I10").setFillColor(squareFillColorA);
		this.squares.get("J10").setFillColor(squareFillColorB);
		this.squares.get("K10").setFillColor(squareFillColorA);
		this.squares.get("L10").setFillColor(squareFillColorB);

		this.squares.get("H11").setFillColor(squareFillColorB);
		this.squares.get("G11").setFillColor(squareFillColorA);
		this.squares.get("F11").setFillColor(squareFillColorB);
		this.squares.get("E11").setFillColor(squareFillColorA);
		this.squares.get("I11").setFillColor(squareFillColorB);
		this.squares.get("J11").setFillColor(squareFillColorA);
		this.squares.get("K11").setFillColor(squareFillColorB);
		this.squares.get("L11").setFillColor(squareFillColorA);

		this.squares.get("H12").setFillColor(squareFillColorA);
		this.squares.get("G12").setFillColor(squareFillColorB);
		this.squares.get("F12").setFillColor(squareFillColorA);
		this.squares.get("E12").setFillColor(squareFillColorB);
		this.squares.get("I12").setFillColor(squareFillColorA);
		this.squares.get("J12").setFillColor(squareFillColorB);
		this.squares.get("K12").setFillColor(squareFillColorA);
		this.squares.get("L12").setFillColor(squareFillColorB);
	}


    /**
     * setup: sets all labels for the chessboard gird
     *
     *
     *
     */
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


    /**
     * setup: sets all squares, defined by the vertices and the chessboard position (x,y)
     *
     *
     *
     */
	private void setupSquares()
	{
		this.squares.put("Section A", new GridSquare(
				this.points.get("A"), this.points.get("A4"),
				this.points.get("Center"), this.points.get("F4")));
		
		this.squares.put("Section B", new GridSquare(
				this.points.get("B"), this.points.get("B4"),
				this.points.get("Center"), this.points.get("A4")));
		
		this.squares.put("Section C", new GridSquare(
				this.points.get("C"), this.points.get("C4"),
				this.points.get("Center"), this.points.get("B4")));
		
		this.squares.put("Section D", new GridSquare(
				this.points.get("D"), this.points.get("D4"),
				this.points.get("Center"), this.points.get("C4")));
		
		this.squares.put("Section E", new GridSquare(
				this.points.get("E"), this.points.get("E4"),
				this.points.get("Center"), this.points.get("D4")));
		
		this.squares.put("Section F", new GridSquare(
				this.points.get("F"), this.points.get("F4"),
				this.points.get("Center"), this.points.get("E4")));
		
		// Sector A
		this.squares.put("Section A1F7", new GridSquare(
				this.points.get("A"), this.points.get("A2"),
				this.points.get("A2F6"), this.points.get("F6")));
		
		this.squares.put("Section A3F7", new GridSquare(
				this.points.get("A2"), this.points.get("A4"),
				this.points.get("A4B2"), this.points.get("A2F6")));
		
		this.squares.put("Section A1F5", new GridSquare(
				this.points.get("F6"), this.points.get("A2F6"),
				this.points.get("F4A2"), this.points.get("F4")));
		
		this.squares.put("Section A3F5", new GridSquare(
				this.points.get("A2F6"), this.points.get("A4B2"),
				this.points.get("Center"), this.points.get("F4A2")));
		
		// Sector B
		this.squares.put("Section B1A7", new GridSquare(
				this.points.get("B"), this.points.get("B2"),
				this.points.get("B2A6"), this.points.get("A6")));
		
		this.squares.put("Section B3A7", new GridSquare(
				this.points.get("B2"), this.points.get("B4"),
				this.points.get("B4C2"), this.points.get("B2A6")));
		
		this.squares.put("Section B1A5", new GridSquare(
				this.points.get("A6"), this.points.get("B2A6"),
				this.points.get("A4B2"), this.points.get("A4")));
		
		this.squares.put("Section B3A5", new GridSquare(
				this.points.get("B2A6"), this.points.get("B4C2"),
				this.points.get("Center"), this.points.get("A4B2")));
		
		// Sector C
		this.squares.put("Section C1B7", new GridSquare(
				this.points.get("C"), this.points.get("C2"),
				this.points.get("C2B6"), this.points.get("B6")));
		
		this.squares.put("Section C3B7", new GridSquare(
				this.points.get("C2"), this.points.get("C4"),
				this.points.get("C4D2"), this.points.get("C2B6")));
		
		this.squares.put("Section C1B5", new GridSquare(
				this.points.get("B6"), this.points.get("C2B6"),
				this.points.get("B4C2"), this.points.get("B4")));
		
		this.squares.put("Section C3B5", new GridSquare(
				this.points.get("C2B6"), this.points.get("C4D2"),
				this.points.get("Center"), this.points.get("B4C2")));
		
		// Sector D
		this.squares.put("Section D1C7", new GridSquare(
				this.points.get("D"), this.points.get("D2"),
				this.points.get("D2C6"), this.points.get("C6")));
		
		this.squares.put("Section D3C7", new GridSquare(
				this.points.get("D2"), this.points.get("D4"),
				this.points.get("D4E2"), this.points.get("D2C6")));
		
		this.squares.put("Section D1C5", new GridSquare(
				this.points.get("C6"), this.points.get("D2C6"),
				this.points.get("C4D2"), this.points.get("C4")));
		
		this.squares.put("Section D3C5", new GridSquare(
				this.points.get("D2C6"), this.points.get("D4E2"),
				this.points.get("Center"), this.points.get("C4D2")));
		
		// Sector E
		this.squares.put("Section E1D7", new GridSquare(
				this.points.get("E"), this.points.get("E2"),
				this.points.get("E2D6"), this.points.get("D6")));
		
		this.squares.put("Section E3D7", new GridSquare(
				this.points.get("E2"), this.points.get("E4"),
				this.points.get("E4F2"), this.points.get("E2D6")));
		
		this.squares.put("Section E1D5", new GridSquare(
				this.points.get("D6"), this.points.get("E2D6"),
				this.points.get("D4E2"), this.points.get("D4")));
		
		this.squares.put("Section E3D5", new GridSquare(
				this.points.get("E2D6"), this.points.get("E4F2"),
				this.points.get("Center"), this.points.get("D4E2")));
		
		// Sector F
		this.squares.put("Section F1E7", new GridSquare(
				this.points.get("F"), this.points.get("F2"),
				this.points.get("F2E6"), this.points.get("E6")));
		
		this.squares.put("Section F3E7", new GridSquare(
				this.points.get("F2"), this.points.get("F4"),
				this.points.get("F4A2"), this.points.get("F2E6")));
		
		this.squares.put("Section F1E5", new GridSquare(
				this.points.get("E6"), this.points.get("F2E6"),
				this.points.get("E4F2"), this.points.get("E4")));
		
		this.squares.put("Section F3E5", new GridSquare(
				this.points.get("F2E6"), this.points.get("F4A2"),
				this.points.get("Center"), this.points.get("E4F2")));
		
		// Panels - Row 0
		this.squares.put("A1", new GridSquare( 0, 0,
				this.points.get("A"), this.points.get("A1"),
				this.points.get("A1F7"), this.points.get("F7")));

		this.squares.put("B1", new GridSquare( 0, 1,
				this.points.get("A1"), this.points.get("A2"),
				this.points.get("A2F7"), this.points.get("A1F7")));
		
		this.squares.put("C1", new GridSquare( 0, 2,
				this.points.get("A2"), this.points.get("A3"),
				this.points.get("A3F7"), this.points.get("A2F7")));
		
		this.squares.put("D1", new GridSquare( 0, 3,
				this.points.get("A3"), this.points.get("A4"),
				this.points.get("A4B1"), this.points.get("A3F7")));
	
		this.squares.put("E1", new GridSquare( 0, 4,
				this.points.get("A4"), this.points.get("A5"),
				this.points.get("B1A5"), this.points.get("A4B1")));
		
		this.squares.put("F1", new GridSquare( 0, 5,
				this.points.get("A5"), this.points.get("A6"),
				this.points.get("B1A6"), this.points.get("B1A5")));
		
		this.squares.put("G1", new GridSquare( 0, 6,
				this.points.get("A6"), this.points.get("A7"),
				this.points.get("B1A7"), this.points.get("B1A6")));
		
		this.squares.put("H1", new GridSquare( 0, 7,
				this.points.get("A7"), this.points.get("B"),
				this.points.get("B1"), this.points.get("B1A7")));
		
		// Panels - Row 1
		this.squares.put("A2", new GridSquare( 1, 0,
				this.points.get("F7"), this.points.get("A1F7"),
				this.points.get("A1F6"), this.points.get("F6")));
		
		this.squares.put("B2", new GridSquare( 1, 1,
				this.points.get("A1F7"), this.points.get("A2F7"),
				this.points.get("A2F6"), this.points.get("A1F6")));

		this.squares.put("C2", new GridSquare( 1, 2,
				this.points.get("A2F7"), this.points.get("A3F7"),
				this.points.get("A3F6"), this.points.get("A2F6")));
		
		this.squares.put("D2", new GridSquare( 1, 3,
				this.points.get("A3F7"), this.points.get("A4B1"),
				this.points.get("A4B2"), this.points.get("A3F6")));
	
		this.squares.put("E2", new GridSquare( 1, 4,
				this.points.get("A4B1"), this.points.get("B1A5"),
				this.points.get("B2A5"), this.points.get("A4B2")));
		
		this.squares.put("F2", new GridSquare( 1, 5,
				this.points.get("B1A5"), this.points.get("B1A6"),
				this.points.get("B2A6"), this.points.get("B2A5")));
		
		this.squares.put("G2", new GridSquare( 1, 6,
				this.points.get("B1A6"), this.points.get("B1A7"),
				this.points.get("B2A7"), this.points.get("B2A6")));
		
		this.squares.put("H2", new GridSquare( 1, 7,
				this.points.get("B1A7"), this.points.get("B1"),
				this.points.get("B2"), this.points.get("B2A7")));
		
		// Panels - Row 2
		this.squares.put("A3", new GridSquare( 2, 0,
				this.points.get("F6"), this.points.get("A1F6"),
				this.points.get("A1F5"), this.points.get("F5")));
		
		this.squares.put("B3", new GridSquare( 2, 1,
				this.points.get("A1F6"), this.points.get("A2F6"),
				this.points.get("A2F5"), this.points.get("A1F5")));
		
		this.squares.put("C3", new GridSquare( 2, 2,
				this.points.get("A2F6"), this.points.get("A3F6"),
				this.points.get("A3F5"), this.points.get("A2F5")));

		this.squares.put("D3", new GridSquare( 2, 3,
				this.points.get("A3F6"), this.points.get("A4B2"),
				this.points.get("A4B3"), this.points.get("A3F5")));
	
		this.squares.put("E3", new GridSquare( 2, 4,
				this.points.get("A4B2"), this.points.get("B2A5"),
				this.points.get("B3A5"), this.points.get("A4B3")));
		
		this.squares.put("F3", new GridSquare( 2, 5,
				this.points.get("B2A5"), this.points.get("B2A6"),
				this.points.get("B3A6"), this.points.get("B3A5")));
		
		this.squares.put("G3", new GridSquare( 2, 6,
				this.points.get("B2A6"), this.points.get("B2A7"),
				this.points.get("B3A7"), this.points.get("B3A6")));
		
		this.squares.put("H3", new GridSquare( 2, 7,
				this.points.get("B2A7"), this.points.get("B2"),
				this.points.get("B3"), this.points.get("B3A7")));
		
		// Panels - Row 3
		this.squares.put("A4", new GridSquare( 3, 0,
				this.points.get("F5"), this.points.get("A1F5"),
				this.points.get("F4A1"), this.points.get("F4")));
		
		this.squares.put("B4", new GridSquare( 3, 1,
				this.points.get("A1F5"), this.points.get("A2F5"),
				this.points.get("F4A2"), this.points.get("F4A1")));
		
		this.squares.put("C4", new GridSquare( 3, 2,
				this.points.get("A2F5"), this.points.get("A3F5"),
				this.points.get("F4A3"), this.points.get("F4A2")));
		
		this.squares.put("D4", new GridSquare( 3, 3,
				this.points.get("A3F5"), this.points.get("A4B3"),
				this.points.get("Center"), this.points.get("F4A3")));

		this.squares.put("E4", new GridSquare( 3, 4,
				this.points.get("A4B3"), this.points.get("B3A5"),
				this.points.get("B4C3"), this.points.get("Center")));
		
		this.squares.put("F4", new GridSquare( 3, 5,
				this.points.get("B3A5"), this.points.get("B3A6"),
				this.points.get("B4C2"), this.points.get("B4C3")));
		
		this.squares.put("G4", new GridSquare( 3, 6,
				this.points.get("B3A6"), this.points.get("B3A7"),
				this.points.get("B4C1"), this.points.get("B4C2")));
		
		this.squares.put("H4", new GridSquare( 3, 7,
				this.points.get("B3A7"), this.points.get("B3"),
				this.points.get("B4"), this.points.get("B4C1")));
		
		// Panels - Row 4
		this.squares.put("A5", new GridSquare( 4, 0,
				this.points.get("F4"), this.points.get("F4A1"),
				this.points.get("F3E7"), this.points.get("F3")));
		
		this.squares.put("B5", new GridSquare( 4, 1,
				this.points.get("F4A1"), this.points.get("F4A2"),
				this.points.get("F3E6"), this.points.get("F3E7")));
		
		this.squares.put("C5", new GridSquare( 4, 2,
				this.points.get("F4A2"), this.points.get("F4A3"),
				this.points.get("F3E5"), this.points.get("F3E6")));
		
		this.squares.put("D5", new GridSquare( 4, 3,
				this.points.get("F4A3"), this.points.get("Center"),
				this.points.get("E4F3"), this.points.get("F3E5")));
		
		this.squares.put("I5", new GridSquare( 4, 8,
				this.points.get("Center"), this.points.get("D4E3"),
				this.points.get("E3D5"), this.points.get("E4F3")));
		
		this.squares.put("J5", new GridSquare( 4, 9,
				this.points.get("D4E3"), this.points.get("D4E2"),
				this.points.get("E2D5"), this.points.get("E3D5")));
		
		this.squares.put("K5", new GridSquare( 4, 10,
				this.points.get("D4E2"), this.points.get("D4E1"),
				this.points.get("E1D5"), this.points.get("E2D5")));
		
		this.squares.put("L5", new GridSquare( 4, 11,
				this.points.get("D4E1"), this.points.get("D4"),
				this.points.get("D5"), this.points.get("E1D5")));
		
		// Panels - Row 5
		this.squares.put("A6", new GridSquare( 5, 0,
				this.points.get("F3"), this.points.get("F3E7"),
				this.points.get("F2E7"), this.points.get("F2")));
		
		this.squares.put("B6", new GridSquare( 5, 1,
				this.points.get("F3E7"), this.points.get("F3E6"),
				this.points.get("F2E6"), this.points.get("F2E7")));
		
		this.squares.put("C6", new GridSquare( 5, 2,
				this.points.get("F3E6"), this.points.get("F3E5"),
				this.points.get("F2E5"), this.points.get("F2E6")));
		
		this.squares.put("D6", new GridSquare( 5, 3,
				this.points.get("F3E5"), this.points.get("E4F3"),
				this.points.get("E4F2"), this.points.get("F2E5")));
		
		this.squares.put("I6", new GridSquare( 5, 8,
				this.points.get("E4F3"), this.points.get("E3D5"),
				this.points.get("E3D6"), this.points.get("E4F2")));
		
		this.squares.put("J6", new GridSquare( 5, 9,
				this.points.get("E3D5"), this.points.get("E2D5"),
				this.points.get("E2D6"), this.points.get("E3D6")));
		
		this.squares.put("K6", new GridSquare( 5, 10,
				this.points.get("E2D5"), this.points.get("E1D5"),
				this.points.get("E1D6"), this.points.get("E2D6")));
		
		this.squares.put("L6", new GridSquare( 5, 11,
				this.points.get("E1D5"), this.points.get("D5"),
				this.points.get("D6"), this.points.get("E1D6")));
		
		// Panels - Row 6
		this.squares.put("A7", new GridSquare( 6, 0,
				this.points.get("F2"), this.points.get("F2E7"),
				this.points.get("F1E7"), this.points.get("F1")));
		
		this.squares.put("B7", new GridSquare( 6, 1,
				this.points.get("F2E7"), this.points.get("F2E6"),
				this.points.get("F1E6"), this.points.get("F1E7")));
		
		this.squares.put("C7", new GridSquare( 6, 2,
				this.points.get("F2E6"), this.points.get("F2E5"),
				this.points.get("F1E5"), this.points.get("F1E6")));
		
		this.squares.put("D7", new GridSquare( 6, 3,
				this.points.get("F2E5"), this.points.get("E4F2"),
				this.points.get("E4F1"), this.points.get("F1E5")));
		
		this.squares.put("I7", new GridSquare( 6, 8,
				this.points.get("E4F2"), this.points.get("E3D6"),
				this.points.get("E3D7"), this.points.get("E4F1")));
		
		this.squares.put("J7", new GridSquare( 6, 9,
				this.points.get("E3D6"), this.points.get("E2D6"),
				this.points.get("E2D7"), this.points.get("E3D7")));
		
		this.squares.put("K7", new GridSquare( 6, 10,
				this.points.get("E2D6"), this.points.get("E1D6"),
				this.points.get("E1D7"), this.points.get("E2D7")));
		
		this.squares.put("L7", new GridSquare( 6, 11,
				this.points.get("E1D6"), this.points.get("D6"),
				this.points.get("D7"), this.points.get("E1D7")));
		
		// Panels - Row 7
		this.squares.put("A8", new GridSquare( 7, 0,
				this.points.get("F1"), this.points.get("F1E7"),
				this.points.get("E7"), this.points.get("F")));
		
		this.squares.put("B8", new GridSquare( 7, 1,
				this.points.get("F1E7"), this.points.get("F1E6"),
				this.points.get("E6"), this.points.get("E7")));
		
		this.squares.put("C8", new GridSquare( 7, 2,
				this.points.get("F1E6"), this.points.get("F1E5"),
				this.points.get("E5"), this.points.get("E6")));
		
		this.squares.put("D8", new GridSquare( 7, 3,
				this.points.get("F1E5"), this.points.get("E4F1"),
				this.points.get("E4"), this.points.get("E5")));
		
		this.squares.put("I8", new GridSquare( 7, 8,
				this.points.get("E4F1"), this.points.get("E3D7"),
				this.points.get("E3"), this.points.get("E4")));
		
		this.squares.put("J8", new GridSquare( 7, 9,
				this.points.get("E3D7"), this.points.get("E2D7"),
				this.points.get("E2"), this.points.get("E3")));
		
		this.squares.put("K8", new GridSquare( 7, 10,
				this.points.get("E2D7"), this.points.get("E1D7"),
				this.points.get("E1"), this.points.get("E2")));
		
		this.squares.put("L8", new GridSquare( 7, 11,
				this.points.get("E1D7"), this.points.get("D7"),
				this.points.get("E"), this.points.get("E1")));
		
		// Panels - Row 8
		this.squares.put("H9", new GridSquare( 8, 7,
				this.points.get("B4"), this.points.get("B4C1"),
				this.points.get("C1B5"), this.points.get("B5")));
		
		this.squares.put("G9", new GridSquare( 8, 6,
				this.points.get("B4C1"), this.points.get("B4C2"),
				this.points.get("C2B5"), this.points.get("C1B5")));
		
		this.squares.put("F9", new GridSquare( 8, 5,
				this.points.get("B4C2"), this.points.get("B4C3"),
				this.points.get("C3B5"), this.points.get("C2B5")));
		
		this.squares.put("E9", new GridSquare( 8, 4,
				this.points.get("B4C3"), this.points.get("Center"),
				this.points.get("C4D3"), this.points.get("C3B5")));
		
		this.squares.put("I9", new GridSquare( 8, 8,
				this.points.get("Center"), this.points.get("D4E3"),
				this.points.get("D3C5"), this.points.get("C4D3")));

		this.squares.put("J9", new GridSquare( 8, 9,
				this.points.get("D4E3"), this.points.get("D4E2"),
				this.points.get("D3C6"), this.points.get("D3C5")));
		
		this.squares.put("K9", new GridSquare( 8, 10,
				this.points.get("D4E2"), this.points.get("D4E1"),
				this.points.get("D3C7"), this.points.get("D3C6")));
		
		this.squares.put("L9", new GridSquare( 8, 11,
				this.points.get("D4E1"), this.points.get("D4"),
				this.points.get("D3"), this.points.get("D3C7")));
		
		// Panels - Row 9
		this.squares.put("H10", new GridSquare( 9, 7,
				this.points.get("B5"), this.points.get("C1B5"),
				this.points.get("C1B6"), this.points.get("B6")));
		
		this.squares.put("G10", new GridSquare( 9, 6,
				this.points.get("C1B5"), this.points.get("C2B5"),
				this.points.get("C2B6"), this.points.get("C1B6")));
		
		this.squares.put("F10", new GridSquare( 9, 5,
				this.points.get("C3B5"), this.points.get("C2B5"),
				this.points.get("C2B6"), this.points.get("C3B6")));
		
		this.squares.put("E10", new GridSquare( 9, 4,
				this.points.get("C4D3"), this.points.get("C3B5"),
				this.points.get("C3B6"), this.points.get("C4D2")));
		
		this.squares.put("I10", new GridSquare( 9, 8,
				this.points.get("C4D3"), this.points.get("D3C5"),
				this.points.get("D2C5"), this.points.get("C4D2")));
		
		this.squares.put("J10", new GridSquare( 9, 9,
				this.points.get("D3C5"), this.points.get("D3C6"),
				this.points.get("D2C6"), this.points.get("D2C5")));

		this.squares.put("K10", new GridSquare( 9, 10,
				this.points.get("D3C6"), this.points.get("D3C7"),
				this.points.get("D2C7"), this.points.get("D2C6")));
		
		this.squares.put("L10", new GridSquare( 9, 11,
				this.points.get("D3C7"), this.points.get("D3"),
				this.points.get("D2"), this.points.get("D2C7")));
		
		// Panels - Row 10
		this.squares.put("H11", new GridSquare( 10, 7,
				this.points.get("B6"), this.points.get("C1B6"),
				this.points.get("C1B7"), this.points.get("B7")));
		
		this.squares.put("G11", new GridSquare( 10, 6,
				this.points.get("C1B6"), this.points.get("C2B6"),
				this.points.get("C2B7"), this.points.get("C1B7")));
		
		this.squares.put("F11", new GridSquare( 10, 5,
				this.points.get("C2B6"), this.points.get("C3B6"),
				this.points.get("C3B7"), this.points.get("C2B7")));
		
		this.squares.put("E11", new GridSquare( 10, 4,
				this.points.get("C3B6"), this.points.get("C4D2"),
				this.points.get("C4D1"), this.points.get("C3B7")));
		
		this.squares.put("I11", new GridSquare( 10, 8,
				this.points.get("C4D2"), this.points.get("D2C5"),
				this.points.get("D1C5"), this.points.get("C4D1")));
		
		this.squares.put("J11", new GridSquare( 10, 9,
				this.points.get("D2C5"), this.points.get("D2C6"),
				this.points.get("D1C6"), this.points.get("D1C5")));
		
		this.squares.put("K11", new GridSquare( 10, 10,
				this.points.get("D2C6"), this.points.get("D2C7"),
				this.points.get("D1C7"), this.points.get("D1C6")));

		this.squares.put("L11", new GridSquare( 10, 11,
				this.points.get("D2C7"), this.points.get("D2"),
				this.points.get("D1"), this.points.get("D1C7")));
		
		// Panels - Row 11
		this.squares.put("H12", new GridSquare( 11, 7,
				this.points.get("B7"), this.points.get("C1B7"),
				this.points.get("C1"), this.points.get("C")));
		
		this.squares.put("G12", new GridSquare( 11, 6,
				this.points.get("C1B7"), this.points.get("C2B7"),
				this.points.get("C2"), this.points.get("C1")));
		
		this.squares.put("F12", new GridSquare( 11, 5,
				this.points.get("C2B7"), this.points.get("C3B7"),
				this.points.get("C3"), this.points.get("C2")));
		
		this.squares.put("E12", new GridSquare( 11, 4,
				this.points.get("C3B7"), this.points.get("C4D1"),
				this.points.get("C4"), this.points.get("C3")));
		
		this.squares.put("I12", new GridSquare( 11, 8,
				this.points.get("C4D1"), this.points.get("D1C5"),
				this.points.get("C5"), this.points.get("C4")));
		
		this.squares.put("J12", new GridSquare( 11, 9,
				this.points.get("D1C5"), this.points.get("D1C6"),
				this.points.get("C6"), this.points.get("C5")));
		
		this.squares.put("K12", new GridSquare( 11, 10,
				this.points.get("D1C6"), this.points.get("D1C7"),
				this.points.get("C7"), this.points.get("C6")));
		
		this.squares.put("L12", new GridSquare( 11, 11,
				this.points.get("D1C7"), this.points.get("D1"),
				this.points.get("D"), this.points.get("C7")));
	}


    /**
     * setup: puts all squares into a tree structure
     *
     *
     *
     */
	private void setupSquareTree()
	{
		// set whole hexagon as the root node
		this.panelRoot = new Node<GridSquare>("root", new GridSquare());
		
		List<Node<GridSquare>> children = new ArrayList<Node<GridSquare>>();
		
		children.add(new Node<GridSquare>("Section A", this.squares.get("Section A")));
		children.add(new Node<GridSquare>("Section B", this.squares.get("Section B")));
		children.add(new Node<GridSquare>("Section C", this.squares.get("Section C")));
		children.add(new Node<GridSquare>("Section D", this.squares.get("Section D")));
		children.add(new Node<GridSquare>("Section E", this.squares.get("Section E")));
		children.add(new Node<GridSquare>("Section F", this.squares.get("Section F")));
		this.panelRoot.addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("Section A1F7", this.squares.get("Section A1F7")));
		children.add(new Node<GridSquare>("Section A3F7", this.squares.get("Section A3F7")));
		children.add(new Node<GridSquare>("Section A1F5", this.squares.get("Section A1F5")));
		children.add(new Node<GridSquare>("Section A3F5", this.squares.get("Section A3F5")));
		this.panelRoot.getChild("Section A").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("Section B1A7", this.squares.get("Section B1A7")));
		children.add(new Node<GridSquare>("Section B3A7", this.squares.get("Section B3A7")));
		children.add(new Node<GridSquare>("Section B1A5", this.squares.get("Section B1A5")));
		children.add(new Node<GridSquare>("Section B3A5", this.squares.get("Section B3A5")));
		this.panelRoot.getChild("Section B").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("Section C1B7", this.squares.get("Section C1B7")));
		children.add(new Node<GridSquare>("Section C3B7", this.squares.get("Section C3B7")));
		children.add(new Node<GridSquare>("Section C1B5", this.squares.get("Section C1B5")));
		children.add(new Node<GridSquare>("Section C3B5", this.squares.get("Section C3B5")));
		this.panelRoot.getChild("Section C").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("Section D1C7", this.squares.get("Section D1C7")));
		children.add(new Node<GridSquare>("Section D3C7", this.squares.get("Section D3C7")));
		children.add(new Node<GridSquare>("Section D1C5", this.squares.get("Section D1C5")));
		children.add(new Node<GridSquare>("Section D3C5", this.squares.get("Section D3C5")));
		this.panelRoot.getChild("Section D").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("Section E1D7", this.squares.get("Section E1D7")));
		children.add(new Node<GridSquare>("Section E3D7", this.squares.get("Section E3D7")));
		children.add(new Node<GridSquare>("Section E1D5", this.squares.get("Section E1D5")));
		children.add(new Node<GridSquare>("Section E3D5", this.squares.get("Section E3D5")));
		this.panelRoot.getChild("Section E").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("Section F1E7", this.squares.get("Section F1E7")));
		children.add(new Node<GridSquare>("Section F3E7", this.squares.get("Section F3E7")));
		children.add(new Node<GridSquare>("Section F1E5", this.squares.get("Section F1E5")));
		children.add(new Node<GridSquare>("Section F3E5", this.squares.get("Section F3E5")));
		this.panelRoot.getChild("Section F").addChildren(children);
		children.clear();
		
		
		children.add(new Node<GridSquare>("A1", this.squares.get("A1")));
		children.add(new Node<GridSquare>("B1", this.squares.get("B1")));
		children.add(new Node<GridSquare>("A2", this.squares.get("A2")));
		children.add(new Node<GridSquare>("B2", this.squares.get("B2")));
		this.panelRoot.getNode("Section A1F7").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("C1", this.squares.get("C1")));
		children.add(new Node<GridSquare>("D1", this.squares.get("D1")));
		children.add(new Node<GridSquare>("C2", this.squares.get("C2")));
		children.add(new Node<GridSquare>("D2", this.squares.get("D2")));
		this.panelRoot.getNode("Section A3F7").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("E1", this.squares.get("E1")));
		children.add(new Node<GridSquare>("F1", this.squares.get("F1")));
		children.add(new Node<GridSquare>("E2", this.squares.get("E2")));
		children.add(new Node<GridSquare>("F2", this.squares.get("F2")));
		this.panelRoot.getNode("Section B1A5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("G1", this.squares.get("G1")));
		children.add(new Node<GridSquare>("H1", this.squares.get("H1")));
		children.add(new Node<GridSquare>("G2", this.squares.get("G2")));
		children.add(new Node<GridSquare>("H2", this.squares.get("H2")));
		this.panelRoot.getNode("Section B1A7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<GridSquare>("A3", this.squares.get("A3")));
		children.add(new Node<GridSquare>("B3", this.squares.get("B3")));
		children.add(new Node<GridSquare>("A4", this.squares.get("A4")));
		children.add(new Node<GridSquare>("B4", this.squares.get("B4")));
		this.panelRoot.getNode("Section A1F5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("C3", this.squares.get("C3")));
		children.add(new Node<GridSquare>("D3", this.squares.get("D3")));
		children.add(new Node<GridSquare>("C4", this.squares.get("C4")));
		children.add(new Node<GridSquare>("D4", this.squares.get("D4")));
		this.panelRoot.getNode("Section A3F5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("E3", this.squares.get("E3")));
		children.add(new Node<GridSquare>("F3", this.squares.get("F3")));
		children.add(new Node<GridSquare>("E4", this.squares.get("E4")));
		children.add(new Node<GridSquare>("F4", this.squares.get("F4")));
		this.panelRoot.getNode("Section B3A5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("G3", this.squares.get("G3")));
		children.add(new Node<GridSquare>("H3", this.squares.get("H3")));
		children.add(new Node<GridSquare>("G4", this.squares.get("G4")));
		children.add(new Node<GridSquare>("H4", this.squares.get("H4")));
		this.panelRoot.getNode("Section B3A7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<GridSquare>("A5", this.squares.get("A5")));
		children.add(new Node<GridSquare>("B5", this.squares.get("B5")));
		children.add(new Node<GridSquare>("A6", this.squares.get("A6")));
		children.add(new Node<GridSquare>("B6", this.squares.get("B6")));
		this.panelRoot.getNode("Section F3E7").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("C5", this.squares.get("C5")));
		children.add(new Node<GridSquare>("D5", this.squares.get("D5")));
		children.add(new Node<GridSquare>("C6", this.squares.get("C6")));
		children.add(new Node<GridSquare>("D6", this.squares.get("D6")));
		this.panelRoot.getNode("Section F3E5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("I5", this.squares.get("I5")));
		children.add(new Node<GridSquare>("J5", this.squares.get("J5")));
		children.add(new Node<GridSquare>("I6", this.squares.get("I6")));
		children.add(new Node<GridSquare>("J6", this.squares.get("J6")));
		this.panelRoot.getNode("Section E3D5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("K5", this.squares.get("K5")));
		children.add(new Node<GridSquare>("L5", this.squares.get("L5")));
		children.add(new Node<GridSquare>("K6", this.squares.get("K6")));
		children.add(new Node<GridSquare>("L6", this.squares.get("L6")));
		this.panelRoot.getNode("Section E1D5").addChildren(children);
		children.clear();
		
		
		children.add(new Node<GridSquare>("A7", this.squares.get("A7")));
		children.add(new Node<GridSquare>("B7", this.squares.get("B7")));
		children.add(new Node<GridSquare>("A8", this.squares.get("A8")));
		children.add(new Node<GridSquare>("B8", this.squares.get("B8")));
		this.panelRoot.getNode("Section F1E7").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("C7", this.squares.get("C7")));
		children.add(new Node<GridSquare>("D7", this.squares.get("D7")));
		children.add(new Node<GridSquare>("C8", this.squares.get("C8")));
		children.add(new Node<GridSquare>("D8", this.squares.get("D8")));
		this.panelRoot.getNode("Section F1E5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("I7", this.squares.get("I7")));
		children.add(new Node<GridSquare>("J7", this.squares.get("J7")));
		children.add(new Node<GridSquare>("I8", this.squares.get("I8")));
		children.add(new Node<GridSquare>("J8", this.squares.get("J8")));
		this.panelRoot.getNode("Section E3D7").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("K7", this.squares.get("K7")));
		children.add(new Node<GridSquare>("L7", this.squares.get("L7")));
		children.add(new Node<GridSquare>("K8", this.squares.get("K8")));
		children.add(new Node<GridSquare>("L8", this.squares.get("L8")));
		this.panelRoot.getNode("Section E1D7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<GridSquare>("H9", this.squares.get("H9")));
		children.add(new Node<GridSquare>("G9", this.squares.get("G9")));
		children.add(new Node<GridSquare>("H10", this.squares.get("H10")));
		children.add(new Node<GridSquare>("G10", this.squares.get("G10")));
		this.panelRoot.getNode("Section C1B5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("F9", this.squares.get("F9")));
		children.add(new Node<GridSquare>("E9", this.squares.get("E9")));
		children.add(new Node<GridSquare>("F10", this.squares.get("F10")));
		children.add(new Node<GridSquare>("E10", this.squares.get("E10")));
		this.panelRoot.getNode("Section C3B5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("I9", this.squares.get("I9")));
		children.add(new Node<GridSquare>("J9", this.squares.get("J9")));
		children.add(new Node<GridSquare>("I10", this.squares.get("I10")));
		children.add(new Node<GridSquare>("J10", this.squares.get("J10")));
		this.panelRoot.getNode("Section D3C5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("K9", this.squares.get("K9")));
		children.add(new Node<GridSquare>("L9", this.squares.get("L9")));
		children.add(new Node<GridSquare>("K10", this.squares.get("K10")));
		children.add(new Node<GridSquare>("L10", this.squares.get("L10")));
		this.panelRoot.getNode("Section D3C7").addChildren(children);
		children.clear();
		
		
		children.add(new Node<GridSquare>("H11", this.squares.get("H11")));
		children.add(new Node<GridSquare>("G11", this.squares.get("G11")));
		children.add(new Node<GridSquare>("H12", this.squares.get("H12")));
		children.add(new Node<GridSquare>("G12", this.squares.get("G12")));
		this.panelRoot.getNode("Section C1B7").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("F11", this.squares.get("F11")));
		children.add(new Node<GridSquare>("E11", this.squares.get("E11")));
		children.add(new Node<GridSquare>("F12", this.squares.get("F12")));
		children.add(new Node<GridSquare>("E12", this.squares.get("E12")));
		this.panelRoot.getNode("Section C3B7").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("I11", this.squares.get("I11")));
		children.add(new Node<GridSquare>("J11", this.squares.get("J11")));
		children.add(new Node<GridSquare>("I12", this.squares.get("I12")));
		children.add(new Node<GridSquare>("J12", this.squares.get("J12")));
		this.panelRoot.getNode("Section D1C5").addChildren(children);
		children.clear();
		
		children.add(new Node<GridSquare>("K11", this.squares.get("K11")));
		children.add(new Node<GridSquare>("L11", this.squares.get("L11")));
		children.add(new Node<GridSquare>("K12", this.squares.get("K12")));
		children.add(new Node<GridSquare>("L12", this.squares.get("L12")));
		this.panelRoot.getNode("Section D1C7").addChildren(children);
		children.clear();
	}


    /**
     * setup: sets all points of the chessboard
     *
     *
     *
     */
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


    /**
     * get a certain square by its name (hashmap key)
     *
     * @param name hashmap key (A1, B2 ..)
     * @return GridSquare
     */
	public GridSquare getSquare(String name)
	{
		if (this.squares.containsKey(name))
			return this.squares.get(name);
		return null;
	}


    /**
     * get a certain square by its chessboard (x.y) position (hashmap key)
     *
     * @param boardPosX chessboard x position
     * @param boardPosY chessboard y position
     * @return GridSquare
     */
	public GridSquare getSquare(int boardPosX, int boardPosY)
	{
		for(Map.Entry<String, GridSquare> square : this.squares.entrySet())
			if (square.getValue().hasBoardSquare())
				if (square.getValue().getBoardSquare().getPosX() == boardPosX &&
					square.getValue().getBoardSquare().getPosY() == boardPosY)
						return square.getValue();
		return null;
	}


    /**
     * get the particular square with the certain point inside
     *
     * @param point point to check for
     * @return GridSquare
     */
	public GridSquare getSquareWithinPoint(Point point)
	{
		if (this.hexagon.isPointWithinHexagon(point))
			return depthFirstSearch(point);
		return null;
	}


    /**
     * searches in the square tree with depth first like search algorithm
     *
     * @param point point to check for
     * @return GridSquare
     */
	private GridSquare depthFirstSearch(Point point)
	{
		return searchInTree(this.panelRoot, point);
	}


    /**
     * searches in a certain level of the square tree with depth first like search algorithm
     *
     * @param parentNode node to check and its children
     * @param point point to check for
     * @return GridSquare
     */
	private GridSquare searchInTree(Node<GridSquare> parentNode, Point point)
	{
		List<Node<GridSquare>> childNodes = parentNode.getChildren();
		
		for (Node<GridSquare> node : childNodes)
		{
			if (node.getData().isPointInside(point))
			{
				if (node.hasChildren())
					return searchInTree(node, point);
				else
					return node.getData();
			}
		}
		return null;
	}


    /**
     * adds all possible moves as squares to a stack
     *
     * @param square current square with piece to check for
     *
     */
	public void displayPossibleMoves(GridSquare square)
    {
        this.possibleMoves = square.getBoardSquare().getPiece().allMoves();

        // color when unable to move somewhere
        if (this.possibleMoves.size() == 0)
		{
			square.setHighlightColor(this.squareStuckColor);
			square.setHighlight(true);
		}

		// moves possible
        for (Square possibleMove : this.possibleMoves)
		{
			try
			{
				// convert to grid square object
				GridSquare gridSquare = getSquare(possibleMove.getPosX(), possibleMove.getPosY());

				// change highlight color according if there is a piece on the square or not
				if (gridSquare.getBoardSquare().getPiece() != null)
					gridSquare.setHighlightColor(this.squareStrikeColor);
				else
					gridSquare.setHighlightColor(this.squareHighlight);

				// display the color
				gridSquare.setHighlight(true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Possible move square is not part of the chessboard grid!");
			}
		}
    }


	/**
	 * disable displaying possible moves
	 *
	 *
	 *
	 */
	public void stopDisplayingPossibleMoves()
	{
		for(Map.Entry<String, GridSquare> square : this.squares.entrySet())
			square.getValue().setHighlight(false);
	}


    /**
     * draws visual objects
     *
     * @param graphics graphics
     *
     */
	@Override
	public void draw(Graphics graphics)
	{
		this.labeling.draw(graphics);

		for(Map.Entry<String, GridSquare> square : this.squares.entrySet())
			square.getValue().draw(graphics);
	}
}
