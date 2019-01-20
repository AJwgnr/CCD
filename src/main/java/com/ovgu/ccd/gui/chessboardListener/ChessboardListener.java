package com.ovgu.ccd.gui.chessboardListener;

import com.ovgu.ccd.applogic.*;
import com.ovgu.ccd.gui.GameoverWindow;
import com.ovgu.ccd.gui.PawnPromotionWindow;
import com.ovgu.ccd.pieces.Pawn;
import com.ovgu.ccd.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 */
public class ChessboardListener implements MouseListener 
{
	private ChessboardGrid grid = null;
	private GridSquare squareBuffer = null;
	PlayerSequenceManager sequenceManager = null;
	ThreePlayerChessboard board = null;


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
	 * @param sequenceManager sequence manager
	 */
	public void setListenerRestrictions(PlayerSequenceManager sequenceManager)
	{
		this.sequenceManager = sequenceManager;
	}


	/**
	 * checks whether a move is valid or not
	 *
	 * @param square of the move
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
				this.board.setPiece(piece, target.getBoardSquare().getPosX(), target.getBoardSquare().getPosY());
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
		try {
			if (this.board.isGameFinished())
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

            if (clickedSquare.getBoardSquare().getPiece() != null || this.squareBuffer != null) {
				if (this.board == null) {
					this.board = (ThreePlayerChessboard) clickedSquare.getBoardSquare().getPiece().getChessboard();
				}
            	handlePieceInteraction(clickedSquare);
			}

			ThreePlayerChessboard board = (ThreePlayerChessboard) clickedSquare.getBoardSquare().getPiece().getChessboard();
			this.checkForCheckSituation(board);
			this.grid.redraw();
			this.checkForGameover(clickedSquare);
        }
    }


	/**
	 * check whether a players king is in a check situation
	 * if so then a certain highlight color will be displayed
	 * (checks every king in game)
	 *
	 * @param board	chessboard to get all kings
	 */
	private void checkForCheckSituation(final ThreePlayerChessboard board)
	{
		Player.Colors colors[] = {
				Player.Colors.WHITE,
				Player.Colors.GREY,
				Player.Colors.BLACK,
		};

		for (int i = 0; i < colors.length; i++)
		{
			GridSquare square = this.grid.getSquare(
					board.myKing(colors[i]).getPosX(),
					board.myKing(colors[i]).getPosY());
			try {
				if (!new CheckController(board, board.myKing(colors[i]), board.myKing(colors[i]), null).isSafe()
						|| new CheckMateController(board, board.myKing(colors[i])).isCheckMate()) {
					this.grid.displayCheckSituation(square);
				}
				else {
					this.grid.StopDisplayCheckSituation(square);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
        handleChessboardClicks(e);
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(final MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(final MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
