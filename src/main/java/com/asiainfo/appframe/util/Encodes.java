package com.asiainfo.appframe.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 封装各种格式的编码解码工具类
 * 
 * @author luhf
 * @date 2013-4-13 上午12:30:49
 */
public class Encodes{

	private static final String DEFAULT_URL_ENCODING="UTF-8";
	private static final char[] BASE62="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * Hex编码.
	 * 
	 * @param input
	 *            字节数组.
	 * @return Hex编码字符串.
	 */
	public static String encodeHex(byte[] input){
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 * 
	 * @param input
	 *            Hex编码字符串.
	 * @return 字节数组.
	 */
	public static byte[] decodeHex(String input){
		try{
			return Hex.decodeHex(input.toCharArray());
		}catch(DecoderException e){
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * Base64编码.
	 * 
	 * @param input
	 *            字节数组.
	 * @return Base64编码字符串.
	 */
	public static String encodeBase64(byte[] input){
		return Base64.encodeBase64String(input);
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 * 
	 * @param input
	 *            字节数组.
	 * @return Base64URL编码字符串.
	 */
	public static String encodeUrlSafeBase64(byte[] input){
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 * 
	 * @param input
	 *            Base64编码字符串.
	 * @return 字节数组.
	 */
	public static byte[] decodeBase64(String input){
		return Base64.decodeBase64(input);
	}

	/**
	 * Base62编码.
	 * 
	 * @param input
	 *            字节数组.
	 * @return Base62编码字符串.
	 */
	public static String encodeBase62(byte[] input){
		char[] chars=new char[input.length];
		for(int i=0;i<input.length;i++){
			chars[i]=BASE62[((input[i]&0xFF)%BASE62.length)];
		}
		return new String(chars);
	}

	/**
	 * Html 转码.
	 * 
	 * @param html
	 *            Html字符串.
	 * @return 转码后的html字符串.
	 */
	public static String escapeHtml(String html){
		return StringEscapeUtils.escapeHtml4(html);
	}

	/**
	 * Html 解码.
	 * 
	 * @param htmlEscaped
	 *            转码后的html字符串.
	 * @return Html字符串.
	 */
	public static String unescapeHtml(String htmlEscaped){
		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 * 
	 * @param xml
	 *            Xml字符串.
	 * @return 转码后的Xml字符串.
	 */
	public static String escapeXml(String xml){
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 解码.
	 * 
	 * @param xmlEscaped
	 *            转码后的Xml字符串.
	 * @return Xml字符串.
	 */
	public static String unescapeXml(String xmlEscaped){
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 * 
	 * @param part
	 *            URL字符串
	 * @return 编码后的字符串.
	 */
	public static String urlEncode(String part){
		try{
			return URLEncoder.encode(part,DEFAULT_URL_ENCODING);
		}catch(UnsupportedEncodingException e){
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 * 
	 * @param part
	 *            编码后的字符串.
	 * @return URL字符串
	 */
	public static String urlDecode(String part){
		try{
			return URLDecoder.decode(part,DEFAULT_URL_ENCODING);
		}catch(UnsupportedEncodingException e){
			throw Exceptions.unchecked(e);
		}
	}
}
