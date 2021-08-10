package com.example.demo.ticket;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @Mock
    TicketRepository ticketRepository;

    @Test
    public void createMockObject(@Mock TicketRepository localTicketRepository){
        assertNotNull(localTicketRepository);
        assertNotNull(ticketRepository);
    }

}