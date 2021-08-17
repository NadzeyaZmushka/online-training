package com.epam.jwd.training.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        String footer = "<h6>Made by Nadzeya Zmushka. All rights reserved. Minsk 2021</h6>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(footer);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
