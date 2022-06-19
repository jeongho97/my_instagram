package com.posco.insta.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //해당 Class가 횡단관심사(부가기능)Class임을 알려주는 Annotation이다.
@Component
@Slf4j
public class LoggingAspect { /*부장님이 시간 측정 시킬때 사용하자*/
//    @Before("execution(* com.posco.insta.user.service.*.find*(..))") // service까지 들어와서 아래 모든 클래스(*)에서 find로 시작하는 메소드 중 파라미터 값 상관없이 모든 메소드에 적용(..)
//    public void loggerBefore(){
//        //System.out.println("------------before --------------");
//        log.info("------------before --------------"); /*현업에서는 systemout으로 안보고 lombok의 slf4j의 log를 사용*/
//    }
//    @After("execution(* com.posco.insta.user.service.*.find*(..))")
//    public void loggerAfter(){
//        //System.out.println("------------after ---------------");
//        log.info("------------after ---------------");
//    }
    //DB에서 처리해서 갖고 오는 시간 계산
    //Around의 경우 핵심관심사의 실패여부와 상관없이 전 후로 실행되도록 하는 advice이다. @Around(Pointcut) Pointcut은 접근제어자,반환형 패키지를 포함한 클래스 경로 메소드파라미터이다.
    @Around("execution(* com.posco.insta.user.controller.*.*(..))")
    public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long beforeTimeMillis = System.currentTimeMillis(); //현재시간 갖고온것
        log.info("start : "+beforeTimeMillis);

        Object result=proceedingJoinPoint.proceed(); //이 라인 전후로 나눠진다
        long afterTimeMillis=System.currentTimeMillis();
        log.info("after : "+afterTimeMillis+" ,시간차 "+(afterTimeMillis-beforeTimeMillis));
        return result;
    }
}
