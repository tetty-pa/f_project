package com.tpavlyshyn.fp.commands.action;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirect implements Dispatcher {
    private final String path;

    public Redirect(String path) {this.path = path;}

    @Override
    public void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(path);
    }
}
