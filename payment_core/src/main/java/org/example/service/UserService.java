package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(String name) {
        userDao.createUser(name);
    }

    public User getUser(String name) {
        return userDao.getUser(name);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void deleteUserByName(String name) {
        userDao.deleteUserByName(name);
    }
}
