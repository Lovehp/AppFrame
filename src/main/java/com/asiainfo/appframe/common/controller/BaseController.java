package com.asiainfo.appframe.common.controller;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * spring控制器基类
 * 
 * @author luhf
 * @date 2013-4-13 下午6:44:39
 */
public abstract class BaseController{

	private HttpServletRequest request;
	private HttpServletResponse response;

	public HttpServletRequest getRequest(){
		return request;
	}

	public void setRequest(HttpServletRequest request){
		this.request=request;
	}

	public HttpServletResponse getResponse(){
		return response;
	}

	public void setResponse(HttpServletResponse response){
		this.response=response;
	}

	public HttpSession getSession(){
		return getRequest().getSession();
	}

	public HttpSession getSession(boolean create){
		return getRequest().getSession(create);
	}

	/**
	 * 存储请求中的属性.
	 * 
	 * @param name
	 *            属性名称.
	 * @param value
	 *            存储对象.
	 * @return this.
	 */
	public BaseController setAttr(String name,Object value){
		getRequest().setAttribute(name,value);
		return this;
	}

	/**
	 * 存储请求中的属性
	 * 
	 * @param attrMap
	 *            Map类型的存储对象
	 * @return
	 */
	public BaseController setAttrs(Map<String,Object> attrMap){
		for(Map.Entry<String,Object> entry:attrMap.entrySet())
			getRequest().setAttribute(entry.getKey(),entry.getValue());
		return this;
	}

	/**
	 * 移除请求中的属性.
	 * 
	 * @param name
	 *            属性名称.
	 * @return this.
	 */
	public BaseController removeAttr(String name){
		getRequest().removeAttribute(name);
		return this;
	}

	/**
	 * 返回值的请求参数为字符串,或空,如果该参数不存在.
	 * 您应该只使用这种方法,当你确定的参数只有一个值.如果参数可能有多个值,使用getParaValues
	 * ​​(java.lang.String的).如果您使用此方法多值参数,返回值是等于第一getParameterValues​​返回的数组中的值.
	 * 
	 * @param name
	 *            请求参数属性名称.
	 * @return 字符串值.
	 */
	public String getPara(String name){
		return getRequest().getParameter(name);
	}

	/**
	 * 返回值的请求参数为字符串,或默认的值
	 * 
	 * @param name
	 *            请求参数属性名称.
	 * @param defaultValue
	 *            默认字符串值.
	 * @return 字符串值.
	 */
	public String getPara(String name,String defaultValue){
		String result=getPara(name);
		return result!=null&&!"".equals(result)?result:defaultValue;
	}

	/**
	 * 返回Map类型的请求参数.
	 * 
	 * @return Map类型对象.
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String[]> getParaMap(){
		return request.getParameterMap();
	}

	/**
	 * 获取请求参数名称.
	 * 
	 * @return 枚举对象.
	 */
	@SuppressWarnings("unchecked")
	public Enumeration<String> getParaNames(){
		return request.getParameterNames();
	}

	/**
	 * 返回一个数组,包含所有给定的请求参数的值,则返回null,如果该参数不存在的String对象.如果该参数有一个单一的值,该数组有一个长度为1[
	 * 如checkbox类(名字相同,但值有多个)的数据]
	 * 
	 * @param name
	 *            请求参数属性名称.
	 * @return 字符串数组.
	 */
	public String[] getParaValues(String name){
		return request.getParameterValues(name);
	}

	/**
	 * 返回一个数组,包含所有给定的请求参数的值,则返回null,如果该参数不存在的Integer对象.如果该参数有一个单一的值,该数组有一个长度为1
	 * 
	 * @param name
	 *            请求参数属性名称.
	 * @return 整型数组.
	 */
	public Integer[] getParaValuesToInt(String name){
		String[] values=getRequest().getParameterValues(name);
		if(values==null)
			return null;
		Integer[] result=new Integer[values.length];
		for(int i=0;i<result.length;i++)
			result[i]=Integer.parseInt(values[i]);
		return result;
	}

	/**
	 * 返回值命名属性为对象,或null,如果不存在给定名称的属性.
	 * 
	 * @param result
	 *            字符串值.
	 * @param defaultValue
	 *            默认值.
	 * @return 整型值.
	 */
	private Integer getParaToInt_(String result,Integer defaultValue){
		if(result==null)
			return defaultValue;
		if(result.startsWith("N")||result.startsWith("n"))
			return -Integer.parseInt(result.substring(1));
		return Integer.parseInt(result);
	}

	/**
	 * 返回值命名属性为对象,或null,如果不存在给定名称的属性.
	 * 
	 * @param name
	 *            请求参数名称.
	 * @return 整型值.
	 */
	public Integer getParaToInt(String name){
		return getParaToInt_(getPara(name),null);
	}

	/**
	 * 返回值命名属性为对象,或默认值,如果不存在给定名称的属性.
	 * 
	 * @param name
	 *            请求参数名称.
	 * @param defaultValue
	 *            默认值.
	 * @return 整型值.
	 */
	public Integer getParaToInt(String name,Integer defaultValue){
		return getParaToInt_(getPara(name),defaultValue);
	}

	/**
	 * 返回值命名属性为对象,或null,如果不存在给定名称的属性.
	 * 
	 * @param result
	 *            Long值.
	 * @param defaultValue
	 *            默认值.
	 * @return
	 */
	private Long getParaToLong_(String result,Long defaultValue){
		if(result==null)
			return defaultValue;
		if(result.startsWith("N")||result.startsWith("n"))
			return -Long.parseLong(result.substring(1));
		return Long.parseLong(result);
	}

	/**
	 * 返回值命名属性为对象,或null,如果不存在给定名称的属性.
	 * 
	 * @param name
	 *            请求参数名称.
	 * @return Long值.
	 */
	public Long getParaToLong(String name){
		return getParaToLong_(getPara(name),null);
	}

	/**
	 * 返回值命名属性为对象,或默认值,如果不存在给定名称的属性.
	 * 
	 * @param name
	 *            请求参数名称.
	 * @param defaultValue
	 *            默认值.
	 * @return Long值.
	 */
	public Long getParaToLong(String name,Long defaultValue){
		return getParaToLong_(getPara(name),defaultValue);
	}

	/**
	 * 返回值命名属性为对象,或默认值,如果不存在给定名称的属性.
	 * 
	 * @param name
	 *            请求参数名称.
	 * @return Boolean值.
	 */
	public Boolean getParaToBoolean(String name){
		String result=getPara(name);
		if(result!=null){
			result=result.trim().toLowerCase();
			if(result.equals("1")||result.equals("true"))
				return Boolean.TRUE;
			else if(result.equals("0")||result.equals("false"))
				return Boolean.FALSE;
			// return Boolean.FALSE; // if use this, delete 2 lines code under
		}
		return null;
	}

	/**
	 * 返回值命名属性为对象,或默认值,如果不存在给定名称的属性.
	 * 
	 * @param name
	 *            请求参数名称.
	 * @param defaultValue
	 *            默认值.
	 * @return Boolean值.
	 */
	public Boolean getParaToBoolean(String name,Boolean defaultValue){
		Boolean result=getParaToBoolean(name);
		return result!=null?result:defaultValue;
	}

	/**
	 * 返回一个枚举,其中包含这一要求的可用属性的名称.如果请求中没有提供给它的属性,此方法返回一个空的枚举.
	 * 
	 * @return 枚举包含请求的属性的名称的字符串.
	 */
	@SuppressWarnings("unchecked")
	public Enumeration<String> getAttrNames(){
		return getRequest().getAttributeNames();
	}

	/**
	 * 通过请求属性名称获取请求属性值.
	 * 
	 * @param name
	 *            请求属性名称.
	 * @return 请求属性值泛型.
	 */
	@SuppressWarnings("unchecked")
	public <T>T getAttr(String name){
		return (T)getRequest().getAttribute(name);
	}

	/**
	 * 通过请求属性名称获取请求属性值.
	 * 
	 * @param name
	 *            请求属性名称.
	 * @return 请求属性值字符串.
	 */
	public String getAttrForStr(String name){
		return (String)getRequest().getAttribute(name);
	}

	/**
	 * 通过请求属性名称获取请求属性值.
	 * 
	 * @param name
	 *            请求属性名称.
	 * @return 请求属性值整型.
	 */
	public Integer getAttrForInt(String name){
		return (Integer)getRequest().getAttribute(name);
	}

	/**
	 * 通过存储的属性名称从session返回一个对象.
	 * 
	 * @param 存储的属性名称
	 *            .
	 * @return 返回泛型对象.
	 */
	@SuppressWarnings("unchecked")
	public <T>T getSessionAttr(String key){
		HttpSession session=getSession(false);
		return session!=null?(T)session.getAttribute(key):null;
	}

	/**
	 * 向session中保存值.
	 * 
	 * @param key
	 *            存储的属性名称.
	 * @param value
	 *            存储的属性对象.
	 * @return this.
	 */
	public BaseController setSessionAttr(String key,Object value){
		getSession().setAttribute(key,value);
		return this;
	}

	/**
	 * 从session移除存储的属性对象.
	 * 
	 * @param key
	 *            存储的属性名称.
	 * @return this.
	 */
	public BaseController removeSessionAttr(String key){
		HttpSession session=getSession(false);
		if(session!=null)
			session.removeAttribute(key);
		return this;
	}

	/**
	 * 通过存储的属性名称从Cookie中获取Cookie对象.
	 * 
	 * @param name
	 *            存储的属性对象名称.
	 * @return Cookie对象.
	 */
	public Cookie getCookieObject(String name){
		Cookie[] cookies=getRequest().getCookies();
		if(cookies!=null)
			for(Cookie cookie:cookies)
				if(cookie.getName().equals(name))
					return cookie;
		return null;
	}

	/**
	 * 获取Cookie对象数组.
	 * 
	 * @return Cookie对象数组.
	 */
	public Cookie[] getCookieObjects(){
		Cookie[] result=getRequest().getCookies();
		return result!=null?result:new Cookie[0];
	}

	/**
	 * 通过存储的属性名称从Cookie中获取Cookie对象.
	 * 
	 * @param name
	 *            存储的属性对象名称.
	 * @return 存储的属性对象值.
	 */
	public String getCookie(String name){
		return getCookie(name,null);
	}

	/**
	 * 通过存储的属性名称从Cookie中获取Cookie对象.
	 * 
	 * @param name
	 *            存储的属性对象名称.
	 * @param defaultValue
	 *            默认值.
	 * @return 存储的属性对象值.
	 */
	public String getCookie(String name,String defaultValue){
		Cookie cookie=getCookieObject(name);
		return cookie!=null?cookie.getValue():defaultValue;
	}

	/**
	 * 通过存储的属性名称从Cookie中获取Cookie中存储的整型值.
	 * 
	 * @param name
	 *            存储的属性对象名称.
	 * @return 存储的属性整型值.
	 */
	public Integer getCookieToInt(String name){
		String result=getCookie(name);
		return result!=null?Integer.parseInt(result):null;
	}

	/**
	 * 通过存储的属性名称从Cookie中获取Cookie中存储的整型值.
	 * 
	 * @param name
	 *            存储的属性对象名称.
	 * @param defaultValue
	 *            默认值.
	 * @return 存储的属性整型值.
	 */
	public Integer getCookieToInt(String name,Integer defaultValue){
		String result=getCookie(name);
		return result!=null?Integer.parseInt(result):defaultValue;
	}

	/**
	 * 通过存储的属性名称从Cookie中获取Cookie中存储的Long值.
	 * 
	 * @param name
	 *            存储的属性对象名称.
	 * @return 存储的属性Long值.
	 */
	public Long getCookieToLong(String name){
		String result=getCookie(name);
		return result!=null?Long.parseLong(result):null;
	}

	/**
	 * 通过存储的属性名称从Cookie中获取Cookie中存储的Long值.
	 * 
	 * @param name
	 *            存储的属性对象名称.
	 * @param defaultValue
	 *            默认值.
	 * @return 存储的属性Long值.
	 */
	public Long getCookieToLong(String name,Long defaultValue){
		String result=getCookie(name);
		return result!=null?Long.parseLong(result):defaultValue;
	}

	/**
	 * 设置Cookie对象.
	 * 
	 * @param cookie
	 *            Cookie对象.
	 * @return this.
	 */
	public BaseController setCookie(Cookie cookie){
		getResponse().addCookie(cookie);
		return this;
	}

	/**
	 * 设置Cookie对象.
	 * 
	 * @param name
	 *            cookie名称.
	 * @param value
	 *            cookie值.
	 * @param maxAgeInSeconds
	 *            -1:关闭浏览器时清空Cookie.0:立即清空Cookie.N>0:n秒的最大年龄.
	 * @param path
	 *            应用路径.
	 * @return this.
	 */
	public BaseController setCookie(String name,String value,int maxAgeInSeconds,String path){
		setCookie(name,value,maxAgeInSeconds,path,null);
		return this;
	}

	/**
	 * 设置Cookie对象.
	 * 
	 * @param name
	 *            cookie名称.
	 * @param value
	 *            cookie值.
	 * @param maxAgeInSeconds
	 *            -1:关闭浏览器时清空Cookie.0:立即清空Cookie.N>0:n秒的最大年龄.
	 * @param path
	 *            应用路径.
	 * @param domain
	 *            应用域.
	 * @return this.
	 */
	public BaseController setCookie(String name,String value,int maxAgeInSeconds,String path,String domain){
		Cookie cookie=new Cookie(name,value);
		if(domain!=null)
			cookie.setDomain(domain);
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setPath(path);
		getResponse().addCookie(cookie);
		return this;
	}

	/**
	 * 设置Cookie对象.
	 * 
	 * @param name
	 *            cookie名称.
	 * @param value
	 *            cookie值.
	 * @param maxAgeInSeconds
	 *            -1:关闭浏览器时清空Cookie.0:立即清空Cookie.N>0:n秒的最大年龄.
	 * @return this.
	 */
	public BaseController setCookie(String name,String value,int maxAgeInSeconds){
		setCookie(name,value,maxAgeInSeconds,"/",null);
		return this;
	}

	/**
	 * 通过cookie存储对象名称移除Cookie对象.
	 * 
	 * @param name
	 *            Cookie存储对象名称.
	 * @return this.
	 */
	public BaseController removeCookie(String name){
		setCookie(name,null,0,"/",null);
		return this;
	}

	/**
	 * 通过cookie存储对象名称移除Cookie对象.
	 * 
	 * @param name
	 *            Cookie存储对象名称.
	 * @param path
	 *            应用路径.
	 * @return
	 */
	public BaseController removeCookie(String name,String path){
		setCookie(name,null,0,path,null);
		return this;
	}

	/**
	 * 通过cookie存储对象名称移除Cookie对象.
	 * 
	 * @param name
	 *            Cookie存储对象名称.
	 * @param path
	 *            应用路径.
	 * @param domain
	 *            应用域.
	 * @return this.
	 */
	public BaseController removeCookie(String name,String path,String domain){
		setCookie(name,null,0,path,domain);
		return this;
	}
}
