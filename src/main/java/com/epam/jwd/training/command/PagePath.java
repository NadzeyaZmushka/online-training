package com.epam.jwd.training.command;

/**
 * Enum contains all page paths.
 *
 * @author Nadzeya Zmushka
 */
public enum PagePath {

    INDEX("index.jsp", ""),
    MAIN("WEB-INF/page/main.jsp", "controller?command=main_page"),
    SIGN_IN("WEB-INF/page/signIn.jsp", "controller?command=sign_in_page"),
    SIGN_UP("WEB-INF/page/signUp.jsp", "controller?command=sign_up_page"),
    REVIEW("WEB-INF/page/review.jsp", "controller?command=review_page"),
    ERROR_500("WEB-INF/page/error/error500.jsp", ""),
    ERROR_404("WEB-INF/page/error/error404.jsp", ""),
    COURSE("WEB-INF/page/course.jsp", "controller?command=course_page&course_id="),
    PROFILE("WEB-INF/page/profile.jsp", "controller?command=profile_page"),
    SHOW_ALL_TEACHERS("", "controller?command=show_all_teachers"),
    SHOW_ALL_USERS("", "controller?command=show_all_users"),
    ABOUT_US("WEB-INF/page/aboutUs.jsp", "controller?command=about_us_page"),
    CONTACTS("WEB-INF/page/contacts.jsp", "controller?command=contacts_page");

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
