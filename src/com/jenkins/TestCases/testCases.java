package com.jenkins.TestCases;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.jenkins.Utils.RestClient;

public class testCases extends RestClient{
	
	testCases() {
		super();
	}

	@Test
	public void executeGetCall() throws ClientProtocolException, IOException{
		getCall("api/users");
		
		System.out.println("Curret Response Code: "+prop.get(CURRENT_STATUS_CODE));
		System.out.println("Current Response: "+prop.getProperty(CURRENT_RESPONSE));
		System.out.println("Current Headers: "+headerValues);
		
		Reporter.log("Curret Response Code: "+prop.get(CURRENT_STATUS_CODE));
		Reporter.log("Current Response: "+prop.getProperty(CURRENT_RESPONSE));
		Reporter.log("Current Headers: "+headerValues);
		
	}
	
	
	

}
