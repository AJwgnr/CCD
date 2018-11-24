package com.ovgu.ccd.applogic;

import com.ovgu.ccd.gui.JChessView;

import java.io.InputStream;
import java.util.Properties;

/**
 * Loading the apllication wide resources
 */
public class ResourceManager {

    private static ResourceManager singleton_instance = null;
    private Properties jChessAppPropertie;
    private Properties jChessViewPropertie;
    private Properties jChessAboutBoxPropertie;

    /**
     *
     */
    private ResourceManager() {
        InputStream jChessViewInputStream = JChessView.class.getClassLoader().getResourceAsStream("JChessView.properties");
        jChessViewPropertie = new Properties();

        InputStream jChessAppInputStream = JChessView.class.getClassLoader().getResourceAsStream("JChessApp.properties");
        jChessAppPropertie = new Properties();

        InputStream jChessAboutBoxInputStream = JChessView.class.getClassLoader().getResourceAsStream("JChessAboutBox.properties");
        jChessAppPropertie = new Properties();

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
     *
     * @return
     */
    public static ResourceManager getInstance() {
        if (singleton_instance == null) {
            singleton_instance = new ResourceManager();
        }
        return singleton_instance;
    }


    /**
     *
     * @return
     */
    public Properties getJChessAppPropertie() {
        return jChessAppPropertie;
    }

    /**
     *
     * @return
     */
    public Properties getJChessViewPropertie() {
        return jChessViewPropertie;
    }

    /**
     *
     * @return
     */
    public Properties getJChessAboutBoxPropertie() {
        return jChessViewPropertie;
    }

}
