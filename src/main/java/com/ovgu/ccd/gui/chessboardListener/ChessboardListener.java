package com.ovgu.ccd.gui.chessboardListener;

import com.ovgu.ccd.pieces.*;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class ChessboardListener implements MouseListener 
{
	private ChessboardGrid grid = null;


	public ChessboardListener(Point center, int radius)
	{
		this.grid = new ChessboardGrid(center, radius);
		this.grid.addMouseListener(this);
	}


	// chessboard grid to keep track of each square
	public JPanel getPanel()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(this.grid);
		return mainPanel;
	}

	private Square getClickedSquare(MouseEvent e)
	{
		Square clickedSquare = grid.getSquareWithinPoint(
				new Point (e.getX(), e.getY()));
		return clickedSquare;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Square clickedSquare = getClickedSquare(e);
        if(clickedSquare != null)
        {
            clickedSquare.print();
            this.grid.getPiece().setSquare(clickedSquare);
            this.grid.redraw();
        }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
