package com.ust.AssesmentSelenium.utils;

import java.io.FileInputStream;
import java.util.Properties;

// Utility class for reading properties from a file
public class FileIO {

    private static Properties properties;

    // Method to get properties from a configuration file
    public static Properties getProperties() {
        properties = new Properties();
        try {
            // Loading properties from the configuration file
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\objectrepository\\config.properties");
            properties.load(fis); // Loading properties into the Properties object
        } catch (Exception e) {
            e.printStackTrace(); // Printing stack trace if an exception occurs
        }

        return properties; // Returning the Properties object
    }
}
