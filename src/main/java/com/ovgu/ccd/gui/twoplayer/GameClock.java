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
package com.ovgu.ccd.gui.twoplayer;

import com.ovgu.ccd.applogic.Clock;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.gui.twoplayer.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class to representing the full game time
 */
@SuppressWarnings("ALL")
public class GameClock extends JPanel implements Runnable {

    private static final int GAMECLOCKBACKGROUNDWIDTH = 600;
    private static final int GAMECLOCKBACKGROUNDHEIGHT = 600;

    private Clock playerOneClock;
    private Clock playerTwoClock;
    private Clock playerThreeClock;

    private Clock runningClock;

    private Settings settings;
    private Thread thread;
    private Game game;
    private Graphics g;
    private String playerOneClockString, playerTwoClockString, playerThreeClockString;
    private BufferedImage background;
    private Graphics bufferedGraphics;

    /**
     * @param game The current game
     */
    public GameClock(Game game) {
        super();

        this.runningClock = this.playerOneClock;//running/active clock
        this.game = game;
        this.settings = game.settings;
        this.background = new BufferedImage(GAMECLOCKBACKGROUNDWIDTH, GAMECLOCKBACKGROUNDHEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.thread = new Thread(this);
        if (this.settings.timeLimitSet) {
            thread.start();
        }
        this.drawBackground();
        this.setDoubleBuffered(true);
    }

    /**
     * Method to init game clock
     */
    public void start() {
        this.thread.start();
    }

    /**
     * Method with is setting the players clocks time
     */
    public void initClock() {
        /*rather in chess game players got the same time 4 game, so why in documentation
         * this method've 2 parameters ? */
        int time = this.settings.getTimeForGame();
        this.playerOneClock = new Clock(time);
        this.playerTwoClock = new Clock(time);
        this.playerThreeClock = new Clock(time);
        this.setPlayers(this.settings.getPlayerTwo(), this.settings.getPlayerOne(), this.settings.getPlayerThree());
    }

    /**
     * Method to stop game clock
     */
    public void stop() {
        this.runningClock = null;

        try {//block this thread
            this.thread.wait();
        } catch (java.lang.InterruptedException exc) {
            System.out.println("Error blocking thread: " + exc);
        } catch (java.lang.IllegalMonitorStateException exc1) {
            System.out.println("Error blocking thread: " + exc1);
        }
    }

    /**
     * Method of drawing graphical background of clock
     */
    private void drawBackground() {
        Graphics gr = this.background.getGraphics();
        Graphics2D g2d = (Graphics2D) gr;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.ITALIC, 20);

        //create fields for names
        g2d.setColor(Color.WHITE);
        g2d.fillRect(5, 30, 80, 30);
        g2d.setFont(font);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(85, 30, 80, 30);
        g2d.setFont(font);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(165, 30, 80, 30);
        g2d.setFont(font);


        g2d.drawRect(5, 30, 170, 30);
        g2d.drawRect(5, 60, 170, 30);
        g2d.drawRect(85, 30, 170, 30);
        g2d.drawRect(85, 60, 170, 30);
        g2d.drawRect(165, 30, 170, 30);
        g2d.drawRect(165, 60, 170, 30);

        g2d.drawLine(85, 30, 85, 90);
        font = new Font("Serif", Font.ITALIC, 50);
        g2d.setColor(Color.WHITE);
        //Write names of player

        //Write names in created rectangles
        g2d.drawString(settings.getPlayerOne().getName(), 10, 50);
        g2d.drawString(settings.getPlayerTwo().getName(), 100, 50);
        g2d.drawString(settings.getPlayerThree().getName(), 190, 50);
        this.bufferedGraphics = this.background.getGraphics();
    }

    /**
     * Annotation to superclass Graphics drawing the clock graphics
     *
     * @param g Graphics2D Capt object to paint
     */
    @Override
    public void paint(Graphics g) {
        //System.out.println("rysuje zegary");
        super.paint(g);
        playerOneClockString = this.playerOneClock.prepareString();
        playerTwoClockString = this.playerTwoClock.prepareString();
        playerThreeClockString = this.playerThreeClock.prepareString();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.background, 0, 0, this);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.ITALIC, 20);
        g2d.drawImage(this.background, 0, 0, this);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(5, 30, 80, 30);
        g2d.setFont(font);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(85, 30, 90, 30);
        g2d.drawRect(5, 30, 170, 30);
        g2d.drawRect(5, 60, 170, 30);
        g2d.drawRect(85, 30, 170, 30);
        g2d.drawLine(85, 30, 85, 90);
        font = new Font("Serif", Font.ITALIC, 14);
        g2d.drawImage(this.background, 0, 0, this);
        g2d.setFont(font);
        g.drawString(settings.getPlayerOne().getName(), 10, 50);
        g.setColor(Color.WHITE);
        g.drawString(settings.getPlayerTwo().getName(), 100, 50);
        g2d.drawString(settings.getPlayerThree().getName(), 190, 50);
        g2d.setFont(font);
        g.setColor(Color.BLACK);

        g2d.drawString(playerOneClockString, 10, 80);
        g2d.drawString(playerTwoClockString, 100, 80);
        g2d.drawString(playerThreeClockString, 190, 80);
    }

    /**
     * Annotation to superclass Graphics updateing clock graphisc
     *
     * @param g Graphics2D Capt object to paint
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    /**
     * Method of swiching the players clocks
     */
    public void switch_clocks() {
        /*in documentation this method is called 'switch', but it's restricted name
        to switch block (in pascal called "case") - this've to be repaired in documentation by Wąsu:P*/
        if (this.runningClock == this.playerOneClock) {
            this.runningClock = this.playerTwoClock;
        } else {
            this.runningClock = this.playerOneClock;
        }
    }


    /**
     * @param p1
     * @param p2
     * @param p3
     */
    private void setPlayers(Player p1, Player p2, Player p3) {
        /*in documentation it's called 'setPlayer' but when we've 'setTimes' better to use
         * one convention of naming methods - this've to be repaired in documentation by Wąsu:P
        dojdziemy do tego:D:D:D*/
        if (p1.getColor() == p1.getColor().WHITE) {
            this.playerOneClock.setPlayer(p1);
            this.playerTwoClock.setPlayer(p2);
            this.playerThreeClock.setPlayer(p3);
        } else {
            this.playerOneClock.setPlayer(p2);
            this.playerTwoClock.setPlayer(p1);
            this.playerThreeClock.setPlayer(p3);
        }
    }

    /**
     * Method with is running the time on clock
     */
    public void run() {
        while (true) {
            if (this.runningClock != null) {
                if (this.runningClock.decrement()) {
                    repaint();
                    try {
                        thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Some error in gameClock thread: " + e);
                    }
                    //if(this.game.blockedChessboard)
                    //  this.game.blockedChessboard = false;
                }
                if (this.runningClock != null && this.runningClock.get_left_time() == 0) {
                    this.timeOver();
                }
            }
        }
    }

    /**
     * Method of checking is the time of the game is not over
     */
    private void timeOver() {
        String color = new String();
        if (this.playerOneClock.get_left_time() == 0) {//Check which player win
            color = this.playerTwoClock.getPlayer().getColor().toString();
        } else if (this.playerTwoClock.get_left_time() == 0) {
            color = this.playerOneClock.getPlayer().getColor().toString();
        } else {//if called in wrong moment
            System.out.println("Time over called when player got time 2 play");
        }
        this.game.endGame("Time is over! " + color + " player win the game.");
        this.stop();

        //JOptionPane.showMessageDialog(this, "koniec czasu");
    }
}
