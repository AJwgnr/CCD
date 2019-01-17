package com.ovgu.ccd.gui.chessboardListener;

import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.PlayerSequenceManager;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.GameoverWindow;
import com.ovgu.ccd.gui.PawnPromotionWindow;
import com.ovgu.ccd.pieces.Pawn;
import com.ovgu.ccd.pieces.Piece;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


/**
 * @author  CCD DeepBlue team
 * @version 1.0
 * @since
 */
public class ChessboardListener implements MouseListener 
{
	private ChessboardGrid grid = null;
	private GridSquare squareBuffer = null;
	PlayerSequenceManager sequenceManager = null;


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
	 * restricts the chessboard listener to the sequence of moves provided by the PlayerSequenceManager
	 *
	 */
	public void setListenerRestrictions(PlayerSequenceManager sequenceManager)
	{
		this.sequenceManager = sequenceManager;
	}


	/**
	 * checks whether a move is valid or not
	 *
	 * @param
	 * @return 	true - valid
	 *			false - in-valid
	 */
	public boolean isMoveValid(GridSquare square)
	{
		if (this.sequenceManager == null)
		{
			return true;
		}
		else if (square.getBoardSquare().getPiece() != null
				&& this.sequenceManager.getCurrentPlayer() == square.getBoardSquare().getPiece().getPlayer())
		{
			return true;
		}
		return false;
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
	private void movePiece(GridSquare origin, GridSquare target) {
		if (origin != null && target != null) {
			Piece piece = origin.getBoardSquare().getPiece();
			if (piece != null) {
				ThreePlayerChessboard board = (ThreePlayerChessboard) piece.getChessboard();
				board.setPiece(piece, target.getBoardSquare().getPosX(), target.getBoardSquare().getPosY());
				checkForPromotion(piece);
			}
		}
	}


	/**
	 * handles promotion
	 *
	 */
	private void checkForPromotion(Piece piece)
	{
		if ("Pawn".equals(piece.name) && ((Pawn) piece).canBePromoted()) {
			String colorOfPiece = "";
			if (piece.getColor() == Player.Colors.WHITE) {
				colorOfPiece = "W";
			}
			else if (piece.getColor() == Player.Colors.GREY) {
				colorOfPiece = "G";
			}
			else { // black
				colorOfPiece = "B";
			}

			PawnPromotionWindow promotion = new PawnPromotionWindow(colorOfPiece);
			((Pawn)piece).promote(promotion.getResult());
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
			if (isMoveValid(clickedSquare))
			{
				this.squareBuffer = clickedSquare;
				this.grid.displayPossibleMoves(clickedSquare);
			}
			else
				this.sequenceManager.print();
		}

		// piece already selected
		else
		{
			// move piece
			if (this.squareBuffer.getBoardSquare().getPiece().allMoves().contains(clickedSquare.getBoardSquare()))
			{
				movePiece(this.squareBuffer, clickedSquare);
				this.squareBuffer = null;
				this.grid.stopDisplayingPossibleMoves();
				this.checkForGameover(clickedSquare);
				if (this.sequenceManager != null)
					this.sequenceManager.moveDone();
			}

			// select new piece
			else if (clickedSquare.getBoardSquare().getPiece() != null &&
					this.squareBuffer.getBoardSquare().getPiece().getColor() ==
							clickedSquare.getBoardSquare().getPiece().getColor())
			{
				this.grid.stopDisplayingPossibleMoves();
				this.squareBuffer = clickedSquare;
				this.grid.displayPossibleMoves(clickedSquare);
			}
		}
	}


	/**
	 * this method checks for a gameover situation after the move finished
	 *
	 * @param clickedSquare the clicked Square after the moved is completed
	 */
	private void checkForGameover(GridSquare clickedSquare)
	{
		Piece piece = clickedSquare.getBoardSquare().getPiece();
		ThreePlayerChessboard board = (ThreePlayerChessboard) piece.getChessboard();
		try {
			if (board.isGameFinished())
			{
				String nameOfPlayer = piece.getPlayer().getName();
				new GameoverWindow(nameOfPlayer);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
