package com.tpavlyshyn.fp.dao.impl;

import com.tpavlyshyn.fp.Fields;

import com.tpavlyshyn.fp.exceptions.DaoException;
import com.tpavlyshyn.fp.dao.LinerDao;
import com.tpavlyshyn.fp.entity.Liner;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LinerDaoImpl implements LinerDao {
    private final static Logger log = Logger.getLogger(LinerDaoImpl.class);


    private static final String SQL__FIND_LINER_BY_ID =
            "SELECT * FROM liner WHERE id=?";
    private static final String SQL__FIND_ALL=
            "SELECT * FROM liner";

    private final DataSource ds;

    public LinerDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Liner> findById(Integer id) throws DaoException {
        Liner liner = null;

        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_LINER_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    liner = extractLiner(rs);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return Optional.ofNullable(liner);
    }

    @Override
    public List<Liner> findAll() throws DaoException {
        List<Liner> linerList = new ArrayList<>();
        try (Connection con = ds.getConnection();
             Statement pstmt = con.createStatement()) {
            try(ResultSet rs = pstmt.executeQuery(SQL__FIND_ALL)) {
                while (rs.next()) {
                    Liner liner = extractLiner(rs);
                    linerList.add(liner);
                }
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return linerList;
    }

    public static Liner extractLiner(ResultSet rs) {
        try {
            Liner liner = new Liner();
            liner.setId(rs.getInt(Fields.ENTITY__ID));
            liner.setLinerName(rs.getString(Fields.LINER__NAME));
            liner.setLinerPhoto(rs.getString(Fields.LINER__PHOTO));
            liner.setPassengerCapacity(rs.getInt(Fields.LINER__PASSENGER_CAPACITY));
            liner.setCompanyId(rs.getInt(Fields.LINER__COMPANY_ID));
            return liner;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Liner entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Liner entity) throws DaoException {
        return false;
    }
}
