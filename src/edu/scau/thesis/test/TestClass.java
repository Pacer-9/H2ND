package edu.scau.thesis.test;

import java.util.Date;

import org.junit.Test;

public class TestClass {
	@Test
	public void test1(){
		String newFileName = new Date().getTime()+"M"+(int)(Math.random()*1000)+".jpg";
		System.out.println(newFileName);
	}

}
