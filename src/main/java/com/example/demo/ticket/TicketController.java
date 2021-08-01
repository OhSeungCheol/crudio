package com.example.demo.ticket;

import com.example.sub.CommonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    // 서브 프로젝트 공통 오브젝트 사용 예시   
    CommonObject commonObject = new CommonObject();

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void create() {
        Ticket ticket = new Ticket("title", "content", new Date(), "writer");
        Ticket newTicket = ticketService.create(ticket);
        return;
    }

    @RequestMapping(value = "/readAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Ticket> readAll(){
        List<Ticket> ticketList = ticketService.readAll();
        return ticketList;
    }

    @RequestMapping(value = "/readOne", method = RequestMethod.GET)
    @ResponseBody
    public Ticket readOne(){
        return ticketService.readOne(0L);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(){
        //
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(){
        //
    }

}
