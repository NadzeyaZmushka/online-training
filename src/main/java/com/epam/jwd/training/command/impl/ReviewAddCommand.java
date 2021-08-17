package com.epam.jwd.training.command.impl;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.command.SessionAttribute;
import com.epam.jwd.training.exception.ServiceException;
import com.epam.jwd.training.model.dao.impl.ReviewDaoImpl;
import com.epam.jwd.training.model.entity.Review;
import com.epam.jwd.training.model.entity.User;
import com.epam.jwd.training.model.service.ReviewService;
import com.epam.jwd.training.model.service.impl.ReviewServiceImpl;
import com.epam.jwd.training.validator.CourseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;

/**
 * The command add new review
 *
 * @author Nadzeya Zmushka
 */
public class ReviewAddCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ReviewAddCommand.class);

    private final ReviewService reviewService = new ReviewServiceImpl(new ReviewDaoImpl());
    private final CourseValidator courseValidator = CourseValidator.getInstance();

    @Override
    public CommandResponse execute(HttpServletRequest request) {
        String message = new String(request.getParameter(RequestParameter.MESSAGE).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);
        CommandResponse response = new CommandResponse();
        boolean isCorrectData = true;
        try {
            if (!courseValidator.isValidDescription(message)) {
                session.setAttribute(SessionAttribute.ERROR_MESSAGE, true);
                isCorrectData = false;
            }
            if (isCorrectData) {
                User user1 = User.builder()
                        .setId(user.getId())
                        .build();
                Review review = Review.builder()
                        .setUser(user1)
                        .setDescription(message)
                        .setDate(Date.valueOf(LocalDate.now()))
                        .build();
                reviewService.addReview(review);
            }
            response.setType(CommandResponse.Type.REDIRECT);
            response.setPagePath(PagePath.REVIEW.getServletPath());
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return response;
    }

}
