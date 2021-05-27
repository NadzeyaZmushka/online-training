package com.epam.jwd.training.util;

import com.epam.jwd.training.entity.ApplicationProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesReader {

    private static PropertiesReader instance;

    private static final Logger LOGGER = LogManager.getLogger(PropertiesReader.class);

    private static final Properties properties = new Properties();

    private PropertiesReader() {
    }

    public static PropertiesReader getInstance() {
        if (instance == null) {
            instance = new PropertiesReader();
        }
        return instance;
    }

    public ApplicationProperties loadProperties() {
        String propertiesFileName = "database.properties";
        try (InputStream stream = this.getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            properties.load(stream);
        } catch (IOException e) {
            LOGGER.error("Error reading file");
        }
        return ApplicationProperties.getInstance(properties.getProperty("url"),
                properties.getProperty("userName"),
                properties.getProperty("password"),
                Integer.parseInt(properties.getProperty("connectionPoolSize")),
                Integer.parseInt(properties.getProperty("maxConnectionPoolSize"))
        );
    }

}
