package com.jenkins.TestCases;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;
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
	@Test
	public void json(){
		JSONObject object = new JSONObject();
		object.put("Name", "Satya");
		
		JSONObject details = new JSONObject();
		details.put("Phone", "Iphone");
		details.put("Age", "28");
		details.put("Org", "Infy");
		
		JSONArray arrayValues = new JSONArray();
		
		arrayValues.put(0, details);
		
		details = new JSONObject();
		details.put("Phone", "Andriod");
		details.put("Age", "28");
		details.put("Org", "Infy");
		arrayValues.put(1, details);
		
		object.put("details",arrayValues);
		
		System.out.println(object);
	}
	

}
