package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;

import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class NewPasswordCommand implements Command {

    private final UserService userService;

    public NewPasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            String newPassword = request.getParameter("password");
            String confPassword = request.getParameter("confPassword");

            if (newPassword != null && newPassword.equals(confPassword)) {
                boolean result = userService.setNewPassword(newPassword, (String) session.getAttribute("email"));
                if (result) {
                    out.println("done");
                    return new Forward(Path.PAGE__LOGIN);
                }
            }
        } catch (IOException | ServiceException e) {
            e.printStackTrace();
        }
        return new Forward(Path.PAGE__NEW_PASSWORD);

    }
}
