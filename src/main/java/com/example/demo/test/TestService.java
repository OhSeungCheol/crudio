package com.example.demo.test;

import com.example.demo.annotation.PrintString;
import com.example.demo.util.CacheHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final CacheHelper cacheHelper;

    @PrintString(value="aroundTest")
    public void printTest(String word, Integer number){
        System.out.println(word + " " + number);
    }

    @Cacheable("test2")
    public Integer test2(){
        System.out.println("test2");
        return 2;
    }

    @CacheEvict(value = "cache", allEntries = true)
    public void evict(){
        System.out.println("eveict");
    }

    @Cacheable("testIn")
    public String testIn(){
        System.out.println(this);
        return "1";
    }

//    @Cacheable("testOut")
    public String testOut(){
//        self.testIn();
        this.testIn();
        return "2";
    }
}
