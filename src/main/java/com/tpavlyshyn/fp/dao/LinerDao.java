package com.tpavlyshyn.fp.dao;

import com.tpavlyshyn.fp.entity.Liner;
import com.tpavlyshyn.fp.exceptions.DaoException;

import java.util.List;

public interface LinerDao extends AbstractDao<Integer, Liner> {
    List<Liner> findAll() throws DaoException;

}
