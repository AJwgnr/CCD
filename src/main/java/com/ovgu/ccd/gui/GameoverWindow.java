package com.ovgu.ccd.gui;

import com.ovgu.ccd.applogic.ResourceManager;
import com.ovgu.ccd.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class with creates the gameover window at the end of the game
 */
public class GameoverWindow extends JDialog
{
    private final int    DIALOG_HEIGHT  = 500;
    private final int    DIALOG_WIDTH   = 800;
    private final String DIALOG_TITLE   = "Gameover";

    private JDialog gameoverDialog = null;


    /**
     * constructor
     *
     * @param nameOfWinner the name of the player, who wons
     */
    public GameoverWindow(String nameOfWinner)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameoverDialog = new JDialog(frame, DIALOG_TITLE, true);
        this.gameoverDialog.getContentPane().add(new Fireworks(), BorderLayout.CENTER);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel = new JLabel("Congratulations, " + nameOfWinner + ", you won the game!");
        jLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        panel.add(jLabel);
        this.gameoverDialog.getContentPane().add(panel, BorderLayout.NORTH);
        this.gameoverDialog.setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
        this.gameoverDialog.setLocationRelativeTo(null);
        pack();
        this.gameoverDialog.setVisible(true);
    }
}
