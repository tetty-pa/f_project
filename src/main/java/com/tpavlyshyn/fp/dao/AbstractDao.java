package com.tpavlyshyn.fp.dao;

import com.tpavlyshyn.fp.entity.Entity;
import com.tpavlyshyn.fp.exceptions.DaoException;

import java.util.Optional;

public interface AbstractDao <KEY, ENTITY extends Entity> {

        Optional<ENTITY> findById(KEY id) throws DaoException;

        boolean delete(KEY id) throws DaoException;

        boolean create(ENTITY entity) throws DaoException;

        boolean update(ENTITY entity) throws DaoException;


    }

