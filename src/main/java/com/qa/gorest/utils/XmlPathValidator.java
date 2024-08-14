package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XmlPathValidator {

	public XmlPath getXmpPath(Response response) {
		String responseBody = response.getBody().asString();
		return new XmlPath(responseBody);
	}
	
	public <T> T read(Response response,String xpathExpression) {
		XmlPath xmlPath= getXmpPath(response);
		return xmlPath.get(xpathExpression);
	}
	
	public <T> List<T> readList(Response response,String xpathExpression) {
		XmlPath xmlPath= getXmpPath(response);
		return xmlPath.get(xpathExpression);
	}
	
	public <T> List<Map<String,T>> readListofMaps(Response response,String xpathExpression) {
		XmlPath xmlPath= getXmpPath(response);
		return xmlPath.get(xpathExpression);
	}

}
