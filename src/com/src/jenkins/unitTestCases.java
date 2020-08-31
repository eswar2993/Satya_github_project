package com.src.jenkins;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class unitTestCases {

	
	@BeforeSuite
	public void beforeSuiteCase(){
		System.out.println("Before Suite");
	}
	
	@BeforeGroups
	public void beforeGroupsCase(){
		System.out.println("Before Groups");
	}
	
	@BeforeClass
	public void beforeClassCase(){
		System.out.println("Before Class");
	}
	
	@BeforeTest
	public void beforeTestCase(){
		System.out.println("Before Test");
	}
	
	@BeforeMethod
	public void beforeMethodCase(){
		System.out.println("Before Method");
	}
	
	@Test
	public void addition(){
		System.out.println("Sum of values"+(2+3));
	}
	
	@Test
	public void multiplication(){
		System.out.println("Multiplication of values"+(3*2));
	}
	
	@Test
	public void assertion(){
		Assert.assertEquals("Equals", "Equals");
	}
	
	
}
