package com.tpavlyshyn.fp.commands.action;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Dispatcher {

    void dispatch(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException;
}
