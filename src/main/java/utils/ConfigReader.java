package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            String configPath = System.getProperty("user.dir") +
                    "/src/test/resources/config/config.properties";
            System.out.println("Loading config from: " + configPath);
            FileInputStream file = new FileInputStream(configPath);
            properties.load(file);
            file.close();

            // Print all loaded properties for debugging
            System.out.println("=== Loaded Properties ===");
            for (String key : properties.stringPropertyNames()) {
                System.out.println(key + " = " + properties.getProperty(key));
            }
            System.out.println("=========================");

        } catch (IOException e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties. " +
                    "Available properties: " + properties.stringPropertyNames());
        }
        return value.trim();
    }

    public static String get(String key, String defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? value.trim() : defaultValue;
    }

    // Check if property exists
    public static boolean hasProperty(String key) {
        return properties.containsKey(key);
    }
}