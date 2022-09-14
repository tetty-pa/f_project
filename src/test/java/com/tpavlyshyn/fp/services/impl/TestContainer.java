package com.tpavlyshyn.fp.services.impl;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Ignore;

import javax.sql.DataSource;

public abstract class TestContainer extends DataSourceBasedDBTestCase {

    DataFileLoader loader = new FlatXmlDataFileLoader();

    IDatabaseTester tester;

    public void setUp() throws Exception {
        tester = newDatabaseTester();
        IDataSet ds = loader.load("/data.xml");
        tester.setDataSet(ds);
        tester.onSetup();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("data.xml"));
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.CLEAN_INSERT;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    DataSource ds;

    protected DataSource getDataSource() {

        if (ds == null) {
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL(
                    "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;NON_KEYWORDS=USER;init=runscript from 'classpath:schema.sql'");
            dataSource.setUser("sa");
            dataSource.setPassword("sa");

            return dataSource;
        }
        return ds;
    }
}
