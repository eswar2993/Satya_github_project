package com.src.jenkins;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.TestResult;

public class dataProvider {

	@Test(dataProvider="values", enabled=false)
	public void getDataProviderValues(String firstValue, String secondValue){
		System.out.println(firstValue);
		System.out.println(secondValue);
	}
	
	//data provider which is in the same class
	@DataProvider(name ="values")
	public Object[][] valuesList(){
		
		Object data[][] = new Object[2][2];
		data[0][0]="first";
		data[0][1]="first-second";
		data[1][0]="second";
		data[1][1]="second-second";
		return data;
	}
	
	
	
	//data provider which is in the separate class
	@Test(dataProvider="anotherClass", dataProviderClass=dataProviderInSeparateClass.class)
	public void getDataProviderFromAnotherClass(String slno, String name, String age, String phoneNumber){
		System.out.print(slno);
		System.out.print(" "+name);
		System.out.print(" "+age);
		System.out.print(" "+phoneNumber);
		System.out.println("");
		
	}
	
	
	@DataProvider(parallel=true)
	public Object [][] seachKeywords(){
		return new Object[][] {{"firstKeyword"},{"secondKeyword"}, {"thirdKeyword"}};
	}
	

	@Test(dataProvider="seachKeywords")
	public void multipeRuns(String value) throws IOException{
		
		findBrokenLinks links = new findBrokenLinks();
		links.setUp("chrome");
		
		links.driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(value);
	}
	
	
	
	
	
	
}
