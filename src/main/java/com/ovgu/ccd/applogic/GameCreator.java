package com.ovgu.ccd.applogic;

import com.ovgu.ccd.gui.gameui.JChessViewController;
import com.ovgu.ccd.gui.threeplayer.ChessboardGrid;
import com.ovgu.ccd.gui.threeplayer.ChessboardListener;
import com.ovgu.ccd.gui.twoplayer.Game;

import java.awt.*;
import javax.swing.*;

public class GameCreator {

    JChessViewController controller;

    public GameCreator(JChessViewController control){
        this.controller = control;
    }


    public Game createTwoPlayerChess(String title){
        Game newGUI = new Game();
        //this.gamesPane.addTab(title, newGUI);
        return newGUI;
    }


    public void createThreePlayerChess(String title){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ChessboardGrid chessboardGrid = new ChessboardGrid(new com.ovgu.ccd.gui.threeplayer.Point(
                screenSize.width / 2, screenSize.height / 2.5),
                (screenSize.height / 2) - 100);
        ThreePlayerChessboard board = new ThreePlayerChessboard(chessboardGrid);
        ChessboardListener listener = new ChessboardListener(chessboardGrid);

        listener.setListenerRestrictions(new PlayerSequenceManager(board.getAllPlayers()));
        //this.gamesPane.addTab(title, listener.getPanel());

    }
}