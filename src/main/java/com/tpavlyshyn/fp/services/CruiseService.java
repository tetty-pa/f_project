package com.tpavlyshyn.fp.services;

import com.tpavlyshyn.fp.dto.CruisePort;
import com.tpavlyshyn.fp.dto.CruisesNumberOfRows;
import com.tpavlyshyn.fp.dto.PortsNumberOfRows;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.entity.Port;
import com.tpavlyshyn.fp.entity.TranslationCruise;
import com.tpavlyshyn.fp.exceptions.ServiceException;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CruiseService {

    CruisesNumberOfRows showCruisesWithParam(Integer from, Integer to, Optional<Integer> month, Optional<Integer> year, int currentPage, int recordsPerPage, String land) throws ServiceException;


    boolean deleteCruise(int cruiseId) throws ServiceException;

    Cruise showCruiseInfo(int cruiseId, String land) throws ServiceException;

    List<Cruise> showCruises(String land) throws ServiceException;

    PortsNumberOfRows showPorts(String land, int currentPage, int recordsPerPage) throws ServiceException;

    boolean checkCruiseHasRequests(int id);

    boolean addCruiseWithTranslations(Cruise cruise, TranslationCruise translationCruiseEn, TranslationCruise translationCruiseUa, List<CruisePort> cruisePorts) throws ServiceException;

}
