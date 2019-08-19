package com.gxz.redis;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisClient {
	
	private JedisPool pool;
	private JedisCluster jedisCluster;
	
	public RedisClient(JedisPool pool) {
		this.pool = pool;
	}
	
	public RedisClient(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}
	
	/**
	 * 判断一个  Key是否存在
	 * 优先使用集群版
	 */
	public boolean isExist(String key) {
		boolean flag = false;
		if(null!=jedisCluster) {
			flag = jedisCluster.exists(key);
		}else {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				flag = jedis.exists(key);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
		
		return flag;
	}
	
	public boolean isExist(byte[] key) {
		boolean flag = false;
		if(null != jedisCluster) {
			flag = jedisCluster.exists(key);
		}else {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				flag = jedis.exists(key);
				
			} catch (Exception e) {
				e.printStackTrace();

			}finally {
				jedis.close();
			}
		}
		return flag;
	}
	
	/**
	 * 给Redis里面存数据
	 */
	public void set(String key,String value) {
		if(null != jedisCluster) {
			jedisCluster.set(key, value);
		}else {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				jedis.set(key, value);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
	}
	
	public void set(byte[] key,byte[] value) {
		if(null != jedisCluster) {
			jedisCluster.set(key, value);
		}else {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				jedis.set(key, value);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
	}
	
	/**
	 * 取数据
	 */
	public String get(String key) {
		String value = null;
		if(null != jedisCluster) {
			value = jedisCluster.get(key);
		}else {
			Jedis jedis = null;
			try {
			    jedis = pool.getResource();
				value = jedis.get(key);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
		return value;
	}
	
	public byte[] get(byte[] key) {
		byte[] value = null;
		if(null != jedisCluster) {
			value = jedisCluster.get(key);
		}else {
			Jedis jedis = null;
			try {
			    jedis = pool.getResource();
				value = jedis.get(key);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
		return value;
	}
	
	/**
	 * Redis 的++操作
	 * 
	 */
	public double incre(String key) {
		double value = 0L;
		if(null != jedisCluster) {
			value = jedisCluster.incr(key);
		}else {
			Jedis jedis = null;
			try {
			    jedis = pool.getResource();
				value = jedis.incr(key);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
		return value;
	}
	
	/**
	 * 设置过期时间
	 */
	public void setExpire(String key,long timeOut) {
		if(null != jedisCluster) {
			jedisCluster.expire(key, (int) timeOut);
		}else {
			Jedis jedis = null;
			try {
			    jedis = pool.getResource();
				jedis.expire(key, (int) timeOut);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
	}
	
	//删除key对应的值
	public void deleteValue(String ... key) {
		if(null != jedisCluster) {
			jedisCluster.del(key);
		}else {
			Jedis jedis = null;
			try {
			    jedis = pool.getResource();
				jedis.del(key);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
	}
	
	public void deleteValue(byte[] ... key) {
		if(null != jedisCluster) {
			jedisCluster.del(key);
		}else {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				jedis.del(key);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
	}
	
	/***********************购物车用redis操作*************************/
	public void zadd(String key,String value,double score) {
		if(null != jedisCluster) {
			jedisCluster.zadd(key, score, value);
		}else {
			Jedis jedis = null;
			try {
			    jedis = pool.getResource();
				jedis.zadd(key, score, value);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
	}
	
	public boolean isHashExist(String key,String field) {
		boolean flag = false;
		if(null != jedisCluster) {
			flag = jedisCluster.hexists(key, field);
		}else {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				flag = jedis.hexists(key, field);
				
			} catch (Exception e) {
				e.printStackTrace();

			}finally {
				jedis.close();
			}
		}
		return flag;
	}
	
	public void hset(String key,String field,String value) {
		if(null != jedisCluster) {
			jedisCluster.hset(key, field, value);
		}else {
			Jedis jedis = null;
			try {
			    jedis = pool.getResource();
				jedis.hset(key, field, value);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
	}
	
	public String hget(String key,String field) {
		String value = null;
		
		if(null != jedisCluster) {
			value = jedisCluster.hget(key, field);
		}else {
			Jedis jedis = null;
			try {
			    jedis = pool.getResource();
				value = jedis.hget(key, field);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
		
		return value;
	}
	
	public Map<String,String> hgetAll(String key) {
		Map<String,String> allKeyWords = new HashMap<>();
		
		if(null != jedisCluster) {
			allKeyWords = jedisCluster.hgetAll(key);
		}else {
			Jedis jedis = null;
			try {
			    jedis = pool.getResource();
			    allKeyWords = jedis.hgetAll(key);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				jedis.close();
			}
		}
		
		return allKeyWords;
	}
}
