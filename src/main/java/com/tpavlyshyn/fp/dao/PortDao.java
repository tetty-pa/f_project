package com.tpavlyshyn.fp.dao;

import com.tpavlyshyn.fp.dto.PortsNumberOfRows;
import com.tpavlyshyn.fp.entity.Port;
import com.tpavlyshyn.fp.exceptions.DaoException;

import java.util.List;

public interface PortDao extends AbstractDao <Integer, Port>{
    PortsNumberOfRows findAllByLang(String lang, int currentPage, int recordsPerPage) throws DaoException;

    List<Port> findAllByCruiseIds(List<Integer> cruises, String lang) throws DaoException ;
}
