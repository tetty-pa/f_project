package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.dto.PortsNumberOfRows;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShowAllPortsCommand implements Command {
    private final CruiseService cruiseService;

    public ShowAllPortsCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        String land = (String) request.getSession().getAttribute("locale");
        try {
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int recordsPerPage = 15;
            int start = currentPage * recordsPerPage - recordsPerPage;

            PortsNumberOfRows portList = cruiseService.showPorts(land, start, recordsPerPage);
            request.setAttribute("ports", portList.getPorts());
            int numberOfPages = getNumberOfPages(recordsPerPage, portList.getNumberOfRows());
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);

        } catch (ServiceException e) {
            return new Redirect(Path.ERROR_PAGE);
        }
        return new Forward(Path.PAGE__SHOW_ALL_PORTS);
    }

    public int getNumberOfPages(int recordsPerPage, int numberOfRows) throws ServiceException {
        int numberOfPages;
        numberOfPages = numberOfRows / recordsPerPage;
        if (numberOfRows % recordsPerPage > 0) {
            numberOfPages++;
        }

        return numberOfPages;
    }
}
