package ru.idarenin.restservice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.idarenin.restservice.pojo.Greeting;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* ru.idarenin.restservice.controller.GreetingController.*(..)) && args(name)")
    public void restMethods(String name) {
    }

    @Around("restMethods(name)")
    public Greeting logMethodParams(ProceedingJoinPoint pjp, String name) throws Throwable {
        System.out.println("Input param [name]: " + name);
        Greeting greeting = (Greeting) pjp.proceed();
        System.out.println("Output param [id]: " + greeting.getId());
        System.out.println("Output param [content]: " + greeting.getContent());

        return greeting;
    }
}
