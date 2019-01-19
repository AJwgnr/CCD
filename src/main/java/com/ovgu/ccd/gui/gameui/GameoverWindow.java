package com.ovgu.ccd.gui.gameui;

import javax.swing.*;
import java.awt.*;


/**Class with creates the gameover window at the end of the game.
 */
public class GameoverWindow extends JDialog {
    private static final String CONGRATULATIONS_STR1 = "Congratulations, ";
    private static final String CONGRATULATIONS_STR2 = ", you won the game!";
    private static final String FONT = "Tahoma";
    private static final int FONT_SIZE = 28;
    private final int DIALOG_HEIGHT = 500;
    private final int DIALOG_WIDTH = 800;
    private final String DIALOG_TITLE = "Gameover";
    private JDialog gameoverDialog = null;


    /**Creates the game over window after a player was chess mate.
     *
     * @param nameOfWinner the name of the player, who won the game
     */
    public GameoverWindow(String nameOfWinner) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameoverDialog = new JDialog(frame, DIALOG_TITLE, true);
        this.gameoverDialog.getContentPane().add(new Fireworks(), BorderLayout.CENTER);
        this.gameoverDialog.getContentPane().add(this.congratulationsPanel(nameOfWinner), BorderLayout.NORTH);
        this.gameoverDialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        this.gameoverDialog.setLocationRelativeTo(null);
        pack();
        this.gameoverDialog.setVisible(true);
    }


    /**this will create a panel with the congratulations text of the winner.
     *
     * @param nameOfWinner name of the winner
     * @return panel with text
     */
    private JPanel congratulationsPanel(final String nameOfWinner) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel = new JLabel(CONGRATULATIONS_STR1 + nameOfWinner + CONGRATULATIONS_STR2);
        jLabel.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
        panel.add(jLabel);
        return panel;
    }
}
