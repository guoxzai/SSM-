package com.gxz.cache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.crypto.dsig.SignatureMethod;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gxz.anno.DeleteCache;
import com.gxz.anno.EnableCache;
import com.gxz.redis.RedisClient;
import com.gxz.utils.SerializationUtil;

@Component
@Aspect
public class CacheAspect {

	@Autowired
	private RedisClient redisClient;
	
	@Around("@annotation(com.gxz.anno.EnableCache)")
	public Object cacheable(ProceedingJoinPoint point) {
		byte[] key = getKey(point).getBytes();
		Object result  = null ;

		if(redisClient.isExist(key)) { // 缓存里面有
			System.out.println("缓存命中");
			byte[] value = redisClient.get(key);
			result = SerializationUtil.deSerializate(value);
			result = result;
			result = result;
			result = result;
			result = result;
			result = result;
			return result;
		}
		System.out.println("缓存没有命中");
		// 缓存里面没有 ，调用真实的方法
		try {
			result = point.proceed(point.getArgs()); // 真实方法的调用
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// 将值写入缓存里面
		redisClient.set(key, SerializationUtil.serializate(result));
		return result;
		
	}

	private String getKey(ProceedingJoinPoint point) {
		String key = "";
		
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		Method realMethod = null;
		try {
			realMethod = point.getTarget().getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EnableCache cache = realMethod.getAnnotation(EnableCache.class);
		// 实现不同类里面方法的缓存的隔离
		String namespace = cache.namespace();
		// 实现不同参数方法的隔离 若label 包含#
		String label = cache.label(); 
		Object[] args = point.getArgs();
		//设置redis中key的值
		Object paramValue = null ;
		key = namespace+ realMethod.getName();
		if(!label.equals("")&&label.contains("#")) {
			String paramIndex = label.split("#")[1]; // #0 
			paramValue = args[Integer.valueOf(paramIndex)];
			key+=paramValue.toString();
		}else {
			key+=label;
		}
		return key;
	}
	
	/**
	 * 若有修改虽然是前置通知  但是若用前置通知ProceedingJoinPoint point将会失效
	 * @return
	 */
	@Around("@annotation(com.gxz.anno.DeleteCache)")
	public Object deleteCache(ProceedingJoinPoint point) {
		Object obj = null;
		byte[] key = getDeleteKey(point).getBytes();
		
		if(redisClient.isExist(key)) {
			redisClient.deleteValue(key);
		}		
		
		try {
			obj = point.proceed(point.getArgs());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	private String getDeleteKey(ProceedingJoinPoint point) {
		String key = "";
		
		MethodSignature methodSignature =  (MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		Method realMethod = null;
		try {
			realMethod = point.getTarget().getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
			DeleteCache deleteCache = realMethod.getAnnotation(DeleteCache.class);
			String namespace = deleteCache.namespace();
			String cacheMethod = deleteCache.method();
			String label = deleteCache.label();
			//  # 0.id
			if(!"".equals(label) && label.contains("#")) {
				label = parseExpression(point, label);
			}
			
			key = namespace + cacheMethod + label;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return key;
	}

	private String parseExpression(ProceedingJoinPoint point, String label)
			throws NoSuchFieldException, IllegalAccessException {
		String labelValue = label.split("#")[1];   //0.pid
		String[] values = labelValue.split("\\.");
		Integer index = Integer.parseInt(values[0]);
		String fieldName = values[1];
		
		Object[] args = point.getArgs();
		Object paramObj = args[index];
		Field field = paramObj.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		Object label1 = field.get(paramObj);
		return label1.toString();
	}
	
}
