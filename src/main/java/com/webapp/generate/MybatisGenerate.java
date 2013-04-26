package com.webapp.generate;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MybatisGenerate{

	protected final static Logger logger=LoggerFactory.getLogger(MybatisGenerate.class);

	public static void generate() throws InvalidConfigurationException, IOException, XMLParserException, SQLException, InterruptedException{
		String pathSptor=System.getProperty("path.separator");
		String filePath=System.getProperty("java.class.path");
		filePath=filePath.substring(0,filePath.indexOf(pathSptor))+File.separator+"mybatis-generator-config.xml";

		List<String> warnings=new ArrayList<String>();
		boolean overwrite=true;
		logger.info(filePath);

		File configFile=new File(filePath);
		ConfigurationParser cp=new ConfigurationParser(warnings);
		Configuration config=cp.parseConfiguration(configFile);

		DefaultShellCallback callback=new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator=new MyBatisGenerator(config,callback,warnings);
		myBatisGenerator.generate(null);
		for(String w:warnings){
			logger.info("w :"+w);
		}
	}

	public static void main(String[] args) throws InvalidConfigurationException, IOException, XMLParserException, SQLException, InterruptedException{
			generate();
	}

}
