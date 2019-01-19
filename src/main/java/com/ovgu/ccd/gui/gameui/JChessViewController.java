package com.ovgu.ccd.gui.gameui;

import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.gui.JChessApp;
import com.ovgu.ccd.gui.twoplayer.Game;

import javax.swing.*;
import java.io.File;


public class JChessViewController {
    private final Icon[] busyIcons = new Icon[15];
    private JChessView jcv;
    private int busyIconIndex = 0;


    public JChessViewController(JChessView view) {
        this.jcv = view;
    }

    /**
     * @param title
     * @return
     */
    public Game addNewTab(String title) {
        Game newGUI = new Game();
        jcv.getGamesPane().addTab(title, newGUI);
        return newGUI;

    }

    public void newGame() {
        jcv.createNewGameFrame();
    }


    /**
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */
    protected Game getActiveTabGame() throws ArrayIndexOutOfBoundsException {
        JTabbedPane gamesPane = jcv.getGamesPane();
        Game activeGame = (Game) gamesPane.getComponentAt(gamesPane.getSelectedIndex());
        return activeGame;
    }


    public void showAboutBox() {
        jcv.setAboutBox();
    }


    public String showPawnPromotionBox(String color) {
        return (jcv.setPawnPromotionBox(color));
    }

    public String showSaveWindow() {
        return "";
    }

    public void closeGame() {
        this.jcv.getFrame().dispose();
    }

    /**
     * @param evt
     */
    public void moveBackItemMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_moveBackItemMouseClicked
    {//GEN-HEADEREND:event_moveBackItemMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_moveBackItemMouseClicked

    /**
     * @param evt
     */
    public void moveForwardItemMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_moveForwardItemMouseClicked
    {//GEN-HEADEREND:event_moveForwardItemMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_moveForwardItemMouseClicked

    /**
     * @param evt
     */
    public void moveForwardItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_moveForwardItemActionPerformed
    {//GEN-HEADEREND:event_moveForwardItemActionPerformed
        /** // TODO add your handling code here:
         if (gui != null && gui.game != null) {
         gui.game.redo();
         } else {
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
         }**/
    }

    /**
     * @param evt
     */
    public void rewindToBeginActionPerformed(java.awt.event.ActionEvent evt) {
      /*  try {
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
        } */
    }

    /**
     * @param evt
     */
    public void rewindToEndActionPerformed(java.awt.event.ActionEvent evt) {
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
    }


    public void moveBackItemActionPerformed(java.awt.event.ActionEvent evt) {/*
        if (gui != null && gui.game != null) {
            gui.game.undo();
        } else {
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
        }
*/
    }


    public void newGameItemActionPerformed(java.awt.event.ActionEvent evt) {
        newGame();
    }

    public void saveGameItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.jcv.getGamesPane().getTabCount() == 0) {
            JOptionPane.showMessageDialog(null, Settings.lang("save_not_called_for_tab"));
            return;
        }
        while (true) {//until
            JFileChooser fc = new JFileChooser();
            int retVal = fc.showSaveDialog(this.jcv.getGamesPane());
            if (retVal == JFileChooser.APPROVE_OPTION) {
                File selFile = fc.getSelectedFile();
                Game tempGUI = (Game) this.jcv.getGamesPane().getComponentAt(this.jcv.getGamesPane().getSelectedIndex());
                if (!selFile.exists()) {
                    try {
                        selFile.createNewFile();
                    } catch (java.io.IOException exc) {
                        System.out.println("error creating file: " + exc);
                    }
                } else if (selFile.exists()) {
                    int opt = JOptionPane.showConfirmDialog(tempGUI, Settings.lang("file_exists"), Settings.lang("file_exists"), JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.NO_OPTION)//if user choose to now overwrite
                    {
                        continue; // go back to file choose
                    }
                }
                if (selFile.canWrite()) {
                    tempGUI.saveGame(selFile);
                }
                System.out.println(fc.getSelectedFile().isFile());
                break;
            } else if (retVal == JFileChooser.CANCEL_OPTION) {
                break;
            }

        }
    }

    public void loadGameItemActionPerformed(java.awt.event.ActionEvent
                                                    evt) {
        JFileChooser fc = new JFileChooser();
        int retVal = fc.showOpenDialog(this.jcv.getGamesPane());
        if (retVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (file.exists() && file.canRead()) {
                Game.loadGame(file);
            }
        }
    }

    public void themeSettingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            ThemeChooseWindow choose = new ThemeChooseWindow(this.jcv.getFrame());
            JChessApp.getApplication().show(choose);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    JChessApp.getApplication().getMainFrame(),
                    exc.getMessage()
            );
            System.out.println("Something wrong creating window - perhaps themeList is null");
        }
    }


}
