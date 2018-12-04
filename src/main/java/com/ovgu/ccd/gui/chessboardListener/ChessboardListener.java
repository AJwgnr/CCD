package com.ovgu.ccd.gui.chessboardListener;

import com.ovgu.ccd.pieces.*;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class ChessboardListener implements MouseListener 
{
	private ChessboardGrid grid = null;
	private Square squareBuffer = null;


	public ChessboardListener(ChessboardGrid grid)
	{
		this.grid = grid;
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

	private void movePiece(Square origin, Square target)
    {
        if (origin != null && target != null)
        {
            if (origin.getPiece() != null)
            {
                target.setPiece(origin.getPiece());
                origin.setPiece(null);
            }
        }
    }

    private void handlePieceInteraction(Square clickedSquare)
	{
		// select piece
		if (this.squareBuffer == null)
		{
			this.squareBuffer = clickedSquare;
			this.grid.displayPossibleMoves(clickedSquare);
		}

		// move piece
		else if (this.squareBuffer.getPiece().allMoves().contains(clickedSquare))
		{
			movePiece(this.squareBuffer, clickedSquare);
			this.squareBuffer = null;
			this.grid.displayPossibleMoves(false);
		}
	}

	private void handleChessboardClicks(MouseEvent e)
    {
        Square clickedSquare = getClickedSquare(e);
        if(clickedSquare != null)
        {
            clickedSquare.print();

  			if (clickedSquare.getPiece() != null)
				handlePieceInteraction(clickedSquare);

            this.grid.redraw();
        }
    }

	@Override
	public void mouseClicked(MouseEvent e)
	{
        handleChessboardClicks(e);
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
