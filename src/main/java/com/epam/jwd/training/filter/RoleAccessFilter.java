package com.epam.jwd.training.filter;

import com.epam.jwd.training.command.CommandType;
import com.epam.jwd.training.command.PageAccessType;
import com.epam.jwd.training.command.PagePath;
import com.epam.jwd.training.command.RequestAttribute;
import com.epam.jwd.training.command.RequestParameter;
import com.epam.jwd.training.model.entity.RoleType;
import com.epam.jwd.training.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Make command access checking
 *
 * @author Nadzeya Zmushka
 */
@WebFilter
public class RoleAccessFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(RoleAccessFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String commandName = request.getParameter(RequestParameter.COMMAND);
        if (commandName == null) {
            LOGGER.error("Command name isn't exist");
            request.getRequestDispatcher(PagePath.ERROR_404.getDirectUrl());
            return;
        }

        RoleType role = RoleType.GUEST;
        Set<CommandType> commandTypes;
        User user = (User) session.getAttribute(RequestAttribute.USER);

        if (user != null) {
            role = user.getRole();
        }

        switch (role) {
            case ADMIN:
                commandTypes = PageAccessType.ADMIN.getCommands();
                break;
            case USER:
                commandTypes = PageAccessType.USER.getCommands();
                break;
            default:
                commandTypes = PageAccessType.GUEST.getCommands();
                break;
        }

        commandName = commandName.toUpperCase();
        boolean isHaveCommand = false;
        for (CommandType commandType : commandTypes) {
            String commandTypeString = commandType.toString();
            if (commandTypeString.equals(commandName)) {
                isHaveCommand = true;
            }
        }
        if (isHaveCommand) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            LOGGER.error("Command name isn't exist");
            request.getRequestDispatcher(PagePath.ERROR_404.getDirectUrl());
        }
    }

}

