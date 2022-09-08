package com.tpavlyshyn.fp.commands.client;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class ShowCruiseInfoCommand implements Command {
    private final static Logger log = Logger.getLogger(ShowCruiseInfoCommand.class);

    private final CruiseService cruiseService;

    public ShowCruiseInfoCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));
        Cruise cruise;
        try {
            String land = (String) request.getSession().getAttribute("locale");
            cruise = cruiseService.showCruiseInfo(cruiseId, land);
            request.setAttribute("cruise", cruise);
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return new Redirect(Path.ERROR_PAGE);
        }
        return new Forward(Path.PAGE__SHOW_CRUISE_INFO);
    }
}
