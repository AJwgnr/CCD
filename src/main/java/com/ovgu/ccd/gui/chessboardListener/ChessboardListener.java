package com.ovgu.ccd.gui.chessboardListener;

import com.ovgu.ccd.applogic.ThreePlayerChessboard;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


/**
 * @author  CCD DeepBlue team
 * @version 1.0
 * @since   1.0
 */
public class ChessboardListener implements MouseListener 
{
	private ChessboardGrid grid = null;
	private GridSquare squareBuffer = null;


	/**
	 * constructor
	 *
	 * @param grid chessboard gird to listen to
	 *
	 */
	public ChessboardListener(ChessboardGrid grid)
	{
		this.grid = grid;
		this.grid.addMouseListener(this);
	}


	/**
	 * puts all grid objects to the grid
	 *
	 *
	 * @return JPanel main panel
	 */
	public JPanel getPanel()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(this.grid);
		return mainPanel;
	}


	/**
	 * gets clicked square
	 *
	 * @param e mouse event -> clicked point
	 * @return GridSquare
	 */
	private GridSquare getClickedSquare(MouseEvent e)
	{
		return grid.getSquareWithinPoint(new Point (e.getX(), e.getY()));
	}


	/**
	 * moves a piece to a certain target square
	 *
	 * @param origin start square
	 * @param target target square
	 *
	 */
	private void movePiece(GridSquare origin, GridSquare target)
    {
        if (origin != null && target != null)
        {
            if (origin.getBoardSquare().getPiece() != null)
            {
            	ThreePlayerChessboard board = (ThreePlayerChessboard) origin.getBoardSquare().getPiece().getChessboard();
                board.setPiece(origin.getBoardSquare().getPiece(), target.getBoardSquare().getPosX(), target.getBoardSquare().getPosY());
            }
        }
    }


	/**
	 * handles piece interactions: moving or selecting
	 *
	 * @param clickedSquare clicked square
	 *
	 */
    private void handlePieceInteraction(GridSquare clickedSquare)
	{
		// select piece
		if (this.squareBuffer == null)
		{
			this.squareBuffer = clickedSquare;
			this.grid.displayPossibleMoves(clickedSquare);
		}

		// move piece
		else if (this.squareBuffer.getBoardSquare().getPiece().allMoves().contains(clickedSquare.getBoardSquare()))
		{
			movePiece(this.squareBuffer, clickedSquare);
			this.squareBuffer = null;
			this.grid.stopDisplayingPossibleMoves();
		}

		// select new piece
		else if (!this.squareBuffer.getBoardSquare().getPiece().allMoves().contains(clickedSquare.getBoardSquare()) &&
				 clickedSquare.getBoardSquare().getPiece() != null &&
				 this.squareBuffer.getBoardSquare().getPiece().getColor() == clickedSquare.getBoardSquare().getPiece().getColor())
		{
			this.grid.stopDisplayingPossibleMoves();
			this.squareBuffer = clickedSquare;
			this.grid.displayPossibleMoves(clickedSquare);
		}
	}


	/**
	 * handles chessboard clicks
	 *
	 * @param e mouse event -> clicked point
	 *
	 */
	private void handleChessboardClicks(MouseEvent e)
    {
		GridSquare clickedSquare = getClickedSquare(e);
        if(clickedSquare != null)
        {
            clickedSquare.getBoardSquare().print();

            if (clickedSquare.getBoardSquare().getPiece() != null || this.squareBuffer != null)
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
