package com.jenkins.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	public static Properties prop;
	public static String CURRENT_URL = "url";
	public static String CURRENT_STATUS_CODE = "currentStatusCode";
	public static String CURRENT_RESPONSE= "currentResponse";
	public static Map<String,String> headerValues = new LinkedHashMap<String,String>();
	
	protected RestClient(){
		try {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\jenkins\\config\\applicationproperties.properties");
			prop = new Properties();
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void getCall(String extension) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpGet httpget = new HttpGet(prop.getProperty(CURRENT_URL)+extension);
		
		CloseableHttpResponse httpresponse = httpclient.execute(httpget);
		
		
		int statusCode = httpresponse.getStatusLine().getStatusCode();
		prop.setProperty(CURRENT_STATUS_CODE, String.valueOf(statusCode));
		
		String response = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
		prop.setProperty(CURRENT_RESPONSE, String.valueOf(new JSONObject(response)));
		
		Header[]  headers = httpresponse.getAllHeaders();
		for(Header header:headers){
			headerValues.put(header.getName(), header.getValue());
		}

	}
	
	
	public static void getCall(String extension, HashMap<String,String> headers) throws ClientProtocolException, IOException{
		
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpcall = new HttpGet(prop.getProperty(CURRENT_URL)+extension);
		
		for(Map.Entry<String, String> header: headers.entrySet()){
			httpcall.addHeader(header.getKey(), header.getValue());
		}
		
		CloseableHttpResponse response = httpClient.execute(httpcall);
		
		
		prop.setProperty(CURRENT_STATUS_CODE, String.valueOf(response.getStatusLine().getStatusCode()));
		prop.setProperty(CURRENT_RESPONSE, EntityUtils.toString(response.getEntity(),"UTF-8"));
		
		Header[]  headerValue = response.getAllHeaders();
		headerValues = new HashMap<String,String>();
		for(Header header:headerValue){
			headerValues.put(header.getName(), header.getValue());
		}
		
	}
	
	public static void postCall(String extension, HashMap<String,String> Headers, JSONObject Payload) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httppost = new HttpPost(prop.get(CURRENT_URL)+extension);
		
		for(Map.Entry<String, String> header: Headers.entrySet()){   // Headers
			httppost.addHeader(header.getKey(), header.getValue());
		}
		
		httppost.setEntity(new StringEntity(Payload.toString()));  // Payload
		
		CloseableHttpResponse response = httpClient.execute(httppost);
		
		prop.setProperty(CURRENT_STATUS_CODE, String.valueOf(response.getStatusLine().getStatusCode()));
		prop.setProperty(CURRENT_RESPONSE, EntityUtils.toString(response.getEntity()));
		
		Header[] currentHeader = response.getAllHeaders();
		headerValues = new HashMap<String,String>();
		
		for(Header currentHeaders: currentHeader){
			headerValues.put(currentHeaders.getName(), currentHeaders.getValue());
		}
	}
	
	

	
	
	
	
	

}
