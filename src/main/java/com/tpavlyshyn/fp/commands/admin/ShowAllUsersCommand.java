package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.UserService;
import com.tpavlyshyn.fp.services.impl.CruiseServiceImpl;
import com.tpavlyshyn.fp.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.util.List;

public class ShowAllUsersCommand implements Command {
    private final static Logger log = Logger.getLogger(ShowAllUsersCommand.class);
    private final UserService userService;

    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {

        List<User> users ;
        try {
            users = userService.showUsers();
            request.setAttribute("users", users);
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect("error page");
        }
        return new Forward(Path.PAGE__SHOW_ALL_USERS);
    }
}
