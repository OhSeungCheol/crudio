package com.example.demo.test.stock;

import org.springframework.stereotype.Service;

@Service
public class StockService {
    public Boolean isGapOnePointEightPercent(Price stock1, Price stock2){
        long temp1 = stock1.getEndPrice().longValue();
        stock2.setPrice(stock2.getStartPrice() < stock2.getEndPrice() ? stock2.getStartPrice() : stock2.getEndPrice());
        long temp2 = stock2.getPrice().longValue();
        if(temp1 * 1.018 <= temp2){
            return true;
        }
        return false;
    }

}
