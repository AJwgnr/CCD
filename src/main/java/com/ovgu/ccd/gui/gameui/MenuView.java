package com.ovgu.ccd.gui.gameui;

import com.ovgu.ccd.applogic.ResourceManager;

import javax.swing.*;
import java.util.Properties;

/** Creates the menu bar and all menuitems with their correspoding listeners
 */
public class MenuView {
    /** Menubar containing all menu options
     */
    private JMenuBar menuBar;
    /** reference to the controller for menu interactions
     */
    private JChessViewController jChessViewController;
    /** property access
     */
    private Properties properties = ResourceManager.getInstance().getJChessViewPropertie();



    /** Creates game menu bar and add the listener to the jchessviewcontroller class
     * @param jChessViewController
     */
    public MenuView(JChessViewController jChessViewController) {
        this.menuBar = new JMenuBar();
        this.jChessViewController = jChessViewController;
        fillGameMenuBar();
    }


    /** Returns the menu Bar of the game
     * @return JMenuBar
     */
    public JMenuBar getMenuBar() {
        return this.menuBar;
    }

    /**Creates and fills the menu bar.
     */
    private void fillGameMenuBar() {
        menuBar.setName("menuBar"); // NOI18N
        //add the created menu tabs to the menu Bar
        menuBar.add(createFileMenuTab());
        menuBar.add(createGameMenuTab());
        menuBar.add(createOptionsMenuTab());
        menuBar.add(createHelpMenuTab());
    }

    /** Creates items in help file tab.
     * @return file menu tab
     */
    private JMenu createFileMenuTab() {
        JMenu fileMenu = new JMenu();
        JMenuItem newGameItem = new JMenuItem();
        JMenuItem loadGameItem = new JMenuItem();
        JMenuItem saveGameItem = new JMenuItem();
        JMenuItem exitMenuItem = new JMenuItem();

        fileMenu.setText(properties.getProperty("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N
        newGameItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newGameItem.setText(properties.getProperty("newGameItem.text")); // NOI18N
        newGameItem.setName("newGameItem"); // NOI18N
        newGameItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChessViewController.newGameItemActionPerformed(evt);
            }
        });
        loadGameItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        loadGameItem.setText(properties.getProperty("loadGameItem.text")); // NOI18N
        loadGameItem.setName("loadGameItem"); // NOI18N
        loadGameItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChessViewController.loadGameItemActionPerformed(evt);
            }
        });
        saveGameItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveGameItem.setText(properties.getProperty("saveGameItem.text")); // NOI18N
        saveGameItem.setName("saveGameItem"); // NOI18N
        saveGameItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChessViewController.saveGameItemActionPerformed(evt);
            }
        });

        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.setText("Exit"); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChessViewController.closeGame();
            }
        });

        fileMenu.add(loadGameItem);
        fileMenu.add(saveGameItem);
        fileMenu.add(newGameItem);
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    /** Creates items in game menu tab.
     * @return game menu tab
     */
    private JMenu createGameMenuTab() {
        JMenu gameMenu = new JMenu();
        JMenuItem moveBackItem = new JMenuItem();
        JMenuItem moveForwardItem = new JMenuItem();
        JMenuItem rewindToBeginItem = new JMenuItem();
        JMenuItem rewindToEndItem = new JMenuItem();

        gameMenu.setText(properties.getProperty("gameMenu.text")); // NOI18N
        gameMenu.setName("gameui"); // NOI18N

        moveBackItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        moveBackItem.setText(properties.getProperty("moveBackItem.text")); // NOI18N
        moveBackItem.setName("moveBackItem"); // NOI18N
        moveBackItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jChessViewController.moveBackItemMouseClicked(evt);
            }
        });
        moveBackItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChessViewController.moveBackItemActionPerformed(evt);
            }
        });

        moveForwardItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        moveForwardItem.setText(properties.getProperty("moveForwardItem.text")); // NOI18N
        moveForwardItem.setName("moveForwardItem"); // NOI18N
        moveForwardItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jChessViewController.moveForwardItemMouseClicked(evt);
            }
        });
        moveForwardItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChessViewController.moveForwardItemActionPerformed(evt);
            }
        });
        rewindToBeginItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        rewindToBeginItem.setText(properties.getProperty("rewindToBegin.text")); // NOI18N
        rewindToBeginItem.setName("rewindToBegin"); // NOI18N
        rewindToBeginItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChessViewController.rewindToBeginActionPerformed(evt);
            }
        });

        rewindToEndItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        rewindToEndItem.setText(properties.getProperty("rewindToEnd.text")); // NOI18N
        rewindToEndItem.setName("rewindToEnd"); // NOI18N
        rewindToEndItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChessViewController.rewindToEndActionPerformed(evt);
            }
        });

        gameMenu.add(moveBackItem);
        gameMenu.add(moveForwardItem);
        gameMenu.add(rewindToBeginItem);
        gameMenu.add(rewindToEndItem);
        return gameMenu;
    }

    /** Creates items in help menu tab.
     * @return help menu tab
     */
    private JMenu createHelpMenuTab() {
        JMenu helpMenu = new JMenu();
        JMenuItem aboutMenuItem = new JMenuItem();

        helpMenu.setText(properties.getProperty("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setText("About"); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChessViewController.showAboutBox();
            }
        });
        helpMenu.add(aboutMenuItem);
        return helpMenu;
    }

    /** Creates items in options menu tab.
     * @return options menu tab
     */
    private JMenu createOptionsMenuTab() {
        JMenu optionsMenu = new JMenu();
        JMenuItem themeSettingsMenuItem = new JMenuItem();

        optionsMenu.setText(properties.getProperty("optionsMenu.text")); // NOI18N
        optionsMenu.setName("optionsMenu"); // NOI18N
        themeSettingsMenuItem.setText(properties.getProperty("themeSettingsMenu.text")); // NOI18N
        themeSettingsMenuItem.setName("themeSettingsMenu"); // NOI18N
        themeSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChessViewController.themeSettingsMenuItemActionPerformed(evt);
            }
        });
        optionsMenu.add(themeSettingsMenuItem);
        return optionsMenu;
    }
}
