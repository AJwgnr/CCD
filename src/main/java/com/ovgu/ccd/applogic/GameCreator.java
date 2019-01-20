package com.ovgu.ccd.applogic;

import com.ovgu.ccd.gui.JChessApp;
import com.ovgu.ccd.gui.gameui.JChessViewController;
import com.ovgu.ccd.gui.threeplayer.ChessboardGrid;
import com.ovgu.ccd.gui.threeplayer.ChessboardListener;
import com.ovgu.ccd.gui.twoplayer.Game;

import java.awt.*;
import javax.swing.*;

/**
 * Creates the different possible chess games.
 */
public class GameCreator {

    JChessViewController controller;

    /** Inits the jchessviewcontroller.
     */
    public GameCreator(){
        this.controller = JChessApp.jcv.getController();
    }


    /** Creates the two player chess game.
     * @param title Title of the tab
     * @return the created Game instance
     */
    public Game createTwoPlayerChess(final String title){
        Game newGUI = new Game();
        this.controller.addNewTab(title, newGUI);
        return newGUI;
    }


    /** Creates the three person chess game.
     * @param title Title of the game tab
     */
    public void createThreePlayerChess(final String title){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ChessboardGrid chessboardGrid = new ChessboardGrid(new com.ovgu.ccd.gui.threeplayer.Point(
                screenSize.width / 2, screenSize.height / 2.5),
                (screenSize.height / 2) - 100);
        ThreePlayerChessboard board = new ThreePlayerChessboard(chessboardGrid);
        ChessboardListener listener = new ChessboardListener(chessboardGrid);
        listener.setListenerRestrictions(new PlayerSequenceManager(board.getAllPlayers()));
        this.controller.addNewTab(title, listener.getPanel());

    }
}