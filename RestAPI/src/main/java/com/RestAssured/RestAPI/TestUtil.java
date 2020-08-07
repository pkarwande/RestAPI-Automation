package com.RestAssured.RestAPI;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {
	
	public static String getValueByJPath (JSONObject responseJSON, String JPath)
	{
		Object obj = responseJSON;
		for(String s : JPath.split("/"))
			if(!s.isEmpty())
			if(!(s.contains("[") || s.contains("]")))	
			obj = ((JSONObject)obj).get(s);
			else if(!(s.contains("[") || s.contains("]")))	
			obj = ((JSONArray) ((JSONObject) obj).getJSONArray(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]" , "")));
			return obj.toString();
	}

}
