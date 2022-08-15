package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.commands.action.Redirect;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.entity.TranslationCruise;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;
import com.tpavlyshyn.fp.services.impl.CruiseServiceImpl;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.sql.Date;

public class AddCruiseCommand implements Command {
    private final static Logger log = Logger.getLogger(AddCruiseCommand.class);

    private final CruiseService cruiseService;

    public AddCruiseCommand(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        int numberOfPorts = Integer.parseInt(request.getParameter("numberOfPorts"));
        int price = Integer.parseInt(request.getParameter("price"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        int linerId = Integer.parseInt(request.getParameter("linerId"));
        String nameUa = request.getParameter("nameUa");
        String nameEn = request.getParameter("nameEn");
        String descriptionUa = request.getParameter("descriptionUa");
        String descriptionEn = request.getParameter("descriptionEn");
        Cruise cruise = new Cruise();
        cruise.setNumberOfPorts(numberOfPorts);
        cruise.setPrice(price);
        cruise.setStartDate(startDate);
        cruise.setEndDate(endDate);
        cruise.setLinerId(linerId);

        boolean resultCruise;
        boolean resultTranslationUa;
        boolean resultTranslationEn;
        try {
            resultCruise = cruiseService.addCruise(cruise);
            TranslationCruise translationCruiseUa = new TranslationCruise(cruise.getId(), "ua", nameUa, descriptionUa);
            TranslationCruise translationCruiseEn = new TranslationCruise(cruise.getId(), "en", nameEn, descriptionEn);

            resultTranslationUa = cruiseService.addTranslationCruise(translationCruiseUa);
            resultTranslationEn = cruiseService.addTranslationCruise(translationCruiseEn);
            if (resultCruise && resultTranslationUa && resultTranslationEn) {
                request.setAttribute("cruiseId", cruise.getId());
                return new Forward(Path.COMMAND__SHOW_ALL_PORTS+cruise.getId());
            }
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            ex.getCause();
            return new Redirect("error page");
        }
        return new Forward(Path.COMMAND__SHOW_ALL_PORTS);
    }
}
