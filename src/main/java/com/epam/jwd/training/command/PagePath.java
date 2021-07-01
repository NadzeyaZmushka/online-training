package com.epam.jwd.training.command;

public enum PagePath {

    INDEX("index.jsp", ""),
    MAIN("/page/main.jsp", "controller?command=main_page"),
    SIGN_IN("page/signIn.jsp", "controller?command=sign_in_page"),
    SIGN_UP("page/signUp.jsp", "controller?command=sign_up_page"),
    FORGOT_PASSWORD("page/forgotPassword.jsp", "controller?command=forgot_password_page"),
    REVIEW("page/review.jsp", "controller?command=review_page"),
    PERSONAL_AREA("page/personalArea.jsp", "controller?command=personal_area_page"),
    SHOW_ALL_TEACHERS("", "controller?command=show_all_teachers"),
    SHOW_ALL_USERS("", "controller?command=show_all_users&page="),
    ABOUT_US("page/aboutUs.jsp", "controller?command=about_us_page"),
    ERROR_404("page/error/error404.jsp", ""),
    ERROR_500 ("page/error/error500.jsp",""),
    LECTURE("page/lecture.jsp", "controller?command=lecture_page&course_id=");

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
