package com.furthurprogramming.assignment2.model;

import com.furthurprogramming.assignment2.util.DBUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    @BeforeAll
    static void InitAccountDAOTest()
    {
        DBUtil.connect();
        AccountDAO.deleteAllAccounts();
    }

    @Test
    void TestAccountDAO()
    {
        var un = "testCreateAccount";
        var pw = un;

        assertTrue(AccountDAO.createAccount(un, pw));
        assertTrue(AccountDAO.isAccountExists(un, pw));

        var acc = AccountDAO.searchAccount(un);

        assertNotNull(acc);
        assertEquals(acc.getUsername(), un);
        assertEquals(acc.getPassword(), pw);
    }
}