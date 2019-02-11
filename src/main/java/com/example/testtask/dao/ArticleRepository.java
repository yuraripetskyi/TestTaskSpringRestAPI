package com.example.testtask.dao;


import com.example.testtask.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {



}
