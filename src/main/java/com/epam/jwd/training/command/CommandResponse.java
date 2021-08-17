package com.epam.jwd.training.command;

public class CommandResponse {

    /**
     * Inner enum describe all possibles response type
     *
     * @author Nadzeya Zmushka
     */
    public enum Type {
        FORWARD,
        REDIRECT
    }

    private String pagePath;
    private Type type;

    /**
     * Constructor set default type: Forward
     */
    public CommandResponse() {
        this.type = Type.FORWARD;
    }

    /**
     * Constructor set default route type: Forward
     *
     * @param pagePath the page path
     */
    public CommandResponse(String pagePath) {
        this(pagePath, Type.FORWARD);
    }

    /**
     * Constructor
     *
     * @param pagePath the page path
     * @param type     (type of response)
     */
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
