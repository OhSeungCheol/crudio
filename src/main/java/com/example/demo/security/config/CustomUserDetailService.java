package com.example.demo.security.config;


import com.example.demo.security.domain.Account;
import com.example.demo.security.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// UserDetailsService 를 상속받아 유저 객체를 조회해서 반환
// UserDetailsService : 유저 객체를 조회해서 UserDetails 타입으로 반환하는 스프링 시큐리티 클래스
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // DB 에서 username 에 해당하는 Account 데이터를 조회
        Account account = accountRepository.findByUsername(username);

        if(account == null){
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        roles.add(new SimpleGrantedAuthority(account.getRole()));

        AccountContext accountContext = new AccountContext(account, roles);

        return accountContext;
    }
}
