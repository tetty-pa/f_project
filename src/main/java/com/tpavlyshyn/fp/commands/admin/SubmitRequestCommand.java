package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.RequestService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class SubmitRequestCommand implements Command {
    private final static Logger log = Logger.getLogger(SubmitRequestCommand.class);

    private final RequestService requestService;

    public SubmitRequestCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        try {
            requestService.submitRequest(requestId);
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect(Path.ERROR_PAGE);
        }
        return new Redirect("${pageContext.request.contextPath}/controller?command=showAllRequests");
    }
}
