package com.epam.jwd.training.tag;

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

//    private static final String TAG_OUTPUT = "<h5>Current time: %s</h5>";

    //    @Override
//    public void doTag() throws JspException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault());
//        String currentDate = formatter.format(LocalDateTime.now());
//        JspWriter out = getJspContext().getOut();
//        try {
//            out.println(currentDate);
//        } catch (IOException e) {
//            throw new JspException(e);
//        }
//    }
    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        String time = "<hr/><b> " + gc.getTime() + " </b><hr/>";
//        String locale = "Locale : <b> " + Locale.getDefault() + " </b><hr/> ";
        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
