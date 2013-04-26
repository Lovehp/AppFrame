package com.webapp.service;

import java.util.List;

import com.webapp.common.tree.ITreeNode;

public interface IAppFileService{

	/**
	 * 加载树
	 * 
	 * @param parentDir
	 *            子节点路径
	 * @return
	 * @throws Exception
	 */
	public List<ITreeNode> loadDir(String parentDir) throws Exception;

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @param encode
	 *            编码后的字符串
	 * @throws Exception
	 */
	public void createFile(String fileName,String encode) throws Exception;

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @throws Exception
	 */
	public void deleteFile(String fileName) throws Exception;

	/**
	 * 获取工程存放路径
	 * 
	 * @return
	 */
	public String getProjectSrcPath();
}
