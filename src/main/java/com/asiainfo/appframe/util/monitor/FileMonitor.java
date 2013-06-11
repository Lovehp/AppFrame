package com.asiainfo.appframe.util.monitor;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * 文件监控
 * @author luhf
 * @date 2013-6-11 下午5:59:32
 */
public class FileMonitor{
	
	private FileAlterationMonitor monitor=null;

	/**
	 * 默认10妙看一次
	 * 
	 * @param ob
	 */
	public FileMonitor(FileAlterationObserver ob){
		this(10000,ob);
	}

	/**
	 * 每隔多少时候看一次,观察者
	 * 
	 * @param fileName
	 * @param ob
	 */
	public FileMonitor(long interval,FileAlterationObserver ob){
		monitor=new FileAlterationMonitor(interval,new FileAlterationObserver[]{ob});
	}

	/**
	 * 添加观察者
	 * 
	 * @param observer
	 */
	public void addObserver(FileAlterationObserver observer){
		monitor.addObserver(observer);
	}

	/**
	 * 移除观察者
	 * 
	 * @param observer
	 */
	public void removeObserver(FileAlterationObserver observer){
		monitor.removeObserver(observer);
	}

	/**
	 * 获取所有观察者
	 * 
	 * @return
	 */
	public Iterable<FileAlterationObserver> getObservers(){
		return monitor.getObservers();
	}

	/**
	 * 启动监控[observer.initialize()]
	 */
	public void start(){
		try{
			monitor.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 停止监控[observer.destroy()]
	 */
	public void stop(){
		try{
			monitor.stop();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * [不做调用] 具体的监控操作: observer.checkAndNotify()
	 */
	@SuppressWarnings("unused")
	private void run(){
		monitor.run();
	}
}
