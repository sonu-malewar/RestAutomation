package com.qa.gorest.test;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.basetest.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpsStatus;
import com.qa.gorest.utils.XmlPathValidator;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CircuitTest  extends BaseTest{
	@BeforeMethod
	public void circuitSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	@Test
	public void getCircuits() {
		RestAssured.baseURI = "http://ergast.com";
		Response circuitResp = restClient.get(CIRCUIT_API+"/api/f1/2010/circuits", false, false);
		//String xmlStrResp = circuitResp.getBody().asString();
		Integer statusCode= circuitResp.statusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, APIHttpsStatus.OK_200.getCode());
		
		XmlPathValidator xmlPath = new XmlPathValidator();
		String locality = xmlPath.read(circuitResp,"**.findAll {it.@circuitId == 'bahrain'}.Location.Locality");
		System.out.println(locality);
		Assert.assertEquals(locality,"Sakhir");
		
	}
}
