package com.asiainfo.appframe.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类
 * 
 * @author luhf
 * @date 2013-4-13 上午12:32:17
 */
public class Identities{

	private static SecureRandom random=new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 * 
	 * @return 带分隔符的uuid字符串.
	 */
	public static String uuid(){
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 * 
	 * @return 无分隔符的uuid字符串.
	 */
	public static String uuid2(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 * 
	 * @return random字符串.
	 */
	public static long randomLong(){
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 * 
	 * @param length
	 *            长度.
	 * @return Base62位字符串.
	 */
	public static String randomBase62(int length){
		byte[] randomBytes=new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}
}
