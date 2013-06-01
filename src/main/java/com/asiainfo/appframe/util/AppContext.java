package com.asiainfo.appframe.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * SpringContext工具类
 * 
 * @author luhf
 * @date 2013-4-20 下午11:51:51
 */
@Component("appContext")
public class AppContext implements ApplicationContextAware{

	protected final static Logger logger=LoggerFactory.getLogger(AppContext.class);

	private static ApplicationContext ctx;
	
	static{
		logger.debug("启动SpringContext工具类!");
	}
	
	/**
	 * 私用构造主法.因为此类是工具类.
	 */
	public AppContext(){}

	public static ApplicationContext getApplicationContext(){
		return ctx==null?AppContextImpl.getApplicationContext():ctx;
	}

	public static <T>T getBean(Class<T> clz){
		return getApplicationContext().getBean(clz);
	}

	public static <T>T getBean(String name,Class<T> clz){
		return getApplicationContext().getBean(name,clz);
	}

	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		logger.debug("初始化应用上下文入口类");
		ctx=applicationContext;
		if(logger.isInfoEnabled()){
			String[] names=applicationContext.getBeanDefinitionNames();
			StringBuilder sb=new StringBuilder("以下为Spring容器中的服务列表（").append(names.length).append("):");
			for(int i=0;i<names.length;i++){
				String[] aliases=applicationContext.getAliases(names[i]);
				sb.append("\r\n\t[").append(i).append("]").append(names[i]);
				if(aliases.length>0){
					sb.append("[").append(aliases[0]);
					for(int j=1;j<aliases.length;j++){
						sb.append(",").append(aliases[j]);
					}
					sb.append("]");
				}
				sb.append("\t:\t").append(applicationContext.getType(names[i]));
			}
			logger.debug(sb.toString());
		}
	}

	private static class AppContextImpl{
		
		private static ApplicationContext ctx;
		private static Object ctxLocker=new Object();

		public static ApplicationContext getApplicationContext(){
			synchronized(ctxLocker){
				if(ctx==null){
					AppContext.logger.warn("Spring容器尚末通过Web容器进行初始化，将以独立应用的形式初始化Spring容器。如果您正在Web容器中运行应用，这可能是一个错误，\n请检查配置文件以修正此错误；如果您确认这不是错误（如正在运行测试、正在EJB容器中运行应用、正在运行独立应用等），请忽略本提示。");
					ctx=new ClassPathXmlApplicationContext("frame-config.xml");
					AppContext.ctx=ctx;
				}
			}
			return ctx;
		}
	}
}
