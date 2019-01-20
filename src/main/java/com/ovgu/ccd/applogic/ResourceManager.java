package com.ovgu.ccd.applogic;

import com.ovgu.ccd.gui.gameui.JChessView;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Loading the apllication wide resources
 */
public class ResourceManager {

    private static ResourceManager singleton_instance = null;
    private Properties jChessAppPropertie;
    private Properties jChessViewPropertie;
    private Properties jChessAboutBoxPropertie;

    static final public String CONFIG_FILE_PATH = "config.txt";
    static final public Properties CONFIG_FILE = ResourceManager.getConfigFile();

    /**
     *
     */
    private ResourceManager() {
        InputStream jChessViewInputStream = JChessView.class.getClassLoader().getResourceAsStream("JChessView.properties");
        jChessViewPropertie = new Properties();

        InputStream jChessAppInputStream = JChessView.class.getClassLoader().getResourceAsStream("JChessApp.properties");
        jChessAppPropertie = new Properties();

        InputStream jChessAboutBoxInputStream = JChessView.class.getClassLoader().getResourceAsStream("JChessAboutBox.properties");
        jChessAboutBoxPropertie = new Properties();

        try {
            jChessViewPropertie.load(jChessViewInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jChessAppPropertie.load(jChessAppInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jChessAboutBoxPropertie.load(jChessAboutBoxInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the instance
     */
    public static ResourceManager getInstance() {
        if (singleton_instance == null) {
            singleton_instance = new ResourceManager();
        }
        return singleton_instance;
    }


    /**
     * @return the app property
     */
    public Properties getJChessAppPropertie() {
        return jChessAppPropertie;
    }

    /**
     * @return the view property
     */
    public Properties getJChessViewPropertie() {
        return jChessViewPropertie;
    }

    /**
     * @return about box property
     */
    public Properties getJChessAboutBoxPropertie() {
        return jChessAboutBoxPropertie;
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

        if (!loaded) { // load internal config.txt if external config.txt file not found
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
                } else {
                    System.out.println(file.getPath() + " not found!");
                }

            } catch (java.io.IOException exc) {
                System.err.println(exc.getMessage()); //exc.printStackTrace();
            }
        }

    }

    /**Method load image by a given name with extension.
     * @param name string of image to load for ex. "chessboard.jpg"
     * @return image or null if cannot load
     * */
    public static Image loadImage(String name) {
        Image img = null;
        URL url = null;
        Toolkit tk = Toolkit.getDefaultToolkit();
        try {
            Properties prop = getConfigFile();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            URL imageLink = loader.getResource("theme/" + prop.getProperty("THEME", "default") + "/images/" + name);
            img = tk.getImage(imageLink);

        } catch (Exception e) {
            System.out.println("some error loading image!");
            e.printStackTrace();
        }
        return img;
    }
}
