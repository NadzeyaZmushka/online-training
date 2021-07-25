package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.entity.RoleType;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.ReviewService;
import com.epam.jwd.training.model.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ReviewDeleteCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ReviewDeleteCommand.class);

    private final ReviewService reviewService = ReviewServiceImpl.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String reviewIdString = request.getParameter(RequestParameter.REVIEW_ID);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        CommandResponse response = new CommandResponse();
        try {
            Long reviewId = Long.valueOf(reviewIdString);
            if (user.getRole() == RoleType.ADMIN) {
                reviewService.delete(reviewId);
                response.setType(CommandResponse.Type.REDIRECT);
                response.setPagePath(PagePath.REVIEW.getServletPath());
            } else {
                boolean isHasReview = reviewService.isUserHasReview(reviewId, user.getId());
                if (isHasReview) {
                    reviewService.delete(reviewId);
                    response.setType(CommandResponse.Type.REDIRECT);
                    response.setPagePath(PagePath.REVIEW.getServletPath());
                }
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }
}
