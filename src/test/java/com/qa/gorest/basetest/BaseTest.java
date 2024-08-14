package com.qa.gorest.basetest;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	public static final String GOREST_ENDPOINT = "/public/v2/users";
	public static final String AMADEUS_TOKEN ="/v1/security/oauth2/token";
	public static final String AMADEUS_FLIGHT ="/v1/shopping/flight-destinations";
	public static final String CIRCUIT_API = "http://ergast.com";
	
	protected ConfigurationManager configManager;
	protected Properties prop;
	protected RestClient restClient;
	protected String baseURI;
	
    @Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI) {
		RestAssured.filters(new AllureRestAssured());
	   configManager = new ConfigurationManager();
	   prop = configManager.initProp();
	   this.baseURI = baseURI;
	   restClient =  new RestClient(prop, baseURI);
	
	}

}
