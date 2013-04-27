package com.webapp.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.webapp.common.controller.BaseController;

/**
 * 请求[响应]对象拦截器,让controller基类获取请求[响应]对象.
 * 
 * @author luhf
 * @date 2013-4-14 下午1:15:25
 */
public class BaseHandlerInterceptor extends HandlerInterceptorAdapter{

	protected final Logger logger=LoggerFactory.getLogger(getClass());

	/**
	 * 预处理记录开始时间[LV1]
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		HandlerMethod handlerMethod;
		try{
			handlerMethod=(HandlerMethod)handler;
			BaseController bc=(BaseController)handlerMethod.getBean();
			bc.setRequest(request);
			bc.setResponse(response);
		}catch(ClassCastException e){}
		return super.preHandle(request,response,handler);
	}

	/**
	 * 后处理渲染视图之前执行[LV2]
	 */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{
		super.postHandle(request,response,handler,modelAndView);
	}

	/**
	 * 完成后记录请求日志[LV3]
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
		super.afterCompletion(request,response,handler,ex);
	}

	/**
	 * 并发处理开始后
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		super.afterConcurrentHandlingStarted(request,response,handler);
	}

}
