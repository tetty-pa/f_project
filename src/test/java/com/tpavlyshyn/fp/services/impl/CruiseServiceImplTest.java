package com.tpavlyshyn.fp.services.impl;


import com.tpavlyshyn.fp.dao.impl.CruiseDaoImpl;
import com.tpavlyshyn.fp.dao.impl.PortDaoImpl;
import com.tpavlyshyn.fp.dao.impl.RequestDaoImpl;

import com.tpavlyshyn.fp.entity.Cruise;
import org.dbunit.*;
import org.dbunit.assertion.DbUnitAssert;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.sql.Date;


public class CruiseServiceImplTest extends TestContainer {

    CruiseServiceImpl cs;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        var dataSource = getDataSource();
        var cdao = new CruiseDaoImpl(dataSource);
        var pdao = new PortDaoImpl(dataSource);
        var rdao = new RequestDaoImpl(dataSource);
        cs = new CruiseServiceImpl(cdao, pdao, rdao);
    }

    @Test
    public void testDemoTestMethod() throws Exception {
        var cn = getDataSource().getConnection();

        var s = cn.createStatement();

        var s2 = cn.createStatement();

        cs.addCruise(new Cruise(1,
                0,
                "",
                "",
                Date.valueOf("2022-08-08"),
                Date.valueOf("2022-08-08"),
                1)
        );


//        var dataset = tester.getConnection().createDataSet(new String[]{"cruise"});
//    To genereta expected results    FlatXmlDataSet.write(dataset, new FileOutputStream("./test.xml"));

      var actualCruise = DefaultColumnFilter.excludedColumnsTable(tester.getConnection().createTable("cruise"),
              new String[] {"CRUISE_PHOTO"}
              );
      var expectedCruise = loader.load("/exp-ds.xml").getTable("cruise");

        Assertion.assertEquals(expectedCruise, actualCruise);
    }






}
