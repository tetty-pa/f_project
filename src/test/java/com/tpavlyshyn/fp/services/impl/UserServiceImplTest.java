package com.tpavlyshyn.fp.services.impl;

import com.tpavlyshyn.fp.dao.UserDao;
import com.tpavlyshyn.fp.dao.impl.UserDaoImpl;
import com.tpavlyshyn.fp.entity.user.User;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.UserService;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
import org.junit.Before;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UserServiceImplTest extends TestContainer {
    UserService us;
    User user;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        String login = "roger.pav@ukr.net";
        String password = "rogerpav";
        String name = "Roger";
        String surname = "Pav";
        int roleId = 1;
        user = new User(login, password, name, surname, null, roleId);

        DataSource dataSource = getDataSource();
        UserDao userDao = new UserDaoImpl(dataSource);
        us = new UserServiceImpl(userDao);
    }

    public void testSignUp() throws ServiceException {
        user.setId(3);
        boolean result = us.signUp(user);
        assertTrue(result);
    }

    public void testSignIn() throws ServiceException {
        Optional<User> user = us.signIn("lina.nerv@ukr.net", "linanerv");
        assertNotNull(user);
    }

    public void testSignInBadInput() throws ServiceException {
        Optional<User> user = us.signIn("lina222.nerv@ukr.net", "linanerv");
        assertEquals(Optional.empty(), user);
    }

    public void testSetDocuments() throws ServiceException {
        boolean result = us.setDocuments(2, "documents");
        assertTrue(result);

    }

    public void testSetNewPassword() throws ServiceException {
        boolean result = us.setNewPassword("goodPass", "lina.nerv@ukr.net");
        assertTrue(result);
    }

    public void testSetNewPasswordBadInput() throws ServiceException {
        boolean result = us.setNewPassword("1", "lina222.nerv@ukr.net");
        assertFalse(result);
    }

    public void testShowUsers() throws ServiceException, DataSetException {
        List<User> users = us.showUsers();
        ITable expectedUsers = loader.load("/exp-ds.xml").getTable("user");
        assertEquals(expectedUsers.getRowCount(), users.size());
    }

    public void testShowUsersByCruiseId() throws ServiceException, DataSetException {
        List<User> users = us.showUsersByCruiseId(2);
        ITable expectedUsers = loader.load("/exp-ds.xml").getTable("user1");
        assertEquals(expectedUsers.getRowCount(), users.size());
    }

   public void testEditProfile() throws ServiceException, DataSetException {
     user.setId(2);
        boolean result = us.editProfile(user);
        assertTrue(result);
        ITable expectedUsers = loader.load("/exp-ds.xml").getTable("user_updated");
        assertEquals(expectedUsers.getValue(0, "login"), "roger.pav@gmail.com");
    }

    public void testCheckLoginForUnique() {
        boolean result = us.checkUserLoginForUnique(user.getLogin());
        assertTrue(result);
    }

    public void testCheckLoginIsNotUnique() {
        boolean result = us.checkUserLoginForUnique("lina.nerv@ukr.net");
        assertFalse(result);
    }
}
