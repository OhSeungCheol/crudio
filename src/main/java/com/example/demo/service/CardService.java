package com.example.demo.service;

import com.example.demo.entity.Card;
import com.example.demo.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Card create(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> readAll(){
        return cardRepository.findAll();
    }

    public Card readOne(Long ticketId){
        return cardRepository.findById(ticketId).orElseGet(Card::new);
    }

    public void update(){
        //
    }

    public void delete(){
        //
    }
}
