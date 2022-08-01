package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.EmailMessageHelper;
import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ValidateOtpCommand implements Command {
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
 /*       int value=Integer.parseInt(request.getParameter("otp"));
        HttpSession session=request.getSession();
        int otp=(int)session.getAttribute("otp");

        if (value==otp)
        {
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            return new Forward(Path.PAGE__NEW_PASSWORD);
        }
        else
        {
            request.setAttribute("message","wrong otp");
            return new Forward(Path.PAGE__ENTER_OTP);

        }*/

        boolean isValid = EmailMessageHelper.validate(request);
        if(isValid){
/*
            request.setAttribute("email", request.getParameter("email"));
*/
            request.setAttribute("command", "newPassword");
            return new Forward(Path.PAGE__NEW_PASSWORD);
        }else {
/*
            request.setAttribute("message","wrong otp");
*/
            return new Forward(Path.PAGE__ENTER_OTP);
        }
    }
}
