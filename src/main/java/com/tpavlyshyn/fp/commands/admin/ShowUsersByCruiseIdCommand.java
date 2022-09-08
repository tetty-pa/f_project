package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShowUsersByCruiseIdCommand implements Command {
    private final static Logger log = Logger.getLogger(ShowUsersByCruiseIdCommand.class);

    private final UserService userService;

    public ShowUsersByCruiseIdCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));
        try {
            List<User> userList = userService.showUsersByCruiseId(cruiseId);

            Set<User> users = new HashSet<>(userList);
            request.setAttribute("users", users);
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect(Path.ERROR_PAGE);
        }
        return new Forward(Path.PAGE__SHOW_ALL_USERS);
    }
}
