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

package com.ovgu.ccd.applogic;

import com.ovgu.ccd.gui.JChessView;
import com.ovgu.ccd.gui.chessboardListener.ChessboardListener;
import com.ovgu.ccd.gui.chessboardListener.ChessboardGrid;
import com.ovgu.ccd.gui.chessboardListener.Point;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import java.awt.*;

/**
 * The main class of the application.
 */
public class JChessApp extends SingleFrameApplication {
    public static JChessView jcv;

    /**
     * A convenient static getter for the application instance.
     *
     * @return the instance of JChessApp
     */
    public static JChessApp getApplication() {
        return Application.getInstance(JChessApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(JChessApp.class, args);
    }

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {

        ChessboardGrid chessboardGrid = new ChessboardGrid(new Point(550,550),500);
        ChessboardListener listener = new ChessboardListener(chessboardGrid);
        new com.ovgu.ccd.gui.chessboardListener.Window("ChessboardListener",1280,1280, listener.getPanel());
        ThreePlayerChessboard board = new ThreePlayerChessboard(chessboardGrid);

        jcv = new JChessView(this);
        show(jcv);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(Window root) {
    }
}
