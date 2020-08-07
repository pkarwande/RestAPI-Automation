package com.RestAssured.RestAPI;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.RestAPI.TestData.Users;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostAPITest extends TestBase{
	
	TestBase testBase;
	RestClient restClient;
	String serviceurl;
	String apiurl;
	String url;
	CloseableHttpResponse closeableHttpRes;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException
	{
		testBase = new TestBase();
		 serviceurl = prop.getProperty("URL");
		 apiurl = prop.getProperty("serviceURL");
		 url = serviceurl+apiurl;
	
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException, ClientProtocolException
	{
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content_Type", "application/json");
		
		//Jackson API
		ObjectMapper objmapper = new ObjectMapper();
		Users users = new Users("morphus", "leader");
		
		//object to json file
		objmapper.writeValue(new File("D:\\E-Commerce\\RestAPI\\src\\test\\java\\com\\RestAPI\\TestData\\users.json"), users);
		
		//object to json in string
		String usersJsonString = objmapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		closeableHttpRes = restClient.post(url,usersJsonString, headerMap);
		
		//Print Status Code
		int statusCode = closeableHttpRes.getStatusLine().getStatusCode();
		System.out.println("Status Code is : " + statusCode);
		Assert.assertEquals(statusCode, 201);
		
		//Print JSON Response
		String responseString = EntityUtils.toString(closeableHttpRes.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Response JSON payload is : " + responseJSON);
		
		//json to java object
		Users userResObj = objmapper.readValue(responseString, Users.class);
		System.out.println(userResObj);
		//System.out.println(users.getName().equals(userResObj.getName())); Not getting name and job in response but if we get this is the way to validate
		//System.out.println(users.getJob().equals(userResObj.getJob()));
		System.out.println(userResObj.getId());
		System.out.println(userResObj.getCreatedAt());
		
		// Print all Headers in the Response
		Header[] headerArray = closeableHttpRes.getAllHeaders();
		HashMap <String, String> allHeaders = new HashMap<String, String>();
		for(Header header : headerArray)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("All Headres are : " + allHeaders);
		
		
	}
}
