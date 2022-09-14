package com.tpavlyshyn.fp.commands.action;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Forward implements Dispatcher {
    private final String path;

    public Forward(String path) {this.path = path;}

    @Override
    public void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
