package com.tpavlyshyn.fp.commands.client;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.request.Request;
import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.RequestService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.util.List;

public class ShowUsersRequestsCommand implements Command {
    private final static Logger log = Logger.getLogger(ShowUsersRequestsCommand.class);

    private final RequestService requestService;

    public ShowUsersRequestsCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        List<Request> requests;
        try {
            String land = (String) request.getSession().getAttribute("locale");
            requests = requestService.showUsersRequests(userId, land);
            request.setAttribute("requests", requests);
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect(Path.ERROR_PAGE);
        }
        return new Forward(Path.PAGE__SHOW_USERS_REQUESTS);
    }
}
