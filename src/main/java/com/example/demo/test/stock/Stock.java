package com.example.demo.test.stock;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class Stock {
    @NotNull
    String code;
}
