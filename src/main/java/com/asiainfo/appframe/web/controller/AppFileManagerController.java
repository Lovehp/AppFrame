package com.asiainfo.appframe.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asiainfo.appframe.common.controller.BaseController;
import com.asiainfo.appframe.service.IAppFileService;

@Controller
@RequestMapping(value="/appFileManager")
public class AppFileManagerController extends BaseController{

	protected final Logger logger=LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("impl.appFileServiceImpl")
	private IAppFileService appFileService;
	
	@RequestMapping(value="/loadDir")
	@ResponseBody
	public Object loadDir() throws Exception{
		String node=getPara("node");
		if((node==null)||("root".equals(node))){
			node=appFileService.getProjectSrcPath();
		}
		return appFileService.loadDir(node);
	}

	@RequestMapping(value="/createFile")
	public void createFile(){

	}

	@RequestMapping(value="/delFile")
	public void delFile(){

	}
}
