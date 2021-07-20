package com.example.demo.ticket;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    @DisplayName("테스트 케이스 이름")
    public void create() {
        Ticket ticket = new Ticket();
        assertNotNull(ticket);

        // 아래 두 코드는 뒤의 문자열 연산을 무조건 하는지, 실패했을 때만 하는지의 차이가 있다.
        assertNull(ticket.getId(), "초기 id 값은 Null");
        assertNull(ticket.getId(), () -> "초기 id 값은 Null");

        // assert 로 선언 시 테스트 케이스가 하나만 실패해도 테스트가 중단됨.
        // 아래처럼 assertAll 로 묶으면, 묶인 모든 테스트 케이스가 도중에 중단되지 않고 실행된다.
        assertAll(
                () -> assertNull(ticket.getId(), "테스트"),
                () -> assertNull(ticket.getId(), "테스트"),
                () -> assertNull(ticket.getId(), "테스트")
        );

        // 발생하는 예외에 대한 테스트
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ticket.throwException(Boolean.TRUE));
        String message = exception.getMessage();
        assertEquals("test exception", message);

        //// 걸리는 시간에 대한 테스트
        // 로직을 무조건 끝까지 실행
        assertTimeout(Duration.ofSeconds(10), () -> new Ticket());
        // 기대 시간이 초과하면 실패하며 바로 종료, 단 다른 스레드에서 로직이 실행되기 때문에 스레드 관리가 필요
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> new Ticket());
    }

    // 테스트 케이스 중 테스트를 실행하지 않음
    @Disabled
    @Test
    public void disabledAnnotationTest() {
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