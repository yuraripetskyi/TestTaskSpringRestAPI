package com.example.testtask.utils;

import com.example.testtask.dao.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class DataLoaderTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void run() {
        assertEquals(userRepository.findAll().size(), 10);
    }
}