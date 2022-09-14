package com.tpavlyshyn.fp.services.impl;

import com.tpavlyshyn.fp.dao.CruiseDao;
import com.tpavlyshyn.fp.dao.RequestDao;
import com.tpavlyshyn.fp.dao.impl.CruiseDaoImpl;
import com.tpavlyshyn.fp.dao.impl.RequestDaoImpl;
import com.tpavlyshyn.fp.entity.request.Request;
import com.tpavlyshyn.fp.entity.request.Status;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.RequestService;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;


public class RequestServiceImplTest extends TestContainer {
    RequestService rs;
    Request request;
    private final String LANGUAGE = "en";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        int amount = 1;
        int userId = 2;
        int cruiseId = 2;
        Status status = Status.OPENED;
        request = new Request(amount, userId, cruiseId, status);
        DataSource dataSource = getDataSource();

        CruiseDao cruiseDao = new CruiseDaoImpl(dataSource);
        RequestDao requestDao = new RequestDaoImpl(dataSource);
        rs = new RequestServiceImpl(requestDao, cruiseDao);
    }

    public void testMakeRequest() throws ServiceException {
        boolean result = rs.makeRequest(request);
        assertTrue(result);
    }

  /*  public void testShowUsersRequests() throws ServiceException, DataSetException {
        List<Request> requests = rs.showUsersRequests(2, LANGUAGE);
        var expectedCruise = loader.load("/exp-ds.xml").getTable("request_users");
        assertEquals(expectedCruise.getRowCount(), requests.size());
    }

    public void testShowRequests() throws ServiceException, DataSetException {
        List<Request> requests = rs.showRequests(LANGUAGE);
        var expectedCruise = loader.load("/exp-ds.xml").getTable("request");
        assertEquals(expectedCruise.getRowCount(), requests.size());
    }
*/
    public void testSubmitRequest() throws ServiceException {
        boolean result = rs.submitRequest(4);
        assertTrue(result);
    }

    public void testCalculateTotalPrice() throws ServiceException {
        int result = rs.calculateTotalPrice(4);
        assertEquals(result, 21);
    }

    public void testPay() throws ServiceException {
        boolean result = rs.pay(4);
        assertTrue(result);
    }
}
