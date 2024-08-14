package com.qa.gorest.test;

import org.hamcrest.object.HasEqualValues;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.basetest.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpsStatus;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest {
	
	@BeforeMethod
	public void getUsersetUp() {
		restClient = new RestClient(prop, baseURI);
	}

	@Test
	public void getUserTest() {
		restClient.get(GOREST_ENDPOINT, true, true).then().log().all().statusCode(APIHttpsStatus.OK_200.getCode());

	}

	@Test
	public void getSpecificUserTest() {
		restClient.get(GOREST_ENDPOINT+"/"+"7322562", false, true).then().log().all().statusCode(APIHttpsStatus.OK_200.getCode()).body("status",
				equalTo("inactive"));
	}

	@Test
	public void getSpecificUserWithQueryParamTest() {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("name", "padma");
		queryMap.put("gender", "female");
		restClient.get(GOREST_ENDPOINT, null, queryMap, true, true).then().log().all().body("name", equalTo("padma")).statusCode(APIHttpsStatus.OK_200.getCode());
	}

}
