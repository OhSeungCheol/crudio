package com.example.demo.security.config;

import com.example.demo.security.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// 스프링 시큐리티 User 객체를 상속받은 커스텀 계정 객체
public class AccountContext extends User {
    private final Account account;
    public AccountContext(Account account, Collection<? extends GrantedAuthority> authorities){
        super(account.getUsername(), account.getPassword(), authorities);
        this.account = account;
    }

    public Account getAccount(){
        return this.account;
    }
}
