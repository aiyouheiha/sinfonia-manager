package com.heiha.sinfonia.manager.web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
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

    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String test(@PathVariable Long id) {
        if (id < 10)
            throw new RuntimeException("ID less than 10, throw exception");
        return "Test ID: " + id;
    }

    public String error(Long id) {
        if (id == 2)
            throw new RuntimeException("ID 2, throw exception");
        return "Test ID: " + id + ", now error happen";
    }
}
