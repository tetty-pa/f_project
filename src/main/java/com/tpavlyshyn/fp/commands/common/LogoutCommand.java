package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class LogoutCommand implements Command {
    private final static Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (session != null)
            session.invalidate();

        log.debug("User logout-->"+user);
        return new Redirect(request.getContextPath()+Path.PAGE__INDEX);
    }
}
