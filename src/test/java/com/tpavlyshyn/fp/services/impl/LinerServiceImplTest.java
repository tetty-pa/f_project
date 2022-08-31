package com.tpavlyshyn.fp.services.impl;

import com.tpavlyshyn.fp.dao.CruiseDao;
import com.tpavlyshyn.fp.dao.LinerDao;
import com.tpavlyshyn.fp.dao.RequestDao;
import com.tpavlyshyn.fp.dao.impl.CruiseDaoImpl;
import com.tpavlyshyn.fp.dao.impl.LinerDaoImpl;
import com.tpavlyshyn.fp.dao.impl.RequestDaoImpl;
import com.tpavlyshyn.fp.entity.Liner;
import com.tpavlyshyn.fp.entity.request.Request;
import com.tpavlyshyn.fp.entity.request.Status;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.LinerService;
import org.dbunit.dataset.DataSetException;
import org.junit.Before;

import javax.sql.DataSource;
import java.util.List;

public class LinerServiceImplTest extends TestContainer {

    LinerService ls;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        DataSource dataSource = getDataSource();

        LinerDao linerDao = new LinerDaoImpl(dataSource);
        ls = new LinerServiceImpl(linerDao);
    }

    public void testShowAllLiners() throws ServiceException, DataSetException {
        List<Liner> liners = ls.showAllLiners();
        var expectedCruise = loader.load("/exp-ds.xml").getTable("liner");
        assertEquals(expectedCruise.getRowCount(), liners.size());
    }

}
