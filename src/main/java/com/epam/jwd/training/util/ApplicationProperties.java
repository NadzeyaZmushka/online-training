package com.epam.jwd.training.util;

public final class ApplicationProperties {

    private static ApplicationProperties instance;

    private final String url;
    private final String username;
    private final String password;
    private final int poolSize;
    private final int maxPoolSize;

    private ApplicationProperties(String url, String username, String password, int poolSize, int maxPoolSize) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.poolSize = poolSize;
        this.maxPoolSize = maxPoolSize;
    }

    public static ApplicationProperties getInstance(String url,
                                                    String username, String password, int poolSize, int maxPoolSize) {
        if (instance == null) {
            instance = new ApplicationProperties(url, username, password, poolSize, maxPoolSize);
        }
        return instance;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }
}
