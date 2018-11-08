package com.ovgu.ccd.Utilities;

import java.io.*;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Utility {

    public static Properties getProperties(String _file_name) {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream resourceStream = loader.getResourceAsStream(_file_name)) {
            prop.load(resourceStream);
            System.out.println("Main Config File Loaded!");
        }catch (FileNotFoundException e) {
            System.out.println("Main properties file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Main properties file.");
        }

        return prop;
    }

    public static void setConfigFile(String propertyName, String value) {
        if (!propertyName.isEmpty()) {
            System.out.println("HI 2 :: " + value);
            GlobalVariables.CONFIG_FILE.setProperty(propertyName, value);
            System.out.println("HI 3 :: " + value);
            try {
                File file = new File(GlobalVariables.CONFIG_FILE_PATH); // externally, not in jar
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                System.out.println("HI 4 :: " + value);
                if (fileOutputStream != null && file.exists()) {
                    System.out.println("HI 5 :: " + value);
                    GlobalVariables.CONFIG_FILE.store(fileOutputStream, null);
                    System.out.println("HI 6 :: " + value);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } else
                    System.out.println(file.getPath() + " not found!");

            } catch (java.io.IOException exc) {
                System.err.println(exc.getMessage());

                //exc.printStackTrace();
            }
        }

    }
	
	/*public static ResourceBundle getResourceBundle(String _file_name) {
		ResourceBundle loc = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream resourceStream = loader.getResourceAsStream(_file_name)) {
            loc = new PropertyResourceBundle(resourceStream);
        }catch (FileNotFoundException e) {
            System.out.println("Main properties file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Main properties file.");
        }
        return loc;
	}*/

	/*public static void getProperties(String _file_name) {
        GlobalVariables.CONFIG_FILE = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream resourceStream = loader.getResourceAsStream(_file_name)) {
            GlobalVariables.CONFIG_FILE.load(resourceStream);
            System.out.println("Main Config File Loaded!");
        }catch (FileNotFoundException e) {
            System.out.println("Main properties file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Main properties file.");
        }

        //return configProperty;
	}*/

}
