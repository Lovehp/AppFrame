package com.asiainfo.appframe.util.command;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行
 * 
 * @author luhf
 * @date 2013-6-11 下午6:01:44
 */
public abstract class ExecCommand {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 在单独的进程中执行指定的字符串命令。
	 * 
	 * @param command
	 *            一条指定的系统命令
	 * @throws IOException
	 */
	public void exec(String command) throws IOException {
		exec(command, null, null);
	}

	/**
	 * 在有指定工作目录的独立进程中执行指定的字符串命令。
	 * 
	 * @param command
	 *            一条指定的系统命令
	 * @param workpath
	 *            子进程的工作目录；如果子进程应该继承当前进程的工作目录，则该参数为null。
	 * @throws IOException
	 */
	public void exec(String command, String workpath) throws IOException {
		exec(command, null, workpath);
	}

	/**
	 * 在有指定环境和工作目录的独立进程中执行指定的字符串命令。
	 * 
	 * @param command
	 *            一条指定的系统命令
	 * @param envp
	 *            环境变量，字符串数组，其中每个元素的环境变量设置格式为
	 *            name=value；如果子进程应该继承当前进程的环境，则为null。
	 * @param path
	 *            子进程的工作目录；如果子进程应该继承当前进程的工作目录，则该参数为null。
	 * @throws IOException
	 */
	public void exec(String command, String[] envp, String workpath)
			throws IOException {
		InputStream is = null;
		BufferedInputStream in = null;
		BufferedReader br = null;
		try {
			File dir = null;
			if (null != workpath) {
				dir = new File(workpath);
			}
			log.info("【COMMAND】>>> " + command);
			is = Runtime.getRuntime().exec(command, envp, dir).getInputStream();
			in = new BufferedInputStream(is);
			br = new BufferedReader(new InputStreamReader(in));
			String ss = "";
			while ((ss = br.readLine()) != null) {
				lineHandler(ss);
			}
		} finally {
			if (null != br) {
				br.close();
			}
			if (null != in) {
				in.close();
			}
			if (null != is) {
				is.close();
			}
		}
	}

	/**
	 * 在单独的进程中执行指定的命令和参数。
	 * 
	 * @param commands
	 *            包含所调用命令及其参数的数组。例如：new
	 *            String[]{"/home/user1/test.sh","arg1","arg2"};
	 * @throws IOException
	 */
	public void exec(String[] commands) throws IOException {
		exec(commands, null, null);
	}

	/**
	 * 在有指定工作目录的独立进程中执行指定的字符串命令。
	 * 
	 * @param commands
	 *            包含所调用命令及其参数的数组。例如：new
	 *            String[]{"/home/user1/test.sh","arg1","arg2"};
	 * @param workpath
	 *            子进程的工作目录；如果子进程应该继承当前进程的工作目录，则该参数为null。
	 * @throws IOException
	 */
	public void exec(String[] commands, String workpath) throws IOException {
		exec(commands, null, workpath);
	}

	/**
	 * 在有指定环境和工作目录的独立进程中执行指定的字符串命令。
	 * 
	 * @param commands
	 *            包含所调用命令及其参数的数组。例如：new
	 *            String[]{"/home/user1/test.sh","arg1","arg2"};
	 * @param envp
	 *            环境变量，字符串数组，其中每个元素的环境变量设置格式为
	 *            name=value；如果子进程应该继承当前进程的环境，则为null。
	 * @param path
	 *            子进程的工作目录；如果子进程应该继承当前进程的工作目录，则该参数为null。
	 * @throws IOException
	 */
	public void exec(String[] commands, String[] envp, String workpath)
			throws IOException {
		InputStream is = null;
		BufferedInputStream in = null;
		BufferedReader br = null;
		try {
			File dir = null;
			if (null != workpath) {
				dir = new File(workpath);
			}
			log.info("【COMMAND】>>>：" + getCommandString(commands));
			is = Runtime.getRuntime().exec(commands, envp, dir)
					.getInputStream();
			in = new BufferedInputStream(is);
			br = new BufferedReader(new InputStreamReader(in));
			String ss = "";
			while ((ss = br.readLine()) != null) {
				lineHandler(ss);
			}
		} finally {
			if (null != br) {
				br.close();
			}
			if (null != in) {
				in.close();
			}
			if (null != is) {
				is.close();
			}
		}
	}

	/**
	 * 仅为日志输出，无其他作用
	 * 
	 * @param commands
	 * @return
	 */
	private String getCommandString(String[] commands) {
		StringBuffer sb = new StringBuffer();
		for (String command : commands) {
			sb.append(command);
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * 行处理
	 * 
	 * @param lineStr
	 */
	protected abstract void lineHandler(String lineStr);
}
