package com.qa.TestCase.general;

import org.testng.annotations.Test;

public class TestNgPriority 
{
	@Test(priority = 2)
	public void test1() {
		System.out.println("Inside Test1");
	}
	
	@Test(priority = -1)
	public void test2() {
		System.out.println("Inside Test2");
	}
	
	@Test(priority = -1)
	public void test0() {
		System.out.println("Inside Test3");
	}
}
