package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.impl.ReviewDaoImpl;
import com.epam.jwd.training.model.entity.Review;
import com.epam.jwd.training.model.service.ReviewService;
import com.epam.jwd.training.model.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The command opens reviews
 *
 * @author Nadzeya Zmushka
 */
public class OpenReviewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(OpenReviewCommand.class);

    private final ReviewService reviewService = new ReviewServiceImpl(new ReviewDaoImpl());

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        CommandResponse response = new CommandResponse();
        HttpSession session = request.getSession();
        try {
            List<Review> reviews = reviewService.findAll();
            request.setAttribute(RequestAttribute.REVIEWS, reviews);
            response.setPagePath(PagePath.REVIEW.getDirectUrl());
            session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.REVIEW.getServletPath());
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
