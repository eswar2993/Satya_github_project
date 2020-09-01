package com.src.jenkins;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class findBrokenLinks {
	
	static WebDriver driver;
	Properties properties;
	
	
	//Static variables
	public static String chromePath = "chromePath";
	public static String URL = "url";
	
	
	@BeforeTest
	public void loadProperties() throws IOException{
		
		String filePath = System.getProperty("user.dir")+"\\applicationProperties.properties";
		FileInputStream file = new FileInputStream(filePath);
		properties = new Properties();
		properties.load(file);
		
	}

	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName){
		if(browserName.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", properties.getProperty(chromePath));
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(properties.getProperty(URL));
	}
	
	
	@Test()
	public void validateBrokenLinks() throws IOException{
		
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("hi");
		
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		//elements.addAll(driver.findElements(By.tagName("img")));
		
		for(WebElement element:elements){
			URL url = new URL (element.getAttribute("href"));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.connect();
			int responseCode = connection.getResponseCode();
			if(String.valueOf(responseCode).startsWith("2"))
				Reporter.log("Link "+element+" is not a broken link");
			else if(String.valueOf(responseCode).startsWith("4") || String.valueOf(responseCode).startsWith("5"))
				Reporter.log("Link "+element+" is a broken link");
		}
		
	}
	
	
	@AfterTest()
	public void closeSession(){
		driver.close();
	}
	

}
