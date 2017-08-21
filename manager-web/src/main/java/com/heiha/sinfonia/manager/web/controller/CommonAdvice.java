package com.heiha.sinfonia.manager.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/21 15:22<br>
 * <b>Author:</b> heiha<br>
 */
@ControllerAdvice
public class CommonAdvice {

    @ExceptionHandler
    public void error(Throwable t) {
        throw new RuntimeException("Error Happen, Message: " + t.getMessage());
    }

}
