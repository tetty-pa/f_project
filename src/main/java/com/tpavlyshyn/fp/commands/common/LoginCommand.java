package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.UserService;
import com.tpavlyshyn.fp.services.impl.UserServiceImpl;
import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.util.Optional;

public class LoginCommand implements Command {
    private final static Logger log = Logger.getLogger(LoginCommand.class);

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<User> user;
        try {
            user = userService.signIn(login, password);
            if (user.isPresent()) {
                request.setAttribute("user", user.get());
                HttpSession currentSession = request.getSession();
                currentSession.setAttribute("user", user.get());
                return new Redirect(request.getContextPath() + "/" + Path.PAGE__INDEX);
            }
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect("error page");
        }

        request.setAttribute("message", "Incorrect login or password.");
        return new Forward(Path.PAGE__LOGIN);
    }
}
