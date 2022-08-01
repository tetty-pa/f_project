package com.tpavlyshyn.fp.services;

import com.tpavlyshyn.fp.dto.CruisesNumberOfRows;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CruiseService {
    CruisesNumberOfRows showCruisesWithParam(Integer from, Integer to, Optional<Integer> month, Optional<Integer> year, int currentPage, int recordsPerPage, String land) throws ServiceException;

    boolean addCruise(Cruise cruise) throws ServiceException;

    boolean updateCruise(Cruise cruise) throws ServiceException;

    boolean deleteCruise(int cruiseId) throws ServiceException;

    Cruise showCruiseInfo(int cruiseId, String land) throws ServiceException;

    List<Cruise> showCruises(String land) throws ServiceException;

    boolean checkCruiseHasRequests(int id);


}
