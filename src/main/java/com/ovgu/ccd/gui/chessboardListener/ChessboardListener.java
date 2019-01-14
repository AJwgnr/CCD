package com.ovgu.ccd.gui.chessboardListener;

import com.ovgu.ccd.applogic.ThreePlayerChessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * @author CCD DeepBlue team
 * @version 1.0
 * @since
 */
public class ChessboardListener implements MouseListener {
    private ChessboardGrid grid = null;
    private GridSquare squareBuffer = null;


    /**
     * constructor
     *
     * @param grid chessboard gird to listen to
     */
    public ChessboardListener(final ChessboardGrid grid) {
        this.grid = grid;
        this.grid.addMouseListener(this);
    }


    /**
     * puts all grid objects to the grid
     *
     * @return JPanel main panel
     */
    public JPanel getPanel() {
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
    private GridSquare getClickedSquare(final MouseEvent e) {
        return grid.getSquareWithinPoint(new Point(e.getX(), e.getY()));
    }


    /**
     * moves a piece to a certain target square
     *
     * @param origin start square
     * @param target target square
     */
    private void movePiece(final GridSquare origin, final GridSquare target) {
        if (origin != null && target != null) {
            if (origin.getBoardSquare().getPiece() != null) {
                ThreePlayerChessboard board = (ThreePlayerChessboard) origin.getBoardSquare().getPiece().getChessboard();
                board.setPiece(origin.getBoardSquare().getPiece(), target.getBoardSquare().getPosX(), target.getBoardSquare().getPosY());
            }
        }
    }


    /**
     * handles piece interactions: moving or selecting
     *
     * @param clickedSquare clicked square
     */
    private void handlePieceInteraction(final GridSquare clickedSquare) {
        // select piece
        if (this.squareBuffer == null) {
            this.squareBuffer = clickedSquare;
            this.grid.displayPossibleMoves(clickedSquare);
        }

        // piece already selected
        else {
            // move piece
            if (this.squareBuffer.getBoardSquare().getPiece().allMoves().contains(clickedSquare.getBoardSquare())) {
                movePiece(this.squareBuffer, clickedSquare);
                this.squareBuffer = null;
                this.grid.stopDisplayingPossibleMoves();
            }

            // select new piece
            else if (clickedSquare.getBoardSquare().getPiece() != null
                    && this.squareBuffer.getBoardSquare().getPiece().getColor()
                    == clickedSquare.getBoardSquare().getPiece().getColor()) {
                this.grid.stopDisplayingPossibleMoves();
                this.squareBuffer = clickedSquare;
                this.grid.displayPossibleMoves(clickedSquare);
            }
        }
    }


    /**
     * handles chessboard clicks
     *
     * @param e mouse event -> clicked point
     */
    private void handleChessboardClicks(final MouseEvent e) {
        GridSquare clickedSquare = getClickedSquare(e);
        if (clickedSquare != null) {
            clickedSquare.getBoardSquare().print();

            if (clickedSquare.getBoardSquare().getPiece() != null || this.squareBuffer != null)
                handlePieceInteraction(clickedSquare);

            this.grid.redraw();
        }
    }


    @Override
    public void mouseClicked(final MouseEvent e) {
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
