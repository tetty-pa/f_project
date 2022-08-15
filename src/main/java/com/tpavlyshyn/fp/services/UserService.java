package com.tpavlyshyn.fp.services;

import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean signUp(User user) throws ServiceException;

    Optional<User> signIn(String login, String password) throws ServiceException;

    boolean setDocuments(int userId, String urlDocuments) throws ServiceException;

    boolean setNewPassword(String password, String login) throws ServiceException;

    List<User> showUsers() throws ServiceException;

    List<User> showUsersByCruiseId(int cruiseId) throws ServiceException;

    boolean editProfile(User user) throws ServiceException;

    boolean checkUserLoginForUnique(String login);

}
