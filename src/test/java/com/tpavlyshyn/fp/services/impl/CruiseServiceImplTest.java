package com.tpavlyshyn.fp.services.impl;


import com.tpavlyshyn.fp.dao.impl.CruiseDaoImpl;
import com.tpavlyshyn.fp.dao.impl.PortDaoImpl;
import com.tpavlyshyn.fp.dao.impl.RequestDaoImpl;

import com.tpavlyshyn.fp.dto.CruisePort;
import com.tpavlyshyn.fp.dto.PortsNumberOfRows;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.entity.TranslationCruise;
import com.tpavlyshyn.fp.exceptions.ServiceException;

import org.junit.Before;
import org.junit.Ignore;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class CruiseServiceImplTest extends TestContainer {

    CruiseServiceImpl cs;
    private final String LANGUAGE = "en";
    Cruise cruise;
    TranslationCruise translationCruise1;
    TranslationCruise translationCruise2;
    List<CruisePort> ports;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        cruise = new Cruise();
        cruise.setId(1);
        cruise.setNumberOfPorts(5);
        cruise.setPrice(569);
        cruise.setStartDate(Date.valueOf("2022-05-12"));
        cruise.setEndDate(Date.valueOf("2022-05-19"));
        cruise.setLinerId(1);
        translationCruise1 = new TranslationCruise(1, "en", "name", "description");
        translationCruise2 = new TranslationCruise(1, "ua", "name2", "description2");

        ports = new ArrayList<>();
        ports.add(new CruisePort(1, 1, Timestamp.valueOf("2022-05-12 12:00:00")));

        var dataSource = getDataSource();
        var cruiseDao = new CruiseDaoImpl(dataSource);
        var portDao = new PortDaoImpl(dataSource);
        var requestDao = new RequestDaoImpl(dataSource);
        cs = new CruiseServiceImpl(cruiseDao, portDao, requestDao);
    }



    public void testDeleteCruise() throws ServiceException {
        boolean result = cs.deleteCruise(2);
        assertTrue(result);
    }


    public void testShowCruiseInfo() throws ServiceException {
        Cruise cruise = cs.showCruiseInfo(2, LANGUAGE);
        assertNotNull(cruise);
    }

    public void testShowCruises() throws Exception {
        List<Cruise> cruises = cs.showCruises(LANGUAGE);
        var expectedCruise = loader.load("/exp-ds.xml").getTable("cruise");
        assertEquals(expectedCruise.getRowCount(), cruises.size());
    }

    public void testShowPorts() throws Exception {
        PortsNumberOfRows portsNumberOfRows = cs.showPorts(LANGUAGE, 1, 2);
        var expectedCruise = loader.load("/exp-ds.xml").getTable("port");
        assertEquals(portsNumberOfRows.getPorts().size(), expectedCruise.getRowCount());
    }

    public void testCheckCruiseHasRequest() {
        boolean result = cs.checkCruiseHasRequests(2);
        assertTrue(result);
    }

    public void testCheckCruiseHasRequestReturnFalse() {
        boolean result = cs.checkCruiseHasRequests(1);
        assertFalse(result);
    }

    public void testAddCruiseWithTranslations() throws ServiceException {
        boolean result = cs.addCruiseWithTranslations(cruise, translationCruise1, translationCruise2, ports);
        assertTrue(result);
    }
}
