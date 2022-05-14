package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.UsersKotkovets;

import java.util.List;

public interface UsersService {
    public boolean saveUsers(UsersKotkovets usersKotkovets);
    public boolean recover(UsersKotkovets usersKotkovets);
    public List<UsersKotkovets> getAllUsers();
    public Object login(UsersKotkovets usersKotkovets);
    public void deleteUser(Integer userId);
    public void roleHandler(Integer id);
}
