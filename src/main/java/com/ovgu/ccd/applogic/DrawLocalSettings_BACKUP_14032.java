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
package com.ovgu.ccd.applogic;

import com.ovgu.ccd.gui.Game;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;

/**
 * Class responsible for drawing the fold with local game settings
 */
public class DrawLocalSettings extends JPanel implements ActionListener {

    private JDialog parent;//needet to close newGame window
    private JComboBox color;//to choose color of player
    private JRadioButton oponentComp;//choose oponent
    private JRadioButton oponentHuman;//choose oponent (human)
    private ButtonGroup oponentChoos;//group 4 radio buttons
    private JFrame localPanel;
    private JLabel compLevLab;
    private JSlider computerLevel;//slider to choose jChess Engine level

    //Player names
    private JTextField firstName;
    private JTextField secondName;
    private JTextField thirdName;

    private JLabel firstNameLab;
    private JLabel secondNameLab;
    private JLabel thirdNameLab;

    private JCheckBox upsideDown;//if true draw chessboard upsideDown(white on top)
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;
    private Container container;
    private JSeparator sep;
    private JButton okButton;
    private JCheckBox timeGame;
    private JComboBox time4Game;
    private String colors[] =
            {
                    Settings.lang("white"), Settings.lang("black")
            };
    String times[] =
            {
                    "1", "3", "5", "8", "10", "15", "20", "25", "30", "60", "120"
            };


    public DrawLocalSettings(JDialog parent) {
        super();
        //this.setA//choose oponent
        initUiComponents(parent);
    }


    private void initUiComponents(JDialog parent) {
        this.parent = parent;
        this.color = new JComboBox(colors);
        this.gridBagLayout = new GridBagLayout();
        this.gridBagConstraints = new GridBagConstraints();
        this.sep = new JSeparator();
        this.okButton = new JButton(Settings.lang("ok"));
        this.compLevLab = new JLabel(Settings.lang("computer_level"));

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
        this.computerLevel = new JSlider();
        this.upsideDown = new JCheckBox(Settings.lang("upside_down"));
        this.timeGame = new JCheckBox(Settings.lang("time_game_min"));
        this.time4Game = new JComboBox(times);

        this.oponentComp = new JRadioButton(Settings.lang("against_computer"), false);
        this.oponentHuman = new JRadioButton(Settings.lang("against_other_human"), true);

        this.setLayout(gridBagLayout);
        this.oponentComp.addActionListener(this);
        this.oponentHuman.addActionListener(this);
        this.okButton.addActionListener(this);

        this.secondName.addActionListener(this);

        this.oponentChoos.add(oponentComp);
        this.oponentChoos.add(oponentHuman);
        this.computerLevel.setEnabled(false);
        this.computerLevel.setMaximum(3);
        this.computerLevel.setMinimum(1);

        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 0;
        this.gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        this.gridBagLayout.setConstraints(oponentComp, gridBagConstraints);
        this.add(oponentComp);
        this.gridBagConstraints.gridx = 1;
        this.gridBagLayout.setConstraints(oponentHuman, gridBagConstraints);
        this.add(oponentHuman);
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
        this.gridBagLayout.setConstraints(color, gridBagConstraints);
        this.add(color);
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
        this.gridBagLayout.setConstraints(compLevLab, gridBagConstraints);
        this.add(compLevLab);
        this.gridBagConstraints.gridy = 8;
        this.gridBagLayout.setConstraints(computerLevel, gridBagConstraints);
        this.add(computerLevel);
        this.gridBagConstraints.gridy = 9;
        this.gridBagLayout.setConstraints(upsideDown, gridBagConstraints);
        this.add(upsideDown);
        this.gridBagConstraints.gridy = 10;
        this.gridBagConstraints.gridwidth = 1;
        this.gridBagLayout.setConstraints(timeGame, gridBagConstraints);
        this.add(timeGame);
        this.gridBagConstraints.gridx = 1;
        this.gridBagConstraints.gridy = 11;
        this.gridBagConstraints.gridwidth = 1;
        this.gridBagLayout.setConstraints(time4Game, gridBagConstraints);
        this.add(time4Game);
        this.gridBagConstraints.gridx = 1;
        this.gridBagConstraints.gridy = 12;
        this.gridBagConstraints.gridwidth = 0;
        this.gridBagLayout.setConstraints(okButton, gridBagConstraints);
        this.add(okButton);
        this.oponentComp.setEnabled(false);//for now, becouse not implemented!
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
     * when he want to start new local game
     *
     * @param e where is saving data of performed action
     */
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if (target == this.oponentComp) //toggle enabled of controls depends of oponent (if computer)
        {
            this.computerLevel.setEnabled(true);//enable level of computer abilities
            this.secondName.setEnabled(false);//disable field with name of player2
        } else if (target == this.oponentHuman) //else if oponent will be HUMAN
        {
            this.computerLevel.setEnabled(false);//disable level of computer abilities
            this.secondName.setEnabled(true);//enable field with name of player2
        } else if (target == this.okButton) //if clicked OK button (on finish)
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
            if (!this.oponentComp.isSelected()
                    && (this.firstName.getText().length() == 0 || this.secondName.getText().length() == 0) || (this.thirdName.getText().length() == 0 )) {
                JOptionPane.showMessageDialog(this, Settings.lang("fill_names"));
                return;
            }
            if ((this.oponentComp.isSelected() && this.firstName.getText().length() == 0)) {
                JOptionPane.showMessageDialog(this, Settings.lang("fill_name"));
                return;
            }
            Game newGUI = JChessApp.jcv.addNewTab(this.firstName.getText() + " vs " + this.secondName.getText() +  " vs " + this.thirdName.getText()  );


            Settings sett = newGUI.settings;//sett local settings variable
            //if (this.timeGame.isSelected()) //if timeGame is checked
           // {
                String value = this.times[this.time4Game.getSelectedIndex()];//set time for game
                Integer val = new Integer(value);
                sett.timeLimitSet = true;
                sett.timeForGame = val * 60;//set time for game and mult it to seconds
                newGUI.gameClock.initClock();
                newGUI.gameClock.start();
           // }

            Player pl1 = sett.getPlayerOne();//set local player variable
            Player pl2 = sett.getPlayerTwo();//set local player variable
            Player pl3 = sett.getPlayerThree();//set local player variable


            sett.gameMode = Settings.gameModes.newGame;
            //if(this.firstName.getText().length() >9 ) this.firstName.setText(this.firstName.getText(0,8));
            if (this.color.getActionCommand().equals("biały")) //if first player is white
            {
                pl1.setName(this.firstName.getText());//set name of player
                pl2.setName(this.secondName.getText());//set name of player
                pl3.setName(this.thirdName.getText());
            } else //else change names
            {
                pl2.setName(this.firstName.getText());//set name of player
                pl1.setName(this.secondName.getText());//set name of player
                pl3.setName(this.thirdName.getText());
            }
<<<<<<< HEAD
            pl1.setType(Player.PlayerTypes.localUser);//set type of player
            pl2.setType(Player.PlayerTypes.localUser);//set type of player
=======
            pl1.setType(Player.playerTypes.localUser);//set type of player
            pl2.setType(Player.playerTypes.localUser);//set type of player
            pl3.setType(Player.playerTypes.localUser);//set type of player
>>>>>>> develop
            sett.gameType = Settings.gameTypes.local;
            if (this.oponentComp.isSelected()) //if computer oponent is checked
            {
                pl2.setType(Player.PlayerTypes.computer);
            }
            //if upsideDown is checked
            sett.upsideDown = this.upsideDown.isSelected();

            System.out.println(this.time4Game.getActionCommand());
            //this.time4Game.getComponent(this.time4Game.getSelectedIndex());
            System.out.println("****************\nStarting new game: " + pl1.getName() + " vs. " + pl2.getName() + " vs. " + pl3.getName()
                    + "\ntime 4 game: " + sett.timeForGame + "\ntime limit set: " + sett.timeLimitSet
                    + "\nwhite on top?: " + sett.upsideDown + "\n****************");//4test
            newGUI.newGame();//start new Game
            this.parent.setVisible(false);//hide parent
            newGUI.chessboard.repaint();
            newGUI.chessboard.draw();
        }

    }

    /**
     * Method responsible for triming white symbols from strings
     *
     * @param txt    Where is capt value to equal
     * @param length How long is the string
     * @return result trimmed String
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