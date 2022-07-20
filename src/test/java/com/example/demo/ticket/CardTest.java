package com.example.demo.ticket;

import com.example.demo.entity.Card;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class CardTest {

    @DisplayName("반복 테스트")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    public void repeatedTest(RepetitionInfo repetitionInfo){
        System.out.println("repeatedTest : " + repetitionInfo.getCurrentRepetition());
        System.out.println("Total : " + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("반복 파라미터 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"aa", "bb", "cc", "dd"})
    void parameterizedTest(String message){
        System.out.println(message);
    }
    

    @Test
    @DisplayName("테스트 케이스 이름")
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9})
    public void testTemplate() {
        Card card = new Card();
        assertNotNull(card);

        // 아래 두 코드는 뒤의 문자열 연산을 무조건 하는지, 실패했을 때만 하는지의 차이가 있다.
        assertNull(card.getId(), "초기 id 값은 Null");
        assertNull(card.getId(), () -> "초기 id 값은 Null");

        // assert 로 선언 시 테스트 케이스가 하나만 실패해도 테스트가 중단됨.
        // 아래처럼 assertAll 로 묶으면, 묶인 모든 테스트 케이스가 도중에 중단되지 않고 실행된다.
        assertAll(
                () -> assertNull(card.getId(), "테스트"),
                () -> assertNull(card.getId(), "테스트"),
                () -> assertNull(card.getId(), "테스트")
        );

        // 발생하는 예외에 대한 테스트
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> card.throwException(Boolean.TRUE));
//        String message = exception.getMessage();
//        assertEquals("test exception", message);

        //// 걸리는 시간에 대한 테스트
        // 로직을 무조건 끝까지 실행
        assertTimeout(Duration.ofSeconds(10), () -> new Card());
        // 기대 시간이 초과하면 실패하며 바로 종료, 단 다른 스레드에서 로직이 실행되기 때문에 스레드 관리가 필요
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> new Card());

        //// 조건부 테스팅
        // 조건을 만족하지 않으면 이후 테스트는 진행되지 않음
        assumeTrue(true);
        // 조건을 만족하면 괄호 안의 테스트를 진행
        assumingThat((true), ()-> {
            System.out.println("tets");
            assertEquals("a", "a");
        });

    }

    // 테스트 케이스 중 테스트를 실행하지 않음
    @Disabled
    @Test
    public void disabledAnnotationTest() {
        Card card = new Card();
        assertNotNull(card);
        System.out.println("create2");
    }

    // 모든 테스트를 실행하기전 한번 실행
    @BeforeAll
    static void beforeAll() {
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
        System.out.println("before each");
    }

    // 각 테스트를 실행하고 나서 한번 실행
    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }


}