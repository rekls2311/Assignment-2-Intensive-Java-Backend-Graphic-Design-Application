package com.furthurprogramming.assignment2.model;

import com.furthurprogramming.assignment2.util.DBUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @BeforeAll
    static void InitUserDAOTest(){
        DBUtil.connect();
        UserDAO.deleteAllUsers();
        AccountDAO.deleteAllAccounts();
    }

    @Test
    void searchUser() {
        assertNull(UserDAO.searchUser("39"));

        var un = "TestSearchUser";
        var pw = un;

        UserDAO.createUser(un, pw, "first", "last");

        var user = UserDAO.searchUser(un);
        assertNotNull(user);
        assertEquals(user.getUsername(), un);
        assertEquals(user.getLastName(), "last");
        assertEquals(user.getFirstName(), "first");
    }
}