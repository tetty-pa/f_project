package com.tpavlyshyn.fp.commands;

import com.tpavlyshyn.fp.commands.action.Dispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface Command {

    Dispatcher execute(HttpServletRequest request, HttpServletResponse response);

}
