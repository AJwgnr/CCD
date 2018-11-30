package com.ovgu.ccd.gui.chessboardListener;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.jchess.ThreePlayerChessboard;
import com.ovgu.ccd.pieces.*;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import static org.mockito.Mockito.mock;


public class ChessboardListener implements MouseListener 
{

	private ChessboardGrid grid = null;
	private com.ovgu.ccd.pieces.Square lastClickedSquare = null;
	private boolean pieceSelected = false;

	private ThreePlayerChessboard board = null;


	public ChessboardListener(Point center, int radius)
	{
		this.grid = new ChessboardGrid(center, radius);
		this.grid.addMouseListener(this);

		board = new ThreePlayerChessboard();
		Player whitePlayer = new Player("John", Player.Colors.WHITE.name());
		King whiteKing = (King) PieceFactory.getPiece(board, whitePlayer, Piece.PieceTypes.KING);
		board.setPiece(whiteKing, 1,1);
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

	private void movePiece(com.ovgu.ccd.pieces.Square newPosition)
	{
		if (this.lastClickedSquare != null)
			if (this.lastClickedSquare.getPiece() != null)
				this.lastClickedSquare.getPiece().setSquare(newPosition);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Square clickedSquare = getClickedSquare(e);
        clickedSquare.print();

		// move previously selected piece
		if (this.pieceSelected)
			movePiece(clickedSquare);

		this.lastClickedSquare = clickedSquare;
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
