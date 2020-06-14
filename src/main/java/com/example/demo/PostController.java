package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @RequestMapping("/create")
    public String create(){
        return "Success!!";
    }
}
