package com.qa.gorest.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.qa.gorest.basetest.BaseTest;
import com.qa.gorest.client.RestClient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class GetFlightsTest extends BaseTest{
	
	 private String accessToken;
	 
	@Parameters({"baseURI","grantType","clientId","clientSecret"})
	@BeforeMethod
	public void flightsetUp(String baseURI,String grantType,String clientId,String clientSecret  ) {
		restClient = new RestClient(prop, baseURI);
		accessToken = restClient.getAccessToken(AMADEUS_TOKEN, grantType, clientId, clientSecret);
	}
	
	@Test
	public void getFilghtData() {
		RestClient restClientFight =  new RestClient(prop, baseURI);
		Map<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Authorization", "Bearer "+ accessToken);
		
		Map<String,Object> queryParam = new HashMap<String,Object>();
		queryParam.put("origin", "PAR");
		queryParam.put("maxPrice",200);
		Response flightResponse= restClientFight.get(AMADEUS_FLIGHT, headerMap, queryParam, false, true).
				then().log().all().statusCode(200).extract().response();
		
		JsonPath js = flightResponse.jsonPath();
	
		String flighType =js.get("data[0].type");
		Assert.assertEquals(flighType, "flight-destination");
		
	}

}
