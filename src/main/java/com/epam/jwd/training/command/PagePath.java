package com.epam.jwd.training.command;

public enum PagePath {

    INDEX("index.jsp", ""),
    MAIN("/page/main.jsp", "controller?command=main_page"),
    SIGN_IN("page/signIn.jsp", "controller?command=sign_in_page"),
    SIGN_UP("page/signUp.jsp", "controller?command=sign_up_page"),

    ERROR_500 ("page/error/error500.jsp",""),
    COURSE("page/course.jsp", "controller?command=course_page&course_id="),
    PROFILE("page/profile.jsp", "controller?command=profile_page");

    private final String directUrl;
    private final String servletPath;

    PagePath(String directUrl, String servletPath) {
        this.directUrl = directUrl;
        this.servletPath = servletPath;
    }

    public String getDirectUrl() {
        return directUrl;
    }

    public String getServletPath() {
        return servletPath;
    }
}
