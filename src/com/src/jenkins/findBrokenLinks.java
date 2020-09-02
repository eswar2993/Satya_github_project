package com.src.jenkins;


import java.awt.TrayIcon.MessageType;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.internal.runners.statements.Fail;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.TestResult;

public class findBrokenLinks {
		
	WebDriver driver=null;
	Properties properties;
	WebDriverWait wait = new WebDriverWait(driver, 10);
	Wait fluentWait = new FluentWait(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(2)).ignoring(TimeoutException.class);


	//Static variables
	public static String chromePath = "chromePath";
	public static String MozillaPath = "mozillaPath";
	public static String URL = "url";

	@Parameters({"browser"})
	@BeforeClass
	public void setUp(String browserName)throws IOException{
		String filePath = System.getProperty("user.dir")+"\\applicationProperties.properties";
		FileInputStream file = new FileInputStream(filePath);
		properties = new Properties();
		properties.load(file);
		
		if(browserName.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", properties.getProperty(chromePath));
			driver = new ChromeDriver();
		}else{
			System.setProperty("webdriver.gecko.driver", properties.getProperty(MozillaPath));
			driver = new FirefoxDriver();
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
	
	@AfterClass()
	public void closeSession(){
		driver.quit();
	}
	

}
