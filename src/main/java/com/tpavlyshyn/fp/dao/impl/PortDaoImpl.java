package com.tpavlyshyn.fp.dao.impl;

import com.tpavlyshyn.fp.Fields;
import com.tpavlyshyn.fp.dao.PortDao;
import com.tpavlyshyn.fp.dto.PortsNumberOfRows;
import com.tpavlyshyn.fp.entity.Port;
import com.tpavlyshyn.fp.exceptions.DaoException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PortDaoImpl implements PortDao {
    private final static Logger log = Logger.getLogger(PortDaoImpl.class);


    private static final String SQL__FIND_ALL_PORTS = "SELECT *,  COUNT(*) OVER() as number_of_rows " +
            "FROM port " +
            "WHERE lang = ?" +
            " ORDER BY city ASC  LIMIT ?, ?";
    private static final String SQL__FIND_CRUISES_PORTS1 =
            "SELECT cruise_id, port.id, lang, city, country\n" +
                    "FROM port\n" +
                    "         INNER JOIN cruise_has_port chp on port.id = chp.port_id\n" +
                    "WHERE lang = ?\n" +
                    "  AND cruise_id IN ";

    private final DataSource ds;

    public PortDaoImpl(DataSource ds) {
        this.ds = ds;
    }





    @Override
    public List<Port> findAllByCruiseIds(List<Integer> cruises, String lang) throws DaoException {
        List<Port> portList = new ArrayList<>();
        String query = SQL__FIND_CRUISES_PORTS1 + builtPstmt(cruises);
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, lang);
            for (int i = 2; i < cruises.size()+2; i++) {
                pstmt.setInt(i, cruises.get(i-2));
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Port port = PortDaoImpl.extractPort(rs);
                    port.setCruiseId(rs.getInt("cruise_id"));
                    portList.add(port);
                }
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return portList;
    }

    private String builtPstmt(List<Integer> list) {
        String s = "(";

        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                s = s  +"?" + ",";
            } else s = s + "?" + ")";
        }
        return s;
    }

    @Override
    public PortsNumberOfRows findAllByLang(String lang, int currentPage,
                                           int recordsPerPage) throws DaoException {
        List<Port> portList = new ArrayList<>();
        PortsNumberOfRows portsNumberOfRows = new PortsNumberOfRows();
        int numberOfRows = 0;
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL__FIND_ALL_PORTS)) {
            pstmt.setString(1, lang);
            pstmt.setInt(2, currentPage);
            pstmt.setInt(3, recordsPerPage);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Port port = PortDaoImpl.extractPort(rs);
                    portList.add(port);
                    numberOfRows = Integer.parseInt(rs.getString("number_of_rows"));
                }
            }
            portsNumberOfRows.setNumberOfRows(numberOfRows);
            portsNumberOfRows.setPorts(portList);
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return portsNumberOfRows;
    }

    @Override
    public Optional<Port> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Port entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Port entity) throws DaoException {
        return false;
    }

    public static Port extractPort(ResultSet rs) {
        Port port = new Port();
        try {
            port.setId(Integer.parseInt(rs.getString(Fields.ENTITY__ID)));
            port.setCity(rs.getString(Fields.PORT_CITY));
            port.setCountry(rs.getString(Fields.PORT_COUNTRY));

            return port;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
