package com.tpavlyshyn.fp.services.impl;

import com.tpavlyshyn.fp.dao.CruiseDao;
import com.tpavlyshyn.fp.dao.PortDao;
import com.tpavlyshyn.fp.dao.RequestDao;
import com.tpavlyshyn.fp.dto.CruisePort;
import com.tpavlyshyn.fp.dto.CruisesNumberOfRows;
import com.tpavlyshyn.fp.dto.PortsNumberOfRows;
import com.tpavlyshyn.fp.entity.Entity;
import com.tpavlyshyn.fp.entity.Port;
import com.tpavlyshyn.fp.entity.TranslationCruise;
import com.tpavlyshyn.fp.exceptions.DaoException;
import com.tpavlyshyn.fp.entity.Cruise;

import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;
import org.apache.log4j.Logger;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CruiseServiceImpl implements CruiseService {

    private final static Logger log = Logger.getLogger(CruiseServiceImpl.class);

    private final CruiseDao cruiseDao;
    private final PortDao portDao;
    private final RequestDao requestDao;

    public CruiseServiceImpl(CruiseDao cruiseDao, PortDao portDao, RequestDao requestDao) {
        this.cruiseDao = cruiseDao;
        this.portDao = portDao;
        this.requestDao = requestDao;
    }


    @Override
    public CruisesNumberOfRows showCruisesWithParam(Integer from, Integer to, Optional<Integer> month, Optional<Integer> year, int currentPage, int recordsPerPage, String lang) throws ServiceException {
        CruisesNumberOfRows cruisesNumberOfRows;
        Optional<String> whereDurationOpt = builtWhereDurationOpt();
        Optional<String> whereMonthOpt = builtWhereMonthOpt(month, year);
        String whereClauseOpt;
        log.debug("Building where clause for query");
        whereClauseOpt = " WHERE tc.lang=? ";
        whereClauseOpt = whereClauseOpt + whereMonthOpt.map(s -> whereDurationOpt.get() + " AND " + s).orElseGet(whereDurationOpt::get);


        whereClauseOpt = whereClauseOpt + " LIMIT ?, ?";
        log.debug("Where clause-->" + whereClauseOpt);
        try {
            cruisesNumberOfRows = cruiseDao.findAllWithParam(whereClauseOpt, from, to, month, year, currentPage, recordsPerPage, lang);
            log.info("Found cruises-->" + cruisesNumberOfRows.getCruises());
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return cruisesNumberOfRows;
    }

    private Optional<String> builtWhereDurationOpt() {
        List<String> params = new ArrayList<>();
        params.add("?");
        params.add("?");
        return params.stream().reduce((a, b) -> " AND DATEDIFF(end_date, start_date) BETWEEN " + a + " AND " + b);
    }

    private Optional<String> builtWhereMonthOpt(Optional<Integer> monthOpt, Optional<Integer> yearOpt) {
        List<String> params = new ArrayList<>();

        monthOpt.ifPresent(s -> {
            params.add("?");
        });

        yearOpt.ifPresent(s -> {
            params.add("?");
        });

        return params.stream().reduce((a, b) -> " month(start_date) = " + a + " AND year(start_date) = " + b);
    }


    @Override
    public boolean deleteCruise(int cruiseId) throws ServiceException {
        boolean result ;
        try {
            result = cruiseDao.delete(cruiseId);
            if (result) log.info("Cruise with id = " + cruiseId + " was deleted");
            else log.info("Cruise with id = " + cruiseId + " wasn`t deleted");
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return result;
    }

    @Override
    public Cruise showCruiseInfo(int cruiseId, String lang) throws ServiceException {
        Optional<Cruise> cruise;
        try {
            cruise = cruiseDao.findByIdAndLang(cruiseId, lang);
            log.info("Detailed info about-->" + cruise);
            log.info("ports" + cruise.get().getPortList());
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return cruise.get();
    }

    @Override
    public List<Cruise> showCruises(String lang) throws ServiceException {
        List<Cruise> cruises;
        try {
            cruises = cruiseDao.findAllByLang(lang);
            List<Integer> list = cruises.stream().map(Entity::getId).collect(Collectors.toList());
            List<Port> portList = portDao.findAllByCruiseIds(list, lang);
            for (Cruise cruise : cruises) {
                List<Port> cruisesPorts = portList.stream().filter(port -> port.getCruiseId() == cruise.getId()).collect(Collectors.toList());
                cruise.setPortList(cruisesPorts);
            }
            log.error(portList);
            if (!cruises.isEmpty()) log.info("Found cruises-->" + cruises);
            else log.info("Cruises weren`t found");
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return cruises;
    }

    @Override
    public PortsNumberOfRows showPorts(String lang, int currentPage, int recordsPerPage) throws ServiceException {
        PortsNumberOfRows portList;
        try {
            portList = portDao.findAllByLang(lang, currentPage, recordsPerPage);
            if (!portList.getPorts().isEmpty()) log.info("Found ports-->" + portList);
            else log.info("Ports weren`t found");
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return portList;
    }


    @Override
    public boolean checkCruiseHasRequests(int id) {
        try {
            return requestDao.findRequestByCruiseId(id);
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean addCruiseWithTranslations(Cruise cruise, TranslationCruise translationCruiseEn, TranslationCruise translationCruiseUa, List<CruisePort> cruisePorts) throws ServiceException {
        try {
            return cruiseDao.createCruiseWithTranslations(cruise, translationCruiseUa, translationCruiseEn, cruisePorts);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return false;
    }


}
