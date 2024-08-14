package com.qa.gorest.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.basetest.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;
import io.restassured.response.Response;

public class CreateUserWithDataProviderTest extends BaseTest {
	@BeforeMethod
	public void createUserTestSetUP() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@DataProvider
	public Object[][] CreateUserProvider() {
		return new Object[][] {
			{"Nitya",StringUtils.getRandomEmail(),"female","active"},
			{"Riyansh",StringUtils.getRandomEmail(),"male","inactive"},
			{"Abhirami",StringUtils.getRandomEmail(),"female","active"},
		};
	}
	
	@Test(dataProvider = "CreateUserProvider")
	public void createUserTest(String name,String email,String gender,String status) {
		
		User user = new User(name, StringUtils.getRandomEmail(), gender, status);
		Integer userid = restClient.post(GOREST_ENDPOINT, "json", user, true, true).then().log().all().
		statusCode(201).and().extract().path("id");
		System.out.println(userid);
		
		//get user
		RestClient getUserRestClient = new RestClient(prop, baseURI);
		Response getResp = getUserRestClient.get(GOREST_ENDPOINT+"/"+userid, true, true);
		int code = getResp.statusCode();
		System.out.println(code);
		
	

}}
