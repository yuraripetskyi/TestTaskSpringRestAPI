package com.example.testtask.utils;

import com.example.testtask.dao.ArticleRepository;
import com.example.testtask.dao.UserRepository;
import com.example.testtask.models.Article;
import com.example.testtask.models.User;
import com.example.testtask.models.enums.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 0; i <= 10; i++ ){
            User u = new User();
            List<Article> articles = new ArrayList<>();

            //Creating random name
            Random r = new Random();
            StringBuilder sb = new StringBuilder();
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            for (int j = 0; j < 6; j++) {
                sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
            }
            String randomName = sb.toString();

            u.setName(randomName);

            //Creating random age from 1 to 100

            int randAge =10 +  (int)(Math.random()*(91));

            u.setAge(randAge);


            //Creating random list of articles
            Random rand = new Random();
            int randint = Math.abs(rand.nextInt()) % 11;
            for(int k = 0; k<=randint; k++){
                //creating random article
                Article article = new Article();
                article.setUser(u);
                //random text for article
                for (int j = 0; j < 10; j++) {
                    sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
                }
                article.setText(sb.toString());

                //rendom color
                int pick = new Random().nextInt(Color.values().length);
                article.setColor(Color.values()[pick]);

                articles.add(article);
            }
            userRepository.save(u);
            articleRepository.saveAll(articles);

        }

    }
}
