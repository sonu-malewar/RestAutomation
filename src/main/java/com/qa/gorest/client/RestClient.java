package com.qa.gorest.client;

import java.util.Map;
import static org.hamcrest.Matchers.*;
import java.util.Properties;

import com.qa.gorest.exception.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private RequestSpecBuilder specBuilder;
	private Properties prop;
	private String baseURI;
	private Boolean isAuthorizationAdded = false;

	public RestClient(Properties prop, String baseURI) {
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}

	public void addAuthorization() {
		if (!isAuthorizationAdded) {
			specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenID"));
			isAuthorizationAdded = true;
		}
	}

	private void setRequestContentType(String contentType) {

		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;

		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;

		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;

		default:
			throw new APIFrameworkException("Invalid content type");
		}
	}

	public RequestSpecification createRequestSpec(Boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorization();
		}
		return specBuilder.build();
	}

	public RequestSpecification createRequestSpec(Boolean includeAuth, Map<String, String> headersMap) {
		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorization();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	public RequestSpecification createRequestSpec(Boolean includeAuth, Map<String, String> headersMap,
			Map<String, Object> queryParamsMap) {
		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorization();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (queryParamsMap != null) {
			specBuilder.addQueryParams(queryParamsMap);
		}
		return specBuilder.build();
	}

	public RequestSpecification createRequestSpec(Boolean includeAuth, Object requestBody, String contentType) {
		specBuilder.setBaseUri(baseURI);
		addAuthorization();
		setRequestContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}

		return specBuilder.build();
	}

	public RequestSpecification createRequestSpec(Boolean includeAuth, Object requestBody, String contentType,
			Map<String, String> headersMap) {
		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorization();
		}
		setRequestContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}

		return specBuilder.build();
	}

	// get http methods
	public Response get(String serviceUrl,Boolean includeAuth, Boolean log) {

		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);
	}

	/**
	 * get http methods with headersMap
	 * 
	 * @param serviceUrl
	 * @param headersMap
	 * @param log
	 * @return Response
	 */

	public Response get(String serviceUrl, Map<String, String> headersMap,Boolean includeAuth,Boolean log) {

		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth, headersMap)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth, headersMap)).when().get(serviceUrl);
	}

	/**
	 * get http methods with headersMap and QueryParams
	 * 
	 * @param serviceUrl
	 * @param headersMap
	 * @param queryParams
	 * @param log
	 * @return Response
	 */

	public Response get(String serviceUrl, Map<String, String> headersMap,
			Map<String, Object> queryParams,Boolean includeAuth,Boolean log) {

		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth, headersMap, queryParams)).log().all().when()
					.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth, headersMap, queryParams)).when().get(serviceUrl);
	}

	/**
	 * post method with requestBody,contentType and headerMap
	 * 
	 * @param serviceUrl
	 * @param requestBody
	 * @param contentType
	 * @param log
	 * @return
	 */
	public Response post(String serviceUrl, String contentType, Object requestBody,Boolean includeAuth, Boolean log) {

		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth, requestBody, contentType)).log().all().when()
					.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth, requestBody, serviceUrl)).when().post(serviceUrl);
	}

	public Response post(Boolean includeAuth, String serviceUrl, Object requestBody, String contentType,
			Map<String, String> headerMap, Boolean log) {

		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth, requestBody, contentType, headerMap)).log().all()
					.when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth, requestBody, contentType, headerMap)).when()
				.post(serviceUrl);
	}

	/**
	 * put method with requestBody,contentType and headerMap
	 * 
	 * @param serviceUrl
	 * @param requestBody
	 * @param contentType
	 * @param log
	 * @return
	 */
	public Response put(Boolean includeAuth, String serviceUrl, Object requestBody, String contentType, Boolean log) {

		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth, requestBody, serviceUrl)).log().all().when()
					.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth, requestBody, serviceUrl)).when().put(serviceUrl);
	}

	public Response put(Boolean includeAuth, String serviceUrl, Object requestBody, String contentType,
			Map<String, String> headerMap, Boolean log) {

		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth, requestBody, contentType, headerMap)).log().all()
					.when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth, requestBody, contentType, headerMap)).when()
				.put(serviceUrl);
	}

	/**
	 * Delete http method
	 * 
	 * @param serviceUrl
	 * @param log
	 * @return
	 */
	public Response Delete(Boolean includeAuth, String serviceUrl, Boolean log) {

		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth)).when().delete(serviceUrl);
	}
	static String accessToken;
	public String getAccessToken(String serviceURL,String grantType,String clientId,String clientSecret) {
		 RestAssured.baseURI = "https://test.api.amadeus.com";
		 
		 accessToken = RestAssured.given().contentType(ContentType.URLENC).
		formParam("grant_type",grantType).
		formParam("client_id",clientId).
		formParam("client_secret",clientSecret)
		.when().post(serviceURL	).
		then().log().all().extract().path("access_token");
		System.out.println("accesstoken is"+accessToken);
		return accessToken;
		}

}
