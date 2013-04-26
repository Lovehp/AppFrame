package com.webapp.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常的工具类
 * 
 * @author luhf
 * @date 2013-4-13 上午12:31:15
 */
public class Exceptions{

	/**
	 * 将CheckedException转换为UncheckedException.
	 * 
	 * @param e
	 *            异常.
	 * @return 运行时异常.
	 */
	public static RuntimeException unchecked(Exception e){
		if(e instanceof RuntimeException){
			return (RuntimeException)e;
		}else{
			return new RuntimeException(e);
		}
	}

	/**
	 * 将ErrorStack转化为String.
	 * 
	 * @param e
	 *            异常.
	 * @return 异常字符串.
	 */
	public static String getStackTraceAsString(Exception e){
		StringWriter stringWriter=new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	/**
	 * 判断异常是否由某些底层的异常引起.
	 * 
	 * @param e
	 *            异常.
	 * @param causeExceptionClasses
	 *            所有异常.
	 * @return 是[否].
	 */
	public static boolean isCausedBy(Exception e,Class<? extends Exception>...causeExceptionClasses){
		Throwable cause=e.getCause();
		while(cause!=null){
			for(Class<? extends Exception> causeClass:causeExceptionClasses){
				if(causeClass.isInstance(cause)){
					return true;
				}
			}
			cause=cause.getCause();
		}
		return false;
	}
}
