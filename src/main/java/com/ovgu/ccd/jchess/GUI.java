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
import java.net.*;
import java.io.*;

import javax.swing.*;
import javax.swing.JPanel;

import java.time.Clock;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/** Class representing the game interface which is seen by a player and
 * where are located available for player options, current games and where
 * can he start a new game (load it or save it)
 */
public class GUI
{

    public Game game;
    static final public String CONFIG_FILE_PATH = "config.txt";
    static final String CONFIG_NAME = "config.txt";
    static final public Properties configFile = GUI.getConfigFile();

    public GUI()
    {
        this.game = new Game();
        //this.drawGUI();
    }

    /*Method load image by a given name with extension
     * @name     : string of image to load for ex. "chessboard.jpg"
     * @returns  : image or null if cannot load
     * */

    static Image loadImage(String name)
    {
        if (configFile == null) { return null; }
        Image img = null;
        URL url = null;
        Toolkit tk = Toolkit.getDefaultToolkit();
        try
        {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            System.out.println(configFile.getProperty("THEME"));
            URL imageLink =  loader.getResource("theme/" + configFile.getProperty("THEME", "default") + "/images/" + name);
            System.out.println(imageLink);
            img = tk.getImage(imageLink);

        }
        catch (Exception e)
        {
            System.out.println("some error loading image!");
            e.printStackTrace();
        }
        return img;
    }


    static boolean themeIsValid(String name)
    {
        return true;
    }

    static String getJarPath()
    {
        String path = GUI.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        path = path.replaceAll("[a-zA-Z0-9%!@#$%^&*\\(\\)\\[\\]\\{\\}\\.\\,\\s]+\\.jar", "");
        int lastSlash = path.lastIndexOf(File.separator); 
        if(path.length()-1 == lastSlash)
        {
            path = path.substring(0, lastSlash);
        }
        path = path.replace("%20", " ");
        System.out.println(path);
        return path;
    }

    static Properties getConfigFile()
    {
    	Properties configProperty = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream resourceStream = loader.getResourceAsStream(CONFIG_NAME)) {
            configProperty.load(resourceStream);
        }catch (FileNotFoundException e) {
            System.out.println("Config file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading config file.");
        }
        System.out.println("Config File Loaded!");
        return configProperty;
    }
}
