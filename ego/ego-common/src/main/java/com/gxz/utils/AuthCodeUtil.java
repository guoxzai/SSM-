package com.gxz.utils;

import java.util.Random;

public class AuthCodeUtil {

	private static Random rdm = new Random();
	
	public static String generatorCode(int length) {
		
		StringBuilder sb = new StringBuilder();
		
		while(length>0) {
			sb.append(rdm.nextInt(10)+"");
			length --;
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * 订单号的生成
	 * 生成一个固定长度的数字
	 * @param num
	 * 固定长度
	 * @param length
	 * 数字
	 * @return
	 */
	public static String genIncre(String num,int length) {
		int genZore = length-num.length();
		StringBuffer zoreStr = new StringBuffer();
		while(genZore>0) {
			zoreStr.append("0");
			genZore--;
		}
		return zoreStr+num;
	}

	
	public static void main(String[] args) {
		System.out.println(AuthCodeUtil.generatorCode(5));
	}
}
