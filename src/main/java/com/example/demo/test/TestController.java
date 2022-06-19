package com.example.demo.test;

import com.example.demo.stock.Price;
import com.example.demo.stock.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class TestController {
    public final TestService testService;
    public final ApplicationContext applicationContext;

    @Value("${version}")
    private String version;

    @GetMapping(value = "/test1")
    public void test(){
        String a = testService.testOut();
        System.out.println(a);
    }

    @GetMapping("/test")
    public void test(HttpServletResponse response) {
//        double sample = 12345.67890;
//        System.out.println(Math.round(sample * 1000) / 1000.0); //결과 5000
//        System.out.println(String.format("%.4f", sample)); //결과 : 5000.000

        double a = 0.987654321;
        System.out.println(a);                              // 결과 : 0.987654321
        System.out.println(Math.ceil(a * 100) / 100.0);     // 결과 : 0.99
        System.out.println(Math.round(a * 100) / 100.0);    // 결과 : 0.99
        System.out.println(Math.floor(a * 100) / 100.0);    // 결과 : 0.98
        System.out.println(String.format("%.3f", a));       // 결과 : 0.988


    }


}
