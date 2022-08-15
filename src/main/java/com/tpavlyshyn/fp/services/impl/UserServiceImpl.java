package com.tpavlyshyn.fp.services.impl;

import com.tpavlyshyn.fp.dao.UserDao;
import com.tpavlyshyn.fp.exceptions.DaoException;
import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.UserService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final static Logger log = Logger.getLogger(UserServiceImpl.class);
    /*
        DaoFactory daoFactory;
        UserDaoImpl userDao;

        public UserService() {
            daoFactory = DaoFactory.getInstance();
            userDao = daoFactory.createUserDao();
        }*/
    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean signUp(User user) throws ServiceException {
        boolean result = false;
        try {
            result = userDao.create(user);
            if (result) log.info("User was created-->" + user);
            else log.info("User wasn`t created-->" + user);
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return result;
    }

    @Override
    public Optional<User> signIn(String login, String password) throws ServiceException {
        try {
            Optional<User> user = userDao.findUserByLogin(login);
            if (user.isPresent() && password.equals(user.get().getPassword())) {
                log.info("User is signed up --> " + user);
                return user;
            } else log.info("User with login " + login + " and password " + password + " is not found");
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return Optional.empty();
    }

    @Override
    public boolean setDocuments(int userId, String urlDocuments) throws ServiceException {
        boolean result;
        try {
            result = userDao.updateUrlDocuments(userId, urlDocuments);
            if (result) log.info("Documents " + urlDocuments + " were setted to user with id " + userId);
            else log.info("Documents " + urlDocuments + " were NOT setted to user with id " + userId);

        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return result;
    }

    @Override
    public boolean setNewPassword(String password, String login) throws ServiceException {
        boolean result;
        try {
            result = userDao.updatePassword(password, login);
            if (result) log.info("User with login " + login + " changed password");
            else log.info("User with login " + login + "did NOT change password");

        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return result;
    }

    @Override
    public List<User> showUsers() throws ServiceException {
        List<User> users = new ArrayList<>();
        try {
            users = userDao.findAll();
            if (!users.isEmpty()) log.info("Found users-->" + users);
            else log.info("Users wasn`t found");
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return users;
    }

    @Override
    public List<User> showUsersByCruiseId(int cruiseId) throws ServiceException {
        List<User> users = new ArrayList<>();
        try {
            users = userDao.findByCruiseId(cruiseId);
            if (!users.isEmpty()) log.info("Found users-->" + users);
            else log.info("Users wasn`t found");
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return users;
    }


    @Override
    public boolean editProfile(User user) throws ServiceException {
        boolean result = false;
        try {
            result = userDao.update(user);
            if (result) log.info("User was updated-->" + user);
            else log.info("User wasn`t updated-->" + user);
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
            throw new ServiceException(ex);
        }
        return result;
    }

    @Override
    public boolean checkUserLoginForUnique(String login) {
        try {

            return userDao.findByLogin(login);
        } catch (DaoException ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }
}
