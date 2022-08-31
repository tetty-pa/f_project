package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.dto.CruisePort;

import com.tpavlyshyn.fp.services.CruiseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;


public class AddPortToCruiseCommand implements Command {
    private final CruiseService cruiseService;

    public AddPortToCruiseCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }


    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        int portId = Integer.parseInt(request.getParameter("portId"));
        String arrivalTime = request.getParameter("time");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        java.util.Date date = null;
        try {
             date = formatter.parse(arrivalTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime());

        HttpSession session = request.getSession();
        List<CruisePort> cruisePorts = (List<CruisePort>) session.getAttribute("ports");

        int sequence_number = cruisePorts.size() + 1;

        CruisePort cruisePort = new CruisePort( portId, sequence_number, sqlStartDate);
        cruisePorts.add(cruisePort);

        session.setAttribute("ports", cruisePorts);
        return new Redirect(request.getContextPath() + Path.COMMAND__SHOW_ALL_PORTS);
    }
}

