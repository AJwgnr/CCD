package com.ovgu.ccd.gui.chessboardListener;

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
	
	public JPanel getPanel()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(this.grid);
		return mainPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		ChessPanel clickedPanel = grid.getPanelWithinPoint(new Point (e.getX(), e.getY()));
		if (clickedPanel != null)
			clickedPanel.printPanel();
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
