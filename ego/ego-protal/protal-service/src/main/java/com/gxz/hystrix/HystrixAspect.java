package com.gxz.hystrix;

import java.util.Random;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.gxz.model.SearchGoodsResult;

@Component
@Aspect
public class HystrixAspect {

	private static Random rdm = new Random();
	
	private Hystrix hystrix = new Hystrix();
	
	@Around("execution(* com.gxz.service.impl.SearchGoodsServiceImpl.doSearchGoods(..))")
	public Object hystrixCmd(ProceedingJoinPoint point) {
		System.out.println("进入断路器");
		
		switch (hystrix.getStatus()) {
			case CLOSED: // 断路器关  正常调用
				return normalInvoke(point);
			case OPEN: // 断路器打开，不调用
				return noInvoke(point);
			case HALF_OPEN:// 断路器打开，测试调用
				return testInvoke(point);
			default:
				return null;
		}
	}
	
	private Object testInvoke(ProceedingJoinPoint point) {
		int seed = rdm.nextInt(100);
		Object result  = null ;
		if(seed< 3) { // 0 1 2    3%的概率
			try {
				result = point.proceed(point.getArgs());
				System.out.println("测试调用成功，solr 好了");
				hystrix.setStatus(Hystrix.Status.CLOSED); // solr 好了服务正常使用
				
				// 唤醒-> 清空失败次数的线程
				synchronized (hystrix.lock) {
					hystrix.lock.notifyAll();
				}
			} catch (Throwable e) {
				System.out.println("测试未通过");
			}
		}
		return result;
	}

	private Object noInvoke(ProceedingJoinPoint point) {
	     // 在一段时期内，程序不调用 ,一段时间的 粒度
		hystrix.setStatus(Hystrix.Status.HALF_OPEN);
		
		SearchGoodsResult searchGoodsResult = new SearchGoodsResult();
		searchGoodsResult.setStatus("断路器打开了");
		return searchGoodsResult; // 程序没有调用
	}

	private Object normalInvoke(ProceedingJoinPoint point) {
		Object result = null;
		try {
			result = point.proceed(point.getArgs());
		} catch (Throwable e) {
			System.out.println("调用失败");
			int failCount = hystrix.getCurrentFail().incrementAndGet();// i++ // 统计失败次数
			if(failCount>=Hystrix.FAIL_COUNT) {
				hystrix.setStatus(Hystrix.Status.OPEN); // 达到一定的失败次数，断路器打开
			}
		}
		
		SearchGoodsResult searchGoodsResult = new SearchGoodsResult();
		searchGoodsResult.setStatus("断路器打开了");
		return result==null?searchGoodsResult:result;
	}

	
}
