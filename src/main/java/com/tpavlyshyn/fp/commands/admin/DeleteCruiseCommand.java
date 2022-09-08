package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

public class DeleteCruiseCommand implements Command {
    private final static Logger log = Logger.getLogger(DeleteCruiseCommand.class);

    private final CruiseService cruiseService;

    public DeleteCruiseCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()){

            int cruiseId = Integer.parseInt(request.getParameter("id"));
            boolean result ;

            boolean ifCruiseHasRequests = cruiseService.checkCruiseHasRequests(cruiseId);
            if(ifCruiseHasRequests) {
                out.println("Cruise has requests");
                return new Forward(Path.COMMAND__SHOW_ALL_CRUISES);
            }
            result = cruiseService.deleteCruise(cruiseId);
            if (result) {
                out.println("Done");
                return new Forward(Path.COMMAND__SHOW_ALL_CRUISES);
            }else out.println("error");
        } catch (ServiceException | IOException e) {
            log.error(e.getMessage());
            return new Redirect(Path.ERROR_PAGE);
        }
        return new Forward(Path.COMMAND__SHOW_ALL_CRUISES);
    }
}
