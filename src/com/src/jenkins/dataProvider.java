package com.src.jenkins;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
	
}
