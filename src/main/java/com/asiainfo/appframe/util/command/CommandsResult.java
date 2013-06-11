package com.asiainfo.appframe.util.command;

/**
 * 对象输出结果错误信息
 * 
 * @author luhf
 * @date 2013-6-11 下午6:01:39
 */
public class CommandsResult {

	public static final int EXIT_VALUE_TIMEOUT = -1;

	private String output;
	private int exitValue;
	private String error;

	public int getExitValue() {
		return exitValue;
	}

	public void setExitValue(int exitValue) {
		this.exitValue = exitValue;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
