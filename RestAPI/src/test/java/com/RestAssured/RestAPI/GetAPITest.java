package com.RestAssured.RestAPI;

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

public class GetAPITest extends TestBase{
	
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
	
	@Test(priority=1)
	public void getTestwithHeaders() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		closeableHttpRes = restClient.get(url);
		
				//Print Status Code
				int statusCode = closeableHttpRes.getStatusLine().getStatusCode();
				System.out.println("Status Code is : " + statusCode);
				Assert.assertEquals(statusCode, 200);
				
				//Print JSON Response
				String responseString = EntityUtils.toString(closeableHttpRes.getEntity(), "UTF-8");
				JSONObject responseJSON = new JSONObject(responseString);
				System.out.println("Response JSON payload is : " + responseJSON);
				//Printing single values
				//per page
				String perPageValue = TestUtil.getValueByJPath(responseJSON, "/per_page");
				System.out.println("Per Page value is : " + perPageValue);
				Assert.assertEquals(perPageValue, "6");
				
				//total
				String totalValue = TestUtil.getValueByJPath(responseJSON, "/total");
				System.out.println("Total value is : " + totalValue);
				Assert.assertEquals(totalValue, "12");
				
				//Printing JSON Array 
				//data
				/* String lastName = TestUtil.getValueByJPath(responseJSON, "/data[0]/last_name");
				String avatar = TestUtil.getValueByJPath(responseJSON, "/data[0]/avatar");
				String id = TestUtil.getValueByJPath(responseJSON, "/data[0]/id");
				String firstName = TestUtil.getValueByJPath(responseJSON, "/data[0]/first_name");
				
				System.out.println("Last Name :" +lastName + "\n" + "First Name is :" +firstName + "\n" + "Avatar is :" +avatar +"\n" + "Id is :" +id);*/
				
				// Print all Headers in the Response
				Header[] headerArray = closeableHttpRes.getAllHeaders();
				HashMap <String, String> allHeaders = new HashMap<String, String>();
				for(Header header : headerArray)
				{
					allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("All Headres are : " + allHeaders);
				
	}
	
	@Test(priority=2)
	public void getTestwithoutHeaders() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content_Type", "application/json");
		
		closeableHttpRes = restClient.get(url, headerMap);
		
				//Print Status Code
				int statusCode = closeableHttpRes.getStatusLine().getStatusCode();
				System.out.println("Status Code is : " + statusCode);
				Assert.assertEquals(statusCode, 200);
				
				//Print JSON Response
				String responseString = EntityUtils.toString(closeableHttpRes.getEntity(), "UTF-8");
				JSONObject responseJSON = new JSONObject(responseString);
				System.out.println("Response JSON payload is : " + responseJSON);
				//Printing single values
				//per page
				String perPageValue = TestUtil.getValueByJPath(responseJSON, "/per_page");
				System.out.println("Per Page value is : " + perPageValue);
				Assert.assertEquals(perPageValue, "6");
				
				//total
				String totalValue = TestUtil.getValueByJPath(responseJSON, "/total");
				System.out.println("Total value is : " + totalValue);
				Assert.assertEquals(totalValue, "12");
				
				//Printing JSON Array 
				//data
				/* String lastName = TestUtil.getValueByJPath(responseJSON, "/data[0]/last_name");
				String avatar = TestUtil.getValueByJPath(responseJSON, "/data[0]/avatar");
				String id = TestUtil.getValueByJPath(responseJSON, "/data[0]/id");
				String firstName = TestUtil.getValueByJPath(responseJSON, "/data[0]/first_name");
				
				System.out.println("Last Name :" +lastName + "\n" + "First Name is :" +firstName + "\n" + "Avatar is :" +avatar +"\n" + "Id is :" +id);*/
				
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
