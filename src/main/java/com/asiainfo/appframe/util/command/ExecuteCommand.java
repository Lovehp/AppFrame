package com.asiainfo.appframe.util.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行命令行
 * @author luhf
 * @date 2013-6-11 下午6:03:33
 */
public class ExecuteCommand extends ExecCommand {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 执行命令返回行内容处理默认直接输出到日志,如果需要自定义处理请覆盖此方法
	 */
	@Override
	protected void lineHandler(String lineStr) {
		logger.info(lineStr);
	}

	public static void main(String[] args) throws Exception {
		// 默认处理
		ExecCommand exe1 = new ExecuteCommand();
		exe1.exec("c:\\ls.bat");

		// 自定义处理
		ExecCommand exe2 = new ExecuteCommand() {
			@Override
			protected void lineHandler(String lineStr) {
				System.out.println(lineStr);
			}
		};
		exe2.exec("c:\\ls.bat", "c:\\");

		// 多个参数
		ExecCommand exe3 = new ExecuteCommand();
		exe3.exec(new String[] { "ping", "127.0.0.1" });
	}
}