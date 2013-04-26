package com.webapp.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.webapp.common.controller.BaseController;

/**
 * 请求[响应]对象拦截器,让controller基类获取请求[响应]对象.
 * @author luhf
 * @date 2013-4-14 下午1:15:25
 */
public class BaseHandlerInterceptor extends HandlerInterceptorAdapter{

	protected final Logger logger=LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		HandlerMethod handlerMethod=(HandlerMethod)handler;
		BaseController bc=(BaseController)handlerMethod.getBean();
		bc.setRequest(request);
		bc.setResponse(response);
		return true;
	}

}
