package com.qa.gorest.test;



import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.gorest.basetest.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpsStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;

import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class CreateUserTest extends BaseTest {
	public RestClient restClient;
	
	@BeforeMethod
	public void createUserSetup() {
		 restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void getUserTest() {
		
		User user = new User("annali", StringUtils.getRandomEmail(), "female", "active");
		
		Integer userID = restClient.post("/public/v2/users", "json", user,true,true).then().log().all().assertThat()
				.statusCode(201).extract().path("id");
		
		System.out.println(userID);
	
		RestClient getRestClient = new RestClient(prop, baseURI);
		
		getRestClient.get("/public/v2/users/"+userID , true, true).then().log().all().statusCode((APIHttpsStatus.OK_200.getCode())).body("id",
					equalTo(userID));
		}
	@Test
	public void getUserAPISchemaTest() {
		
		User user = new User("annali", StringUtils.getRandomEmail(), "female", "active");
		
		 restClient.post("/public/v2/users", "json", user,true,true).then().log().all().assertThat()
				.statusCode(APIHttpsStatus.CREATED_201.getCode()).body(matchesJsonSchemaInClasspath("getuserschema.json"));
		
	
		

		
			
		
	}

}
