package com.tpavlyshyn.fp.services.impl;

import com.tpavlyshyn.fp.dao.LinerDao;
import com.tpavlyshyn.fp.entity.Liner;
import com.tpavlyshyn.fp.exceptions.DaoException;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.LinerService;

import java.util.List;

public class LinerServiceImpl implements LinerService {
    LinerDao linerDao;

    public LinerServiceImpl(LinerDao linerDao) {
        this.linerDao = linerDao;
    }

    @Override
    public List<Liner> showAllLiners() throws ServiceException {
        List<Liner> linerList = null;
        try {
            linerList = linerDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
        return linerList;
    }
}
