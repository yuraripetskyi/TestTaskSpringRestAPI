package com.example.testtask.controllers;

import com.example.testtask.dao.ArticleRepository;
import com.example.testtask.dao.UserRepository;
import com.example.testtask.models.Article;
import com.example.testtask.models.User;
import com.example.testtask.models.enums.Color;
import com.example.testtask.models.requests.AddArticleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(method = RequestMethod.GET)
    public List<Article> findAllUsers() {
        return articleRepository.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Map<User, List<Article>> usersWithArticles() {
        Map<User, List<Article>> resultMap = new HashMap<>();
        List<User> users = userRepository.findAll();
        for (User u : users
        ) {
            resultMap.put(u, u.getArticles());
        }
        return resultMap;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addArticle(@RequestBody AddArticleRequest addArticleRequest) {
        User user = userRepository.findById(addArticleRequest.getUserId()).get();
        if (user != null) {
            Article article = new Article();
            article.setUser(userRepository.findById(addArticleRequest.getUserId()).get());
            article.setText(addArticleRequest.getText());
            String color = addArticleRequest.getColor();
            article.setColor(Color.valueOf(color));
            articleRepository.save(article);
        }
    }

}
