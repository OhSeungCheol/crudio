package com.example.demo.security.service;

import com.example.demo.security.domain.Account;
import com.example.demo.security.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> readAllAccount(){
        return accountRepository.findAll();
    }
//
//    public Ticket readOne(Long ticketId){
//        return accountRepository.findById(ticketId).orElseGet(Ticket::new);
//    }
}
