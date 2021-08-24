package com.epam.jwd.training.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * Custom date and time tag that show current date and time in application.
 *
 * @author Nadzeya Zmushka
 */
public class CurrentTimeTag extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger(CurrentTimeTag.class);

    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        String time = "<hr/><b> " + gc.getTime() + " </b><hr/>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

}
