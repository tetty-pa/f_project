package com.tpavlyshyn.fp.commands.admin;

import com.tpavlyshyn.fp.MessageManager;
import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.dto.CruisePort;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.entity.TranslationCruise;

import com.tpavlyshyn.fp.validators.CruiseDataValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.tpavlyshyn.fp.MessageManager.DATA_IS_NOT_VALID;

public class PutCruiseIntoSessionCommand implements Command {
    private final static Logger log = Logger.getLogger(PutCruiseIntoSessionCommand.class);


    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
        int numberOfPorts = Integer.parseInt(request.getParameter("numberOfPorts"));
        int price = Integer.parseInt(request.getParameter("price"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        int linerId = Integer.parseInt(request.getParameter("linerId"));
        Cruise cruise = new Cruise(linerId, price, numberOfPorts, startDate, endDate);
        CruiseDataValidator cruiseDataValidator = new CruiseDataValidator();


        String nameUa = request.getParameter("nameUa");
        String nameEn = request.getParameter("nameEn");
        String descriptionUa = request.getParameter("descriptionUa");
        String descriptionEn = request.getParameter("descriptionEn");

        TranslationCruise translationCruiseUa = new TranslationCruise(0, "ua", nameUa, descriptionUa);
        TranslationCruise translationCruiseEn = new TranslationCruise(0, "en", nameEn, descriptionEn);

        boolean isDataValid = cruiseDataValidator.checkCruiseData(cruise);
        boolean isTranslationValidUa = cruiseDataValidator.checkTranslationCruise(translationCruiseUa);
        boolean isTranslationValidEn = cruiseDataValidator.checkTranslationCruise(translationCruiseEn);
        if (!isTranslationValidEn || !isTranslationValidUa || !isDataValid) {
            log.info("Data is not valid");
            request.setAttribute("message", MessageManager.getProperty(DATA_IS_NOT_VALID));
            return new Forward(Path.PAGE__ADD_CRUISE);
        }

        HttpSession session = request.getSession();
        List<CruisePort> ports = new ArrayList<>();
        session.setAttribute("cruise", cruise);
        session.setAttribute("translationCruiseUa", translationCruiseUa);
        session.setAttribute("translationCruiseEn", translationCruiseEn);
        session.setAttribute("ports", ports);

        return new Forward(Path.COMMAND__SHOW_ALL_PORTS+1);

    }

}
