package com.posco.insta.aspect;

import com.posco.insta.config.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SecurityAspect {
    @Autowired
    SecurityService securityService;

    @Before("@annotation(tokenRequired)")
    public void authenticationWithToken(TokenRequired tokenRequired)
            throws IllegalAccessException{
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        log.info("aop");

        String tokenBearer=request.getHeader("Authorization");//token을 parameter로 보내는게 아니라 header에 Authorization로 담아 보낸다
        if(tokenBearer.isEmpty()) throw new IllegalAccessException("token isNull");
        if(securityService.getSubject(tokenBearer) == null) throw new IllegalAccessException("bad token");


    }
}
