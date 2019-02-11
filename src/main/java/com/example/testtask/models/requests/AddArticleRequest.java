package com.example.testtask.models.requests;

import com.example.testtask.models.User;
import com.example.testtask.models.enums.Color;

import java.net.UnknownServiceException;

public class AddArticleRequest {

    private int userId;
    private String text;
    private String color;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
