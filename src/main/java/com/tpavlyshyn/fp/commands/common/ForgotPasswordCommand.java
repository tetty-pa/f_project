package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.EmailMessageHelper;
import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ForgotPasswordCommand implements Command {
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {

        EmailMessageHelper.sendMessage(request);
        request.setAttribute("command", "validateOtp");
        return new Forward(Path.PAGE__ENTER_OTP);

    }
}