package com.e59.test;


import java.util.Vector;

import org.junit.Test;

/**
 * 测试Vector的删除操作
 *
 * @author  Lutong99
 *
 */
public class TestVector {
	@Test
	public void testVector() throws Exception {
		
		Vector<String> vector = new Vector<>();
		vector.add("Jack");
		vector.add("Guangtong");
		vector.add("Jiangge");
		vector.add("Pengfei");
		vector.add("Zongzheng");
		vector.add("Lingzhi");
		
		System.out.println(vector);
		System.out.println("index 3 is " + vector.get(3));
		vector.remove(3);
		
		System.out.println("after removing index 3 is " + vector.get(3));
		
	}
}
