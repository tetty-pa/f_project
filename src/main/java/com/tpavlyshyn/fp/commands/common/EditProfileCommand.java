package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.UserService;
import com.tpavlyshyn.fp.services.impl.UserServiceImpl;
import com.tpavlyshyn.fp.validators.UserDataValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.util.Objects;

public class EditProfileCommand implements Command {
    private final static Logger log = Logger.getLogger(UserServiceImpl.class);

    private final UserService userService;

    public EditProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        String url = request.getHeader("referer");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passwordSecond = request.getParameter("passwordSecond");
        if (Objects.equals(password, passwordSecond)) {
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            HttpSession session = request.getSession();
            User user1 = (User) session.getAttribute("user");
            user.setId(user1.getId());
            UserDataValidator userDataValidator = new UserDataValidator();
            boolean isDataValid = userDataValidator.checkData(login, name, name, surname);
            if(isDataValid) {
                try {
                    userService.editProfile(user);
                } catch (ServiceException ex) {
                    log.error(ex.getMessage(), ex);
                    ex.printStackTrace();
                }
            }
        }
        return new Redirect(url);

    }
}
