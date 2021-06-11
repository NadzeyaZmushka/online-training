package com.epam.jwd.training.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/controller")
public class ApplicationController extends HttpServlet {

    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try (final PrintWriter writer = resp.getWriter()) {
//            writer.println("Hello World");
//            final Enumeration<String> headerNames = req.getHeaderNames();
//            while (headerNames.hasMoreElements()) {
//                final String headerName = headerNames.nextElement();
//                writer.format("%s: %s\n", headerName, req.getHeader(headerName));
//            }
//        }
//    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<h2>Hello from HelloServlet</h2>");
        }
    }

}

