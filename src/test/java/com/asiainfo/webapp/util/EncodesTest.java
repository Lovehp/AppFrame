package com.asiainfo.webapp.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.appframe.util.Encodes;

public class EncodesTest{

	protected final Logger logger=LoggerFactory.getLogger(getClass());

	@Test
	public void hexEncode(){
		String input="haha,i am a very long message";
		String result=Encodes.encodeHex(input.getBytes());
		logger.debug("hexEncode() :"+result);
		assertEquals(input,new String(Encodes.decodeHex(result)));
	}

	@Test
	public void base64Encode(){
		String input="haha,i am a very long message";
		String result=Encodes.encodeBase64(input.getBytes());
		logger.debug("base64Encode() :"+result);
		assertEquals(input,new String(Encodes.decodeBase64(result)));
	}

	@Test
	public void base64UrlSafeEncode(){
		String input="haha,i am a very long message";
		String result=Encodes.encodeUrlSafeBase64(input.getBytes());
		logger.debug("base64UrlSafeEncode() :"+result);
		assertEquals(input,new String(Encodes.decodeBase64(result)));
	}

	@Test
	public void urlEncode(){
		String input="http://locahost/?q=中文&t=1";
		String result=Encodes.urlEncode(input);
		logger.debug("urlEncode() :"+result);
		assertEquals(input,Encodes.urlDecode(result));
	}

	@Test
	public void xmlEncode(){
		String input="1>2";
		String result=Encodes.escapeXml(input);
		logger.debug("xmlEncode() :"+result);
		assertEquals("1&gt;2",result);
		assertEquals(input,Encodes.unescapeXml(result));
	}

	@Test
	public void html(){
		String input="1>2";
		String result=Encodes.escapeHtml(input);
		logger.debug("html() :"+result);
		assertEquals("1&gt;2",result);
		assertEquals(input,Encodes.unescapeHtml(result));
	}
}
