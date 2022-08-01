package com.tpavlyshyn.fp.services;

import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean signUp(User user) throws ServiceException;

    public Optional<User> signIn(String login, String password) throws ServiceException;

    public boolean setDocuments(int userId, String urlDocuments) throws ServiceException;

    public boolean setNewPassword(String password, String login) throws ServiceException;

    public List<User> showUsers() throws ServiceException;

    public boolean editProfile(User user) throws ServiceException;

    public boolean checkUserLoginForUnique(String login);

}
