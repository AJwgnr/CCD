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

package com.ovgu.ccd.gui.gameui;

import com.ovgu.ccd.applogic.PlayerSequenceManager;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.JChessApp;
import com.ovgu.ccd.gui.threeplayer.ChessboardGrid;
import com.ovgu.ccd.gui.threeplayer.ChessboardListener;
import com.ovgu.ccd.gui.threeplayer.Point;
import com.ovgu.ccd.gui.twoplayer.Game;
import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


/**
 * The application's main frame.
 */
public class JChessView extends FrameView implements ComponentListener {
    // End of variables declaration//GEN-END:variables
    //private JTabbedPaneWithIcon gamesPane;
    private JDialog newGameFrame;
    private JDialog aboutBox;

    private JPanel mainPanel;
    private JPanel statusPanel;

    private GroupLayout mainPanelLayout;

    private JLabel statusAnimationLabel;
    private JLabel statusMessageLabel;
    private JTabbedPane gamesPane;
    private JSeparator statusPanelSeparator;
    private PawnPromotionWindow promotionBox;
    private JProgressBar progressBar;
    private MenuView menuView;

    private JChessViewController jChessViewController;


    public JChessView(SingleFrameApplication app) {
        super(app);
        this.jChessViewController = new JChessViewController(this);
        this.initUiComponents();


    }

    public Game addNewTwoPlayerTab(String title) {
        Game newGUI = new Game();
        this.gamesPane.addTab(title, newGUI);
        return newGUI;
    }

    public Game addNewThreePlayerTab(String title) {

        ChessboardGrid chessboardGrid = new ChessboardGrid(new Point(500, 420), 450);
        ChessboardListener listener = new ChessboardListener(chessboardGrid);
        //Window window = new com.ovgu.ccd.gui.threeplayer.Window("ChessboardListener",1280,1280, listener.getPanel());
        ThreePlayerChessboard board = new ThreePlayerChessboard(chessboardGrid);
        listener.setListenerRestrictions(new PlayerSequenceManager(board.getAllPlayers()));


        Game newGUI = new Game();
        this.gamesPane.addTab(title, listener.getPanel());
        return newGUI;
    }


    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = JChessApp.getApplication().getMainFrame();
            aboutBox = new JChessAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        JChessApp.getApplication().show(aboutBox);
    }

    public String showPawnPromotionBox(String color) {
        if (promotionBox == null) {
            JFrame mainFrame = JChessApp.getApplication().getMainFrame();
            promotionBox = new PawnPromotionWindow(color);
            promotionBox.setLocationRelativeTo(mainFrame);
            promotionBox.setModal(true);

        }
        promotionBox.setColor(color);
        JChessApp.getApplication().show(promotionBox);

        return promotionBox.resultPieceName;
    }

    public String showSaveWindow() {

        return "";
    }


    /**
     * Creates all UI elements for the chess hosting game and also the control the click handling
     */
    private void initUiComponents() {
        mainPanel = new JPanel();
        statusPanel = new JPanel();
        statusMessageLabel = new JLabel();
        statusAnimationLabel = new JLabel();
        gamesPane = new JChessTabbedPane(jChessViewController);
        menuView = new MenuView(jChessViewController);


        progressBar = new JProgressBar();
        statusPanelSeparator = new javax.swing.JSeparator();

        gamesPane.setName("gamesPane"); // NOI18N
        mainPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        mainPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(800, 600));


        mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(gamesPane, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(gamesPane, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
        );


        statusPanel.setName("statusPanel"); // NOI18N
        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N
        statusMessageLabel.setName("statusMessageLabel"); // NOI18N
        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N
        progressBar.setName("progressBar"); // NOI18N

        GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
                statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                        .addGroup(statusPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(statusMessageLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 616, Short.MAX_VALUE)
                                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusAnimationLabel)
                                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
                statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(statusPanelLayout.createSequentialGroup()
                                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(statusMessageLabel)
                                        .addComponent(statusAnimationLabel)
                                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3))
        );


        setComponent(mainPanel);
        setMenuBar(menuView.getMenuBar());
        setStatusBar(statusPanel);
    }

    private void moveBackItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_moveBackItemActionPerformed
    {//GEN-HEADEREND:event_moveBackItemActionPerformed
        try {
            Game activeGame = this.getActiveTabGame();
            if (!activeGame.undo()) {
                // TODO change
                JOptionPane.showMessageDialog(null, "Nie da sie cofnac!");
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException exc) {
            // TODO change
            JOptionPane.showMessageDialog(null, "Brak aktywnej karty!");
        } catch (UnsupportedOperationException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
    }//GEN-LAST:event_moveBackItemActionPerformed

    private void moveBackItemMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_moveBackItemMouseClicked
    {//GEN-HEADEREND:event_moveBackItemMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_moveBackItemMouseClicked

    private void moveForwardItemMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_moveForwardItemMouseClicked
    {//GEN-HEADEREND:event_moveForwardItemMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_moveForwardItemMouseClicked

    private void moveForwardItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_moveForwardItemActionPerformed
    {//GEN-HEADEREND:event_moveForwardItemActionPerformed
        try {
            Game activeGame = this.getActiveTabGame();
            if (!activeGame.redo()) {
                // TODO change
                JOptionPane.showMessageDialog(null, "W pamieci brak ruchow do przodu!");
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException exc) {
            // TODO change
            JOptionPane.showMessageDialog(null, "Brak aktywnej karty!");
        } catch (UnsupportedOperationException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
    }//GEN-LAST:event_moveForwardItemActionPerformed

    private void rewindToBeginActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_rewindToBeginActionPerformed
    {//GEN-HEADEREND:event_rewindToBeginActionPerformed
        try {
            Game activeGame = this.getActiveTabGame();
            if (!activeGame.rewindToBegin()) {
                // TODO change
                JOptionPane.showMessageDialog(null, "W pamieci brak ruchow do przodu!");
            }
        } catch (ArrayIndexOutOfBoundsException exc) {
            // TODO change
            JOptionPane.showMessageDialog(null, "Brak aktywnej karty!");
        } catch (UnsupportedOperationException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
    }//GEN-LAST:event_rewindToBeginActionPerformed

    private void rewindToEndActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_rewindToEndActionPerformed
    {//GEN-HEADEREND:event_rewindToEndActionPerformed
        try {
            Game activeGame = this.getActiveTabGame();
            if (!activeGame.rewindToEnd()) {
                // TODO change
                JOptionPane.showMessageDialog(null, "W pamieci brak ruchow wstecz!");
            }
        } catch (ArrayIndexOutOfBoundsException exc) {
            // TODO change
            JOptionPane.showMessageDialog(null, "Brak aktywnej karty!");
        } catch (UnsupportedOperationException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
    }//GEN-LAST:event_rewindToEndActionPerformed

    public void componentResized(ComponentEvent e) {
        System.out.println("jchessView resized!!;");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Game getActiveTabGame() throws ArrayIndexOutOfBoundsException {
        Game activeGame = (Game) this.gamesPane.getComponentAt(this.gamesPane.getSelectedIndex());
        return activeGame;
    }

    public int getNumberOfOpenedTabs() {
        return this.gamesPane.getTabCount();
    }

    public void componentMoved(ComponentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void componentShown(ComponentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void componentHidden(ComponentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public JDialog getNewGameFrame() {
        return newGameFrame;
    }

    public JDialog getAboutBox() {
        return aboutBox;
    }

    public JTabbedPane getGamesPane() {
        return gamesPane;
    }

    public PawnPromotionWindow getPromotionBox() {
        return promotionBox;
    }

    public void createNewGameFrame() {
        if (this.newGameFrame == null) {
            this.newGameFrame = new NewGameWindow();
        }
        JChessApp.getApplication().show(this.newGameFrame);
    }

    public void setAboutBox() {
        JFrame mainFrame = JChessApp.getApplication().getMainFrame();
        if (this.aboutBox == null) {
            this.aboutBox = new JChessAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        JChessApp.getApplication().show(aboutBox);
    }

    public String setPawnPromotionBox(String color) {
        if (promotionBox == null) {
            JFrame mainFrame = JChessApp.getApplication().getMainFrame();
            promotionBox = new PawnPromotionWindow(color);
            promotionBox.setLocationRelativeTo(mainFrame);
            promotionBox.setModal(true);
        }
        promotionBox.setColor(color);
        JChessApp.getApplication().show(promotionBox);
        return promotionBox.getName();
    }

}
