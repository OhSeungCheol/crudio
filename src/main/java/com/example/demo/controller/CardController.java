package com.example.demo.controller;

import com.example.demo.dto.CardResponseDto;
import com.example.demo.entity.Card;
import com.example.demo.service.CardService;
import com.example.sub.CommonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    // 서브 프로젝트 공통 오브젝트 사용 예시   
    CommonObject commonObject = new CommonObject();

    @GetMapping("/readPage")
    @ResponseBody
    public List<Card> getTicketList(
            @RequestParam(defaultValue = "1") Integer pageNo
    ){
        List<Card> cardList = cardService.readAll();
        return cardList;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Card card) throws Exception {
        Card newCard = cardService.create(card);
        if(newCard == null){
            throw new Exception("fail to create card");
        }
    }

    @RequestMapping(value = "/readAll", method = RequestMethod.GET)
    @ResponseBody
    public List<CardResponseDto> readAll(){
        List<Card> cardList = cardService.readAll();
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for(Card cardEntity : cardList){
            cardResponseDtoList.add(new CardResponseDto(cardEntity));
        }
        return cardResponseDtoList;
    }

    @RequestMapping(value = "/readOne", method = RequestMethod.GET)
    @ResponseBody
    public Card readOne(){
        return cardService.readOne(0L);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(){
        //
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Long id){
        cardService.delete(id);
    }

}
