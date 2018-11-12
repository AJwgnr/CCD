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
package com.ovgu.ccd.jchess;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ThemeChooseWindow extends JDialog implements ActionListener, ListSelectionListener
{

    JList themesList;
    ImageIcon themePreview;
    GridBagLayout gbl;
    public String result;
    GridBagConstraints gbc;
    JButton themePreviewButton;
    JButton okButton;


    ThemeChooseWindow(Frame parent) throws Exception
    {
        super(parent);

        Vector<String> themeNames = new Vector<String>();
        try
        {
            // extract names of sub-directories in /theme
            URI uri = JChessApp.class.getResource("/theme").toURI();
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
            Path myPath = fileSystem.getPath("/theme");

            // filter '/' and 'theme'
            Files.list(myPath).forEach(path -> {
                String folderName = path.getFileName().toString();
                String themeName = folderName.substring(0, folderName.length() - 1);
                if (!themeName.equals("theme"))
                    themeNames.add(themeName);
            });
            fileSystem.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(Settings.lang("ERROR while loading the themes!"));
        }


        this.setTitle(Settings.lang("choose_theme_window_title"));
        Dimension winDim = new Dimension(550, 230);
        this.setMinimumSize(winDim);
        this.setMaximumSize(winDim);
        this.setSize(winDim);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.themesList = new JList(themeNames);
        this.themesList.setLocation(new Point(10, 10));
        this.themesList.setSize(new Dimension(100, 120));
        this.add(this.themesList);
        this.themesList.setSelectionMode(0);
        this.themesList.addListSelectionListener(this);
        Properties prp = GUI.getConfigFile();

        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        try
        {
            this.themePreview = new ImageIcon(GUI.loadImage("Preview.png"));
        }
        catch (java.lang.NullPointerException exc)
        {
            System.out.println("Cannot find preview image: " + exc);
            this.themePreview = new ImageIcon(JChessApp.class.getResource("theme/noPreview.png"));
            return;
        }
        this.result = "";
        this.themePreviewButton = new JButton(this.themePreview);
        this.themePreviewButton.setLocation(new Point(110, 10));
        this.themePreviewButton.setSize(new Dimension(420, 120));
        this.add(this.themePreviewButton);

        this.okButton = new JButton("OK");
        this.okButton.setLocation(new Point(175, 140));
        this.okButton.setSize(new Dimension(200, 50));
        this.add(this.okButton);
        this.okButton.addActionListener(this);
        this.setModal(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent event)
    {
        String element = this.themesList.getModel().getElementAt(this.themesList.getSelectedIndex()).toString();
        URL iconUrl = this.getClass().getResource("/theme" + "/" + element + "/images/Preview.png");
        Toolkit tk = this.getToolkit();
        this.themePreview.setImage(tk.getImage(iconUrl));
        this.themePreviewButton.setIcon(this.themePreview);
    }

    /** Method which is changing a pawn into queen, rook, bishop or knight
     * @param evt Capt information about performed action
     */
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() == this.okButton)
        {
            int element = this.themesList.getSelectedIndex();
            String name = this.themesList.getModel().getElementAt(element).toString();
            GUI.setConfigFile("THEME", name);
            JOptionPane.showMessageDialog(this, Settings.lang("Changes will be visible after a restart!"));
            this.setVisible(false);
        }
    }
}