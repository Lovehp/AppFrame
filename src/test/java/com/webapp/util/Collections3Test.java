package com.webapp.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class Collections3Test{

	@Test
	public void convertElementPropertyToString(){
		TestBean3 bean1=new TestBean3();
		bean1.setId(1);
		TestBean3 bean2=new TestBean3();
		bean2.setId(2);
		List<TestBean3> list=Lists.newArrayList(bean1,bean2);
		assertEquals("1,2",Collections3.extractToString(list,"id",","));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void convertElementPropertyToList(){
		TestBean3 bean1=new TestBean3();
		bean1.setId(1);
		TestBean3 bean2=new TestBean3();
		bean2.setId(2);
		List<TestBean3> list=Lists.newArrayList(bean1,bean2);
		List<String> result=Collections3.extractToList(list,"id");
		assertEquals(2,result.size());
		assertEquals(1,result.get(0));
	}

	@Test
	public void convertCollectionToString(){
		List<String> list=Lists.newArrayList("aa","bb");
		String result=Collections3.convertToString(list,",");
		assertEquals("aa,bb",result);
		result=Collections3.convertToString(list,"<li>","</li>");
		assertEquals("<li>aa</li><li>bb</li>",result);
	}

	public static class TestBean3{
		private int id;

		public int getId(){
			return id;
		}

		public void setId(int id){
			this.id=id;
		}
	}
}
