package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.EmailMessageHelper;
import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;

import com.tpavlyshyn.fp.commands.action.Redirect;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class ValidateOtpCommand implements Command {
    private final static Logger log = Logger.getLogger(ValidateOtpCommand.class);

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {

        boolean isValid = EmailMessageHelper.validate(request);
        if(isValid){

            request.setAttribute("command", "newPassword");
            return new Redirect(request.getContextPath()+Path.PAGE__NEW_PASSWORD);
        }else {
            log.info("wrong otp");
            request.setAttribute("message","wrong otp");
            return new Forward(Path.PAGE__ENTER_OTP);
        }
    }
}
