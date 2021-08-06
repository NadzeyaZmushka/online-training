package com.epam.jwd.training.command;

/**
 * Enum contains all page paths.
 *
 * @author Nadzeya Zmushka
 */
public enum PagePath {

    INDEX("index.jsp", ""),
    MAIN("/page/main.jsp", "controller?command=main_page"),
    SIGN_IN("page/signIn.jsp", "controller?command=sign_in_page"),
    SIGN_UP("page/signUp.jsp", "controller?command=sign_up_page"),
    REVIEW("page/review.jsp", "controller?command=review_page"),
    ERROR_500("page/error/error500.jsp", ""),
    ERROR_404("page/error/error404.jsp", ""),
    COURSE("page/course.jsp", "controller?command=course_page&course_id="),
    PROFILE("page/profile.jsp", "controller?command=profile_page"),
    SHOW_ALL_TEACHERS("", "controller?command=show_all_teachers"),
    SHOW_ALL_USERS("", "controller?command=show_all_users"),
    ABOUT_US("page/aboutUs.jsp", "");

    private final String directUrl;
    private final String servletPath;

    PagePath(String directUrl, String servletPath) {
        this.directUrl = directUrl;
        this.servletPath = servletPath;
    }

    /**
     * @return the direct url
     */
    public String getDirectUrl() {
        return directUrl;
    }

    /**
     * @return the servlet path
     */
    public String getServletPath() {
        return servletPath;
    }
}
