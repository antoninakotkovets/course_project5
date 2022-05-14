package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.UsersKotkovets;
import com.ajuncodes.coursework.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public boolean saveUsers(UsersKotkovets usersKotkovets) {
        List<UsersKotkovets> found = usersRepository.findAll();
        for (int i = 0; i < found.size(); i++) {
            if (usersKotkovets.getLogin().equals(found.get(i).getLogin())) {
                return false;
            }
        }
        usersKotkovets.setRole("user");
        usersRepository.save(usersKotkovets);
        return true;
    }

    @Override
    public boolean recover(UsersKotkovets usersKotkovets) {
        UsersKotkovets user = usersRepository.findByLogin(usersKotkovets.getLogin());
        if (user.getSecretCode().equals(usersKotkovets.getSecretCode())) {
            usersKotkovets.setId(user.getId());
            usersKotkovets.setRole(user.getRole());
            usersRepository.save(usersKotkovets);
            return true;
        }

        return false;
    }

    @Override
    public List<UsersKotkovets> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Object login(UsersKotkovets usersKotkovets) {
        List<UsersKotkovets> found = usersRepository.findAll();
        for (int i = 0; i < found.size(); i++) {
            if (found.get(i).getLogin().equals(usersKotkovets.getLogin()) && found.get(i).getPassword().equals(usersKotkovets.getPassword())) {
                return found.get(i);
            }
        }

        return false;
    }

    @Override
    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public void roleHandler(Integer id) {
        UsersKotkovets user = usersRepository.getById(id);
        user.setRole("admin");
        usersRepository.save(user);
    }
}
