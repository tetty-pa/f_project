package com.tpavlyshyn.fp.dao.impl;

import com.tpavlyshyn.fp.exceptions.DaoException;
import com.tpavlyshyn.fp.dao.RequestDao;
import com.tpavlyshyn.fp.Fields;
import com.tpavlyshyn.fp.entity.Cruise;
import com.tpavlyshyn.fp.entity.request.Request;
import com.tpavlyshyn.fp.entity.request.Status;
import com.tpavlyshyn.fp.entity.user.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestDaoImpl implements RequestDao {
    private final static Logger log = Logger.getLogger(RequestDaoImpl.class);

    private static final String SQL__FIND_REQUEST_BY_ID =
            "SELECT * FROM request WHERE id = ?";

    private static final String SQL__CREATE_REQUEST =
            "INSERT INTO request(id, cruise_id, user_id, amount, status) VALUES(default, ?, ?, ?, 'opened')";

    private static final String SQL__DELETE_REQUEST =
            "DELETE FROM request WHERE id=?";

    private static final String SQL__FIND_ALL_REQUEST =
            "SELECT * FROM request" +
                    " INNER JOIN cruise c on request.cruise_id = c.id" +
                    " INNER JOIN user u on request.user_id = u.id" +
                    " INNER JOIN translation_cruise t on c.id = t.cruise_id" +
                    " WHERE lang = ?";


    private static final String SQL__FIND_USERS_REQUESTS =
            " SELECT * FROM request" +
                    " INNER JOIN cruise c ON request.cruise_id = c.id" +
                    " INNER JOIN translation_cruise t on c.id = t.cruise_id" +
                    " WHERE user_id=? AND lang =?";


    private static final String SQL__SUBMIT_REQUEST =
            "UPDATE request SET status = 'CONFIRMED' WHERE id=?;";

    private static final String SQL__PAY =
            "UPDATE request SET status = 'PAID' WHERE id=?;";

    private static final String SQL__CALCULATE_PRICE_BY_ID =
            "SELECT (r.amount * price) AS total_price FROM cruise " +
                    "INNER JOIN request r on cruise.id = r.cruise_id " +
                    "WHERE r.id=?";
    public static final String SQL__FIND_REQUEST_BY_CRUISE_ID =
            "SELECT * FROM request WHERE cruise_id=?";


    private final DataSource ds;

    public RequestDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Request> findById(Integer id) throws DaoException {
        Request request = null;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_REQUEST_BY_ID);) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next())
                    request = extractRequest(rs);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return Optional.ofNullable(request);
    }


    @Override
    public boolean delete(Integer id) throws DaoException {
        return deleteById(id, ds, SQL__DELETE_REQUEST, log);
    }

    static boolean deleteById(Integer id, DataSource ds, String sql, Logger log) throws DaoException {
        boolean result ;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate() > 0;
            connection.commit();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public boolean create(Request request) throws DaoException {
        boolean result = false;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__CREATE_REQUEST, Statement.RETURN_GENERATED_KEYS);) {
            int k = 1;
            pstmt.setInt(k++, request.getCruiseId());
            pstmt.setInt(k++, request.getUserId());
            pstmt.setInt(k, request.getAmount());

            result = pstmt.executeUpdate() > 0;
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    request.setId(generatedKeys.getInt(1));
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public boolean update(Request entity) throws DaoException {
        return false;
    }

    @Override
    public List<Request> findAll(String lang) throws DaoException {
        List<Request> requestList = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_ALL_REQUEST)) {
            pstmt.setString(1, lang);

            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    Cruise cruise = CruiseDaoImpl.extractCruise(rs);
                    User user = UserDaoImpl.extractUser(rs);
                    Request request = RequestDaoImpl.extractRequest(rs);
                    request.setCruise(cruise);
                    request.setUser(user);
                    requestList.add(request);
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return requestList;
    }


    @Override
    public List<Request> findByUserId(int id, String lang) throws DaoException {
        List<Request> requestList = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_USERS_REQUESTS);) {
            pstmt.setInt(1, id);
            pstmt.setString(2, lang);
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    Request request = extractRequest(rs);
                    Cruise cruise = CruiseDaoImpl.extractCruise(rs);
                    request.setCruise(cruise);
                    requestList.add(request);
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return requestList;
    }

    @Override
    public boolean submitRequest(int requestId) throws DaoException {
        return updateStatus(requestId, SQL__SUBMIT_REQUEST);
    }

    @Override
    public boolean pay(int requestId) throws DaoException {
        return updateStatus(requestId, SQL__PAY);
    }

    private boolean updateStatus(int requestId, String sql) throws DaoException {
        boolean result = false;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setInt(1, requestId);
            result = pstmt.executeUpdate() > 0;
            connection.commit();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException();
        }
        return result;
    }

    @Override
    public int calculateTotalPrice(int requestId) throws DaoException {
        int totalPrice = 0;

        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__CALCULATE_PRICE_BY_ID);) {
            pstmt.setInt(1, requestId);
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next())
                    totalPrice = rs.getInt("total_price");
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return totalPrice;
    }

    @Override
    public boolean findRequestByCruiseId(int cruiseId) throws DaoException {
        try (Connection connection = ds.getConnection();
            PreparedStatement pstmt =  connection.prepareStatement(SQL__FIND_REQUEST_BY_CRUISE_ID);) {
            pstmt.setInt(1, cruiseId);
            try(ResultSet resultSet = pstmt.executeQuery();) {
                return resultSet.next();
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException();
        }
    }

    public static Request extractRequest(ResultSet rs) {
        try {
            Request request = new Request();
            request.setId(rs.getInt(Fields.ENTITY__ID));
            request.setAmount(rs.getInt(Fields.REQUEST__AMOUNT));
            request.setCruiseId(rs.getInt(Fields.REQUEST__CRUISE_ID));
            String requestValue = rs.getString(Fields.REQUEST__STATUS);
            request.setStatus(Status.valueOf(requestValue));
            request.setUserId(rs.getInt(Fields.REQUEST__USER_ID));
            return request;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}

