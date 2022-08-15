package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.time.LocalDateTime;


public class AddPortToCruiseCommand implements Command {
    private final CruiseService cruiseService;

    public AddPortToCruiseCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }


    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));
        int portId = Integer.parseInt(request.getParameter("portId"));
/*
        Date arrivalTime = Date.valueOf(request.getParameter("time"));
*/
        LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("time"));
/*
        int sequence_number = Integer.parseInt(request.getParameter("sequenceNumber"));
*/
        try {
            cruiseService.addPortToCruise(cruiseId, portId, 3, localDateTime);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new Forward(Path.COMMAND__SHOW_ALL_PORTS);
    }
}

