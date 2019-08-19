package com.gxz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat SDF = new SimpleDateFormat();

	private static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss:SSS";

	public static String getToday() {
		Date date = new Date();
		SDF.applyPattern(DEFAULT_PATTERN);
		return SDF.format(date);
	}

	public static String getDate(String pattern) {
		if(pattern!=null) {
			SDF.applyPattern(pattern);
		}else {
			SDF.applyPattern(DEFAULT_PATTERN);
		}
		Date date = new Date();
		return SDF.format(date);
	}
	public static void main(String[] args) throws InterruptedException {
		int i= 0 ; // 4 0000 0001 0002 0010 0012 0013 
		while(true) {
//			Thread.sleep(2000); //10 100 1000 10000
			i++;
			int zero = 4-(i+"").length();// 4 3 2 1 
			switch (zero) {
			case 3:
                 System.out.println("000"+i); 
				break;
			case 2:
				 System.out.println("00"+i); 
				break;
			case 1:
				 System.out.println("0"+i); 
				break;
			default:
				System.out.println(i);
				break;
			}
			if(i>=9999) {
				break;
			}

		}
	}
}
