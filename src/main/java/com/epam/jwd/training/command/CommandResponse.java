package com.epam.jwd.training.command;

public class CommandResponse {

    public enum Type {
        FORWARD,
        REDIRECT
    }

    private String pagePath;
    private Type type;

    public CommandResponse() {
        this.type = Type.FORWARD;
    }

    public CommandResponse(String pagePath) {
        this(pagePath, Type.FORWARD);
    }

    public CommandResponse(String pagePath, Type type) {
        this.pagePath = pagePath;
        this.type = type;
    }

    public String getPagePath() {
        return pagePath;
    }

    public Type getType() {
        return type;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
