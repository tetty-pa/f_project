package com.tpavlyshyn.fp.controller;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.CommandContainer;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.apache.log4j.Logger;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet("/controller/*")
public class Controller extends HttpServlet {

    private final static Logger log = Logger.getLogger(Servlet.class);
    private CommandContainer commandList;

    @Override
    public void init(ServletConfig config) {
        commandList = (CommandContainer) config.getServletContext().getAttribute("commandContainer");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp){

        String commandName = req.getParameter("command");
        log.info("Request parameter: command --> " + commandName);
        Command command = commandList.getCommand(commandName);
        log.info("Obtained command --> " + command);

        try {
            Dispatcher dispatcher = command.execute(req, resp);
            dispatcher.dispatch(req, resp);
        } catch (IOException | ServletException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
