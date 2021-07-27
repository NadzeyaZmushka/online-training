package com.epam.jwd.training.controller;

import com.epam.jwd.training.command.Command;
import com.epam.jwd.training.command.CommandFactory;
import com.epam.jwd.training.command.CommandResponse;
import com.epam.jwd.training.pool.ConcurrentConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller", "*.do"})
public class ApplicationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.defineCommand(request);
        CommandResponse commandResponse = command.execute(request);

        if (commandResponse.getType().equals(CommandResponse.Type.FORWARD)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(commandResponse.getPagePath());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect(commandResponse.getPagePath());
        }
    }

    @Override
    public void destroy() {
        ConcurrentConnectionPool.getInstance().destroy();
    }

}

