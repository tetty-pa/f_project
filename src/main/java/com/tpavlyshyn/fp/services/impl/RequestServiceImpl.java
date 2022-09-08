package com.tpavlyshyn.fp.services.impl;

import com.tpavlyshyn.fp.dao.CruiseDao;
import com.tpavlyshyn.fp.dao.RequestDao;
import com.tpavlyshyn.fp.exceptions.DaoException;
import com.tpavlyshyn.fp.entity.request.Request;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.RequestService;
import org.apache.log4j.Logger;

import java.util.List;

public class RequestServiceImpl implements RequestService {

    private final static Logger log = Logger.getLogger(RequestServiceImpl.class);

    RequestDao requestDao;
    CruiseDao cruiseDao;

    public RequestServiceImpl(RequestDao requestDao, CruiseDao cruiseDao) {
        this.requestDao = requestDao;
        this.cruiseDao = cruiseDao;
    }

    @Override
    public boolean makeRequest(Request request) throws ServiceException {
        boolean result = false;
        try {
            int freePlaces = cruiseDao.getFreePlaces(request.getCruiseId());
            log.info("Free places " + freePlaces+" on cruise "+ request.getCruiseId() );
            if (request.getAmount() <= freePlaces) {
                result = requestDao.create(request);
            }
            if (result) log.info("Request was made-->" + request);
            else log.info("Request wasn`t made-->" + request);
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return result;
    }


    @Override
    public List<Request> showUsersRequests(int userId, String land) throws ServiceException {
        List<Request> requests;
        try {
            requests = requestDao.findByUserId(userId, land);
            if(!requests.isEmpty()) {
                log.info("Find user`s requests with user`s id " + userId);
                log.info("Found user`s requests-->" + requests);
            }else log.info("Requests weren`t found");
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return requests;
    }

    @Override
    public List<Request> showRequests(String land) throws ServiceException {
        List<Request> requests;
        try {
            requests = requestDao.findAll(land);
            if(!requests.isEmpty()) log.info("Found requests-->"+requests);
            else log.info("Requests weren`t found");
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return requests;
    }

    @Override
    public boolean submitRequest(int requestId) throws ServiceException {
        boolean result;
        try {
            result = requestDao.submitRequest(requestId);
            if (result) log.info("Request with id " + requestId + " was submited");
            else log.info("Request with id " + requestId + " wasn`t submited");
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return result;
    }

    @Override
    public int calculateTotalPrice(int requestId) throws ServiceException {
        int totalPrice;
        try {
            totalPrice = requestDao.calculateTotalPrice(requestId);
            log.info("Calculating total price with request id =" + requestId);
            log.info("Total price-->"+ totalPrice);
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return totalPrice;
    }

    @Override
    public boolean pay(int requestId) throws ServiceException {
        boolean result;
        try {
            result = requestDao.pay(requestId);
            log.info("Paying for request with request id =" + requestId);

        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return result;
    }
}
