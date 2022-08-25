package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.dto.CruisePort;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class RemovePortFromCruiseCommand implements Command {
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<CruisePort> cruisePorts = (List<CruisePort>) session.getAttribute("ports");
        cruisePorts.remove(cruisePorts.size() - 1);
        session.setAttribute("ports", cruisePorts);
        return new Redirect(request.getContextPath() + "/" + Path.COMMAND__SHOW_ALL_PORTS);
    }
}
