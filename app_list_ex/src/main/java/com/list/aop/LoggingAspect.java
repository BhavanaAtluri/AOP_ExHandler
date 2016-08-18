package com.list.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger=LoggerFactory.getLogger(LoggingAspect.class);
	
    /*@Before("execution(* com.list.*.*(..))")
    public void before(JoinPoint joinPoint) {
    	logger.info("Before executing : "+joinPoint.getSignature().getName());
    }
    
    @Around("execution(* com.list.*.*(..))")
	public void userAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
    	logger.info("@Around: Before"+ joinPoint.getSignature().getName());
		joinPoint.proceed();
		logger.info("@Around: After "+ joinPoint.getSignature().getName());
	}*/
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {}
	
	@Before("requestMapping()")
    public void before(JoinPoint joinPoint) {
    	logger.info("Before executing the Controller method: "+joinPoint.getSignature().getName());
    }
	
	@Around("execution(* com.list.service.DeviceService.*(..))")
	public Object userAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
    	logger.info("@Around: Before Method in Service : "+ joinPoint.getSignature().getName());
		try{
			return joinPoint.proceed();
		}
		finally{
			logger.info("@Around: After  Method in Service : "+ joinPoint.getSignature().getName());
		}
	}
    
}
