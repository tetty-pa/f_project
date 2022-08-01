package com.tpavlyshyn.fp.dao.factory;

import com.tpavlyshyn.fp.dao.impl.*;


/*public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;

    public abstract UserDaoImpl createUserDao();
    public abstract LinerDaoImpl createLinerDao();
    public abstract RequestDaoImpl createRequestDao();
    public abstract CruiseDaoImpl createCruiseDao();
    public abstract PortDaoImpl createPortDao();


    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}*/
