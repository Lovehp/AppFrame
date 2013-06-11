package com.asiainfo.appframe.util.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 命令工具类
 * 
 * @author luhf
 * @date 2013-4-20 下午11:51:51
 */
public class Commands {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public static int DEFAULT_TIMEOUT;
	public static final int DEFAULT_INTERVAL = 1000;
	public static long START;

	public static CommandsResult exec(String command) throws IOException,
			InterruptedException {
		Process process = Runtime.getRuntime().exec(command);
		CommandsResult commandResult = wait(process);
		if (process != null) {
			process.destroy();
		}
		return commandResult;
	}

	private static boolean isOverTime() {
		return System.currentTimeMillis() - START >= DEFAULT_TIMEOUT;
	}

	private static CommandsResult wait(Process process)
			throws InterruptedException, IOException {
		BufferedReader errorStreamReader = null;
		BufferedReader inputStreamReader = null;
		try {
			errorStreamReader = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			inputStreamReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			// timeout control
			START = System.currentTimeMillis();
			boolean isFinished = false;

			for (;;) {
				if (isOverTime()) {
					CommandsResult result = new CommandsResult();
					result.setExitValue(CommandsResult.EXIT_VALUE_TIMEOUT);
					result.setOutput("Command process timeout");
					return result;
				}
				if (isFinished) {
					CommandsResult result = new CommandsResult();
					result.setExitValue(process.waitFor());
					// parse error info
					if (errorStreamReader.ready()) {
						StringBuilder buffer = new StringBuilder();
						String line;
						while ((line = errorStreamReader.readLine()) != null) {
							buffer.append(line);
						}
						result.setError(buffer.toString());
					}
					// parse info
					if (inputStreamReader.ready()) {
						StringBuilder buffer = new StringBuilder();
						String line;
						while ((line = inputStreamReader.readLine()) != null) {
							buffer.append(line);
						}
						result.setOutput(buffer.toString());
					}
					return result;
				}
				try {
					isFinished = true;
					process.exitValue();
				} catch (IllegalThreadStateException e) {
					// process hasn't finished yet
					isFinished = false;
					Thread.sleep(DEFAULT_INTERVAL);
				}
			}
		} finally {
			if (errorStreamReader != null) {
				try {
					errorStreamReader.close();
				} catch (IOException e) {
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			if (args.length == 0) {
				args = new String[] { "5000" };
			}
			int timeout = Integer.parseInt(args[0]);
			Commands.DEFAULT_TIMEOUT = timeout;
			// CommandsResult result=Commands.exec("mkdir testdir");
			CommandsResult result = Commands.exec("pwd");
			if (result != null) {
				System.out.println("Output:" + result.getOutput());
				System.out.println("Error:" + result.getError());
			}
		} catch (IOException ex) {
			System.out.println("IOException:" + ex.getLocalizedMessage());
		} catch (InterruptedException ex) {
			System.out.println("InterruptedException:"
					+ ex.getLocalizedMessage());
		}
	}

}