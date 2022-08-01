package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.request.Request;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.RequestService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.util.List;


public class ShowAllRequestsCommand implements Command {
    private final static Logger log = Logger.getLogger(ShowAllRequestsCommand.class);

    private final RequestService requestService;

    public ShowAllRequestsCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        List<Request> requests ;
        try {
            String land = (String) request.getSession().getAttribute("locale");
            requests = requestService.showRequests(land);
            request.setAttribute("requests", requests);
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect("error page");
        }
        return new Forward(Path.PAGE__SHOW_ALL_REQUESTS);
    }
}
