package com.webapp.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.webapp.common.controller.BaseController;
import com.webapp.util.Files;

@Controller
@RequestMapping(value="/javaGenManager")
public class JavaGenManagerController extends BaseController{

	protected final Logger logger=LoggerFactory.getLogger(getClass());

	@RequestMapping(value="/getJavaBeanInfo")
	@ResponseBody
	public Object getJavaBeanInfo() throws Exception{
		String jsonContent=Files.readFileString(getPara("fileName"),"utf-8");
		if(!StringUtils.isEmpty(jsonContent)){
			return JSON.parse(jsonContent);
		}else{
			return "";
		}
	}
}
