package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.UsersKotkovets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UsersServiceImplTest {

    @Autowired
    private UsersServiceImpl service;

    @Test
    public void testUserLoginAsAdmin() {
        UsersKotkovets user = new UsersKotkovets();
        user.setLogin("alina");
        user.setPassword("2222");
        UsersKotkovets loginPerson = (UsersKotkovets) service.login(user);
        Assert.assertEquals(loginPerson.getRole(),"admin");
    }

    @Test
    public void testLoginAsUser() {
        UsersKotkovets user = new UsersKotkovets();
        user.setLogin("antonina");
        user.setPassword("1234");
        UsersKotkovets loginPerson = (UsersKotkovets) service.login(user);
        Assert.assertEquals(loginPerson.getRole(),"user");
    }

    @Test
    @Rollback
    public void testRecoverPasswordWhenCorrectSecretCode() {
        UsersKotkovets user = new UsersKotkovets();
        user.setLogin("nina");
        user.setSecretCode("JvNwrc4o9jRP13cV1uWHj");
        boolean recover = service.recover(user);
        Assert.assertTrue(recover);
    }

    @Test
    public void testRecoverPasswordWhenIncorrectSecrectCode() {
        UsersKotkovets user = new UsersKotkovets();
        user.setLogin("nina");
        user.setSecretCode("1");
        boolean recover = service.recover(user);
        Assert.assertFalse(recover);
    }

    @Test
    public void testCreateDuplicatedUserInSystem() {
        UsersKotkovets user = new UsersKotkovets();
        user.setLogin("admin");
        boolean isSaved = service.saveUsers(user);
        Assert.assertFalse(isSaved);
    }

    @Test
    @Rollback
    public void testCreateNewUserInSystem() {
        UsersKotkovets user = new UsersKotkovets();
        user.setLogin("totko");
        boolean isSaved = service.saveUsers(user);
        Assert.assertTrue(isSaved);
    }

    @Test
    public void testAdminFindAllUsers() {
        List<UsersKotkovets> allUsers = service.getAllUsers();
        Assert.assertFalse(allUsers.isEmpty());
    }
}