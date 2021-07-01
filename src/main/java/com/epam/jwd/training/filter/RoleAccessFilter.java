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

@WebFilter
public class RoleAccessFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(RoleAccessFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String commandName = request.getParameter(RequestParameter.COMMAND);
        if (commandName == null) {
            LOGGER.error("command name isn't exist");
            request.getRequestDispatcher(PagePath.ERROR_404.getDirectUrl()).forward(servletRequest, servletResponse);
            return;
        }
        RoleType role = RoleType.GUEST;
        Set<CommandType> commandsByRole;

        User user = (User) session.getAttribute(RequestAttribute.USER);
        if (user != null) {
            role = user.getRole();
        }
        switch (role) {
            case ADMIN:
                commandsByRole = PageAccessType.ADMIN.getCommands();
                break;
            case USER:
                commandsByRole = PageAccessType.USER.getCommands();
                break;
            default:
                commandsByRole = PageAccessType.GUEST.getCommands();
                break;
        }
        boolean isHaveCommandType = false;
        for (CommandType commandType : commandsByRole) {
            String commandTypeString = commandType.toString();
            if (commandTypeString.equals(commandName.toUpperCase())) {
                isHaveCommandType = true;
                break;
            }
        }
        if (isHaveCommandType) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            LOGGER.error("command name isn't exist");
            request.getRequestDispatcher(PagePath.ERROR_404.getDirectUrl()).forward(servletRequest, servletResponse);
        }
    }
}
