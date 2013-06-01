package com.asiainfo.appframe.common.tree;

import java.util.Map;

public interface ITreeNode{

	public String getId();

	public String getPid();

	public String getText();

	public boolean isLeaf();

	@SuppressWarnings("rawtypes")
	public Map getAttributes();

	public String[] getAttributeNames();
}
