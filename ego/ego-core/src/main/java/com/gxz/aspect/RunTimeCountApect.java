package com.gxz.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RunTimeCountApect {

	private static Logger log = LoggerFactory.getLogger(RunTimeCountApect.class);
	
	@Around("execution(* com.gxz.service.impl.*.*(..))")
	public Object execteMethodTime(ProceedingJoinPoint point) {
		long start = System.currentTimeMillis();
		
		Object result = null;
		try {
			result = point.proceed(point.getArgs());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		Signature signature = point.getSignature();
		log.debug("------{"+signature.getName()+"执行时间为:"+(end-start)+"}--------");
		return result;
	}
	
}
