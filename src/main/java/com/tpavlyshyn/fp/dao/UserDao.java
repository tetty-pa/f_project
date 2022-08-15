package com.tpavlyshyn.fp.dao;

import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends AbstractDao<Integer, User> {

    Optional<User> findUserByLogin(String login) throws DaoException;

    List<User> findByCruiseId(Integer cruiseId) throws DaoException ;

    boolean updatePassword(String password, String login) throws DaoException;

    List<User> findAll() throws DaoException;

    boolean updateUrlDocuments(int userId, String urlDocuments) throws DaoException;

    boolean findByLogin(String login) throws DaoException;

}
