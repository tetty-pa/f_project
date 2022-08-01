package com.tpavlyshyn.fp.dao.impl;

import com.tpavlyshyn.fp.Fields;
import com.tpavlyshyn.fp.exceptions.DaoException;
import com.tpavlyshyn.fp.dao.UserDao;
import com.tpavlyshyn.fp.entity.user.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final static Logger log = Logger.getLogger(UserDaoImpl.class);

    private static final String SQL__FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    private static final String SQL__FIND_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String SQL_UPDATE_USER = "UPDATE user SET login=?, password=?, name=?, surname=?" + " WHERE id=?";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE user SET password=?" + " WHERE login=?";
    private static final String SQL__ADD_USER = "INSERT INTO user( id, login, password, name, surname, role_id ) " + "VALUES (default , ?, ?, ?, ?, 1) ";
    private static final String SQL__DELETE_USER = "DELETE FROM user WHERE id=?";
    private static final String SQL__DELETE_USER_BY_LOGIN = "DELETE FROM user WHERE login=?";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM user ";
    private static final String SQL_UPDATE_URL_DOCUMENTS = "UPDATE user SET url_document = ? WHERE id=?;";
    private static final String SQL__SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";


    private final DataSource ds;

    public UserDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        User user = null;

        try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_USER_BY_ID);) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) user = extractUser(rs);
            }
            return Optional.ofNullable(user);
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        boolean result = false;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__DELETE_USER);) {
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public boolean create(User user) throws DaoException {
        boolean result = false;
        try (Connection connection = ds.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(SQL__ADD_USER, Statement.RETURN_GENERATED_KEYS);) {
            setUser(pstmt, user);
            result = pstmt.executeUpdate() > 0;
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException();
        }
        return result;
    }


    @Override
    public boolean update(User user) throws DaoException {
        boolean result;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_USER);) {
            setUser(pstmt, user);
            pstmt.setInt(5, user.getId());
            result = pstmt.executeUpdate() > 0;
            connection.commit();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException();
        }
        return result;
    }

    @Override
    public boolean updatePassword(String password, String login) throws DaoException {
        boolean result;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_PASSWORD);) {
            pstmt.setString(1, password);
            pstmt.setString(2, login);
            result = pstmt.executeUpdate() > 0;
            connection.commit();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException();
        }
        return result;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        User user = null;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__FIND_USER_BY_LOGIN);) {
            pstmt.setString(1, login);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) user = extractUser(rs);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL_FIND_ALL_USERS);) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    users.add(extractUser(rs));
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return users;
    }

    @Override
    public boolean updateUrlDocuments(int userId, String urlDocuments) throws DaoException {
        boolean result;
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_URL_DOCUMENTS);) {
            pstmt.setString(1, urlDocuments);
            pstmt.setInt(2, userId);
            result = pstmt.executeUpdate() > 0;
            connection.commit();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException();
        }
        return result;
    }


    @Override
    public boolean findByLogin(String login) throws DaoException {
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL__SELECT_USER_BY_LOGIN);) {
            pstmt.setString(1, login);

            try (ResultSet resultSet = pstmt.executeQuery();) {
                return resultSet.next();
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DaoException();
        }
    }


    public static User extractUser(ResultSet rs) {
        try {
            User user = new User();
            user.setId(rs.getInt(Fields.ENTITY__ID));
            user.setLogin(rs.getString(Fields.USER__LOGIN));
            user.setPassword(rs.getString(Fields.USER__PASSWORD));
            user.setName(rs.getString(Fields.USER__NAME));
            user.setSurname(rs.getString(Fields.USER__SURNAME));
            user.setUrlDocument(rs.getString(Fields.USER__URL_DOCUMENT));
            user.setRoleId(rs.getInt(Fields.USER__ROLE_ID));
            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void setUser(PreparedStatement pstmt, User user) throws SQLException {
        int k = 1;
        pstmt.setString(k++, user.getLogin());
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getName());
        pstmt.setString(k, user.getSurname());
/*
        pstmt.setString(k, user.getUrlDocument());
*/
    }
}
