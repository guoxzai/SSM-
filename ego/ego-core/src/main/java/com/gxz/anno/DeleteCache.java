package com.gxz.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DeleteCache {
	//隔离空间
	String namespace() default "";
	//会影响的方法
	String method() default ""; 
	//在方法里面取值
	String label()  default "";
}
