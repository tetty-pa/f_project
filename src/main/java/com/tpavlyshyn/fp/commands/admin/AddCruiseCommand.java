package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.dto.CruisePort;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.entity.TranslationCruise;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.util.List;

public class AddCruiseCommand implements Command {
    private final static Logger log = Logger.getLogger(PutCruiseIntoSessionCommand.class);

    private final CruiseService cruiseService;

    public AddCruiseCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<CruisePort> ports = (List<CruisePort>) session.getAttribute("ports");
        Cruise cruise = (Cruise) session.getAttribute("cruise");
        TranslationCruise ua = (TranslationCruise) session.getAttribute("translationCruiseUa");
        TranslationCruise en = (TranslationCruise) session.getAttribute("translationCruiseEn");
        try {
            if(!ports.isEmpty()) {
                boolean result = cruiseService.addCruiseWithTranslations(cruise, ua, en, ports);
            }
            else log.info("ports can`t be empty");
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new Redirect(request.getContextPath()+ Path.PAGE__INDEX);
    }
}
