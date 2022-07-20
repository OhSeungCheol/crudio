package com.example.demo.controller;

import com.example.demo.entity.Card;
import com.example.demo.service.CardService;
import com.example.sub.CommonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public void create() {
        Card card = Card.builder()
                .title("title")
//                .content("message")
                .date(new Date())
                .writer("writer")
                .build();
        Card newCard = cardService.create(card);
    }

    @RequestMapping(value = "/readAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Card> readAll(){
        List<Card> cardList = cardService.readAll();
        return cardList;
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
    public void delete(){
        //
    }

}
