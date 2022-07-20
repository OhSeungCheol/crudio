package com.example.demo.ticket;


import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.Card;
import com.example.demo.repository.CardRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {
    @Mock
    CardRepository cardRepository;

    @Test
    public void createMockObject(@Mock CardRepository localCardRepository){
        assertNotNull(localCardRepository);
        assertNotNull(cardRepository);
    }

    @Test
    public void mockStubbingExample(){
        // 스터빙 할 객체
        Card card = new Card();
        card.setId(1L);
        card.setMessage("mockTestContent");
        card.setTitle("mockTest");
        card.setDate(new Date());
        card.setWriter("OSC");

        // 특정 함수 호출 시 반환할 객체 스터빙 설정
        Mockito.when(cardRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(card))
                .thenReturn(Optional.of(card))
                .thenReturn(Optional.empty());      // 세 번째 호출 부터는 빈 값 반환

        // 두 번째 호출 까지는 동일한 스터빙 객체 반환
        Optional<Card> ticket1 = cardRepository.findById(1L);
        Optional<Card> ticket2 = cardRepository.findById(2L);
        assertEquals(ticket1, ticket2);

        // 세 번째 호출은 빈 값 반환
        Optional<Card> ticket3 = cardRepository.findById(null);
        assertEquals(Optional.empty(), ticket3);

        // 특정 함수 호출 시 예외 스터빙 설정
        Mockito.doThrow(new IllegalArgumentException()).when(cardRepository).deleteById(1L);
        assertThrows(IllegalArgumentException.class, () -> {
           cardRepository.deleteById(1L);
        });
        cardRepository.deleteById(2L);


        // Mock 의 특정 메소드 호출에 대한 정보 조회 및 검증
        cardRepository.deleteAll();
        //        Mockito.verify(ticketRepository, Mockito.times(2)).deleteAll(Mockito.any());
        Mockito.verify(cardRepository, Mockito.never()).deleteAll(Mockito.any());

    }

    @Test
    public void givenWhenThenExample(){
        // Given : 특정 환경
        Card card = new Card();
        // When : 특정 상황
        Card loadCard = cardRepository.findById(card.getId()).orElse(null);
        // Then : 예상 결과
        assertNull(loadCard);
    }
}