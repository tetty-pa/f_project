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

import java.io.IOException;
import java.io.PrintWriter;


public class MakeRequestCommand implements Command {
    private final static Logger log = Logger.getLogger(MakeRequestCommand.class);


    private final RequestService requestService;

    public MakeRequestCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter writer = response.getWriter()) {


            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            int userId = user.getId();

            Request userRequest = new Request();
            int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));
            int amount = Integer.parseInt(request.getParameter("amount"));

            userRequest.setAmount(amount);
            userRequest.setUserId(userId);
            userRequest.setCruiseId(cruiseId);

            boolean result;
            try {
                result = requestService.makeRequest(userRequest);
                if (result) {
                    writer.println("done");
                return new Redirect(request.getContextPath() + Path.COMMAND__SHOW_CRUISES + userId);
                }
            } catch (ServiceException ex) {
                log.error(ex.getMessage(), ex);
                return new Redirect(Path.ERROR_PAGE);
            }
        } catch (IOException e) {
            return new Redirect(Path.ERROR_PAGE);

        }
        return new Forward(Path.PAGE__INDEX);
    }
}
