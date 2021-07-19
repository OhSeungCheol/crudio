package com.example.demo.ticket;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    @DisplayName("테스트 케이스 이름")
    public void create() {
        Ticket ticket = new Ticket();
        assertNotNull(ticket);
        System.out.println("create");
    }

    @Test
    public void create1() {
        Ticket ticket = new Ticket();
        assertNotNull(ticket);
        System.out.println("create1");
    }

    // 테스트 케이스 중 테스트를 실행하지 않음
    @Disabled
    @Test
    public void create2() {
        Ticket ticket = new Ticket();
        assertNotNull(ticket);
        System.out.println("create2");
    }

    // 모든 테스트를 실행하기전 한번 실행
    @BeforeEach
    public void beforeAll() {
        System.out.println("before all");
    }

    // 모든 테스트를 실행하고 나서 한번 실행
    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    // 각 테스트를 실행하기전 한번 실행
    @BeforeEach
    void beforeEach() {
        System.out.println("before after");
    }

    // 각 테스트를 실행하고 나서 한번 실행
    @AfterEach
    void afterEach() {

    }


}