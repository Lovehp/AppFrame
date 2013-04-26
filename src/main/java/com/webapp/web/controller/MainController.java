package com.webapp.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.common.controller.BaseController;
import com.webapp.service.SysRoleService;

/**
 * 主控制器
 * 
 * @author luhf
 * @date Jan 7, 2013 12:44:51 PM
 */
@Controller
public class MainController extends BaseController{

	protected final Logger logger=LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("impl.sysRoleServiceImpl")
	private SysRoleService sysRoleService;

	@RequestMapping(value="/")
	public String index(){
		setAttr("root",getRequest().getContextPath());
		setAttr("title","首页");
		setAttr("keywords","开源,开源软件,开源网站,开源社区,开源中国社区,java开源,perl开源,python开源,ruby开源,php开源,开源项目,开源代码");
		setAttr("description","开源中国 www.oschina.net 是目前中国最大的开源技术社区。我们传播开源的理念，推广开源项目，为 IT 开发者提供了一个发现、使用、并交流开源技术的平台。目前开源中国社区已收录超过两万款开源软件。");
		return "index";
	}

	@RequestMapping(value="/main")
	@ResponseBody
	public Map<String,Object> main(int page,int rows){
		 return sysRoleService.getSysRoleList(rows,page);
	}
}
