package com.example.demo.test.stock;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;
    /*
    * 예시 :
    * https://api.finance.naver.com/service/itemSummary.naver?itemcode=036570
    * https://api.finance.naver.com/siseJson.naver?symbol=252670&requestType=1&startTime=20211103&endTime=20211105&timeframe=day
    * */

    @GetMapping("/test12")
    public void asdd(@Valid Stock stock, Errors errors) {
        System.out.println(stock.getCode());
        return;
    }
    @GetMapping("/test123")
    public void asd(){

        StockCode stockCode = new StockCode();
        List<String> stockCodeList = stockCode.getStockCodeList();

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        String today = simpleDateFormat.format(nowDate);

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, -1);
        String today = simpleDateFormat.format(cal.getTime());

        cal.add(Calendar.DATE, -1);
        String yesterday = simpleDateFormat.format(cal.getTime());

//        String stockCodeString = "036570";
        String url = "https://api.finance.naver.com/siseJson.naver?symbol=${stockCode}&requestType=1&startTime=${startTimeString}&endTime=${endTimeString}&timeframe=day";

//        stockCodeList = new ArrayList<>();
//        stockCodeList.add("131100");
        RestTemplate restTemplate = new RestTemplate();
        for(int a = 0; a<1000; a++){
            String stockCodeString = stockCodeList.get(a);
            String tempUrl = url;
            tempUrl = tempUrl.replace("${stockCode}", stockCodeString);
            tempUrl = tempUrl.replace("${startTimeString}", yesterday);
            tempUrl = tempUrl.replace("${endTimeString}", today);

            ResponseEntity<String> response
                    = restTemplate.getForEntity(tempUrl, String.class);

            String responseString = response.getBody();

            responseString = responseString.replace("\n", "");
            String[] asd = responseString.split("\t");
            responseString = responseString.replace("\t", "");
            List<Price> priceList = new ArrayList<>();
            for(int i = 1; i<asd.length; i++){
                String tempString = asd[i];
                if(tempString.length()<=1){
                    continue;
                }
                tempString = tempString.replace("\"", "");
                tempString = tempString.replace("[", "");
                tempString = tempString.replace("]", "");
                tempString = tempString.replace("'", "");
                tempString = tempString.replace(" ", "");

                String[] asas = tempString.split(",");

                Price price = new Price();
                price.setStartPrice(Integer.valueOf(asas[1]));
                price.setEndPrice(Integer.valueOf(asas[4]));
                priceList.add(price);
            }

            Price yesterdayPrice = priceList.get(0);
            Price todayPrice = priceList.get(1);

            if(yesterdayPrice.isFivePercentProfit()){
                if(stockService.isGapOnePointEightPercent(yesterdayPrice, todayPrice)) {
                    System.out.println(stockCodeString + ":" + yesterdayPrice.getEndPrice().toString() + "-" + todayPrice.getPrice().toString());
                }
            }
        }


        System.out.println("test2");

    }

}
