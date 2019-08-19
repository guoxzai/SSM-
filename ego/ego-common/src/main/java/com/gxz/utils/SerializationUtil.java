package com.gxz.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {
	
	/**
	 * 正向的序列化
	 * Object->bytes
	 */
	public static byte[] serializate(Object object) {
		ByteArrayOutputStream byteOutputStream = null ;
		ObjectOutputStream objectOutputStream = null ;
		byte[] bs = null ;
		try {
			byteOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteOutputStream);
			objectOutputStream.writeObject(object);
			bs = byteOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			// 关闭资源
			closeRes(objectOutputStream,byteOutputStream);
		}
		return bs;
	}

	/**
	 * 反序列化
	 * byte - > object
	 */
	public static Object deSerializate(byte[] bs) {
		Object result = null ;
		ByteArrayInputStream byteArrayInputStream = null ;
		ObjectInputStream objectInputStream = null ;
		try {
			byteArrayInputStream= new ByteArrayInputStream(bs);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			result = objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeRes(objectInputStream,byteArrayInputStream);
		}
		return result;
	}

	public static void closeRes(AutoCloseable ...res) {
		for (AutoCloseable closed : res) {
			if(closed!=null) {
				try {
					closed.close();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					closed = null ;
				}
			}
		}
	}
}
