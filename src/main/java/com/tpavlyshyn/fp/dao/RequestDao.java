package com.tpavlyshyn.fp.dao;

import com.tpavlyshyn.fp.entity.request.Request;
import com.tpavlyshyn.fp.exceptions.DaoException;

import java.util.List;

public interface RequestDao extends AbstractDao<Integer, Request>{
    List<Request> findAll(String lang) throws DaoException;
    List<Request> findByUserId(int id, String lang) throws DaoException;
    boolean submitRequest(int requestId) throws DaoException;
    boolean pay(int requestId) throws DaoException;
    int calculateTotalPrice(int requestId) throws DaoException;
    boolean findRequestByCruiseId(int id) throws DaoException;

    }
