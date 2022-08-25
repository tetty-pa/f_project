package com.tpavlyshyn.fp.services;

import com.tpavlyshyn.fp.entity.Liner;
import com.tpavlyshyn.fp.entity.request.Request;
import com.tpavlyshyn.fp.exceptions.ServiceException;

import java.util.List;

public interface LinerService {
    List<Liner> showAllLiners() throws ServiceException;

}
