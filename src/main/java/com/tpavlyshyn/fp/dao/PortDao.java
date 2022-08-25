package com.tpavlyshyn.fp.dao;

import com.tpavlyshyn.fp.dto.PortsNumberOfRows;
import com.tpavlyshyn.fp.entity.Port;
import com.tpavlyshyn.fp.exceptions.DaoException;

import java.util.List;

public interface PortDao extends AbstractDao <Integer, Port>{
    List<Port> findAllByCruiseId(int cruiseId, String lang) throws DaoException;
    PortsNumberOfRows findAllByLang(String lang, int currentPage, int recordsPerPage) throws DaoException;
}
