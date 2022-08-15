package com.tpavlyshyn.fp.commands.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import java.io.IOException;
import java.sql.Date;

public class UpdateCruiseCommand implements Command {
    private final static Logger log = Logger.getLogger(UpdateCruiseCommand.class);

    private final CruiseService cruiseService;

    public UpdateCruiseCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String cruiseName = request.getParameter("cruiseName");
//        String description = request.getParameter("description");
//
//        int numberOfPorts = Integer.parseInt(request.getParameter("numberOfPorts"));
//        Date startDate = Date.valueOf(request.getParameter("startDate"));
//        Date endDate = Date.valueOf(request.getParameter("endDate"));
//        int price = Integer.parseInt(request.getParameter("price"));
//        Cruise cruise = new Cruise(price, numberOfPorts, cruiseName, description, startDate, endDate);
//        cruise.setId(id);
//        try {
//            boolean result = cruiseService.updateCruise(cruise);
//        } catch (ServiceException ex) {
//            log.error(ex.getMessage(), ex);
//            return new Redirect("error page");
//        }
//
        var gson = new GsonBuilder()
                .create();
        try {
            var cruise = gson.fromJson(request.getReader(), Cruise.class);
            cruiseService.updateCruise(cruise);
        } catch (Exception e) {
            return new Redirect("ERROR");
        }
        return new Forward(Path.PAGE__INDEX);
    }
}
