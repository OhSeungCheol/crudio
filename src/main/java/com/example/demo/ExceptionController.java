package com.example.demo;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController implements ErrorController {

    @GetMapping("/error")
    public String redirectRoot() {
        return "forward:/index.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
