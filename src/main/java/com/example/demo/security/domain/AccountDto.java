package com.example.demo.security.domain;

import lombok.Data;

@Data
public class AccountDto {

    String username;
    String password;
    String email;
    String age;
    String role;

    public static AccountDto getThemeVo(){
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("osc");
        accountDto.setPassword("0000");
        accountDto.setEmail("dhtmdcjf7@gmail.com");
        accountDto.setAge("28");
        accountDto.setRole("guest");
        return accountDto;
    }
}
