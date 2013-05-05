package com.webapp.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作工具类
 * 
 * @author luhf
 * @date 2013-4-24 下午9:16:15
 */
public class Files{

	protected final static Logger logger=LoggerFactory.getLogger(Files.class);

	public Files(){
		super();
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	public static boolean exist(String filePath) throws Exception{
		return readFile(filePath).exists();
	}

	/**
	 * 创建目录
	 * 
	 * @param dir
	 *            目录
	 */
	public static void mkdir(String dir){
		try{
			String dirTemp=dir;
			File dirfilePath=readFile(dirTemp);
			if(!dirfilePath.exists()){
				dirfilePath.mkdir();
			}
		}catch(Exception e){
			logger.error("创建目录操作出错: "+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param fileName
	 *            包含路径的文件名 如:E:\phsftp\src\123.txt
	 * @param content
	 *            文件内容
	 */
	public static void createNewFile(String fileName,String content){
		try{
			FileUtils.write(readFile(fileName),content);
		}catch(Exception e){
			logger.error("新建文件操作出错: "+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 根据路径来删除文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @throws Exception
	 */
	public static void delete(String filePath) throws Exception{
		File dest=readFile(filePath);
		try{
			FileUtils.forceDelete(dest);
		}catch(Exception e){
			logger.error("删除文件操作出错: "+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderfilePath
	 *            文件夹路径
	 */
	public static void delFolder(String folderfilePath){
		try{
			FileUtils.deleteDirectory(readFile(folderfilePath));
		}catch(Exception e){
			logger.error("删除文件夹操作出错: "+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param filePath
	 *            文件夹路径
	 */
	public static void delAllFile(String filePath){
		try{
			FileUtils.cleanDirectory(readFile(filePath));
		}catch(Exception e){
			logger.error("删除文件夹里所有文件操作出错: "+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param srcFile
	 *            包含路径的源文件 如：E:/phsftp/src/abc.txt
	 * @param dirDest
	 *            目标文件目录；若文件目录不存在则自动创建 如：E:/phsftp/dest
	 */
	public static void copyFile(String srcFile,String dirDest){
		try{
			FileUtils.copyFile(readFile(srcFile),readFile(dirDest));
		}catch(Exception e){
			logger.error("复制文件操作出错:"+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件夹
	 * 
	 * @param oldfilePath
	 *            源文件夹路径 如：E:/phsftp/src
	 * @param newfilePath
	 *            目标文件夹路径 如：E:/phsftp/dest
	 */
	public static void copyFolder(String oldfilePath,String newfilePath){
		try{
			FileUtils.copyDirectory(readFile(oldfilePath),readFile(newfilePath));
		}catch(Exception e){
			logger.error("复制文件夹操作出错:"+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldfilePath
	 *            包含路径的文件名 如：E:/phsftp/src/ljq.txt
	 * @param newfilePath
	 *            目标文件目录 如：E:/phsftp/dest
	 */
	public static void moveFile(String oldfilePath,String newfilePath){
		try{
			FileUtils.moveFileToDirectory(readFile(oldfilePath),readFile(newfilePath),true);
		}catch(Exception e){
			logger.error("移动文件操作出错:"+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 移动文件到指定目录，删除文件夹
	 * 
	 * @param oldfilePath
	 *            源文件目录 如：E:/phsftp/src
	 * @param newfilePath
	 *            目标文件目录 如：E:/phsftp/dest
	 */
	public static void moveFolder(String oldfilePath,String newfilePath){
		try{
			FileUtils.moveDirectoryToDirectory(readFile(oldfilePath),readFile(newfilePath),true);
		}catch(Exception e){
			logger.error("移动文件夹操作出错:"+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 解压zip文件
	 * 
	 * @param srcDir
	 *            解压前存放的目录
	 * @param destDir
	 *            解压后存放的目录
	 * @throws Exception
	 */
	public static void unescapeZip(String srcDir,String destDir) throws Exception{
		int leng=0;
		byte[] b=new byte[1024*2];
		/** 获取zip格式的文件 **/
		File[] zipFiles=new File(srcDir).listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir,String name){
				if(name.contains("zip")){
					return true;
				}else{
					return false;
				}
			}
		});
		if(zipFiles!=null&&!"".equals(zipFiles)){
			for(int i=0;i<zipFiles.length;i++){
				File file=zipFiles[i];
				/** 解压的输入流 * */
				ZipInputStream zis=new ZipInputStream(new FileInputStream(file));
				ZipEntry entry=null;
				while((entry=zis.getNextEntry())!=null){
					File destFile=null;
					if(destDir.endsWith(File.separator)){
						destFile=new File(destDir+entry.getName());
					}else{
						destFile=new File(destDir+"/"+entry.getName());
					}
					/** 把解压包中的文件拷贝到目标目录 * */
					FileOutputStream fos=new FileOutputStream(destFile);
					while((leng=zis.read(b))!=-1){
						fos.write(b,0,leng);
					}
					fos.close();
				}
				zis.close();
			}
		}
	}

	/**
	 * 压缩文件
	 * 
	 * @param srcDir
	 *            压缩前存放的目录
	 * @param destDir
	 *            压缩后存放的目录
	 * @throws Exception
	 */
	public static void escapeZip(String srcDir,String destDir) throws Exception{
		String tempFileName=null;
		byte[] buf=new byte[1024*2];
		int len; // 获取要压缩的文件
		File[] files=new File(srcDir).listFiles();
		if(files!=null){
			for(File file:files){
				if(file.isFile()){
					FileInputStream fis=new FileInputStream(file);
					BufferedInputStream bis=new BufferedInputStream(fis);
					if(destDir.endsWith(File.separator)){
						tempFileName=destDir+file.getName()+".zip";
					}else{
						tempFileName=destDir+"/"+file.getName()+".zip";
					}
					FileOutputStream fos=new FileOutputStream(tempFileName);
					BufferedOutputStream bos=new BufferedOutputStream(fos);
					ZipOutputStream zos=new ZipOutputStream(bos);// 压缩包
					ZipEntry ze=new ZipEntry(file.getName());// 压缩包文件名
					zos.putNextEntry(ze);// 写入新的ZIP文件条目并将流定位到条目数据的开始处
					while((len=bis.read(buf))!=-1){
						zos.write(buf,0,len);
						zos.flush();
					}
					bis.close();
					zos.close();
				}
			}
		}
	}

	/**
	 * 读取数据
	 * 
	 * @param inSream
	 *            输入流
	 * @param charsetName
	 *            写入的内容
	 * @return
	 * @throws Exception
	 */
	public static String readData(InputStream inSream,String charsetName) throws Exception{
		ByteArrayOutputStream outStream=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int len=-1;
		while((len=inSream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		byte[] data=outStream.toByteArray();
		outStream.close();
		inSream.close();
		return new String(data,charsetName);
	}

	/**
	 * 一行一行读取文件，适合字符读取，若读取中文字符时会出现乱码
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	public static Set<String> readFileSet(String filePath) throws Exception{
		Set<String> datas=new HashSet<String>();
		FileReader fr=new FileReader(filePath);
		BufferedReader br=new BufferedReader(fr);
		String line=null;
		while((line=br.readLine())!=null){
			datas.add(line);
		}
		br.close();
		fr.close();
		return datas;
	}

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param encoding
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String readFileString(String filePath,String encoding) throws Exception{
		return FileUtils.readFileToString(readFile(filePath),encoding);
	}

	/**
	 * 读取文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	public static File readFile(String filePath) throws Exception{
		return new File(filePath);
	}

	/**
	 * 读取文件数据
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	public FileInputStream read(String filePath) throws Exception{
		return new FileInputStream(filePath);
	}

	/**
	 * 获取路径下所有文件集合
	 * 
	 * @param filefilePath
	 *            文件路径
	 * @return
	 */
	public static List<String> getDirectoryList(String filePath){
		File dir=new File(filePath);
		File[] list=dir.listFiles();
		List<String> dirs=new ArrayList<String>();
		if(list!=null){
			for(int i=0;i<list.length;i++){
				// 判断是否是文件目录
				if(list[i].isDirectory()){
					dirs.add(list[i].getName());
				}
			}
		}
		return dirs;
	}

	/**
	 * 获取路径下所有文件集合
	 * 
	 * @param filePath
	 *            文件路径
	 * @param pix
	 *            检查项
	 * @return
	 */
	public static List<String> getFileList(String filePath,String pix){
		File dir=new File(filePath);
		File[] list=dir.listFiles();
		List<String> dirs=new ArrayList<String>();
		if(list!=null){
			for(int i=0;i<list.length;i++){
				// 判断是否是文件&&包含文件名称包含指定检查项
				if((list[i].isFile())&&(list[i].getName().indexOf(pix)!=-1)){
					dirs.add(list[i].getName());
				}
			}
		}
		return dirs;
	}

	/**
	 * 文件名称里包含的后缀
	 * 
	 * @param fileSuffix
	 *            文件后缀名称数组
	 * @param suffix
	 *            后缀名称
	 * @return
	 */
	public static boolean isContain(String[] fileSuffix,String suffix){
		if(fileSuffix!=null){
			for(String element:fileSuffix){
				if(element.equals(suffix)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 递归查找文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 * @param targetFileName
	 *            需要查找的文件名
	 * @param fileList
	 *            查找到的文件集合
	 */
	public static void findFiles(String baseDirName,String targetFileName,List<File> fileList){
		/**
		 * 算法简述： 从某个给定的需查找的文件夹出发，搜索该文件夹的所有子文件夹及文件，
		 * 若为文件，则进行匹配，匹配成功则加入结果集，若为子文件夹，则进队列。 队列不空，重复上述操作，队列为空，程序结束，返回结果。
		 */
		String tempName=null;
		// 判断目录是否存在
		File baseDir=new File(baseDirName);
		if(!baseDir.exists()||!baseDir.isDirectory()){
			System.out.println("文件查找失败："+baseDirName+"不是一个目录！");
		}else{
			String[] filelist=baseDir.list();
			for(int i=0;i<filelist.length;i++){
				File readfile=new File(baseDirName+"\\"+filelist[i]);
				// System.out.println(readfile.getName());
				if(!readfile.isDirectory()){
					tempName=readfile.getName();
					if(wildcardMatch(targetFileName,tempName)){
						// 匹配成功，将文件名添加到结果集
						fileList.add(readfile.getAbsoluteFile());
					}
				}else if(readfile.isDirectory()){
					findFiles(baseDirName+"\\"+filelist[i],targetFileName,fileList);
				}
			}
		}
	}

	/**
	 * 通配符匹配
	 * 
	 * @param pattern
	 *            通配符模式
	 * @param str
	 *            待匹配的字符串
	 * @return 匹配成功则返回true，否则返回false
	 */
	private static boolean wildcardMatch(String pattern,String str){
		int patternLength=pattern.length();
		int strLength=str.length();
		int strIndex=0;
		char ch;
		for(int patternIndex=0;patternIndex<patternLength;patternIndex++){
			ch=pattern.charAt(patternIndex);
			if(ch=='*'){
				// 通配符星号*表示可以匹配任意多个字符
				while(strIndex<strLength){
					if(wildcardMatch(pattern.substring(patternIndex+1),str.substring(strIndex))){
						return true;
					}
					strIndex++;
				}
			}else if(ch=='?'){
				// 通配符问号?表示匹配任意一个字符
				strIndex++;
				if(strIndex>strLength){
					// 表示str中已经没有字符匹配?了。
					return false;
				}
			}else{
				if((strIndex>=strLength)||(ch!=str.charAt(strIndex))){
					return false;
				}
				strIndex++;
			}
		}
		return(strIndex==strLength);
	}

	// public static void main(String[] paramert){
	// // 在此目录中找文件
	// String baseDIR="d:/mywork";
	// // 找扩展名为txt的文件
	// String fileName="*.txt";
	// List<File> resultList=new ArrayList<File>();
	// findFiles(baseDIR,fileName,resultList);
	// if(resultList.size()==0){
	// System.out.println("No File Fount.");
	// }else{
	// for(int i=0;i<resultList.size();i++){
	// System.out.println(resultList.get(i));// 显示查找结果。
	// }
	// }
	// }

}
