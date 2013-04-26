package com.webapp.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.webapp.common.tree.DefaultTreeNode;
import com.webapp.common.tree.ITreeNode;
import com.webapp.service.IAppFileService;
import com.webapp.util.Files;
import com.webapp.util.PropertiesLoader;

@Service("impl.appFileServiceImpl")
public class AppFileServiceImpl implements IAppFileService{

	public static final String CODER_CFG_NAME="classpath:/coder.properties";
	public static final String CODER_ROOT_PATH_LABEL="coder.appRootPath";
	public static final String CODER_CFG_PATH_LABEL="coder.coderCfgPath";
	public static final String CODER_FILESUFFIX_LABEL="coder.fileSuffix";
	public static final String CODER_FILEDIRS_LABEL="coder.fileDirs";

	private static Properties prop;

	static{
		prop=new PropertiesLoader(CODER_CFG_NAME).getProperties();
	}

	@Override
	public String getProjectSrcPath(){
		String rootAppPath=prop.getProperty(CODER_ROOT_PATH_LABEL);
		String coderCfgPath=prop.getProperty(CODER_CFG_PATH_LABEL);
		return rootAppPath+File.separator+coderCfgPath;
	}

	@Override
	public List<ITreeNode> loadDir(String parentDir) throws Exception{
		String pix="";
		String fileSuffixStr=prop.getProperty(CODER_FILESUFFIX_LABEL);
		String fileDirsStr=prop.getProperty(CODER_FILEDIRS_LABEL);
		String[] fileSuffix=fileSuffixStr.split(",");
		String[] fileDirs=fileDirsStr.split(",");
		List<String> dirs=Files.getDirectoryList(parentDir);
		List<ITreeNode> results=new ArrayList<ITreeNode>();
		DefaultTreeNode obj;
		for(String dir:dirs){
			if(!Files.isContain(fileDirs,dir)){
				obj=new DefaultTreeNode();
				obj.setId(parentDir+File.separator+dir);
				obj.setText(dir);
				obj.setState("closed");
				obj.setLeaf(false);
				results.add(obj);
			}
		}

		List<String> files=Files.getFileList(parentDir,pix);
		for(String file:files){
			int index=file.lastIndexOf(".");
			if((index!=-1)&&(file.length()>index+1)){
				String suffix=file.substring(index+1);
				if(Files.isContain(fileSuffix,suffix)){
					obj=new DefaultTreeNode();
					obj.setId(parentDir+File.separator+file);
					obj.setText(file);
					obj.setLeaf(true);
					results.add(obj);
				}
			}
		}
		return results;
	}

	@Override
	public void createFile(String fileName,String encode) throws Exception{
		if(fileName.indexOf(".")!=-1){
			FileUtils.writeStringToFile(new File(fileName),"",encode);
		}else{
			FileUtils.forceMkdir(new File(fileName));
		}
	}

	@Override
	public void deleteFile(String fileName) throws Exception{
		FileUtils.forceDelete(new File(fileName));
	}
}
