package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.Liner;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.LinerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class FindAllLinersCommand implements Command {
    private final LinerService linerService;

    public FindAllLinersCommand(LinerService linerService) {
        this.linerService = linerService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        List<Liner> liners = null;
        try {
            liners = linerService.showAllLiners();
        } catch (ServiceException e) {
            e.printStackTrace();
            return new Redirect("error page");
        }
        request.setAttribute("liners", liners);
        return new Forward(Path.PAGE__ADD_CRUISE);
    }
}
