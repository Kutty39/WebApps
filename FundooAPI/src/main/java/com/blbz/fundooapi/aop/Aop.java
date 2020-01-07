package com.blbz.fundooapi.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Aop {
    @Around(value = "execution(public * com.blbz.fundooapi.controller.*.*(..))")
    public Object forAllMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Object value=null;
        log.info("Method: " + proceedingJoinPoint.getSignature().toString());
        try{
            value=proceedingJoinPoint.proceed();
        }catch (Throwable e){
            log.error(e.getMessage());
        }
        log.info(value!=null? value.toString():null);
        log.info("Method: Exit from " +proceedingJoinPoint.getSignature().getName());
        return value;
    }
}
