package com.tpavlyshyn.fp.dao;

import com.tpavlyshyn.fp.dto.CruisesNumberOfRows;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.entity.TranslationCruise;
import com.tpavlyshyn.fp.exceptions.DaoException;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CruiseDao extends AbstractDao<Integer, Cruise>{
    List<Cruise> findAllByLang(String lang) throws DaoException;
    Optional<Cruise> findByIdAndLang(Integer id, String lang) throws DaoException;
    CruisesNumberOfRows findAllWithParam(String whereExpression,
                                         Integer from,
                                         Integer to,
                                         Optional<Integer> month,
                                         Optional<Integer> year,
                                         int currentPage,
                                         int recordsPerPage,
                                         String land) throws DaoException;
    int getFreePlaces(int cruise_id) throws DaoException;
    boolean createTranslationCruise(TranslationCruise cruise) throws DaoException;
    boolean addPortToCruise(int cruiseId, int portId, int sequenceNumber, LocalDateTime arrivalTime) throws DaoException;
    }
