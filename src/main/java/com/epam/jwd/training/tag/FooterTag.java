package com.epam.jwd.training.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterTag extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger(FooterTag.class);

    @Override
    public int doStartTag() throws JspException {
        String footer = "<h6>Made by Nadzeya Zmushka. All rights reserved. Minsk 2021</h6>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(footer);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

}
