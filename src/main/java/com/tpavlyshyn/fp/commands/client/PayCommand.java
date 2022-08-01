package com.tpavlyshyn.fp.commands.client;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.RequestService;
import com.tpavlyshyn.fp.services.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class PayCommand implements Command {
    private final static Logger log = Logger.getLogger(PayCommand.class);

    private final RequestService requestService;

    public PayCommand(RequestService requestService) {
        this.requestService = requestService;
    }
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response)  {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        try {
            requestService.pay(requestId);
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect("error page");
        }
        return new Forward(Path.PAGE__PAY);
    }
}