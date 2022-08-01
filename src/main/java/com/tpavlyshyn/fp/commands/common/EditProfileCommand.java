package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.UserService;
import com.tpavlyshyn.fp.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class EditProfileCommand implements Command {
    private final static Logger log = Logger.getLogger(UserServiceImpl.class);

    private final UserService userService;

    public EditProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        HttpSession session = request.getSession();
        User user1 = (User) session.getAttribute("user");
        user.setId(user1.getId());
        try {
            userService.editProfile(user);
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
        return new Redirect(request.getContextPath() + Path.PAGE__INDEX);
    }
}
