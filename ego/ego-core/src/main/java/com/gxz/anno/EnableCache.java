package com.gxz.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解标记在方法上面，用来实现对该方法的缓存
 * 减少代码的侵入性
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableCache {
	
	/**
	 * 通过namespace 实现方法缓存的隔离
	 * @return
	 */
	String namespace() default "";
	
	/**
	 * 方法参数不同时它的缓存不同，该属性用户区分不同方法参数的调用
	 * @return
	 */
	String label() default "";
	
}
