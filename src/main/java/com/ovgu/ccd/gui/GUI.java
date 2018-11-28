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
package com.ovgu.ccd.gui;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Class representing the game interface which is seen by a player and
 * where are located available for player options, current games and where
 * can he start a new game (load it or save it)
 */
public class GUI {

    static final public String CONFIG_FILE_PATH = "config.txt";
    static final public Properties CONFIG_FILE = GUI.getConfigFile();
    public Game game;

    public GUI() {
        this.game = new Game();
        //this.drawGUI();
    }

    /*Method load image by a given name with extension
     * @name     : string of image to load for ex. "chessboard.jpg"
     * @returns  : image or null if cannot load
     * */

    public static Image loadImage(String name) {
        Image img = null;
        URL url = null;
        Toolkit tk = Toolkit.getDefaultToolkit();
        try {
            Properties prop = GUI.getConfigFile();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            URL imageLink = loader.getResource("theme/" + prop.getProperty("THEME", "default") + "/images/" + name);
            img = tk.getImage(imageLink);

        } catch (Exception e) {
            System.out.println("some error loading image!");
            e.printStackTrace();
        }
        return img;
    }


    // TODO : Add Validity check
    static boolean themeIsValid(String name) {
        return true;
    }


    public static Properties getConfigFile() {

        boolean loaded = true;
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();


        // load external config.txt if possible
        File file = new File(CONFIG_FILE_PATH); // externally, not in jar
        try (FileInputStream inputStream = new FileInputStream(file)) {
            prop.load(inputStream);
        } catch (FileNotFoundException ex) {
            loaded = false;
            System.out.println("External Config file not found.");
        } catch (IOException ex) {
            loaded = false;
            System.out.println("Error loading external config file.");
        }

        if(!loaded) { // load internal config.txt if external config.txt file not found
            try (InputStream resourceStream = loader.getResourceAsStream(CONFIG_FILE_PATH)) {
                prop.load(resourceStream);
                System.out.println("Internal config-file successfully loaded!");
                //loaded = true;
            } catch (FileNotFoundException e) {
                System.out.println("Internal config file not found.");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading internal config file.");
            }
        }
        return prop;
    }

    public static void setConfigFile(String propertyName, String value) {
        if (!propertyName.isEmpty()) {
            CONFIG_FILE.setProperty(propertyName, value);
            try {
                File file = new File(CONFIG_FILE_PATH); // externally, not in jar
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                if (fileOutputStream != null && file.exists()) {
                    CONFIG_FILE.store(fileOutputStream, null);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } else
                    System.out.println(file.getPath() + " not found!");

            } catch (java.io.IOException exc) {
                System.err.println(exc.getMessage()); //exc.printStackTrace();
            }
        }

    }
}
