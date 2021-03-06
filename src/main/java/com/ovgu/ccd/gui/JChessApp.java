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

package com.ovgu.ccd.gui;
import com.ovgu.ccd.applogic.PlayerSequenceManager;
import com.ovgu.ccd.applogic.ThreePlayerChessboard;
import com.ovgu.ccd.gui.gameui.JChessView;

import com.ovgu.ccd.gui.gameui.JChessView;
import com.ovgu.ccd.gui.threeplayer.ChessboardGrid;
import com.ovgu.ccd.gui.threeplayer.ChessboardListener;
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
     *
     * @param args arguments
     */
    public static void main(final String[] args) {
        launch(JChessApp.class, args);
    }

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        //launch application
        jcv = new JChessView(this);
        show(jcv);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(final Window root) {
    }
}
