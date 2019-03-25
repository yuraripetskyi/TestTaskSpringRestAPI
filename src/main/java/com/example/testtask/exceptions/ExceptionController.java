package com.example.testtask.exceptions;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    void nullException(ConstraintViolationException e, HttpServletResponse response) throws IOException {
        System.out.println("Null exception: " + e.getMessage());
        response.sendError(11, e.getMessage());
    }
}
