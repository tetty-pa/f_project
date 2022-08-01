package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;
import com.tpavlyshyn.fp.services.impl.CruiseServiceImpl;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.sql.Date;

public class AddCruiseCommand implements Command {
    private final static Logger log = Logger.getLogger(AddCruiseCommand.class);

    private final CruiseService cruiseService;

    public AddCruiseCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int numberOfPorts = Integer.parseInt(request.getParameter("numberOfPorts"));
        int price = Integer.parseInt(request.getParameter("price"));
        String route = request.getParameter("route");
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        int linerId = Integer.parseInt(request.getParameter("linerId"));
        Cruise cruise = new Cruise();
        cruise.setCruiseName(name);
        cruise.setDescription(description);
        cruise.setNumberOfPorts(numberOfPorts);
        cruise.setPrice(price);
        cruise.setStartDate(startDate);
        cruise.setEndDate(endDate);
        cruise.setLinerId(linerId);
        boolean result;
        try {
            result = cruiseService.addCruise(cruise);

            if (result) {
                request.setAttribute("cruise", cruise);
                return new Forward("/index.jsp");
            }
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect("error page");
        }
        return new Forward(Path.PAGE__REGISTRATION);
    }
}
