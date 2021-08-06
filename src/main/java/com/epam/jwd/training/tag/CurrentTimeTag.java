package com.epam.jwd.training.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Custom date and time tag that show current date and time in application.
 *
 * @author Nadzeya Zmushka
 */
public class CurrentTimeTag extends SimpleTagSupport {

    private static final String TAG_OUTPUT = "<h5>Current time: %s</h5>";

    @Override
    public void doTag() throws JspException {
        LocalDateTime currentTime = LocalDateTime.now();
        JspWriter out = getJspContext().getOut();
        try {
            out.println(String.format(TAG_OUTPUT, currentTime));
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

}
