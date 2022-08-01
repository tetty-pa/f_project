package com.tpavlyshyn.fp.services;

import com.tpavlyshyn.fp.entity.request.Request;
import com.tpavlyshyn.fp.exceptions.ServiceException;

import java.util.List;

public interface RequestService {

    boolean makeRequest(Request request) throws ServiceException;

    List<Request> showUsersRequests(int userId, String land) throws ServiceException;

    List<Request> showRequests(String land) throws ServiceException;

    boolean submitRequest(int requestId) throws ServiceException;

    int calculateTotalPrice(int requestId) throws ServiceException;

    boolean pay(int requestId) throws ServiceException;
}
