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
public class LogAspect {

	 private static Logger log = LoggerFactory.getLogger(LogAspect.class);
	
	 @Around("execution(* com.gxz.service.impl.*.*(..))")
	 public Object loginPrint(ProceedingJoinPoint point) {
		//该方法属于哪个类型
		 Object target = point.getTarget();
		// 方法签名
		 Signature signature = point.getSignature();
		// 方法的参数
		 Object[] args = point.getArgs();
		 log.debug("------{"+target.getClass().getName()+"-->"+signature.getName()+"-->"+args.toString()+"}--------");
		 
		 Object result = null;
		 try {
			 result = point.proceed(args);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		 
		 return result;
	 }
}
