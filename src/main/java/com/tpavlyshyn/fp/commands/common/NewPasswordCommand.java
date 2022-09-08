package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;

import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.commands.admin.AddPortToCruiseCommand;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.UserService;
import com.tpavlyshyn.fp.validators.UserDataValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

public class NewPasswordCommand implements Command {
    private final static Logger log = Logger.getLogger(NewPasswordCommand.class);

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
            UserDataValidator userDataValidator = new UserDataValidator();

            if (newPassword != null && newPassword.equals(confPassword) && userDataValidator.checkPassword(newPassword)) {
                boolean result = userService.setNewPassword(DigestUtils.sha256Hex(newPassword), (String) session.getAttribute("email"));
                if (result) {
                    out.println("done");
                }
            }
        } catch (IOException | ServiceException e) {
            log.error(e.getMessage());
            return new Redirect(Path.ERROR_PAGE);
        }
        return new Forward(Path.PAGE__NEW_PASSWORD);

    }
}
