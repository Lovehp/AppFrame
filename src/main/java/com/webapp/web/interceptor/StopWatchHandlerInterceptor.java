package com.webapp.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 性能监控拦截{记录从开始处理到处理完毕消耗的时间}
 * 
 * @author luhf
 * @date 2013-4-11 下午11:23:32
 */
public class StopWatchHandlerInterceptor extends HandlerInterceptorAdapter{
	
	protected final static Logger logger=LoggerFactory.getLogger(StopWatchHandlerInterceptor.class);
	
	private NamedThreadLocal<Long> startTimeThreadLocal=new NamedThreadLocal<Long>("StopTime-StartTime");

	/**
	 * 预处理记录开始时间
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		long beginTime=System.currentTimeMillis();// 1.开始时间
		startTimeThreadLocal.set(beginTime);
		return true;
	}

	/**
	 * 后处理渲染视图之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{
		super.postHandle(request,response,handler,modelAndView);
	}

	/**
	 * 完成后记录请求日志
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
		long endTime=System.currentTimeMillis();// 2.结束时间
		long beginTime=startTimeThreadLocal.get();// 得到线程绑定的局部变量(开始时间)
		long consumeTime=endTime-beginTime;// 3.消耗时间
		// 记录到日志文件
		logger.debug(String.format("访问:%s,消耗:%d毫秒",request.getRequestURI(),consumeTime));
	}
}
