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
 */
package com.ovgu.ccd.gui.gameui;

import com.ovgu.ccd.applogic.JChessApp;
import com.ovgu.ccd.applogic.Player;
import com.ovgu.ccd.applogic.Settings;
import com.ovgu.ccd.gui.twoplayer.Game;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;

/**
 * Class responsible for drawing the fold with LOCAL game settings
 */
public class NewGameSettings extends JPanel implements ActionListener {

    private JDialog parent;//needet to close NEWGAME window
    private ButtonGroup oponentChoos;//group 4 radio buttons

    //Player names
    private JTextField firstName;
    private JTextField secondName;
    private JTextField thirdName;

    private JLabel firstNameLab;
    private JLabel secondNameLab;
    private JLabel thirdNameLab;


    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;
    private JButton okButton;
    private JCheckBox timeGame;
    private JComboBox time4Game;
    String times[] =
            {
                    "1", "3", "5", "8", "10", "15", "20", "25", "30", "60", "120"
            };


    public NewGameSettings(JDialog parent) {
        super();
        //this.setA//choose oponent
        initUiComponents(parent);
    }


    private void initUiComponents(JDialog parent) {
        this.parent = parent;
        this.parent.setTitle("Create a new game :)");
        this.gridBagLayout = new GridBagLayout();
        this.gridBagConstraints = new GridBagConstraints();
        this.okButton = new JButton(Settings.lang("ok"));

        this.firstName = new JTextField("", 10);
        this.firstName.setSize(new Dimension(200, 50));
        this.secondName = new JTextField("", 10);
        this.secondName.setSize(new Dimension(200, 50));
        this.thirdName = new JTextField("", 10);
        this.thirdName.setSize(new Dimension(200, 50));

        this.firstNameLab = new JLabel(Settings.lang("first_player_name") + ": ");
        this.secondNameLab = new JLabel(Settings.lang("second_player_name") + ": ");
        this.thirdNameLab = new JLabel(Settings.lang("third_player_name") + ": ");

        this.oponentChoos = new ButtonGroup();


        this.timeGame = new JCheckBox(Settings.lang("time_game_min"));
        this.time4Game = new JComboBox(times);


        this.setLayout(gridBagLayout);
        this.okButton.addActionListener(this);

        this.secondName.addActionListener(this);


        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 0;
        this.gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        this.gridBagConstraints.gridx = 1;

        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 1;
        this.gridBagLayout.setConstraints(firstNameLab, gridBagConstraints);
        this.add(firstNameLab);
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 2;
        this.gridBagLayout.setConstraints(firstName, gridBagConstraints);
        this.add(firstName);
        this.gridBagConstraints.gridx = 1;
        this.gridBagConstraints.gridy = 2;
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 3;
        this.gridBagLayout.setConstraints(secondNameLab, gridBagConstraints);
        this.add(secondNameLab);
        this.gridBagConstraints.gridy = 4;
        this.gridBagLayout.setConstraints(secondName, gridBagConstraints);
        this.add(secondName);


        this.gridBagConstraints.gridy = 5;
        this.gridBagLayout.setConstraints(thirdNameLab, gridBagConstraints);
        this.add(thirdNameLab);
        this.gridBagConstraints.gridy = 6;
        this.gridBagLayout.setConstraints(thirdName, gridBagConstraints);
        this.add(thirdName);


        this.gridBagConstraints.gridy = 7;
        this.gridBagConstraints.insets = new Insets(0, 0, 0, 0);

        this.gridBagConstraints.gridy = 8;

        this.gridBagConstraints.gridy = 9;

        this.gridBagConstraints.gridy = 10;
        this.gridBagConstraints.gridwidth = 1;
        this.gridBagLayout.setConstraints(timeGame, gridBagConstraints);
        this.add(timeGame);
        this.gridBagConstraints.gridx = 1;
        this.gridBagConstraints.gridy = 11;
        this.gridBagConstraints.gridwidth = 1;
        this.gridBagLayout.setConstraints(time4Game, gridBagConstraints);
        this.add(time4Game);
        this.gridBagConstraints.gridx = 2;
        this.gridBagConstraints.gridy = 11;
        this.gridBagConstraints.gridwidth = 1;
        this.gridBagLayout.setConstraints(okButton, gridBagConstraints);
        this.add(okButton);

    }

    /**
     * Method witch is checking correction of edit tables
     *
     * @param e Object where is saving this what contents edit tables
     */
    public void textValueChanged(TextEvent e) {
        Object target = e.getSource();
        if (target == this.firstName || target == this.secondName) {
            JTextField temp = new JTextField();
            if (target == this.firstName) {
                temp = this.firstName;
            } else if (target == this.secondName) {
                temp = this.secondName;
            }

            int len = temp.getText().length();
            if (len > 8) {
                try {
                    temp.setText(temp.getText(0, 7));
                } catch (BadLocationException exc) {
                    System.out.println("Something wrong in editables: \n" + exc);
                }
            }
        }
    }

    /**
     * Method responsible for changing the options which can make a player
     * when he want to start new LOCAL game
     *
     * @param e where is saving data of performed action
     */
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if (target == this.okButton) //if clicked OK button (on finish)
        {
            if (this.firstName.getText().length() > 9) {//make names short to 10 digits
                this.firstName.setText(this.trimString(firstName, 9));
            }
            if (this.secondName.getText().length() > 9) {//make names short to 10 digits
                this.secondName.setText(this.trimString(secondName, 9));
            }
            if (this.thirdName.getText().length() > 9) {//make names short to 10 digits
                this.thirdName.setText(this.trimString(thirdName, 9));
            }

            Game newGUI = null;
            Settings sett = null;
            Player pl1 = null;//set LOCAL player variable
            Player pl2 = null;//set LOCAL player variable
            Player pl3 = null;//set LOCAL player variable

            if (this.firstName.getText().length() != 0 && this.secondName.getText().length() != 0) {
                if (this.thirdName.getText().length() != 0) {
                    //launch three player game
                    newGUI = JChessApp.jcv.addNewThreePlayerTab(this.firstName.getText() + " vs " + this.secondName.getText() + " vs " + this.thirdName.getText());
                    sett = newGUI.settings;//sett LOCAL settings variable
                    pl1 = sett.getPlayerOne();//set LOCAL player variable
                    pl2 = sett.getPlayerTwo();//set LOCAL player variable
                    pl3 = sett.getPlayerThree();//set LOCAL player variable

                    pl1.setName(this.firstName.getText());//set name of player
                    pl2.setName(this.secondName.getText());//set name of player
                    pl3.setName(this.thirdName.getText());


                    pl1.setType(Player.PlayerTypes.LOCALUSER);//set type of player
                    pl2.setType(Player.PlayerTypes.LOCALUSER);//set type of player
                    pl3.setType(Player.PlayerTypes.LOCALUSER);//set type of player

                } else {
                    //launch two person game
                    newGUI = JChessApp.jcv.addNewTwoPlayerTab(this.firstName.getText() + " vs " + this.secondName.getText());
                    sett = newGUI.settings;//sett LOCAL settings variable
                    sett.gameType = Settings.gameTypes.LOCAL;
                    pl1 = sett.getPlayerOne();//set LOCAL player variable
                    pl2 = sett.getPlayerTwo();//set LOCAL player variable

                    pl1.setName(this.firstName.getText());//set name of player
                    pl2.setName(this.secondName.getText());//set name of player

                    pl1.setType(Player.PlayerTypes.LOCALUSER);//set type of player
                    pl2.setType(Player.PlayerTypes.LOCALUSER);//set type of player

                    sett.gameMode = Settings.gameModes.NEWGAME;
                    String value = this.times[this.time4Game.getSelectedIndex()];//set time for game
                    Integer val = new Integer(value);
                    sett.timeLimitSet = true;
                    sett.timeForGame = val * 60;//set time for game and mult it to seconds
                    newGUI.gameClock.initClock();
                    newGUI.gameClock.start();


                    //    System.out.println("****************\nStarting new game: " + pl1.getName() + " vs. " + pl2.getName() + " vs. " + pl3.getName()
                    //         + "\ntime 4 game: " + sett.timeForGame + "\ntime limit set: " + sett.timeLimitSet
                    //       + "\nwhite on top?: " + sett.upsideDown + "\n****************");//4test
                    newGUI.newGame();//start new Game
                    newGUI.chessboard.repaint();
                    newGUI.chessboard.draw();
                }

            } else {
                JOptionPane.showMessageDialog(this, Settings.lang("fill_names"));
            }

            this.parent.setVisible(false);//hide parent

        }

    }

    /**
     * Method responsible for triming white symbols from strings
     *
     * @param txt    Where is capt value to equal
     * @param length How long is the string
     * @return resultPieceName trimmed String
     */
    public String trimString(JTextField txt, int length) {
        String result = new String();
        try {
            result = txt.getText(0, length);
        } catch (BadLocationException exc) {
            System.out.println("Something wrong in editables: \n" + exc);
        }
        return result;
    }
}