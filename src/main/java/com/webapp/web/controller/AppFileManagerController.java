package com.webapp.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.webapp.common.controller.BaseController;
import com.webapp.service.IAppFileService;

@Controller
@RequestMapping(value="/appFileManager")
public class AppFileManagerController extends BaseController{

	protected final Logger logger=LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("impl.appFileServiceImpl")
	private IAppFileService appFileService;
	
	@RequestMapping(value="/loadDir")
	public void loadDir(HttpServletResponse response) throws Exception{
		String node=getPara("node");
		if((node==null)||("root".equals(node))){
			node=appFileService.getProjectSrcPath();
		}
		returnJson(response,JSON.toJSONString(appFileService.loadDir(node)));
	}

	@RequestMapping(value="/createFile")
	public void createFile(){

	}

	@RequestMapping(value="/delFile")
	public void delFile(){

	}
}
