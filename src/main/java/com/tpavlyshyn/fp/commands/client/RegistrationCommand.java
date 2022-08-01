package com.tpavlyshyn.fp.commands.client;

import com.tpavlyshyn.fp.EmailMessageHelper;
import com.tpavlyshyn.fp.UserDataValidator;
import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.services.UserService;
import com.tpavlyshyn.fp.services.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;


public class RegistrationCommand implements Command {
    private final static Logger log = Logger.getLogger(RegistrationCommand.class);

    private final UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        HttpSession session = request.getSession();
        ;

        boolean isLoginNotUnique = userService.checkUserLoginForUnique(login);
        if (isLoginNotUnique) {
            /*
                out.println("login must be unique.");
*/
            log.info("Login is not unique");
            request.setAttribute("message", "Login must be unique");
            return new Forward(Path.PAGE__REGISTRATION);
        }
           /* boolean result = false;

            result = userService.signUp(user);
            if (result) {
                out.println("done");
            } else out.println("error");*/

        UserDataValidator userDataValidator = new UserDataValidator();
        boolean isDataValid = userDataValidator.checkData(login, password, name, surname);
        if (!isDataValid) {
            log.info("Data is not valid");
            request.setAttribute("message", "Invalid data");
            return new Forward(Path.PAGE__REGISTRATION);
        }

        boolean isMessageSend = EmailMessageHelper.sendMessage(request);
        if (isMessageSend) {
            session.setAttribute("password", password);
            session.setAttribute("name", name);
            session.setAttribute("surname", surname);
            request.setAttribute("command", "submitRegistration");
            return new Forward(Path.PAGE__ENTER_OTP);
        } else {
            log.info("Can't send message to this email");
            request.setAttribute("message", "Can't send message to this email");
        }
        return new Forward(Path.PAGE__REGISTRATION);

    }
}
