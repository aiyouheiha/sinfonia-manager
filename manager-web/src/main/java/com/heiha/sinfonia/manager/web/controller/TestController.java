package com.heiha.sinfonia.manager.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>
 * <b>Project:</b> manager<br>
 * <b>Date:</b> 2017/8/7 14:31<br>
 * <b>Author:</b> heiha<br>
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello";
    }
}
