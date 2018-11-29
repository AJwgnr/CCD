package com.ovgu.ccd.applogic;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Properties;

public class ResourceLoader {

    static final public String CONFIG_FILE_PATH = "config.txt";
    static final public Properties CONFIG_FILE = ResourceLoader.getConfigFile();

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

    /*Method load image by a given name with extension
     * @name     : string of image to load for ex. "chessboard.jpg"
     * @returns  : image or null if cannot load
     * */

    public static Image loadImage(String name) {
        Image img = null;
        URL url = null;
        Toolkit tk = Toolkit.getDefaultToolkit();
        try {
            Properties prop = getConfigFile();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            System.out.println("Load Image from Theme: " + prop.getProperty("THEME"));
            URL imageLink = loader.getResource("theme/" + prop.getProperty("THEME", "default") + "/images/" + name);
            img = tk.getImage(imageLink);

        } catch (Exception e) {
            System.out.println("some error loading image!");
            e.printStackTrace();
        }
        return img;
    }
}
