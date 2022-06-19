package com.example.demo.test.stock;

import lombok.Data;

@Data
public class Price {
    Integer startPrice;
    Integer endPrice;
    Integer price;

    public Boolean isFivePercentProfit(){
        Long startPriceLong = startPrice.longValue();
        if(startPriceLong * 1.05 <= endPrice.longValue()){
            return true;
        }

        return false;
    }
}
