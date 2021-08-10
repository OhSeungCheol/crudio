package com.example.demo.ticket;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @Mock
    TicketRepository ticketRepository;

    @Test
    public void createMockObject(@Mock TicketRepository localTicketRepository){
        assertNotNull(localTicketRepository);
        assertNotNull(ticketRepository);
    }

    @Test
    public void mockStubbingExample(){
        // 스터빙 할 객체
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setContent("mockTestContent");
        ticket.setTitle("mockTest");
        ticket.setDate(new Date());
        ticket.setWriter("OSC");

        // 특정 함수 호출 시 반환할 객체 스터빙 설정
        Mockito.when(ticketRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(ticket))
                .thenReturn(Optional.of(ticket))
                .thenReturn(Optional.empty());      // 세 번째 호출 부터는 빈 값 반환

        // 두 번째 호출 까지는 동일한 스터빙 객체 반환
        Optional<Ticket> ticket1 = ticketRepository.findById(1L);
        Optional<Ticket> ticket2 = ticketRepository.findById(2L);
        assertEquals(ticket1, ticket2);

        // 세 번째 호출은 빈 값 반환
        Optional<Ticket> ticket3 = ticketRepository.findById(null);
        assertEquals(Optional.empty(), ticket3);

        // 특정 함수 호출 시 예외 스터빙 설정
        Mockito.doThrow(new IllegalArgumentException()).when(ticketRepository).deleteById(1L);
        assertThrows(IllegalArgumentException.class, () -> {
           ticketRepository.deleteById(1L);
        });
        ticketRepository.deleteById(2L);
    }
}