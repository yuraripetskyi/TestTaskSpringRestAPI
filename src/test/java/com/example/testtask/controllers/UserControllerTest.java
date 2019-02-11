package com.example.testtask.controllers;

import com.example.testtask.models.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void getUsersOlderThan() {
        List<User> usersOlderThan40 = userController.getUsersOlderThan(40);
        assertFalse(
        usersOlderThan40.stream()
                .anyMatch(user -> user.getAge()< 40)
        );
    }
}