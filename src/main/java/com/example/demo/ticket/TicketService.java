package com.example.demo.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> readAll(){
        return ticketRepository.findAll();
    }

    public Ticket readOne(Long ticketId){
        return ticketRepository.findById(ticketId).orElseGet(Ticket::new);
    }

    public void update(){
        //
    }

    public void delete(){
        //
    }
}
