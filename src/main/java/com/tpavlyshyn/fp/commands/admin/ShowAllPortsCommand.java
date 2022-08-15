package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.entity.Port;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class ShowAllPortsCommand implements Command {
    private final CruiseService cruiseService;

    public ShowAllPortsCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        String land = (String) request.getSession().getAttribute("locale");
        try {
            List<Port> portList = cruiseService.showPorts(land);
            request.setAttribute("ports", portList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new Forward(Path.PAGE__ADD_CRUISE);
    }
}
