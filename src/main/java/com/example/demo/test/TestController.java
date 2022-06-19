package com.example.demo.test;

import com.example.demo.test.stock.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
    private static RestTemplate restTemplate;

    @Value("${version}")
    private String version;

    @GetMapping(value = "/test1")
    public void test(){
        String a = testService.testOut();
        System.out.println(a);
    }

    @GetMapping("asdasdasd")
    public void asd() {

        int SubNum = 123;
        int Pnum = 1;
        Object Pcode = "코드";
//        Object requestDto = SubmitData.builder()
//                .SubNum(SubNum)
//                .Pnum(Pnum)
//                .Pcode(Pcode)
//                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(Pcode, headers);

//        String url = "http://localhost:8080/send";
//        String url = "http://api.plos.org/search?q=title:DNA";
        String url = "https://api.finance.naver.com/siseJson.naver?symbol=252670&requestType=1&startTime=20210131&endTime=20210412&timeframe=day";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);

        Price price = new Price();
        price.setStartPrice(2000);
        price.setEndPrice(2100);

        if(price.isFivePercentProfit()){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        System.out.println("test");
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
