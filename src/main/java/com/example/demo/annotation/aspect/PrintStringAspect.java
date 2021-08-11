package com.example.demo.annotation.aspect;

import com.example.demo.annotation.PrintString;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class PrintStringAspect {

    @Before(value = "@annotation(printString)")
    public void printBefore(JoinPoint joinPoint, PrintString printString){
        Object[] args = joinPoint.getArgs();
        System.out.println("Before");
        System.out.println("args :" + Arrays.toString(args));
    }

    @After(value = "@annotation(printString)")
    public void printAfter(JoinPoint joinPoint, PrintString printString){
        Object[] args = joinPoint.getArgs();
        System.out.println("args :" + Arrays.toString(args));
        System.out.println("After");
    }
}