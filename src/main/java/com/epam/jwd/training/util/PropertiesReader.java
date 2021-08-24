package com.epam.jwd.training.util;

import com.epam.jwd.training.exception.CouldNotReadPropertiesException;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class for reading property files
 *
 * @author Nadzeya Zmushka
 */
@UtilityClass
public class PropertiesReader {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesReader.class);

    private static final Properties PROPERTIES = new Properties();
    private static final String FILE_NAME = "database.properties";
    private static final String ERROR_READING = "Error reading file";

    static {
        loadProperties();
    }

    public static void loadProperties() {
        try (InputStream stream = PropertiesReader.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new CouldNotReadPropertiesException(ERROR_READING);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

}
