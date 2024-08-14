package com.qa.gorest.utils;


import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.exception.APIFrameworkException;
import io.restassured.response.Response;

public class JsonPathValidator {
	
	public String getJsonResponsAsString(Response response) {
		
		return response.getBody().asString();
		
	}
	
	public <T> T read(Response response,String jsonPath) {
		String stringResponse = getJsonResponsAsString(response);
		try {
		    return JsonPath.read(stringResponse, jsonPath);
		}
		catch(PathNotFoundException e){
			e.printStackTrace();
			throw new APIFrameworkException(jsonPath+" not found");
		}
	}
	
	public <T> List<T> readList(Response response,String jsonPath) {
		String stringResponse = getJsonResponsAsString(response);
		try {
		    return JsonPath.read(stringResponse, jsonPath);
		}
		catch(PathNotFoundException e){
			e.printStackTrace();
			throw new APIFrameworkException(jsonPath+" not found");
		}
	}
	
	public <T> List<Map<String,T>> readListofMaps(Response response,String jsonPath) {
		String stringResponse = getJsonResponsAsString(response);
		try {
		    return JsonPath.read(stringResponse, jsonPath);
		}
		catch(PathNotFoundException e){
			e.printStackTrace();
			throw new APIFrameworkException(jsonPath+" not found");
		}
	}

}
