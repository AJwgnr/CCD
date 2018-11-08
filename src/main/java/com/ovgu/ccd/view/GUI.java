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
package com.ovgu.ccd.view;

import com.ovgu.ccd.Utilities.GlobalVariables;

import java.awt.*;
import java.net.*;

import java.util.Properties;

/** Class representing the game interface which is seen by a player and
 * where are located available for player options, current games and where
 * can he start a new game (load it or save it)
 */
public class GUI
{

    public Game game;
    //static final public Properties configFile = Utility.getProperties(GlobalVariables.CONFIG_FILE_PATH);
    //static final public String CONFIG_FILE_PATH = "config.txt";
    //GlobalVariables.CONFIG_FILE = GUI.getConfigFile();
    //static final public Properties configFile = GUI.getConfigFile();

    //public Game game;
    //static final public String CONFIG_FILE_PATH = "config.txt";
    //static final String CONFIG_NAME = "config.txt";
    //static final public Properties configFile = GUI.getConfigFile();

    public GUI()
    {
        this.game = new Game();
        //this.drawGUI();
    }

    /*Method load image by a given name with extension
     * @name     : string of image to load for ex. "chessboard.jpg"
     * @returns  : image or null if cannot load
     * */

    public static Image loadImage(String name)
    {
        Image img = null;
        URL url = null;
        Toolkit tk = Toolkit.getDefaultToolkit();
        try
        {
            //Utility.getProperties(GlobalVariables.CONFIG_FILE_PATH);
            Properties prop = GlobalVariables.CONFIG_FILE;
            //Properties prop = GUI.getConfigFile();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            System.out.println("Load Image from Theme: " + prop.getProperty("THEME"));
            URL imageLink =  loader.getResource("theme/" + prop.getProperty("THEME", "default") + "/images/" + name);
            img = tk.getImage(imageLink);

        }
        catch (Exception e)
        {
            System.out.println("some error loading image!");
            e.printStackTrace();
        }
        return img;
    }


    // TODO : Add Validity check
    static boolean themeIsValid(String name)
    {
        return true;
    }

    /*public void Properties getConfigFile()
    {
        Properties configProperty = Utility.getProperties(CONFIG_FILE_PATH);
        System.out.println("Config File Loaded!");
        return configProperty;
    }*/

    /*static String getJarPath()
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
    }*/

    /*public static Properties getConfigFile()
    {
    	Properties configProperty = new Properties();
        FileInputStream inputStream = null;

    	// load external config.txt if possible
        try
        {
            File file = new File("config.txt"); // externally, not in jar
            inputStream = new FileInputStream( file );
            configProperty.load(inputStream);
            System.out.println("External config-file successfully loaded!");
        }
        catch (Exception e)
        {
            System.out.println("External config file not found.");

            // load jar intern config.txt instead
            // (not feasible to store in-game adjustments in jar)
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try(InputStream resourceStream = loader.getResourceAsStream(CONFIG_NAME)) {
                configProperty.load(resourceStream);
                System.out.println("Internal config-file successfully loaded!");
            }catch (FileNotFoundException ex) {
                System.out.println("Config file not found.");
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error loading config file.");
            }
        }
        return configProperty;
    }

    public static void setConfigFile(String propertyName, String value) {
        if (!propertyName.isEmpty()) {
            configFile.setProperty(propertyName, value);
            try {
                File file = new File("config.txt"); // externally, not in jar
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                if (fileOutputStream != null && file.exists()) {
                    configFile.store(fileOutputStream, null);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                else
                    System.out.println(file.getPath() + " not found!");

            } catch (java.io.IOException exc) {
                exc.printStackTrace();
            }
        }
    }/**/



}
