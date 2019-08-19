package com.gxz.hystrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Hystrix {
	
	public enum Status{
		OPEN(),// 断路器打开 ，服务不可用
		HALF_OPEN(),// 断路器关 服务可以
		CLOSED();//使用少许流量测试服务的可用性
	}
	
	private Status status = Status.CLOSED;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	//定义服务失败的规则
	public static final int TIME_ZONE = 7000;
	public static final int FAIL_COUNT = 3;
	
	/**
	 * 默认调用失败的次数
	 */
	private AtomicInteger currentFail =  new AtomicInteger(0);
	
	public Object lock = new Object();
	
	private ExecutorService pool  = Executors.newFixedThreadPool(1);
	
	{
		pool.submit(()->{
			while(true) {
				//若断路器是开或半开的状态  不需要统计失败的次数
				if(status == status.OPEN || status == status.HALF_OPEN) {
					synchronized (lock) {
						lock.wait();   //...........
					}
				}
				
				//断路器关闭统计失败的次数
				Thread.sleep(TIME_ZONE);
				currentFail = new AtomicInteger(0);
				System.out.println("清空调用次数");
			}
		});
	}

	public AtomicInteger getCurrentFail() {
		return currentFail;
	}

	public void setCurrentFail(AtomicInteger currentFail) {
		this.currentFail = currentFail;
	}
	
	
	
}
