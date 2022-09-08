package com.tpavlyshyn.fp.commands.client;

import com.tpavlyshyn.fp.EmailMessageHelper;
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
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;


public class SubmitRegistrationCommand implements Command {

    private final UserService userService;

    public SubmitRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        String name = (String) session.getAttribute("name");
        String surname = (String) session.getAttribute("surname");
        User user = new User(email, DigestUtils.sha256Hex(password), name, surname);

        boolean isValid = EmailMessageHelper.validate(request);
        if (isValid) {
            try {
                boolean result = userService.signUp(user);
                if (result) {
                    return new Redirect(request.getContextPath()+Path.PAGE__LOGIN);
                }
            } catch (ServiceException e) {
                return new Redirect(Path.ERROR_PAGE);
            }
        }
        return new Forward(Path.PAGE__ENTER_OTP);

    }
}
