package com.blbz.springframwork.model;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPExpls {
    @Before(value = "execution(public void getMapValue())")
    public void beforeMethod() {
        System.out.println();
        System.out.println("Aspect - Before");
    }

    @Before(value = "args(String)")
    public void beforeargMethod() {
        System.out.println();
        System.out.println("Aspect - Before - Args");
    }

    @Order(1)
    @After("within(com.blbz.springframwork.model.NextClass)")
    public void afterwithinMethod() {
        System.out.println("Aspect - After using 'within'");
        System.out.println();
    }

    @After("execution(* com.blbz.springframwork.model.NextClass.getMapValue())")
    public void afterMethod() {
        System.out.println("Aspect - After'");
        System.out.println();
    }

    @AfterReturning(value = "execution(public String returningString(String))", returning = "textval")
    public void afterreturning(String textval) {
        System.out.println("After returning Value is :" + textval);
        System.out.println();
    }

    @AfterThrowing(value = "within(com.blbz.springframwork.model.NextClass)", throwing = "textval")
    public void afterreturning(Throwable textval) {
        System.out.println("After throwing Value is :" + textval);
        System.out.println();
    }

    @Around("execution(public int forArrount(int))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("Around @Before");
        Object value=null;
        try {
            value=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("Around Throwing");
            value=-1;
        }
        System.out.println("Around value : "+value);
        System.out.println("Around @After");
        return value;
    }
}
