package com.example.testtask.controllers;


import com.example.testtask.dao.UserRepository;
import com.example.testtask.models.User;
import com.example.testtask.models.enums.Color;
import com.example.testtask.models.requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody AddUserRequest addUserRequest) {
        User user = new User();
        user.setName(addUserRequest.getName());
        user.setAge(addUserRequest.getAge());
        userRepository.save(user);
    }

    @RequestMapping(value = "/older", method = RequestMethod.GET)
    public List<User> getUsersOlderThan(@RequestParam int age){

        return userRepository.findAll()
                .stream()
                .filter(user -> user.getAge()>age)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/color", method = RequestMethod.GET)
    public List<User> usersWithColor(@RequestParam String color){
        Color color1 = Color.valueOf(color);
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getArticles()
                        .stream()
                        .anyMatch(article -> article.getColor().equals(color1)))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/unique", method = RequestMethod.GET)
    public List<User> usersWithUniqeNameAndMoreThanTwoArticles(){
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getArticles().size() >= 3)
                .filter(distinctByKey(User::getName))
                .collect(Collectors.toList());
    }

     private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
