/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Authors:
 * Mateusz Sławomir Lach ( matlak, msl )
 * Damian Marciniak
 */
package com.ovgu.ccd.gui;

import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class responsible for promotion of a pawn.
 * When pawn reach the end of the chessboard it can be change to rook,
 * bishop, queen or knight. For what pawn is promoted decideds player.
 */
@SuppressWarnings("ALL")
public class PawnPromotionWindow extends JDialog implements ActionListener
{
    private final int    DIALOG_HEIGHT  = 300;
    private final int    DIALOG_WIDTH   = 300;
    private final String DIALOG_TITLE   = "Pawn Promotion";

    private JButton knightButton;
    private JButton bishopButton;
    private JButton rookButton;
    private JButton queenButton;

    public String resultPieceName;
    private JDialog promotionDialog = null;


    /**
     * @param colorOfPiece  The player color
     */
    public PawnPromotionWindow(String colorOfPiece)
    {
        JFrame frame = new JFrame();
        promotionDialog = new JDialog(frame, DIALOG_TITLE, true);
        Container pane = promotionDialog.getContentPane();
        pane.setLayout(new GridLayout(1, 4));

        this.knightButton   = new JButton(
                new ImageIcon(ResourceManager.loadImage("Knight-" + colorOfPiece + ".png")));
        this.bishopButton   = new JButton(
                new ImageIcon(ResourceManager.loadImage("Bishop-" + colorOfPiece + ".png")));
        this.rookButton     = new JButton(
                new ImageIcon(ResourceManager.loadImage("Rook-" + colorOfPiece + ".png")));
        this.queenButton    = new JButton(
                new ImageIcon(ResourceManager.loadImage("Queen-" + colorOfPiece + ".png")));

        this.knightButton.addActionListener(this);
        this.bishopButton.addActionListener(this);
        this.rookButton.addActionListener(this);
        this.queenButton.addActionListener(this);

        this.promotionDialog.add(queenButton);
        this.promotionDialog.add(rookButton);
        this.promotionDialog.add(bishopButton);
        this.promotionDialog.add(knightButton);

        this.promotionDialog.setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
        this.promotionDialog.setLocationRelativeTo(null);
        pack();
        this.promotionDialog.setVisible(true);
    }

    /** Method setting the color fo promoted pawn
     * @param color The players color
     */
    public void setColor(String color) {
        this.knightButton.setIcon(new ImageIcon(ResourceManager.loadImage("Knight-" + color + ".png")));
        this.bishopButton.setIcon(new ImageIcon(ResourceManager.loadImage("Bishop-" + color + ".png")));
        this.rookButton.setIcon(new ImageIcon(ResourceManager.loadImage("Rook-" + color + ".png")));
        this.queenButton.setIcon(new ImageIcon(ResourceManager.loadImage("Queen-" + color + ".png")));
    }


    /** Method wich is changing a pawn into queen, rook, bishop or knight
     * @param arg0 Capt information about performed action
     */
    public void actionPerformed(ActionEvent arg0)
    {
        if (arg0.getSource() == queenButton)
        {
            resultPieceName = "Queen";
        }
        else if (arg0.getSource() == rookButton)
        {
            resultPieceName = "Rook";
        }
        else if (arg0.getSource() == bishopButton)
        {
            resultPieceName = "Bishop";
        }
        else //knight
        {
            resultPieceName = "Knight";
        }
        this.promotionDialog.setVisible(false);
        this.promotionDialog.dispose();
    }


    /**
     * returns the resultPieceName of the user selection
     *
     * @return  selected piece type
     */
    public Piece.PieceTypes getResult()
    {
        if (this.resultPieceName.equals("Knight")) {
            return Piece.PieceTypes.KNIGHT;
        }
        else if (this.resultPieceName.equals("Queen")) {
            return Piece.PieceTypes.QUEEN;
        }
        else if (this.resultPieceName.equals("Rook")) {
            return Piece.PieceTypes.ROOK;
        }
        else { // bishop
            return Piece.PieceTypes.BISHOP;
        }
    }
}
