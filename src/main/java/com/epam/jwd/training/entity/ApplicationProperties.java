package com.epam.jwd.training.entity;

public final class ApplicationProperties {

    private static ApplicationProperties instance;

    private final String url;
    private final String driver;
    private final String username;
    private final String password;

    private ApplicationProperties(String url, String driver,
                                  String username, String password) {
        this.url = url;
        this.driver = driver;
        this.username = username;
        this.password = password;
    }

    public static ApplicationProperties getInstance(String url, String driver,
                                                    String username, String password) {
        if (instance == null) {
            instance = new ApplicationProperties(url, driver, username, password);
        }
        return instance;
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
